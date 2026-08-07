package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.WeeklyUsageEntity;
import kotlinx.coroutines.flow.Flow;

/**
 * DAO for weekly app usage aggregates.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0012\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0014\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010$J\u001c\u0010%\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010'\u00a8\u0006("}, d2 = {"Lcom/soc/agent/database/dao/WeeklyUsageDao;", "", "deleteForPackage", "", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOlderThan", "beforeWeek", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllInRange", "", "Lcom/soc/agent/database/entity/WeeklyUsageEntity;", "fromWeek", "toWeek", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppCountForWeek", "weekStart", "getForPackage", "limit", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForPackageAndWeek", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForWeek", "getLatestWeek", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecent", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalTimeForWeek", "getWeekRange", "startWeek", "endWeek", "insert", "", "weeklyUsage", "(Lcom/soc/agent/database/entity/WeeklyUsageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "weeklyUsageList", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface WeeklyUsageDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.WeeklyUsageEntity weeklyUsage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.WeeklyUsageEntity> weeklyUsageList, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM weekly_usage WHERE package_name = :packageName AND week_start = :weekStart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackageAndWeek(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, long weekStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.WeeklyUsageEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM weekly_usage WHERE week_start = :weekStart ORDER BY total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForWeek(long weekStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM weekly_usage WHERE week_start BETWEEN :startWeek AND :endWeek ORDER BY week_start DESC, total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWeekRange(long startWeek, long endWeek, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM weekly_usage WHERE package_name = :packageName ORDER BY week_start DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM weekly_usage ORDER BY week_start DESC, total_time_ms DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(total_time_ms) FROM weekly_usage WHERE week_start = :weekStart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalTimeForWeek(long weekStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM weekly_usage WHERE week_start = :weekStart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAppCountForWeek(long weekStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT week_start FROM weekly_usage ORDER BY week_start DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestWeek(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "DELETE FROM weekly_usage WHERE week_start < :beforeWeek")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeWeek, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM weekly_usage WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Get all entries within a week start range (epoch millis).
     */
    @androidx.room.Query(value = "SELECT * FROM weekly_usage WHERE week_start >= :fromWeek AND week_start <= :toWeek ORDER BY week_start DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllInRange(long fromWeek, long toWeek, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyUsageEntity>> $completion);
    
    /**
     * DAO for weekly app usage aggregates.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}