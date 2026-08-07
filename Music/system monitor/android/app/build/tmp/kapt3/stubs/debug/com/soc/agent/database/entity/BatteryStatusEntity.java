package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * One battery status sample. Mirrors the `battery_status` table on the SOC server.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001f\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0007H\u00c6\u0003J\t\u0010!\u001a\u00020\tH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\fH\u00c6\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ`\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020\tH\u00d6\u0001J\t\u0010*\u001a\u00020\fH\u00d6\u0001R\u0016\u0010\n\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012\u00a8\u0006+"}, d2 = {"Lcom/soc/agent/database/entity/BatteryStatusEntity;", "", "id", "", "deviceId", "timestampMillis", "hasBattery", "", "percent", "", "charging", "status", "", "timeRemaining", "(JJJZIZLjava/lang/String;Ljava/lang/Long;)V", "getCharging", "()Z", "getDeviceId", "()J", "getHasBattery", "getId", "getPercent", "()I", "getStatus", "()Ljava/lang/String;", "getTimeRemaining", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTimestampMillis", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(JJJZIZLjava/lang/String;Ljava/lang/Long;)Lcom/soc/agent/database/entity/BatteryStatusEntity;", "equals", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "battery_status", indices = {@androidx.room.Index(value = {"device_id", "timestamp_millis"})})
public final class BatteryStatusEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "device_id")
    private final long deviceId = 0L;
    @androidx.room.ColumnInfo(name = "timestamp_millis")
    private final long timestampMillis = 0L;
    
    /**
     * False on devices without a battery (e.g. emulators, set-top boxes).
     */
    @androidx.room.ColumnInfo(name = "has_battery")
    private final boolean hasBattery = false;
    
    /**
     * Charge level 0..100.
     */
    @androidx.room.ColumnInfo(name = "percent")
    private final int percent = 0;
    @androidx.room.ColumnInfo(name = "charging")
    private final boolean charging = false;
    
    /**
     * charging / discharging / full / not_charging / unknown.
     */
    @androidx.room.ColumnInfo(name = "status")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    
    /**
     * Estimated seconds remaining, if reported by the platform.
     */
    @androidx.room.ColumnInfo(name = "time_remaining")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long timeRemaining = null;
    
    public BatteryStatusEntity(long id, long deviceId, long timestampMillis, boolean hasBattery, int percent, boolean charging, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.Long timeRemaining) {
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
     * False on devices without a battery (e.g. emulators, set-top boxes).
     */
    public final boolean getHasBattery() {
        return false;
    }
    
    /**
     * Charge level 0..100.
     */
    public final int getPercent() {
        return 0;
    }
    
    public final boolean getCharging() {
        return false;
    }
    
    /**
     * charging / discharging / full / not_charging / unknown.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    /**
     * Estimated seconds remaining, if reported by the platform.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getTimeRemaining() {
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
    
    public final boolean component4() {
        return false;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.BatteryStatusEntity copy(long id, long deviceId, long timestampMillis, boolean hasBattery, int percent, boolean charging, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.Long timeRemaining) {
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