package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One battery status sample. Mirrors the `battery_status` table on the SOC server.
 */
@Entity(
    tableName = "battery_status",
    indices = [Index(value = ["device_id", "timestamp_millis"])]
)
data class BatteryStatusEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis(),

    /** False on devices without a battery (e.g. emulators, set-top boxes). */
    @ColumnInfo(name = "has_battery")
    val hasBattery: Boolean = true,

    /** Charge level 0..100. */
    @ColumnInfo(name = "percent")
    val percent: Int = 0,

    @ColumnInfo(name = "charging")
    val charging: Boolean = false,

    /** charging / discharging / full / not_charging / unknown. */
    @ColumnInfo(name = "status")
    val status: String = "unknown",

    /** Estimated seconds remaining, if reported by the platform. */
    @ColumnInfo(name = "time_remaining")
    val timeRemaining: Long? = null
)
