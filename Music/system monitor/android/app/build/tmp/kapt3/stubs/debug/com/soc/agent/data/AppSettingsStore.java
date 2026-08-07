package com.soc.agent.data;

import android.content.SharedPreferences;

/**
 * Data models and key constants for SettingsRepository.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b7\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/soc/agent/data/AppSettingsStore;", "", "()V", "KEY_ANALYTICS", "", "KEY_APP_LOCK_HISTORY_ENABLED", "KEY_AUDIT_LOG_ENABLED", "KEY_AUTO_REFRESH", "KEY_AUTO_SCAN", "KEY_CAMERA_ACCESS", "KEY_DARK_MODE", "KEY_DASHBOARD_LAYOUT", "KEY_DATA_COLLECTION", "KEY_DATE_FORMAT", "KEY_DEVICE_HISTORY_ENABLED", "KEY_DEVICE_ID", "KEY_DEVICE_LAST_SYNC", "KEY_DEVICE_MODEL", "KEY_DEVICE_NAME", "KEY_FACE_UNLOCK", "KEY_FINGERPRINT_LOGIN", "KEY_LANGUAGE", "KEY_LAST_BACKUP_MS", "KEY_LAST_SYNC_MS", "KEY_LOCATION_ACCESS", "KEY_LOGIN_HISTORY_ENABLED", "KEY_LOGIN_NOTIFICATIONS", "KEY_NOTIFY_APP_LOCK_ALERTS", "KEY_NOTIFY_DEVICE_ALERTS", "KEY_NOTIFY_EMAIL_NOTIFICATIONS", "KEY_NOTIFY_MALWARE_ALERTS", "KEY_NOTIFY_PUSH_NOTIFICATIONS", "KEY_NOTIFY_SCAN_COMPLETED", "KEY_NOTIFY_SECURITY_ALERTS", "KEY_NOTIFY_SOUND_VIBRATION", "KEY_PASSWORD_LOCK", "KEY_PATTERN_LOCK", "KEY_PIN_LOCK", "KEY_REGISTERED_DEVICE_COUNT", "KEY_SCAN_HISTORY_ENABLED", "KEY_SCAN_SCHEDULE", "KEY_SECURITY_ALERTS", "KEY_SECURITY_EVENTS_ENABLED", "KEY_STORAGE_ACCESS", "KEY_STORAGE_USED_BYTES", "KEY_SYNC_ENABLED", "KEY_THEME", "KEY_TIME_FORMAT", "KEY_TRUSTED_DEVICES", "KEY_USAGE_ACCESS", "KEY_USER_ACCOUNT_STATUS", "KEY_USER_AVATAR_URI", "KEY_USER_EMAIL", "KEY_USER_LAST_LOGIN", "KEY_USER_MEMBER_SINCE", "KEY_USER_NAME", "KEY_USER_PHONE", "KEY_USER_SECURITY_SCORE", "PREFS_NAME", "app_debug"})
public final class AppSettingsStore {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREFS_NAME = "soc_agent_settings";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_NAME = "user_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_EMAIL = "user_email";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_PHONE = "user_phone";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_AVATAR_URI = "user_avatar_uri";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_SECURITY_SCORE = "user_security_score";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_MEMBER_SINCE = "user_member_since";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_ACCOUNT_STATUS = "user_account_status";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USER_LAST_LOGIN = "user_last_login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_FINGERPRINT_LOGIN = "fingerprint_login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_FACE_UNLOCK = "face_unlock";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PIN_LOCK = "pin_lock";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PATTERN_LOCK = "pattern_lock";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PASSWORD_LOCK = "password_lock";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_TRUSTED_DEVICES = "trusted_devices";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SECURITY_ALERTS = "security_alerts";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LOGIN_NOTIFICATIONS = "login_notifications";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DEVICE_ID = "device_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DEVICE_NAME = "device_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DEVICE_MODEL = "device_model";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_REGISTERED_DEVICE_COUNT = "registered_device_count";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DEVICE_LAST_SYNC = "device_last_sync";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DATA_COLLECTION = "data_collection";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_ANALYTICS = "analytics";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_CAMERA_ACCESS = "camera_access";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LOCATION_ACCESS = "location_access";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_STORAGE_ACCESS = "storage_access";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_USAGE_ACCESS = "usage_access";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_SECURITY_ALERTS = "notify_security_alerts";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_MALWARE_ALERTS = "notify_malware_alerts";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_SCAN_COMPLETED = "notify_scan_completed";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_APP_LOCK_ALERTS = "notify_app_lock_alerts";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_DEVICE_ALERTS = "notify_device_alerts";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_EMAIL_NOTIFICATIONS = "notify_email_notifications";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_PUSH_NOTIFICATIONS = "notify_push_notifications";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_NOTIFY_SOUND_VIBRATION = "notify_sound_vibration";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DARK_MODE = "dark_mode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LANGUAGE = "language";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_THEME = "theme";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DATE_FORMAT = "date_format";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_TIME_FORMAT = "time_format";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_AUTO_REFRESH = "auto_refresh";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_AUTO_SCAN = "auto_scan";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SCAN_SCHEDULE = "scan_schedule";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DASHBOARD_LAYOUT = "dashboard_layout";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LAST_BACKUP_MS = "last_backup_ms";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SYNC_ENABLED = "sync_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LAST_SYNC_MS = "last_sync_ms";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_STORAGE_USED_BYTES = "storage_used_bytes";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LOGIN_HISTORY_ENABLED = "login_history_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SCAN_HISTORY_ENABLED = "scan_history_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DEVICE_HISTORY_ENABLED = "device_history_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_APP_LOCK_HISTORY_ENABLED = "app_lock_history_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SECURITY_EVENTS_ENABLED = "security_events_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_AUDIT_LOG_ENABLED = "audit_log_enabled";
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.data.AppSettingsStore INSTANCE = null;
    
    private AppSettingsStore() {
        super();
    }
}