package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * A Focus Mode session: user-defined distraction-free period.
 * When active, apps in the blocked set are restricted from foreground access.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050%J\t\u0010&\u001a\u00020'H\u00d6\u0001J\u000e\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u0005J\u0010\u0010*\u001a\u00020\n2\b\b\u0002\u0010+\u001a\u00020\u0003J\t\u0010,\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013\u00a8\u0006-"}, d2 = {"Lcom/soc/agent/database/entity/FocusModeEntity;", "", "id", "", "name", "", "blockedApps", "startTimeMs", "endTimeMs", "active", "", "schedule", "createdAtMs", "(JLjava/lang/String;Ljava/lang/String;JJZLjava/lang/String;J)V", "getActive", "()Z", "getBlockedApps", "()Ljava/lang/String;", "getCreatedAtMs", "()J", "getEndTimeMs", "getId", "getName", "getSchedule", "getStartTimeMs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "getBlockedPackageList", "", "hashCode", "", "isPackageBlocked", "packageName", "isRunning", "now", "toString", "app_debug"})
@androidx.room.Entity(tableName = "focus_mode", indices = {@androidx.room.Index(value = {"active"}), @androidx.room.Index(value = {"start_time_ms"}), @androidx.room.Index(value = {"end_time_ms"})})
public final class FocusModeEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    /**
     * Unique name for this focus mode (e.g. "Work", "Sleep", "Study").
     */
    @androidx.room.ColumnInfo(name = "name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    
    /**
     * Comma-separated list of blocked package names.
     */
    @androidx.room.ColumnInfo(name = "blocked_apps")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String blockedApps = null;
    
    /**
     * Epoch millis when the focus session starts.
     */
    @androidx.room.ColumnInfo(name = "start_time_ms")
    private final long startTimeMs = 0L;
    
    /**
     * Epoch millis when the focus session ends. 0 = manual end.
     */
    @androidx.room.ColumnInfo(name = "end_time_ms")
    private final long endTimeMs = 0L;
    
    /**
     * Whether this focus mode is currently active.
     */
    @androidx.room.ColumnInfo(name = "active")
    private final boolean active = false;
    
    /**
     * Optional: repeat schedule (e.g. "daily", "weekdays", "weekends", or "" for one-shot).
     */
    @androidx.room.ColumnInfo(name = "schedule")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String schedule = null;
    
    /**
     * Created timestamp.
     */
    @androidx.room.ColumnInfo(name = "created_at_ms")
    private final long createdAtMs = 0L;
    
    public FocusModeEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String blockedApps, long startTimeMs, long endTimeMs, boolean active, @org.jetbrains.annotations.NotNull()
    java.lang.String schedule, long createdAtMs) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    /**
     * Unique name for this focus mode (e.g. "Work", "Sleep", "Study").
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    /**
     * Comma-separated list of blocked package names.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBlockedApps() {
        return null;
    }
    
    /**
     * Epoch millis when the focus session starts.
     */
    public final long getStartTimeMs() {
        return 0L;
    }
    
    /**
     * Epoch millis when the focus session ends. 0 = manual end.
     */
    public final long getEndTimeMs() {
        return 0L;
    }
    
    /**
     * Whether this focus mode is currently active.
     */
    public final boolean getActive() {
        return false;
    }
    
    /**
     * Optional: repeat schedule (e.g. "daily", "weekdays", "weekends", or "" for one-shot).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSchedule() {
        return null;
    }
    
    /**
     * Created timestamp.
     */
    public final long getCreatedAtMs() {
        return 0L;
    }
    
    /**
     * Get the list of blocked package names.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getBlockedPackageList() {
        return null;
    }
    
    /**
     * Check if a package is blocked in this focus mode.
     */
    public final boolean isPackageBlocked(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        return false;
    }
    
    /**
     * Check if this focus mode is currently running (active + within time window).
     */
    public final boolean isRunning(long now) {
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
    
    public final long component4() {
        return 0L;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.FocusModeEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String blockedApps, long startTimeMs, long endTimeMs, boolean active, @org.jetbrains.annotations.NotNull()
    java.lang.String schedule, long createdAtMs) {
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