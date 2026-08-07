package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Daily aggregate of app usage for a single package.
 * One row per package per day (keyed by package_name + date).
 */
@Entity(
    tableName = "daily_usage",
    indices = [
        Index(value = ["package_name", "date"], unique = true),
        Index(value = ["date"]),
        Index(value = ["total_time_ms"])
    ]
)
data class DailyUsageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app label captured at time of recording. */
    @ColumnInfo(name = "app_name")
    val appName: String = "",

    /** Date in YYYYMMDD format (e.g., 20240115). */
    @ColumnInfo(name = "date")
    val date: Int,

    /** Total time app was in foreground (milliseconds). */
    @ColumnInfo(name = "total_time_ms")
    var totalTimeMs: Long = 0L,

    /** Number of times the app was launched/foregrounded. */
    @ColumnInfo(name = "launch_count")
    var launchCount: Int = 0,

    /** First foreground timestamp of the day. */
    @ColumnInfo(name = "first_time_ms")
    var firstTimeMs: Long = 0L,

    /** Last foreground timestamp of the day. */
    @ColumnInfo(name = "last_time_ms")
    var lastTimeMs: Long = 0L,

    /** Last sync timestamp. */
    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = System.currentTimeMillis()
)