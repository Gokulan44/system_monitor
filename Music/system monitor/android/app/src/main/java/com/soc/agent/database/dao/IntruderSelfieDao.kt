package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.IntruderSelfieEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for intruder selfie records.
 */
@Dao
interface IntruderSelfieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: IntruderSelfieEntity)

    @Query("SELECT * FROM intruder_selfies WHERE package_name = :packageName ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getSelfiesForPackage(packageName: String, limit: Int = 50): List<IntruderSelfieEntity>

    @Query("SELECT * FROM intruder_selfies ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentSelfies(limit: Int = 100): List<IntruderSelfieEntity>

    @Query("SELECT COUNT(*) FROM intruder_selfies WHERE package_name = :packageName")
    suspend fun countForPackage(packageName: String): Int

    @Query("DELETE FROM intruder_selfies WHERE timestamp < :before")
    suspend fun deleteOlderThan(before: Long): Int

    @Query("SELECT COUNT(*) FROM intruder_selfies")
    suspend fun totalCount(): Int

    /** Get all intruder selfies (for backup). */
    @Query("SELECT * FROM intruder_selfies")
    suspend fun getAll(): List<IntruderSelfieEntity>

    /** Insert multiple records (for restore). */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(records: Array<IntruderSelfieEntity>)

    /** Delete all records (for restore). */
    @Query("DELETE FROM intruder_selfies")
    suspend fun deleteAll()
}