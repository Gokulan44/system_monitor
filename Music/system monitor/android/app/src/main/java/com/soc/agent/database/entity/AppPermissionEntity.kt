package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * A granted/declared permission grouped by category for one app.
 * Mirrors the `app_permissions` table on the SOC server, extended with the
 * owning package name so permissions can be queried per app locally.
 */
@Entity(
    tableName = "app_permissions",
    indices = [Index(value = ["device_id", "package_name"])]
)
data class AppPermissionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    /** Permission group, e.g. "dangerous", "location", "sms". */
    @ColumnInfo(name = "category")
    val category: String = "other",

    @ColumnInfo(name = "granted")
    val granted: Boolean = false,

    /** Permission identifier, e.g. android.permission.CAMERA. */
    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis()
)
