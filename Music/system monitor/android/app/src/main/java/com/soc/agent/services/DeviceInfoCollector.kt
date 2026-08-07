package com.soc.agent.services

import android.content.Context
import android.os.Build
import android.os.SystemClock
import com.soc.agent.api.dto.DeviceInfoSample
import java.io.File

/**
 * Collects static device identity/OS information. Called once at registration
 * and on each telemetry push (cheap enough for a 15-minute cadence).
 */
object DeviceInfoCollector {

    fun collect(context: Context): DeviceInfoSample {
        val kernel = System.getProperty("os.version")?.takeIf { it.isNotBlank() }
            ?: readKernelVersion()

        val uptimeSec = SystemClock.elapsedRealtime() / 1000L

        return DeviceInfoSample(
            manufacturer = Build.MANUFACTURER,
            model = Build.MODEL,
            kernel = kernel,
            osName = "Android",
            osVersion = Build.VERSION.RELEASE,
            securityPatchLevel = Build.VERSION.SECURITY_PATCH,
            osBuild = Build.DISPLAY,
            uptimeSec = uptimeSec
        )
    }

    private fun readKernelVersion(): String {
        return try {
            val line = File("/proc/version").readText().trim()
            // e.g. "Linux version 4.19.113-g81bb3c1 (build@host) (gcc ...) #1 SMP ..."
            line.substringBefore("(").trim().takeIf { it.isNotBlank() } ?: line.take(64)
        } catch (e: Exception) {
            "unknown"
        }
    }
}