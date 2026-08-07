package com.soc.agent.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import com.soc.agent.R
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.UnlockHistoryEntity
import com.soc.agent.security.AppLockPolicy
import com.soc.agent.ui.AppLockGateActivity
import com.soc.agent.utils.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

/**
 * Foreground watcher that enforces per-app locks.
 *
 * Polls [UsageStatsManager] for the currently visible app; whenever a locked
 * package comes to the foreground it launches the configured unlock gate
 * ([AppLockGateActivity], which shows the PIN or pattern prompt). A short
 * grace window after a successful unlock keeps the app usable until it leaves
 * the foreground again, and a static [gateVisible] flag prevents re-launching
 * the gate while it is already on screen.
 *
 * Requires the PACKAGE_USAGE_STATS special access permission (user-granted in
 * system settings — see [hasUsageAccess]); without it the watcher cannot see
 * other apps and degrades to a no-op with the permission notice surfaced in
 * the App Lock dashboard.
 */
class AppLockService : Service() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var watcherJob: Job? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        ensureChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Prefs.appLockWatcherRunning = true
        if (watcherJob == null || watcherJob?.isActive != true) {
            watcherJob = scope.launch { watchLoop() }
        }
        // Keep the service alive if the system kills it while locks are enabled.
        return START_STICKY
    }

    override fun onDestroy() {
        watcherJob?.cancel()
        watcherJob = null
        Prefs.appLockWatcherRunning = false
        super.onDestroy()
    }

    // ------------------------------------------------------------------ loop --

    private suspend fun watchLoop() {
        if (!hasUsageAccess(this)) {
            Log.w(TAG, "AppLockService: PACKAGE_USAGE_STATS not granted — watcher idle")
            return
        }
        val usm = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        var hotSet = AppLockPolicy.loadEnabledPackages(this)
        var lastPackage = ""
        val currentlyUnlocked = mutableSetOf<String>()
        while (scope.isActive) {
            val current = foregroundPackage(usm)
            if (current != null && current != lastPackage) {
                hotSet = AppLockPolicy.loadEnabledPackages(this)
                if (AppLockPolicy.shouldGate(this, current, hotSet)) {
                    // App just came to foreground - check if it was previously unlocked
                    if (!currentlyUnlocked.contains(current)) {
                        triggerGate(current)
                    } else {
                        // App was unlocked, record foreground time for auto-lock
                        recordForeground(current)
                    }
                } else {
                    // Not a locked app, clear any unlock state
                    currentlyUnlocked.remove(current)
                }
                lastPackage = current
            }
            // Check for auto-lock on apps that are unlocked but no longer in foreground
            val toRemove = mutableSetOf<String>()
            for (pkg in currentlyUnlocked) {
                if (pkg != current && shouldAutoLock(this, pkg)) {
                    currentlyUnlocked.remove(pkg)
                    recentUnlocks.remove(pkg)
                    lastForegroundTime.remove(pkg)
                }
            }
            delay(POLL_INTERVAL_MS)
        }
    }

    /** Returns the package currently in the foreground, or null when unknown. */
    private fun foregroundPackage(usm: UsageStatsManager): String? {
        return runCatching {
            val end = System.currentTimeMillis()
            val start = end - WINDOW_MS
            val events = usm.queryEvents(start, end)
            var foreground: String? = null
            val event = UsageEvents.Event()
            while (events.hasNextEvent()) {
                events.getNextEvent(event)
                if (event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                    foreground = event.packageName
                } else if (event.eventType == UsageEvents.Event.MOVE_TO_BACKGROUND) {
                    if (event.packageName == foreground) foreground = null
                }
            }
            foreground
        }.getOrNull()
    }

    // ---------------------------------------------------------------- trigger --

    private fun triggerGate(packageName: String) {
        if (gateVisible) return
        val now = System.currentTimeMillis()
        val lastUnlock = recentUnlocks[packageName] ?: 0L
        val lockDelay = Prefs.lockDelay
        if (now - lastUnlock < lockDelay) return

        gateVisible = true
        val gate = AppLockPolicy.gateFor() ?: run {
            gateVisible = false
            return
        }
        val intent = Intent(this, AppLockGateActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_VERIFY)
            putExtra(AppLockGateActivity.EXTRA_PACKAGE, packageName)
            putExtra(AppLockGateActivity.EXTRA_GATE, gate)
        }
        runCatching { startActivity(intent) }
            .onFailure { e ->
                Log.w(TAG, "Failed to launch gate for $packageName: ${e.message}")
                gateVisible = false
            }
    }

    // ------------------------------------------------------------- notification --

    private fun buildNotification() =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setContentTitle(getString(R.string.applock_notification_title))
            .setContentText(getString(R.string.applock_notification_text))
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.applock_channel_name),
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = getString(R.string.applock_channel_desc) }
            nm.createNotificationChannel(channel)
        }
    }

    // ------------------------------------------------------------- companion --

    companion object {
        private const val TAG = "AppLockService"
        private const val CHANNEL_ID = "app_lock"
        private const val NOTIFICATION_ID = 0x4C4B // "LK"
        private const val POLL_INTERVAL_MS = 600L
        private const val WINDOW_MS = 30_000L

        /** True while the unlock gate activity is on screen (prevents relaunch). */
        @Volatile
        var gateVisible: Boolean = false

        /** package -> last successful unlock time, per grace window. */
        private val recentUnlocks = ConcurrentHashMap<String, Long>()

        /** package -> last time the app was in foreground (for auto-lock timeout). */
        private val lastForegroundTime = ConcurrentHashMap<String, Long>()

        /** Set of currently-unlocked packages (in foreground). */
        private val currentlyUnlocked = ConcurrentHashMap.newKeySet<String>()

        /** Records a successful unlock so the watcher leaves the app alone briefly. */
        fun recordUnlock(packageName: String, context: Context? = null, gateMethod: String = "", autoUnlock: Boolean = false) {
            recentUnlocks[packageName] = System.currentTimeMillis()
            lastForegroundTime[packageName] = System.currentTimeMillis()
            currentlyUnlocked.add(packageName)
            gateVisible = false

            // Record to unlock history (fire-and-forget)
            context?.let { ctx ->
                kotlinx.coroutines.CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDatabase.getInstance(ctx.applicationContext)
                    val appName = getAppLabel(ctx, packageName) ?: packageName
                    val record = UnlockHistoryEntity(
                        packageName = packageName,
                        appName = appName,
                        gateMethod = gateMethod,
                        timestamp = System.currentTimeMillis(),
                        autoUnlock = autoUnlock
                    )
                    db.unlockHistoryDao().insert(record)
                }
            }
        }

        private fun getAppLabel(context: Context, packageName: String): String? {
            return try {
                context.packageManager.getApplicationLabel(
                    context.packageManager.getApplicationInfo(packageName, 0)
                ).toString()
            } catch (e: Exception) {
                null
            }
        }

        /** Records that a locked app just went to foreground. */
        fun recordForeground(packageName: String) {
            lastForegroundTime[packageName] = System.currentTimeMillis()
        }

        /** Checks if a package should be re-locked based on auto-lock settings. */
        fun shouldAutoLock(context: Context, packageName: String): Boolean {
            val mode = Prefs.autoLockMode
            val now = System.currentTimeMillis()
            val lastTime = lastForegroundTime[packageName] ?: 0L
            // Also check if it's in the currently unlocked set
            if (packageName !in currentlyUnlocked) return true
            return when (mode) {
                "immediate" -> true // will re-lock as soon as it leaves foreground
                "screen_off" -> false // handled by screen-off broadcast receiver elsewhere
                "timeout" -> {
                    val timeout = Prefs.autoLockTimeout
                    now - lastTime >= timeout
                }
                else -> true
            }
        }

        /** True when the user granted the PACKAGE_USAGE_STATS special access. */
        fun hasUsageAccess(context: Context): Boolean {
            return try {
                val appOps = context.getSystemService(Context.APP_OPS_SERVICE)
                    as android.app.AppOpsManager
                val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    appOps.unsafeCheckOpNoThrow(
                        android.app.AppOpsManager.OPSTR_GET_USAGE_STATS,
                        android.os.Process.myUid(),
                        context.packageName
                    )
                } else {
                    @Suppress("DEPRECATION")
                    appOps.checkOpNoThrow(
                        android.app.AppOpsManager.OPSTR_GET_USAGE_STATS,
                        android.os.Process.myUid(),
                        context.packageName
                    )
                }
                mode == android.app.AppOpsManager.MODE_ALLOWED
            } catch (e: Exception) {
                // On devices without AppOps the usage permission can be inferred
                // from the manifest (pre-M).
                Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                    context.checkCallingOrSelfPermission(
                        "android.permission.PACKAGE_USAGE_STATS"
                    ) == PackageManager.PERMISSION_GRANTED
            }
        }

        /** Deep link into the usage-access settings page. */
        fun usageAccessIntent(): Intent =
            Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
    }
}
