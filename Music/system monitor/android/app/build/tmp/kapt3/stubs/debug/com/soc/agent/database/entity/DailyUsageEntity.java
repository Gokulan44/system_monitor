package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Daily aggregate of app usage for a single package.
 * One row per package per day (keyed by package_name + date).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\bH\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\bH\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003Jc\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\bH\u00d6\u0001J\t\u00100\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001e\u0010\n\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0010R\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u001e\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016\u00a8\u00061"}, d2 = {"Lcom/soc/agent/database/entity/DailyUsageEntity;", "", "id", "", "packageName", "", "appName", "date", "", "totalTimeMs", "launchCount", "firstTimeMs", "lastTimeMs", "updatedAt", "(JLjava/lang/String;Ljava/lang/String;IJIJJJ)V", "getAppName", "()Ljava/lang/String;", "getDate", "()I", "getFirstTimeMs", "()J", "setFirstTimeMs", "(J)V", "getId", "getLastTimeMs", "setLastTimeMs", "getLaunchCount", "setLaunchCount", "(I)V", "getPackageName", "getTotalTimeMs", "setTotalTimeMs", "getUpdatedAt", "setUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "daily_usage", indices = {@androidx.room.Index(value = {"package_name", "date"}, unique = true), @androidx.room.Index(value = {"date"}), @androidx.room.Index(value = {"total_time_ms"})})
public final class DailyUsageEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "package_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    
    /**
     * Human-readable app label captured at time of recording.
     */
    @androidx.room.ColumnInfo(name = "app_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appName = null;
    
    /**
     * Date in YYYYMMDD format (e.g., 20240115).
     */
    @androidx.room.ColumnInfo(name = "date")
    private final int date = 0;
    
    /**
     * Total time app was in foreground (milliseconds).
     */
    @androidx.room.ColumnInfo(name = "total_time_ms")
    private long totalTimeMs;
    
    /**
     * Number of times the app was launched/foregrounded.
     */
    @androidx.room.ColumnInfo(name = "launch_count")
    private int launchCount;
    
    /**
     * First foreground timestamp of the day.
     */
    @androidx.room.ColumnInfo(name = "first_time_ms")
    private long firstTimeMs;
    
    /**
     * Last foreground timestamp of the day.
     */
    @androidx.room.ColumnInfo(name = "last_time_ms")
    private long lastTimeMs;
    
    /**
     * Last sync timestamp.
     */
    @androidx.room.ColumnInfo(name = "updated_at")
    private long updatedAt;
    
    public DailyUsageEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, int date, long totalTimeMs, int launchCount, long firstTimeMs, long lastTimeMs, long updatedAt) {
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
     * Human-readable app label captured at time of recording.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppName() {
        return null;
    }
    
    /**
     * Date in YYYYMMDD format (e.g., 20240115).
     */
    public final int getDate() {
        return 0;
    }
    
    /**
     * Total time app was in foreground (milliseconds).
     */
    public final long getTotalTimeMs() {
        return 0L;
    }
    
    /**
     * Total time app was in foreground (milliseconds).
     */
    public final void setTotalTimeMs(long p0) {
    }
    
    /**
     * Number of times the app was launched/foregrounded.
     */
    public final int getLaunchCount() {
        return 0;
    }
    
    /**
     * Number of times the app was launched/foregrounded.
     */
    public final void setLaunchCount(int p0) {
    }
    
    /**
     * First foreground timestamp of the day.
     */
    public final long getFirstTimeMs() {
        return 0L;
    }
    
    /**
     * First foreground timestamp of the day.
     */
    public final void setFirstTimeMs(long p0) {
    }
    
    /**
     * Last foreground timestamp of the day.
     */
    public final long getLastTimeMs() {
        return 0L;
    }
    
    /**
     * Last foreground timestamp of the day.
     */
    public final void setLastTimeMs(long p0) {
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
    
    public final int component4() {
        return 0;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.DailyUsageEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, int date, long totalTimeMs, int launchCount, long firstTimeMs, long lastTimeMs, long updatedAt) {
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