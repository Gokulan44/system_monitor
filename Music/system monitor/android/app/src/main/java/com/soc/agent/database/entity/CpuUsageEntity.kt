package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One CPU usage sample. Mirrors the `cpu_usage` table on the SOC server;
 * [perCore] and [usageHistory] are stored as JSON via [com.soc.agent.database.Converters].
 */
@Entity(
    tableName = "cpu_usage",
    indices = [Index(value = ["device_id", "timestamp_millis"])]
)
data class CpuUsageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis(),

    /** Overall load percentage 0..100. */
    @ColumnInfo(name = "load_pct")
    val loadPct: Double = 0.0,

    @ColumnInfo(name = "cores")
    val cores: Int = 1,

    /** Per-core load percentages (JSON-encoded). */
    @ColumnInfo(name = "per_core")
    val perCore: List<Double> = emptyList(),

    /** Current clock speed in GHz, if available. */
    @ColumnInfo(name = "speed_ghz")
    val speedGhz: Double? = null,

    /** CPU temperature in Celsius, if available. */
    @ColumnInfo(name = "temp_c")
    val tempC: Double? = null,

    /** Short rolling history of load samples (JSON-encoded). */
    @ColumnInfo(name = "usage_history")
    val usageHistory: List<Double> = emptyList()
)
