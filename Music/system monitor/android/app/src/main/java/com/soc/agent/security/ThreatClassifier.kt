package com.soc.agent.security

/**
 * Deterministic threat classification of a scanned artifact.
 *
 * Logic (first match wins):
 *  1. EICAR hash                     -> (malicious, high)
 *  2. hash present in blocklist      -> (malicious, medium)
 *  3. anomalous size / system hint   -> (suspicious, low)
 *  4. otherwise                      -> (clean, none)
 */
object ThreatClassifier {

    // Thresholds that mark an artifact as "suspicious" in isolation.
    private const val VERY_LARGE_FILE = 150L * 1024 * 1024 // >150 MB
    private const val TINY_SYSTEM_APK = 50L * 1024          // <50 KB for a system app

    /**
     * @param sha256 the SHA-256 hex of the artifact (may be null when hashing failed).
     * @param sizeB  size of the artifact in bytes.
     * @param isSystemApp whether the artifact belongs to a privileged/system app.
     * @param hashBlocklist remote IOC blocklist hashes (lowercase).
     * @return a [Pair] of (verdict, severity): e.g. ("malicious", "high").
     */
    fun classify(
        sha256: String?,
        sizeB: Long,
        isSystemApp: Boolean,
        hashBlocklist: Set<String>
    ): Pair<String, String> {
        val hash = sha256?.trim()?.lowercase()

        // 1. EICAR overrides everything else.
        if (isEicarHash(hash)) return "malicious" to "high"

        // 2. Known-bad blocklist hit (remote IOCs).
        if (hash != null && hashBlocklist.contains(hash)) return "malicious" to "medium"

        // 3. Anomaly heuristics.
        val anomalousSize = sizeB > VERY_LARGE_FILE
        val systemAnomaly = isSystemApp && sizeB in 1..TINY_SYSTEM_APK
        if (anomalousSize || systemAnomaly) return "suspicious" to "low"

        // 4. Nothing triggered.
        return "clean" to "none"
    }

    private fun isEicarHash(hash: String?): Boolean = Eicar.isEicarHash(hash)
}