package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.LaunchCountEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ,\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\u00020\n2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ&\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ$\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00112\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010#J\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u00112\u0006\u0010 \u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010%J&\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00150\u00112\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010'\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ.\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010'\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010)J\u000e\u0010*\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010+J\u000e\u0010,\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010+J\u0016\u0010-\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ,\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u00060"}, d2 = {"Lcom/soc/agent/database/dao/LaunchCountDao;", "", "activeDaysInRange", "", "packageName", "", "fromInt", "toInt", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOlderThan", "", "beforeMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstLaunchTime", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hourlyDistribution", "", "Lcom/soc/agent/database/dao/HourlyLaunch;", "insert", "entity", "Lcom/soc/agent/database/entity/LaunchCountEntity;", "(Lcom/soc/agent/database/entity/LaunchCountEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "entities", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lastLaunchTime", "launchDatesDesc", "toDateInt", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchesForPackageInRange", "launchesForPackageOnDay", "dateInt", "launchesPerPackageInRange", "Lcom/soc/agent/database/dao/PackageLaunchRank;", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchesPerPackageOnDay", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recentLaunches", "limit", "topHoursAllApps", "(IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalLaunches", "totalLaunchesForPackage", "weeklyDistribution", "Lcom/soc/agent/database/dao/DailyLaunch;", "app_debug"})
@androidx.room.Dao()
public abstract interface LaunchCountDao {
    
    /**
     * Insert a launch event.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.LaunchCountEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Bulk insert (for batch imports).
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.LaunchCountEntity> entities, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total launches for a package.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM launch_count WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalLaunchesForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Total launches across all apps.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM launch_count")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalLaunches(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Launch count for a package on a specific day (YYYYMMDD int).
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM launch_count WHERE package_name = :packageName AND date_int = :dateInt")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object launchesForPackageOnDay(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Launch count for a package in a date range (YYYYMMDD int, inclusive).
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM launch_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object launchesForPackageInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Launch count for all apps on a specific day, ordered by count descending.
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, COUNT(*) AS launchCount\n        FROM launch_count\n        WHERE date_int = :dateInt\n        GROUP BY package_name\n        ORDER BY launchCount DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object launchesPerPackageOnDay(int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.PackageLaunchRank>> $completion);
    
    /**
     * Launch count for all apps in a date range, ordered by count descending.
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, COUNT(*) AS launchCount\n        FROM launch_count\n        WHERE date_int BETWEEN :fromInt AND :toInt\n        GROUP BY package_name\n        ORDER BY launchCount DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object launchesPerPackageInRange(int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.PackageLaunchRank>> $completion);
    
    /**
     * Hourly distribution for a package (how many launches per hour of day).
     */
    @androidx.room.Query(value = "\n        SELECT hour_of_day AS hourOfDay, COUNT(*) AS launchCount\n        FROM launch_count\n        WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt\n        GROUP BY hour_of_day\n        ORDER BY hour_of_day ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object hourlyDistribution(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.HourlyLaunch>> $completion);
    
    /**
     * Day-of-week distribution for a package.
     */
    @androidx.room.Query(value = "\n        SELECT day_of_week AS dayOfWeek, COUNT(*) AS launchCount\n        FROM launch_count\n        WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt\n        GROUP BY day_of_week\n        ORDER BY day_of_week ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object weeklyDistribution(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.DailyLaunch>> $completion);
    
    /**
     * Most active hours across all apps in a date range.
     */
    @androidx.room.Query(value = "\n        SELECT hour_of_day AS hourOfDay, COUNT(*) AS launchCount\n        FROM launch_count\n        WHERE date_int BETWEEN :fromInt AND :toInt\n        GROUP BY hour_of_day\n        ORDER BY launchCount DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topHoursAllApps(int fromInt, int toInt, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.HourlyLaunch>> $completion);
    
    /**
     * Recent launches for a package (latest N).
     */
    @androidx.room.Query(value = "\n        SELECT * FROM launch_count\n        WHERE package_name = :packageName\n        ORDER BY launch_time_ms DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recentLaunches(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.LaunchCountEntity>> $completion);
    
    /**
     * Date of first launch for a package.
     */
    @androidx.room.Query(value = "SELECT MIN(launch_time_ms) FROM launch_count WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object firstLaunchTime(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Date of last launch for a package.
     */
    @androidx.room.Query(value = "SELECT MAX(launch_time_ms) FROM launch_count WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object lastLaunchTime(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Unique active days for a package in a date range.
     */
    @androidx.room.Query(value = "SELECT COUNT(DISTINCT date_int) FROM launch_count WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object activeDaysInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Launch streak: consecutive days with launches ending at [toDateInt].
     */
    @androidx.room.Query(value = "\n        SELECT DISTINCT date_int FROM launch_count\n        WHERE package_name = :packageName AND date_int <= :toDateInt\n        ORDER BY date_int DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object launchDatesDesc(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int toDateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Integer>> $completion);
    
    /**
     * Delete launches older than a given epoch millis.
     */
    @androidx.room.Query(value = "DELETE FROM launch_count WHERE launch_time_ms < :beforeMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total row count.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM launch_count")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}