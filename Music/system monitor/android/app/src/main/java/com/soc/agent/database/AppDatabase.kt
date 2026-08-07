package com.soc.agent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soc.agent.database.dao.AlertDao
import com.soc.agent.database.dao.AppDao
import com.soc.agent.database.dao.DeviceDao
import com.soc.agent.database.dao.LockedAppDao
import com.soc.agent.database.dao.NetworkDao
import com.soc.agent.database.dao.PolicyDao
import com.soc.agent.database.dao.ScanDao
import com.soc.agent.database.dao.TelemetryDao
import com.soc.agent.database.dao.UnlockHistoryDao
import com.soc.agent.database.dao.FailedAttemptDao
import com.soc.agent.database.dao.IntruderSelfieDao
import com.soc.agent.database.dao.SecuritySettingsDao
import com.soc.agent.database.dao.BackupDao
import com.soc.agent.database.dao.DailyUsageDao
import com.soc.agent.database.dao.AppUsageDao
import com.soc.agent.database.dao.UsageSessionDao
import com.soc.agent.database.dao.WeeklyUsageDao
import com.soc.agent.database.dao.MonthlyUsageDao
import com.soc.agent.database.dao.ScreenTimeDao
import com.soc.agent.database.dao.MostUsedAppsDao
import com.soc.agent.database.dao.LaunchCountDao
import com.soc.agent.database.dao.NotificationCountDao
import com.soc.agent.database.dao.UsageTimelineDao
import com.soc.agent.database.dao.FocusModeDao
import com.soc.agent.database.dao.DailyLimitDao
import com.soc.agent.database.dao.WeeklyReportDao
import com.soc.agent.database.dao.ExportReportDao
import com.soc.agent.database.entity.AlertEntity
import com.soc.agent.database.entity.AppPermissionEntity
import com.soc.agent.database.entity.BatteryStatusEntity
import com.soc.agent.database.entity.CpuUsageEntity
import com.soc.agent.database.entity.DeviceEntity
import com.soc.agent.database.entity.DeviceHistoryEntity
import com.soc.agent.database.entity.DeviceInfoEntity
import com.soc.agent.database.entity.FileScanLogEntity
import com.soc.agent.database.entity.InstalledAppEntity
import com.soc.agent.database.entity.IocEntity
import com.soc.agent.database.entity.LockedAppEntity
import com.soc.agent.database.entity.MalwareScanLogEntity
import com.soc.agent.database.entity.MemoryUsageEntity
import com.soc.agent.database.entity.NetworkLogEntity
import com.soc.agent.database.entity.PolicyEntity
import com.soc.agent.database.entity.ScanRunEntity
import com.soc.agent.database.entity.StorageUsageEntity
import com.soc.agent.database.entity.ThreatEntity
import com.soc.agent.database.entity.DailyUsageEntity
import com.soc.agent.database.entity.AppUsageEntity
import com.soc.agent.database.entity.UsageSessionEntity
import com.soc.agent.database.entity.WeeklyUsageEntity
import com.soc.agent.database.entity.MonthlyUsageEntity
import com.soc.agent.database.entity.LaunchCountEntity
import com.soc.agent.database.entity.NotificationCountEntity
import com.soc.agent.database.entity.UsageTimelineEntity
import com.soc.agent.database.entity.FocusModeEntity
import com.soc.agent.database.entity.DailyLimitEntity
import com.soc.agent.database.entity.WeeklyReportEntity
import com.soc.agent.database.entity.ExportReportEntity
import com.soc.agent.database.entity.ScreenTimeEntity
import com.soc.agent.database.entity.UnlockHistoryEntity
import com.soc.agent.database.entity.FailedAttemptEntity
import com.soc.agent.database.entity.IntruderSelfieEntity
import com.soc.agent.database.entity.SecuritySettingsEntity
import com.soc.agent.database.entity.BackupEntity

/**
 * The agent's local Room database. Holds the device record, telemetry
 * series, app inventory, scan results, alerts, network observations and the
 * server-enforced policy/IOC caches.
 *
 * Schema version 1; destructive migration is acceptable here because all
 * rows are re-synced from the server / re-collected from the device.
 */
@Database(
    entities = [
        DeviceEntity::class,
        DeviceHistoryEntity::class,
        CpuUsageEntity::class,
        MemoryUsageEntity::class,
        StorageUsageEntity::class,
        BatteryStatusEntity::class,
        DeviceInfoEntity::class,
        InstalledAppEntity::class,
        AppPermissionEntity::class,
        FileScanLogEntity::class,
        ScanRunEntity::class,
        MalwareScanLogEntity::class,
        ThreatEntity::class,
        AlertEntity::class,
        NetworkLogEntity::class,
        IocEntity::class,
        LockedAppEntity::class,
        PolicyEntity::class,
        UnlockHistoryEntity::class,
        FailedAttemptEntity::class,
        IntruderSelfieEntity::class,
        SecuritySettingsEntity::class,
        BackupEntity::class,
        DailyUsageEntity::class,
        AppUsageEntity::class,
        UsageSessionEntity::class,
        WeeklyUsageEntity::class,
        MonthlyUsageEntity::class,
        ScreenTimeEntity::class,
        LaunchCountEntity::class,
        NotificationCountEntity::class,
        UsageTimelineEntity::class,
        FocusModeEntity::class,
        DailyLimitEntity::class,
        WeeklyReportEntity::class,
        ExportReportEntity::class
    ],
    version = 14,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun deviceDao(): DeviceDao
    abstract fun telemetryDao(): TelemetryDao
    abstract fun appDao(): AppDao
    abstract fun scanDao(): ScanDao
    abstract fun alertDao(): AlertDao
    abstract fun networkDao(): NetworkDao
    abstract fun policyDao(): PolicyDao
    abstract fun lockedAppDao(): LockedAppDao
    abstract fun unlockHistoryDao(): UnlockHistoryDao
    abstract fun failedAttemptDao(): FailedAttemptDao
    abstract fun intruderSelfieDao(): IntruderSelfieDao
    abstract fun securitySettingsDao(): SecuritySettingsDao
    abstract fun backupDao(): BackupDao
    abstract fun dailyUsageDao(): DailyUsageDao
    abstract fun appUsageDao(): AppUsageDao
    abstract fun usageSessionDao(): UsageSessionDao
    abstract fun weeklyUsageDao(): WeeklyUsageDao
    abstract fun monthlyUsageDao(): MonthlyUsageDao
    abstract fun screenTimeDao(): ScreenTimeDao
    abstract fun mostUsedAppsDao(): MostUsedAppsDao
    abstract fun launchCountDao(): LaunchCountDao
    abstract fun notificationCountDao(): NotificationCountDao
    abstract fun usageTimelineDao(): UsageTimelineDao
    abstract fun focusModeDao(): FocusModeDao
    abstract fun dailyLimitDao(): DailyLimitDao
    abstract fun weeklyReportDao(): WeeklyReportDao
    abstract fun exportReportDao(): ExportReportDao

    companion object {
        private const val DB_NAME = "soc_agent.db"

        @Volatile
        private var instance: AppDatabase? = null

        /** Thread-safe singleton accessor. */
        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
    }
}