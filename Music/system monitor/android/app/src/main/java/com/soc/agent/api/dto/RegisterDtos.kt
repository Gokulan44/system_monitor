package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/**
 * POST /api/agent/register payload. The server upserts the device row keyed
 * by the stable [agentId] and replies with the server-side device id.
 */
data class RegisterRequest(
    @SerializedName("agent_id")
    val agentId: String,

    val manufacturer: String,

    val model: String,

    /** Human-friendly device name shown on the SOC dashboard. */
    val name: String,

    @SerializedName("android_version")
    val androidVersion: String,

    @SerializedName("app_version")
    val appVersion: String
)

/**
 * POST /api/agent/register response.
 * [registered] is true only on the very first registration; re-registrations
 * return the existing [deviceId] with registered = false.
 */
data class RegisterResponse(
    val ok: Boolean = false,

    @SerializedName("device_id")
    val deviceId: Long = 0L,

    val registered: Boolean = false,

    @SerializedName("server_time")
    val serverTime: String? = null
)
