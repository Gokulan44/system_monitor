package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The agent's own device record, keyed locally by the stable agent UUID
 * ([agentId]) and carrying the server-assigned [deviceId] once registered.
 * Mirrors the `devices` table on the SOC server.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0013J\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0010H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0005H\u00c6\u0003J\u009c\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020\u0010H\u00d6\u0001J\t\u0010;\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0016\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0016\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0015\u00a8\u0006<"}, d2 = {"Lcom/soc/agent/database/entity/DeviceEntity;", "", "id", "", "agentId", "", "deviceId", "name", "manufacturer", "model", "androidVersion", "appVersion", "platform", "status", "risk", "riskScore", "", "lastSeen", "registeredAt", "(JLjava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJJ)V", "getAgentId", "()Ljava/lang/String;", "getAndroidVersion", "getAppVersion", "getDeviceId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getId", "()J", "getLastSeen", "getManufacturer", "getModel", "getName", "getPlatform", "getRegisteredAt", "getRisk", "getRiskScore", "()I", "getStatus", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJJ)Lcom/soc/agent/database/entity/DeviceEntity;", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "devices", indices = {@androidx.room.Index(value = {"agent_id"}, unique = true)})
public final class DeviceEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    /**
     * Stable agent UUID, generated once and persisted. Unique.
     */
    @androidx.room.ColumnInfo(name = "agent_id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String agentId = null;
    
    /**
     * Server-assigned device id (from RegisterResponse.device_id).
     */
    @androidx.room.ColumnInfo(name = "device_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long deviceId = null;
    @androidx.room.ColumnInfo(name = "name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @androidx.room.ColumnInfo(name = "manufacturer")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String manufacturer = null;
    @androidx.room.ColumnInfo(name = "model")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String model = null;
    @androidx.room.ColumnInfo(name = "android_version")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String androidVersion = null;
    @androidx.room.ColumnInfo(name = "app_version")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appVersion = null;
    @androidx.room.ColumnInfo(name = "platform")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String platform = null;
    
    /**
     * online / offline / unknown.
     */
    @androidx.room.ColumnInfo(name = "status")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    
    /**
     * safe / medium / high / unknown, as reported by the server.
     */
    @androidx.room.ColumnInfo(name = "risk")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String risk = null;
    @androidx.room.ColumnInfo(name = "risk_score")
    private final int riskScore = 0;
    
    /**
     * Epoch millis of the last successful sync.
     */
    @androidx.room.ColumnInfo(name = "last_seen")
    private final long lastSeen = 0L;
    
    /**
     * Epoch millis of the first successful registration.
     */
    @androidx.room.ColumnInfo(name = "registered_at")
    private final long registeredAt = 0L;
    
    public DeviceEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String agentId, @org.jetbrains.annotations.Nullable()
    java.lang.Long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String manufacturer, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String androidVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String appVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String platform, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    java.lang.String risk, int riskScore, long lastSeen, long registeredAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    /**
     * Stable agent UUID, generated once and persisted. Unique.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAgentId() {
        return null;
    }
    
    /**
     * Server-assigned device id (from RegisterResponse.device_id).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getDeviceId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
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
    public final java.lang.String getAndroidVersion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppVersion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlatform() {
        return null;
    }
    
    /**
     * online / offline / unknown.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    /**
     * safe / medium / high / unknown, as reported by the server.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRisk() {
        return null;
    }
    
    public final int getRiskScore() {
        return 0;
    }
    
    /**
     * Epoch millis of the last successful sync.
     */
    public final long getLastSeen() {
        return 0L;
    }
    
    /**
     * Epoch millis of the first successful registration.
     */
    public final long getRegisteredAt() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final long component13() {
        return 0L;
    }
    
    public final long component14() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component3() {
        return null;
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
    public final com.soc.agent.database.entity.DeviceEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String agentId, @org.jetbrains.annotations.Nullable()
    java.lang.Long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String manufacturer, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String androidVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String appVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String platform, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    java.lang.String risk, int riskScore, long lastSeen, long registeredAt) {
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