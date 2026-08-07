package com.soc.agent.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\\\u001a\u00020]J\b\u0010^\u001a\u00020#H\u0002J\u000e\u0010_\u001a\u00020]2\u0006\u0010`\u001a\u00020#J\u0006\u0010a\u001a\u00020$J\u000e\u0010b\u001a\u00020$2\u0006\u0010`\u001a\u00020#J\u000e\u0010c\u001a\u00020:2\u0006\u0010`\u001a\u00020#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R$\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010%\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020$8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010*\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020$8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b+\u0010'\"\u0004\b,\u0010)R$\u0010-\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b.\u0010\u001c\"\u0004\b/\u0010\u001eR$\u00101\u001a\u0002002\u0006\u0010\u0019\u001a\u0002008F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b2\u00103\"\u0004\b4\u00105R$\u00106\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020$8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b7\u0010'\"\u0004\b8\u0010)R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010;\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b<\u0010\u001c\"\u0004\b=\u0010\u001eR$\u0010>\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b?\u0010\u001c\"\u0004\b@\u0010\u001eR$\u0010A\u001a\u0002002\u0006\u0010\u0019\u001a\u0002008F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bB\u00103\"\u0004\bC\u00105R$\u0010D\u001a\u0002002\u0006\u0010\u0019\u001a\u0002008F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bE\u00103\"\u0004\bF\u00105R$\u0010G\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bH\u0010\u001c\"\u0004\bI\u0010\u001eR$\u0010J\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bK\u0010\u001c\"\u0004\bL\u0010\u001eR$\u0010M\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bN\u0010\u001c\"\u0004\bO\u0010\u001eR$\u0010P\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020$8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bQ\u0010'\"\u0004\bR\u0010)R$\u0010S\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bT\u0010\u001c\"\u0004\bU\u0010\u001eR$\u0010V\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bW\u0010\u001c\"\u0004\bX\u0010\u001eR$\u0010Y\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bZ\u0010\u001c\"\u0004\b[\u0010\u001e\u00a8\u0006d"}, d2 = {"Lcom/soc/agent/utils/Prefs;", "", "()V", "KEY_AGENT_ID", "", "KEY_API_KEY", "KEY_APP_LOCK_ENABLED", "KEY_APP_LOCK_WATCHER", "KEY_AUTO_LOCK_MODE", "KEY_AUTO_LOCK_TIMEOUT", "KEY_BIOMETRIC_ENABLED", "KEY_CRYPTO_FALLBACK", "KEY_DEVICE_NAME", "KEY_LAST_ERROR", "KEY_LAST_SYNC", "KEY_LOCK_DELAY", "KEY_LOCK_METHOD", "KEY_PASSWORD_HASH", "KEY_PATTERN_HASH", "KEY_PIN_ENABLED", "KEY_PIN_HASH", "KEY_REGISTERED_DEVICE_ID", "KEY_SERVER_URL", "PREFS_NAME", "TAG", "value", "agentId", "getAgentId", "()Ljava/lang/String;", "setAgentId", "(Ljava/lang/String;)V", "apiKey", "getApiKey", "setApiKey", "appContext", "Landroid/content/Context;", "", "appLockEnabled", "getAppLockEnabled", "()Z", "setAppLockEnabled", "(Z)V", "appLockWatcherRunning", "getAppLockWatcherRunning", "setAppLockWatcherRunning", "autoLockMode", "getAutoLockMode", "setAutoLockMode", "", "autoLockTimeout", "getAutoLockTimeout", "()J", "setAutoLockTimeout", "(J)V", "biometricEnabled", "getBiometricEnabled", "setBiometricEnabled", "cached", "Landroid/content/SharedPreferences;", "deviceName", "getDeviceName", "setDeviceName", "lastError", "getLastError", "setLastError", "lastSync", "getLastSync", "setLastSync", "lockDelay", "getLockDelay", "setLockDelay", "lockMethod", "getLockMethod", "setLockMethod", "passwordHash", "getPasswordHash", "setPasswordHash", "patternHash", "getPatternHash", "setPatternHash", "pinEnabled", "getPinEnabled", "setPinEnabled", "pinHash", "getPinHash", "setPinHash", "registeredDeviceId", "getRegisteredDeviceId", "setRegisteredDeviceId", "serverUrl", "getServerUrl", "setServerUrl", "clear", "", "getCtx", "init", "context", "isConfigured", "isCryptoFallback", "prefs", "app_debug"})
public final class Prefs {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "Prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "soc_agent_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SERVER_URL = "serverUrl";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_API_KEY = "apiKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AGENT_ID = "agentId";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DEVICE_NAME = "deviceName";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_REGISTERED_DEVICE_ID = "registeredDeviceId";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_SYNC = "lastSync";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_ERROR = "lastError";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PIN_HASH = "pinHash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PIN_ENABLED = "pinEnabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PASSWORD_HASH = "passwordHash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_BIOMETRIC_ENABLED = "biometricEnabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CRYPTO_FALLBACK = "cryptoFallback";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_APP_LOCK_ENABLED = "appLockEnabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PATTERN_HASH = "patternHash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LOCK_METHOD = "lockMethod";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_APP_LOCK_WATCHER = "appLockWatcherRunning";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AUTO_LOCK_MODE = "autoLockMode";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AUTO_LOCK_TIMEOUT = "autoLockTimeout";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LOCK_DELAY = "lockDelay";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile android.content.SharedPreferences cached;
    
    /**
     * Application context set once by the App class (or first caller).
     */
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile android.content.Context appContext;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.utils.Prefs INSTANCE = null;
    
    private Prefs() {
        super();
    }
    
    /**
     * Returns the encrypted (or fallback plain) preferences instance.
     * The instance is cached per process; a single MasterKey is generated once
     * and reused. Creating the master key may take ~100ms the first time.
     */
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences prefs(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * True when the encryption layer failed and plain prefs are in use.
     */
    public final boolean isCryptoFallback(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getServerUrl() {
        return null;
    }
    
    public final void setServerUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getApiKey() {
        return null;
    }
    
    public final void setApiKey(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAgentId() {
        return null;
    }
    
    public final void setAgentId(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeviceName() {
        return null;
    }
    
    public final void setDeviceName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRegisteredDeviceId() {
        return null;
    }
    
    public final void setRegisteredDeviceId(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final long getLastSync() {
        return 0L;
    }
    
    public final void setLastSync(long value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastError() {
        return null;
    }
    
    public final void setLastError(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPinHash() {
        return null;
    }
    
    public final void setPinHash(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPasswordHash() {
        return null;
    }
    
    public final void setPasswordHash(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean getPinEnabled() {
        return false;
    }
    
    public final void setPinEnabled(boolean value) {
    }
    
    public final boolean getBiometricEnabled() {
        return false;
    }
    
    public final void setBiometricEnabled(boolean value) {
    }
    
    public final boolean getAppLockEnabled() {
        return false;
    }
    
    public final void setAppLockEnabled(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPatternHash() {
        return null;
    }
    
    public final void setPatternHash(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLockMethod() {
        return null;
    }
    
    public final void setLockMethod(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean getAppLockWatcherRunning() {
        return false;
    }
    
    public final void setAppLockWatcherRunning(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAutoLockMode() {
        return null;
    }
    
    public final void setAutoLockMode(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final long getAutoLockTimeout() {
        return 0L;
    }
    
    public final void setAutoLockTimeout(long value) {
    }
    
    public final long getLockDelay() {
        return 0L;
    }
    
    public final void setLockDelay(long value) {
    }
    
    /**
     * The agent is considered configured once it has a server URL and an API key.
     * It does not require a successful registration — that happens lazily on the
     * first sync attempt.
     */
    public final boolean isConfigured() {
        return false;
    }
    
    /**
     * Wipes every stored setting, returning the app to first-run state.
     */
    public final void clear() {
    }
    
    /**
     * Initializes the internal app context reference; must be called before any getter/setter.
     */
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final android.content.Context getCtx() {
        return null;
    }
}