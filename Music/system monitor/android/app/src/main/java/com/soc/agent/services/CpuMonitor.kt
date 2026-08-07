package com.soc.agent.services

import android.content.Context
import com.soc.agent.api.dto.CpuSample
import kotlinx.coroutines.delay
import kotlin.math.max
import java.io.File
import kotlin.math.roundToInt

/**
 * Samples CPU load by diffing /proc/stat over a 300 ms window and reads
 * topology/speed/thermal data from /proc/cpuinfo and /sys/class/thermal.
 *
 * Keeps a rolling history of the last 60 aggregate load values that is included
 * in each sample so the UI/server can render a trend sparkline.
 */
class CpuMonitor(private val context: Context) {

    private val history = ArrayDeque<Double>() // last 60 aggregate load % values

    suspend fun sample(): CpuSample {
        // Measure cumulative CPU counters, wait the delta window, measure again.
        val firstStat = readStat()
        kotlinx.coroutines.delay(300L)
        val secondStat = readStat()

        val currentAggregate = secondStat.first
        val currentPerCore = secondStat.second

        // Compute load % over the 300 ms delta.
        val before = firstStat.first
        val beforeCores = firstStat.second
        var loadPct = loadBetween(before, currentAggregate)
        if (loadPct.isNaN() || loadPct < 0) loadPct = 0.0
        val perCore = if (beforeCores.size == currentPerCore.size) {
            currentPerCore.indices.map { i -> loadBetween(beforeCores[i], currentPerCore[i]).coerceIn(0.0, 100.0) }
        } else {
            currentPerCore.map { 0.0 }
        }

        // Rolling history (last 60).
        history.addLast(loadPct)
        while (history.size > HISTORY_SIZE) history.removeFirst()

        // Topology + thermal.
        val cpuInfo = readCpuInfo()
        val tempC = readThermal()

        return CpuSample(
            loadPct = (loadPct.coerceIn(0.0, 100.0) * 10).roundToInt() / 10.0,
            cores = cpuInfo.second,
            perCore = perCore.map { (it * 10).roundToInt() / 10.0 },
            speedGhz = if (cpuInfo.first > 0) cpuInfo.first / 1000.0 else null,
            tempC = tempC,
            history = history.toList()
        )
    }

    /** Cumulative idle + total CPU time from /proc/stat. */
    private data class Stat(val idle: Long, val total: Long)

    private fun readStat(): Pair<Stat, List<Stat>> {
        val lines = try {
            File("/proc/stat").readLines()
        } catch (e: Exception) {
            return Stat(0, 1) to emptyList()
        }
        var aggregate: Stat? = null
        val cores = mutableListOf<Stat>()
        for (line in lines) {
            if (!line.startsWith("cpu")) continue
            val parts = line.split(Regex("\\s+")).filter { it.isNotEmpty() }
            if (parts.size < 2) continue
            val name = parts[0]
            val nums = parts.drop(1).mapNotNull { it.toLongOrNull() }
            if (nums.isEmpty()) continue
            val total = nums.sum()
            val idle = if (nums.size > 3) nums[3] else 0L // CPUTIME_IOWAIT offset
            val stat = Stat(idle, total)
            if (name == "cpu") aggregate = stat else cores.add(stat)
        }
        return (aggregate ?: Stat(0, 1)) to cores
    }

    private fun loadBetween(a: Stat, b: Stat): Double {
        val totalDelta = b.total - a.total
        if (totalDelta <= 0) return 0.0
        val idleDelta = b.idle - a.idle
        return (1.0 - idleDelta.toDouble() / totalDelta.toDouble()) * 100.0
    }

    /** Returns (maxSpeedMHz, coreCount) parsed from /proc/cpuinfo. */
    private fun readCpuInfo(): Pair<Double, Int> {
        var maxMhz = 0.0
        var cores = 0
        try {
            val lines = File("/proc/cpuinfo").readLines()
            for (line in lines) {
                val idx = line.indexOf(':')
                if (idx < 0) continue
                val key = line.substring(0, idx).trim()
                val value = line.substring(idx + 1).trim()
                when {
                    key == "Processor" && value.contains("MHz") -> {
                        val mhz = extractMhz(value)
                        maxMhz = maxOf(maxMhz, mhz)
                    }
                    key == "cpu MHz" -> maxMhz = maxOf(maxMhz, value.toDoubleOrNull() ?: 0.0)
                    key == "processor" -> cores++
                }
            }
        } catch (e: Exception) {
            // fall through
        }
        // Some vendors omit "processor"; count cores by unique CPU maps fallback.
        if (cores == 0) {
            try {
                cores = Runtime.getRuntime().availableProcessors()
            } catch (e: Exception) {
                cores = 1
            }
        }
        return maxMhz to max(cores, 1)
    }

    private fun extractMhz(raw: String): Double {
        // e.g. "64-bit ARMv8 ... cpu 1"
        val mhzMatches = Regex("(\\d+)\\s*MHz").find(raw)
        return mhzMatches?.groupValues?.get(1)?.toDoubleOrNull() ?: 0.0
    }

    /** Thermal zone temp in Celsius (millidegrees read from sysfs). */
    private fun readThermal(): Double? {
        return try {
            val tempMillis = File("/sys/class/thermal/thermal_zone0/temp").readText().trim().toDoubleOrNull()
                ?: return null
            tempMillis / 1000.0
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val HISTORY_SIZE = 60
    }
}