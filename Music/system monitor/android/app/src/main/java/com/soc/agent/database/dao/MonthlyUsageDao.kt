package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.MonthlyUsageEntity

@Dao
interface MonthlyUsageDao {

    /** Upsert (insert or replace). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MonthlyUsageEntity)

    /** All rows for a given month start, ordered by total usage descending. */
    @Query("SELECT * FROM monthly_usage WHERE month_start = :monthStart ORDER BY total_time_ms DESC")
    suspend fun getForMonth(monthStart: Long): List<MonthlyUsageEntity>

    /** Single row for a package within a month. */
    @Query("SELECT * FROM monthly_usage WHERE package_name = :packageName AND month_start = :monthStart LIMIT 1")
    suspend fun getForPackageAndMonth(packageName: String, monthStart: Long): MonthlyUsageEntity?

    /** All months available (distinct month_start values), newest first. */
    @Query("SELECT DISTINCT month_start FROM monthly_usage ORDER BY month_start DESC")
    suspend fun getAllMonths(): List<Long>

    /** All rows across months for a package, ordered by month descending. */
    @Query("SELECT * FROM monthly_usage WHERE package_name = :packageName ORDER BY month_start DESC")
    suspend fun getAllForPackage(packageName: String): List<MonthlyUsageEntity>

    /** Recent rows across all packages/months. */
    @Query("SELECT * FROM monthly_usage ORDER BY updated_at DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 100): List<MonthlyUsageEntity>

    /** Delete rows older than a given epoch millis. */
    @Query("DELETE FROM monthly_usage WHERE month_start < :beforeMs")
    suspend fun deleteOlderThan(beforeMs: Long)

    /** Total time and launch count for a month. */
    @Query("SELECT COALESCE(SUM(total_time_ms), 0) AS totalTimeMs, COALESCE(SUM(launch_count), 0) AS totalLaunches FROM monthly_usage WHERE month_start = :monthStart")
    suspend fun getMonthTotals(monthStart: Long): MonthTotals

    /** Count all monthly rows. */
    @Query("SELECT COUNT(*) FROM monthly_usage")
    suspend fun totalCount(): Int

    /** Get all monthly usage entries, newest first. */
    @Query("SELECT * FROM monthly_usage ORDER BY month_start DESC")
    suspend fun getAll(): List<MonthlyUsageEntity>
}

data class MonthTotals(
    val totalTimeMs: Long,
    val totalLaunches: Int
)