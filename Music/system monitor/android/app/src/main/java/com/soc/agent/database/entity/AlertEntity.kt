package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One security alert. Mirrors the `alerts` table on the SOC server.
 */
@Entity(
    tableName = "alerts",
    indices = [Index(value = ["read"]), Index(value = ["device_id", "created_at"])]
)
data class AlertEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    /** info / warning / critical. */
    @ColumnInfo(name = "level")
    val level: String = "info",

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "message")
    val message: String = "",

    @ColumnInfo(name = "read")
    val read: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
