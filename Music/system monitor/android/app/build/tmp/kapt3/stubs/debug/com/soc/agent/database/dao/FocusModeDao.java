package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.soc.agent.database.entity.FocusModeEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0014\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u001b\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0018\u00a8\u0006\u001d"}, d2 = {"Lcom/soc/agent/database/dao/FocusModeDao;", "", "deactivate", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteById", "getActive", "", "Lcom/soc/agent/database/entity/FocusModeEntity;", "getActiveModesBlockingPackage", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "getById", "getByName", "name", "getScheduled", "insert", "entity", "(Lcom/soc/agent/database/entity/FocusModeEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPackageBlocked", "", "totalCount", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface FocusModeDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.FocusModeEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.FocusModeEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Get a focus mode by id.
     */
    @androidx.room.Query(value = "SELECT * FROM focus_mode WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.FocusModeEntity> $completion);
    
    /**
     * Get all focus modes.
     */
    @androidx.room.Query(value = "SELECT * FROM focus_mode ORDER BY created_at_ms DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.FocusModeEntity>> $completion);
    
    /**
     * Get currently active focus modes.
     */
    @androidx.room.Query(value = "SELECT * FROM focus_mode WHERE active = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActive(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.FocusModeEntity>> $completion);
    
    /**
     * Get a specific focus mode by name.
     */
    @androidx.room.Query(value = "SELECT * FROM focus_mode WHERE name = :name LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.FocusModeEntity> $completion);
    
    /**
     * Check if a package is blocked by any active focus mode.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM focus_mode WHERE active = 1 AND blocked_apps LIKE '%' || :packageName || '%'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isPackageBlocked(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Get all active focus modes that block a specific package.
     */
    @androidx.room.Query(value = "SELECT * FROM focus_mode WHERE active = 1 AND blocked_apps LIKE '%' || :packageName || '%'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveModesBlockingPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.FocusModeEntity>> $completion);
    
    /**
     * Deactivate a focus mode by id.
     */
    @androidx.room.Query(value = "UPDATE focus_mode SET active = 0 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivate(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Deactivate all focus modes.
     */
    @androidx.room.Query(value = "UPDATE focus_mode SET active = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivateAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Get scheduled focus modes (non-empty schedule).
     */
    @androidx.room.Query(value = "SELECT * FROM focus_mode WHERE schedule != ''")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getScheduled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.FocusModeEntity>> $completion);
    
    /**
     * Delete a focus mode by id.
     */
    @androidx.room.Query(value = "DELETE FROM focus_mode WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total row count.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM focus_mode")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}