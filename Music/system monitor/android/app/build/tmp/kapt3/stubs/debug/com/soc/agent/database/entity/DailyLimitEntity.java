package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * User-configured daily usage limit per app.
 * When an app exceeds its limit, the system can show a warning or block it.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\fH\u00c6\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010!\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u000e\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0003J\u000e\u0010%\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0003J\t\u0010&\u001a\u00020'H\u00d6\u0001J\u000e\u0010(\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003J\t\u0010)\u001a\u00020\u0005H\u00d6\u0001J\u000e\u0010*\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0003R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006+"}, d2 = {"Lcom/soc/agent/database/entity/DailyLimitEntity;", "", "id", "", "packageName", "", "appName", "limitMs", "warningThreshold", "", "exceededAction", "enabled", "", "(JLjava/lang/String;Ljava/lang/String;JDLjava/lang/String;Z)V", "getAppName", "()Ljava/lang/String;", "getEnabled", "()Z", "getExceededAction", "getId", "()J", "getLimitMs", "getPackageName", "getWarningThreshold", "()D", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hasExceededLimit", "currentMs", "hasReachedWarning", "hashCode", "", "remainingMs", "toString", "usageFraction", "app_debug"})
@androidx.room.Entity(tableName = "daily_limit", indices = {@androidx.room.Index(value = {"package_name"}, unique = true), @androidx.room.Index(value = {"enabled"})})
public final class DailyLimitEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    /**
     * Package name of the app.
     */
    @androidx.room.ColumnInfo(name = "package_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    
    /**
     * Human-readable app name.
     */
    @androidx.room.ColumnInfo(name = "app_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appName = null;
    
    /**
     * Daily limit in milliseconds. 0 = no limit.
     */
    @androidx.room.ColumnInfo(name = "limit_ms")
    private final long limitMs = 0L;
    
    /**
     * Warning threshold (0.0-1.0) — fraction of limit at which to show a warning. Default 0.8 (80%).
     */
    @androidx.room.ColumnInfo(name = "warning_threshold")
    private final double warningThreshold = 0.0;
    
    /**
     * Action when limit exceeded: "warning" (show overlay), "block" (prevent launch), or "notify" (notification only).
     */
    @androidx.room.ColumnInfo(name = "exceeded_action")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String exceededAction = null;
    
    /**
     * Whether this limit is enabled.
     */
    @androidx.room.ColumnInfo(name = "enabled")
    private final boolean enabled = false;
    
    public DailyLimitEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long limitMs, double warningThreshold, @org.jetbrains.annotations.NotNull()
    java.lang.String exceededAction, boolean enabled) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    /**
     * Package name of the app.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackageName() {
        return null;
    }
    
    /**
     * Human-readable app name.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppName() {
        return null;
    }
    
    /**
     * Daily limit in milliseconds. 0 = no limit.
     */
    public final long getLimitMs() {
        return 0L;
    }
    
    /**
     * Warning threshold (0.0-1.0) — fraction of limit at which to show a warning. Default 0.8 (80%).
     */
    public final double getWarningThreshold() {
        return 0.0;
    }
    
    /**
     * Action when limit exceeded: "warning" (show overlay), "block" (prevent launch), or "notify" (notification only).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExceededAction() {
        return null;
    }
    
    /**
     * Whether this limit is enabled.
     */
    public final boolean getEnabled() {
        return false;
    }
    
    /**
     * Check if the given usage duration has reached the warning threshold.
     */
    public final boolean hasReachedWarning(long currentMs) {
        return false;
    }
    
    /**
     * Check if the given usage duration has exceeded the limit.
     */
    public final boolean hasExceededLimit(long currentMs) {
        return false;
    }
    
    /**
     * Remaining time in ms (0 if over limit).
     */
    public final long remainingMs(long currentMs) {
        return 0L;
    }
    
    /**
     * Usage fraction (0.0-1.0+).
     */
    public final double usageFraction(long currentMs) {
        return 0.0;
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
    
    public final long component4() {
        return 0L;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.DailyLimitEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long limitMs, double warningThreshold, @org.jetbrains.annotations.NotNull()
    java.lang.String exceededAction, boolean enabled) {
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