package com.soc.agent.services

import android.content.Context
import android.os.Environment
import com.soc.agent.api.dto.ScanItemDto
import com.soc.agent.api.dto.ScanRequest
import com.soc.agent.security.ApkScanner
import com.soc.agent.security.IocMatcher
import java.io.File
import java.time.Instant

/**
 * Coordinates on-device malware scans and packages the results into a
 * [ScanRequest] for the SOC server. Quick scan targets installed APKs plus the
 * top level of the usual public directories; the full scan additionally attempts
 * private app data and read-only system dirs (best-effort).
 */
class ScanService(private val context: Context) {

    private val apkScanner = ApkScanner(context)

    /**
     * Quick scan: installed APKs under /data/app plus the top-level contents of
     * the public Downloads and DCIM directories.
     */
    suspend fun quickScan(iocMatcher: IocMatcher): ScanRequest {
        val startedAt = Instant.now()
        val items = mutableListOf<ScanItemDto>()

        // Installed APK store.
        items += apkScanner.scanDirectory(File("/data/app"), iocMatcher)

        // Public top-level files (depth 1 to keep it quick).
        val publicDirs = listOfNotNull(
            safePublicDir(Environment.DIRECTORY_DOWNLOADS),
            safePublicDir(Environment.DIRECTORY_DCIM)
        )
        for (dir in publicDirs) {
            items += apkScanner.scanDirectory(dir, iocMatcher, maxDepth = 1)
        }

        return buildRequest("quick", startedAt, items)
    }

    /**
     * Full scan: quick scan targets plus private app data dirs (best-effort) and
     * read-only system directories. Likely to hit permission bumps — those are
     * skipped gracefully rather than treated as failures.
     */
    suspend fun fullScan(iocMatcher: IocMatcher): ScanRequest {
        val startedAt = Instant.now()
        val items = quickScan(iocMatcher).items.toMutableList()

        // System dirs — read-only, may require root. Best-effort.
        val systemDirs = listOf(
            File("/system"),
            File("/vendor"),
            File("/data/local/tmp")
        ).filter { it.isDirectory }

        for (dir in systemDirs) {
            items += apkScanner.scanDirectory(dir, iocMatcher, maxDepth = 2)
        }

        // Private app data dirs for apps we can read (usually only self).
        runCatching {
            val appDir = File(context.getApplicationInfo().dataDir)
            if (appDir.isDirectory) {
                items += apkScanner.scanDirectory(appDir, iocMatcher, maxDepth = 3)
            }
        }

        return buildRequest("full", startedAt, items)
    }

    // --- helpers -------------------------------------------------------------
    private fun safePublicDir(directoryType: String): File? {
        return try {
            val dir = Environment.getExternalStoragePublicDirectory(directoryType)
            if (dir.isDirectory) dir else null
        } catch (e: Exception) {
            null
        }
    }

    private fun buildRequest(
        scanType: String,
        startedAt: Instant,
        items: List<ScanItemDto>
    ): ScanRequest {
        val threats = items.count { it.verdict == "malicious" || it.verdict == "suspicious" }
        return ScanRequest(
            scanType = scanType,
            status = "completed",
            itemsScanned = items.size,
            threatsFound = threats,
            startedAt = startedAt.toString(),
            finishedAt = Instant.now().toString(),
            items = items
        )
    }
}