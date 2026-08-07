package com.soc.agent.utils;

import java.util.Locale;

/**
 * Formatting helpers shared by the UI layer and the notification dispatcher.
 * All functions are null-tolerant so they can be applied directly to DTO fields.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\b\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\tJ\u0015\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\tJ\u0015\u0010\u0014\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\nJ\u0015\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/soc/agent/utils/Formatters;", "", "()V", "GB", "", "KB", "MB", "TB", "bytes", "", "(Ljava/lang/Long;)Ljava/lang/String;", "isoNow", "pct", "value", "", "(Ljava/lang/Double;)Ljava/lang/String;", "relativeTime", "epochMillis", "shortHash", "hash", "timeAgo", "uptime", "seconds", "app_debug"})
public final class Formatters {
    private static final long KB = 1024L;
    private static final long MB = 1048576L;
    private static final long GB = 1073741824L;
    private static final long TB = 1099511627776L;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.utils.Formatters INSTANCE = null;
    
    private Formatters() {
        super();
    }
    
    /**
     * Formats a byte count into a human readable string: 512 B, 2.4 KB, 1.2 GB...
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String bytes(@org.jetbrains.annotations.Nullable()
    java.lang.Long bytes) {
        return null;
    }
    
    /**
     * Formats a percentage value (0..100). Values outside the range are clamped.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String pct(@org.jetbrains.annotations.Nullable()
    java.lang.Double value) {
        return null;
    }
    
    /**
     * Formats an epoch-millis timestamp as a short relative time ("just now",
     * "5m ago", "3h ago", "2d ago"). Returns "--" for 0/null inputs.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String timeAgo(@org.jetbrains.annotations.Nullable()
    java.lang.Long epochMillis) {
        return null;
    }
    
    /**
     * Returns a short, safe-to-display prefix of a hex hash (first 8 chars).
     * Used for signatures and hashes in the UI.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String shortHash(@org.jetbrains.annotations.Nullable()
    java.lang.String hash) {
        return null;
    }
    
    /**
     * Formats an uptime in seconds as a compact human string ("45s", "12m",
     * "3h 20m", "2d 5h"). Returns "--" for null/<=0 inputs.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String uptime(@org.jetbrains.annotations.Nullable()
    java.lang.Long seconds) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String isoNow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String relativeTime(@org.jetbrains.annotations.Nullable()
    java.lang.Long epochMillis) {
        return null;
    }
}