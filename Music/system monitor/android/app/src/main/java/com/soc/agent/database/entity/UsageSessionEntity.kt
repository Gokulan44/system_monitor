package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Individual app usage session (one continuous foreground period).
 * Created when app comes to foreground, closed when it goes to background.
 */
@Entity(
    tableName = "usage_sessions",
    indices = [
        Index(value = ["package_name", "start_time_ms"]),
        Index(value = ["start_time_ms"]),
        Index(value = ["end_time_ms"])
    ]
)
data class UsageSessionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app label. */
    @ColumnInfo(name = "app_name")
    val appName: String = "",

    /** Session start timestamp (epoch millis). */
    @ColumnInfo(name = "start_time_ms")
    val startTimeMs: Long,

    /** Session end timestamp (epoch millis), 0 if still active. */
    @ColumnInfo(name = "end_time_ms")
    val endTimeMs: Long = 0L,

    /** Session duration in milliseconds (end - start). */
    @ColumnInfo(name = "duration_ms")
    val durationMs: Long = 0L,

    /** Whether session is still active (end_time_ms == 0). */
    @ColumnInfo(name = "active")
    val active: Boolean = true
)