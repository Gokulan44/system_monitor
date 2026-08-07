package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * POST /api/agent/telemetry payload. The device is identified by the
 * X-Agent-Id header, so no device id is sent in the body.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\fH\u00c6\u0003JI\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006%"}, d2 = {"Lcom/soc/agent/api/dto/TelemetryRequest;", "", "cpu", "Lcom/soc/agent/api/dto/CpuSample;", "memory", "Lcom/soc/agent/api/dto/MemorySample;", "storage", "", "Lcom/soc/agent/api/dto/StorageSample;", "battery", "Lcom/soc/agent/api/dto/BatterySample;", "info", "Lcom/soc/agent/api/dto/DeviceInfoSample;", "(Lcom/soc/agent/api/dto/CpuSample;Lcom/soc/agent/api/dto/MemorySample;Ljava/util/List;Lcom/soc/agent/api/dto/BatterySample;Lcom/soc/agent/api/dto/DeviceInfoSample;)V", "getBattery", "()Lcom/soc/agent/api/dto/BatterySample;", "getCpu", "()Lcom/soc/agent/api/dto/CpuSample;", "getInfo", "()Lcom/soc/agent/api/dto/DeviceInfoSample;", "getMemory", "()Lcom/soc/agent/api/dto/MemorySample;", "getStorage", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class TelemetryRequest {
    @org.jetbrains.annotations.Nullable()
    private final com.soc.agent.api.dto.CpuSample cpu = null;
    @org.jetbrains.annotations.Nullable()
    private final com.soc.agent.api.dto.MemorySample memory = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.soc.agent.api.dto.StorageSample> storage = null;
    @org.jetbrains.annotations.Nullable()
    private final com.soc.agent.api.dto.BatterySample battery = null;
    @org.jetbrains.annotations.Nullable()
    private final com.soc.agent.api.dto.DeviceInfoSample info = null;
    
    public TelemetryRequest(@org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.CpuSample cpu, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.MemorySample memory, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.StorageSample> storage, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.BatterySample battery, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.DeviceInfoSample info) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.CpuSample getCpu() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.MemorySample getMemory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.StorageSample> getStorage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.BatterySample getBattery() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.DeviceInfoSample getInfo() {
        return null;
    }
    
    public TelemetryRequest() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.CpuSample component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.MemorySample component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.StorageSample> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.BatterySample component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.DeviceInfoSample component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.TelemetryRequest copy(@org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.CpuSample cpu, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.MemorySample memory, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.StorageSample> storage, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.BatterySample battery, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.DeviceInfoSample info) {
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