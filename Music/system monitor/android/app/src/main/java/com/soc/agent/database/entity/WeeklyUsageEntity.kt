package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Weekly aggregate of app usage for a single package.
 * One row per package per ISO week (keyed by package_name + week_start).
 */
@Entity(
    tableName = "weekly_usage",
    indices = [
        Index(value = ["package_name", "week_start"], unique = true),
        Index(value = ["week_start"]),
        Index(value = ["total_time_ms"])
    ]
)
data class WeeklyUsageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app label. */
    @ColumnInfo(name = "app_name")
    val appName: String = "",

    /** Start of the week (Monday 00:00) in epoch millis. */
    @ColumnInfo(name = "week_start")
    val weekStart: Long,

    /** Total time app was in foreground during the week (milliseconds). */
    @ColumnInfo(name = "total_time_ms")
    var totalTimeMs: Long = 0L,

    /** Number of times the app was launched/foregrounded during the week. */
    @ColumnInfo(name = "launch_count")
    var launchCount: Int = 0,

    /** Days in the week the app was used (count of distinct active days). */
    @ColumnInfo(name = "active_days")
    var activeDays: Int = 0,

    /** List of dates (YYYYMMDD) the app was used within the week (JSON/CSV). */
    @ColumnInfo(name = "active_dates")
    var activeDates: String = "",

    /** Last sync timestamp. */
    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = System.currentTimeMillis()
)