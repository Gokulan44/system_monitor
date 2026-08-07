package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

/**
 * Total device screen time (all apps combined) stored per time bucket.
 * Primary key: bucketStart.
 */
@Entity(
    tableName = "screen_time",
    primaryKeys = ["bucket_start"],
    indices = [
        Index(value = ["bucket_start"])
    ]
)
data class ScreenTimeEntity(
    /** Start of the bucket (epoch millis). For daily = midnight local, weekly = Monday 00:00, monthly = 1st 00:00. */
    @ColumnInfo(name = "bucket_start")
    val bucketStart: Long,

    /** Granularity: "daily", "weekly", or "monthly". */
    @ColumnInfo(name = "granularity")
    val granularity: String,

    /** Total device screen time in this bucket (milliseconds, sum of all apps). */
    @ColumnInfo(name = "total_time_ms")
    var totalTimeMs: Long = 0L,

    /** Number of distinct apps used in this bucket. */
    @ColumnInfo(name = "app_count")
    var appCount: Int = 0,

    /** Comma-separated list of top-5 packages by usage time. */
    @ColumnInfo(name = "top_apps")
    var topApps: String = "",

    /** Last sync timestamp. */
    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = System.currentTimeMillis()
)