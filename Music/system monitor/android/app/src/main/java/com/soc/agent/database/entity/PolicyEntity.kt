package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * One server-side policy the agent must enforce locally. The primary key is
 * the server's policy id. Mirrors the `device_policies` table on the SOC
 * server (rules arrive as a JSON object and are stored via
 * [com.soc.agent.database.Converters]).
 */
@Entity(tableName = "policies")
data class PolicyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    /** e.g. custom / malware / network / compliance. */
    @ColumnInfo(name = "policy_type")
    val policyType: String = "custom",

    /** Free-form rule map, JSON-encoded in the database. */
    @ColumnInfo(name = "rules")
    val rules: Map<String, Any> = emptyMap(),

    @ColumnInfo(name = "enabled")
    val enabled: Boolean = true,

    /** Epoch millis of the last sync that delivered this policy. */
    @ColumnInfo(name = "synced_at")
    val syncedAt: Long = System.currentTimeMillis()
)
