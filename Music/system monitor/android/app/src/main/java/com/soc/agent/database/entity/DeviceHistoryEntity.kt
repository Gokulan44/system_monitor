package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Local device event history (registration, sync milestones, local events).
 * Mirrors the `device_history` table on the SOC server.
 */
@Entity(
    tableName = "device_history",
    indices = [Index(value = ["device_id"])]
)
data class DeviceHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    /** Event name, e.g. "agent.register", "sync.completed". */
    @ColumnInfo(name = "event")
    val event: String,

    @ColumnInfo(name = "detail")
    val detail: String = "",

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis()
)
