package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.LockedAppEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001d\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"\u00a8\u0006#"}, d2 = {"Lcom/soc/agent/database/dao/LockedAppDao;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "countEnabled", "", "deleteAll", "getAll", "", "Lcom/soc/agent/database/entity/LockedAppEntity;", "getAllLockedApps", "getEnabledPackages", "", "getLockedAppsSorted", "insertAll", "apps", "", "([Lcom/soc/agent/database/entity/LockedAppEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertLockedApp", "", "app", "(Lcom/soc/agent/database/entity/LockedAppEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertLockedApps", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLocked", "packageName", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeLockedApp", "removeLockedApps", "packageNames", "setEnabled", "enabled", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface LockedAppDao {
    
    /**
     * Add an app to the lock list, replacing any existing row for the package.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertLockedApp(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.LockedAppEntity app, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Add several locked apps in one transaction (bulk from the picker).
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertLockedApps(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.LockedAppEntity> apps, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Remove one locked app by package name.
     */
    @androidx.room.Query(value = "DELETE FROM locked_apps WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeLockedApp(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Remove many locked apps at once (bulk unlock / app uninstalled).
     */
    @androidx.room.Query(value = "DELETE FROM locked_apps WHERE package_name IN (:packageNames)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeLockedApps(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> packageNames, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Wipe the entire lock list (disable App Lock / reset).
     */
    @androidx.room.Query(value = "DELETE FROM locked_apps")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * All locked apps, most recently added first.
     */
    @androidx.room.Query(value = "SELECT * FROM locked_apps ORDER BY added_at DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllLockedApps(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.LockedAppEntity>> $completion);
    
    /**
     * Locked apps ordered by name for stable list rendering.
     */
    @androidx.room.Query(value = "SELECT * FROM locked_apps ORDER BY name COLLATE NOCASE ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLockedAppsSorted(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.LockedAppEntity>> $completion);
    
    /**
     * True when the given package is enabled in the lock set.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM locked_apps WHERE package_name = :packageName AND enabled = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isLocked(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Number of enabled locks (for the dashboard stat).
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM locked_apps WHERE enabled = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Every enabled locked package name (watcher hot-set).
     */
    @androidx.room.Query(value = "SELECT package_name FROM locked_apps WHERE enabled = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEnabledPackages(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    /**
     * Set the enabled flag for a specific app.
     */
    @androidx.room.Query(value = "UPDATE locked_apps SET enabled = :enabled WHERE package_name = :packageName")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setEnabled(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Get all locked apps (for backup).
     */
    @androidx.room.Query(value = "SELECT * FROM locked_apps")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.LockedAppEntity>> $completion);
    
    /**
     * Insert multiple apps (for restore).
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.LockedAppEntity[] apps, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Delete all locked apps (for restore).
     */
    @androidx.room.Query(value = "DELETE FROM locked_apps")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}