package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/** One server policy the agent must enforce locally. */
data class PolicyDto(
    val id: Long,

    val name: String,

    @SerializedName("policy_type")
    val policyType: String = "custom",

    /** Free-form rule map from the server. */
    val rules: Map<String, Any> = emptyMap()
)

/** The server's current IOC blocklist, grouped by kind. */
data class BlocklistDto(
    val domains: List<String> = emptyList(),
    val urls: List<String> = emptyList(),
    val hashes: List<String> = emptyList()
)

/** GET /api/agent/policies response. */
data class PolicyResponse(
    val policies: List<PolicyDto> = emptyList(),
    val blocklist: BlocklistDto? = null,

    @SerializedName("synced_at")
    val syncedAt: String? = null
)

/** GET /api/agent/sync response — heartbeat that also returns policies. */
data class SyncResponse(
    val ok: Boolean = false,

    @SerializedName("server_time")
    val serverTime: String? = null,

    val score: Int = 100,

    val grade: String = "Excellent",

    val policies: List<PolicyDto> = emptyList(),
    val blocklist: BlocklistDto? = null
)