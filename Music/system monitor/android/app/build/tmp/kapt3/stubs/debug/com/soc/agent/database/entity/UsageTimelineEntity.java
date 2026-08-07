package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Chronological app usage event. One row per foreground/background transition.
 * Enables timeline views: what was used, when, for how long, in what order.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\nH\u00c6\u0003J\t\u0010$\u001a\u00020\nH\u00c6\u0003J\t\u0010%\u001a\u00020\nH\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003Jo\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020\nH\u00d6\u0001J\t\u0010,\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/soc/agent/database/entity/UsageTimelineEntity;", "", "id", "", "packageName", "", "appName", "eventType", "eventTimeMs", "hourOfDay", "", "dayOfWeek", "dateInt", "durationMs", "previousApp", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JIIIJLjava/lang/String;)V", "getAppName", "()Ljava/lang/String;", "getDateInt", "()I", "getDayOfWeek", "getDurationMs", "()J", "getEventTimeMs", "getEventType", "getHourOfDay", "getId", "getPackageName", "getPreviousApp", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "usage_timeline", indices = {@androidx.room.Index(value = {"package_name"}), @androidx.room.Index(value = {"event_time_ms"}), @androidx.room.Index(value = {"date_int"}), @androidx.room.Index(value = {"package_name", "event_time_ms"})})
public final class UsageTimelineEntity {
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
     * Event type: "foreground" or "background".
     */
    @androidx.room.ColumnInfo(name = "event_type")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String eventType = null;
    
    /**
     * Epoch millis when the event occurred.
     */
    @androidx.room.ColumnInfo(name = "event_time_ms")
    private final long eventTimeMs = 0L;
    
    /**
     * Hour of day (0-23) for quick filtering.
     */
    @androidx.room.ColumnInfo(name = "hour_of_day")
    private final int hourOfDay = 0;
    
    /**
     * Day of week (1=Mon..7=Sun).
     */
    @androidx.room.ColumnInfo(name = "day_of_week")
    private final int dayOfWeek = 0;
    
    /**
     * Date as YYYYMMDD int.
     */
    @androidx.room.ColumnInfo(name = "date_int")
    private final int dateInt = 0;
    
    /**
     * Duration in ms (only meaningful for "background" events = session length). 0 for foreground events.
     */
    @androidx.room.ColumnInfo(name = "duration_ms")
    private final long durationMs = 0L;
    
    /**
     * Previous foreground app (the app that was active before this one, for context).
     */
    @androidx.room.ColumnInfo(name = "previous_app")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String previousApp = null;
    
    public UsageTimelineEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, @org.jetbrains.annotations.NotNull()
    java.lang.String eventType, long eventTimeMs, int hourOfDay, int dayOfWeek, int dateInt, long durationMs, @org.jetbrains.annotations.Nullable()
    java.lang.String previousApp) {
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
     * Event type: "foreground" or "background".
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEventType() {
        return null;
    }
    
    /**
     * Epoch millis when the event occurred.
     */
    public final long getEventTimeMs() {
        return 0L;
    }
    
    /**
     * Hour of day (0-23) for quick filtering.
     */
    public final int getHourOfDay() {
        return 0;
    }
    
    /**
     * Day of week (1=Mon..7=Sun).
     */
    public final int getDayOfWeek() {
        return 0;
    }
    
    /**
     * Date as YYYYMMDD int.
     */
    public final int getDateInt() {
        return 0;
    }
    
    /**
     * Duration in ms (only meaningful for "background" events = session length). 0 for foreground events.
     */
    public final long getDurationMs() {
        return 0L;
    }
    
    /**
     * Previous foreground app (the app that was active before this one, for context).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPreviousApp() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
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
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.UsageTimelineEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, @org.jetbrains.annotations.NotNull()
    java.lang.String eventType, long eventTimeMs, int hourOfDay, int dayOfWeek, int dateInt, long durationMs, @org.jetbrains.annotations.Nullable()
    java.lang.String previousApp) {
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