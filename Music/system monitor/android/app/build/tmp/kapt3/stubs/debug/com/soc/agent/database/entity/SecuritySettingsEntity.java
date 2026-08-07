package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Security settings for the App Lock module (optional features).
 * Single row (id=1) stores all boolean toggles and thresholds.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0010H\u00c6\u0003J\t\u0010&\u001a\u00020\u0010H\u00c6\u0003J\t\u0010'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\tH\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\u0081\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u00c6\u0001J\u0013\u00100\u001a\u00020\u00052\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\u0003H\u00d6\u0001J\t\u00103\u001a\u00020\tH\u00d6\u0001R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0016\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0016\u0010\u0011\u001a\u00020\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014\u00a8\u00064"}, d2 = {"Lcom/soc/agent/database/entity/SecuritySettingsEntity;", "", "id", "", "intruderSelfieEnabled", "", "intruderSelfieThreshold", "fakeCrashEnabled", "fakeCrashMessage", "", "breakinAlertEnabled", "autoLockScreenOff", "requireUnlockOnBackground", "vibrateOnFailed", "soundOnFailed", "lockDelayMs", "", "updatedAt", "(IZIZLjava/lang/String;ZZZZZJJ)V", "getAutoLockScreenOff", "()Z", "getBreakinAlertEnabled", "getFakeCrashEnabled", "getFakeCrashMessage", "()Ljava/lang/String;", "getId", "()I", "getIntruderSelfieEnabled", "getIntruderSelfieThreshold", "getLockDelayMs", "()J", "getRequireUnlockOnBackground", "getSoundOnFailed", "getUpdatedAt", "getVibrateOnFailed", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "security_settings")
public final class SecuritySettingsEntity {
    @androidx.room.PrimaryKey()
    @androidx.room.ColumnInfo(name = "id")
    private final int id = 0;
    
    /**
     * Enable intruder selfie after failed attempts (requires camera permission).
     */
    @androidx.room.ColumnInfo(name = "intruder_selfie_enabled")
    private final boolean intruderSelfieEnabled = false;
    
    /**
     * Number of failed attempts before capturing selfie (1-10).
     */
    @androidx.room.ColumnInfo(name = "intruder_selfie_threshold")
    private final int intruderSelfieThreshold = 0;
    
    /**
     * Enable fake crash dialog when unlock fails.
     */
    @androidx.room.ColumnInfo(name = "fake_crash_enabled")
    private final boolean fakeCrashEnabled = false;
    
    /**
     * Fake crash message template.
     */
    @androidx.room.ColumnInfo(name = "fake_crash_message")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fakeCrashMessage = null;
    
    /**
     * Enable break-in alert notification.
     */
    @androidx.room.ColumnInfo(name = "breakin_alert_enabled")
    private final boolean breakinAlertEnabled = false;
    
    /**
     * Auto-lock on screen off (supplements Auto Lock mode).
     */
    @androidx.room.ColumnInfo(name = "auto_lock_screen_off")
    private final boolean autoLockScreenOff = false;
    
    /**
     * Require unlock after app goes to background (immediate re-lock).
     */
    @androidx.room.ColumnInfo(name = "require_unlock_on_background")
    private final boolean requireUnlockOnBackground = false;
    
    /**
     * Vibrate on failed attempt.
     */
    @androidx.room.ColumnInfo(name = "vibrate_on_failed")
    private final boolean vibrateOnFailed = false;
    
    /**
     * Sound on failed attempt.
     */
    @androidx.room.ColumnInfo(name = "sound_on_failed")
    private final boolean soundOnFailed = false;
    
    /**
     * Lock delay in milliseconds (grace period).
     */
    @androidx.room.ColumnInfo(name = "lock_delay_ms")
    private final long lockDelayMs = 0L;
    
    /**
     * Last modified timestamp.
     */
    @androidx.room.ColumnInfo(name = "updated_at")
    private final long updatedAt = 0L;
    
    public SecuritySettingsEntity(int id, boolean intruderSelfieEnabled, int intruderSelfieThreshold, boolean fakeCrashEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String fakeCrashMessage, boolean breakinAlertEnabled, boolean autoLockScreenOff, boolean requireUnlockOnBackground, boolean vibrateOnFailed, boolean soundOnFailed, long lockDelayMs, long updatedAt) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    /**
     * Enable intruder selfie after failed attempts (requires camera permission).
     */
    public final boolean getIntruderSelfieEnabled() {
        return false;
    }
    
    /**
     * Number of failed attempts before capturing selfie (1-10).
     */
    public final int getIntruderSelfieThreshold() {
        return 0;
    }
    
    /**
     * Enable fake crash dialog when unlock fails.
     */
    public final boolean getFakeCrashEnabled() {
        return false;
    }
    
    /**
     * Fake crash message template.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFakeCrashMessage() {
        return null;
    }
    
    /**
     * Enable break-in alert notification.
     */
    public final boolean getBreakinAlertEnabled() {
        return false;
    }
    
    /**
     * Auto-lock on screen off (supplements Auto Lock mode).
     */
    public final boolean getAutoLockScreenOff() {
        return false;
    }
    
    /**
     * Require unlock after app goes to background (immediate re-lock).
     */
    public final boolean getRequireUnlockOnBackground() {
        return false;
    }
    
    /**
     * Vibrate on failed attempt.
     */
    public final boolean getVibrateOnFailed() {
        return false;
    }
    
    /**
     * Sound on failed attempt.
     */
    public final boolean getSoundOnFailed() {
        return false;
    }
    
    /**
     * Lock delay in milliseconds (grace period).
     */
    public final long getLockDelayMs() {
        return 0L;
    }
    
    /**
     * Last modified timestamp.
     */
    public final long getUpdatedAt() {
        return 0L;
    }
    
    public SecuritySettingsEntity() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final long component11() {
        return 0L;
    }
    
    public final long component12() {
        return 0L;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.SecuritySettingsEntity copy(int id, boolean intruderSelfieEnabled, int intruderSelfieThreshold, boolean fakeCrashEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String fakeCrashMessage, boolean breakinAlertEnabled, boolean autoLockScreenOff, boolean requireUnlockOnBackground, boolean vibrateOnFailed, boolean soundOnFailed, long lockDelayMs, long updatedAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}