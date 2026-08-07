package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.AppUsageEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for overall app usage statistics.
 */
@Dao
interface AppUsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appUsage: AppUsageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(appUsageList: List<AppUsageEntity>)

    @Query("SELECT * FROM app_usage WHERE package_name = :packageName")
    suspend fun getForPackage(packageName: String): AppUsageEntity?

    @Query("SELECT * FROM app_usage ORDER BY total_time_ms DESC LIMIT :limit")
    suspend fun getTopApps(limit: Int = 50): List<AppUsageEntity>

    @Query("SELECT * FROM app_usage ORDER BY last_used_ms DESC LIMIT :limit")
    suspend fun getRecentlyUsed(limit: Int = 50): List<AppUsageEntity>

    @Query("SELECT * FROM app_usage ORDER BY launch_count DESC LIMIT :limit")
    suspend fun getMostLaunched(limit: Int = 50): List<AppUsageEntity>

    @Query("SELECT * FROM app_usage WHERE total_time_ms > :minTime ORDER BY total_time_ms DESC")
    suspend fun getAppsAboveTime(minTime: Long): List<AppUsageEntity>

    @Query("SELECT SUM(total_time_ms) FROM app_usage")
    suspend fun getTotalTimeAllApps(): Long?

    @Query("SELECT SUM(launch_count) FROM app_usage")
    suspend fun getTotalLaunchesAllApps(): Long?

    @Query("SELECT COUNT(*) FROM app_usage")
    suspend fun getTotalAppCount(): Int

    @Query("DELETE FROM app_usage WHERE package_name = :packageName")
    suspend fun deleteForPackage(packageName: String): Int

    @Query("DELETE FROM app_usage WHERE last_used_ms < :beforeTime")
    suspend fun deleteUnusedSince(beforeTime: Long): Int

    /** Get all app usage entries, sorted by total time descending. */
    @Query("SELECT * FROM app_usage ORDER BY total_time_ms DESC")
    suspend fun getAll(): List<AppUsageEntity>
}