package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * One application protected by the App Lock module, keyed by package name.
 *
 * Rows are added from the "Add App Lock" picker and removed when the user
 * unlocks an app from the Locked Apps list. The [lockMethod] records which
 * gate was active when the app was locked ("pin" or "pattern") so the watcher
 * can present the matching unlock screen. [addedAt] drives the dashboard
 * "locked for" stat.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u0006!"}, d2 = {"Lcom/soc/agent/database/entity/LockedAppEntity;", "", "id", "", "packageName", "", "name", "lockMethod", "addedAt", "enabled", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JZ)V", "getAddedAt", "()J", "getEnabled", "()Z", "getId", "getLockMethod", "()Ljava/lang/String;", "getName", "getPackageName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
@androidx.room.Entity(tableName = "locked_apps", indices = {@androidx.room.Index(value = {"package_name"}, unique = true), @androidx.room.Index(value = {"lock_method"})})
public final class LockedAppEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "package_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    
    /**
     * Human-readable app label captured when the lock was added.
     */
    @androidx.room.ColumnInfo(name = "name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    
    /**
     * "pin" or "pattern" — the unlock gate presented when this app opens.
     */
    @androidx.room.ColumnInfo(name = "lock_method")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String lockMethod = null;
    
    /**
     * Timestamp (epoch millis) when the lock was applied.
     */
    @androidx.room.ColumnInfo(name = "added_at")
    private final long addedAt = 0L;
    
    /**
     * Hard-disable switch; disabled locks are ignored by the watcher.
     */
    @androidx.room.ColumnInfo(name = "enabled")
    private final boolean enabled = false;
    
    public LockedAppEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String lockMethod, long addedAt, boolean enabled) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackageName() {
        return null;
    }
    
    /**
     * Human-readable app label captured when the lock was added.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    /**
     * "pin" or "pattern" — the unlock gate presented when this app opens.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLockMethod() {
        return null;
    }
    
    /**
     * Timestamp (epoch millis) when the lock was applied.
     */
    public final long getAddedAt() {
        return 0L;
    }
    
    /**
     * Hard-disable switch; disabled locks are ignored by the watcher.
     */
    public final boolean getEnabled() {
        return false;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.LockedAppEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String lockMethod, long addedAt, boolean enabled) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}