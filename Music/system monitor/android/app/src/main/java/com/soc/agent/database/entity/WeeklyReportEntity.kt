package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Generated weekly usage report. One row per report generation.
 * Stores pre-computed summaries for quick retrieval.
 */
@Entity(
    tableName = "weekly_report",
    indices = [
        Index(value = ["week_start"], unique = true),
        Index(value = ["generated_at"])
    ]
)
data class WeeklyReportEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    /** Week start epoch millis (Monday 00:00). */
    @ColumnInfo(name = "week_start")
    val weekStart: Long,

    /** Week end epoch millis (Sunday 23:59:59). */
    @ColumnInfo(name = "week_end")
    val weekEnd: Long,

    /** Total screen time for the week in ms. */
    @ColumnInfo(name = "total_screen_time_ms")
    val totalScreenTimeMs: Long,

    /** Total number of app launches. */
    @ColumnInfo(name = "total_launches")
    val totalLaunches: Int,

    /** Number of distinct apps used. */
    @ColumnInfo(name = "apps_used_count")
    val appsUsedCount: Int,

    /** Top 5 apps as "packageName:timeMs" CSV. */
    @ColumnInfo(name = "top_apps")
    val topApps: String,

    /** Most used app package name. */
    @ColumnInfo(name = "most_used_app")
    val mostUsedApp: String?,

    /** Most used app time in ms. */
    @ColumnInfo(name = "most_used_app_time_ms")
    val mostUsedAppTimeMs: Long = 0,

    /** Peak day (1=Mon..7=Sun). */
    @ColumnInfo(name = "peak_day")
    val peakDay: Int?,

    /** Peak day total time in ms. */
    @ColumnInfo(name = "peak_day_time_ms")
    val peakDayTimeMs: Long = 0,

    /** Average daily screen time in ms. */
    @ColumnInfo(name = "avg_daily_time_ms")
    val avgDailyTimeMs: Long,

    /** Peak hour (0-23). */
    @ColumnInfo(name = "peak_hour")
    val peakHour: Int?,

    /** Number of notifications received. */
    @ColumnInfo(name = "notifications_count")
    val notificationsCount: Int = 0,

    /** Number of focus mode sessions. */
    @ColumnInfo(name = "focus_sessions_count")
    val focusSessionsCount: Int = 0,

    /** Total focus time in ms. */
    @ColumnInfo(name = "focus_time_ms")
    val focusTimeMs: Long = 0,

    /** Generated timestamp. */
    @ColumnInfo(name = "generated_at")
    val generatedAt: Long = System.currentTimeMillis()
) {
    /** Human-readable week label (e.g. "Jun 2 - Jun 8"). */
    fun weekLabel(): String {
        val sdf = java.text.SimpleDateFormat("MMM d", java.util.Locale.getDefault())
        val endSdf = java.text.SimpleDateFormat("MMM d, yyyy", java.util.Locale.getDefault())
        return "${sdf.format(java.util.Date(weekStart))} - ${endSdf.format(java.util.Date(weekEnd))}"
    }

    /** Get top apps as list of pairs (packageName, timeMs). */
    fun topAppsList(): List<Pair<String, Long>> {
        if (topApps.isBlank()) return emptyList()
        return topApps.split(",").mapNotNull {
            val parts = it.trim().split(":")
            if (parts.size == 2) parts[0] to (parts[1].toLongOrNull() ?: 0L) else null
        }
    }

    /** Screen time in hours and minutes (e.g. "12h 30m"). */
    fun screenTimeFormatted(): String {
        val hours = totalScreenTimeMs / 3_600_000
        val minutes = (totalScreenTimeMs % 3_600_000) / 60_000
        return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    }

    /** Average daily time formatted. */
    fun avgDailyFormatted(): String {
        val hours = avgDailyTimeMs / 3_600_000
        val minutes = (avgDailyTimeMs % 3_600_000) / 60_000
        return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    }
}