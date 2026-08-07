package com.soc.agent.security;

import java.net.URI;

/**
 * Heuristic URL risk scoring for on-device phishing triage.
 *
 * Scores are additive; the final verdict buckets the total:
 *  - score >= 80  -> "malicious"
 *  - score >= 40  -> "suspicious"
 *  - otherwise    -> "safe"
 *
 * The heuristic covers common phishing tells: missing TLS, bare-IP hosts,
 * credential "@"-style embeds, long numeric hostnames, suspicious TLDs,
 * IDN/punycode hostnames, and direct hits against the remote IOC blocklist.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015J\"\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\u00172\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/security/PhishingChecker;", "", "()V", "IPV4", "Lkotlin/text/Regex;", "MALICIOUS_THRESHOLD", "", "NUMERIC_TOKEN", "SUSPICIOUS_THRESHOLD", "SUSPICIOUS_TLDS", "", "", "extractHost", "url", "isIpv4", "", "host", "schemeOf", "score", "Lcom/soc/agent/security/PhishingResult;", "blocklist", "Lcom/soc/agent/security/IocMatcher;", "scoreUrl", "Lkotlin/Pair;", "app_debug"})
public final class PhishingChecker {
    private static final double MALICIOUS_THRESHOLD = 80.0;
    private static final double SUSPICIOUS_THRESHOLD = 40.0;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> SUSPICIOUS_TLDS = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex IPV4 = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex NUMERIC_TOKEN = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.PhishingChecker INSTANCE = null;
    
    private PhishingChecker() {
        super();
    }
    
    /**
     * Compact form returning only the (verdict, score) pair.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.String, java.lang.Double> scoreUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    com.soc.agent.security.IocMatcher blocklist) {
        return null;
    }
    
    /**
     * Full check returning verdict, score and the list of contributing reasons.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.security.PhishingResult score(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    com.soc.agent.security.IocMatcher blocklist) {
        return null;
    }
    
    private final java.lang.String schemeOf(java.lang.String url) {
        return null;
    }
    
    private final java.lang.String extractHost(java.lang.String url) {
        return null;
    }
    
    private final boolean isIpv4(java.lang.String host) {
        return false;
    }
}