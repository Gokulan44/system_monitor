package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One indicator of compromise (hash / url / domain / ip) pushed from the
 * server's blocklist. Unique on (value, type). Mirrors the `iocs` table on
 * the SOC server.
 */
@Entity(
    tableName = "iocs",
    indices = [
        Index(value = ["value", "type"], unique = true),
        Index(value = ["type"])
    ]
)
data class IocEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "value")
    val value: String,

    /** hash / url / domain / ip. */
    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "threat")
    val threat: String? = null,

    @ColumnInfo(name = "source")
    val source: String? = null,

    @ColumnInfo(name = "severity")
    val severity: String = "medium",

    @ColumnInfo(name = "active")
    val active: Boolean = true,

    @ColumnInfo(name = "added_at")
    val addedAt: Long = System.currentTimeMillis()
)
