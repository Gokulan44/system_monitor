package com.soc.agent.services;

import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.MonthlyUsageEntity;
import com.soc.agent.database.entity.ScreenTimeEntity;
import com.soc.agent.database.entity.WeeklyUsageEntity;
import java.util.Calendar;

/**
 * Helper object providing date calculations and metadata updates for AppUsageService.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J.\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0013J.\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0013J.\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/services/UsageTrackingHelper;", "", "()V", "dateInt", "", "timeMs", "", "getDayStart", "getMonthStart", "getTodayDate", "getWeekStart", "recordMonthlyLaunch", "", "db", "Lcom/soc/agent/database/AppDatabase;", "packageName", "", "appName", "now", "(Lcom/soc/agent/database/AppDatabase;Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordWeeklyLaunch", "updateScreenTimeBucket", "duration", "(Lcom/soc/agent/database/AppDatabase;JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UsageTrackingHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.UsageTrackingHelper INSTANCE = null;
    
    private UsageTrackingHelper() {
        super();
    }
    
    public final int getTodayDate() {
        return 0;
    }
    
    public final long getDayStart(long timeMs) {
        return 0L;
    }
    
    public final long getWeekStart(long timeMs) {
        return 0L;
    }
    
    public final int dateInt(long timeMs) {
        return 0;
    }
    
    public final long getMonthStart(long timeMs) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object recordWeeklyLaunch(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.AppDatabase db, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long now, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object recordMonthlyLaunch(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.AppDatabase db, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, long now, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateScreenTimeBucket(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.AppDatabase db, long duration, long now, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}