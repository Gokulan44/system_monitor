package com.soc.agent.security

import android.content.Context
import com.soc.agent.api.dto.ScanItemDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.util.zip.ZipFile

/**
 * Scans APK files and directories for malware. The primary signal is the
 * SHA-256 of the DEX bytecode (or the whole file when no DEX is present),
 * checked against the local signature DB (EICAR) and the remote IOC blocklist.
 * The final verdict is produced by [ThreatClassifier].
 *
 * @param context app context (reserved for future needs such as package lookup).
 */
class ApkScanner(private val context: Context) {

    /**
     * Scans a single APK. Opens the archive as a [ZipFile], hashes the
     * `classes.dex` entry when present (falling back to hashing the whole file),
     * checks EICAR + blocklist, then classifies via [ThreatClassifier].
     *
     * @return a [ScanItemDto] with kind = "apk".
     */
    suspend fun scanApk(
        file: File,
        iocMatcher: IocMatcher = IocMatcher.current()
    ): ScanItemDto = withContext(Dispatchers.IO) {
        if (!file.isFile || file.length() == 0L) {
            return@withContext cleanIoItem(file, note = "Empty or unreadable APK")
        }

        // Hash the DEX bytecode stream (or the whole file when the APK has no DEX).
        val hash = runCatching {
            ZipFile(file).use { zip ->
                val dexEntry = zip.getEntry("classes.dex")
                if (dexEntry != null && !dexEntry.isDirectory) {
                    zip.getInputStream(dexEntry).use { Sha256.hashStream(it) }
                } else {
                    Sha256.hashFile(file)
                }
            }
        }.getOrNull()

        val (matchName, reason) = resolveMatch(hash, iocMatcher)
        val blocklist = if (hash != null && iocMatcher.checkHash(hash)) setOf(hash.lowercase()) else emptySet()
        val (verdict, severity) = ThreatClassifier.classify(
            sha256 = hash,
            sizeB = file.length(),
            isSystemApp = true,
            hashBlocklist = blocklist
        )

        ScanItemDto(
            kind = "apk",
            item = file.name,
            hash = hash,
            matchName = matchName,
            verdict = verdict,
            severity = severity,
            detail = buildDetail(verdict, severity, reason),
            quarantined = false
        )
    }

    /**
     * Walks a directory up to [maxDepth] levels (default 4), skipping symlinks,
     * and scans every regular file. Non-APK files are hashed and classified too,
     * so this is safe on mixed public directories. Runs on the IO dispatcher.
     */
    suspend fun scanDirectory(
        dir: File,
        iocMatcher: IocMatcher,
        maxDepth: Int = 4
    ): List<ScanItemDto> = withContext(Dispatchers.IO) {
        if (!dir.isDirectory || !dir.canRead()) return@withContext emptyList()
        try {
            dir.walkTopDown()
                .maxDepth(maxDepth)
                .filter { it.isFile && it.canRead() && !isSymlink(it) && it.length() > 0L }
                .map { file ->
                    kotlinx.coroutines.runBlocking {
                        if (file.name.endsWith(".apk", ignoreCase = true)) scanApk(file, iocMatcher)
                        else scanPlainFile(file, iocMatcher)
                    }
                }
                .toList()
        } catch (e: SecurityException) {
            emptyList()
        }
    }

    // --- plain (non-APK) file handling inside a directory scan ---------------
    private suspend fun scanPlainFile(file: File, iocMatcher: IocMatcher): ScanItemDto {
        val hash = runCatching { Sha256.hashFile(file) }.getOrNull()
        val (matchName, reason) = resolveMatch(hash, iocMatcher)
        val blocklist = if (hash != null && iocMatcher.checkHash(hash)) setOf(hash.lowercase()) else emptySet()
        val (verdict, severity) = ThreatClassifier.classify(
            sha256 = hash,
            sizeB = file.length(),
            isSystemApp = false,
            hashBlocklist = blocklist
        )
        return ScanItemDto(
            kind = "file",
            item = file.name,
            hash = hash,
            matchName = matchName,
            verdict = verdict,
            severity = severity,
            detail = buildDetail(verdict, severity, reason),
            quarantined = false
        )
    }

    // --- helpers -------------------------------------------------------------

    /** Resolves the IOC match label and correlated reason for a hash. */
    private fun resolveMatch(hash: String?, iocMatcher: IocMatcher): Pair<String?, String> = when {
        hash == null -> null to "Hash unavailable"
        Eicar.isEicarHash(hash) -> "EICAR test file" to "EICAR test string detected"
        iocMatcher.checkHash(hash) -> "IoC blocklist hash match" to "Hash present in remote IOC blocklist"
        else -> null to ""
    }

    private fun buildDetail(verdict: String, severity: String, reason: String): String = when {
        reason.isNotEmpty() -> reason
        verdict == "malicious" -> "Known-bad hash ($severity severity)"
        verdict == "suspicious" -> "Anomalous artifact ($severity severity)"
        else -> "No threats detected"
    }

    private fun cleanIoItem(file: File, note: String): ScanItemDto =
        ScanItemDto(
            kind = if (file.name.endsWith(".apk", ignoreCase = true)) "apk" else "file",
            item = file.name,
            hash = null,
            matchName = null,
            verdict = "clean",
            severity = "none",
            detail = note,
            quarantined = false
        )

    private fun isSymlink(file: File): Boolean {
        return try {
            Files.isSymbolicLink(file.toPath()) || file.canonicalPath != file.absolutePath
        } catch (e: Exception) {
            false
        }
    }
}