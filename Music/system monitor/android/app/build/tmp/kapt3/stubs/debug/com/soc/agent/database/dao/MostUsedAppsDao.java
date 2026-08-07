package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

/**
 * Read-only DAO that provides Most Used Apps rankings from existing usage data.
 * No new entity — these queries aggregate daily_usage / app_usage / weekly / monthly tables.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\bJ.\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\bJ&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0014J&\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0014J.\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/database/dao/MostUsedAppsDao;", "", "mostLaunchedForDay", "", "Lcom/soc/agent/database/dao/LaunchRank;", "dateInt", "", "limit", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mostLaunchedInRange", "fromInt", "toInt", "(IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "topAppsAllTime", "Lcom/soc/agent/database/dao/UsageRank;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "topAppsForDay", "topAppsForMonth", "monthStart", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "topAppsForWeek", "weekStart", "topAppsInRange", "app_debug"})
@androidx.room.Dao()
public abstract interface MostUsedAppsDao {
    
    /**
     * Top [limit] apps for a given day (YYYYMMDD int), ordered by total usage time descending.
     * Returns pairs of (packageName, totalTimeMs).
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n        FROM daily_usage\n        WHERE date = :dateInt\n        GROUP BY package_name\n        ORDER BY totalTimeMs DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topAppsForDay(int dateInt, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.UsageRank>> $completion);
    
    /**
     * Top [limit] apps for a given week, ordered by total usage time descending.
     * @param weekStart Monday 00:00 epoch millis of the week.
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n        FROM weekly_usage\n        WHERE week_start = :weekStart\n        GROUP BY package_name\n        ORDER BY totalTimeMs DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topAppsForWeek(long weekStart, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.UsageRank>> $completion);
    
    /**
     * Top [limit] apps for a given month, ordered by total usage time descending.
     * @param monthStart 1st-of-month 00:00 epoch millis.
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n        FROM monthly_usage\n        WHERE month_start = :monthStart\n        GROUP BY package_name\n        ORDER BY totalTimeMs DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topAppsForMonth(long monthStart, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.UsageRank>> $completion);
    
    /**
     * All-time top [limit] apps by lifetime usage, from app_usage table.
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, total_time_ms AS totalTimeMs\n        FROM app_usage\n        ORDER BY totalTimeMs DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topAppsAllTime(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.UsageRank>> $completion);
    
    /**
     * Top [limit] apps across a date range, aggregated from daily_usage.
     * @param fromInt start date (YYYYMMDD int, inclusive)
     * @param toInt   end date (YYYYMMDD int, inclusive)
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n        FROM daily_usage\n        WHERE date BETWEEN :fromInt AND :toInt\n        GROUP BY package_name\n        ORDER BY totalTimeMs DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object topAppsInRange(int fromInt, int toInt, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.UsageRank>> $completion);
    
    /**
     * Most launched apps for a given day.
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, launch_count AS launchCount\n        FROM daily_usage\n        WHERE date = :dateInt\n        ORDER BY launch_count DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object mostLaunchedForDay(int dateInt, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.LaunchRank>> $completion);
    
    /**
     * Most launched apps for the current month.
     */
    @androidx.room.Query(value = "\n        SELECT package_name AS packageName, SUM(launch_count) AS launchCount\n        FROM daily_usage\n        WHERE date BETWEEN :fromInt AND :toInt\n        GROUP BY package_name\n        ORDER BY launchCount DESC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object mostLaunchedInRange(int fromInt, int toInt, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.dao.LaunchRank>> $completion);
    
    /**
     * Read-only DAO that provides Most Used Apps rankings from existing usage data.
     * No new entity — these queries aggregate daily_usage / app_usage / weekly / monthly tables.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}