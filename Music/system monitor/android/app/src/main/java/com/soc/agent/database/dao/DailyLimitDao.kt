package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.soc.agent.database.entity.DailyLimitEntity

@Dao
interface DailyLimitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DailyLimitEntity): Long

    @Update
    suspend fun update(entity: DailyLimitEntity)

    /** Get a limit by id. */
    @Query("SELECT * FROM daily_limit WHERE id = :id")
    suspend fun getById(id: Long): DailyLimitEntity?

    /** Get the limit for a specific package. */
    @Query("SELECT * FROM daily_limit WHERE package_name = :packageName LIMIT 1")
    suspend fun getForPackage(packageName: String): DailyLimitEntity?

    /** Get all configured limits. */
    @Query("SELECT * FROM daily_limit ORDER BY app_name ASC")
    suspend fun getAll(): List<DailyLimitEntity>

    /** Get all enabled limits. */
    @Query("SELECT * FROM daily_limit WHERE enabled = 1")
    suspend fun getEnabled(): List<DailyLimitEntity>

    /** Get all limits where the package is in the blocked list. */
    @Query("SELECT * FROM daily_limit WHERE enabled = 1 AND exceeded_action = 'block'")
    suspend fun getBlockLimits(): List<DailyLimitEntity>

    /** Check if a specific package has a block-type limit. */
    @Query("SELECT COUNT(*) FROM daily_limit WHERE enabled = 1 AND package_name = :packageName AND exceeded_action = 'block'")
    suspend fun isBlockLimited(packageName: String): Int

    /** Delete a limit by id. */
    @Query("DELETE FROM daily_limit WHERE id = :id")
    suspend fun deleteById(id: Long)

    /** Delete a limit by package name. */
    @Query("DELETE FROM daily_limit WHERE package_name = :packageName")
    suspend fun deleteByPackage(packageName: String)

    /** Total row count. */
    @Query("SELECT COUNT(*) FROM daily_limit")
    suspend fun totalCount(): Int
}