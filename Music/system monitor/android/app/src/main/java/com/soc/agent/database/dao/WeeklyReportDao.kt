package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.WeeklyReportEntity

@Dao
interface WeeklyReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WeeklyReportEntity): Long

    /** Get a report by week start. */
    @Query("SELECT * FROM weekly_report WHERE week_start = :weekStart")
    suspend fun getForWeek(weekStart: Long): WeeklyReportEntity?

    /** Get a report by id. */
    @Query("SELECT * FROM weekly_report WHERE id = :id")
    suspend fun getById(id: Long): WeeklyReportEntity?

    /** Get all reports, newest first. */
    @Query("SELECT * FROM weekly_report ORDER BY week_start DESC")
    suspend fun getAll(): List<WeeklyReportEntity>

    /** Get the most recent report. */
    @Query("SELECT * FROM weekly_report ORDER BY week_start DESC LIMIT 1")
    suspend fun getLatest(): WeeklyReportEntity?

    /** Get reports in a date range. */
    @Query("SELECT * FROM weekly_report WHERE week_start BETWEEN :from AND :to ORDER BY week_start DESC")
    suspend fun getInRange(from: Long, to: Long): List<WeeklyReportEntity>

    /** Get last N reports. */
    @Query("SELECT * FROM weekly_report ORDER BY week_start DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 4): List<WeeklyReportEntity>

    /** Delete reports older than a given epoch millis. */
    @Query("DELETE FROM weekly_report WHERE week_start < :beforeMs")
    suspend fun deleteOlderThan(beforeMs: Long)

    /** Total row count. */
    @Query("SELECT COUNT(*) FROM weekly_report")
    suspend fun totalCount(): Int
}