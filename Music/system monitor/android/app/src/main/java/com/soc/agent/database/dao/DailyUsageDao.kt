package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.soc.agent.database.entity.DailyUsageEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for daily app usage aggregates.
 */
@Dao
interface DailyUsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyUsage: DailyUsageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dailyUsageList: List<DailyUsageEntity>)

    @Query("SELECT * FROM daily_usage WHERE package_name = :packageName AND date = :date")
    suspend fun getForPackageAndDate(packageName: String, date: Int): DailyUsageEntity?

    @Query("SELECT * FROM daily_usage WHERE date = :date ORDER BY total_time_ms DESC")
    suspend fun getForDate(date: Int): List<DailyUsageEntity>

    @Query("SELECT * FROM daily_usage WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC, total_time_ms DESC")
    suspend fun getDateRange(startDate: Int, endDate: Int): List<DailyUsageEntity>

    @Query("SELECT * FROM daily_usage WHERE package_name = :packageName ORDER BY date DESC LIMIT :limit")
    suspend fun getForPackage(packageName: String, limit: Int = 30): List<DailyUsageEntity>

    @Query("SELECT * FROM daily_usage ORDER BY date DESC, total_time_ms DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 100): List<DailyUsageEntity>

    @Query("SELECT SUM(total_time_ms) FROM daily_usage WHERE date = :date")
    suspend fun getTotalTimeForDate(date: Int): Long?

    @Query("SELECT COUNT(*) FROM daily_usage WHERE date = :date")
    suspend fun getAppCountForDate(date: Int): Int

    @Query("DELETE FROM daily_usage WHERE date < :beforeDate")
    suspend fun deleteOlderThan(beforeDate: Int): Int

    @Query("DELETE FROM daily_usage WHERE package_name = :packageName")
    suspend fun deleteForPackage(packageName: String): Int

    @Transaction
    @Query("SELECT * FROM daily_usage WHERE date = :date ORDER BY total_time_ms DESC")
    suspend fun getForDateWithApps(date: Int): List<DailyUsageEntity>

    /** Get all entries within a date range (epoch millis converted to date ints internally). */
    @Query("SELECT * FROM daily_usage WHERE date >= :fromDate AND date <= :toDate ORDER BY date DESC")
    suspend fun getAllInRange(fromDate: Int, toDate: Int): List<DailyUsageEntity>
}