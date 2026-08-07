package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.LaunchCountEntity

@Dao
interface LaunchCountDao {

    /** Insert a launch event. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LaunchCountEntity)

    /** Bulk insert (for batch imports). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<LaunchCountEntity>)

    /** Total launches for a package. */
    @Query("SELECT COUNT(*) FROM launch_count WHERE package_name = :packageName")
    suspend fun totalLaunchesForPackage(packageName: String): Int

    /** Total launches across all apps. */
    @Query("SELECT COUNT(*) FROM launch_count")
    suspend fun totalLaunches(): Int

    /** Launch count for a package on a specific day (YYYYMMDD int). */
    @Query("SELECT COUNT(*) FROM launch_count WHERE package_name = :packageName AND date_int = :dateInt")
    suspend fun launchesForPackageOnDay(packageName: String, dateInt: Int): Int

    /** Launch count for a package in a date range (YYYYMMDD int, inclusive). */
    @Query("SELECT COUNT(*) FROM launch_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt")
    suspend fun launchesForPackageInRange(packageName: String, fromInt: Int, toInt: Int): Int

    /** Launch count for all apps on a specific day, ordered by count descending. */
    @Query("""
        SELECT package_name AS packageName, COUNT(*) AS launchCount
        FROM launch_count
        WHERE date_int = :dateInt
        GROUP BY package_name
        ORDER BY launchCount DESC
    """)
    suspend fun launchesPerPackageOnDay(dateInt: Int): List<PackageLaunchRank>

    /** Launch count for all apps in a date range, ordered by count descending. */
    @Query("""
        SELECT package_name AS packageName, COUNT(*) AS launchCount
        FROM launch_count
        WHERE date_int BETWEEN :fromInt AND :toInt
        GROUP BY package_name
        ORDER BY launchCount DESC
    """)
    suspend fun launchesPerPackageInRange(fromInt: Int, toInt: Int): List<PackageLaunchRank>

    /** Hourly distribution for a package (how many launches per hour of day). */
    @Query("""
        SELECT hour_of_day AS hourOfDay, COUNT(*) AS launchCount
        FROM launch_count
        WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt
        GROUP BY hour_of_day
        ORDER BY hour_of_day ASC
    """)
    suspend fun hourlyDistribution(packageName: String, fromInt: Int, toInt: Int): List<HourlyLaunch>

    /** Day-of-week distribution for a package. */
    @Query("""
        SELECT day_of_week AS dayOfWeek, COUNT(*) AS launchCount
        FROM launch_count
        WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt
        GROUP BY day_of_week
        ORDER BY day_of_week ASC
    """)
    suspend fun weeklyDistribution(packageName: String, fromInt: Int, toInt: Int): List<DailyLaunch>

    /** Most active hours across all apps in a date range. */
    @Query("""
        SELECT hour_of_day AS hourOfDay, COUNT(*) AS launchCount
        FROM launch_count
        WHERE date_int BETWEEN :fromInt AND :toInt
        GROUP BY hour_of_day
        ORDER BY launchCount DESC
        LIMIT :limit
    """)
    suspend fun topHoursAllApps(fromInt: Int, toInt: Int, limit: Int = 5): List<HourlyLaunch>

    /** Recent launches for a package (latest N). */
    @Query("""
        SELECT * FROM launch_count
        WHERE package_name = :packageName
        ORDER BY launch_time_ms DESC
        LIMIT :limit
    """)
    suspend fun recentLaunches(packageName: String, limit: Int = 20): List<LaunchCountEntity>

    /** Date of first launch for a package. */
    @Query("SELECT MIN(launch_time_ms) FROM launch_count WHERE package_name = :packageName")
    suspend fun firstLaunchTime(packageName: String): Long?

    /** Date of last launch for a package. */
    @Query("SELECT MAX(launch_time_ms) FROM launch_count WHERE package_name = :packageName")
    suspend fun lastLaunchTime(packageName: String): Long?

    /** Unique active days for a package in a date range. */
    @Query("SELECT COUNT(DISTINCT date_int) FROM launch_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt")
    suspend fun activeDaysInRange(packageName: String, fromInt: Int, toInt: Int): Int

    /** Launch streak: consecutive days with launches ending at [toDateInt]. */
    @Query("""
        SELECT DISTINCT date_int FROM launch_count
        WHERE package_name = :packageName AND date_int <= :toDateInt
        ORDER BY date_int DESC
    """)
    suspend fun launchDatesDesc(packageName: String, toDateInt: Int): List<Int>

    /** Delete launches older than a given epoch millis. */
    @Query("DELETE FROM launch_count WHERE launch_time_ms < :beforeMs")
    suspend fun deleteOlderThan(beforeMs: Long)

    /** Total row count. */
    @Query("SELECT COUNT(*) FROM launch_count")
    suspend fun totalCount(): Int
}

/** POJO for per-package launch ranking. */
data class PackageLaunchRank(
    val packageName: String,
    val launchCount: Int
)

/** POJO for hourly launch distribution. */
data class HourlyLaunch(
    val hourOfDay: Int,
    val launchCount: Int
)

/** POJO for day-of-week launch distribution. */
data class DailyLaunch(
    val dayOfWeek: Int,
    val launchCount: Int
)