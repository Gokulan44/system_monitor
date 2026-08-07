package com.soc.agent.security

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream
import java.security.MessageDigest

/**
 * SHA-256 hashing helpers. File hashing reads in 64 KB chunks so large APKs can
 * be processed without loading the whole file into memory, and runs on the IO
 * dispatcher because it is a blocking operation.
 */
object Sha256 {

    private const val CHUNK_SIZE = 64 * 1024 // 64 KiB

    /**
     * Computes the lowercase hex SHA-256 of a file's contents.
     *
     * @throws Exception if the file cannot be read.
     */
    suspend fun hashFile(file: File): String = withContext(Dispatchers.IO) {
        val digest = MessageDigest.getInstance("SHA-256")
        file.inputStream().use { input ->
            val buffer = ByteArray(CHUNK_SIZE)
            while (true) {
                val read = input.read(buffer)
                if (read < 0) break
                if (read > 0) digest.update(buffer, 0, read)
            }
        }
        digest.digest().toHex()
    }

    /**
     * Computes the SHA-256 hex of an in-memory byte array (used for DEX entries
     * and small scan targets). Runs on the IO dispatcher for uniformity.
     */
    suspend fun hashBytes(bytes: ByteArray): String = withContext(Dispatchers.IO) {
        MessageDigest.getInstance("SHA-256").digest(bytes).toHex()
    }

    /**
     * Computes the SHA-256 hex of a UTF-8 string (used for PIN hashing and IOC
     * string matching).
     */
    fun hashString(s: String): String =
        MessageDigest.getInstance("SHA-256").digest(s.toByteArray(Charsets.UTF_8)).toHex()

    /** Helpers to compute SHA-256 from any open stream in chunks. */
    suspend fun hashStream(input: InputStream): String = withContext(Dispatchers.IO) {
        val digest = MessageDigest.getInstance("SHA-256")
        input.use { stream ->
            val buffer = ByteArray(CHUNK_SIZE)
            while (true) {
                val read = stream.read(buffer)
                if (read < 0) break
                if (read > 0) digest.update(buffer, 0, read)
            }
        }
        digest.digest().toHex()
    }

    /** Lower-case hex encoding of a byte array. */
    private fun ByteArray.toHex(): String {
        val sb = StringBuilder(size * 2)
        for (b in this) {
            val v = b.toInt() and 0xFF
            sb.append(HEX_CHARS[v ushr 4])
            sb.append(HEX_CHARS[v and 0x0F])
        }
        return sb.toString()
    }

    private val HEX_CHARS = "0123456789abcdef".toCharArray()
}