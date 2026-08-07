package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soc.agent.database.entity.NetworkLogEntity

@Dao
interface NetworkDao {

    /**
     * Persist network observations. [NetworkLogEntity] carries both
     * interface samples and phishing checks discriminated by
     * [NetworkLogEntity.kind].
     */
    @Insert
    suspend fun insertNetworkLogs(logs: List<NetworkLogEntity>)

    /** Latest interface samples, newest first. */
    @Query("SELECT * FROM network_logs WHERE kind = 'network' ORDER BY timestamp_millis DESC LIMIT :limit")
    suspend fun getLatestNetwork(limit: Int): List<NetworkLogEntity>

    /** Latest phishing URL checks, newest first. */
    @Query("SELECT * FROM network_logs WHERE kind = 'phishing' ORDER BY timestamp_millis DESC LIMIT :limit")
    suspend fun getLatestPhishing(limit: Int): List<NetworkLogEntity>

    /** Prune observations older than [cutoffMillis]. */
    @Query("DELETE FROM network_logs WHERE timestamp_millis < :cutoffMillis")
    suspend fun deleteOld(cutoffMillis: Long)
}