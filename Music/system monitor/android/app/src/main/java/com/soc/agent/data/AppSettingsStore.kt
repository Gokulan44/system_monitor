package com.soc.agent.data

import android.content.SharedPreferences

/**
 * Data models and key constants for SettingsRepository.
 */
object AppSettingsStore {

    const val PREFS_NAME = "soc_agent_settings"

    // UserProfile keys
    const val KEY_USER_NAME = "user_name"
    const val KEY_USER_EMAIL = "user_email"
    const val KEY_USER_PHONE = "user_phone"
    const val KEY_USER_AVATAR_URI = "user_avatar_uri"
    const val KEY_USER_SECURITY_SCORE = "user_security_score"
    const val KEY_USER_MEMBER_SINCE = "user_member_since"
    const val KEY_USER_ACCOUNT_STATUS = "user_account_status"
    const val KEY_USER_LAST_LOGIN = "user_last_login"

    // SecuritySettings keys
    const val KEY_FINGERPRINT_LOGIN = "fingerprint_login"
    const val KEY_FACE_UNLOCK = "face_unlock"
    const val KEY_PIN_LOCK = "pin_lock"
    const val KEY_PATTERN_LOCK = "pattern_lock"
    const val KEY_PASSWORD_LOCK = "password_lock"
    const val KEY_TRUSTED_DEVICES = "trusted_devices"
    const val KEY_SECURITY_ALERTS = "security_alerts"
    const val KEY_LOGIN_NOTIFICATIONS = "login_notifications"

    // DeviceInfo keys
    const val KEY_DEVICE_ID = "device_id"
    const val KEY_DEVICE_NAME = "device_name"
    const val KEY_DEVICE_MODEL = "device_model"
    const val KEY_REGISTERED_DEVICE_COUNT = "registered_device_count"
    const val KEY_DEVICE_LAST_SYNC = "device_last_sync"

    // PrivacySettings keys
    const val KEY_DATA_COLLECTION = "data_collection"
    const val KEY_ANALYTICS = "analytics"
    const val KEY_CAMERA_ACCESS = "camera_access"
    const val KEY_LOCATION_ACCESS = "location_access"
    const val KEY_STORAGE_ACCESS = "storage_access"
    const val KEY_USAGE_ACCESS = "usage_access"

    // NotificationPrefs keys
    const val KEY_NOTIFY_SECURITY_ALERTS = "notify_security_alerts"
    const val KEY_NOTIFY_MALWARE_ALERTS = "notify_malware_alerts"
    const val KEY_NOTIFY_SCAN_COMPLETED = "notify_scan_completed"
    const val KEY_NOTIFY_APP_LOCK_ALERTS = "notify_app_lock_alerts"
    const val KEY_NOTIFY_DEVICE_ALERTS = "notify_device_alerts"
    const val KEY_NOTIFY_EMAIL_NOTIFICATIONS = "notify_email_notifications"
    const val KEY_NOTIFY_PUSH_NOTIFICATIONS = "notify_push_notifications"
    const val KEY_NOTIFY_SOUND_VIBRATION = "notify_sound_vibration"

    // AppPreferences keys
    const val KEY_DARK_MODE = "dark_mode"
    const val KEY_LANGUAGE = "language"
    const val KEY_THEME = "theme"
    const val KEY_DATE_FORMAT = "date_format"
    const val KEY_TIME_FORMAT = "time_format"
    const val KEY_AUTO_REFRESH = "auto_refresh"
    const val KEY_AUTO_SCAN = "auto_scan"
    const val KEY_SCAN_SCHEDULE = "scan_schedule"
    const val KEY_DASHBOARD_LAYOUT = "dashboard_layout"

    // BackupSettings keys
    const val KEY_LAST_BACKUP_MS = "last_backup_ms"
    const val KEY_SYNC_ENABLED = "sync_enabled"
    const val KEY_LAST_SYNC_MS = "last_sync_ms"
    const val KEY_STORAGE_USED_BYTES = "storage_used_bytes"

    // ActivityLog keys
    const val KEY_LOGIN_HISTORY_ENABLED = "login_history_enabled"
    const val KEY_SCAN_HISTORY_ENABLED = "scan_history_enabled"
    const val KEY_DEVICE_HISTORY_ENABLED = "device_history_enabled"
    const val KEY_APP_LOCK_HISTORY_ENABLED = "app_lock_history_enabled"
    const val KEY_SECURITY_EVENTS_ENABLED = "security_events_enabled"
    const val KEY_AUDIT_LOG_ENABLED = "audit_log_enabled"
}
