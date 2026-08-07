package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.ExportReportEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0011\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/database/dao/ExportReportDao;", "", "deleteById", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "Lcom/soc/agent/database/entity/ExportReportEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByFormat", "format", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "getByScope", "scope", "getLatest", "insert", "entity", "(Lcom/soc/agent/database/entity/ExportReportEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "", "app_debug"})
@androidx.room.Dao()
public abstract interface ExportReportDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.ExportReportEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Get an export by id.
     */
    @androidx.room.Query(value = "SELECT * FROM export_report WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.ExportReportEntity> $completion);
    
    /**
     * Get all exports, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM export_report ORDER BY exported_at DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ExportReportEntity>> $completion);
    
    /**
     * Get the most recent export.
     */
    @androidx.room.Query(value = "SELECT * FROM export_report ORDER BY exported_at DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatest(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.ExportReportEntity> $completion);
    
    /**
     * Get exports by format.
     */
    @androidx.room.Query(value = "SELECT * FROM export_report WHERE format = :format ORDER BY exported_at DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByFormat(@org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ExportReportEntity>> $completion);
    
    /**
     * Get exports by scope.
     */
    @androidx.room.Query(value = "SELECT * FROM export_report WHERE scope = :scope ORDER BY exported_at DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByScope(@org.jetbrains.annotations.NotNull()
    java.lang.String scope, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ExportReportEntity>> $completion);
    
    /**
     * Delete an export record by id.
     */
    @androidx.room.Query(value = "DELETE FROM export_report WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total row count.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM export_report")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}