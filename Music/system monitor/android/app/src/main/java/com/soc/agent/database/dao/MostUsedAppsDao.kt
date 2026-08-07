package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Query

/**
 * Read-only DAO that provides Most Used Apps rankings from existing usage data.
 * No new entity — these queries aggregate daily_usage / app_usage / weekly / monthly tables.
 */
@Dao
interface MostUsedAppsDao {

    /**
     * Top [limit] apps for a given day (YYYYMMDD int), ordered by total usage time descending.
     * Returns pairs of (packageName, totalTimeMs).
     */
    @Query("""
        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs
        FROM daily_usage
        WHERE date = :dateInt
        GROUP BY package_name
        ORDER BY totalTimeMs DESC
        LIMIT :limit
    """)
    suspend fun topAppsForDay(dateInt: Int, limit: Int = 10): List<UsageRank>

    /**
     * Top [limit] apps for a given week, ordered by total usage time descending.
     * @param weekStart Monday 00:00 epoch millis of the week.
     */
    @Query("""
        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs
        FROM weekly_usage
        WHERE week_start = :weekStart
        GROUP BY package_name
        ORDER BY totalTimeMs DESC
        LIMIT :limit
    """)
    suspend fun topAppsForWeek(weekStart: Long, limit: Int = 10): List<UsageRank>

    /**
     * Top [limit] apps for a given month, ordered by total usage time descending.
     * @param monthStart 1st-of-month 00:00 epoch millis.
     */
    @Query("""
        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs
        FROM monthly_usage
        WHERE month_start = :monthStart
        GROUP BY package_name
        ORDER BY totalTimeMs DESC
        LIMIT :limit
    """)
    suspend fun topAppsForMonth(monthStart: Long, limit: Int = 10): List<UsageRank>

    /**
     * All-time top [limit] apps by lifetime usage, from app_usage table.
     */
    @Query("""
        SELECT package_name AS packageName, total_time_ms AS totalTimeMs
        FROM app_usage
        ORDER BY totalTimeMs DESC
        LIMIT :limit
    """)
    suspend fun topAppsAllTime(limit: Int = 10): List<UsageRank>

    /**
     * Top [limit] apps across a date range, aggregated from daily_usage.
     * @param fromInt start date (YYYYMMDD int, inclusive)
     * @param toInt   end date (YYYYMMDD int, inclusive)
     */
    @Query("""
        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs
        FROM daily_usage
        WHERE date BETWEEN :fromInt AND :toInt
        GROUP BY package_name
        ORDER BY totalTimeMs DESC
        LIMIT :limit
    """)
    suspend fun topAppsInRange(fromInt: Int, toInt: Int, limit: Int = 10): List<UsageRank>

    /**
     * Most launched apps for a given day.
     */
    @Query("""
        SELECT package_name AS packageName, launch_count AS launchCount
        FROM daily_usage
        WHERE date = :dateInt
        ORDER BY launch_count DESC
        LIMIT :limit
    """)
    suspend fun mostLaunchedForDay(dateInt: Int, limit: Int = 10): List<LaunchRank>

    /**
     * Most launched apps for the current month.
     */
    @Query("""
        SELECT package_name AS packageName, SUM(launch_count) AS launchCount
        FROM daily_usage
        WHERE date BETWEEN :fromInt AND :toInt
        GROUP BY package_name
        ORDER BY launchCount DESC
        LIMIT :limit
    """)
    suspend fun mostLaunchedInRange(fromInt: Int, toInt: Int, limit: Int = 10): List<LaunchRank>
}

/** POJO for ranked usage queries (packageName + time). */
data class UsageRank(
    val packageName: String,
    val totalTimeMs: Long
)

/** POJO for ranked launch queries (packageName + count). */
data class LaunchRank(
    val packageName: String,
    val launchCount: Int
)