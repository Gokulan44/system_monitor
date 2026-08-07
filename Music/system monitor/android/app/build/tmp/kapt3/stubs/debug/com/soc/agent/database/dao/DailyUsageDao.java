package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.soc.agent.database.entity.DailyUsageEntity;
import kotlinx.coroutines.flow.Flow;

/**
 * DAO for daily app usage aggregates.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0011\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0011\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ&\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0019J \u0010\u001a\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0011\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010!J\u001c\u0010\"\u001a\u00020\u001f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010$\u00a8\u0006%"}, d2 = {"Lcom/soc/agent/database/dao/DailyUsageDao;", "", "deleteForPackage", "", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOlderThan", "beforeDate", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllInRange", "", "Lcom/soc/agent/database/entity/DailyUsageEntity;", "fromDate", "toDate", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppCountForDate", "date", "getDateRange", "startDate", "endDate", "getForDate", "getForDateWithApps", "getForPackage", "limit", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForPackageAndDate", "getRecent", "getTotalTimeForDate", "", "insert", "", "dailyUsage", "(Lcom/soc/agent/database/entity/DailyUsageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "dailyUsageList", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface DailyUsageDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.DailyUsageEntity dailyUsage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.DailyUsageEntity> dailyUsageList, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_usage WHERE package_name = :packageName AND date = :date")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackageAndDate(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.DailyUsageEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_usage WHERE date = :date ORDER BY total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForDate(int date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_usage WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC, total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDateRange(int startDate, int endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_usage WHERE package_name = :packageName ORDER BY date DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_usage ORDER BY date DESC, total_time_ms DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(total_time_ms) FROM daily_usage WHERE date = :date")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalTimeForDate(int date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_usage WHERE date = :date")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAppCountForDate(int date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM daily_usage WHERE date < :beforeDate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(int beforeDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM daily_usage WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM daily_usage WHERE date = :date ORDER BY total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForDateWithApps(int date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyUsageEntity>> $completion);
    
    /**
     * Get all entries within a date range (epoch millis converted to date ints internally).
     */
    @androidx.room.Query(value = "SELECT * FROM daily_usage WHERE date >= :fromDate AND date <= :toDate ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllInRange(int fromDate, int toDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyUsageEntity>> $completion);
    
    /**
     * DAO for daily app usage aggregates.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}