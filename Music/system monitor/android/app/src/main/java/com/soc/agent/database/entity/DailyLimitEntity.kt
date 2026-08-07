package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * User-configured daily usage limit per app.
 * When an app exceeds its limit, the system can show a warning or block it.
 */
@Entity(
    tableName = "daily_limit",
    indices = [
        Index(value = ["package_name"], unique = true),
        Index(value = ["enabled"])
    ]
)
data class DailyLimitEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    /** Package name of the app. */
    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app name. */
    @ColumnInfo(name = "app_name")
    val appName: String,

    /** Daily limit in milliseconds. 0 = no limit. */
    @ColumnInfo(name = "limit_ms")
    val limitMs: Long,

    /** Warning threshold (0.0-1.0) — fraction of limit at which to show a warning. Default 0.8 (80%). */
    @ColumnInfo(name = "warning_threshold")
    val warningThreshold: Double = 0.8,

    /** Action when limit exceeded: "warning" (show overlay), "block" (prevent launch), or "notify" (notification only). */
    @ColumnInfo(name = "exceeded_action")
    val exceededAction: String = "warning",

    /** Whether this limit is enabled. */
    @ColumnInfo(name = "enabled")
    val enabled: Boolean = true
) {
    /** Check if the given usage duration has reached the warning threshold. */
    fun hasReachedWarning(currentMs: Long): Boolean =
        enabled && limitMs > 0 && currentMs >= (limitMs * warningThreshold).toLong()

    /** Check if the given usage duration has exceeded the limit. */
    fun hasExceededLimit(currentMs: Long): Boolean =
        enabled && limitMs > 0 && currentMs >= limitMs

    /** Remaining time in ms (0 if over limit). */
    fun remainingMs(currentMs: Long): Long =
        if (limitMs <= 0) Long.MAX_VALUE
        else maxOf(0, limitMs - currentMs)

    /** Usage fraction (0.0-1.0+). */
    fun usageFraction(currentMs: Long): Double =
        if (limitMs <= 0) 0.0
        else currentMs.toDouble() / limitMs
}