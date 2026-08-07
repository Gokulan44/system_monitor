package com.soc.agent.services

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Debug
import com.soc.agent.api.dto.AppPermissionDto
import com.soc.agent.api.dto.InstalledAppDto
import com.soc.agent.utils.Formatters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.security.MessageDigest

/**
 * Builds a full inventory of installed applications for the SOC server,
 * including a fingerprint of each app's signing certificate and its runtime
 * memory footprint (when a live process is observable). Runs on the IO dispatcher
 * because PackageManager queries can be slow with many apps.
 */
class AppInventory(private val context: Context) {

    suspend fun collect(): List<InstalledAppDto> = withContext(Dispatchers.IO) {
        val pm = context.packageManager
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        val flags = PackageManager.MATCH_ALL or PackageManager.GET_PERMISSIONS

        val applications = runCatching { pm.getInstalledApplications(flags) }.getOrElse { emptyList() }
        val runningPids = runningProcessPids()

        applications.mapNotNull { ai ->
            val pkg = ai.packageName
            if (pkg.isNullOrEmpty()) return@mapNotNull null

            val label = runCatching { pm.getApplicationLabel(ai).toString() }.getOrElse { pkg }
            val pid = runningPids[pkg]

            var memB: Long? = null
            if (pid != null) {
                runCatching {
                    val memInfos = am.getProcessMemoryInfo(intArrayOf(pid))
                    if (memInfos.isNotEmpty()) {
                        memB = memInfos[0].totalPss.toLong() * 1024L
                    }
                }
            }
            // Per-process CPU% needs a sampling daemon we deliberately avoid;
            // cpuPct stays null for the server to estimate once it has deltas.

            InstalledAppDto(
                packageName = pkg,
                name = label,
                pid = pid,
                cpuPct = 0.0,
                memB = memB ?: 0L,
                status = "installed",
                risk = "unknown",
                signature = Formatters.shortHash(signingFingerprint(pm, pkg) ?: "n/a"),
                permissions = permissionsFor(pm, pkg)
            )
        }
    }

    // --- Running process lookup ----------------------------------------------
    private fun runningProcessPids(): Map<String, Int> {
        val result = mutableMapOf<String, Int>()
        runCatching {
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (proc in am.runningAppProcesses ?: return@runCatching) {
                if (proc.pid > 0 && !proc.processName.isNullOrBlank()) {
                    result[proc.processName] = proc.pid
                }
            }
        }
        return result
    }

    /** Signature fingerprint: SHA-256 hex of the signing certificate. */
    private fun signingFingerprint(pm: PackageManager, pkg: String): String? {
        val certBytes: ByteArray? = try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val info = pm.getPackageInfo(pkg, PackageManager.GET_SIGNING_CERTIFICATES)
                info.signingInfo?.apkContentsSigners?.firstOrNull()?.toByteArray()
            } else {
                @Suppress("DEPRECATION")
                val info = pm.getPackageInfo(pkg, PackageManager.GET_SIGNATURES)
                info.signatures?.firstOrNull()?.toByteArray()
            }
        } catch (e: Exception) {
            null
        } ?: return null
        return MessageDigest.getInstance("SHA-256")
            .digest(certBytes)
            .joinToString("") { "%02x".format(it) }
    }

    // --- Permissions ----------------------------------------------------------
    private fun permissionsFor(pm: PackageManager, pkg: String): List<AppPermissionDto> {
        val info = runCatching { pm.getPackageInfo(pkg, PackageManager.GET_PERMISSIONS) }.getOrNull()
            ?: return emptyList()
        val requested = info.requestedPermissions ?: return emptyList()
        val flags = info.requestedPermissionsFlags ?: IntArray(0)

        val result = mutableListOf<AppPermissionDto>()
        for (i in requested.indices) {
            val permission = requested[i] ?: continue
            val granted = i < flags.size && (flags[i] and PackageManager.PERMISSION_GRANTED) != 0
            result.add(
                AppPermissionDto(
                    name = permission,
                    category = categoryOf(permission),
                    granted = granted
                )
            )
        }
        return result
    }

    /** Maps a permission string to a concise category for the SOC dashboard. */
    private fun categoryOf(permission: String): String {
        val p = permission.lowercase()
        return when {
            p.contains("location") -> "location"
            p.contains("storage") || p.contains("external_storage") -> "storage"
            p.contains("camera") -> "camera"
            p.contains("microphone") || p.contains("record_audio") -> "microphone"
            p.contains("contacts") || p.contains("phone_book") -> "contacts"
            p.contains("sms") -> "sms"
            p.contains("phone") || p.contains("call_phone") -> "phone"
            else -> "other"
        }
    }

    companion object {
        /**
         * Synchronous convenience entry point used by the UI (already on an IO
         * dispatcher): collects the full inventory in a single call.
         */
        fun scan(context: Context): List<InstalledAppDto> =
            runBlocking(Dispatchers.IO) { AppInventory(context).collect() }
    }
}