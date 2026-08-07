package com.soc.agent.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.soc.agent.R;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.UnlockHistoryEntity;
import com.soc.agent.security.AppLockPolicy;
import com.soc.agent.ui.AppLockGateActivity;
import com.soc.agent.utils.Prefs;
import kotlinx.coroutines.Dispatchers;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Foreground watcher that enforces per-app locks.
 *
 * Polls [UsageStatsManager] for the currently visible app; whenever a locked
 * package comes to the foreground it launches the configured unlock gate
 * ([AppLockGateActivity], which shows the PIN or pattern prompt). A short
 * grace window after a successful unlock keeps the app usable until it leaves
 * the foreground again, and a static [gateVisible] flag prevents re-launching
 * the gate while it is already on screen.
 *
 * Requires the PACKAGE_USAGE_STATS special access permission (user-granted in
 * system settings — see [hasUsageAccess]); without it the watcher cannot see
 * other apps and degrades to a no-op with the permission notice surfaced in
 * the App Lock dashboard.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u000e\u0010\u001b\u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/soc/agent/services/AppLockService;", "Landroid/app/Service;", "()V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "watcherJob", "Lkotlinx/coroutines/Job;", "buildNotification", "Landroid/app/Notification;", "ensureChannel", "", "foregroundPackage", "", "usm", "Landroid/app/usage/UsageStatsManager;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "triggerGate", "packageName", "watchLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class AppLockService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job watcherJob;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AppLockService";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "app_lock";
    private static final int NOTIFICATION_ID = 19531;
    private static final long POLL_INTERVAL_MS = 600L;
    private static final long WINDOW_MS = 30000L;
    
    /**
     * True while the unlock gate activity is on screen (prevents relaunch).
     */
    @kotlin.jvm.Volatile()
    private static volatile boolean gateVisible = false;
    
    /**
     * package -> last successful unlock time, per grace window.
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Long> recentUnlocks = null;
    
    /**
     * package -> last time the app was in foreground (for auto-lock timeout).
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Long> lastForegroundTime = null;
    
    /**
     * Set of currently-unlocked packages (in foreground).
     */
    private static final java.util.concurrent.ConcurrentHashMap.KeySetView<java.lang.String, java.lang.Boolean> currentlyUnlocked = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.AppLockService.Companion Companion = null;
    
    public AppLockService() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
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
    public void onDestroy() {
    }
    
    private final java.lang.Object watchLoop(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Returns the package currently in the foreground, or null when unknown.
     */
    private final java.lang.String foregroundPackage(android.app.usage.UsageStatsManager usm) {
        return null;
    }
    
    private final void triggerGate(java.lang.String packageName) {
    }
    
    private final android.app.Notification buildNotification() {
        return null;
    }
    
    private final void ensureChannel() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u0004J.\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u000eJ\u0016\u0010!\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0004J\u0006\u0010\"\u001a\u00020#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000RN\u0010\u000b\u001aB\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \r*\u0004\u0018\u00010\u000e0\u000e \r* \u0012\f\u0012\n \r*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \r*\u0004\u0018\u00010\u000e0\u000e\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/soc/agent/services/AppLockService$Companion;", "", "()V", "CHANNEL_ID", "", "NOTIFICATION_ID", "", "POLL_INTERVAL_MS", "", "TAG", "WINDOW_MS", "currentlyUnlocked", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", "", "gateVisible", "getGateVisible", "()Z", "setGateVisible", "(Z)V", "lastForegroundTime", "Ljava/util/concurrent/ConcurrentHashMap;", "recentUnlocks", "getAppLabel", "context", "Landroid/content/Context;", "packageName", "hasUsageAccess", "recordForeground", "", "recordUnlock", "gateMethod", "autoUnlock", "shouldAutoLock", "usageAccessIntent", "Landroid/content/Intent;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * True while the unlock gate activity is on screen (prevents relaunch).
         */
        public final boolean getGateVisible() {
            return false;
        }
        
        /**
         * True while the unlock gate activity is on screen (prevents relaunch).
         */
        public final void setGateVisible(boolean p0) {
        }
        
        /**
         * Records a successful unlock so the watcher leaves the app alone briefly.
         */
        public final void recordUnlock(@org.jetbrains.annotations.NotNull()
        java.lang.String packageName, @org.jetbrains.annotations.Nullable()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String gateMethod, boolean autoUnlock) {
        }
        
        private final java.lang.String getAppLabel(android.content.Context context, java.lang.String packageName) {
            return null;
        }
        
        /**
         * Records that a locked app just went to foreground.
         */
        public final void recordForeground(@org.jetbrains.annotations.NotNull()
        java.lang.String packageName) {
        }
        
        /**
         * Checks if a package should be re-locked based on auto-lock settings.
         */
        public final boolean shouldAutoLock(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String packageName) {
            return false;
        }
        
        /**
         * True when the user granted the PACKAGE_USAGE_STATS special access.
         */
        public final boolean hasUsageAccess(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return false;
        }
        
        /**
         * Deep link into the usage-access settings page.
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent usageAccessIntent() {
            return null;
        }
    }
}