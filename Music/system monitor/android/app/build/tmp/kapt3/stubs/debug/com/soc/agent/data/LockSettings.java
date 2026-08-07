package com.soc.agent.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.soc.agent.utils.Prefs;

/**
 * Centralized settings for the App Lock module. Wraps [Prefs] with
 * type-safe accessors and validation. All writes are immediately
 * persisted to SharedPreferences.
 *
 * Usage:
 *  val settings = LockSettings.getInstance(context)
 *  settings.lockMethod = "pattern"
 *  settings.autoLockDelayMs = 30_000
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0006\u0018\u0000 N2\u00020\u0001:\u0001NB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u00109\u001a\u00020:J\u0006\u0010;\u001a\u00020:J\u0006\u0010<\u001a\u00020:J\u0010\u0010=\u001a\f\u0012\u0004\u0012\u00020\"\u0012\u0002\b\u00030>J\u0006\u0010?\u001a\u00020\"J\u0006\u0010@\u001a\u00020\"J\u0006\u0010A\u001a\u00020\"J\u0018\u0010B\u001a\u00020:2\u0010\u0010C\u001a\f\u0012\u0004\u0012\u00020\"\u0012\u0002\b\u00030>J\u0006\u0010D\u001a\u00020:J\u000e\u0010E\u001a\u00020:2\u0006\u0010F\u001a\u00020\"J\u0014\u0010G\u001a\u00020:2\f\u0010H\u001a\b\u0012\u0004\u0012\u0002010IJ\u000e\u0010J\u001a\u00020:2\u0006\u0010K\u001a\u00020\"J\u0010\u0010L\u001a\u00020\"2\u0006\u0010M\u001a\u00020\"H\u0002R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R$\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0015\u0010\u000f\"\u0004\b\u0016\u0010\u0011R$\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0017\u0010\u000f\"\u0004\b\u0018\u0010\u0011R\u0011\u0010\u0019\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u000fR$\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R$\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u0011\u0010\u001e\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u000fR$\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b \u0010\t\"\u0004\b!\u0010\u000bR$\u0010#\u001a\u00020\"2\u0006\u0010\u0005\u001a\u00020\"8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010(\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b)\u0010\u000f\"\u0004\b*\u0010\u0011R$\u0010+\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b,\u0010\u000f\"\u0004\b-\u0010\u0011R$\u0010.\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b/\u0010\t\"\u0004\b0\u0010\u000bR$\u00102\u001a\u0002012\u0006\u0010\u0005\u001a\u0002018F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u000e\u00107\u001a\u000208X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006O"}, d2 = {"Lcom/soc/agent/data/LockSettings;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "autoLockDelayMs", "getAutoLockDelayMs", "()J", "setAutoLockDelayMs", "(J)V", "", "hasIntruderConsent", "getHasIntruderConsent", "()Z", "setHasIntruderConsent", "(Z)V", "includeInBackup", "getIncludeInBackup", "setIncludeInBackup", "isAutoLockEnabled", "setAutoLockEnabled", "isBiometricFallbackAllowed", "setBiometricFallbackAllowed", "isBiometricPrimary", "isFakeCrashEnabled", "setFakeCrashEnabled", "isIntruderSelfieEnabled", "setIntruderSelfieEnabled", "isLockMethodConfigured", "lastBackupMs", "getLastBackupMs", "setLastBackupMs", "", "lockMethod", "getLockMethod", "()Ljava/lang/String;", "setLockMethod", "(Ljava/lang/String;)V", "lockOnAppSwitch", "getLockOnAppSwitch", "setLockOnAppSwitch", "lockOnScreenOff", "getLockOnScreenOff", "setLockOnScreenOff", "lockoutDurationMs", "getLockoutDurationMs", "setLockoutDurationMs", "", "maxFailedAttempts", "getMaxFailedAttempts", "()I", "setMaxFailedAttempts", "(I)V", "prefs", "Landroid/content/SharedPreferences;", "clearPassword", "", "clearPattern", "clearPin", "exportAll", "", "getPasswordHash", "getPatternString", "getPinnedHash", "importAll", "settings", "resetAll", "setPassword", "password", "setPattern", "points", "", "setPin", "pin", "sha256", "input", "Companion", "app_debug"})
public final class LockSettings {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "app_lock_settings";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LOCK_METHOD = "lock_method";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PIN_HASH = "pin_hash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PATTERN = "pattern";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PASSWORD_HASH = "password_hash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AUTO_LOCK = "auto_lock";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AUTO_LOCK_DELAY = "auto_lock_delay";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LOCK_SCREEN_OFF = "lock_screen_off";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LOCK_APP_SWITCH = "lock_app_switch";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_BIOMETRIC_FALLBACK = "biometric_fallback";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_MAX_FAILED = "max_failed";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LOCKOUT_DURATION = "lockout_duration";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FAKE_CRASH = "fake_crash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_INTRUDER_SELFIE = "intruder_selfie";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_INTRUDER_CONSENT = "intruder_consent";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_INCLUDE_BACKUP = "include_backup";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_BACKUP = "last_backup";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> VALID_METHODS = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.data.LockSettings INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.data.LockSettings.Companion Companion = null;
    
    private LockSettings(android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLockMethod() {
        return null;
    }
    
    public final void setLockMethod(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean isLockMethodConfigured() {
        return false;
    }
    
    /**
     * Hashed PIN (SHA-256). Blank if not set.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPinnedHash() {
        return null;
    }
    
    /**
     * Store a new PIN (hashed before storage).
     */
    public final void setPin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
    }
    
    /**
     * Clear the stored PIN.
     */
    public final void clearPin() {
    }
    
    /**
     * Stored pattern as comma-separated point indices (e.g. "0,1,2,4,6,7,8").
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPatternString() {
        return null;
    }
    
    /**
     * Store a pattern from a list of point indices.
     */
    public final void setPattern(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> points) {
    }
    
    /**
     * Clear the stored pattern.
     */
    public final void clearPattern() {
    }
    
    /**
     * Hashed password (SHA-256). Blank if not set.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPasswordHash() {
        return null;
    }
    
    /**
     * Store a new password (hashed before storage).
     */
    public final void setPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    /**
     * Clear the stored password.
     */
    public final void clearPassword() {
    }
    
    public final boolean isAutoLockEnabled() {
        return false;
    }
    
    public final void setAutoLockEnabled(boolean value) {
    }
    
    public final long getAutoLockDelayMs() {
        return 0L;
    }
    
    public final void setAutoLockDelayMs(long value) {
    }
    
    public final boolean getLockOnScreenOff() {
        return false;
    }
    
    public final void setLockOnScreenOff(boolean value) {
    }
    
    public final boolean getLockOnAppSwitch() {
        return false;
    }
    
    public final void setLockOnAppSwitch(boolean value) {
    }
    
    public final boolean isBiometricFallbackAllowed() {
        return false;
    }
    
    public final void setBiometricFallbackAllowed(boolean value) {
    }
    
    public final boolean isBiometricPrimary() {
        return false;
    }
    
    public final int getMaxFailedAttempts() {
        return 0;
    }
    
    public final void setMaxFailedAttempts(int value) {
    }
    
    public final long getLockoutDurationMs() {
        return 0L;
    }
    
    public final void setLockoutDurationMs(long value) {
    }
    
    public final boolean isFakeCrashEnabled() {
        return false;
    }
    
    public final void setFakeCrashEnabled(boolean value) {
    }
    
    public final boolean isIntruderSelfieEnabled() {
        return false;
    }
    
    public final void setIntruderSelfieEnabled(boolean value) {
    }
    
    public final boolean getHasIntruderConsent() {
        return false;
    }
    
    public final void setHasIntruderConsent(boolean value) {
    }
    
    public final boolean getIncludeInBackup() {
        return false;
    }
    
    public final void setIncludeInBackup(boolean value) {
    }
    
    public final long getLastBackupMs() {
        return 0L;
    }
    
    public final void setLastBackupMs(long value) {
    }
    
    /**
     * Reset all settings to defaults.
     */
    public final void resetAll() {
    }
    
    /**
     * Export all settings as a map (for backup).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, ?> exportAll() {
        return null;
    }
    
    /**
     * Import settings from a map (for restore).
     */
    public final void importAll(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ?> settings) {
    }
    
    /**
     * Simple SHA-256 hash.
     */
    private final java.lang.String sha256(java.lang.String input) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001dR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001e"}, d2 = {"Lcom/soc/agent/data/LockSettings$Companion;", "", "()V", "INSTANCE", "Lcom/soc/agent/data/LockSettings;", "KEY_AUTO_LOCK", "", "KEY_AUTO_LOCK_DELAY", "KEY_BIOMETRIC_FALLBACK", "KEY_FAKE_CRASH", "KEY_INCLUDE_BACKUP", "KEY_INTRUDER_CONSENT", "KEY_INTRUDER_SELFIE", "KEY_LAST_BACKUP", "KEY_LOCKOUT_DURATION", "KEY_LOCK_APP_SWITCH", "KEY_LOCK_METHOD", "KEY_LOCK_SCREEN_OFF", "KEY_MAX_FAILED", "KEY_PASSWORD_HASH", "KEY_PATTERN", "KEY_PIN_HASH", "PREFS_NAME", "VALID_METHODS", "", "getVALID_METHODS", "()Ljava/util/Set;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.String> getVALID_METHODS() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.data.LockSettings getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}