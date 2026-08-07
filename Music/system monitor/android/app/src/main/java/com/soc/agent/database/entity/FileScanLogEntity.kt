package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One file visited during a scan. Mirrors the `file_scan_logs` table on the
 * SOC server.
 */
@Entity(
    tableName = "file_scan_logs",
    indices = [Index(value = ["verdict"]), Index(value = ["device_id", "scanned_at"])]
)
data class FileScanLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    /** Which scan produced this entry: quick / full / apk / agent. */
    @ColumnInfo(name = "scan_type")
    val scanType: String = "agent",

    @ColumnInfo(name = "path")
    val path: String,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "ext")
    val ext: String = "",

    @ColumnInfo(name = "size_b")
    val sizeB: Long = 0L,

    @ColumnInfo(name = "sha256")
    val sha256: String? = null,

    /** clean / suspicious / malicious. */
    @ColumnInfo(name = "verdict")
    val verdict: String = "clean",

    @ColumnInfo(name = "detail")
    val detail: String = "",

    @ColumnInfo(name = "scanned_at")
    val scannedAt: Long = System.currentTimeMillis()
)
