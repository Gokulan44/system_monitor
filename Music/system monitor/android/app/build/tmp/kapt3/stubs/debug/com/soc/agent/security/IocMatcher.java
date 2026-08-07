package com.soc.agent.security;

/**
 * Case-insensitive substring matcher for Indicators of Compromise (IOCs).
 * Pulled from the SOC server's `blocklist` (domains / urls / hashes) during the
 * periodic policy sync, then checked against network activity and scan results.
 *
 * Matching is deliberately lenient (substring + case-insensitive) so partial
 * matches still flag; this trades a small false-positive rate for detection
 * coverage, which is appropriate for a client-side first-pass filter.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u0006\u0010\u0013\u001a\u00020\fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/soc/agent/security/IocMatcher;", "", "domains", "", "", "urls", "hashes", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "domainsLower", "hashesLower", "urlsLower", "checkDomain", "", "domain", "checkHash", "hash", "checkUrl", "url", "hostOf", "isEmpty", "Companion", "app_debug"})
public final class IocMatcher {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> domains = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> urls = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> hashes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> domainsLower = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> urlsLower = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> hashesLower = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.NotNull()
    private static volatile com.soc.agent.security.IocMatcher global;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.IocMatcher.Companion Companion = null;
    
    public IocMatcher(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> domains, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> urls, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> hashes) {
        super();
    }
    
    /**
     * True when the URL (or any of its parsed domains) matches a blocklist entry.
     */
    public final boolean checkUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return false;
    }
    
    /**
     * True when the domain is a substring of the host portion.
     */
    public final boolean checkDomain(@org.jetbrains.annotations.NotNull()
    java.lang.String domain) {
        return false;
    }
    
    /**
     * True when the hash matches a blocklisted hash (case-insensitive).
     */
    public final boolean checkHash(@org.jetbrains.annotations.NotNull()
    java.lang.String hash) {
        return false;
    }
    
    /**
     * True when this matcher holds no IOCs (all lists empty).
     */
    public final boolean isEmpty() {
        return false;
    }
    
    private final java.lang.String hostOf(java.lang.String url) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006J\u0006\u0010\t\u001a\u00020\u0004J0\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/soc/agent/security/IocMatcher$Companion;", "", "()V", "global", "Lcom/soc/agent/security/IocMatcher;", "blocklistCounts", "", "", "", "current", "updateGlobalBlocklist", "", "domains", "", "urls", "hashes", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Replaces the process-wide IOC snapshot with a freshly synced blocklist.
         * Called by the repository after `pullPolicies()`.
         */
        public final void updateGlobalBlocklist(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> domains, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> urls, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> hashes) {
        }
        
        /**
         * The current process-wide matcher (empty until the first policy sync).
         */
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.security.IocMatcher current() {
            return null;
        }
        
        /**
         * Blocklist sizes keyed for the dashboard: domains / urls / hashes.
         */
        @org.jetbrains.annotations.NotNull()
        public final java.util.Map<java.lang.String, java.lang.Integer> blocklistCounts() {
            return null;
        }
    }
}