package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.WeeklyReportEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/soc/agent/database/dao/WeeklyReportDao;", "", "deleteOlderThan", "", "beforeMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "Lcom/soc/agent/database/entity/WeeklyReportEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "getForWeek", "weekStart", "getInRange", "from", "to", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatest", "getRecent", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "entity", "(Lcom/soc/agent/database/entity/WeeklyReportEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "app_debug"})
@androidx.room.Dao()
public abstract interface WeeklyReportDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.WeeklyReportEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Get a report by week start.
     */
    @androidx.room.Query(value = "SELECT * FROM weekly_report WHERE week_start = :weekStart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForWeek(long weekStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.WeeklyReportEntity> $completion);
    
    /**
     * Get a report by id.
     */
    @androidx.room.Query(value = "SELECT * FROM weekly_report WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.WeeklyReportEntity> $completion);
    
    /**
     * Get all reports, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM weekly_report ORDER BY week_start DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyReportEntity>> $completion);
    
    /**
     * Get the most recent report.
     */
    @androidx.room.Query(value = "SELECT * FROM weekly_report ORDER BY week_start DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatest(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.WeeklyReportEntity> $completion);
    
    /**
     * Get reports in a date range.
     */
    @androidx.room.Query(value = "SELECT * FROM weekly_report WHERE week_start BETWEEN :from AND :to ORDER BY week_start DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getInRange(long from, long to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyReportEntity>> $completion);
    
    /**
     * Get last N reports.
     */
    @androidx.room.Query(value = "SELECT * FROM weekly_report ORDER BY week_start DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.WeeklyReportEntity>> $completion);
    
    /**
     * Delete reports older than a given epoch millis.
     */
    @androidx.room.Query(value = "DELETE FROM weekly_report WHERE week_start < :beforeMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total row count.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM weekly_report")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}