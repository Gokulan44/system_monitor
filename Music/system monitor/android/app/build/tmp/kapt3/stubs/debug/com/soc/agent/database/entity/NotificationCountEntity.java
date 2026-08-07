package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Individual notification event. One row per notification posted.
 * Enables analytics: notification count, frequency, hourly patterns, noise ranking.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\n\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\nH\u00c6\u0003J\t\u0010!\u001a\u00020\nH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\nH\u00c6\u0003J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\nH\u00c6\u0003J\t\u0010)\u001a\u00020\nH\u00c6\u0003J{\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\nH\u00c6\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020\nH\u00d6\u0001J\t\u0010/\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0016\u0010\u000e\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\r\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0016\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u000f\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012\u00a8\u00060"}, d2 = {"Lcom/soc/agent/database/entity/NotificationCountEntity;", "", "id", "", "packageName", "", "appName", "title", "category", "priority", "", "postedAtMs", "hourOfDay", "dayOfWeek", "dateInt", "monthInt", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJIIII)V", "getAppName", "()Ljava/lang/String;", "getCategory", "getDateInt", "()I", "getDayOfWeek", "getHourOfDay", "getId", "()J", "getMonthInt", "getPackageName", "getPostedAtMs", "getPriority", "getTitle", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "notification_count", indices = {@androidx.room.Index(value = {"package_name"}), @androidx.room.Index(value = {"posted_at_ms"}), @androidx.room.Index(value = {"package_name", "posted_at_ms"}), @androidx.room.Index(value = {"date_int"})})
public final class NotificationCountEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    /**
     * Package name of the app that posted the notification.
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
     * Notification title (may be null for silent notifications).
     */
    @androidx.room.ColumnInfo(name = "title")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String title = null;
    
    /**
     * Notification category (e.g. "social", "email", "alarm", "progress").
     */
    @androidx.room.ColumnInfo(name = "category")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String category = null;
    
    /**
     * Notification priority (0=MIN..4=MAX).
     */
    @androidx.room.ColumnInfo(name = "priority")
    private final int priority = 0;
    
    /**
     * Epoch millis when the notification was posted.
     */
    @androidx.room.ColumnInfo(name = "posted_at_ms")
    private final long postedAtMs = 0L;
    
    /**
     * Hour of day (0-23) for hourly distribution queries.
     */
    @androidx.room.ColumnInfo(name = "hour_of_day")
    private final int hourOfDay = 0;
    
    /**
     * Day of week (1=Mon..7=Sun) for weekly pattern queries.
     */
    @androidx.room.ColumnInfo(name = "day_of_week")
    private final int dayOfWeek = 0;
    
    /**
     * Date as YYYYMMDD int for daily grouping.
     */
    @androidx.room.ColumnInfo(name = "date_int")
    private final int dateInt = 0;
    
    /**
     * Month as YYYYMM int for monthly grouping.
     */
    @androidx.room.ColumnInfo(name = "month_int")
    private final int monthInt = 0;
    
    public NotificationCountEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String category, int priority, long postedAtMs, int hourOfDay, int dayOfWeek, int dateInt, int monthInt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    /**
     * Package name of the app that posted the notification.
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
     * Notification title (may be null for silent notifications).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTitle() {
        return null;
    }
    
    /**
     * Notification category (e.g. "social", "email", "alarm", "progress").
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCategory() {
        return null;
    }
    
    /**
     * Notification priority (0=MIN..4=MAX).
     */
    public final int getPriority() {
        return 0;
    }
    
    /**
     * Epoch millis when the notification was posted.
     */
    public final long getPostedAtMs() {
        return 0L;
    }
    
    /**
     * Hour of day (0-23) for hourly distribution queries.
     */
    public final int getHourOfDay() {
        return 0;
    }
    
    /**
     * Day of week (1=Mon..7=Sun) for weekly pattern queries.
     */
    public final int getDayOfWeek() {
        return 0;
    }
    
    /**
     * Date as YYYYMMDD int for daily grouping.
     */
    public final int getDateInt() {
        return 0;
    }
    
    /**
     * Month as YYYYMM int for monthly grouping.
     */
    public final int getMonthInt() {
        return 0;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.NotificationCountEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String category, int priority, long postedAtMs, int hourOfDay, int dayOfWeek, int dateInt, int monthInt) {
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