package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One application protected by the App Lock module, keyed by package name.
 *
 * Rows are added from the "Add App Lock" picker and removed when the user
 * unlocks an app from the Locked Apps list. The [lockMethod] records which
 * gate was active when the app was locked ("pin" or "pattern") so the watcher
 * can present the matching unlock screen. [addedAt] drives the dashboard
 * "locked for" stat.
 */
@Entity(
    tableName = "locked_apps",
    indices = [
        Index(value = ["package_name"], unique = true),
        Index(value = ["lock_method"])
    ]
)
data class LockedAppEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Human-readable app label captured when the lock was added. */
    @ColumnInfo(name = "name")
    val name: String = "",

    /** "pin" or "pattern" — the unlock gate presented when this app opens. */
    @ColumnInfo(name = "lock_method")
    val lockMethod: String = "pin",

    /** Timestamp (epoch millis) when the lock was applied. */
    @ColumnInfo(name = "added_at")
    val addedAt: Long = System.currentTimeMillis(),

    /** Hard-disable switch; disabled locks are ignored by the watcher. */
    @ColumnInfo(name = "enabled")
    val enabled: Boolean = true
)
