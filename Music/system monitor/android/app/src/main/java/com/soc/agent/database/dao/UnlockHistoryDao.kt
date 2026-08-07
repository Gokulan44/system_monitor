package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.UnlockHistoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for unlock history records.
 */
@Dao
interface UnlockHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: UnlockHistoryEntity)

    @Query("SELECT * FROM unlock_history WHERE package_name = :packageName ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getHistoryForPackage(packageName: String, limit: Int = 50): List<UnlockHistoryEntity>

    @Query("SELECT * FROM unlock_history ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentHistory(limit: Int = 100): List<UnlockHistoryEntity>

    @Query("SELECT COUNT(*) FROM unlock_history WHERE package_name = :packageName")
    suspend fun countForPackage(packageName: String): Int

    @Query("DELETE FROM unlock_history WHERE timestamp < :before")
    suspend fun deleteOlderThan(before: Long): Int

    @Query("SELECT COUNT(*) FROM unlock_history")
    suspend fun totalCount(): Int

    /** Get all unlock history (for backup). */
    @Query("SELECT * FROM unlock_history")
    suspend fun getAll(): List<UnlockHistoryEntity>

    /** Insert multiple records (for restore). */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(records: Array<UnlockHistoryEntity>)

    /** Delete all records (for restore). */
    @Query("DELETE FROM unlock_history")
    suspend fun deleteAll()
}