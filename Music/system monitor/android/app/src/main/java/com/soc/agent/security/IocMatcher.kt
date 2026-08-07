package com.soc.agent.security

/**
 * Case-insensitive substring matcher for Indicators of Compromise (IOCs).
 * Pulled from the SOC server's `blocklist` (domains / urls / hashes) during the
 * periodic policy sync, then checked against network activity and scan results.
 *
 * Matching is deliberately lenient (substring + case-insensitive) so partial
 * matches still flag; this trades a small false-positive rate for detection
 * coverage, which is appropriate for a client-side first-pass filter.
 */
class IocMatcher(
    private val domains: List<String>,
    private val urls: List<String>,
    private val hashes: List<String>
) {

    private val domainsLower = domains.map { it.trim().lowercase() }.filter { it.isNotEmpty() }
    private val urlsLower = urls.map { it.trim().lowercase() }.filter { it.isNotEmpty() }
    private val hashesLower = hashes.map { it.trim().lowercase() }.filter { it.isNotEmpty() }

    /** True when the URL (or any of its parsed domains) matches a blocklist entry. */
    fun checkUrl(url: String): Boolean {
        val u = url.trim().lowercase()
        if (u.isEmpty()) return false
        if (checkDomain(u)) return true
        // Direct substring against URL entries.
        return urlsLower.any { it in u }
    }

    /** True when the domain is a substring of the host portion. */
    fun checkDomain(domain: String): Boolean {
        val d = domain.trim().lowercase()
        if (d.isEmpty()) return false
        val host = hostOf(d)
        return domainsLower.any { it in host || host in it }
    }

    /** True when the hash matches a blocklisted hash (case-insensitive). */
    fun checkHash(hash: String): Boolean {
        val h = hash.trim().lowercase()
        if (h.isEmpty()) return false
        return hashesLower.any { it == h || (h.length > 8 && it in h) }
    }

    /** True when this matcher holds no IOCs (all lists empty). */
    fun isEmpty(): Boolean = domainsLower.isEmpty() && urlsLower.isEmpty() && hashesLower.isEmpty()

    private fun hostOf(url: String): String {
        var s = url
        val schemeIdx = s.indexOf("://")
        if (schemeIdx >= 0) s = s.substring(schemeIdx + 3)
        val slash = s.indexOf('/')
        if (slash >= 0) s = s.substring(0, slash)
        val at = s.lastIndexOf('@')
        if (at >= 0) s = s.substring(at + 1)
        val colon = s.indexOf(':')
        if (colon >= 0) s = s.substring(0, colon)
        return s
    }

    companion object {
        @Volatile
        private var global: IocMatcher = IocMatcher(emptyList(), emptyList(), emptyList())

        /**
         * Replaces the process-wide IOC snapshot with a freshly synced blocklist.
         * Called by the repository after `pullPolicies()`.
         */
        fun updateGlobalBlocklist(domains: List<String>, urls: List<String>, hashes: List<String>) {
            global = IocMatcher(domains, urls, hashes)
        }

        /** The current process-wide matcher (empty until the first policy sync). */
        fun current(): IocMatcher = global

        /** Blocklist sizes keyed for the dashboard: domains / urls / hashes. */
        fun blocklistCounts(): Map<String, Int> = mapOf(
            "domains" to global.domains.size,
            "urls" to global.urls.size,
            "hashes" to global.hashes.size
        )
    }
}