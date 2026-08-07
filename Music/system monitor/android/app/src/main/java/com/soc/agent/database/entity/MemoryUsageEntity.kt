package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One memory usage sample. Mirrors the `memory_usage` table on the SOC server.
 */
@Entity(
    tableName = "memory_usage",
    indices = [Index(value = ["device_id", "timestamp_millis"])]
)
data class MemoryUsageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "total_b")
    val totalB: Long = 0L,

    @ColumnInfo(name = "used_b")
    val usedB: Long = 0L,

    @ColumnInfo(name = "free_b")
    val freeB: Long = 0L,

    /** Usage percentage 0..100 (computed server-side if omitted). */
    @ColumnInfo(name = "usage_pct")
    val usagePct: Double = 0.0,

    @ColumnInfo(name = "swap_total_b")
    val swapTotalB: Long? = null,

    @ColumnInfo(name = "swap_used_b")
    val swapUsedB: Long? = null
)
