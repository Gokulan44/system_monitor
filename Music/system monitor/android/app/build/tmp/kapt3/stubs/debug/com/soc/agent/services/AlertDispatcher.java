package com.soc.agent.services;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.soc.agent.api.dto.AlertDto;

/**
 * Pushes security alerts locally (as a notification) and returns an [AlertDto]
 * that the repository will forward to the SOC server for persistence and
 * operator notification.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J(\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/soc/agent/services/AlertDispatcher;", "", "()V", "CHANNEL_ID", "", "CHANNEL_NAME", "channelCreated", "", "ensureChannel", "", "context", "Landroid/content/Context;", "postNotification", "level", "title", "message", "push", "Lcom/soc/agent/api/dto/AlertDto;", "app_debug"})
public final class AlertDispatcher {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "soc_alerts";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_NAME = "Security alerts";
    private static boolean channelCreated = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.AlertDispatcher INSTANCE = null;
    
    private AlertDispatcher() {
        super();
    }
    
    /**
     * Records an alert. A heads-up notification is posted when the
     * POST_NOTIFICATIONS runtime permission is granted (Android 13+); on older
     * versions no permission is required. Always returns the [AlertDto] for sync.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.AlertDto push(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String level, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        return null;
    }
    
    private final void ensureChannel(android.content.Context context) {
    }
    
    private final void postNotification(android.content.Context context, java.lang.String level, java.lang.String title, java.lang.String message) {
    }
}