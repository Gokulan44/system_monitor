package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Weekly aggregate of app usage for a single package.
 * One row per package per ISO week (keyed by package_name + week_start).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\nH\u00c6\u0003J\t\u0010)\u001a\u00020\nH\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003Jc\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u00020\nH\u00d6\u0001J\t\u00101\u001a\u00020\u0005H\u00d6\u0001R\u001e\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0014\"\u0004\b\u001b\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001fR\u001e\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001fR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019\u00a8\u00062"}, d2 = {"Lcom/soc/agent/database/entity/WeeklyUsageEntity;", "", "id", "", "packageName", "", "appName", "weekStart", "totalTimeMs", "launchCount", "", "activeDays", "activeDates", "updatedAt", "(JLjava/lang/String;Ljava/lang/String;JJIILjava/lang/String;J)V", "getActiveDates", "()Ljava/lang/String;", "setActiveDates", "(Ljava/lang/String;)V", "getActiveDays", "()I", "setActiveDays", "(I)V", "getAppName", "getId", "()J", "getLaunchCount", "setLaunchCount", "getPackageName", "getTotalTimeMs", "setTotalTimeMs", "(J)V", "getUpdatedAt", "setUpdatedAt", "getWeekStart", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "weekly_usage", indices = {@androidx.room.Index(value = {"package_name", "week_start"}, unique = true), @androidx.room.Index(value = {"week_start"}), @androidx.room.Index(value = {"total_time_ms"})})
public final class WeeklyUsageEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "package_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    
    /**
     * Human-readable app label.
     */
    @androidx.room.ColumnInfo(name = "app_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appName = null;
    
    /**
     * Start of the week (Monday 00:00) in epoch millis.
     */
    @androidx.room.ColumnInfo(name = "week_start")
    private final long weekStart = 0L;
    
    /**
     * Total time app was in foreground during the week (milliseconds).
     */
    @androidx.room.ColumnInfo(name = "total_time_ms")
    private long totalTimeMs;
    
    /**
     * Number of times the app was launched/foregrounded during the week.
     */
    @androidx.room.ColumnInfo(name = "launch_count")
    private int launchCount;
    
    /**
     * Days in the week the app was used (count of distinct active days).
     */
    @androidx.room.ColumnInfo(name = "active_days")
    private int activeDays;
    
    /**
     * List of dates (YYYYMMDD) the app was used within the week (JSON/CSV).
     */
    @androidx.room.ColumnInfo(name = "active_dates")
    @org.jetbrains.annotations.NotNull()
    private java.lang.String activeDates;
    
    /**
     * Last sync timestamp.
     */
    @androidx.room.ColumnInfo(name = "updated_at")
    private long updatedAt;
    
    public WeeklyUsageEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long weekStart, long totalTimeMs, int launchCount, int activeDays, @org.jetbrains.annotations.NotNull()
    java.lang.String activeDates, long updatedAt) {
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
     * Human-readable app label.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppName() {
        return null;
    }
    
    /**
     * Start of the week (Monday 00:00) in epoch millis.
     */
    public final long getWeekStart() {
        return 0L;
    }
    
    /**
     * Total time app was in foreground during the week (milliseconds).
     */
    public final long getTotalTimeMs() {
        return 0L;
    }
    
    /**
     * Total time app was in foreground during the week (milliseconds).
     */
    public final void setTotalTimeMs(long p0) {
    }
    
    /**
     * Number of times the app was launched/foregrounded during the week.
     */
    public final int getLaunchCount() {
        return 0;
    }
    
    /**
     * Number of times the app was launched/foregrounded during the week.
     */
    public final void setLaunchCount(int p0) {
    }
    
    /**
     * Days in the week the app was used (count of distinct active days).
     */
    public final int getActiveDays() {
        return 0;
    }
    
    /**
     * Days in the week the app was used (count of distinct active days).
     */
    public final void setActiveDays(int p0) {
    }
    
    /**
     * List of dates (YYYYMMDD) the app was used within the week (JSON/CSV).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActiveDates() {
        return null;
    }
    
    /**
     * List of dates (YYYYMMDD) the app was used within the week (JSON/CSV).
     */
    public final void setActiveDates(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    /**
     * Last sync timestamp.
     */
    public final long getUpdatedAt() {
        return 0L;
    }
    
    /**
     * Last sync timestamp.
     */
    public final void setUpdatedAt(long p0) {
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
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.WeeklyUsageEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long weekStart, long totalTimeMs, int launchCount, int activeDays, @org.jetbrains.annotations.NotNull()
    java.lang.String activeDates, long updatedAt) {
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