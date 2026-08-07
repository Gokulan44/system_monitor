package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.UsageSessionEntity;
import kotlinx.coroutines.flow.Flow;

/**
 * DAO for individual app usage sessions.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0017J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001fJ\u001c\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00a7@\u00a2\u0006\u0002\u0010#\u00a8\u0006$"}, d2 = {"Lcom/soc/agent/database/dao/UsageSessionDao;", "", "deleteOlderThan", "", "beforeTime", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endSession", "id", "endTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveSessions", "", "Lcom/soc/agent/database/entity/UsageSessionEntity;", "getById", "getForDate", "date", "getForPackage", "packageName", "", "limit", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInTimeRange", "start", "end", "getTotalDurationForPackage", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "session", "(Lcom/soc/agent/database/entity/UsageSessionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "", "sessions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface UsageSessionDao {
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.UsageSessionEntity session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.UsageSessionEntity> sessions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM usage_sessions WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.UsageSessionEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM usage_sessions WHERE package_name = :packageName ORDER BY start_time_ms DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageSessionEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM usage_sessions WHERE active = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveSessions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageSessionEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM usage_sessions WHERE start_time_ms BETWEEN :start AND :end ORDER BY start_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getInTimeRange(long start, long end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageSessionEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM usage_sessions WHERE date(start_time_ms / 1000, 'unixepoch') = date(:date / 1000, 'unixepoch') ORDER BY start_time_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForDate(long date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageSessionEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE usage_sessions SET end_time_ms = :endTime, duration_ms = :endTime - start_time_ms, active = 0 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object endSession(long id, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM usage_sessions WHERE start_time_ms < :beforeTime")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM usage_sessions WHERE active = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(duration_ms) FROM usage_sessions WHERE package_name = :packageName AND active = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalDurationForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * DAO for individual app usage sessions.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}