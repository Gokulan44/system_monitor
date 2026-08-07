package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Record of a failed unlock attempt for a locked app.
 * Used for security audit / brute-force detection in the App Lock dashboard.
 */
@Entity(
    tableName = "failed_attempts",
    indices = [
        Index(value = ["package_name", "timestamp"]),
        Index(value = ["timestamp"])
    ]
)
data class FailedAttemptEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    @ColumnInfo(name = "app_name")
    val appName: String = "",

    /** "pin", "pattern", "password", "biometric" — the gate that was attempted. */
    @ColumnInfo(name = "gate_method")
    val gateMethod: String,

    /** Timestamp (epoch millis) when the failed attempt occurred. */
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    /** Optional detail: "wrong_pin", "wrong_pattern", "biometric_failed", "cancelled", etc. */
    @ColumnInfo(name = "failure_reason")
    val failureReason: String = ""
)