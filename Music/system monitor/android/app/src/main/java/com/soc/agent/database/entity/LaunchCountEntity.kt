package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Individual app launch event. One row per launch.
 * Enables detailed launch analytics: frequency, hourly distribution, streaks.
 */
@Entity(
    tableName = "launch_count",
    indices = [
        Index(value = ["package_name"]),
        Index(value = ["launch_time_ms"]),
        Index(value = ["package_name", "launch_time_ms"])
    ]
)
data class LaunchCountEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    /** Package name of the launched app. */
    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app name. */
    @ColumnInfo(name = "app_name")
    val appName: String,

    /** Epoch millis when the launch was recorded. */
    @ColumnInfo(name = "launch_time_ms")
    val launchTimeMs: Long,

    /** Hour of day (0-23) for quick hourly distribution queries. */
    @ColumnInfo(name = "hour_of_day")
    val hourOfDay: Int,

    /** Day of week (1=Mon..7=Sun) for weekly pattern queries. */
    @ColumnInfo(name = "day_of_week")
    val dayOfWeek: Int,

    /** Date as YYYYMMDD int for daily grouping. */
    @ColumnInfo(name = "date_int")
    val dateInt: Int,

    /** Month as YYYYMM int for monthly grouping. */
    @ColumnInfo(name = "month_int")
    val monthInt: Int
)