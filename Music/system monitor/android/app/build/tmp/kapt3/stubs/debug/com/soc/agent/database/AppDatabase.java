package com.soc.agent.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.soc.agent.database.dao.AlertDao;
import com.soc.agent.database.dao.AppDao;
import com.soc.agent.database.dao.DeviceDao;
import com.soc.agent.database.dao.LockedAppDao;
import com.soc.agent.database.dao.NetworkDao;
import com.soc.agent.database.dao.PolicyDao;
import com.soc.agent.database.dao.ScanDao;
import com.soc.agent.database.dao.TelemetryDao;
import com.soc.agent.database.dao.UnlockHistoryDao;
import com.soc.agent.database.dao.FailedAttemptDao;
import com.soc.agent.database.dao.IntruderSelfieDao;
import com.soc.agent.database.dao.SecuritySettingsDao;
import com.soc.agent.database.dao.BackupDao;
import com.soc.agent.database.dao.DailyUsageDao;
import com.soc.agent.database.dao.AppUsageDao;
import com.soc.agent.database.dao.UsageSessionDao;
import com.soc.agent.database.dao.WeeklyUsageDao;
import com.soc.agent.database.dao.MonthlyUsageDao;
import com.soc.agent.database.dao.ScreenTimeDao;
import com.soc.agent.database.dao.MostUsedAppsDao;
import com.soc.agent.database.dao.LaunchCountDao;
import com.soc.agent.database.dao.NotificationCountDao;
import com.soc.agent.database.dao.UsageTimelineDao;
import com.soc.agent.database.dao.FocusModeDao;
import com.soc.agent.database.dao.DailyLimitDao;
import com.soc.agent.database.dao.WeeklyReportDao;
import com.soc.agent.database.dao.ExportReportDao;
import com.soc.agent.database.entity.AlertEntity;
import com.soc.agent.database.entity.AppPermissionEntity;
import com.soc.agent.database.entity.BatteryStatusEntity;
import com.soc.agent.database.entity.CpuUsageEntity;
import com.soc.agent.database.entity.DeviceEntity;
import com.soc.agent.database.entity.DeviceHistoryEntity;
import com.soc.agent.database.entity.DeviceInfoEntity;
import com.soc.agent.database.entity.FileScanLogEntity;
import com.soc.agent.database.entity.InstalledAppEntity;
import com.soc.agent.database.entity.IocEntity;
import com.soc.agent.database.entity.LockedAppEntity;
import com.soc.agent.database.entity.MalwareScanLogEntity;
import com.soc.agent.database.entity.MemoryUsageEntity;
import com.soc.agent.database.entity.NetworkLogEntity;
import com.soc.agent.database.entity.PolicyEntity;
import com.soc.agent.database.entity.ScanRunEntity;
import com.soc.agent.database.entity.StorageUsageEntity;
import com.soc.agent.database.entity.ThreatEntity;
import com.soc.agent.database.entity.DailyUsageEntity;
import com.soc.agent.database.entity.AppUsageEntity;
import com.soc.agent.database.entity.UsageSessionEntity;
import com.soc.agent.database.entity.WeeklyUsageEntity;
import com.soc.agent.database.entity.MonthlyUsageEntity;
import com.soc.agent.database.entity.LaunchCountEntity;
import com.soc.agent.database.entity.NotificationCountEntity;
import com.soc.agent.database.entity.UsageTimelineEntity;
import com.soc.agent.database.entity.FocusModeEntity;
import com.soc.agent.database.entity.DailyLimitEntity;
import com.soc.agent.database.entity.WeeklyReportEntity;
import com.soc.agent.database.entity.ExportReportEntity;
import com.soc.agent.database.entity.ScreenTimeEntity;
import com.soc.agent.database.entity.UnlockHistoryEntity;
import com.soc.agent.database.entity.FailedAttemptEntity;
import com.soc.agent.database.entity.IntruderSelfieEntity;
import com.soc.agent.database.entity.SecuritySettingsEntity;
import com.soc.agent.database.entity.BackupEntity;

/**
 * The agent's local Room database. Holds the device record, telemetry
 * series, app inventory, scan results, alerts, network observations and the
 * server-enforced policy/IOC caches.
 *
 * Schema version 1; destructive migration is acceptable here because all
 * rows are re-synced from the server / re-collected from the device.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 92\u00020\u0001:\u00019B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020 H&J\b\u0010!\u001a\u00020\"H&J\b\u0010#\u001a\u00020$H&J\b\u0010%\u001a\u00020&H&J\b\u0010'\u001a\u00020(H&J\b\u0010)\u001a\u00020*H&J\b\u0010+\u001a\u00020,H&J\b\u0010-\u001a\u00020.H&J\b\u0010/\u001a\u000200H&J\b\u00101\u001a\u000202H&J\b\u00103\u001a\u000204H&J\b\u00105\u001a\u000206H&J\b\u00107\u001a\u000208H&\u00a8\u0006:"}, d2 = {"Lcom/soc/agent/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "alertDao", "Lcom/soc/agent/database/dao/AlertDao;", "appDao", "Lcom/soc/agent/database/dao/AppDao;", "appUsageDao", "Lcom/soc/agent/database/dao/AppUsageDao;", "backupDao", "Lcom/soc/agent/database/dao/BackupDao;", "dailyLimitDao", "Lcom/soc/agent/database/dao/DailyLimitDao;", "dailyUsageDao", "Lcom/soc/agent/database/dao/DailyUsageDao;", "deviceDao", "Lcom/soc/agent/database/dao/DeviceDao;", "exportReportDao", "Lcom/soc/agent/database/dao/ExportReportDao;", "failedAttemptDao", "Lcom/soc/agent/database/dao/FailedAttemptDao;", "focusModeDao", "Lcom/soc/agent/database/dao/FocusModeDao;", "intruderSelfieDao", "Lcom/soc/agent/database/dao/IntruderSelfieDao;", "launchCountDao", "Lcom/soc/agent/database/dao/LaunchCountDao;", "lockedAppDao", "Lcom/soc/agent/database/dao/LockedAppDao;", "monthlyUsageDao", "Lcom/soc/agent/database/dao/MonthlyUsageDao;", "mostUsedAppsDao", "Lcom/soc/agent/database/dao/MostUsedAppsDao;", "networkDao", "Lcom/soc/agent/database/dao/NetworkDao;", "notificationCountDao", "Lcom/soc/agent/database/dao/NotificationCountDao;", "policyDao", "Lcom/soc/agent/database/dao/PolicyDao;", "scanDao", "Lcom/soc/agent/database/dao/ScanDao;", "screenTimeDao", "Lcom/soc/agent/database/dao/ScreenTimeDao;", "securitySettingsDao", "Lcom/soc/agent/database/dao/SecuritySettingsDao;", "telemetryDao", "Lcom/soc/agent/database/dao/TelemetryDao;", "unlockHistoryDao", "Lcom/soc/agent/database/dao/UnlockHistoryDao;", "usageSessionDao", "Lcom/soc/agent/database/dao/UsageSessionDao;", "usageTimelineDao", "Lcom/soc/agent/database/dao/UsageTimelineDao;", "weeklyReportDao", "Lcom/soc/agent/database/dao/WeeklyReportDao;", "weeklyUsageDao", "Lcom/soc/agent/database/dao/WeeklyUsageDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.soc.agent.database.entity.DeviceEntity.class, com.soc.agent.database.entity.DeviceHistoryEntity.class, com.soc.agent.database.entity.CpuUsageEntity.class, com.soc.agent.database.entity.MemoryUsageEntity.class, com.soc.agent.database.entity.StorageUsageEntity.class, com.soc.agent.database.entity.BatteryStatusEntity.class, com.soc.agent.database.entity.DeviceInfoEntity.class, com.soc.agent.database.entity.InstalledAppEntity.class, com.soc.agent.database.entity.AppPermissionEntity.class, com.soc.agent.database.entity.FileScanLogEntity.class, com.soc.agent.database.entity.ScanRunEntity.class, com.soc.agent.database.entity.MalwareScanLogEntity.class, com.soc.agent.database.entity.ThreatEntity.class, com.soc.agent.database.entity.AlertEntity.class, com.soc.agent.database.entity.NetworkLogEntity.class, com.soc.agent.database.entity.IocEntity.class, com.soc.agent.database.entity.LockedAppEntity.class, com.soc.agent.database.entity.PolicyEntity.class, com.soc.agent.database.entity.UnlockHistoryEntity.class, com.soc.agent.database.entity.FailedAttemptEntity.class, com.soc.agent.database.entity.IntruderSelfieEntity.class, com.soc.agent.database.entity.SecuritySettingsEntity.class, com.soc.agent.database.entity.BackupEntity.class, com.soc.agent.database.entity.DailyUsageEntity.class, com.soc.agent.database.entity.AppUsageEntity.class, com.soc.agent.database.entity.UsageSessionEntity.class, com.soc.agent.database.entity.WeeklyUsageEntity.class, com.soc.agent.database.entity.MonthlyUsageEntity.class, com.soc.agent.database.entity.ScreenTimeEntity.class, com.soc.agent.database.entity.LaunchCountEntity.class, com.soc.agent.database.entity.NotificationCountEntity.class, com.soc.agent.database.entity.UsageTimelineEntity.class, com.soc.agent.database.entity.FocusModeEntity.class, com.soc.agent.database.entity.DailyLimitEntity.class, com.soc.agent.database.entity.WeeklyReportEntity.class, com.soc.agent.database.entity.ExportReportEntity.class}, version = 14, exportSchema = false)
@androidx.room.TypeConverters(value = {com.soc.agent.database.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DB_NAME = "soc_agent.db";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.database.AppDatabase instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.DeviceDao deviceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.TelemetryDao telemetryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.AppDao appDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.ScanDao scanDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.AlertDao alertDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.NetworkDao networkDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.PolicyDao policyDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.LockedAppDao lockedAppDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.UnlockHistoryDao unlockHistoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.FailedAttemptDao failedAttemptDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.IntruderSelfieDao intruderSelfieDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.SecuritySettingsDao securitySettingsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.BackupDao backupDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.DailyUsageDao dailyUsageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.AppUsageDao appUsageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.UsageSessionDao usageSessionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.WeeklyUsageDao weeklyUsageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.MonthlyUsageDao monthlyUsageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.ScreenTimeDao screenTimeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.MostUsedAppsDao mostUsedAppsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.LaunchCountDao launchCountDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.NotificationCountDao notificationCountDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.UsageTimelineDao usageTimelineDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.FocusModeDao focusModeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.DailyLimitDao dailyLimitDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.WeeklyReportDao weeklyReportDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.ExportReportDao exportReportDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/soc/agent/database/AppDatabase$Companion;", "", "()V", "DB_NAME", "", "instance", "Lcom/soc/agent/database/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Thread-safe singleton accessor.
         */
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.database.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}