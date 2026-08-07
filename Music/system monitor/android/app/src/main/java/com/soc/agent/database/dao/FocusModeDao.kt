package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.soc.agent.database.entity.FocusModeEntity

@Dao
interface FocusModeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FocusModeEntity): Long

    @Update
    suspend fun update(entity: FocusModeEntity)

    /** Get a focus mode by id. */
    @Query("SELECT * FROM focus_mode WHERE id = :id")
    suspend fun getById(id: Long): FocusModeEntity?

    /** Get all focus modes. */
    @Query("SELECT * FROM focus_mode ORDER BY created_at_ms DESC")
    suspend fun getAll(): List<FocusModeEntity>

    /** Get currently active focus modes. */
    @Query("SELECT * FROM focus_mode WHERE active = 1")
    suspend fun getActive(): List<FocusModeEntity>

    /** Get a specific focus mode by name. */
    @Query("SELECT * FROM focus_mode WHERE name = :name LIMIT 1")
    suspend fun getByName(name: String): FocusModeEntity?

    /** Check if a package is blocked by any active focus mode. */
    @Query("SELECT COUNT(*) FROM focus_mode WHERE active = 1 AND blocked_apps LIKE '%' || :packageName || '%'")
    suspend fun isPackageBlocked(packageName: String): Int

    /** Get all active focus modes that block a specific package. */
    @Query("SELECT * FROM focus_mode WHERE active = 1 AND blocked_apps LIKE '%' || :packageName || '%'")
    suspend fun getActiveModesBlockingPackage(packageName: String): List<FocusModeEntity>

    /** Deactivate a focus mode by id. */
    @Query("UPDATE focus_mode SET active = 0 WHERE id = :id")
    suspend fun deactivate(id: Long)

    /** Deactivate all focus modes. */
    @Query("UPDATE focus_mode SET active = 0")
    suspend fun deactivateAll()

    /** Get scheduled focus modes (non-empty schedule). */
    @Query("SELECT * FROM focus_mode WHERE schedule != ''")
    suspend fun getScheduled(): List<FocusModeEntity>

    /** Delete a focus mode by id. */
    @Query("DELETE FROM focus_mode WHERE id = :id")
    suspend fun deleteById(id: Long)

    /** Total row count. */
    @Query("SELECT COUNT(*) FROM focus_mode")
    suspend fun totalCount(): Int
}