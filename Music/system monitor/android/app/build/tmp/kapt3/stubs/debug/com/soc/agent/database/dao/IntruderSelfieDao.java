package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.IntruderSelfieEntity;
import kotlinx.coroutines.flow.Flow;

/**
 * DAO for intruder selfie records.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u001bH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u001e"}, d2 = {"Lcom/soc/agent/database/dao/IntruderSelfieDao;", "", "countForPackage", "", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOlderThan", "before", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "Lcom/soc/agent/database/entity/IntruderSelfieEntity;", "getRecentSelfies", "limit", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSelfiesForPackage", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "record", "(Lcom/soc/agent/database/entity/IntruderSelfieEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "records", "", "([Lcom/soc/agent/database/entity/IntruderSelfieEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "app_debug"})
@androidx.room.Dao()
public abstract interface IntruderSelfieDao {
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.IntruderSelfieEntity record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM intruder_selfies WHERE package_name = :packageName ORDER BY timestamp DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSelfiesForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM intruder_selfies ORDER BY timestamp DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecentSelfies(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM intruder_selfies WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM intruder_selfies WHERE timestamp < :before")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long before, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM intruder_selfies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Get all intruder selfies (for backup).
     */
    @androidx.room.Query(value = "SELECT * FROM intruder_selfies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity>> $completion);
    
    /**
     * Insert multiple records (for restore).
     */
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.IntruderSelfieEntity[] records, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Delete all records (for restore).
     */
    @androidx.room.Query(value = "DELETE FROM intruder_selfies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * DAO for intruder selfie records.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}