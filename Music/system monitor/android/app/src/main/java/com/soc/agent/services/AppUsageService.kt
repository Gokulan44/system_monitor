package com.soc.agent.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.soc.agent.R
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.AppUsageEntity
import com.soc.agent.database.entity.DailyUsageEntity
import com.soc.agent.database.entity.LaunchCountEntity
import com.soc.agent.database.entity.UsageSessionEntity
import com.soc.agent.database.entity.UsageTimelineEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.ConcurrentHashMap

class AppUsageService : Service() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var watcherJob: Job? = null

    private val activeSessions = ConcurrentHashMap<String, Long>()
    private var currentForeground: String? = null

    override fun onCreate() {
        super.onCreate()
        ensureChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
        startWatcher()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (watcherJob?.isActive != true) {
            startWatcher()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        watcherJob?.cancel()
        closeAllActiveSessions()
        super.onDestroy()
    }

    private fun startWatcher() {
        watcherJob = scope.launch(Dispatchers.IO) {
            watchLoop()
        }
    }

    private suspend fun watchLoop() {
        if (!hasUsageAccess(this)) {
            Log.w(TAG, "AppUsageService: PACKAGE_USAGE_STATS not granted — watcher idle")
            return
        }
        val usm = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        var lastPackage = ""
        while (scope.isActive) {
            val current = foregroundPackage(usm)
            if (current != null && current != lastPackage) {
                if (lastPackage.isNotEmpty()) {
                    onAppBackground(lastPackage)
                }
                onAppForeground(current)
                lastPackage = current
            }
            delay(POLL_INTERVAL_MS)
        }
    }

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

    private fun onAppForeground(packageName: String) {
        val now = System.currentTimeMillis()
        val prevApp = currentForeground
        currentForeground = packageName
        scope.launch(Dispatchers.IO) {
            val db = AppDatabase.getInstance(applicationContext)
            val appName = getAppLabel(packageName) ?: packageName
            val session = UsageSessionEntity(
                packageName = packageName,
                appName = appName,
                startTimeMs = now,
                active = true
            )
            val sessionId = db.usageSessionDao().insert(session)
            activeSessions[packageName] = sessionId

            val appUsage = db.appUsageDao().getForPackage(packageName) ?: AppUsageEntity(
                packageName = packageName,
                appName = appName,
                firstUsedMs = now
            ).also { it.launchCount = 1; it.lastUsedMs = now }
            appUsage.launchCount++
            appUsage.lastUsedMs = now
            db.appUsageDao().insert(appUsage)

            val today = UsageTrackingHelper.getTodayDate()
            val dailyUsage = db.dailyUsageDao().getForPackageAndDate(packageName, today) ?: DailyUsageEntity(
                packageName = packageName,
                appName = appName,
                date = today,
                firstTimeMs = now
            ).also { it.launchCount = 1 }
            dailyUsage.launchCount++
            dailyUsage.lastTimeMs = now
            db.dailyUsageDao().insert(dailyUsage)

            UsageTrackingHelper.recordWeeklyLaunch(db, packageName, appName, now)
            UsageTrackingHelper.recordMonthlyLaunch(db, packageName, appName, now)

            val cal = Calendar.getInstance().apply { timeInMillis = now }
            val launchEntity = LaunchCountEntity(
                packageName = packageName,
                appName = appName,
                launchTimeMs = now,
                hourOfDay = cal.get(Calendar.HOUR_OF_DAY),
                dayOfWeek = cal.get(Calendar.DAY_OF_WEEK).let { if (it == Calendar.SUNDAY) 7 else it - 1 },
                dateInt = today,
                monthInt = cal.get(Calendar.YEAR) * 100 + (cal.get(Calendar.MONTH) + 1)
            )
            db.launchCountDao().insert(launchEntity)

            val timelineFore = UsageTimelineEntity(
                packageName = packageName,
                appName = appName,
                eventType = "foreground",
                eventTimeMs = now,
                hourOfDay = cal.get(Calendar.HOUR_OF_DAY),
                dayOfWeek = cal.get(Calendar.DAY_OF_WEEK).let { if (it == Calendar.SUNDAY) 7 else it - 1 },
                dateInt = today,
                durationMs = 0,
                previousApp = prevApp
            )
            db.usageTimelineDao().insert(timelineFore)

            val focusBlocked = db.focusModeDao().isPackageBlocked(packageName) > 0
            if (focusBlocked) {
                val intent = Intent("com.soc.agent.FOCUS_BLOCK").apply {
                    putExtra("package_name", packageName)
                    putExtra("app_name", appName)
                }
                sendBroadcast(intent)
            }

            val usedMs = dailyUsage.totalTimeMs
            val limit = db.dailyLimitDao().getForPackage(packageName)
            limit?.let { l ->
                if (l.enabled && l.limitMs > 0) {
                    if (usedMs >= l.limitMs && l.exceededAction == "block") {
                        val intent = Intent("com.soc.agent.LIMIT_EXCEEDED").apply {
                            putExtra("package_name", packageName)
                            putExtra("app_name", appName)
                            putExtra("used_ms", usedMs)
                            putExtra("limit_ms", l.limitMs)
                            putExtra("action", "block")
                        }
                        sendBroadcast(intent)
                    }
                }
            }
        }
    }

    private fun onAppBackground(packageName: String) {
        val now = System.currentTimeMillis()
        val sessionId = activeSessions.remove(packageName)
        sessionId?.let { id ->
            scope.launch(Dispatchers.IO) {
                val db = AppDatabase.getInstance(applicationContext)
                db.usageSessionDao().endSession(id, now)
                val session = db.usageSessionDao().getById(id)
                session?.let { s ->
                    val duration = now - s.startTimeMs
                    val appUsage = db.appUsageDao().getForPackage(packageName)
                    appUsage?.let {
                        it.totalTimeMs += duration
                        it.lastUsedMs = now
                        db.appUsageDao().insert(it)
                    }
                    val today = UsageTrackingHelper.getTodayDate()
                    val dailyUsage = db.dailyUsageDao().getForPackageAndDate(packageName, today)
                    dailyUsage?.let {
                        it.totalTimeMs += duration
                        it.lastTimeMs = now
                        db.dailyUsageDao().insert(it)
                    }

                    UsageTrackingHelper.updateScreenTimeBucket(db, duration, now, packageName)
                }
            }
        }
    }

    private fun closeAllActiveSessions() {
        val now = System.currentTimeMillis()
        activeSessions.forEach { (packageName, sessionId) ->
            scope.launch(Dispatchers.IO) {
                val db = AppDatabase.getInstance(applicationContext)
                db.usageSessionDao().endSession(sessionId, now)
            }
        }
        activeSessions.clear()
    }

    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setContentTitle(getString(R.string.app_usage_notification_title))
            .setContentText(getString(R.string.app_usage_notification_text))
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.app_usage_channel_name),
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = getString(R.string.app_usage_channel_desc) }
            nm.createNotificationChannel(channel)
        }
    }

    private fun getAppLabel(packageName: String): String? {
        return try {
            packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(packageName, 0)
            ).toString()
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val TAG = "AppUsageService"
        private const val CHANNEL_ID = "app_usage"
        private const val NOTIFICATION_ID = 0x5553
        private const val POLL_INTERVAL_MS = 1000L
        private const val WINDOW_MS = 30_000L

        fun hasUsageAccess(context: Context): Boolean {
            return try {
                val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as android.app.AppOpsManager
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
                Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                    context.checkCallingOrSelfPermission(
                        "android.permission.PACKAGE_USAGE_STATS"
                    ) == android.content.pm.PackageManager.PERMISSION_GRANTED
            }
        }
    }
}