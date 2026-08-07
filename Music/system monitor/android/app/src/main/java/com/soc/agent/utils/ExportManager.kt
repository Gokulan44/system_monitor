package com.soc.agent.utils

import android.content.Context
import android.os.Environment
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.ExportReportEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Utility for exporting usage data to CSV or JSON files.
 * Handles file creation, data serialization, and export record tracking.
 */
object ExportManager {

    private const val EXPORT_DIR = "SOC_Agent/Exports"

    /** Get or create the export directory. */
    private fun getExportDir(context: Context): File {
        val dir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            EXPORT_DIR
        )
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    /**
     * Export daily usage data to CSV.
     * Returns the created file.
     */
    suspend fun exportDailyUsageCsv(
        context: Context,
        from: Long,
        to: Long
    ): File = withContext(Dispatchers.IO) {
        val db = AppDatabase.getInstance(context)
        val dailyDao = db.dailyUsageDao()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val file = File(getExportDir(context), "daily_usage_$timestamp.csv")

        FileWriter(file).use { writer ->
            writer.append("Package,App Name,Date,Total Time (ms),Launches,Last Time\n")
            val cal1 = Calendar.getInstance().apply { timeInMillis = from }
            val fromDate = cal1.get(Calendar.YEAR) * 10000 + (cal1.get(Calendar.MONTH) + 1) * 100 + cal1.get(Calendar.DAY_OF_MONTH)
            val cal2 = Calendar.getInstance().apply { timeInMillis = to }
            val toDate = cal2.get(Calendar.YEAR) * 10000 + (cal2.get(Calendar.MONTH) + 1) * 100 + cal2.get(Calendar.DAY_OF_MONTH)
            val entries = dailyDao.getDateRange(fromDate, toDate)
            for (e in entries) {
                writer.append("${e.packageName},${e.appName},${e.date},${e.totalTimeMs},${e.launchCount},${e.lastTimeMs}\n")
            }
        }

        val cal1 = Calendar.getInstance().apply { timeInMillis = from }
        val fromDate = cal1.get(Calendar.YEAR) * 10000 + (cal1.get(Calendar.MONTH) + 1) * 100 + cal1.get(Calendar.DAY_OF_MONTH)
        val cal2 = Calendar.getInstance().apply { timeInMillis = to }
        val toDate = cal2.get(Calendar.YEAR) * 10000 + (cal2.get(Calendar.MONTH) + 1) * 100 + cal2.get(Calendar.DAY_OF_MONTH)

        // Record the export
        val record = ExportReportEntity(
            format = "csv",
            scope = "daily",
            dateFrom = from,
            dateTo = to,
            fileUri = file.absolutePath,
            fileSizeBytes = file.length(),
            recordCount = dailyDao.getDateRange(fromDate, toDate).size
        )
        db.exportReportDao().insert(record)
        file
    }

    /**
     * Export weekly usage data to CSV.
     */
    suspend fun exportWeeklyUsageCsv(
        context: Context,
        from: Long,
        to: Long
    ): File = withContext(Dispatchers.IO) {
        val db = AppDatabase.getInstance(context)
        val weeklyDao = db.weeklyUsageDao()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val file = File(getExportDir(context), "weekly_usage_$timestamp.csv")

        FileWriter(file).use { writer ->
            writer.append("Package,App Name,Week Start,Total Time (ms),Launches,Days Active\n")
            val entries = weeklyDao.getAllInRange(from, to)
            for (e in entries) {
                writer.append("${e.packageName},${e.appName},${e.weekStart},${e.totalTimeMs},${e.launchCount},${e.activeDays}\n")
            }
        }

        val record = ExportReportEntity(
            format = "csv",
            scope = "weekly",
            dateFrom = from,
            dateTo = to,
            fileUri = file.absolutePath,
            fileSizeBytes = file.length(),
            recordCount = weeklyDao.getAllInRange(from, to).size
        )
        db.exportReportDao().insert(record)
        file
    }

    /**
     * Export all app usage data to JSON.
     */
    suspend fun exportAppUsageJson(
        context: Context
    ): File = withContext(Dispatchers.IO) {
        val db = AppDatabase.getInstance(context)
        val appDao = db.appUsageDao()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val file = File(getExportDir(context), "app_usage_$timestamp.json")

        val json = JSONObject()
        json.put("exportDate", System.currentTimeMillis())
        val arr = JSONArray()
        for (e in appDao.getAll()) {
            val obj = JSONObject().apply {
                put("packageName", e.packageName)
                put("appName", e.appName)
                put("totalTimeMs", e.totalTimeMs)
                put("launchCount", e.launchCount)
                put("firstUsedMs", e.firstUsedMs)
                put("lastUsedMs", e.lastUsedMs)
            }
            arr.put(obj)
        }
        json.put("apps", arr)

        file.writeText(json.toString(2))

        val record = ExportReportEntity(
            format = "json",
            scope = "all",
            dateFrom = 0,
            dateTo = System.currentTimeMillis(),
            fileUri = file.absolutePath,
            fileSizeBytes = file.length(),
            recordCount = appDao.getAll().size
        )
        db.exportReportDao().insert(record)
        file
    }

    /**
     * Export monthly usage data to CSV.
     */
    suspend fun exportMonthlyUsageCsv(
        context: Context,
        from: Long,
        to: Long
    ): File = withContext(Dispatchers.IO) {
        val db = AppDatabase.getInstance(context)
        val monthlyDao = db.monthlyUsageDao()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val file = File(getExportDir(context), "monthly_usage_$timestamp.csv")

        FileWriter(file).use { writer ->
            writer.append("Package,App Name,Month Start,Total Time (ms),Launches,Days Active\n")
            val entries = monthlyDao.getAll()
            for (e in entries) {
                if (e.monthStart in from..to) {
                    writer.append("${e.packageName},${e.appName},${e.monthStart},${e.totalTimeMs},${e.launchCount},${e.activeDays}\n")
                }
            }
        }

        val record = ExportReportEntity(
            format = "csv",
            scope = "monthly",
            dateFrom = from,
            dateTo = to,
            fileUri = file.absolutePath,
            fileSizeBytes = file.length(),
            recordCount = file.readLines().size - 1
        )
        db.exportReportDao().insert(record)
        file
    }

    /**
     * Generate a weekly report from existing data.
     * Returns the generated WeeklyReportEntity.
     */
    suspend fun generateWeeklyReport(
        context: Context,
        weekStart: Long
    ): com.soc.agent.database.entity.WeeklyReportEntity = withContext(Dispatchers.IO) {
        val db = AppDatabase.getInstance(context)
        val dailyDao = db.dailyUsageDao()
        val screenTimeDao = db.screenTimeDao()
        val notificationDao = db.notificationCountDao()
        val focusDao = db.focusModeDao()

        val weekEnd = weekStart + 7 * 24 * 60 * 60 * 1000L
        val dateStart = com.soc.agent.services.AppUsageService.Companion.let {
            // Convert epoch to YYYYMMDD int for daily range
            val cal = java.util.Calendar.getInstance().apply { timeInMillis = weekStart }
            cal.get(java.util.Calendar.YEAR) * 10000 +
                (cal.get(java.util.Calendar.MONTH) + 1) * 100 +
                cal.get(java.util.Calendar.DAY_OF_MONTH)
        }
        val dateEnd = {
            val cal = java.util.Calendar.getInstance().apply { timeInMillis = weekEnd - 1 }
            cal.get(java.util.Calendar.YEAR) * 10000 +
                (cal.get(java.util.Calendar.MONTH) + 1) * 100 +
                cal.get(java.util.Calendar.DAY_OF_MONTH)
        }()

        // Aggregate from daily usage
        val allDaily = mutableListOf<com.soc.agent.database.entity.DailyUsageEntity>()
        val cal = java.util.Calendar.getInstance().apply { timeInMillis = weekStart }
        for (d in 0 until 7) {
            val dateInt = cal.get(java.util.Calendar.YEAR) * 10000 +
                (cal.get(java.util.Calendar.MONTH) + 1) * 100 +
                cal.get(java.util.Calendar.DAY_OF_MONTH)
            allDaily.addAll(dailyDao.getForDate(dateInt))
            cal.add(java.util.Calendar.DAY_OF_MONTH, 1)
        }

        val totalScreenTime = allDaily.sumOf { it.totalTimeMs }
        val totalLaunches = allDaily.sumOf { it.launchCount }
        val appsUsed = allDaily.map { it.packageName }.distinct().size

        // Top apps by total time
        val appTimes = allDaily.groupBy { it.packageName }
            .mapValues { it.value.sumOf { e -> e.totalTimeMs } }
            .entries.sortedByDescending { it.value }.take(5)
        val topAppsCsv = appTimes.joinToString(",") { "${it.key}:${it.value}" }

        // Daily totals for peak day
        val cal2 = java.util.Calendar.getInstance().apply { timeInMillis = weekStart }
        val dailyTotals = mutableListOf<Pair<Int, Long>>()
        for (d in 1..7) {
            val dateInt = cal2.get(java.util.Calendar.YEAR) * 10000 +
                (cal2.get(java.util.Calendar.MONTH) + 1) * 100 +
                cal2.get(java.util.Calendar.DAY_OF_MONTH)
            val dayTotal = allDaily.filter { it.date == dateInt }.sumOf { it.totalTimeMs }
            dailyTotals.add(d to dayTotal)
            cal2.add(java.util.Calendar.DAY_OF_MONTH, 1)
        }
        val peakDay = dailyTotals.maxByOrNull { it.second }

        // Peak hour from daily usage (if available)
        val peakHour = allDaily.groupBy { it.lastTimeMs / 3_600_000 }
            .maxByOrNull { it.value.size }?.key?.toInt()

        // Focus sessions this week
        val focusSessions = focusDao.getActive().count { it.startTimeMs in weekStart..weekEnd }

        val report = com.soc.agent.database.entity.WeeklyReportEntity(
            weekStart = weekStart,
            weekEnd = weekEnd,
            totalScreenTimeMs = totalScreenTime,
            totalLaunches = totalLaunches,
            appsUsedCount = appsUsed,
            topApps = topAppsCsv,
            mostUsedApp = appTimes.firstOrNull()?.key,
            mostUsedAppTimeMs = appTimes.firstOrNull()?.value ?: 0,
            peakDay = peakDay?.first,
            peakDayTimeMs = peakDay?.second ?: 0,
            avgDailyTimeMs = if (totalScreenTime > 0) totalScreenTime / 7 else 0,
            peakHour = peakHour,
            focusSessionsCount = focusSessions,
            focusTimeMs = 0
        )

        db.weeklyReportDao().insert(report)
        report
    }

    /** Get the exports directory path. */
    fun getExportPath(context: Context): String =
        getExportDir(context).absolutePath
}