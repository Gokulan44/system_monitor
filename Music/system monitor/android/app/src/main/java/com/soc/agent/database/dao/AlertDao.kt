package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soc.agent.database.entity.AlertEntity

@Dao
interface AlertDao {

    @Insert
    suspend fun insertAlerts(alerts: List<AlertEntity>)

    /** Latest alerts, newest first (regardless of read state). */
    @Query("SELECT * FROM alerts ORDER BY created_at DESC LIMIT :limit")
    suspend fun getAllAlerts(limit: Int = 200): List<AlertEntity>

    /** Convenience alias used by the UI — all persisted alerts, newest first. */
    @Query("SELECT * FROM alerts ORDER BY created_at DESC")
    suspend fun getAlerts(): List<AlertEntity>

    /** Unread alerts, newest first. */
    @Query("SELECT * FROM alerts WHERE read = 0 ORDER BY created_at DESC LIMIT :limit")
    suspend fun getUnreadAlerts(limit: Int): List<AlertEntity>

    /** Count of unread alerts (for badges). */
    @Query("SELECT COUNT(*) FROM alerts WHERE read = 0")
    suspend fun countUnread(): Int

    @Query("UPDATE alerts SET read = 1 WHERE id = :id")
    suspend fun markRead(id: Long)

    /** Prune alerts older than [cutoffMillis] to bound local storage. */
    @Query("DELETE FROM alerts WHERE created_at < :cutoffMillis")
    suspend fun deleteOld(cutoffMillis: Long)
}