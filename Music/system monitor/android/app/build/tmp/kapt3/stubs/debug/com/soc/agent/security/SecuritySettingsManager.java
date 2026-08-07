package com.soc.agent.security;

import android.content.Context;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.SecuritySettingsEntity;
import kotlinx.coroutines.Dispatchers;

/**
 * Manager for App Lock security settings.
 * Wraps the SecuritySettingsEntity DAO with defaults and in-memory caching.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u000e\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u0013\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u0014\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J'\u0010\u0019\u001a\u00020\u00062\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001b\u00a2\u0006\u0002\b\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/soc/agent/security/SecuritySettingsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cached", "Lcom/soc/agent/database/entity/SecuritySettingsEntity;", "db", "Lcom/soc/agent/database/AppDatabase;", "getFakeCrashMessage", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLockDelayMs", "", "getSettings", "invalidateCache", "", "isBreakInAlertEnabled", "", "isFakeCrashEnabled", "isVibrateOnFailed", "shouldCaptureSelfie", "attemptNumber", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSettings", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class SecuritySettingsManager {
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.database.AppDatabase db = null;
    @org.jetbrains.annotations.Nullable()
    private com.soc.agent.database.entity.SecuritySettingsEntity cached;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.security.SecuritySettingsManager INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.SecuritySettingsManager.Companion Companion = null;
    
    private SecuritySettingsManager(android.content.Context context) {
        super();
    }
    
    /**
     * Get current settings (loads from DB on first call).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.SecuritySettingsEntity> $completion) {
        return null;
    }
    
    /**
     * Update settings and persist to DB.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateSettings(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.soc.agent.database.entity.SecuritySettingsEntity, com.soc.agent.database.entity.SecuritySettingsEntity> block, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.SecuritySettingsEntity> $completion) {
        return null;
    }
    
    /**
     * Check if intruder selfie is enabled and threshold reached.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object shouldCaptureSelfie(int attemptNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Check if fake crash is enabled.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isFakeCrashEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Get fake crash message.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFakeCrashMessage(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Check if break-in alert is enabled.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isBreakInAlertEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Check if vibrate on failed.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isVibrateOnFailed(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Get lock delay from settings.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLockDelayMs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    /**
     * Invalidate cache.
     */
    public final void invalidateCache() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/soc/agent/security/SecuritySettingsManager$Companion;", "", "()V", "INSTANCE", "Lcom/soc/agent/security/SecuritySettingsManager;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.security.SecuritySettingsManager getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}