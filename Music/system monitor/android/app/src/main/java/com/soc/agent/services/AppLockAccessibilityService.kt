package com.soc.agent.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.soc.agent.data.LockSettings
import com.soc.agent.data.LockedAppsRepository
import com.soc.agent.database.AppDatabase
import kotlinx.coroutines.*

/**
 * AccessibilityService that detects foreground app changes for App Lock.
 * When a locked app comes to the foreground, broadcasts an intent to
 * launch the appropriate lock gate activity.
 *
 * Setup: User must enable this service in Settings → Accessibility → SOC Agent.
 *
 * Flow:
 *   1. onAccessibilityEvent(TYPE_WINDOW_STATE_CHANGED)
 *   2. Check if new app is locked via LockedAppsRepository
 *   3. If locked → broadcast LOCK_ACTIVITY intent
 *   4. AppLockGateActivity receives intent and shows gate
 */
class AppLockAccessibilityService : AccessibilityService() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val handler = Handler(Looper.getMainLooper())
    private var lastPackage: String? = null
    private var lastTimestamp: Long = 0

    // Debounce: ignore events within 300ms of each other for same package
    private companion object {
        const val TAG = "AppLockA11y"
        const val DEBOUNCE_MS = 300L
        const val ACTION_LOCK = "com.soc.agent.LOCK_APP"
        const val EXTRA_PACKAGE = "package_name"
        const val EXTRA_GATE_TYPE = "gate_type"
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "AccessibilityService connected")

        serviceInfo = serviceInfo.apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS
            notificationTimeout = 100
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val packageName = event.packageName?.toString() ?: return
        val now = System.currentTimeMillis()

        // Skip same app within debounce window
        if (packageName == lastPackage && now - lastTimestamp < DEBOUNCE_MS) return

        lastPackage = packageName
        lastTimestamp = now

        // Skip system UI, our own app, launchers
        if (isSystemPackage(packageName)) return

        // Check if app is locked (async to avoid blocking main thread)
        scope.launch {
            try {
                checkAndLock(packageName)
            } catch (e: Exception) {
                Log.e(TAG, "Error checking lock for $packageName", e)
            }
        }
    }

    private suspend fun checkAndLock(packageName: String) {
        val db = AppDatabase.getInstance(this@AppLockAccessibilityService)
        val repo = LockedAppsRepository.getInstance(db)
        val settings = LockSettings.getInstance(this@AppLockAccessibilityService)

        // Check if auto-lock is enabled
        if (!settings.isAutoLockEnabled) return

        // Check if app is locked
        if (!repo.isAppLocked(packageName)) return
        val gateType = "pin"

        // Broadcast to launch lock gate
        withContext(Dispatchers.Main) {
            val intent = Intent(ACTION_LOCK).apply {
                putExtra(EXTRA_PACKAGE, packageName)
                putExtra(EXTRA_GATE_TYPE, gateType)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            sendBroadcast(intent)
            Log.i(TAG, "Lock gate requested for $packageName (gate=$gateType)")
        }
    }

    private fun isSystemPackage(packageName: String): Boolean {
        return packageName.startsWith("com.android.systemui") ||
                packageName.startsWith("com.android.launcher") ||
                packageName.startsWith("com.google.android.apps.nexuslauncher") ||
                packageName == "com.soc.agent" ||
                packageName == packageName // own app check handled above
    }

    override fun onInterrupt() {
        Log.w(TAG, "AccessibilityService interrupted")
    }

    override fun onDestroy() {
        scope.cancel()
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
        Log.i(TAG, "AccessibilityService destroyed")
    }
}