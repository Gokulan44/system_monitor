package com.soc.agent.security;

/**
 * Deterministic threat classification of a scanned artifact.
 *
 * Logic (first match wins):
 * 1. EICAR hash                     -> (malicious, high)
 * 2. hash present in blocklist      -> (malicious, medium)
 * 3. anomalous size / system hint   -> (suspicious, low)
 * 4. otherwise                      -> (clean, none)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J:\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eJ\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/soc/agent/security/ThreatClassifier;", "", "()V", "TINY_SYSTEM_APK", "", "VERY_LARGE_FILE", "classify", "Lkotlin/Pair;", "", "sha256", "sizeB", "isSystemApp", "", "hashBlocklist", "", "isEicarHash", "hash", "app_debug"})
public final class ThreatClassifier {
    private static final long VERY_LARGE_FILE = 157286400L;
    private static final long TINY_SYSTEM_APK = 51200L;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.ThreatClassifier INSTANCE = null;
    
    private ThreatClassifier() {
        super();
    }
    
    /**
     * @param sha256 the SHA-256 hex of the artifact (may be null when hashing failed).
     * @param sizeB  size of the artifact in bytes.
     * @param isSystemApp whether the artifact belongs to a privileged/system app.
     * @param hashBlocklist remote IOC blocklist hashes (lowercase).
     * @return a [Pair] of (verdict, severity): e.g. ("malicious", "high").
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.String, java.lang.String> classify(@org.jetbrains.annotations.Nullable()
    java.lang.String sha256, long sizeB, boolean isSystemApp, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> hashBlocklist) {
        return null;
    }
    
    private final boolean isEicarHash(java.lang.String hash) {
        return false;
    }
}