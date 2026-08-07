package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.AppUsageEntity;
import kotlinx.coroutines.flow.Flow;

/**
 * DAO for overall app usage statistics.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0010\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0017\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u001c\u0010\u001e\u001a\u00020\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010 \u00a8\u0006!"}, d2 = {"Lcom/soc/agent/database/dao/AppUsageDao;", "", "deleteForPackage", "", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUnusedSince", "beforeTime", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "Lcom/soc/agent/database/entity/AppUsageEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppsAboveTime", "minTime", "getForPackage", "getMostLaunched", "limit", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentlyUsed", "getTopApps", "getTotalAppCount", "getTotalLaunchesAllApps", "getTotalTimeAllApps", "insert", "", "appUsage", "(Lcom/soc/agent/database/entity/AppUsageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "appUsageList", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface AppUsageDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.AppUsageEntity appUsage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.AppUsageEntity> appUsageList, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.AppUsageEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage ORDER BY total_time_ms DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTopApps(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AppUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage ORDER BY last_used_ms DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecentlyUsed(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AppUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage ORDER BY launch_count DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMostLaunched(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AppUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_usage WHERE total_time_ms > :minTime ORDER BY total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAppsAboveTime(long minTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AppUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(total_time_ms) FROM app_usage")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalTimeAllApps(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(launch_count) FROM app_usage")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalLaunchesAllApps(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM app_usage")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalAppCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM app_usage WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM app_usage WHERE last_used_ms < :beforeTime")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteUnusedSince(long beforeTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Get all app usage entries, sorted by total time descending.
     */
    @androidx.room.Query(value = "SELECT * FROM app_usage ORDER BY total_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AppUsageEntity>> $completion);
    
    /**
     * DAO for overall app usage statistics.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}