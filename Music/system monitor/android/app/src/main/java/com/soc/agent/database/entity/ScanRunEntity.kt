package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One scan run summary. Mirrors the `scan_runs` table on the SOC server,
 * extended with the server-recomputed score/grade for local display.
 */
@Entity(
    tableName = "scan_runs",
    indices = [Index(value = ["device_id", "started_at"])]
)
data class ScanRunEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    /** quick / full / apk. */
    @ColumnInfo(name = "scan_type")
    val scanType: String = "quick",

    /** running / completed / failed. */
    @ColumnInfo(name = "status")
    val status: String = "completed",

    @ColumnInfo(name = "items_scanned")
    val itemsScanned: Int = 0,

    @ColumnInfo(name = "threats_found")
    val threatsFound: Int = 0,

    /** Security score recomputed by the server (0..100). */
    @ColumnInfo(name = "score")
    val score: Int = 100,

    /** Grade label from the server, e.g. "Excellent". */
    @ColumnInfo(name = "grade")
    val grade: String = "Excellent",

    @ColumnInfo(name = "started_at")
    val startedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "finished_at")
    val finishedAt: Long = System.currentTimeMillis()
)
