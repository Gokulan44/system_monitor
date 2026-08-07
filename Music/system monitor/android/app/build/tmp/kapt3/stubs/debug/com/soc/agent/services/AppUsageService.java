package com.soc.agent.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.soc.agent.R;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.AppUsageEntity;
import com.soc.agent.database.entity.DailyUsageEntity;
import com.soc.agent.database.entity.LaunchCountEntity;
import com.soc.agent.database.entity.UsageSessionEntity;
import com.soc.agent.database.entity.UsageTimelineEntity;
import kotlinx.coroutines.Dispatchers;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u000fH\u0016J\b\u0010\u001d\u001a\u00020\u000fH\u0016J\"\u0010\u001e\u001a\u00020\u001f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020\u000fH\u0002J\u000e\u0010#\u001a\u00020\u000fH\u0082@\u00a2\u0006\u0002\u0010$R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/soc/agent/services/AppUsageService;", "Landroid/app/Service;", "()V", "activeSessions", "Ljava/util/concurrent/ConcurrentHashMap;", "", "", "currentForeground", "scope", "Lkotlinx/coroutines/CoroutineScope;", "watcherJob", "Lkotlinx/coroutines/Job;", "buildNotification", "Landroid/app/Notification;", "closeAllActiveSessions", "", "ensureChannel", "foregroundPackage", "usm", "Landroid/app/usage/UsageStatsManager;", "getAppLabel", "packageName", "onAppBackground", "onAppForeground", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "startWatcher", "watchLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class AppUsageService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job watcherJob;
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Long> activeSessions = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentForeground;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AppUsageService";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "app_usage";
    private static final int NOTIFICATION_ID = 21843;
    private static final long POLL_INTERVAL_MS = 1000L;
    private static final long WINDOW_MS = 30000L;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.AppUsageService.Companion Companion = null;
    
    public AppUsageService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void startWatcher() {
    }
    
    private final java.lang.Object watchLoop(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.String foregroundPackage(android.app.usage.UsageStatsManager usm) {
        return null;
    }
    
    private final void onAppForeground(java.lang.String packageName) {
    }
    
    private final void onAppBackground(java.lang.String packageName) {
    }
    
    private final void closeAllActiveSessions() {
    }
    
    private final android.app.Notification buildNotification() {
        return null;
    }
    
    private final void ensureChannel() {
    }
    
    private final java.lang.String getAppLabel(java.lang.String packageName) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/soc/agent/services/AppUsageService$Companion;", "", "()V", "CHANNEL_ID", "", "NOTIFICATION_ID", "", "POLL_INTERVAL_MS", "", "TAG", "WINDOW_MS", "hasUsageAccess", "", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean hasUsageAccess(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return false;
        }
    }
}