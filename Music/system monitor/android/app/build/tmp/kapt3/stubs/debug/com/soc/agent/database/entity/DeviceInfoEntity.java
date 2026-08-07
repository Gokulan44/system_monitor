package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Snapshot of static device identity details. Mirrors the `device_info`
 * table on the SOC server.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bq\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\u0007H\u00c6\u0003J\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\t\u0010'\u001a\u00020\u0007H\u00c6\u0003Jw\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020-H\u00d6\u0001J\t\u0010.\u001a\u00020\u0007H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0016\u0010\r\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0016\u0010\n\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0016\u0010\u000b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0016\u0010\f\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011\u00a8\u0006/"}, d2 = {"Lcom/soc/agent/database/entity/DeviceInfoEntity;", "", "id", "", "deviceId", "timestampMillis", "manufacturer", "", "model", "kernel", "osName", "osVersion", "securityPatchLevel", "osBuild", "uptimeSec", "(JJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getDeviceId", "()J", "getId", "getKernel", "()Ljava/lang/String;", "getManufacturer", "getModel", "getOsBuild", "getOsName", "getOsVersion", "getSecurityPatchLevel", "getTimestampMillis", "getUptimeSec", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
@androidx.room.Entity(tableName = "device_info", indices = {@androidx.room.Index(value = {"device_id", "timestamp_millis"})})
public final class DeviceInfoEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "device_id")
    private final long deviceId = 0L;
    @androidx.room.ColumnInfo(name = "timestamp_millis")
    private final long timestampMillis = 0L;
    @androidx.room.ColumnInfo(name = "manufacturer")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String manufacturer = null;
    @androidx.room.ColumnInfo(name = "model")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String model = null;
    @androidx.room.ColumnInfo(name = "kernel")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String kernel = null;
    @androidx.room.ColumnInfo(name = "os_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String osName = null;
    @androidx.room.ColumnInfo(name = "os_version")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String osVersion = null;
    @androidx.room.ColumnInfo(name = "security_patch_level")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String securityPatchLevel = null;
    @androidx.room.ColumnInfo(name = "os_build")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String osBuild = null;
    
    /**
     * Uptime in seconds at sample time.
     */
    @androidx.room.ColumnInfo(name = "uptime_sec")
    private final long uptimeSec = 0L;
    
    public DeviceInfoEntity(long id, long deviceId, long timestampMillis, @org.jetbrains.annotations.NotNull()
    java.lang.String manufacturer, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String kernel, @org.jetbrains.annotations.NotNull()
    java.lang.String osName, @org.jetbrains.annotations.NotNull()
    java.lang.String osVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String securityPatchLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String osBuild, long uptimeSec) {
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
    public final java.lang.String getManufacturer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKernel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOsName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOsVersion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSecurityPatchLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOsBuild() {
        return null;
    }
    
    /**
     * Uptime in seconds at sample time.
     */
    public final long getUptimeSec() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    public final long component11() {
        return 0L;
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
    public final com.soc.agent.database.entity.DeviceInfoEntity copy(long id, long deviceId, long timestampMillis, @org.jetbrains.annotations.NotNull()
    java.lang.String manufacturer, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String kernel, @org.jetbrains.annotations.NotNull()
    java.lang.String osName, @org.jetbrains.annotations.NotNull()
    java.lang.String osVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String securityPatchLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String osBuild, long uptimeSec) {
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