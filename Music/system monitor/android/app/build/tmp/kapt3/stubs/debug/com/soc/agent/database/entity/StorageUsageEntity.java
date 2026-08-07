package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * One storage volume sample (one row per mount point per sample time).
 * Mirrors the `storage_usage` table on the SOC server.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000eH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003Jm\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020,H\u00d6\u0001J\t\u0010-\u001a\u00020\u0007H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0016\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011\u00a8\u0006."}, d2 = {"Lcom/soc/agent/database/entity/StorageUsageEntity;", "", "id", "", "deviceId", "timestampMillis", "filesystem", "", "mount", "type", "totalB", "usedB", "freeB", "usagePct", "", "(JJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJD)V", "getDeviceId", "()J", "getFilesystem", "()Ljava/lang/String;", "getFreeB", "getId", "getMount", "getTimestampMillis", "getTotalB", "getType", "getUsagePct", "()D", "getUsedB", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
@androidx.room.Entity(tableName = "storage_usage", indices = {@androidx.room.Index(value = {"device_id", "timestamp_millis"})})
public final class StorageUsageEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "device_id")
    private final long deviceId = 0L;
    @androidx.room.ColumnInfo(name = "timestamp_millis")
    private final long timestampMillis = 0L;
    @androidx.room.ColumnInfo(name = "filesystem")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String filesystem = null;
    
    /**
     * Mount point, e.g. "/" or "/storage/emulated/0".
     */
    @androidx.room.ColumnInfo(name = "mount")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String mount = null;
    @androidx.room.ColumnInfo(name = "type")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String type = null;
    @androidx.room.ColumnInfo(name = "total_b")
    private final long totalB = 0L;
    @androidx.room.ColumnInfo(name = "used_b")
    private final long usedB = 0L;
    @androidx.room.ColumnInfo(name = "free_b")
    private final long freeB = 0L;
    
    /**
     * Usage percentage 0..100.
     */
    @androidx.room.ColumnInfo(name = "usage_pct")
    private final double usagePct = 0.0;
    
    public StorageUsageEntity(long id, long deviceId, long timestampMillis, @org.jetbrains.annotations.NotNull()
    java.lang.String filesystem, @org.jetbrains.annotations.NotNull()
    java.lang.String mount, @org.jetbrains.annotations.NotNull()
    java.lang.String type, long totalB, long usedB, long freeB, double usagePct) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getDeviceId() {
        return 0L;
    }
    
    public final long getTimestampMillis() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFilesystem() {
        return null;
    }
    
    /**
     * Mount point, e.g. "/" or "/storage/emulated/0".
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    public final long getTotalB() {
        return 0L;
    }
    
    public final long getUsedB() {
        return 0L;
    }
    
    public final long getFreeB() {
        return 0L;
    }
    
    /**
     * Usage percentage 0..100.
     */
    public final double getUsagePct() {
        return 0.0;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
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
    public final com.soc.agent.database.entity.StorageUsageEntity copy(long id, long deviceId, long timestampMillis, @org.jetbrains.annotations.NotNull()
    java.lang.String filesystem, @org.jetbrains.annotations.NotNull()
    java.lang.String mount, @org.jetbrains.annotations.NotNull()
    java.lang.String type, long totalB, long usedB, long freeB, double usagePct) {
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