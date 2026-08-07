package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

/**
 * Total device screen time (all apps combined) stored per time bucket.
 * Primary key: bucketStart.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\bH\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\bH\u00d6\u0001J\t\u0010'\u001a\u00020\u0005H\u00d6\u0001R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\t\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0013\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0019\u00a8\u0006("}, d2 = {"Lcom/soc/agent/database/entity/ScreenTimeEntity;", "", "bucketStart", "", "granularity", "", "totalTimeMs", "appCount", "", "topApps", "updatedAt", "(JLjava/lang/String;JILjava/lang/String;J)V", "getAppCount", "()I", "setAppCount", "(I)V", "getBucketStart", "()J", "getGranularity", "()Ljava/lang/String;", "getTopApps", "setTopApps", "(Ljava/lang/String;)V", "getTotalTimeMs", "setTotalTimeMs", "(J)V", "getUpdatedAt", "setUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "screen_time", primaryKeys = {"bucket_start"}, indices = {@androidx.room.Index(value = {"bucket_start"})})
public final class ScreenTimeEntity {
    
    /**
     * Start of the bucket (epoch millis). For daily = midnight local, weekly = Monday 00:00, monthly = 1st 00:00.
     */
    @androidx.room.ColumnInfo(name = "bucket_start")
    private final long bucketStart = 0L;
    
    /**
     * Granularity: "daily", "weekly", or "monthly".
     */
    @androidx.room.ColumnInfo(name = "granularity")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String granularity = null;
    
    /**
     * Total device screen time in this bucket (milliseconds, sum of all apps).
     */
    @androidx.room.ColumnInfo(name = "total_time_ms")
    private long totalTimeMs;
    
    /**
     * Number of distinct apps used in this bucket.
     */
    @androidx.room.ColumnInfo(name = "app_count")
    private int appCount;
    
    /**
     * Comma-separated list of top-5 packages by usage time.
     */
    @androidx.room.ColumnInfo(name = "top_apps")
    @org.jetbrains.annotations.NotNull()
    private java.lang.String topApps;
    
    /**
     * Last sync timestamp.
     */
    @androidx.room.ColumnInfo(name = "updated_at")
    private long updatedAt;
    
    public ScreenTimeEntity(long bucketStart, @org.jetbrains.annotations.NotNull()
    java.lang.String granularity, long totalTimeMs, int appCount, @org.jetbrains.annotations.NotNull()
    java.lang.String topApps, long updatedAt) {
        super();
    }
    
    /**
     * Start of the bucket (epoch millis). For daily = midnight local, weekly = Monday 00:00, monthly = 1st 00:00.
     */
    public final long getBucketStart() {
        return 0L;
    }
    
    /**
     * Granularity: "daily", "weekly", or "monthly".
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGranularity() {
        return null;
    }
    
    /**
     * Total device screen time in this bucket (milliseconds, sum of all apps).
     */
    public final long getTotalTimeMs() {
        return 0L;
    }
    
    /**
     * Total device screen time in this bucket (milliseconds, sum of all apps).
     */
    public final void setTotalTimeMs(long p0) {
    }
    
    /**
     * Number of distinct apps used in this bucket.
     */
    public final int getAppCount() {
        return 0;
    }
    
    /**
     * Number of distinct apps used in this bucket.
     */
    public final void setAppCount(int p0) {
    }
    
    /**
     * Comma-separated list of top-5 packages by usage time.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTopApps() {
        return null;
    }
    
    /**
     * Comma-separated list of top-5 packages by usage time.
     */
    public final void setTopApps(@org.jetbrains.annotations.NotNull()
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
    
    public final long component3() {
        return 0L;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final long component6() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.ScreenTimeEntity copy(long bucketStart, @org.jetbrains.annotations.NotNull()
    java.lang.String granularity, long totalTimeMs, int appCount, @org.jetbrains.annotations.NotNull()
    java.lang.String topApps, long updatedAt) {
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