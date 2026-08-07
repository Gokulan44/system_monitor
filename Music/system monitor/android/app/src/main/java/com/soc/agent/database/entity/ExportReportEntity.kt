package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Records of exported reports. One row per export operation.
 * Used to track what was exported, when, and where.
 */
@Entity(
    tableName = "export_report",
    indices = [
        Index(value = ["exported_at"])
    ]
)
data class ExportReportEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    /** Export format: "csv", "json", or "pdf". */
    @ColumnInfo(name = "format")
    val format: String,

    /** Export scope: "daily", "weekly", "monthly", "all". */
    @ColumnInfo(name = "scope")
    val scope: String,

    /** Date range start epoch millis. */
    @ColumnInfo(name = "date_from")
    val dateFrom: Long,

    /** Date range end epoch millis. */
    @ColumnInfo(name = "date_to")
    val dateTo: Long,

    /** Output file URI (absolute path). */
    @ColumnInfo(name = "file_uri")
    val fileUri: String,

    /** File size in bytes. */
    @ColumnInfo(name = "file_size_bytes")
    val fileSizeBytes: Long,

    /** Number of records exported. */
    @ColumnInfo(name = "record_count")
    val recordCount: Int,

    /** Exported timestamp. */
    @ColumnInfo(name = "exported_at")
    val exportedAt: Long = System.currentTimeMillis()
) {
    /** Human-readable file size (e.g. "1.2 MB"). */
    fun fileSizeFormatted(): String = when {
        fileSizeBytes < 1024 -> "${fileSizeBytes}B"
        fileSizeBytes < 1_048_576 -> "${fileSizeBytes / 1024}KB"
        else -> String.format("%.1fMB", fileSizeBytes / 1_048_576.0)
    }

    /** Human-readable scope label. */
    fun scopeLabel(): String = when (scope) {
        "daily" -> "Daily Usage"
        "weekly" -> "Weekly Summary"
        "monthly" -> "Monthly Summary"
        "all" -> "All Data"
        else -> scope
    }
}