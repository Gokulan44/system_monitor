package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Monthly aggregate of app usage per package.
 * Primary key: (packageName, monthStart).
 * monthStart = Monday 00:00 of the month's first Monday.
 * For simplicity, monthStart is the epoch millis of the 1st of the month at 00:00 local.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\rJ\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0006H\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\tH\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0006H\u00c6\u0003JY\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020\tH\u00d6\u0001J\t\u0010.\u001a\u00020\u0003H\u00d6\u0001R\u001e\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000fR\u001e\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001a\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\f\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001e\u00a8\u0006/"}, d2 = {"Lcom/soc/agent/database/entity/MonthlyUsageEntity;", "", "packageName", "", "appName", "monthStart", "", "totalTimeMs", "launchCount", "", "activeDays", "activeDates", "updatedAt", "(Ljava/lang/String;Ljava/lang/String;JJIILjava/lang/String;J)V", "getActiveDates", "()Ljava/lang/String;", "setActiveDates", "(Ljava/lang/String;)V", "getActiveDays", "()I", "setActiveDays", "(I)V", "getAppName", "getLaunchCount", "setLaunchCount", "getMonthStart", "()J", "getPackageName", "getTotalTimeMs", "setTotalTimeMs", "(J)V", "getUpdatedAt", "setUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "monthly_usage", primaryKeys = {"package_name", "month_start"}, indices = {@androidx.room.Index(value = {"month_start"}), @androidx.room.Index(value = {"package_name"})})
public final class MonthlyUsageEntity {
    
    /**
     * Package name of the app.
     */
    @androidx.room.ColumnInfo(name = "package_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    
    /**
     * App human-readable label.
     */
    @androidx.room.ColumnInfo(name = "app_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appName = null;
    
    /**
     * Start of the month (epoch millis, 1st of month 00:00 local).
     */
    @androidx.room.ColumnInfo(name = "month_start")
    private final long monthStart = 0L;
    
    /**
     * Total time app was in foreground during the month (milliseconds).
     */
    @androidx.room.ColumnInfo(name = "total_time_ms")
    private long totalTimeMs;
    
    /**
     * Number of times the app was launched/foregrounded during the month.
     */
    @androidx.room.ColumnInfo(name = "launch_count")
    private int launchCount;
    
    /**
     * Days in the month the app was used (count of distinct active days).
     */
    @androidx.room.ColumnInfo(name = "active_days")
    private int activeDays;
    
    /**
     * List of dates (YYYYMMDD) the app was used within the month (CSV).
     */
    @androidx.room.ColumnInfo(name = "active_dates")
    @org.jetbrains.annotations.NotNull()
    private java.lang.String activeDates;
    
    /**
     * Last sync timestamp.
     */
    @androidx.room.ColumnInfo(name = "updated_at")
    private long updatedAt;
    
    public MonthlyUsageEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long monthStart, long totalTimeMs, int launchCount, int activeDays, @org.jetbrains.annotations.NotNull()
    java.lang.String activeDates, long updatedAt) {
        super();
    }
    
    /**
     * Package name of the app.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackageName() {
        return null;
    }
    
    /**
     * App human-readable label.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppName() {
        return null;
    }
    
    /**
     * Start of the month (epoch millis, 1st of month 00:00 local).
     */
    public final long getMonthStart() {
        return 0L;
    }
    
    /**
     * Total time app was in foreground during the month (milliseconds).
     */
    public final long getTotalTimeMs() {
        return 0L;
    }
    
    /**
     * Total time app was in foreground during the month (milliseconds).
     */
    public final void setTotalTimeMs(long p0) {
    }
    
    /**
     * Number of times the app was launched/foregrounded during the month.
     */
    public final int getLaunchCount() {
        return 0;
    }
    
    /**
     * Number of times the app was launched/foregrounded during the month.
     */
    public final void setLaunchCount(int p0) {
    }
    
    /**
     * Days in the month the app was used (count of distinct active days).
     */
    public final int getActiveDays() {
        return 0;
    }
    
    /**
     * Days in the month the app was used (count of distinct active days).
     */
    public final void setActiveDays(int p0) {
    }
    
    /**
     * List of dates (YYYYMMDD) the app was used within the month (CSV).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActiveDates() {
        return null;
    }
    
    /**
     * List of dates (YYYYMMDD) the app was used within the month (CSV).
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.MonthlyUsageEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long monthStart, long totalTimeMs, int launchCount, int activeDays, @org.jetbrains.annotations.NotNull()
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