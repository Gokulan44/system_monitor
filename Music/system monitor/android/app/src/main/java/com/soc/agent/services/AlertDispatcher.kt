package com.soc.agent.services

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.soc.agent.api.dto.AlertDto

/**
 * Pushes security alerts locally (as a notification) and returns an [AlertDto]
 * that the repository will forward to the SOC server for persistence and
 * operator notification.
 */
object AlertDispatcher {

    private const val CHANNEL_ID = "soc_alerts"
    private const val CHANNEL_NAME = "Security alerts"
    private var channelCreated = false

    /**
     * Records an alert. A heads-up notification is posted when the
     * POST_NOTIFICATIONS runtime permission is granted (Android 13+); on older
     * versions no permission is required. Always returns the [AlertDto] for sync.
     */
    fun push(context: Context, level: String, title: String, message: String): AlertDto {
        ensureChannel(context)

        val canNotify = Build.VERSION.SDK_INT < 33 ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) ==
            PackageManager.PERMISSION_GRANTED

        if (canNotify) {
            postNotification(context, level, title, message)
        }

        return AlertDto(level = level, title = title, message = message)
    }

    private fun ensureChannel(context: Context) {
        if (channelCreated) return
        synchronized(this) {
            if (channelCreated) return
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Security and policy alerts from the SOC agent"
                }
                nm.createNotificationChannel(channel)
            }
            channelCreated = true
        }
    }

    private fun postNotification(context: Context, level: String, title: String, message: String) {
        try {
            val importance = if (level == "critical" || level == "high") {
                NotificationCompat.PRIORITY_HIGH
            } else {
                NotificationCompat.PRIORITY_DEFAULT
            }
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setPriority(importance)
                .setAutoCancel(true)
                .build()
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.notify(System.currentTimeMillis().toInt(), notification)
        } catch (e: Exception) {
            // Notification posting must never crash telemetry collection.
            android.util.Log.w("AlertDispatcher", "Failed to post notification: ${e.message}")
        }
    }
}