package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Snapshot of static device identity details. Mirrors the `device_info`
 * table on the SOC server.
 */
@Entity(
    tableName = "device_info",
    indices = [Index(value = ["device_id", "timestamp_millis"])]
)
data class DeviceInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "manufacturer")
    val manufacturer: String = "",

    @ColumnInfo(name = "model")
    val model: String = "",

    @ColumnInfo(name = "kernel")
    val kernel: String = "",

    @ColumnInfo(name = "os_name")
    val osName: String = "Android",

    @ColumnInfo(name = "os_version")
    val osVersion: String = "",

    @ColumnInfo(name = "security_patch_level")
    val securityPatchLevel: String = "",

    @ColumnInfo(name = "os_build")
    val osBuild: String = "",

    /** Uptime in seconds at sample time. */
    @ColumnInfo(name = "uptime_sec")
    val uptimeSec: Long = 0L
)
