package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.AppPermissionEntity
import com.soc.agent.database.entity.InstalledAppEntity

@Dao
interface AppDao {

    /** Insert/update the installed app inventory, replacing on conflict. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApps(apps: List<InstalledAppEntity>)

    /** Insert/update per-app permission records. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPermissions(permissions: List<AppPermissionEntity>)

    /** All installed apps, alphabetically. */
    @Query("SELECT * FROM installed_apps ORDER BY name COLLATE NOCASE ASC")
    suspend fun getAllApps(): List<InstalledAppEntity>

    /** Apps at or above the given risk level. */
    @Query("SELECT * FROM installed_apps WHERE risk = :risk ORDER BY name COLLATE NOCASE ASC")
    suspend fun getAppsByRisk(risk: String): List<InstalledAppEntity>

    /** Permissions declared by one package. */
    @Query("SELECT * FROM app_permissions WHERE device_id = :deviceId AND package_name = :packageName ORDER BY category ASC, name ASC")
    suspend fun getPermissionsForApp(deviceId: Long, packageName: String): List<AppPermissionEntity>

    /** Wipe the app inventory (e.g. before a fresh full inventory sync). */
    @Query("DELETE FROM installed_apps")
    suspend fun clearApps()

    /** Wipe all permission records. */
    @Query("DELETE FROM app_permissions")
    suspend fun clearPermissions()
}