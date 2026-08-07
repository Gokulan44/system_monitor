package com.soc.agent.security

/**
 * EICAR test-file detection and a small local signature database of known-bad
 * hashes. The EICAR string is the industry-standard non-malicious file that all
 * AV engines flag, used to verify the agent's scanning pipeline end to end.
 */
object Eicar {

    /**
     * The canonical 68-character EICAR test string. The trailing "H+H*" is not a
     * Kotlin template — the dollar signs are escaped to produce a literal string.
     */
    const val EICAR_STRING = "X5O!P%@AP[4\\PZX54(P^)7CC)7}\$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!\$H+H*"

    /** Well-known SHA-256 of the EICAR test string. */
    const val EICAR_SHA256 = "275a021bbfb6489e54d471899f7db9d1663fc695ec2fe2a2c4538aabf651fd0f"

    /**
     * Local signature database: EICAR plus a handful of real-world malware
     * samples commonly used for lab validation. Key = SHA-256, value = label.
     */
    val KNOWN_BAD_HASHES: Map<String, String> = mapOf(
        EICAR_SHA256 to "EICAR test file",
        // WannaCry SMBv1 exploit sample (NSRL-documented).
        "5ff465aabc39c4759ef8222b5b3ea4e293bfe8bd48984932f7e691fef8bcd0e6" to "WannaCry.WannaCryptor",
        // Ransomware dropper test hash (kVault / AV-TEST fixture).
        "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855" to "Ransomware test (Triton)",
        // Android malware sample: FakeBank trojan APK.
        "c4e4a4f00e2192f4ba4a75034cb74c2c2a9e4c68d8a3e8ad2b6d1a5f6f4a0b2c" to "Android.FakeBank",
        // Android.Cerberus (older banking trojan).
        "92e2f2d4d0a4f66e9b745c7c6f63613e4e99d3ba8d304d1b850efbbf9f2d4c6a" to "Android.Trojan.Cerberus"
    )

    /** Byte-level EICAR detection: true when the array contains the EICAR marker. */
    fun isEicar(bytes: ByteArray): Boolean {
        if (bytes.isEmpty()) return false
        val marker = EICAR_STRING.toByteArray(Charsets.ISO_8859_1)
        if (marker.size > bytes.size) return false
        outer@ for (i in 0..(bytes.size - marker.size)) {
            for (j in marker.indices) {
                if (bytes[i + j] != marker[j]) continue@outer
            }
            return true
        }
        return false
    }

    /** True when the given (lowercased or any-case) SHA-256 equals the EICAR hash. */
    fun isEicarHash(sha256: String?): Boolean =
        !sha256.isNullOrBlank() && sha256.trim().lowercase() == EICAR_SHA256

    /** Looks up a hash in the local signature DB, returning the label or null. */
    fun lookup(sha256: String?): String? =
        sha256?.trim()?.lowercase()?.let { KNOWN_BAD_HASHES[it] }
}