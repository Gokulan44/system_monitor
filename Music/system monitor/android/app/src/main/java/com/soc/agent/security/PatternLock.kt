package com.soc.agent.security

import com.soc.agent.utils.Prefs

/**
 * 3x3 Android-style pattern lock.
 *
 * The grid uses dot indices 0..8 in row-major order:
 *
 *     0  1  2
 *     3  4  5
 *     6  7  8
 *
 * A pattern is a list of dot indices the finger visited. Like the stock
 * Android lock, drawing a line across an unvisited intermediate dot adds that
 * dot to the pattern automatically ([normalize]). Only the SHA-256 hash of the
 * canonical form is persisted (in [Prefs.patternHash]) — never the pattern
 * itself, so a leaked database/prefs file cannot replay the gesture.
 */
object PatternLock {

    /** Minimum number of distinct dots for an acceptable pattern. */
    const val MIN_POINTS = 4

    /** Maximum is always 9 (all dots), enforced by [normalize]. */
    const val MAX_POINTS = 9

    /**
     * Inserts intermediate dots for lines that skip over an unvisited dot,
     * mirroring Android's lock behaviour: 0->2 passes through 1, 0->8 passes
     * through 4, 0->6 passes through 3, etc. Invalid inputs (out-of-range or
     * duplicate dots) are returned unchanged so callers can still detect them.
     */
    fun normalize(raw: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        for (dot in raw) {
            if (dot !in 0..8) return raw
            if (dot in result) return raw
            val prev = result.lastOrNull()
            if (prev != null) {
                intermediate(prev, dot)?.let { mid ->
                    if (mid !in result) result.add(mid)
                }
            }
            result.add(dot)
        }
        return result
    }

    /**
     * Canonical string form used for hashing: dot indices joined with '-',
     * e.g. "0-4-8-7". Two visually identical patterns always produce the same
     * canonical form regardless of finger speed or pauses.
     */
    fun canonical(pattern: List<Int>): String =
        normalize(pattern).joinToString("-")

    /** SHA-256 hex of the canonical pattern (the value stored in prefs). */
    fun hash(pattern: List<Int>): String = Sha256.hashString(canonical(pattern))

    /**
     * True when [pattern] matches the stored unlock pattern. Requires at least
     * [MIN_POINTS] dots and a configured hash.
     */
    fun matches(pattern: List<Int>): Boolean {
        val stored = Prefs.patternHash
        if (stored.isEmpty()) return false
        val normalized = normalize(pattern)
        if (normalized.size < MIN_POINTS) return false
        return hash(normalized) == stored
    }

    /** True when a pattern has been configured (setup completed at least once). */
    fun isConfigured(): Boolean = Prefs.patternHash.isNotEmpty()

    /** Store a new pattern hash, replacing any previous one. */
    fun setPattern(pattern: List<Int>) {
        Prefs.patternHash = hash(normalize(pattern))
        Prefs.lockMethod = "pattern"
    }

    /** Remove the stored pattern (disable pattern gate). */
    fun clearPattern() {
        Prefs.patternHash = ""
        if (Prefs.lockMethod == "pattern") Prefs.lockMethod = ""
    }

    /** Dot directly between [a] and [b] on the 3x3 grid, or null when none. */
    private fun intermediate(a: Int, b: Int): Int? {
        val ax = a % 3; val ay = a / 3
        val bx = b % 3; val by = b / 3
        // Same row with a gap of exactly one column.
        if (ay == by && kotlin.math.abs(bx - ax) == 2) return ay * 3 + 1
        // Same column with a gap of exactly one row.
        if (ax == bx && kotlin.math.abs(by - ay) == 2) return 1 * 3 + ax
        // Diagonal through the centre (0->8, 2->6 and reverse).
        if (kotlin.math.abs(bx - ax) == 2 && kotlin.math.abs(by - ay) == 2) return 4
        return null
    }
}
