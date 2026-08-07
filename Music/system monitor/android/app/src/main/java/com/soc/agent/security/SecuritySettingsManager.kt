package com.soc.agent.security

import android.content.Context
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.SecuritySettingsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Manager for App Lock security settings.
 * Wraps the SecuritySettingsEntity DAO with defaults and in-memory caching.
 */
class SecuritySettingsManager private constructor(context: Context) {

    private val db = AppDatabase.getInstance(context.applicationContext)
    private var cached: SecuritySettingsEntity? = null

    companion object {
        @Volatile
        private var INSTANCE: SecuritySettingsManager? = null

        fun getInstance(context: Context): SecuritySettingsManager =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SecuritySettingsManager(context.applicationContext).also { INSTANCE = it }
            }
    }

    /** Get current settings (loads from DB on first call). */
    suspend fun getSettings(): SecuritySettingsEntity = withContext(Dispatchers.IO) {
        cached ?: db.securitySettingsDao().getSettings()
            ?: SecuritySettingsEntity().also { db.securitySettingsDao().upsert(it) }
            .also { cached = it }
    }

    /** Update settings and persist to DB. */
    suspend fun updateSettings(block: SecuritySettingsEntity.() -> SecuritySettingsEntity) = withContext(Dispatchers.IO) {
        val current = getSettings()
        val updated = current.copy(updatedAt = System.currentTimeMillis()).block()
        db.securitySettingsDao().upsert(updated)
        cached = updated
        updated
    }

    /** Check if intruder selfie is enabled and threshold reached. */
    suspend fun shouldCaptureSelfie(attemptNumber: Int): Boolean = withContext(Dispatchers.IO) {
        val s = getSettings()
        s.intruderSelfieEnabled && attemptNumber >= s.intruderSelfieThreshold
    }

    /** Check if fake crash is enabled. */
    suspend fun isFakeCrashEnabled(): Boolean = withContext(Dispatchers.IO) {
        getSettings().fakeCrashEnabled
    }

    /** Get fake crash message. */
    suspend fun getFakeCrashMessage(): String = withContext(Dispatchers.IO) {
        getSettings().fakeCrashMessage
    }

    /** Check if break-in alert is enabled. */
    suspend fun isBreakInAlertEnabled(): Boolean = withContext(Dispatchers.IO) {
        getSettings().breakinAlertEnabled
    }

    /** Check if vibrate on failed. */
    suspend fun isVibrateOnFailed(): Boolean = withContext(Dispatchers.IO) {
        getSettings().vibrateOnFailed
    }

    /** Get lock delay from settings. */
    suspend fun getLockDelayMs(): Long = withContext(Dispatchers.IO) {
        getSettings().lockDelayMs
    }

    /** Invalidate cache. */
    fun invalidateCache() {
        cached = null
    }
}