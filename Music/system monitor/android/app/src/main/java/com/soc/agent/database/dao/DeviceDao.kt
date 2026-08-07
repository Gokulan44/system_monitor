package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.soc.agent.database.entity.DeviceEntity
import com.soc.agent.database.entity.DeviceHistoryEntity

@Dao
interface DeviceDao {

    /** Upsert the agent's own device record (matched on the autogen pk). */
    @Upsert
    suspend fun upsertDevice(device: DeviceEntity)

    /** Look up a device by its stable agent UUID. */
    @Query("SELECT * FROM devices WHERE agent_id = :agentId LIMIT 1")
    suspend fun getDevice(agentId: String): DeviceEntity?

    /** Return every locally-known device, newest first. */
    @Query("SELECT * FROM devices ORDER BY id DESC")
    suspend fun getAllDevices(): List<DeviceEntity>

    /** Return the single most recent device record. */
    @Query("SELECT * FROM devices ORDER BY id DESC LIMIT 1")
    suspend fun getLatestDevice(): DeviceEntity?

    /** Insert a local device history event. */
    @Insert
    suspend fun insertHistory(event: DeviceHistoryEntity)

    /** Remove all locally cached device records. */
    @Query("DELETE FROM devices")
    suspend fun clear()
}