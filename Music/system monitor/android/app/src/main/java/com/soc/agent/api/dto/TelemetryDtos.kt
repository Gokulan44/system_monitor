package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/** CPU sample, serialised with the snake_case names the server expects. */
data class CpuSample(
    @SerializedName("load_pct")
    val loadPct: Double,

    val cores: Int = 1,

    @SerializedName("per_core")
    val perCore: List<Double> = emptyList(),

    @SerializedName("speed_ghz")
    val speedGhz: Double? = null,

    @SerializedName("temp_c")
    val tempC: Double? = null,

    /** Short rolling load history for the dashboard chart. */
    val history: List<Double> = emptyList()
)

/** Memory sample in bytes. */
data class MemorySample(
    @SerializedName("total_b")
    val totalB: Long,

    @SerializedName("used_b")
    val usedB: Long,

    @SerializedName("free_b")
    val freeB: Long,

    @SerializedName("usage_pct")
    val usagePct: Double,

    @SerializedName("swap_total_b")
    val swapTotalB: Long? = null,

    @SerializedName("swap_used_b")
    val swapUsedB: Long? = null
)

/** One storage volume sample. */
data class StorageSample(
    val filesystem: String,

    val mount: String,

    val type: String,

    @SerializedName("total_b")
    val totalB: Long,

    @SerializedName("used_b")
    val usedB: Long,

    @SerializedName("free_b")
    val freeB: Long,

    @SerializedName("usage_pct")
    val usagePct: Double
)

/** Battery status sample. */
data class BatterySample(
    @SerializedName("has_battery")
    val hasBattery: Boolean,

    val percent: Int,

    val charging: Boolean,

    /** charging / discharging / full / not_charging / unknown. */
    val status: String,

    @SerializedName("time_remaining")
    val timeRemaining: Long? = null
)

/** Static device identity snapshot. */
data class DeviceInfoSample(
    val manufacturer: String,

    val model: String,

    val kernel: String,

    @SerializedName("os_name")
    val osName: String,

    @SerializedName("os_version")
    val osVersion: String,

    @SerializedName("security_patch_level")
    val securityPatchLevel: String,

    @SerializedName("os_build")
    val osBuild: String,

    @SerializedName("uptime_sec")
    val uptimeSec: Long
)

/**
 * POST /api/agent/telemetry payload. The device is identified by the
 * X-Agent-Id header, so no device id is sent in the body.
 */
data class TelemetryRequest(
    val cpu: CpuSample? = null,

    val memory: MemorySample? = null,

    val storage: List<StorageSample> = emptyList(),

    val battery: BatterySample? = null,

    val info: DeviceInfoSample? = null
)
