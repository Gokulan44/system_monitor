package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * One installed application, keyed by (deviceId, packageName).
 * Mirrors the `installed_apps` table on the SOC server (unique on
 * device_id + path, where path == package name).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0010J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001bJ\t\u0010%\u001a\u00020\nH\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003Jt\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\bH\u00d6\u0001J\t\u00100\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0016\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018\u00a8\u00061"}, d2 = {"Lcom/soc/agent/database/entity/InstalledAppEntity;", "", "deviceId", "", "packageName", "", "name", "pid", "", "cpuPct", "", "memB", "status", "risk", "signature", "firstSeen", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;DJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getCpuPct", "()D", "getDeviceId", "()J", "getFirstSeen", "getMemB", "getName", "()Ljava/lang/String;", "getPackageName", "getPid", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRisk", "getSignature", "getStatus", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;DJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lcom/soc/agent/database/entity/InstalledAppEntity;", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "installed_apps", primaryKeys = {"device_id", "package_name"}, indices = {@androidx.room.Index(value = {"device_id"}), @androidx.room.Index(value = {"risk"})})
public final class InstalledAppEntity {
    @androidx.room.ColumnInfo(name = "device_id")
    private final long deviceId = 0L;
    @androidx.room.ColumnInfo(name = "package_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    @androidx.room.ColumnInfo(name = "name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    
    /**
     * Running process id, if the app is currently running.
     */
    @androidx.room.ColumnInfo(name = "pid")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer pid = null;
    @androidx.room.ColumnInfo(name = "cpu_pct")
    private final double cpuPct = 0.0;
    @androidx.room.ColumnInfo(name = "mem_b")
    private final long memB = 0L;
    
    /**
     * installed / running / system / disabled.
     */
    @androidx.room.ColumnInfo(name = "status")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    
    /**
     * unknown / low / medium / high / critical.
     */
    @androidx.room.ColumnInfo(name = "risk")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String risk = null;
    
    /**
     * Signing certificate fingerprint, "n/a" if unavailable.
     */
    @androidx.room.ColumnInfo(name = "signature")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String signature = null;
    @androidx.room.ColumnInfo(name = "first_seen")
    private final long firstSeen = 0L;
    
    public InstalledAppEntity(long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.Integer pid, double cpuPct, long memB, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    java.lang.String risk, @org.jetbrains.annotations.NotNull()
    java.lang.String signature, long firstSeen) {
        super();
    }
    
    public final long getDeviceId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackageName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    /**
     * Running process id, if the app is currently running.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPid() {
        return null;
    }
    
    public final double getCpuPct() {
        return 0.0;
    }
    
    public final long getMemB() {
        return 0L;
    }
    
    /**
     * installed / running / system / disabled.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    /**
     * unknown / low / medium / high / critical.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRisk() {
        return null;
    }
    
    /**
     * Signing certificate fingerprint, "n/a" if unavailable.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSignature() {
        return null;
    }
    
    public final long getFirstSeen() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component10() {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final long component6() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.InstalledAppEntity copy(long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.Integer pid, double cpuPct, long memB, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    java.lang.String risk, @org.jetbrains.annotations.NotNull()
    java.lang.String signature, long firstSeen) {
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