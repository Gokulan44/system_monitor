package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Monthly aggregate of app usage per package.
 * Primary key: (packageName, monthStart).
 * monthStart = Monday 00:00 of the month's first Monday.
 * For simplicity, monthStart is the epoch millis of the 1st of the month at 00:00 local.
 */
@Entity(
    tableName = "monthly_usage",
    primaryKeys = ["package_name", "month_start"],
    indices = [
        Index(value = ["month_start"]),
        Index(value = ["package_name"])
    ]
)
data class MonthlyUsageEntity(
    /** Package name of the app. */
    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** App human-readable label. */
    @ColumnInfo(name = "app_name")
    val appName: String,

    /** Start of the month (epoch millis, 1st of month 00:00 local). */
    @ColumnInfo(name = "month_start")
    val monthStart: Long,

    /** Total time app was in foreground during the month (milliseconds). */
    @ColumnInfo(name = "total_time_ms")
    var totalTimeMs: Long = 0L,

    /** Number of times the app was launched/foregrounded during the month. */
    @ColumnInfo(name = "launch_count")
    var launchCount: Int = 0,

    /** Days in the month the app was used (count of distinct active days). */
    @ColumnInfo(name = "active_days")
    var activeDays: Int = 0,

    /** List of dates (YYYYMMDD) the app was used within the month (CSV). */
    @ColumnInfo(name = "active_dates")
    var activeDates: String = "",

    /** Last sync timestamp. */
    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = System.currentTimeMillis()
)