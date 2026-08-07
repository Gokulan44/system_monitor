package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * A Focus Mode session: user-defined distraction-free period.
 * When active, apps in the blocked set are restricted from foreground access.
 */
@Entity(
    tableName = "focus_mode",
    indices = [
        Index(value = ["active"]),
        Index(value = ["start_time_ms"]),
        Index(value = ["end_time_ms"])
    ]
)
data class FocusModeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    /** Unique name for this focus mode (e.g. "Work", "Sleep", "Study"). */
    @ColumnInfo(name = "name")
    val name: String,

    /** Comma-separated list of blocked package names. */
    @ColumnInfo(name = "blocked_apps")
    val blockedApps: String,

    /** Epoch millis when the focus session starts. */
    @ColumnInfo(name = "start_time_ms")
    val startTimeMs: Long,

    /** Epoch millis when the focus session ends. 0 = manual end. */
    @ColumnInfo(name = "end_time_ms")
    val endTimeMs: Long = 0,

    /** Whether this focus mode is currently active. */
    @ColumnInfo(name = "active")
    val active: Boolean = false,

    /** Optional: repeat schedule (e.g. "daily", "weekdays", "weekends", or "" for one-shot). */
    @ColumnInfo(name = "schedule")
    val schedule: String = "",

    /** Created timestamp. */
    @ColumnInfo(name = "created_at_ms")
    val createdAtMs: Long = System.currentTimeMillis()
) {
    /** Get the list of blocked package names. */
    fun getBlockedPackageList(): List<String> =
        if (blockedApps.isBlank()) emptyList()
        else blockedApps.split(",").map { it.trim() }.filter { it.isNotEmpty() }

    /** Check if a package is blocked in this focus mode. */
    fun isPackageBlocked(packageName: String): Boolean =
        getBlockedPackageList().contains(packageName)

    /** Check if this focus mode is currently running (active + within time window). */
    fun isRunning(now: Long = System.currentTimeMillis()): Boolean {
        if (!active) return false
        if (endTimeMs > 0 && now > endTimeMs) return false
        return now >= startTimeMs
    }
}