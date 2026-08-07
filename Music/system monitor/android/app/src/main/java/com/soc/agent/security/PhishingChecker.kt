package com.soc.agent.security

import java.net.URI

/** Result of a phishing score check. */
data class PhishingResult(
    val verdict: String,      // "safe" | "suspicious" | "malicious"
    val score: Double,        // 0..100 heuristic risk score
    val reasons: List<String> // human-readable justifications
)

/**
 * Heuristic URL risk scoring for on-device phishing triage.
 *
 * Scores are additive; the final verdict buckets the total:
 *   - score >= 80  -> "malicious"
 *   - score >= 40  -> "suspicious"
 *   - otherwise    -> "safe"
 *
 * The heuristic covers common phishing tells: missing TLS, bare-IP hosts,
 * credential "@"-style embeds, long numeric hostnames, suspicious TLDs,
 * IDN/punycode hostnames, and direct hits against the remote IOC blocklist.
 */
object PhishingChecker {

    private const val MALICIOUS_THRESHOLD = 80.0
    private const val SUSPICIOUS_THRESHOLD = 40.0

    private val SUSPICIOUS_TLDS = setOf(
        ".xyz", ".tk", ".ml", ".ga", ".cf", ".gq", ".top", ".club", ".work",
        ".download", ".stream", ".click", ".link", ".pro", ".info", ".biz",
        ".zip", ".mov", ".review", ".rest", ".icu", ".monster"
    )

    private val IPV4 = Regex("""^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$""")
    private val NUMERIC_TOKEN = Regex("""^[0-9]{4,}$""")

    /** Compact form returning only the (verdict, score) pair. */
    fun scoreUrl(url: String, blocklist: IocMatcher): Pair<String, Double> {
        val result = score(url, blocklist)
        return result.verdict to result.score
    }

    /** Full check returning verdict, score and the list of contributing reasons. */
    fun score(url: String, blocklist: IocMatcher): PhishingResult {
        val reasons = mutableListOf<String>()
        var score = 0.0

        val trimmed = url.trim()
        val uri = try { URI(trimmed.lowercase()) } catch (e: Exception) { null }
        val rawHost = uri?.host ?: extractHost(trimmed)
        val scheme = uri?.scheme ?: schemeOf(trimmed)

        // --- Hard blocklist hit: immediate malicious. ---
        if (blocklist.checkUrl(trimmed) || blocklist.checkDomain(rawHost)) {
            reasons.add("URL or host matches the SOC IOC blocklist")
            score += 100.0
        }

        // --- Scheme. ---
        when (scheme) {
            "https" -> { /* secure transport, no penalty */ }
            "http" -> {
                reasons.add("Uses unencrypted HTTP instead of HTTPS")
                score += 10.0
            }
            else -> {
                if (scheme.isNotEmpty()) {
                    reasons.add("Uses non-standard scheme '$scheme'")
                    score += 15.0
                }
            }
        }

        // --- Bare IP address as host. ---
        if (isIpv4(rawHost)) {
            reasons.add("Host is a raw IP address ($rawHost), not a domain")
            score += 40.0
        }

        // --- "@" in URL, indicating a credential-phishing style embed. ---
        if ('@' in trimmed) {
            reasons.add("URL contains '@', hinting at a lookalike host embed")
            score += 40.0
        }

        // --- Long all-numeric hostname token. ---
        val firstToken = rawHost.split('.')[0]
        if (firstToken.isNotEmpty() && NUMERIC_TOKEN.matches(firstToken)) {
            reasons.add("Hostname begins with a long numeric token")
            score += 25.0
        }

        // --- Suspicious / disposable TLDs. ---
        val hostLower = rawHost.lowercase()
        val tldHit = SUSPICIOUS_TLDS.firstOrNull { hostLower.endsWith(it) }
        if (tldHit != null) {
            reasons.add("Uses revenue- or abuse-heavy TLD '$tldHit'")
            score += 25.0
        }

        // --- IDN / punycode host. ---
        if (hostLower.contains("xn--")) {
            reasons.add("Host uses Internationalized Domain Name encoding (punycode)")
            score += 20.0
        }

        score = score.coerceIn(0.0, 100.0)
        if (reasons.isEmpty()) reasons.add("No phishing indicators observed")

        val verdict = when {
            score >= MALICIOUS_THRESHOLD -> "malicious"
            score >= SUSPICIOUS_THRESHOLD -> "suspicious"
            else -> "safe"
        }
        return PhishingResult(verdict, score, reasons)
    }

    private fun schemeOf(url: String): String {
        val idx = url.indexOf("://")
        return if (idx > 0) url.substring(0, idx).lowercase() else ""
    }

    private fun extractHost(url: String): String {
        var s = url.trim()
        val at = s.lastIndexOf('@')
        if (at >= 0) s = s.substring(at + 1)
        val schemeIdx = s.indexOf("://")
        if (schemeIdx >= 0) s = s.substring(schemeIdx + 3)
        val slash = s.indexOf('/')
        if (slash >= 0) s = s.substring(0, slash)
        val colon = s.indexOf(':')
        if (colon >= 0) s = s.substring(0, colon)
        return s
    }

    private fun isIpv4(host: String): Boolean {
        val m = IPV4.matchEntire(host) ?: return false
        for (i in 1..4) {
            val octet = m.groupValues[i].toIntOrNull() ?: return false
            if (octet < 0 || octet > 255) return false
        }
        return true
    }
}