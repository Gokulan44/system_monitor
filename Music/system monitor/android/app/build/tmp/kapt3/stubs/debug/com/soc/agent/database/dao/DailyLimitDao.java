package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.soc.agent.database.entity.DailyLimitEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u0018\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u001a"}, d2 = {"Lcom/soc/agent/database/dao/DailyLimitDao;", "", "deleteById", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByPackage", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "Lcom/soc/agent/database/entity/DailyLimitEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBlockLimits", "getById", "getEnabled", "getForPackage", "insert", "entity", "(Lcom/soc/agent/database/entity/DailyLimitEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isBlockLimited", "", "totalCount", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface DailyLimitDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.DailyLimitEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.DailyLimitEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Get a limit by id.
     */
    @androidx.room.Query(value = "SELECT * FROM daily_limit WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.DailyLimitEntity> $completion);
    
    /**
     * Get the limit for a specific package.
     */
    @androidx.room.Query(value = "SELECT * FROM daily_limit WHERE package_name = :packageName LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.DailyLimitEntity> $completion);
    
    /**
     * Get all configured limits.
     */
    @androidx.room.Query(value = "SELECT * FROM daily_limit ORDER BY app_name ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyLimitEntity>> $completion);
    
    /**
     * Get all enabled limits.
     */
    @androidx.room.Query(value = "SELECT * FROM daily_limit WHERE enabled = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyLimitEntity>> $completion);
    
    /**
     * Get all limits where the package is in the blocked list.
     */
    @androidx.room.Query(value = "SELECT * FROM daily_limit WHERE enabled = 1 AND exceeded_action = 'block'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBlockLimits(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DailyLimitEntity>> $completion);
    
    /**
     * Check if a specific package has a block-type limit.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_limit WHERE enabled = 1 AND package_name = :packageName AND exceeded_action = 'block'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isBlockLimited(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Delete a limit by id.
     */
    @androidx.room.Query(value = "DELETE FROM daily_limit WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Delete a limit by package name.
     */
    @androidx.room.Query(value = "DELETE FROM daily_limit WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteByPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total row count.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}