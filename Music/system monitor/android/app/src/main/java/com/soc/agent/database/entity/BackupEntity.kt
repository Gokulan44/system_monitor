package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Backup record for App Lock settings and locked apps.
 * Used for export/import functionality.
 */
@Entity(tableName = "backups")
data class BackupEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    /** Backup name/label (user-provided or auto-generated). */
    @ColumnInfo(name = "name")
    val name: String = "App Lock Backup",

    /** JSON payload containing all App Lock data. */
    @ColumnInfo(name = "payload")
    val payload: String,

    /** Timestamp when backup was created. */
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    /** Version of the backup format. */
    @ColumnInfo(name = "version")
    val version: Int = 1
)