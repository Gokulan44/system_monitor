package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.NotificationCountEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ,\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ2\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0018J,\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160\n2\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010!J\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00160\n2\u0006\u0010#\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010$J&\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001d0\n2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010'J.\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001a0\n2\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00160\n2\b\b\u0002\u0010&\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010$J\u000e\u0010*\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u000e\u0010-\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010+J,\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u00060"}, d2 = {"Lcom/soc/agent/database/dao/NotificationCountDao;", "", "activeDaysInRange", "", "packageName", "", "fromInt", "toInt", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "byCategory", "", "Lcom/soc/agent/database/dao/CategoryNotification;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dailyTrend", "Lcom/soc/agent/database/dao/DailyNotificationCount;", "deleteOlderThan", "", "beforeMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstNotificationTime", "highPriorityByPackage", "Lcom/soc/agent/database/dao/PackageNotificationRank;", "minPriority", "(IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hourlyDistribution", "Lcom/soc/agent/database/dao/HourlyNotification;", "insert", "entity", "Lcom/soc/agent/database/entity/NotificationCountEntity;", "(Lcom/soc/agent/database/entity/NotificationCountEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lastNotificationTime", "notificationsPerPackageInRange", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "notificationsPerPackageOnDay", "dateInt", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recentForPackage", "limit", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "topHoursAllApps", "topPackages", "totalCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalForPackage", "totalNotifications", "weeklyDistribution", "Lcom/soc/agent/database/dao/DailyNotification;", "app_debug"})
@androidx.room.Dao()
public abstract interface NotificationCountDao {
    
    /**
     * Insert a notification event.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.NotificationCountEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total notifications across all apps.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM notification_count")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalNotifications(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Total notifications for a specific app.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM notification_count WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Total notifications for all apps on a specific day (YYYYMMDD int).
     */
    @androidx.room.Query(value = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE date_int = :dateInt GROUP BY package_name ORDER BY notificationCount DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object notificationsPerPackageOnDay(int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.PackageNotificationRank>> $completion);
    
    /**
     * Total notifications for all apps in a date range (YYYYMMDD int, inclusive).
     */
    @androidx.room.Query(value = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE date_int BETWEEN :fromInt AND :toInt GROUP BY package_name ORDER BY notificationCount DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object notificationsPerPackageInRange(int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.PackageNotificationRank>> $completion);
    
    /**
     * Top notification-spamming apps (all-time).
     */
    @androidx.room.Query(value = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count GROUP BY package_name ORDER BY notificationCount DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topPackages(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.PackageNotificationRank>> $completion);
    
    /**
     * Hourly distribution for a package in a date range.
     */
    @androidx.room.Query(value = "SELECT hour_of_day AS hourOfDay, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt GROUP BY hour_of_day ORDER BY hour_of_day ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object hourlyDistribution(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.HourlyNotification>> $completion);
    
    /**
     * Day-of-week distribution for a package in a date range.
     */
    @androidx.room.Query(value = "SELECT day_of_week AS dayOfWeek, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt GROUP BY day_of_week ORDER BY day_of_week ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object weeklyDistribution(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.DailyNotification>> $completion);
    
    /**
     * Most active notification hours across all apps.
     */
    @androidx.room.Query(value = "SELECT hour_of_day AS hourOfDay, COUNT(*) AS notificationCount FROM notification_count WHERE date_int BETWEEN :fromInt AND :toInt GROUP BY hour_of_day ORDER BY notificationCount DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topHoursAllApps(int fromInt, int toInt, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.HourlyNotification>> $completion);
    
    /**
     * Daily notification count for a package (trend data).
     */
    @androidx.room.Query(value = "SELECT date_int AS dateInt, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt GROUP BY date_int ORDER BY date_int ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object dailyTrend(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.DailyNotificationCount>> $completion);
    
    /**
     * Most recent notifications for a package.
     */
    @androidx.room.Query(value = "SELECT * FROM notification_count WHERE package_name = :packageName ORDER BY posted_at_ms DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recentForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.NotificationCountEntity>> $completion);
    
    /**
     * Notification count by category for a package.
     */
    @androidx.room.Query(value = "SELECT category, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = :packageName GROUP BY category ORDER BY notificationCount DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object byCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.CategoryNotification>> $completion);
    
    /**
     * High-priority notifications only.
     */
    @androidx.room.Query(value = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE priority >= :minPriority AND date_int BETWEEN :fromInt AND :toInt GROUP BY package_name ORDER BY notificationCount DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object highPriorityByPackage(int minPriority, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.PackageNotificationRank>> $completion);
    
    /**
     * Date of first notification for a package.
     */
    @androidx.room.Query(value = "SELECT MIN(posted_at_ms) FROM notification_count WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object firstNotificationTime(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Date of last notification for a package.
     */
    @androidx.room.Query(value = "SELECT MAX(posted_at_ms) FROM notification_count WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object lastNotificationTime(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Unique active days for a package in a date range.
     */
    @androidx.room.Query(value = "SELECT COUNT(DISTINCT date_int) FROM notification_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object activeDaysInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Delete notifications older than a given epoch millis.
     */
    @androidx.room.Query(value = "DELETE FROM notification_count WHERE posted_at_ms < :beforeMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total row count.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM notification_count")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}