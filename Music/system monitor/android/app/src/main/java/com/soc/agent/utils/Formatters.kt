package com.soc.agent.utils

import java.util.Locale

/**
 * Formatting helpers shared by the UI layer and the notification dispatcher.
 * All functions are null-tolerant so they can be applied directly to DTO fields.
 */
object Formatters {

    private const val KB = 1024L
    private const val MB = KB * 1024
    private const val GB = MB * 1024
    private const val TB = GB * 1024

    /**
     * Formats a byte count into a human readable string: 512 B, 2.4 KB, 1.2 GB...
     */
    fun bytes(bytes: Long?): String {
        if (bytes == null || bytes < 0) return "0 B"
        return when {
            bytes < KB -> "$bytes B"
            bytes < MB -> String.format(Locale.US, "%.1f KB", bytes / KB.toDouble())
            bytes < GB -> String.format(Locale.US, "%.1f MB", bytes / MB.toDouble())
            bytes < TB -> String.format(Locale.US, "%.2f GB", bytes / GB.toDouble())
            else -> String.format(Locale.US, "%.2f TB", bytes / TB.toDouble())
        }
    }

    /**
     * Formats a percentage value (0..100). Values outside the range are clamped.
     */
    fun pct(value: Double?): String {
        if (value == null) return "--"
        val clamped = value.coerceIn(0.0, 100.0)
        return String.format(Locale.US, "%.1f%%", clamped)
    }

    /**
     * Formats an epoch-millis timestamp as a short relative time ("just now",
     * "5m ago", "3h ago", "2d ago"). Returns "--" for 0/null inputs.
     */
    fun timeAgo(epochMillis: Long?): String {
        if (epochMillis == null || epochMillis <= 0L) return "--"
        val diff = System.currentTimeMillis() - epochMillis
        if (diff < 0) return "just now"
        val minutes = diff / 60_000L
        return when {
            minutes < 1 -> "just now"
            minutes < 60 -> "${minutes}m ago"
            minutes < 60 * 24 -> "${minutes / 60}h ago"
            else -> "${minutes / (60 * 24)}d ago"
        }
    }

    /**
     * Returns a short, safe-to-display prefix of a hex hash (first 8 chars).
     * Used for signatures and hashes in the UI.
     */
    fun shortHash(hash: String?): String {
        if (hash.isNullOrBlank()) return "n/a"
        return if (hash.length <= 8) hash else hash.substring(0, 8)
    }

    /**
     * Formats an uptime in seconds as a compact human string ("45s", "12m",
     * "3h 20m", "2d 5h"). Returns "--" for null/<=0 inputs.
     */
    fun uptime(seconds: Long?): String {
        if (seconds == null || seconds <= 0L) return "--"
        val days = seconds / 86400
        val hours = (seconds % 86400) / 3600
        val minutes = (seconds % 3600) / 60
        return when {
            days > 0 -> "${days}d ${hours}h"
            hours > 0 -> "${hours}h ${minutes}m"
            minutes > 0 -> "${minutes}m"
            else -> "${seconds}s"
        }
    }

    fun isoNow(): String {
        return java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(java.util.Date())
    }

    fun relativeTime(epochMillis: Long?): String = timeAgo(epochMillis)
}
