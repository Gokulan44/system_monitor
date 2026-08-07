package com.soc.agent.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Typed wrapper around the app's encrypted preferences store.
 *
 * Secrets (apiKey, pinHash, agentId) and connection settings are persisted with
 * [EncryptedSharedPreferences] backed by a [MasterKey] (AES-256-GCM key +
 * AES-SIV pref keys) so they are at rest protected by the Android Keystore.
 *
 * If the encryption layer is unavailable (e.g. keystore wiped, corrupted
 * prefs file, crypto provider missing) we degrade gracefully to a plain
 * [SharedPreferences] with the same name so the app never crashes on startup.
 * A flag is recorded so callers can warn the user that at-rest protection is off.
 */
object Prefs {

    private const val TAG = "Prefs"
    private const val PREFS_NAME = "soc_agent_prefs"

    // Keys
    private const val KEY_SERVER_URL = "serverUrl"
    private const val KEY_API_KEY = "apiKey"
    private const val KEY_AGENT_ID = "agentId"
    private const val KEY_DEVICE_NAME = "deviceName"
    private const val KEY_REGISTERED_DEVICE_ID = "registeredDeviceId"
    private const val KEY_LAST_SYNC = "lastSync"
    private const val KEY_LAST_ERROR = "lastError"
    private const val KEY_PIN_HASH = "pinHash"
    private const val KEY_PIN_ENABLED = "pinEnabled"
    private const val KEY_PASSWORD_HASH = "passwordHash"
    private const val KEY_BIOMETRIC_ENABLED = "biometricEnabled"
    private const val KEY_CRYPTO_FALLBACK = "cryptoFallback"
    private const val KEY_APP_LOCK_ENABLED = "appLockEnabled"
    private const val KEY_PATTERN_HASH = "patternHash"
    private const val KEY_LOCK_METHOD = "lockMethod"
    private const val KEY_APP_LOCK_WATCHER = "appLockWatcherRunning"
    private const val KEY_AUTO_LOCK_MODE = "autoLockMode"
    private const val KEY_AUTO_LOCK_TIMEOUT = "autoLockTimeout"
    private const val KEY_LOCK_DELAY = "lockDelay"

    @Volatile
    private var cached: SharedPreferences? = null

    /**
     * Returns the encrypted (or fallback plain) preferences instance.
     * The instance is cached per process; a single MasterKey is generated once
     * and reused. Creating the master key may take ~100ms the first time.
     */
    fun prefs(context: Context): SharedPreferences {
        cached?.let { return it }
        synchronized(this) {
            cached?.let { return it }
            val appContext = context.applicationContext
            val instance = try {
                val masterKey = MasterKey.Builder(appContext)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()
                EncryptedSharedPreferences.create(
                    appContext,
                    PREFS_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                ).also {
                    // Mark that encrypted storage is active on first successful open.
                    it.edit().putBoolean(KEY_CRYPTO_FALLBACK, false).apply()
                }
            } catch (e: Exception) {
                // GeneralSecurityException and any provider hiccup -> plain fallback.
                Log.w(TAG, "EncryptedSharedPreferences unavailable (${e.javaClass.simpleName}: ${e.message}); falling back to plain prefs")
                appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).also {
                    it.edit().putBoolean(KEY_CRYPTO_FALLBACK, true).apply()
                }
            }
            cached = instance
            return instance
        }
    }

    /** True when the encryption layer failed and plain prefs are in use. */
    fun isCryptoFallback(context: Context): Boolean = prefs(context).getBoolean(KEY_CRYPTO_FALLBACK, false)

    // ------------------------------------------------------------------
    // Server connection
    // ------------------------------------------------------------------
    var serverUrl: String
        get() = prefs(getCtx()).getString(KEY_SERVER_URL, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_SERVER_URL, value).apply()

    var apiKey: String
        get() = prefs(getCtx()).getString(KEY_API_KEY, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_API_KEY, value).apply()

    // ------------------------------------------------------------------
    // Identity
    // ------------------------------------------------------------------
    var agentId: String
        get() = prefs(getCtx()).getString(KEY_AGENT_ID, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_AGENT_ID, value).apply()

    var deviceName: String
        get() = prefs(getCtx()).getString(KEY_DEVICE_NAME, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_DEVICE_NAME, value).apply()

    var registeredDeviceId: String
        get() = prefs(getCtx()).getString(KEY_REGISTERED_DEVICE_ID, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_REGISTERED_DEVICE_ID, value).apply()

    // ------------------------------------------------------------------
    // Sync state
    // ------------------------------------------------------------------
    var lastSync: Long
        get() = prefs(getCtx()).getLong(KEY_LAST_SYNC, 0L)
        set(value) = prefs(getCtx()).edit().putLong(KEY_LAST_SYNC, value).apply()

    var lastError: String
        get() = prefs(getCtx()).getString(KEY_LAST_ERROR, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_LAST_ERROR, value).apply()

    // ------------------------------------------------------------------
    // Local PIN lock (hash of the PIN, never the PIN itself)
    // ------------------------------------------------------------------
    var pinHash: String
        get() = prefs(getCtx()).getString(KEY_PIN_HASH, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_PIN_HASH, value).apply()

    var passwordHash: String
        get() = prefs(getCtx()).getString(KEY_PASSWORD_HASH, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_PASSWORD_HASH, value).apply()

    var pinEnabled: Boolean
        get() = prefs(getCtx()).getBoolean(KEY_PIN_ENABLED, false)
        set(value) = prefs(getCtx()).edit().putBoolean(KEY_PIN_ENABLED, value).apply()

    var biometricEnabled: Boolean
        get() = prefs(getCtx()).getBoolean(KEY_BIOMETRIC_ENABLED, false)
        set(value) = prefs(getCtx()).edit().putBoolean(KEY_BIOMETRIC_ENABLED, value).apply()

    // ------------------------------------------------------------------
    // App Lock (per-app + pattern gate)
    // ------------------------------------------------------------------
    /** Master switch for the whole App Lock module (barriers active or off). */
    var appLockEnabled: Boolean
        get() = prefs(getCtx()).getBoolean(KEY_APP_LOCK_ENABLED, false)
        set(value) = prefs(getCtx()).edit().putBoolean(KEY_APP_LOCK_ENABLED, value).apply()

    /** SHA-256 hash of the approved unlock pattern, or "" when unset. */
    var patternHash: String
        get() = prefs(getCtx()).getString(KEY_PATTERN_HASH, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_PATTERN_HASH, value).apply()

    /** "pin", "pattern" or "" — the gate the user chose for app unlocks. */
    var lockMethod: String
        get() = prefs(getCtx()).getString(KEY_LOCK_METHOD, "") ?: ""
        set(value) = prefs(getCtx()).edit().putString(KEY_LOCK_METHOD, value).apply()

    /** Whether the foreground watcher service is currently running. */
    var appLockWatcherRunning: Boolean
        get() = prefs(getCtx()).getBoolean(KEY_APP_LOCK_WATCHER, false)
        set(value) = prefs(getCtx()).edit().putBoolean(KEY_APP_LOCK_WATCHER, value).apply()

    /** Auto-lock mode: "immediate", "screen_off", "timeout" */
    var autoLockMode: String
        get() = prefs(getCtx()).getString(KEY_AUTO_LOCK_MODE, "immediate") ?: "immediate"
        set(value) = prefs(getCtx()).edit().putString(KEY_AUTO_LOCK_MODE, value).apply()

    /** Auto-lock timeout in milliseconds (used when mode is "timeout"). */
    var autoLockTimeout: Long
        get() = prefs(getCtx()).getLong(KEY_AUTO_LOCK_TIMEOUT, 30000L)
        set(value) = prefs(getCtx()).edit().putLong(KEY_AUTO_LOCK_TIMEOUT, value).apply()

    /** Lock delay in milliseconds — grace period before showing gate when locked app opens. */
    var lockDelay: Long
        get() = prefs(getCtx()).getLong(KEY_LOCK_DELAY, 5000L)
        set(value) = prefs(getCtx()).edit().putLong(KEY_LOCK_DELAY, value).apply()

    // ------------------------------------------------------------------
    // State helpers
    // ------------------------------------------------------------------

    /**
     * The agent is considered configured once it has a server URL and an API key.
     * It does not require a successful registration — that happens lazily on the
     * first sync attempt.
     */
    fun isConfigured(): Boolean {
        val url = serverUrl.trim()
        val key = apiKey.trim()
        return url.isNotEmpty() && key.isNotEmpty()
    }

    /** Wipes every stored setting, returning the app to first-run state. */
    fun clear() {
        prefs(getCtx()).edit().clear().apply()
    }

    // ------------------------------------------------------------------
    // Context holder
    // ------------------------------------------------------------------

    /** Application context set once by the App class (or first caller). */
    @Volatile
    private var appContext: Context? = null

    /** Initializes the internal app context reference; must be called before any getter/setter. */
    fun init(context: Context) {
        if (appContext == null) {
            appContext = context.applicationContext
        }
    }

    private fun getCtx(): Context =
        appContext ?: throw IllegalStateException("Prefs.init(context) must be called before use")
}
