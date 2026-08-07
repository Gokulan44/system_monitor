package com.soc.agent.data

import android.content.Context
import android.content.SharedPreferences
import com.soc.agent.utils.Prefs

/**
 * Centralized settings for the App Lock module. Wraps [Prefs] with
 * type-safe accessors and validation. All writes are immediately
 * persisted to SharedPreferences.
 *
 * Usage:
 *   val settings = LockSettings.getInstance(context)
 *   settings.lockMethod = "pattern"
 *   settings.autoLockDelayMs = 30_000
 */
class LockSettings private constructor(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // ── Lock Method ──────────────────────────────────────────────────

    /** Active lock method: "pin", "pattern", "password", "biometric", or "none". */
    var lockMethod: String
        get() = prefs.getString(KEY_LOCK_METHOD, "none") ?: "none"
        set(value) {
            require(value in VALID_METHODS) { "Invalid lock method: $value" }
            prefs.edit().putString(KEY_LOCK_METHOD, value).apply()
        }

    /** Whether the user has set up their primary lock method. */
    val isLockMethodConfigured: Boolean
        get() = lockMethod != "none" && getPinnedHash().isNotBlank()

    // ── PIN ──────────────────────────────────────────────────────────

    /** Hashed PIN (SHA-256). Blank if not set. */
    fun getPinnedHash(): String = prefs.getString(KEY_PIN_HASH, "") ?: ""

    /** Store a new PIN (hashed before storage). */
    fun setPin(pin: String) {
        require(pin.length in 4..8) { "PIN must be 4-8 digits" }
        require(pin.all { it.isDigit() }) { "PIN must contain only digits" }
        prefs.edit().putString(KEY_PIN_HASH, sha256(pin)).apply()
    }

    /** Clear the stored PIN. */
    fun clearPin() {
        prefs.edit().remove(KEY_PIN_HASH).apply()
    }

    // ── Pattern ──────────────────────────────────────────────────────

    /** Stored pattern as comma-separated point indices (e.g. "0,1,2,4,6,7,8"). */
    fun getPatternString(): String = prefs.getString(KEY_PATTERN, "") ?: ""

    /** Store a pattern from a list of point indices. */
    fun setPattern(points: List<Int>) {
        require(points.size >= 4) { "Pattern must connect at least 4 points" }
        prefs.edit().putString(KEY_PATTERN, points.joinToString(",")).apply()
    }

    /** Clear the stored pattern. */
    fun clearPattern() {
        prefs.edit().remove(KEY_PATTERN).apply()
    }

    // ── Password ─────────────────────────────────────────────────────

    /** Hashed password (SHA-256). Blank if not set. */
    fun getPasswordHash(): String = prefs.getString(KEY_PASSWORD_HASH, "") ?: ""

    /** Store a new password (hashed before storage). */
    fun setPassword(password: String) {
        require(password.length in 6..64) { "Password must be 6-64 characters" }
        prefs.edit().putString(KEY_PASSWORD_HASH, sha256(password)).apply()
    }

    /** Clear the stored password. */
    fun clearPassword() {
        prefs.edit().remove(KEY_PASSWORD_HASH).apply()
    }

    // ── Auto-Lock ────────────────────────────────────────────────────

    /** Whether auto-lock is enabled (lock on screen off / app switch). */
    var isAutoLockEnabled: Boolean
        get() = prefs.getBoolean(KEY_AUTO_LOCK, true)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_LOCK, value).apply()

    /** Delay before auto-lock triggers (ms). 0 = immediate. */
    var autoLockDelayMs: Long
        get() = prefs.getLong(KEY_AUTO_LOCK_DELAY, 0)
        set(value) {
            require(value in 0..300_000) { "Delay must be 0-300 seconds" }
            prefs.edit().putLong(KEY_AUTO_LOCK_DELAY, value).apply()
        }

    /** Whether to lock on screen off specifically. */
    var lockOnScreenOff: Boolean
        get() = prefs.getBoolean(KEY_LOCK_SCREEN_OFF, true)
        set(value) = prefs.edit().putBoolean(KEY_LOCK_SCREEN_OFF, value).apply()

    /** Whether to lock on app switch (leaving a locked app). */
    var lockOnAppSwitch: Boolean
        get() = prefs.getBoolean(KEY_LOCK_APP_SWITCH, true)
        set(value) = prefs.edit().putBoolean(KEY_LOCK_APP_SWITCH, value).apply()

    // ── Biometric ────────────────────────────────────────────────────

    /** Whether biometric fallback is allowed when primary method is PIN/pattern/password. */
    var isBiometricFallbackAllowed: Boolean
        get() = prefs.getBoolean(KEY_BIOMETRIC_FALLBACK, true)
        set(value) = prefs.edit().putBoolean(KEY_BIOMETRIC_FALLBACK, value).apply()

    /** Whether biometric is the primary lock method. */
    val isBiometricPrimary: Boolean
        get() = lockMethod == "biometric"

    // ── Security ─────────────────────────────────────────────────────

    /** Max failed attempts before lockout. */
    var maxFailedAttempts: Int
        get() = prefs.getInt(KEY_MAX_FAILED, 5)
        set(value) {
            require(value in 1..20) { "Max attempts must be 1-20" }
            prefs.edit().putInt(KEY_MAX_FAILED, value).apply()
        }

    /** Lockout duration after max failed attempts (ms). */
    var lockoutDurationMs: Long
        get() = prefs.getLong(KEY_LOCKOUT_DURATION, 30_000)
        set(value) {
            require(value in 10_000..600_000) { "Lockout must be 10-600 seconds" }
            prefs.edit().putLong(KEY_LOCKOUT_DURATION, value).apply()
        }

    /** Whether to show fake crash on failed attempt (for supported apps). */
    var isFakeCrashEnabled: Boolean
        get() = prefs.getBoolean(KEY_FAKE_CRASH, false)
        set(value) = prefs.edit().putBoolean(KEY_FAKE_CRASH, value).apply()

    /** Whether to capture intruder selfie on failed attempt (requires consent). */
    var isIntruderSelfieEnabled: Boolean
        get() = prefs.getBoolean(KEY_INTRUDER_SELFIE, false)
        set(value) = prefs.edit().putBoolean(KEY_INTRUDER_SELFIE, value).apply()

    /** Whether intruder selfie capture has user consent. */
    var hasIntruderConsent: Boolean
        get() = prefs.getBoolean(KEY_INTRUDER_CONSENT, false)
        set(value) = prefs.edit().putBoolean(KEY_INTRUDER_CONSENT, value).apply()

    // ── Backup ───────────────────────────────────────────────────────

    /** Whether to include lock settings in backup. */
    var includeInBackup: Boolean
        get() = prefs.getBoolean(KEY_INCLUDE_BACKUP, true)
        set(value) = prefs.edit().putBoolean(KEY_INCLUDE_BACKUP, value).apply()

    /** Timestamp of last successful backup. */
    var lastBackupMs: Long
        get() = prefs.getLong(KEY_LAST_BACKUP, 0)
        set(value) = prefs.edit().putLong(KEY_LAST_BACKUP, value).apply()

    // ── Helpers ──────────────────────────────────────────────────────

    /** Reset all settings to defaults. */
    fun resetAll() {
        prefs.edit().clear().apply()
    }

    /** Export all settings as a map (for backup). */
    fun exportAll(): Map<String, *> = prefs.all

    /** Import settings from a map (for restore). */
    fun importAll(settings: Map<String, *>) {
        val editor = prefs.edit()
        editor.clear()
        settings.forEach { (key, value) ->
            when (value) {
                is String -> editor.putString(key, value)
                is Boolean -> editor.putBoolean(key, value)
                is Int -> editor.putInt(key, value)
                is Long -> editor.putLong(key, value)
                is Float -> editor.putFloat(key, value)
            }
        }
        editor.apply()
    }

    /** Simple SHA-256 hash. */
    private fun sha256(input: String): String {
        val bytes = java.security.MessageDigest.getInstance("SHA-256")
            .digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    companion object {
        private const val PREFS_NAME = "app_lock_settings"

        // Keys
        private const val KEY_LOCK_METHOD = "lock_method"
        private const val KEY_PIN_HASH = "pin_hash"
        private const val KEY_PATTERN = "pattern"
        private const val KEY_PASSWORD_HASH = "password_hash"
        private const val KEY_AUTO_LOCK = "auto_lock"
        private const val KEY_AUTO_LOCK_DELAY = "auto_lock_delay"
        private const val KEY_LOCK_SCREEN_OFF = "lock_screen_off"
        private const val KEY_LOCK_APP_SWITCH = "lock_app_switch"
        private const val KEY_BIOMETRIC_FALLBACK = "biometric_fallback"
        private const val KEY_MAX_FAILED = "max_failed"
        private const val KEY_LOCKOUT_DURATION = "lockout_duration"
        private const val KEY_FAKE_CRASH = "fake_crash"
        private const val KEY_INTRUDER_SELFIE = "intruder_selfie"
        private const val KEY_INTRUDER_CONSENT = "intruder_consent"
        private const val KEY_INCLUDE_BACKUP = "include_backup"
        private const val KEY_LAST_BACKUP = "last_backup"

        val VALID_METHODS = setOf("none", "pin", "pattern", "password", "biometric")

        @Volatile
        private var INSTANCE: LockSettings? = null

        fun getInstance(context: Context): LockSettings {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LockSettings(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}