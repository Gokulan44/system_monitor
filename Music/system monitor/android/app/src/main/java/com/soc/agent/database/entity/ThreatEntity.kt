package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One detected threat. Mirrors the `threats` table on the SOC server.
 */
@Entity(
    tableName = "threats",
    indices = [
        Index(value = ["status"]),
        Index(value = ["severity"]),
        Index(value = ["device_id", "detected_at"])
    ]
)
data class ThreatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    /** malware / phishing / network / policy / ... */
    @ColumnInfo(name = "kind")
    val kind: String = "malware",

    @ColumnInfo(name = "title")
    val title: String,

    /** low / medium / high / critical. */
    @ColumnInfo(name = "severity")
    val severity: String = "low",

    @ColumnInfo(name = "detail")
    val detail: String = "",

    /** open / acknowledged / resolved. */
    @ColumnInfo(name = "status")
    val status: String = "open",

    @ColumnInfo(name = "detected_at")
    val detectedAt: Long = System.currentTimeMillis()
)
