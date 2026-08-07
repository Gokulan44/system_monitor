package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Detailed per-app usage statistics (overall totals, not daily).
 * One row per package, updated incrementally.
 */
@Entity(
    tableName = "app_usage",
    indices = [
        Index(value = ["package_name"], unique = true),
        Index(value = ["total_time_ms"]),
        Index(value = ["last_used_ms"])
    ]
)
data class AppUsageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app label. */
    @ColumnInfo(name = "app_name")
    val appName: String = "",

    /** Total time app has been in foreground (milliseconds). */
    @ColumnInfo(name = "total_time_ms")
    var totalTimeMs: Long = 0L,

    /** Total number of launches. */
    @ColumnInfo(name = "launch_count")
    var launchCount: Int = 0,

    /** First ever foreground timestamp. */
    @ColumnInfo(name = "first_used_ms")
    var firstUsedMs: Long = 0L,

    /** Most recent foreground timestamp. */
    @ColumnInfo(name = "last_used_ms")
    var lastUsedMs: Long = 0L,

    /** Last sync timestamp. */
    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = System.currentTimeMillis()
)