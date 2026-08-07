package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Generated weekly usage report. One row per report generation.
 * Stores pre-computed summaries for quick retrieval.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b6\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00a1\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0016J\u0006\u0010-\u001a\u00020\u000bJ\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010$J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010$J\t\u00103\u001a\u00020\bH\u00c6\u0003J\t\u00104\u001a\u00020\bH\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\bH\u00c6\u0003J\t\u0010;\u001a\u00020\bH\u00c6\u0003J\t\u0010<\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\u00be\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010@J\u0013\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010D\u001a\u00020\bH\u00d6\u0001J\u0006\u0010E\u001a\u00020\u000bJ\t\u0010F\u001a\u00020\u000bH\u00d6\u0001J\u0018\u0010G\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030I0HJ\u0006\u0010J\u001a\u00020\u000bR\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0013\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\u0015\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0016\u0010\u0012\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u001a\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010%\u001a\u0004\b'\u0010$R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001a\u00a8\u0006K"}, d2 = {"Lcom/soc/agent/database/entity/WeeklyReportEntity;", "", "id", "", "weekStart", "weekEnd", "totalScreenTimeMs", "totalLaunches", "", "appsUsedCount", "topApps", "", "mostUsedApp", "mostUsedAppTimeMs", "peakDay", "peakDayTimeMs", "avgDailyTimeMs", "peakHour", "notificationsCount", "focusSessionsCount", "focusTimeMs", "generatedAt", "(JJJJIILjava/lang/String;Ljava/lang/String;JLjava/lang/Integer;JJLjava/lang/Integer;IIJJ)V", "getAppsUsedCount", "()I", "getAvgDailyTimeMs", "()J", "getFocusSessionsCount", "getFocusTimeMs", "getGeneratedAt", "getId", "getMostUsedApp", "()Ljava/lang/String;", "getMostUsedAppTimeMs", "getNotificationsCount", "getPeakDay", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPeakDayTimeMs", "getPeakHour", "getTopApps", "getTotalLaunches", "getTotalScreenTimeMs", "getWeekEnd", "getWeekStart", "avgDailyFormatted", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJJJIILjava/lang/String;Ljava/lang/String;JLjava/lang/Integer;JJLjava/lang/Integer;IIJJ)Lcom/soc/agent/database/entity/WeeklyReportEntity;", "equals", "", "other", "hashCode", "screenTimeFormatted", "toString", "topAppsList", "", "Lkotlin/Pair;", "weekLabel", "app_debug"})
@androidx.room.Entity(tableName = "weekly_report", indices = {@androidx.room.Index(value = {"week_start"}, unique = true), @androidx.room.Index(value = {"generated_at"})})
public final class WeeklyReportEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    /**
     * Week start epoch millis (Monday 00:00).
     */
    @androidx.room.ColumnInfo(name = "week_start")
    private final long weekStart = 0L;
    
    /**
     * Week end epoch millis (Sunday 23:59:59).
     */
    @androidx.room.ColumnInfo(name = "week_end")
    private final long weekEnd = 0L;
    
    /**
     * Total screen time for the week in ms.
     */
    @androidx.room.ColumnInfo(name = "total_screen_time_ms")
    private final long totalScreenTimeMs = 0L;
    
    /**
     * Total number of app launches.
     */
    @androidx.room.ColumnInfo(name = "total_launches")
    private final int totalLaunches = 0;
    
    /**
     * Number of distinct apps used.
     */
    @androidx.room.ColumnInfo(name = "apps_used_count")
    private final int appsUsedCount = 0;
    
    /**
     * Top 5 apps as "packageName:timeMs" CSV.
     */
    @androidx.room.ColumnInfo(name = "top_apps")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String topApps = null;
    
    /**
     * Most used app package name.
     */
    @androidx.room.ColumnInfo(name = "most_used_app")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String mostUsedApp = null;
    
    /**
     * Most used app time in ms.
     */
    @androidx.room.ColumnInfo(name = "most_used_app_time_ms")
    private final long mostUsedAppTimeMs = 0L;
    
    /**
     * Peak day (1=Mon..7=Sun).
     */
    @androidx.room.ColumnInfo(name = "peak_day")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer peakDay = null;
    
    /**
     * Peak day total time in ms.
     */
    @androidx.room.ColumnInfo(name = "peak_day_time_ms")
    private final long peakDayTimeMs = 0L;
    
    /**
     * Average daily screen time in ms.
     */
    @androidx.room.ColumnInfo(name = "avg_daily_time_ms")
    private final long avgDailyTimeMs = 0L;
    
    /**
     * Peak hour (0-23).
     */
    @androidx.room.ColumnInfo(name = "peak_hour")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer peakHour = null;
    
    /**
     * Number of notifications received.
     */
    @androidx.room.ColumnInfo(name = "notifications_count")
    private final int notificationsCount = 0;
    
    /**
     * Number of focus mode sessions.
     */
    @androidx.room.ColumnInfo(name = "focus_sessions_count")
    private final int focusSessionsCount = 0;
    
    /**
     * Total focus time in ms.
     */
    @androidx.room.ColumnInfo(name = "focus_time_ms")
    private final long focusTimeMs = 0L;
    
    /**
     * Generated timestamp.
     */
    @androidx.room.ColumnInfo(name = "generated_at")
    private final long generatedAt = 0L;
    
    public WeeklyReportEntity(long id, long weekStart, long weekEnd, long totalScreenTimeMs, int totalLaunches, int appsUsedCount, @org.jetbrains.annotations.NotNull()
    java.lang.String topApps, @org.jetbrains.annotations.Nullable()
    java.lang.String mostUsedApp, long mostUsedAppTimeMs, @org.jetbrains.annotations.Nullable()
    java.lang.Integer peakDay, long peakDayTimeMs, long avgDailyTimeMs, @org.jetbrains.annotations.Nullable()
    java.lang.Integer peakHour, int notificationsCount, int focusSessionsCount, long focusTimeMs, long generatedAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    /**
     * Week start epoch millis (Monday 00:00).
     */
    public final long getWeekStart() {
        return 0L;
    }
    
    /**
     * Week end epoch millis (Sunday 23:59:59).
     */
    public final long getWeekEnd() {
        return 0L;
    }
    
    /**
     * Total screen time for the week in ms.
     */
    public final long getTotalScreenTimeMs() {
        return 0L;
    }
    
    /**
     * Total number of app launches.
     */
    public final int getTotalLaunches() {
        return 0;
    }
    
    /**
     * Number of distinct apps used.
     */
    public final int getAppsUsedCount() {
        return 0;
    }
    
    /**
     * Top 5 apps as "packageName:timeMs" CSV.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTopApps() {
        return null;
    }
    
    /**
     * Most used app package name.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMostUsedApp() {
        return null;
    }
    
    /**
     * Most used app time in ms.
     */
    public final long getMostUsedAppTimeMs() {
        return 0L;
    }
    
    /**
     * Peak day (1=Mon..7=Sun).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPeakDay() {
        return null;
    }
    
    /**
     * Peak day total time in ms.
     */
    public final long getPeakDayTimeMs() {
        return 0L;
    }
    
    /**
     * Average daily screen time in ms.
     */
    public final long getAvgDailyTimeMs() {
        return 0L;
    }
    
    /**
     * Peak hour (0-23).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPeakHour() {
        return null;
    }
    
    /**
     * Number of notifications received.
     */
    public final int getNotificationsCount() {
        return 0;
    }
    
    /**
     * Number of focus mode sessions.
     */
    public final int getFocusSessionsCount() {
        return 0;
    }
    
    /**
     * Total focus time in ms.
     */
    public final long getFocusTimeMs() {
        return 0L;
    }
    
    /**
     * Generated timestamp.
     */
    public final long getGeneratedAt() {
        return 0L;
    }
    
    /**
     * Human-readable week label (e.g. "Jun 2 - Jun 8").
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String weekLabel() {
        return null;
    }
    
    /**
     * Get top apps as list of pairs (packageName, timeMs).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.Long>> topAppsList() {
        return null;
    }
    
    /**
     * Screen time in hours and minutes (e.g. "12h 30m").
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String screenTimeFormatted() {
        return null;
    }
    
    /**
     * Average daily time formatted.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String avgDailyFormatted() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component10() {
        return null;
    }
    
    public final long component11() {
        return 0L;
    }
    
    public final long component12() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component13() {
        return null;
    }
    
    public final int component14() {
        return 0;
    }
    
    public final int component15() {
        return 0;
    }
    
    public final long component16() {
        return 0L;
    }
    
    public final long component17() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.WeeklyReportEntity copy(long id, long weekStart, long weekEnd, long totalScreenTimeMs, int totalLaunches, int appsUsedCount, @org.jetbrains.annotations.NotNull()
    java.lang.String topApps, @org.jetbrains.annotations.Nullable()
    java.lang.String mostUsedApp, long mostUsedAppTimeMs, @org.jetbrains.annotations.Nullable()
    java.lang.Integer peakDay, long peakDayTimeMs, long avgDailyTimeMs, @org.jetbrains.annotations.Nullable()
    java.lang.Integer peakHour, int notificationsCount, int focusSessionsCount, long focusTimeMs, long generatedAt) {
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