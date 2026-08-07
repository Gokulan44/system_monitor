package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One installed application, keyed by (deviceId, packageName).
 * Mirrors the `installed_apps` table on the SOC server (unique on
 * device_id + path, where path == package name).
 */
@Entity(
    tableName = "installed_apps",
    primaryKeys = ["device_id", "package_name"],
    indices = [
        Index(value = ["device_id"]),
        Index(value = ["risk"])
    ]
)
data class InstalledAppEntity(
    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    @ColumnInfo(name = "name")
    val name: String = "",

    /** Running process id, if the app is currently running. */
    @ColumnInfo(name = "pid")
    val pid: Int? = null,

    @ColumnInfo(name = "cpu_pct")
    val cpuPct: Double = 0.0,

    @ColumnInfo(name = "mem_b")
    val memB: Long = 0L,

    /** installed / running / system / disabled. */
    @ColumnInfo(name = "status")
    val status: String = "installed",

    /** unknown / low / medium / high / critical. */
    @ColumnInfo(name = "risk")
    val risk: String = "unknown",

    /** Signing certificate fingerprint, "n/a" if unavailable. */
    @ColumnInfo(name = "signature")
    val signature: String = "n/a",

    @ColumnInfo(name = "first_seen")
    val firstSeen: Long = System.currentTimeMillis()
)
