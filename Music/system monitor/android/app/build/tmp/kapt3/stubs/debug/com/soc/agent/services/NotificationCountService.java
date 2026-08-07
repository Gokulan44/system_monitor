package com.soc.agent.services;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.NotificationCountEntity;
import kotlinx.coroutines.Dispatchers;
import java.util.Calendar;

/**
 * Captures all posted notifications for analytics.
 * Must be enabled by user in Settings > Notification access.
 *
 * Events:
 * - onNotificationPosted: records every notification with app name, title, category, priority, timestamps
 * - Retention: keeps last 30 days (cleanup on start)
 *
 * Foreground service not required (system binding handles lifecycle).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/soc/agent/services/NotificationCountService;", "Landroid/service/notification/NotificationListenerService;", "()V", "appLabelCache", "Ljava/util/HashMap;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getAppLabel", "packageName", "onCreate", "", "onDestroy", "onNotificationPosted", "sbn", "Landroid/service/notification/StatusBarNotification;", "onNotificationRemoved", "Companion", "app_debug"})
public final class NotificationCountService extends android.service.notification.NotificationListenerService {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.HashMap<java.lang.String, java.lang.String> appLabelCache = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.NotificationCountService.Companion Companion = null;
    
    public NotificationCountService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onNotificationPosted(@org.jetbrains.annotations.NotNull()
    android.service.notification.StatusBarNotification sbn) {
    }
    
    @java.lang.Override()
    public void onNotificationRemoved(@org.jetbrains.annotations.NotNull()
    android.service.notification.StatusBarNotification sbn) {
    }
    
    private final java.lang.String getAppLabel(java.lang.String packageName) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\t"}, d2 = {"Lcom/soc/agent/services/NotificationCountService$Companion;", "", "()V", "isEnabled", "", "androidContext", "Landroid/content/Context;", "settingsIntent", "Landroid/content/Intent;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Check if notification listener is enabled for this app.
         * Returns true if user has granted Notification Access.
         */
        public final boolean isEnabled(@org.jetbrains.annotations.NotNull()
        android.content.Context androidContext) {
            return false;
        }
        
        /**
         * Intent to open the Notification Access settings page for this app.
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent settingsIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context androidContext) {
            return null;
        }
    }
}