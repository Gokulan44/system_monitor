package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soc.agent.database.entity.BatteryStatusEntity
import com.soc.agent.database.entity.CpuUsageEntity
import com.soc.agent.database.entity.DeviceInfoEntity
import com.soc.agent.database.entity.MemoryUsageEntity
import com.soc.agent.database.entity.StorageUsageEntity

/**
 * Read/write access to telemetry series (CPU, memory, storage, battery,
 * device info). History queries return newest-first; callers reverse for
 * chronological charting.
 */
@Dao
interface TelemetryDao {

    @Insert
    suspend fun insertCpu(sample: CpuUsageEntity)

    @Insert
    suspend fun insertMemory(sample: MemoryUsageEntity)

    @Insert
    suspend fun insertStorage(samples: List<StorageUsageEntity>)

    @Insert
    suspend fun insertBattery(sample: BatteryStatusEntity)

    @Insert
    suspend fun insertDeviceInfo(sample: DeviceInfoEntity)

    @Query("SELECT * FROM cpu_usage ORDER BY timestamp_millis DESC LIMIT 1")
    suspend fun getLatestCpu(): CpuUsageEntity?

    @Query("SELECT * FROM memory_usage ORDER BY timestamp_millis DESC LIMIT 1")
    suspend fun getLatestMemory(): MemoryUsageEntity?

    /** Newest-first CPU history up to [limit] samples. */
    @Query("SELECT * FROM cpu_usage ORDER BY timestamp_millis DESC LIMIT :limit")
    suspend fun getCpuHistory(limit: Int = 60): List<CpuUsageEntity>

    /** Newest-first memory history up to [limit] samples. */
    @Query("SELECT * FROM memory_usage ORDER BY timestamp_millis DESC LIMIT :limit")
    suspend fun getMemoryHistory(limit: Int = 60): List<MemoryUsageEntity>

    @Query("SELECT * FROM battery_status ORDER BY timestamp_millis DESC LIMIT 1")
    suspend fun getLatestBattery(): BatteryStatusEntity?

    /**
     * All storage rows for the most recent sample time (one row per
     * mount point), ordered by mount.
     */
    @Query("SELECT * FROM storage_usage WHERE timestamp_millis = (SELECT MAX(timestamp_millis) FROM storage_usage) ORDER BY mount ASC")
    suspend fun getLatestStorage(): List<StorageUsageEntity>

    @Query("SELECT * FROM device_info ORDER BY timestamp_millis DESC LIMIT 1")
    suspend fun getLatestDeviceInfo(): DeviceInfoEntity?
}