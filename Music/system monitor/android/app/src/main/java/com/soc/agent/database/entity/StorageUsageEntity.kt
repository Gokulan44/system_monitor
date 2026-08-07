package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One storage volume sample (one row per mount point per sample time).
 * Mirrors the `storage_usage` table on the SOC server.
 */
@Entity(
    tableName = "storage_usage",
    indices = [Index(value = ["device_id", "timestamp_millis"])]
)
data class StorageUsageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "filesystem")
    val filesystem: String = "internal",

    /** Mount point, e.g. "/" or "/storage/emulated/0". */
    @ColumnInfo(name = "mount")
    val mount: String = "/",

    @ColumnInfo(name = "type")
    val type: String = "ext4",

    @ColumnInfo(name = "total_b")
    val totalB: Long = 0L,

    @ColumnInfo(name = "used_b")
    val usedB: Long = 0L,

    @ColumnInfo(name = "free_b")
    val freeB: Long = 0L,

    /** Usage percentage 0..100. */
    @ColumnInfo(name = "usage_pct")
    val usagePct: Double = 0.0
)
