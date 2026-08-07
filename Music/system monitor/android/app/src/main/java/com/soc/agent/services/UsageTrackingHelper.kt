package com.soc.agent.services

import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.MonthlyUsageEntity
import com.soc.agent.database.entity.ScreenTimeEntity
import com.soc.agent.database.entity.WeeklyUsageEntity
import java.util.Calendar

/**
 * Helper object providing date calculations and metadata updates for AppUsageService.
 */
object UsageTrackingHelper {

    fun getTodayDate(): Int {
        val cal = Calendar.getInstance()
        return cal.get(Calendar.YEAR) * 10000 +
               (cal.get(Calendar.MONTH) + 1) * 100 +
               cal.get(Calendar.DAY_OF_MONTH)
    }

    fun getDayStart(timeMs: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = timeMs
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }

    fun getWeekStart(timeMs: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = timeMs
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }

    fun dateInt(timeMs: Long): Int {
        val cal = Calendar.getInstance().apply { timeInMillis = timeMs }
        return cal.get(Calendar.YEAR) * 10000 +
               (cal.get(Calendar.MONTH) + 1) * 100 +
               cal.get(Calendar.DAY_OF_MONTH)
    }

    fun getMonthStart(timeMs: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = timeMs
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }

    suspend fun recordWeeklyLaunch(
        db: AppDatabase,
        packageName: String,
        appName: String,
        now: Long
    ) {
        val weekStart = getWeekStart(now)
        val today = dateInt(now).toString()
        val existing = db.weeklyUsageDao().getForPackageAndWeek(packageName, weekStart)
        val weekly = existing ?: WeeklyUsageEntity(
            packageName = packageName,
            appName = appName,
            weekStart = weekStart
        )
        val activeDates = weekly.activeDates
            .split(',')
            .filter { it.isNotBlank() }
            .toMutableSet()
        val wasNewDay = activeDates.add(today)
        weekly.launchCount++
        if (wasNewDay) {
            weekly.activeDates = activeDates.sorted().joinToString(",")
            weekly.activeDays = activeDates.size
        }
        weekly.updatedAt = now
        db.weeklyUsageDao().insert(weekly)
    }

    suspend fun recordMonthlyLaunch(
        db: AppDatabase,
        packageName: String,
        appName: String,
        now: Long
    ) {
        val monthStart = getMonthStart(now)
        val today = dateInt(now).toString()
        val existing = db.monthlyUsageDao().getForPackageAndMonth(packageName, monthStart)
        val monthly = existing ?: MonthlyUsageEntity(
            packageName = packageName,
            appName = appName,
            monthStart = monthStart
        )
        val activeDates = monthly.activeDates
            .split(',')
            .filter { it.isNotBlank() }
            .toMutableSet()
        val wasNewDay = activeDates.add(today)
        monthly.launchCount++
        if (wasNewDay) {
            monthly.activeDates = activeDates.sorted().joinToString(",")
            monthly.activeDays = activeDates.size
        }
        monthly.updatedAt = now
        db.monthlyUsageDao().insert(monthly)
    }

    suspend fun updateScreenTimeBucket(
        db: AppDatabase,
        duration: Long,
        now: Long,
        packageName: String
    ) {
        val dayStart = getDayStart(now)
        val daily = db.screenTimeDao().getForBucket(dayStart)
            ?: ScreenTimeEntity(bucketStart = dayStart, granularity = "daily")
        daily.totalTimeMs += duration
        daily.updatedAt = now
        db.screenTimeDao().insert(daily)

        val weekStart = getWeekStart(now)
        val weekly = db.screenTimeDao().getForBucket(weekStart)
            ?: ScreenTimeEntity(bucketStart = weekStart, granularity = "weekly")
        weekly.totalTimeMs += duration
        weekly.updatedAt = now
        db.screenTimeDao().insert(weekly)

        val monthStart = getMonthStart(now)
        val monthly = db.screenTimeDao().getForBucket(monthStart)
            ?: ScreenTimeEntity(bucketStart = monthStart, granularity = "monthly")
        monthly.totalTimeMs += duration
        monthly.updatedAt = now
        db.screenTimeDao().insert(monthly)
    }
}
