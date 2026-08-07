package com.soc.agent.security

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.util.Date
import java.util.Locale

/**
 * Manager for App Lock backup and restore functionality.
 * Exports/imports all App Lock data as JSON.
 */
class BackupManager private constructor(context: Context) {

    private val db = AppDatabase.getInstance(context.applicationContext)
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val context = context.applicationContext

    companion object {
        @Volatile
        private var INSTANCE: BackupManager? = null

        fun getInstance(context: Context): BackupManager =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: BackupManager(context.applicationContext).also { INSTANCE = it }
            }
    }

    /** Data structure for backup export. */
    data class BackupData(
        val version: Int = 1,
        val timestamp: Long = System.currentTimeMillis(),
        val lockedApps: List<LockedAppEntity> = emptyList(),
        val unlockHistory: List<UnlockHistoryEntity> = emptyList(),
        val failedAttempts: List<FailedAttemptEntity> = emptyList(),
        val intruderSelfies: List<IntruderSelfieEntity> = emptyList(),
        val securitySettings: SecuritySettingsEntity? = null
    )

    /** Export all App Lock data to a JSON file. */
    suspend fun createBackup(name: String = "App Lock Backup"): BackupEntity = withContext(Dispatchers.IO) {
        val lockedApps = db.lockedAppDao().getAll()
        val unlockHistory = db.unlockHistoryDao().getRecentHistory(1000)
        val failedAttempts = db.failedAttemptDao().getRecentFailed(1000)
        val intruderSelfies = db.intruderSelfieDao().getRecentSelfies(1000)
        val securitySettings = db.securitySettingsDao().getSettings()

        val backupData = BackupData(
            lockedApps = lockedApps,
            unlockHistory = unlockHistory,
            failedAttempts = failedAttempts,
            intruderSelfies = intruderSelfies,
            securitySettings = securitySettings
        )

        val json = gson.toJson(backupData)
        val entity = BackupEntity(
            name = name,
            payload = json,
            createdAt = System.currentTimeMillis(),
            version = 1
        )
        db.backupDao().insert(entity)
        entity
    }

    /** Restore App Lock data from Uri. */
    suspend fun restoreBackupFromUri(uri: Uri): Boolean = withContext(Dispatchers.IO) {
        try {
            val json = context.contentResolver.openInputStream(uri)?.use { it.bufferedReader().readText() } ?: return@withContext false
            val backupData = gson.fromJson(json, BackupData::class.java)
            db.runInTransaction {
                kotlinx.coroutines.runBlocking {
                    db.lockedAppDao().clearAll()
                    db.unlockHistoryDao().deleteAll()
                    if (backupData.lockedApps.isNotEmpty()) {
                        db.lockedAppDao().insertLockedApps(backupData.lockedApps)
                    }
                    if (backupData.unlockHistory.isNotEmpty()) {
                        db.unlockHistoryDao().insertAll(backupData.unlockHistory.toTypedArray())
                    }
                }
            }
            true
        } catch (e: Exception) {
            Log.e("BackupManager", "Restore failed", e)
            false
        }
    }

    /** Export backup to a file (for sharing/external storage). */
    suspend fun exportBackupToFile(backup: BackupEntity): Uri? = withContext(Dispatchers.IO) {
        try {
            val fileName = "soc_agent_backup_${java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}.json"
            val contentValues = android.content.ContentValues().apply {
                put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "application/json")
                put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, android.os.Environment.DIRECTORY_DOWNLOADS + "/SOCAgent/Backups")
            }
            val resolver = context.contentResolver
            val uri = resolver.insert(android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
                ?: return@withContext null

            resolver.openOutputStream(uri).use { outputStream ->
                val writer = OutputStreamWriter(outputStream!!)
                writer.write(backup.payload)
                writer.flush()
            }
            uri
        } catch (e: Exception) {
            Log.e("BackupManager", "Export failed", e)
            null
        }
    }

    /** Import backup from a file URI. */
    suspend fun importBackupFromFile(uri: Uri): BackupEntity? = withContext(Dispatchers.IO) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
                ?: return@withContext null
            val json = inputStream.bufferedReader().readText()
            val backupData = gson.fromJson(json, BackupData::class.java)
            val entity = BackupEntity(
                name = "Imported ${java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}",
                payload = json,
                createdAt = System.currentTimeMillis(),
                version = 1
            )
            db.backupDao().insert(entity)
            entity
        } catch (e: Exception) {
            Log.e("BackupManager", "Import failed", e)
            null
        }
    }

    /** Delete a backup by ID. */
    suspend fun deleteBackup(id: Long) = withContext(Dispatchers.IO) {
        db.backupDao().deleteBackup(id)
    }
}