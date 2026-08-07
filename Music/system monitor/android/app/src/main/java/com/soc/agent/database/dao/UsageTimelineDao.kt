package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soc.agent.database.entity.UsageTimelineEntity

@Dao
interface UsageTimelineDao {

    /** Insert a timeline event. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: UsageTimelineEntity)

    /** All events for a specific day (YYYYMMDD int), chronological. */
    @Query("SELECT * FROM usage_timeline WHERE date_int = :dateInt ORDER BY event_time_ms ASC")
    suspend fun getForDay(dateInt: Int): List<UsageTimelineEntity>

    /** All events for a package on a specific day, chronological. */
    @Query("SELECT * FROM usage_timeline WHERE package_name = :packageName AND date_int = :dateInt ORDER BY event_time_ms ASC")
    suspend fun getForPackageOnDay(packageName: String, dateInt: Int): List<UsageTimelineEntity>

    /** All events for a package in a date range, chronological. */
    @Query("SELECT * FROM usage_timeline WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt ORDER BY event_time_ms ASC")
    suspend fun getForPackageInRange(packageName: String, fromInt: Int, toInt: Int): List<UsageTimelineEntity>

    /** All events across all apps in a date range, chronological (full timeline). */
    @Query("SELECT * FROM usage_timeline WHERE date_int BETWEEN :fromInt AND :toInt ORDER BY event_time_ms ASC")
    suspend fun getFullTimeline(fromInt: Int, toInt: Int): List<UsageTimelineEntity>

    /** Recent N events across all apps. */
    @Query("SELECT * FROM usage_timeline ORDER BY event_time_ms DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 50): List<UsageTimelineEntity>

    /** Foreground events only (app launches) for a day. */
    @Query("SELECT * FROM usage_timeline WHERE date_int = :dateInt AND event_type = 'foreground' ORDER BY event_time_ms ASC")
    suspend fun getForegroundEventsForDay(dateInt: Int): List<UsageTimelineEntity>

    /** Session durations (background events with duration) for a package in a range. */
    @Query("SELECT * FROM usage_timeline WHERE package_name = :packageName AND event_type = 'background' AND duration_ms > 0 AND date_int BETWEEN :fromInt AND :toInt ORDER BY event_time_ms ASC")
    suspend fun getSessionDurations(packageName: String, fromInt: Int, toInt: Int): List<UsageTimelineEntity>

    /** App switch sequence for a day (what was used after what). */
    @Query("SELECT package_name AS packageName, previous_app AS previousApp, COUNT(*) AS switchCount FROM usage_timeline WHERE date_int = :dateInt AND event_type = 'foreground' AND previous_app IS NOT NULL GROUP BY package_name, previous_app ORDER BY switchCount DESC LIMIT :limit")
    suspend fun getSwitchPatterns(dateInt: Int, limit: Int = 20): List<AppSwitch>

    /** Unique apps used on a specific day. */
    @Query("SELECT DISTINCT package_name FROM usage_timeline WHERE date_int = :dateInt ORDER BY package_name")
    suspend fun getActiveAppsForDay(dateInt: Int): List<String>

    /** Unique apps used in a date range. */
    @Query("SELECT DISTINCT package_name FROM usage_timeline WHERE date_int BETWEEN :fromInt AND :toInt ORDER BY package_name")
    suspend fun getActiveAppsInRange(fromInt: Int, toInt: Int): List<String>

    /** Time gap between consecutive foreground events (for detecting idle periods). */
    @Query("SELECT event_time_ms FROM usage_timeline WHERE date_int = :dateInt AND event_type = 'foreground' ORDER BY event_time_ms ASC")
    suspend fun getForegroundTimestamps(dateInt: Int): List<Long>

    /** Delete events older than a given epoch millis. */
    @Query("DELETE FROM usage_timeline WHERE event_time_ms < :beforeMs")
    suspend fun deleteOlderThan(beforeMs: Long)

    /** Total row count. */
    @Query("SELECT COUNT(*) FROM usage_timeline")
    suspend fun totalCount(): Int
}

/** POJO for app switch pattern results. */
data class AppSwitch(
    val packageName: String,
    val previousApp: String?,
    val switchCount: Int
)