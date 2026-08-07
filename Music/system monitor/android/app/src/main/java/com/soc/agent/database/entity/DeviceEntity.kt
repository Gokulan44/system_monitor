package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * The agent's own device record, keyed locally by the stable agent UUID
 * ([agentId]) and carrying the server-assigned [deviceId] once registered.
 * Mirrors the `devices` table on the SOC server.
 */
@Entity(
    tableName = "devices",
    indices = [Index(value = ["agent_id"], unique = true)]
)
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    /** Stable agent UUID, generated once and persisted. Unique. */
    @ColumnInfo(name = "agent_id")
    val agentId: String,

    /** Server-assigned device id (from RegisterResponse.device_id). */
    @ColumnInfo(name = "device_id")
    val deviceId: Long? = null,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "manufacturer")
    val manufacturer: String = "",

    @ColumnInfo(name = "model")
    val model: String = "",

    @ColumnInfo(name = "android_version")
    val androidVersion: String = "",

    @ColumnInfo(name = "app_version")
    val appVersion: String = "",

    @ColumnInfo(name = "platform")
    val platform: String = "android",

    /** online / offline / unknown. */
    @ColumnInfo(name = "status")
    val status: String = "unknown",

    /** safe / medium / high / unknown, as reported by the server. */
    @ColumnInfo(name = "risk")
    val risk: String = "unknown",

    @ColumnInfo(name = "risk_score")
    val riskScore: Int = 100,

    /** Epoch millis of the last successful sync. */
    @ColumnInfo(name = "last_seen")
    val lastSeen: Long = 0L,

    /** Epoch millis of the first successful registration. */
    @ColumnInfo(name = "registered_at")
    val registeredAt: Long = 0L
)
