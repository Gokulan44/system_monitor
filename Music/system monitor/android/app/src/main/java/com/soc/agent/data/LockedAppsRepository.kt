package com.soc.agent.data

import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.LockedAppEntity
import com.soc.agent.database.entity.UnlockHistoryEntity

/**
 * Repository for App Lock data — wraps LockedAppDao and UnlockHistoryDao
 * behind a clean API. All methods are suspend functions (call from coroutines).
 */
class LockedAppsRepository private constructor(db: AppDatabase) {

    private val lockedDao = db.lockedAppDao()
    private val historyDao = db.unlockHistoryDao()

    // ── Locked Apps ──────────────────────────────────────────────────

    /** Lock an app with the given lock method (pin/pattern). */
    suspend fun lockApp(packageName: String, lockMethod: String = "pin", name: String = "") {
        lockedDao.insertLockedApp(
            LockedAppEntity(
                packageName = packageName,
                name = name,
                lockMethod = lockMethod,
                addedAt = System.currentTimeMillis(),
                enabled = true
            )
        )
    }

    /** Remove lock for a single app. */
    suspend fun unlockApp(packageName: String) {
        lockedDao.removeLockedApp(packageName)
    }

    /** Remove locks for multiple apps. */
    suspend fun unlockApps(packageNames: List<String>) {
        lockedDao.removeLockedApps(packageNames)
    }

    /** Get all locked apps, sorted by name. */
    suspend fun getLockedApps(): List<LockedAppEntity> {
        return lockedDao.getLockedAppsSorted()
    }

    /** Check if a specific app is locked. */
    suspend fun isAppLocked(packageName: String): Boolean {
        return lockedDao.isLocked(packageName) > 0
    }

    /** Enable or disable lock for an app. */
    suspend fun setEnabled(packageName: String, enabled: Boolean) {
        lockedDao.setEnabled(packageName, enabled)
    }

    /** Get count of locked apps. */
    suspend fun getLockedCount(): Int {
        return lockedDao.countEnabled()
    }

    /** Get all enabled locked package names. */
    suspend fun getEnabledPackages(): List<String> {
        return lockedDao.getEnabledPackages()
    }

    /** Backup all locked apps. */
    suspend fun backupAll(): List<LockedAppEntity> {
        return lockedDao.getAll()
    }

    /** Restore locked apps from backup (clears existing). */
    suspend fun restoreAll(apps: List<LockedAppEntity>) {
        lockedDao.clearAll()
        lockedDao.insertLockedApps(apps)
    }

    // ── Unlock History ───────────────────────────────────────────────

    /** Record an unlock event. */
    suspend fun recordUnlock(
        packageName: String,
        gateMethod: String = "pin",
        appName: String = "",
        autoUnlock: Boolean = false
    ) {
        historyDao.insert(
            UnlockHistoryEntity(
                packageName = packageName,
                appName = appName,
                gateMethod = gateMethod,
                timestamp = System.currentTimeMillis(),
                autoUnlock = autoUnlock
            )
        )
    }

    /** Get recent unlock history. */
    suspend fun getUnlockHistory(limit: Int = 100): List<UnlockHistoryEntity> {
        return historyDao.getRecentHistory(limit)
    }

    /** Get unlock history for a specific app. */
    suspend fun getHistoryForPackage(
        packageName: String,
        limit: Int = 50
    ): List<UnlockHistoryEntity> {
        return historyDao.getHistoryForPackage(packageName, limit)
    }

    /** Purge unlock history older than the given timestamp. */
    suspend fun purgeHistory(olderThanMs: Long) {
        historyDao.deleteOlderThan(olderThanMs)
    }

    /** Backup all unlock history. */
    suspend fun backupHistory(): List<UnlockHistoryEntity> {
        return historyDao.getAll()
    }

    /** Restore unlock history from backup. */
    suspend fun restoreHistory(records: List<UnlockHistoryEntity>) {
        historyDao.deleteAll()
        historyDao.insertAll(records.toTypedArray())
    }

    companion object {
        @Volatile
        private var INSTANCE: LockedAppsRepository? = null

        fun getInstance(db: AppDatabase): LockedAppsRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LockedAppsRepository(db).also { INSTANCE = it }
            }
        }
    }
}