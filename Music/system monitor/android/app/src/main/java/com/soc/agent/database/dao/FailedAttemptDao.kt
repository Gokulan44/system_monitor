package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.FailedAttemptEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for failed unlock attempt records.
 */
@Dao
interface FailedAttemptDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: FailedAttemptEntity)

    @Query("SELECT * FROM failed_attempts WHERE package_name = :packageName ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getFailedForPackage(packageName: String, limit: Int = 50): List<FailedAttemptEntity>

    @Query("SELECT * FROM failed_attempts ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentFailed(limit: Int = 100): List<FailedAttemptEntity>

    @Query("SELECT COUNT(*) FROM failed_attempts WHERE package_name = :packageName AND timestamp > :since")
    suspend fun countRecentFailures(packageName: String, since: Long): Int

    @Query("DELETE FROM failed_attempts WHERE timestamp < :before")
    suspend fun deleteOlderThan(before: Long): Int

    @Query("SELECT COUNT(*) FROM failed_attempts")
    suspend fun totalCount(): Int

    /** Get all failed attempts (for backup). */
    @Query("SELECT * FROM failed_attempts")
    suspend fun getAll(): List<FailedAttemptEntity>

    /** Insert multiple records (for restore). */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(records: Array<FailedAttemptEntity>)

    /** Delete all records (for restore). */
    @Query("DELETE FROM failed_attempts")
    suspend fun deleteAll()
}