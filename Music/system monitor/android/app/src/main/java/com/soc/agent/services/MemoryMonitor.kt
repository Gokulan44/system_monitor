package com.soc.agent.services

import android.content.Context
import com.soc.agent.api.dto.MemorySample
import java.io.File

/**
 * Samples memory usage from /proc/meminfo.
 *
 * Used memory is computed as: total - free - buffers - cached, which reflects
 * the memory that genuinely cannot be reclaimed without pressure. The free value
 * reported includes the reclaimable cache so the three numbers are self-consistent.
 */
class MemoryMonitor(private val context: Context) {

    fun sample(): MemorySample {
        val info = readMemInfo()

        val totalB = info["MemTotal"] ?: 0L
        val freeB = info["MemFree"] ?: 0L
        val buffersB = info["Buffers"] ?: 0L
        val cachedB = info["Cached"] ?: 0L
        val swapTotalB = info["SwapTotal"] ?: 0L
        val swapFreeB = info["SwapFree"] ?: 0L

        val usedB = (totalB - freeB - buffersB - cachedB).coerceAtLeast(0L)
        val usagePct = if (totalB > 0) (usedB.toDouble() / totalB.toDouble()) * 100.0 else 0.0
        // "Free" shown to users = free memory plus reclaimable cache.
        val freeReported = (freeB + buffersB + cachedB).coerceAtLeast(0L)
        val swapUsedB = (swapTotalB - swapFreeB).coerceAtLeast(0L)

        return MemorySample(
            totalB = totalB,
            usedB = usedB,
            freeB = freeReported,
            usagePct = usagePct,
            swapTotalB = swapTotalB,
            swapUsedB = swapUsedB
        )
    }

    /** Parses /proc/meminfo key/value pairs; values are already in KiB -> bytes. */
    private fun readMemInfo(): Map<String, Long> {
        val result = mutableMapOf<String, Long>()
        try {
            val lines = File("/proc/meminfo").readLines()
            for (line in lines) {
                val idx = line.indexOf(':')
                if (idx < 0) continue
                val key = line.substring(0, idx).trim()
                val rest = line.substring(idx + 1).trim()
                val kb = Regex("""(\d+)""").find(rest)?.groupValues?.get(1)?.toLongOrNull() ?: continue
                result[key] = kb * 1024 // KiB -> bytes
            }
        } catch (e: Exception) {
            // Not available (won't happen on Android, but stay safe).
        }
        return result
    }
}