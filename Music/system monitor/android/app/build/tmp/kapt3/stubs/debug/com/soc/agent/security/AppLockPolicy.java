package com.soc.agent.security;

import android.content.Context;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.utils.Prefs;

/**
 * Decision engine for the App Lock module.
 *
 * Centralises "should this foreground app be gated right now?" so the
 * watcher service ([com.soc.agent.services.AppLockService]) and the dashboard
 * agree on one rule set. A package needs a gate when:
 * - the module is enabled ([Prefs.appLockEnabled]),
 * - the package is in the enabled locked set, and
 * - it isn't excluded (_EXCLUDED cover the agent itself, the launcher and a
 *   small hard-coded set that must never trap the user).
 *
 * This is pure logic — no blocking I/O beyond the cached DAO queries — and is
 * safe to call from a service polling loop.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/soc/agent/security/AppLockPolicy;", "", "()V", "RESERVED", "", "", "getRESERVED", "()Ljava/util/Set;", "gateFor", "isConfigured", "", "loadEnabledPackages", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldGate", "packageName", "enabledPackages", "app_debug"})
public final class AppLockPolicy {
    
    /**
     * Packages that must never be gated (would lock the user out of core UI).
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> RESERVED = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.AppLockPolicy INSTANCE = null;
    
    private AppLockPolicy() {
        super();
    }
    
    /**
     * Packages that must never be gated (would lock the user out of core UI).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getRESERVED() {
        return null;
    }
    
    /**
     * True when the given foreground package currently needs a lock gate.
     * [enabledPackages] is the pre-fetched locked set (hot-set) to avoid a DB
     * hit on every poll; passing it keeps the watcher cheap.
     */
    public final boolean shouldGate(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> enabledPackages) {
        return false;
    }
    
    /**
     * The gate type to present: "pin", "pattern", "password", "biometric", or null when no usable gate
     * is configured. Falls back to pin when a pattern was the selection but
     * none is stored (and vice versa).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String gateFor() {
        return null;
    }
    
    /**
     * True when any unlock gate (PIN, pattern, password or biometric) is configured.
     */
    public final boolean isConfigured() {
        return false;
    }
    
    /**
     * Loads the hot-set of enabled locked package names for the watcher.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadEnabledPackages(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Set<java.lang.String>> $completion) {
        return null;
    }
}