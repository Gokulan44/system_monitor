package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.ExportReportEntity

@Dao
interface ExportReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ExportReportEntity): Long

    /** Get an export by id. */
    @Query("SELECT * FROM export_report WHERE id = :id")
    suspend fun getById(id: Long): ExportReportEntity?

    /** Get all exports, newest first. */
    @Query("SELECT * FROM export_report ORDER BY exported_at DESC")
    suspend fun getAll(): List<ExportReportEntity>

    /** Get the most recent export. */
    @Query("SELECT * FROM export_report ORDER BY exported_at DESC LIMIT 1")
    suspend fun getLatest(): ExportReportEntity?

    /** Get exports by format. */
    @Query("SELECT * FROM export_report WHERE format = :format ORDER BY exported_at DESC")
    suspend fun getByFormat(format: String): List<ExportReportEntity>

    /** Get exports by scope. */
    @Query("SELECT * FROM export_report WHERE scope = :scope ORDER BY exported_at DESC")
    suspend fun getByScope(scope: String): List<ExportReportEntity>

    /** Delete an export record by id. */
    @Query("DELETE FROM export_report WHERE id = :id")
    suspend fun deleteById(id: Long)

    /** Total row count. */
    @Query("SELECT COUNT(*) FROM export_report")
    suspend fun totalCount(): Int
}