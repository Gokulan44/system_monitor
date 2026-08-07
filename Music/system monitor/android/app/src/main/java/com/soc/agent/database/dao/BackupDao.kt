package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.BackupEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for backup records.
 */
@Dao
interface BackupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(backup: BackupEntity)

    @Query("SELECT * FROM backups ORDER BY created_at DESC")
    suspend fun getAllBackups(): List<BackupEntity>

    @Query("SELECT * FROM backups WHERE id = :id")
    suspend fun getBackupById(id: Long): BackupEntity?

    @Query("DELETE FROM backups WHERE id = :id")
    suspend fun deleteBackup(id: Long): Int

    @Query("DELETE FROM backups WHERE created_at < :before")
    suspend fun deleteOlderThan(before: Long): Int

    @Query("SELECT COUNT(*) FROM backups")
    suspend fun totalCount(): Int
}