package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/**
 * Generic OK/error envelope returned by endpoints that only acknowledge.
 * The server's telemetry route returns `{ ok, received_at }` — the extra
 * `received_at` is intentionally ignored, [message] stays null there.
 */
data class GenericResponse(
    val ok: Boolean = false,
    val message: String? = null
)

/**
 * Count ack for /apps, /files and /alerts.
 * The server returns `{ ok, synced }` for apps/files but `{ ok, pushed }`
 * for alerts, so both fields are decoded and one is populated by Gson.
 */
data class SyncCountResponse(
    val ok: Boolean = false,

    /** /apps and /files. */
    val synced: Int = 0,

    /** /alerts. */
    @SerializedName("pushed")
    val pushed: Int = 0
)

/** Ack for POST /api/agent/network with per-section counts. */
data class NetworkAckResponse(
    val ok: Boolean = false,
    val networks: Int = 0,
    val phishing: Int = 0
)