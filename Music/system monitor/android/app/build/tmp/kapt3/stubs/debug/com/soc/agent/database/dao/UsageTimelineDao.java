package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.UsageTimelineEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\b\b\u0002\u0010\u001c\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ,\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0015J&\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\b2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\"J\u000e\u0010#\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010$\u00a8\u0006%"}, d2 = {"Lcom/soc/agent/database/dao/UsageTimelineDao;", "", "deleteOlderThan", "", "beforeMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveAppsForDay", "", "", "dateInt", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveAppsInRange", "fromInt", "toInt", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForDay", "Lcom/soc/agent/database/entity/UsageTimelineEntity;", "getForPackageInRange", "packageName", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForPackageOnDay", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForegroundEventsForDay", "getForegroundTimestamps", "getFullTimeline", "getRecent", "limit", "getSessionDurations", "getSwitchPatterns", "Lcom/soc/agent/database/dao/AppSwitch;", "insert", "entity", "(Lcom/soc/agent/database/entity/UsageTimelineEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface UsageTimelineDao {
    
    /**
     * Insert a timeline event.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.UsageTimelineEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * All events for a specific day (YYYYMMDD int), chronological.
     */
    @androidx.room.Query(value = "SELECT * FROM usage_timeline WHERE date_int = :dateInt ORDER BY event_time_ms ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForDay(int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageTimelineEntity>> $completion);
    
    /**
     * All events for a package on a specific day, chronological.
     */
    @androidx.room.Query(value = "SELECT * FROM usage_timeline WHERE package_name = :packageName AND date_int = :dateInt ORDER BY event_time_ms ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackageOnDay(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageTimelineEntity>> $completion);
    
    /**
     * All events for a package in a date range, chronological.
     */
    @androidx.room.Query(value = "SELECT * FROM usage_timeline WHERE package_name = :packageName AND date_int BETWEEN :fromInt AND :toInt ORDER BY event_time_ms ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForPackageInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageTimelineEntity>> $completion);
    
    /**
     * All events across all apps in a date range, chronological (full timeline).
     */
    @androidx.room.Query(value = "SELECT * FROM usage_timeline WHERE date_int BETWEEN :fromInt AND :toInt ORDER BY event_time_ms ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFullTimeline(int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageTimelineEntity>> $completion);
    
    /**
     * Recent N events across all apps.
     */
    @androidx.room.Query(value = "SELECT * FROM usage_timeline ORDER BY event_time_ms DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageTimelineEntity>> $completion);
    
    /**
     * Foreground events only (app launches) for a day.
     */
    @androidx.room.Query(value = "SELECT * FROM usage_timeline WHERE date_int = :dateInt AND event_type = 'foreground' ORDER BY event_time_ms ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForegroundEventsForDay(int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageTimelineEntity>> $completion);
    
    /**
     * Session durations (background events with duration) for a package in a range.
     */
    @androidx.room.Query(value = "SELECT * FROM usage_timeline WHERE package_name = :packageName AND event_type = 'background' AND duration_ms > 0 AND date_int BETWEEN :fromInt AND :toInt ORDER BY event_time_ms ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSessionDurations(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UsageTimelineEntity>> $completion);
    
    /**
     * App switch sequence for a day (what was used after what).
     */
    @androidx.room.Query(value = "SELECT package_name AS packageName, previous_app AS previousApp, COUNT(*) AS switchCount FROM usage_timeline WHERE date_int = :dateInt AND event_type = 'foreground' AND previous_app IS NOT NULL GROUP BY package_name, previous_app ORDER BY switchCount DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSwitchPatterns(int dateInt, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.AppSwitch>> $completion);
    
    /**
     * Unique apps used on a specific day.
     */
    @androidx.room.Query(value = "SELECT DISTINCT package_name FROM usage_timeline WHERE date_int = :dateInt ORDER BY package_name")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveAppsForDay(int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    /**
     * Unique apps used in a date range.
     */
    @androidx.room.Query(value = "SELECT DISTINCT package_name FROM usage_timeline WHERE date_int BETWEEN :fromInt AND :toInt ORDER BY package_name")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveAppsInRange(int fromInt, int toInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    /**
     * Time gap between consecutive foreground events (for detecting idle periods).
     */
    @androidx.room.Query(value = "SELECT event_time_ms FROM usage_timeline WHERE date_int = :dateInt AND event_type = 'foreground' ORDER BY event_time_ms ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForegroundTimestamps(int dateInt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    /**
     * Delete events older than a given epoch millis.
     */
    @androidx.room.Query(value = "DELETE FROM usage_timeline WHERE event_time_ms < :beforeMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Total row count.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM usage_timeline")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}