package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.WeeklyUsageEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for weekly app usage aggregates.
 */
@Dao
interface WeeklyUsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weeklyUsage: WeeklyUsageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weeklyUsageList: List<WeeklyUsageEntity>)

    @Query("SELECT * FROM weekly_usage WHERE package_name = :packageName AND week_start = :weekStart")
    suspend fun getForPackageAndWeek(packageName: String, weekStart: Long): WeeklyUsageEntity?

    @Query("SELECT * FROM weekly_usage WHERE week_start = :weekStart ORDER BY total_time_ms DESC")
    suspend fun getForWeek(weekStart: Long): List<WeeklyUsageEntity>

    @Query("SELECT * FROM weekly_usage WHERE week_start BETWEEN :startWeek AND :endWeek ORDER BY week_start DESC, total_time_ms DESC")
    suspend fun getWeekRange(startWeek: Long, endWeek: Long): List<WeeklyUsageEntity>

    @Query("SELECT * FROM weekly_usage WHERE package_name = :packageName ORDER BY week_start DESC LIMIT :limit")
    suspend fun getForPackage(packageName: String, limit: Int = 12): List<WeeklyUsageEntity>

    @Query("SELECT * FROM weekly_usage ORDER BY week_start DESC, total_time_ms DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 100): List<WeeklyUsageEntity>

    @Query("SELECT SUM(total_time_ms) FROM weekly_usage WHERE week_start = :weekStart")
    suspend fun getTotalTimeForWeek(weekStart: Long): Long?

    @Query("SELECT COUNT(*) FROM weekly_usage WHERE week_start = :weekStart")
    suspend fun getAppCountForWeek(weekStart: Long): Int

    @Query("SELECT week_start FROM weekly_usage ORDER BY week_start DESC LIMIT 1")
    suspend fun getLatestWeek(): Long?

    @Query("DELETE FROM weekly_usage WHERE week_start < :beforeWeek")
    suspend fun deleteOlderThan(beforeWeek: Long): Int

    @Query("DELETE FROM weekly_usage WHERE package_name = :packageName")
    suspend fun deleteForPackage(packageName: String): Int

    /** Get all entries within a week start range (epoch millis). */
    @Query("SELECT * FROM weekly_usage WHERE week_start >= :fromWeek AND week_start <= :toWeek ORDER BY week_start DESC")
    suspend fun getAllInRange(fromWeek: Long, toWeek: Long): List<WeeklyUsageEntity>
}