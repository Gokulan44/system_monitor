package com.soc.agent.data;

import com.soc.agent.api.dto.BatterySample;
import com.soc.agent.api.dto.CpuSample;
import com.soc.agent.api.dto.DeviceInfoSample;
import com.soc.agent.api.dto.MemorySample;
import com.soc.agent.api.dto.NetworkSampleDto;
import com.soc.agent.api.dto.PhishingSampleDto;
import com.soc.agent.api.dto.StorageSample;
import com.soc.agent.database.entity.BatteryStatusEntity;
import com.soc.agent.database.entity.CpuUsageEntity;
import com.soc.agent.database.entity.DeviceInfoEntity;
import com.soc.agent.database.entity.MemoryUsageEntity;
import com.soc.agent.database.entity.NetworkLogEntity;
import com.soc.agent.database.entity.StorageUsageEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001a\u0010\u0000\u001a\u00020\b*\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001a\u0010\u0000\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001a\u0010\u0000\u001a\u00020\f*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001a\u0010\u0000\u001a\u00020\f*\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0011"}, d2 = {"toEntity", "Lcom/soc/agent/database/entity/BatteryStatusEntity;", "Lcom/soc/agent/api/dto/BatterySample;", "deviceId", "", "ts", "Lcom/soc/agent/database/entity/CpuUsageEntity;", "Lcom/soc/agent/api/dto/CpuSample;", "Lcom/soc/agent/database/entity/DeviceInfoEntity;", "Lcom/soc/agent/api/dto/DeviceInfoSample;", "Lcom/soc/agent/database/entity/MemoryUsageEntity;", "Lcom/soc/agent/api/dto/MemorySample;", "Lcom/soc/agent/database/entity/NetworkLogEntity;", "Lcom/soc/agent/api/dto/NetworkSampleDto;", "Lcom/soc/agent/api/dto/PhishingSampleDto;", "Lcom/soc/agent/database/entity/StorageUsageEntity;", "Lcom/soc/agent/api/dto/StorageSample;", "app_debug"})
public final class SecuritySyncHelperKt {
    
    /**
     * Mapping extension functions translating DTOs into Room database entities.
     */
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.entity.CpuUsageEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.CpuSample $this$toEntity, long deviceId, long ts) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.entity.MemoryUsageEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.MemorySample $this$toEntity, long deviceId, long ts) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.entity.StorageUsageEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.StorageSample $this$toEntity, long deviceId, long ts) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.entity.BatteryStatusEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.BatterySample $this$toEntity, long deviceId, long ts) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.entity.DeviceInfoEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.DeviceInfoSample $this$toEntity, long deviceId, long ts) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.entity.NetworkLogEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.NetworkSampleDto $this$toEntity, long deviceId, long ts) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.entity.NetworkLogEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.PhishingSampleDto $this$toEntity, long deviceId, long ts) {
        return null;
    }
}