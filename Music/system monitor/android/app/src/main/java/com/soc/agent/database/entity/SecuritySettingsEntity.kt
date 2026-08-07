package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Security settings for the App Lock module (optional features).
 * Single row (id=1) stores all boolean toggles and thresholds.
 */
@Entity(tableName = "security_settings")
data class SecuritySettingsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 1, // singleton row

    /** Enable intruder selfie after failed attempts (requires camera permission). */
    @ColumnInfo(name = "intruder_selfie_enabled")
    val intruderSelfieEnabled: Boolean = false,

    /** Number of failed attempts before capturing selfie (1-10). */
    @ColumnInfo(name = "intruder_selfie_threshold")
    val intruderSelfieThreshold: Int = 3,

    /** Enable fake crash dialog when unlock fails. */
    @ColumnInfo(name = "fake_crash_enabled")
    val fakeCrashEnabled: Boolean = false,

    /** Fake crash message template. */
    @ColumnInfo(name = "fake_crash_message")
    val fakeCrashMessage: String = "Application has stopped unexpectedly.",

    /** Enable break-in alert notification. */
    @ColumnInfo(name = "breakin_alert_enabled")
    val breakinAlertEnabled: Boolean = true,

    /** Auto-lock on screen off (supplements Auto Lock mode). */
    @ColumnInfo(name = "auto_lock_screen_off")
    val autoLockScreenOff: Boolean = true,

    /** Require unlock after app goes to background (immediate re-lock). */
    @ColumnInfo(name = "require_unlock_on_background")
    val requireUnlockOnBackground: Boolean = false,

    /** Vibrate on failed attempt. */
    @ColumnInfo(name = "vibrate_on_failed")
    val vibrateOnFailed: Boolean = true,

    /** Sound on failed attempt. */
    @ColumnInfo(name = "sound_on_failed")
    val soundOnFailed: Boolean = false,

    /** Lock delay in milliseconds (grace period). */
    @ColumnInfo(name = "lock_delay_ms")
    val lockDelayMs: Long = 5000,

    /** Last modified timestamp. */
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)