package com.soc.agent.security;

import com.soc.agent.utils.Prefs;

/**
 * 3x3 Android-style pattern lock.
 *
 * The grid uses dot indices 0..8 in row-major order:
 *
 *    0  1  2
 *    3  4  5
 *    6  7  8
 *
 * A pattern is a list of dot indices the finger visited. Like the stock
 * Android lock, drawing a line across an unvisited intermediate dot adds that
 * dot to the pattern automatically ([normalize]). Only the SHA-256 hash of the
 * canonical form is persisted (in [Prefs.patternHash]) — never the pattern
 * itself, so a leaked database/prefs file cannot replay the gesture.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\f\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tJ\u001f\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u0013\u001a\u00020\u00122\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tJ\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\tJ\u0014\u0010\u0016\u001a\u00020\u000b2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/soc/agent/security/PatternLock;", "", "()V", "MAX_POINTS", "", "MIN_POINTS", "canonical", "", "pattern", "", "clearPattern", "", "hash", "intermediate", "a", "b", "(II)Ljava/lang/Integer;", "isConfigured", "", "matches", "normalize", "raw", "setPattern", "app_debug"})
public final class PatternLock {
    
    /**
     * Minimum number of distinct dots for an acceptable pattern.
     */
    public static final int MIN_POINTS = 4;
    
    /**
     * Maximum is always 9 (all dots), enforced by [normalize].
     */
    public static final int MAX_POINTS = 9;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.PatternLock INSTANCE = null;
    
    private PatternLock() {
        super();
    }
    
    /**
     * Inserts intermediate dots for lines that skip over an unvisited dot,
     * mirroring Android's lock behaviour: 0->2 passes through 1, 0->8 passes
     * through 4, 0->6 passes through 3, etc. Invalid inputs (out-of-range or
     * duplicate dots) are returned unchanged so callers can still detect them.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> normalize(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> raw) {
        return null;
    }
    
    /**
     * Canonical string form used for hashing: dot indices joined with '-',
     * e.g. "0-4-8-7". Two visually identical patterns always produce the same
     * canonical form regardless of finger speed or pauses.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String canonical(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> pattern) {
        return null;
    }
    
    /**
     * SHA-256 hex of the canonical pattern (the value stored in prefs).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String hash(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> pattern) {
        return null;
    }
    
    /**
     * True when [pattern] matches the stored unlock pattern. Requires at least
     * [MIN_POINTS] dots and a configured hash.
     */
    public final boolean matches(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> pattern) {
        return false;
    }
    
    /**
     * True when a pattern has been configured (setup completed at least once).
     */
    public final boolean isConfigured() {
        return false;
    }
    
    /**
     * Store a new pattern hash, replacing any previous one.
     */
    public final void setPattern(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> pattern) {
    }
    
    /**
     * Remove the stored pattern (disable pattern gate).
     */
    public final void clearPattern() {
    }
    
    /**
     * Dot directly between [a] and [b] on the 3x3 grid, or null when none.
     */
    private final java.lang.Integer intermediate(int a, int b) {
        return null;
    }
}