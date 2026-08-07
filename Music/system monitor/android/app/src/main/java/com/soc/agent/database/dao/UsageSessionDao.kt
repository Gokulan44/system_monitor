package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.UsageSessionEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for individual app usage sessions.
 */
@Dao
interface UsageSessionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(session: UsageSessionEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(sessions: List<UsageSessionEntity>)

    @Query("SELECT * FROM usage_sessions WHERE id = :id")
    suspend fun getById(id: Long): UsageSessionEntity?

    @Query("SELECT * FROM usage_sessions WHERE package_name = :packageName ORDER BY start_time_ms DESC LIMIT :limit")
    suspend fun getForPackage(packageName: String, limit: Int = 50): List<UsageSessionEntity>

    @Query("SELECT * FROM usage_sessions WHERE active = 1")
    suspend fun getActiveSessions(): List<UsageSessionEntity>

    @Query("SELECT * FROM usage_sessions WHERE start_time_ms BETWEEN :start AND :end ORDER BY start_time_ms DESC")
    suspend fun getInTimeRange(start: Long, end: Long): List<UsageSessionEntity>

    @Query("SELECT * FROM usage_sessions WHERE date(start_time_ms / 1000, 'unixepoch') = date(:date / 1000, 'unixepoch') ORDER BY start_time_ms DESC")
    suspend fun getForDate(date: Long): List<UsageSessionEntity>

    @Query("UPDATE usage_sessions SET end_time_ms = :endTime, duration_ms = :endTime - start_time_ms, active = 0 WHERE id = :id")
    suspend fun endSession(id: Long, endTime: Long): Int

    @Query("DELETE FROM usage_sessions WHERE start_time_ms < :beforeTime")
    suspend fun deleteOlderThan(beforeTime: Long): Int

    @Query("SELECT COUNT(*) FROM usage_sessions WHERE active = 1")
    suspend fun getActiveCount(): Int

    @Query("SELECT SUM(duration_ms) FROM usage_sessions WHERE package_name = :packageName AND active = 0")
    suspend fun getTotalDurationForPackage(packageName: String): Long?
}