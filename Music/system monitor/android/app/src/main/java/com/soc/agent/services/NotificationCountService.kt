package com.soc.agent.services

import android.app.Notification
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.NotificationCountEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * Captures all posted notifications for analytics.
 * Must be enabled by user in Settings > Notification access.
 *
 * Events:
 * - onNotificationPosted: records every notification with app name, title, category, priority, timestamps
 * - Retention: keeps last 30 days (cleanup on start)
 *
 * Foreground service not required (system binding handles lifecycle).
 */
class NotificationCountService : NotificationListenerService() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val appLabelCache = HashMap<String, String>()

    override fun onCreate() {
        super.onCreate()
        scope.launch {
            val db = AppDatabase.getInstance(applicationContext)
            // Cleanup old notifications (keep 30 days)
            val cutoff = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000)
            db.notificationCountDao().deleteOlderThan(cutoff)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Ignore system/ongoing/background notifications
        if (sbn.isOngoing) return
        if (sbn.packageName == packageName) return // ignore own notifications

        val notification = sbn.notification ?: return
        val extras = notification.extras ?: return

        // Skip low-priority / silent notifications
        val priority = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.priority
        } else {
            @Suppress("DEPRECATION")
            notification.priority
        }
        if (priority < Notification.PRIORITY_LOW) return

        val packageName = sbn.packageName
        val title = extras.getCharSequence(Notification.EXTRA_TITLE)?.toString()
        val category = notification.category
        val now = sbn.postTime
        val cal = Calendar.getInstance().apply { timeInMillis = now }

        scope.launch(Dispatchers.IO) {
            val db = AppDatabase.getInstance(applicationContext)
            val appName = appLabelCache.getOrPut(packageName) { getAppLabel(packageName) ?: packageName }

            val entity = NotificationCountEntity(
                packageName = packageName,
                appName = appName,
                title = title,
                category = category,
                priority = priority,
                postedAtMs = now,
                hourOfDay = cal.get(Calendar.HOUR_OF_DAY),
                dayOfWeek = cal.get(Calendar.DAY_OF_WEEK).let { if (it == Calendar.SUNDAY) 7 else it - 1 },
                dateInt = cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH),
                monthInt = cal.get(Calendar.YEAR) * 100 + (cal.get(Calendar.MONTH) + 1)
            )
            db.notificationCountDao().insert(entity)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // No-op — we only record postings
    }

    private fun getAppLabel(packageName: String): String? {
        return try {
            packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(packageName, 0)
            ).toString()
        } catch (_: Exception) {
            null
        }
    }

    companion object {
        /**
         * Check if notification listener is enabled for this app.
         * Returns true if user has granted Notification Access.
         */
        fun isEnabled(androidContext: android.content.Context): Boolean {
            val flat = android.provider.Settings.Secure.getString(
                androidContext.contentResolver,
                "enabled_notification_listeners"
            ) ?: return false
            val myComponent = "${androidContext.packageName}/com.soc.agent.services.NotificationCountService"
            return flat.contains(myComponent)
        }

        /**
         * Intent to open the Notification Access settings page for this app.
         */
        fun settingsIntent(androidContext: android.content.Context): Intent {
            return Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS").apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }
}