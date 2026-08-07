package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.soc.agent.database.entity.BatteryStatusEntity;
import com.soc.agent.database.entity.CpuUsageEntity;
import com.soc.agent.database.entity.DeviceInfoEntity;
import com.soc.agent.database.entity.MemoryUsageEntity;
import com.soc.agent.database.entity.StorageUsageEntity;

/**
 * Read/write access to telemetry series (CPU, memory, storage, battery,
 * device info). History queries return newest-first; callers reverse for
 * chronological charting.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\f\u001a\u0004\u0018\u00010\rH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u001f\u00a8\u0006 "}, d2 = {"Lcom/soc/agent/database/dao/TelemetryDao;", "", "getCpuHistory", "", "Lcom/soc/agent/database/entity/CpuUsageEntity;", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestBattery", "Lcom/soc/agent/database/entity/BatteryStatusEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestCpu", "getLatestDeviceInfo", "Lcom/soc/agent/database/entity/DeviceInfoEntity;", "getLatestMemory", "Lcom/soc/agent/database/entity/MemoryUsageEntity;", "getLatestStorage", "Lcom/soc/agent/database/entity/StorageUsageEntity;", "getMemoryHistory", "insertBattery", "", "sample", "(Lcom/soc/agent/database/entity/BatteryStatusEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCpu", "(Lcom/soc/agent/database/entity/CpuUsageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertDeviceInfo", "(Lcom/soc/agent/database/entity/DeviceInfoEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertMemory", "(Lcom/soc/agent/database/entity/MemoryUsageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertStorage", "samples", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface TelemetryDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertCpu(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.CpuUsageEntity sample, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertMemory(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.MemoryUsageEntity sample, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertStorage(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.StorageUsageEntity> samples, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBattery(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.BatteryStatusEntity sample, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDeviceInfo(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.DeviceInfoEntity sample, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM cpu_usage ORDER BY timestamp_millis DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestCpu(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.CpuUsageEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM memory_usage ORDER BY timestamp_millis DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestMemory(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.MemoryUsageEntity> $completion);
    
    /**
     * Newest-first CPU history up to [limit] samples.
     */
    @androidx.room.Query(value = "SELECT * FROM cpu_usage ORDER BY timestamp_millis DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCpuHistory(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.CpuUsageEntity>> $completion);
    
    /**
     * Newest-first memory history up to [limit] samples.
     */
    @androidx.room.Query(value = "SELECT * FROM memory_usage ORDER BY timestamp_millis DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMemoryHistory(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.MemoryUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM battery_status ORDER BY timestamp_millis DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestBattery(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.BatteryStatusEntity> $completion);
    
    /**
     * All storage rows for the most recent sample time (one row per
     * mount point), ordered by mount.
     */
    @androidx.room.Query(value = "SELECT * FROM storage_usage WHERE timestamp_millis = (SELECT MAX(timestamp_millis) FROM storage_usage) ORDER BY mount ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestStorage(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.StorageUsageEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM device_info ORDER BY timestamp_millis DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestDeviceInfo(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.DeviceInfoEntity> $completion);
    
    /**
     * Read/write access to telemetry series (CPU, memory, storage, battery,
     * device info). History queries return newest-first; callers reverse for
     * chronological charting.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}