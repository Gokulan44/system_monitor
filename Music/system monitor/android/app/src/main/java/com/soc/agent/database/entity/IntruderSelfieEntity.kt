package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Intruder selfie captured after failed unlock attempts.
 * Only stored when user has granted camera permission and enabled the feature.
 */
@Entity(
    tableName = "intruder_selfies",
    indices = [
        Index(value = ["package_name", "timestamp"]),
        Index(value = ["timestamp"])
    ]
)
data class IntruderSelfieEntity(
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

    /** Timestamp (epoch millis) when the selfie was captured. */
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    /** Path to the saved image file (internal storage). */
    @ColumnInfo(name = "image_path")
    val imagePath: String,

    /** Failure reason that triggered the capture. */
    @ColumnInfo(name = "failure_reason")
    val failureReason: String = "",

    /** Number of consecutive failed attempts before capture. */
    @ColumnInfo(name = "attempt_number")
    val attemptNumber: Int = 1
)