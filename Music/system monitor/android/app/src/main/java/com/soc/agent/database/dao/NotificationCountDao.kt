package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.NotificationCountEntity

@Dao
interface NotificationCountDao {

    /** Insert a notification event. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NotificationCountEntity)

    /** Total notifications across all apps. */
    @Query("SELECT COUNT(*) FROM notification_count")
    suspend fun totalNotifications(): Int

    /** Total notifications for a specific app. */
    @Query("SELECT COUNT(*) FROM notification_count WHERE package_name = :packageName")
    suspend fun totalForPackage(packageName: String): Int

    /** Total notifications for all apps on a specific day (YYYYMMDD int). */
    @Query("SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE date_int = :dateInt GROUP BY package_name ORDER BY notificationCount DESC")
    suspend fun notificationsPerPackageOnDay(dateInt: Int): List<PackageNotificationRank>

    /** Total notifications for all apps in a date range (YYYYMMDD int, inclusive). */
    @Query("SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE date_int BETWEEN :fromInt AND :toInt GROUP BY package_name ORDER BY notificationCount DESC")
    suspend fun notificationsPerPackageInRange(fromInt: Int, toInt: Int): List<PackageNotificationRank>

    /** Top notification-spamming apps (all-time). */
    @Query("SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count GROUP BY package_name ORDER BY notificationCount DESC LIMIT :limit")
    suspend fun topPackages(limit: Int = 10): List<PackageNotificationRank>

    /** Hourly distribution for a package in a date range. */
    @Query("SELECT hour_of_day AS hourOfDay, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt GROUP BY hour_of_day ORDER BY hour_of_day ASC")
    suspend fun hourlyDistribution(packageName: String, fromInt: Int, toInt: Int): List<HourlyNotification>

    /** Day-of-week distribution for a package in a date range. */
    @Query("SELECT day_of_week AS dayOfWeek, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt GROUP BY day_of_week ORDER BY day_of_week ASC")
    suspend fun weeklyDistribution(packageName: String, fromInt: Int, toInt: Int): List<DailyNotification>

    /** Most active notification hours across all apps. */
    @Query("SELECT hour_of_day AS hourOfDay, COUNT(*) AS notificationCount FROM notification_count WHERE date_int BETWEEN :fromInt AND :toInt GROUP BY hour_of_day ORDER BY notificationCount DESC LIMIT :limit")
    suspend fun topHoursAllApps(fromInt: Int, toInt: Int, limit: Int = 5): List<HourlyNotification>

    /** Daily notification count for a package (trend data). */
    @Query("SELECT date_int AS dateInt, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt GROUP BY date_int ORDER BY date_int ASC")
    suspend fun dailyTrend(packageName: String, fromInt: Int, toInt: Int): List<DailyNotificationCount>

    /** Most recent notifications for a package. */
    @Query("SELECT * FROM notification_count WHERE package_name = :packageName ORDER BY posted_at_ms DESC LIMIT :limit")
    suspend fun recentForPackage(packageName: String, limit: Int = 20): List<NotificationCountEntity>

    /** Notification count by category for a package. */
    @Query("SELECT category, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName GROUP BY category ORDER BY notificationCount DESC")
    suspend fun byCategory(packageName: String): List<CategoryNotification>

    /** High-priority notifications only. */
    @Query("SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE priority >= :minPriority AND date_int BETWEEN :fromInt AND :toInt GROUP BY package_name ORDER BY notificationCount DESC")
    suspend fun highPriorityByPackage(minPriority: Int = 3, fromInt: Int = 0, toInt: Int = 99999999): List<PackageNotificationRank>

    /** Date of first notification for a package. */
    @Query("SELECT MIN(posted_at_ms) FROM notification_count WHERE package_name = :packageName")
    suspend fun firstNotificationTime(packageName: String): Long?

    /** Date of last notification for a package. */
    @Query("SELECT MAX(posted_at_ms) FROM notification_count WHERE package_name = :packageName")
    suspend fun lastNotificationTime(packageName: String): Long?

    /** Unique active days for a package in a date range. */
    @Query("SELECT COUNT(DISTINCT date_int) FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt")
    suspend fun activeDaysInRange(packageName: String, fromInt: Int, toInt: Int): Int

    /** Delete notifications older than a given epoch millis. */
    @Query("DELETE FROM notification_count WHERE posted_at_ms < :beforeMs")
    suspend fun deleteOlderThan(beforeMs: Long)

    /** Total row count. */
    @Query("SELECT COUNT(*) FROM notification_count")
    suspend fun totalCount(): Int
}

/** POJO for per-package notification ranking. */
data class PackageNotificationRank(
    val packageName: String,
    val notificationCount: Int
)

/** POJO for hourly notification distribution. */
data class HourlyNotification(
    val hourOfDay: Int,
    val notificationCount: Int
)

/** POJO for day-of-week notification distribution. */
data class DailyNotification(
    val dayOfWeek: Int,
    val notificationCount: Int
)

/** POJO for daily notification count trend. */
data class DailyNotificationCount(
    val dateInt: Int,
    val notificationCount: Int
)

/** POJO for notification count by category. */
data class CategoryNotification(
    val category: String?,
    val notificationCount: Int
)