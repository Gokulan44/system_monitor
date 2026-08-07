package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.SecuritySettingsEntity

/**
 * DAO for security settings (singleton row).
 */
@Dao
interface SecuritySettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(settings: SecuritySettingsEntity)

    @Query("SELECT * FROM security_settings WHERE id = 1")
    suspend fun getSettings(): SecuritySettingsEntity?

    @Query("DELETE FROM security_settings WHERE id = 1")
    suspend fun clearSettings(): Int
}