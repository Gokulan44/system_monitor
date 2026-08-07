package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Record of a successful unlock event for a locked app.
 * Used for audit / history viewing in the App Lock dashboard.
 */
@Entity(
    tableName = "unlock_history",
    indices = [
        Index(value = ["package_name", "timestamp"]),
        Index(value = ["timestamp"])
    ]
)
data class UnlockHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    @ColumnInfo(name = "app_name")
    val appName: String = "",

    /** "pin", "pattern", "password", "biometric" — the gate used. */
    @ColumnInfo(name = "gate_method")
    val gateMethod: String,

    /** Timestamp (epoch millis) when the unlock occurred. */
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    /** Whether the unlock was via the grace window (auto) or explicit gate. */
    @ColumnInfo(name = "auto_unlock")
    val autoUnlock: Boolean = false
)