package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.MonthlyUsageEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J \u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u001e"}, d2 = {"Lcom/soc/agent/database/dao/MonthlyUsageDao;", "", "deleteOlderThan", "", "beforeMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "Lcom/soc/agent/database/entity/MonthlyUsageEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllForPackage", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMonths", "getForMonth", "monthStart", "getForPackageAndMonth", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMonthTotals", "Lcom/soc/agent/database/dao/MonthTotals;", "getRecent", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "entity", "(Lcom/soc/agent/database/entity/MonthlyUsageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "app_debug"})
@androidx.room.Dao()
public abstract interface MonthlyUsageDao {
    
    /**
     * Upsert (insert or replace).
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.MonthlyUsageEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * All rows for a given month start, ordered by total usage descending.
     */
    @androidx.room.Query(value = "SELECT * FROM monthly_usage WHERE month_start = :monthStart ORDER BY total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForMonth(long monthStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.MonthlyUsageEntity>> $completion);
    
    /**
     * Single row for a package within a month.
     */
    @androidx.room.Query(value = "SELECT * FROM monthly_usage WHERE package_name = :packageName AND month_start = :monthStart LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackageAndMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, long monthStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.MonthlyUsageEntity> $completion);
    
    /**
     * All months available (distinct month_start values), newest first.
     */
    @androidx.room.Query(value = "SELECT DISTINCT month_start FROM monthly_usage ORDER BY month_start DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllMonths(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    /**
     * All rows across months for a package, ordered by month descending.
     */
    @androidx.room.Query(value = "SELECT * FROM monthly_usage WHERE package_name = :packageName ORDER BY month_start DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.MonthlyUsageEntity>> $completion);
    
    /**
     * Recent rows across all packages/months.
     */
    @androidx.room.Query(value = "SELECT * FROM monthly_usage ORDER BY updated_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.MonthlyUsageEntity>> $completion);
    
    /**
     * Delete rows older than a given epoch millis.
     */
    @androidx.room.Query(value = "DELETE FROM monthly_usage WHERE month_start < :beforeMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total time and launch count for a month.
     */
    @androidx.room.Query(value = "SELECT COALESCE(SUM(total_time_ms), 0) AS totalTimeMs, COALESCE(SUM(launch_count), 0) AS totalLaunches FROM monthly_usage WHERE month_start = :monthStart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMonthTotals(long monthStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.dao.MonthTotals> $completion);
    
    /**
     * Count all monthly rows.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM monthly_usage")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Get all monthly usage entries, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM monthly_usage ORDER BY month_start DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.MonthlyUsageEntity>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}