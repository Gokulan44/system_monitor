package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Individual notification event. One row per notification posted.
 * Enables analytics: notification count, frequency, hourly patterns, noise ranking.
 */
@Entity(
    tableName = "notification_count",
    indices = [
        Index(value = ["package_name"]),
        Index(value = ["posted_at_ms"]),
        Index(value = ["package_name", "posted_at_ms"]),
        Index(value = ["date_int"])
    ]
)
data class NotificationCountEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    /** Package name of the app that posted the notification. */
    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app name. */
    @ColumnInfo(name = "app_name")
    val appName: String,

    /** Notification title (may be null for silent notifications). */
    @ColumnInfo(name = "title")
    val title: String? = null,

    /** Notification category (e.g. "social", "email", "alarm", "progress"). */
    @ColumnInfo(name = "category")
    val category: String? = null,

    /** Notification priority (0=MIN..4=MAX). */
    @ColumnInfo(name = "priority")
    val priority: Int = 0,

    /** Epoch millis when the notification was posted. */
    @ColumnInfo(name = "posted_at_ms")
    val postedAtMs: Long,

    /** Hour of day (0-23) for hourly distribution queries. */
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