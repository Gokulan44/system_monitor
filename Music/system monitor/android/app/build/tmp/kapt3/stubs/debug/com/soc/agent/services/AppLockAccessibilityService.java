package com.soc.agent.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.soc.agent.data.LockSettings;
import com.soc.agent.data.LockedAppsRepository;
import com.soc.agent.database.AppDatabase;
import kotlinx.coroutines.*;

/**
 * AccessibilityService that detects foreground app changes for App Lock.
 * When a locked app comes to the foreground, broadcasts an intent to
 * launch the appropriate lock gate activity.
 *
 * Setup: User must enable this service in Settings → Accessibility → SOC Agent.
 *
 * Flow:
 *  1. onAccessibilityEvent(TYPE_WINDOW_STATE_CHANGED)
 *  2. Check if new app is locked via LockedAppsRepository
 *  3. If locked → broadcast LOCK_ACTIVITY intent
 *  4. AppLockGateActivity receives intent and shows gate
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0006H\u0002J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\b\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/services/AppLockAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "handler", "Landroid/os/Handler;", "lastPackage", "", "lastTimestamp", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "checkAndLock", "", "packageName", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSystemPackage", "", "onAccessibilityEvent", "event", "Landroid/view/accessibility/AccessibilityEvent;", "onDestroy", "onInterrupt", "onServiceConnected", "Companion", "app_debug"})
public final class AppLockAccessibilityService extends android.accessibilityservice.AccessibilityService {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastPackage;
    private long lastTimestamp = 0L;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String TAG = "AppLockA11y";
    @java.lang.Deprecated()
    public static final long DEBOUNCE_MS = 300L;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String ACTION_LOCK = "com.soc.agent.LOCK_APP";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String EXTRA_PACKAGE = "package_name";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String EXTRA_GATE_TYPE = "gate_type";
    @org.jetbrains.annotations.NotNull()
    private static final com.soc.agent.services.AppLockAccessibilityService.Companion Companion = null;
    
    public AppLockAccessibilityService() {
        super();
    }
    
    @java.lang.Override()
    protected void onServiceConnected() {
    }
    
    @java.lang.Override()
    public void onAccessibilityEvent(@org.jetbrains.annotations.Nullable()
    android.view.accessibility.AccessibilityEvent event) {
    }
    
    private final java.lang.Object checkAndLock(java.lang.String packageName, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final boolean isSystemPackage(java.lang.String packageName) {
        return false;
    }
    
    @java.lang.Override()
    public void onInterrupt() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/soc/agent/services/AppLockAccessibilityService$Companion;", "", "()V", "ACTION_LOCK", "", "DEBOUNCE_MS", "", "EXTRA_GATE_TYPE", "EXTRA_PACKAGE", "TAG", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}