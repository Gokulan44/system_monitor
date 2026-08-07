package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.LockedAppEntity

@Dao
interface LockedAppDao {

    /** Add an app to the lock list, replacing any existing row for the package. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLockedApp(app: LockedAppEntity): Long

    /** Add several locked apps in one transaction (bulk from the picker). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLockedApps(apps: List<LockedAppEntity>)

    /** Remove one locked app by package name. */
    @Query("DELETE FROM locked_apps WHERE package_name = :packageName")
    suspend fun removeLockedApp(packageName: String)

    /** Remove many locked apps at once (bulk unlock / app uninstalled). */
    @Query("DELETE FROM locked_apps WHERE package_name IN (:packageNames)")
    suspend fun removeLockedApps(packageNames: List<String>)

    /** Wipe the entire lock list (disable App Lock / reset). */
    @Query("DELETE FROM locked_apps")
    suspend fun clearAll()

    /** All locked apps, most recently added first. */
    @Query("SELECT * FROM locked_apps ORDER BY added_at DESC")
    suspend fun getAllLockedApps(): List<LockedAppEntity>

    /** Locked apps ordered by name for stable list rendering. */
    @Query("SELECT * FROM locked_apps ORDER BY name COLLATE NOCASE ASC")
    suspend fun getLockedAppsSorted(): List<LockedAppEntity>

    /** True when the given package is enabled in the lock set. */
    @Query("SELECT COUNT(*) FROM locked_apps WHERE package_name = :packageName AND enabled = 1")
    suspend fun isLocked(packageName: String): Int

    /** Number of enabled locks (for the dashboard stat). */
    @Query("SELECT COUNT(*) FROM locked_apps WHERE enabled = 1")
    suspend fun countEnabled(): Int

    /** Every enabled locked package name (watcher hot-set). */
    @Query("SELECT package_name FROM locked_apps WHERE enabled = 1")
    suspend fun getEnabledPackages(): List<String>

    /** Set the enabled flag for a specific app. */
    @Query("UPDATE locked_apps SET enabled = :enabled WHERE package_name = :packageName")
    suspend fun setEnabled(packageName: String, enabled: Boolean)

    /** Get all locked apps (for backup). */
    @Query("SELECT * FROM locked_apps")
    suspend fun getAll(): List<LockedAppEntity>

    /** Insert multiple apps (for restore). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apps: Array<LockedAppEntity>)

    /** Delete all locked apps (for restore). */
    @Query("DELETE FROM locked_apps")
    suspend fun deleteAll()
}