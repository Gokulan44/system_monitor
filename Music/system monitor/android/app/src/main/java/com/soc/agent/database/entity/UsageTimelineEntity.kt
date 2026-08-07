package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Chronological app usage event. One row per foreground/background transition.
 * Enables timeline views: what was used, when, for how long, in what order.
 */
@Entity(
    tableName = "usage_timeline",
    indices = [
        Index(value = ["package_name"]),
        Index(value = ["event_time_ms"]),
        Index(value = ["date_int"]),
        Index(value = ["package_name", "event_time_ms"])
    ]
)
data class UsageTimelineEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    /** Package name of the app. */
    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app name. */
    @ColumnInfo(name = "app_name")
    val appName: String,

    /** Event type: "foreground" or "background". */
    @ColumnInfo(name = "event_type")
    val eventType: String,

    /** Epoch millis when the event occurred. */
    @ColumnInfo(name = "event_time_ms")
    val eventTimeMs: Long,

    /** Hour of day (0-23) for quick filtering. */
    @ColumnInfo(name = "hour_of_day")
    val hourOfDay: Int,

    /** Day of week (1=Mon..7=Sun). */
    @ColumnInfo(name = "day_of_week")
    val dayOfWeek: Int,

    /** Date as YYYYMMDD int. */
    @ColumnInfo(name = "date_int")
    val dateInt: Int,

    /** Duration in ms (only meaningful for "background" events = session length). 0 for foreground events. */
    @ColumnInfo(name = "duration_ms")
    val durationMs: Long = 0,

    /** Previous foreground app (the app that was active before this one, for context). */
    @ColumnInfo(name = "previous_app")
    val previousApp: String? = null
)