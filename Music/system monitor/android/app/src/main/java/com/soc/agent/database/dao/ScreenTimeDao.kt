package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.ScreenTimeEntity

@Dao
interface ScreenTimeDao {

    /** Upsert (insert or replace). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ScreenTimeEntity)

    /** All buckets for a given granularity, ordered newest first. */
    @Query("SELECT * FROM screen_time WHERE granularity = :granularity ORDER BY bucket_start DESC")
    suspend fun getAllByGranularity(granularity: String): List<ScreenTimeEntity>

    /** Single bucket by start time. */
    @Query("SELECT * FROM screen_time WHERE bucket_start = :bucketStart LIMIT 1")
    suspend fun getForBucket(bucketStart: Long): ScreenTimeEntity?

    /** Recent buckets across all granularities. */
    @Query("SELECT * FROM screen_time ORDER BY updated_at DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 100): List<ScreenTimeEntity>

    /** Total screen time for a granularity range. */
    @Query("SELECT COALESCE(SUM(total_time_ms), 0) FROM screen_time WHERE granularity = :granularity AND bucket_start BETWEEN :fromMs AND :toMs")
    suspend fun getTotalInRange(granularity: String, fromMs: Long, toMs: Long): Long

    /** Delete rows older than a given epoch millis. */
    @Query("DELETE FROM screen_time WHERE bucket_start < :beforeMs")
    suspend fun deleteOlderThan(beforeMs: Long)

    /** Count all screen_time rows. */
    @Query("SELECT COUNT(*) FROM screen_time")
    suspend fun totalCount(): Int
}