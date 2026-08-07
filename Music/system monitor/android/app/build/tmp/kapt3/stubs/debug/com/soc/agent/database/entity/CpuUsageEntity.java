package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * One CPU usage sample. Mirrors the `cpu_usage` table on the SOC server;
 * [perCore] and [usageHistory] are stored as JSON via [com.soc.agent.database.Converters].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\tH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010&\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u00c6\u0003Jx\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u00c6\u0001\u00a2\u0006\u0002\u0010)J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020\tH\u00d6\u0001J\t\u0010.\u001a\u00020/H\u00d6\u0001R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018\u00a8\u00060"}, d2 = {"Lcom/soc/agent/database/entity/CpuUsageEntity;", "", "id", "", "deviceId", "timestampMillis", "loadPct", "", "cores", "", "perCore", "", "speedGhz", "tempC", "usageHistory", "(JJJDILjava/util/List;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)V", "getCores", "()I", "getDeviceId", "()J", "getId", "getLoadPct", "()D", "getPerCore", "()Ljava/util/List;", "getSpeedGhz", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getTempC", "getTimestampMillis", "getUsageHistory", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJJDILjava/util/List;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)Lcom/soc/agent/database/entity/CpuUsageEntity;", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
@androidx.room.Entity(tableName = "cpu_usage", indices = {@androidx.room.Index(value = {"device_id", "timestamp_millis"})})
public final class CpuUsageEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "device_id")
    private final long deviceId = 0L;
    @androidx.room.ColumnInfo(name = "timestamp_millis")
    private final long timestampMillis = 0L;
    
    /**
     * Overall load percentage 0..100.
     */
    @androidx.room.ColumnInfo(name = "load_pct")
    private final double loadPct = 0.0;
    @androidx.room.ColumnInfo(name = "cores")
    private final int cores = 0;
    
    /**
     * Per-core load percentages (JSON-encoded).
     */
    @androidx.room.ColumnInfo(name = "per_core")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Double> perCore = null;
    
    /**
     * Current clock speed in GHz, if available.
     */
    @androidx.room.ColumnInfo(name = "speed_ghz")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double speedGhz = null;
    
    /**
     * CPU temperature in Celsius, if available.
     */
    @androidx.room.ColumnInfo(name = "temp_c")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double tempC = null;
    
    /**
     * Short rolling history of load samples (JSON-encoded).
     */
    @androidx.room.ColumnInfo(name = "usage_history")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Double> usageHistory = null;
    
    public CpuUsageEntity(long id, long deviceId, long timestampMillis, double loadPct, int cores, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> perCore, @org.jetbrains.annotations.Nullable()
    java.lang.Double speedGhz, @org.jetbrains.annotations.Nullable()
    java.lang.Double tempC, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> usageHistory) {
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
    
    /**
     * Overall load percentage 0..100.
     */
    public final double getLoadPct() {
        return 0.0;
    }
    
    public final int getCores() {
        return 0;
    }
    
    /**
     * Per-core load percentages (JSON-encoded).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> getPerCore() {
        return null;
    }
    
    /**
     * Current clock speed in GHz, if available.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getSpeedGhz() {
        return null;
    }
    
    /**
     * CPU temperature in Celsius, if available.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getTempC() {
        return null;
    }
    
    /**
     * Short rolling history of load samples (JSON-encoded).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> getUsageHistory() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.CpuUsageEntity copy(long id, long deviceId, long timestampMillis, double loadPct, int cores, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> perCore, @org.jetbrains.annotations.Nullable()
    java.lang.Double speedGhz, @org.jetbrains.annotations.Nullable()
    java.lang.Double tempC, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> usageHistory) {
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