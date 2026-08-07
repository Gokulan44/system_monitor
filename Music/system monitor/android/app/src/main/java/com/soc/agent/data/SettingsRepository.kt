package com.soc.agent.data

import android.content.Context
import android.content.SharedPreferences

object SettingsRepository {

    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(AppSettingsStore.PREFS_NAME, Context.MODE_PRIVATE)
        }
    }

    private fun getPrefs(): SharedPreferences {
        return prefs ?: throw IllegalStateException("SettingsRepository not initialized.")
    }

    data class UserProfile(
        val name: String = "",
        val email: String = "",
        val phone: String = "",
        val avatarUri: String = "",
        val securityScore: Int = 0,
        val memberSince: Long = 0L,
        val accountStatus: String = "active",
        val lastLogin: Long = 0L
    )

    fun getUserProfile(): UserProfile {
        val p = getPrefs()
        return UserProfile(
            name = p.getString(AppSettingsStore.KEY_USER_NAME, "") ?: "",
            email = p.getString(AppSettingsStore.KEY_USER_EMAIL, "") ?: "",
            phone = p.getString(AppSettingsStore.KEY_USER_PHONE, "") ?: "",
            avatarUri = p.getString(AppSettingsStore.KEY_USER_AVATAR_URI, "") ?: "",
            securityScore = p.getInt(AppSettingsStore.KEY_USER_SECURITY_SCORE, 0),
            memberSince = p.getLong(AppSettingsStore.KEY_USER_MEMBER_SINCE, 0L),
            accountStatus = p.getString(AppSettingsStore.KEY_USER_ACCOUNT_STATUS, "active") ?: "active",
            lastLogin = p.getLong(AppSettingsStore.KEY_USER_LAST_LOGIN, 0L)
        )
    }

    fun saveUserProfile(profile: UserProfile) {
        getPrefs().edit()
            .putString(AppSettingsStore.KEY_USER_NAME, profile.name)
            .putString(AppSettingsStore.KEY_USER_EMAIL, profile.email)
            .putString(AppSettingsStore.KEY_USER_PHONE, profile.phone)
            .putString(AppSettingsStore.KEY_USER_AVATAR_URI, profile.avatarUri)
            .putInt(AppSettingsStore.KEY_USER_SECURITY_SCORE, profile.securityScore)
            .putLong(AppSettingsStore.KEY_USER_MEMBER_SINCE, profile.memberSince)
            .putString(AppSettingsStore.KEY_USER_ACCOUNT_STATUS, profile.accountStatus)
            .putLong(AppSettingsStore.KEY_USER_LAST_LOGIN, profile.lastLogin)
            .apply()
    }

    data class SecuritySettings(
        val fingerprintLogin: Boolean = false,
        val faceUnlock: Boolean = false,
        val pinLock: Boolean = false,
        val patternLock: Boolean = false,
        val passwordLock: Boolean = false,
        val trustedDevices: Int = 0,
        val securityAlerts: Boolean = true,
        val loginNotifications: Boolean = true
    )

    fun getSecuritySettings(): SecuritySettings {
        val p = getPrefs()
        return SecuritySettings(
            fingerprintLogin = p.getBoolean(AppSettingsStore.KEY_FINGERPRINT_LOGIN, false),
            faceUnlock = p.getBoolean(AppSettingsStore.KEY_FACE_UNLOCK, false),
            pinLock = p.getBoolean(AppSettingsStore.KEY_PIN_LOCK, false),
            patternLock = p.getBoolean(AppSettingsStore.KEY_PATTERN_LOCK, false),
            passwordLock = p.getBoolean(AppSettingsStore.KEY_PASSWORD_LOCK, false),
            trustedDevices = p.getInt(AppSettingsStore.KEY_TRUSTED_DEVICES, 0),
            securityAlerts = p.getBoolean(AppSettingsStore.KEY_SECURITY_ALERTS, true),
            loginNotifications = p.getBoolean(AppSettingsStore.KEY_LOGIN_NOTIFICATIONS, true)
        )
    }

    fun saveSecuritySettings(settings: SecuritySettings) {
        getPrefs().edit()
            .putBoolean(AppSettingsStore.KEY_FINGERPRINT_LOGIN, settings.fingerprintLogin)
            .putBoolean(AppSettingsStore.KEY_FACE_UNLOCK, settings.faceUnlock)
            .putBoolean(AppSettingsStore.KEY_PIN_LOCK, settings.pinLock)
            .putBoolean(AppSettingsStore.KEY_PATTERN_LOCK, settings.patternLock)
            .putBoolean(AppSettingsStore.KEY_PASSWORD_LOCK, settings.passwordLock)
            .putInt(AppSettingsStore.KEY_TRUSTED_DEVICES, settings.trustedDevices)
            .putBoolean(AppSettingsStore.KEY_SECURITY_ALERTS, settings.securityAlerts)
            .putBoolean(AppSettingsStore.KEY_LOGIN_NOTIFICATIONS, settings.loginNotifications)
            .apply()
    }

    fun setFingerprintLogin(enabled: Boolean) = getPrefs().edit().putBoolean(AppSettingsStore.KEY_FINGERPRINT_LOGIN, enabled).apply()
    fun setFaceUnlock(enabled: Boolean) = getPrefs().edit().putBoolean(AppSettingsStore.KEY_FACE_UNLOCK, enabled).apply()
    fun setPinLock(enabled: Boolean) = getPrefs().edit().putBoolean(AppSettingsStore.KEY_PIN_LOCK, enabled).apply()
    fun setPatternLock(enabled: Boolean) = getPrefs().edit().putBoolean(AppSettingsStore.KEY_PATTERN_LOCK, enabled).apply()
    fun setPasswordLock(enabled: Boolean) = getPrefs().edit().putBoolean(AppSettingsStore.KEY_PASSWORD_LOCK, enabled).apply()
    fun setSecurityAlerts(enabled: Boolean) = getPrefs().edit().putBoolean(AppSettingsStore.KEY_SECURITY_ALERTS, enabled).apply()
    fun setLoginNotifications(enabled: Boolean) = getPrefs().edit().putBoolean(AppSettingsStore.KEY_LOGIN_NOTIFICATIONS, enabled).apply()

    data class DeviceInfo(
        val deviceId: String = "",
        val deviceName: String = "",
        val deviceModel: String = "",
        val registeredDeviceCount: Int = 1,
        val lastSync: Long = 0L
    )

    fun getDeviceInfo(): DeviceInfo {
        val p = getPrefs()
        return DeviceInfo(
            deviceId = p.getString(AppSettingsStore.KEY_DEVICE_ID, "") ?: "",
            deviceName = p.getString(AppSettingsStore.KEY_DEVICE_NAME, "") ?: "",
            deviceModel = p.getString(AppSettingsStore.KEY_DEVICE_MODEL, "") ?: "",
            registeredDeviceCount = p.getInt(AppSettingsStore.KEY_REGISTERED_DEVICE_COUNT, 1),
            lastSync = p.getLong(AppSettingsStore.KEY_DEVICE_LAST_SYNC, 0L)
        )
    }

    data class PrivacySettings(
        val dataCollection: Boolean = true,
        val analytics: Boolean = true,
        val cameraAccess: Boolean = false,
        val locationAccess: Boolean = false,
        val storageAccess: Boolean = false,
        val usageAccess: Boolean = false
    )

    fun getPrivacySettings(): PrivacySettings {
        val p = getPrefs()
        return PrivacySettings(
            dataCollection = p.getBoolean(AppSettingsStore.KEY_DATA_COLLECTION, true),
            analytics = p.getBoolean(AppSettingsStore.KEY_ANALYTICS, true),
            cameraAccess = p.getBoolean(AppSettingsStore.KEY_CAMERA_ACCESS, false),
            locationAccess = p.getBoolean(AppSettingsStore.KEY_LOCATION_ACCESS, false),
            storageAccess = p.getBoolean(AppSettingsStore.KEY_STORAGE_ACCESS, false),
            usageAccess = p.getBoolean(AppSettingsStore.KEY_USAGE_ACCESS, false)
        )
    }

    fun savePrivacySettings(settings: PrivacySettings) {
        getPrefs().edit()
            .putBoolean(AppSettingsStore.KEY_DATA_COLLECTION, settings.dataCollection)
            .putBoolean(AppSettingsStore.KEY_ANALYTICS, settings.analytics)
            .putBoolean(AppSettingsStore.KEY_CAMERA_ACCESS, settings.cameraAccess)
            .putBoolean(AppSettingsStore.KEY_LOCATION_ACCESS, settings.locationAccess)
            .putBoolean(AppSettingsStore.KEY_STORAGE_ACCESS, settings.storageAccess)
            .putBoolean(AppSettingsStore.KEY_USAGE_ACCESS, settings.usageAccess)
            .apply()
    }

    data class NotificationPrefs(
        val securityAlerts: Boolean = true,
        val malwareAlerts: Boolean = true,
        val scanCompleted: Boolean = true,
        val appLockAlerts: Boolean = true,
        val deviceAlerts: Boolean = true,
        val emailNotifications: Boolean = false,
        val pushNotifications: Boolean = true,
        val soundVibration: Boolean = true
    )

    fun getNotificationPrefs(): NotificationPrefs {
        val p = getPrefs()
        return NotificationPrefs(
            securityAlerts = p.getBoolean(AppSettingsStore.KEY_NOTIFY_SECURITY_ALERTS, true),
            malwareAlerts = p.getBoolean(AppSettingsStore.KEY_NOTIFY_MALWARE_ALERTS, true),
            scanCompleted = p.getBoolean(AppSettingsStore.KEY_NOTIFY_SCAN_COMPLETED, true),
            appLockAlerts = p.getBoolean(AppSettingsStore.KEY_NOTIFY_APP_LOCK_ALERTS, true),
            deviceAlerts = p.getBoolean(AppSettingsStore.KEY_NOTIFY_DEVICE_ALERTS, true),
            emailNotifications = p.getBoolean(AppSettingsStore.KEY_NOTIFY_EMAIL_NOTIFICATIONS, false),
            pushNotifications = p.getBoolean(AppSettingsStore.KEY_NOTIFY_PUSH_NOTIFICATIONS, true),
            soundVibration = p.getBoolean(AppSettingsStore.KEY_NOTIFY_SOUND_VIBRATION, true)
        )
    }

    fun saveNotificationPrefs(prefs: NotificationPrefs) {
        getPrefs().edit()
            .putBoolean(AppSettingsStore.KEY_NOTIFY_SECURITY_ALERTS, prefs.securityAlerts)
            .putBoolean(AppSettingsStore.KEY_NOTIFY_MALWARE_ALERTS, prefs.malwareAlerts)
            .putBoolean(AppSettingsStore.KEY_NOTIFY_SCAN_COMPLETED, prefs.scanCompleted)
            .putBoolean(AppSettingsStore.KEY_NOTIFY_APP_LOCK_ALERTS, prefs.appLockAlerts)
            .putBoolean(AppSettingsStore.KEY_NOTIFY_DEVICE_ALERTS, prefs.deviceAlerts)
            .putBoolean(AppSettingsStore.KEY_NOTIFY_EMAIL_NOTIFICATIONS, prefs.emailNotifications)
            .putBoolean(AppSettingsStore.KEY_NOTIFY_PUSH_NOTIFICATIONS, prefs.pushNotifications)
            .putBoolean(AppSettingsStore.KEY_NOTIFY_SOUND_VIBRATION, prefs.soundVibration)
            .apply()
    }

    data class AppPreferences(
        val darkMode: Boolean = true,
        val language: String = "en",
        val theme: String = "default",
        val dateFormat: String = "yyyy-MM-dd",
        val timeFormat: String = "HH:mm",
        val autoRefresh: Boolean = true,
        val autoScan: Boolean = false,
        val scanSchedule: String = "daily",
        val dashboardLayout: String = "grid"
    )

    fun getAppPreferences(): AppPreferences {
        val p = getPrefs()
        return AppPreferences(
            darkMode = p.getBoolean(AppSettingsStore.KEY_DARK_MODE, true),
            language = p.getString(AppSettingsStore.KEY_LANGUAGE, "en") ?: "en",
            theme = p.getString(AppSettingsStore.KEY_THEME, "default") ?: "default",
            dateFormat = p.getString(AppSettingsStore.KEY_DATE_FORMAT, "yyyy-MM-dd") ?: "yyyy-MM-dd",
            timeFormat = p.getString(AppSettingsStore.KEY_TIME_FORMAT, "HH:mm") ?: "HH:mm",
            autoRefresh = p.getBoolean(AppSettingsStore.KEY_AUTO_REFRESH, true),
            autoScan = p.getBoolean(AppSettingsStore.KEY_AUTO_SCAN, false),
            scanSchedule = p.getString(AppSettingsStore.KEY_SCAN_SCHEDULE, "daily") ?: "daily",
            dashboardLayout = p.getString(AppSettingsStore.KEY_DASHBOARD_LAYOUT, "grid") ?: "grid"
        )
    }

    fun saveAppPreferences(prefs: AppPreferences) {
        getPrefs().edit()
            .putBoolean(AppSettingsStore.KEY_DARK_MODE, prefs.darkMode)
            .putString(AppSettingsStore.KEY_LANGUAGE, prefs.language)
            .putString(AppSettingsStore.KEY_THEME, prefs.theme)
            .putString(AppSettingsStore.KEY_DATE_FORMAT, prefs.dateFormat)
            .putString(AppSettingsStore.KEY_TIME_FORMAT, prefs.timeFormat)
            .putBoolean(AppSettingsStore.KEY_AUTO_REFRESH, prefs.autoRefresh)
            .putBoolean(AppSettingsStore.KEY_AUTO_SCAN, prefs.autoScan)
            .putString(AppSettingsStore.KEY_SCAN_SCHEDULE, prefs.scanSchedule)
            .putString(AppSettingsStore.KEY_DASHBOARD_LAYOUT, prefs.dashboardLayout)
            .apply()
    }
}