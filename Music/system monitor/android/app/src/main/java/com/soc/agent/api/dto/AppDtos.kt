package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/** One declared permission (grouped by category). */
data class AppPermissionDto(
    /** Permission group, e.g. "dangerous", "location", "sms". */
    val category: String = "other",

    val granted: Boolean = false,

    /** Permission identifier, e.g. android.permission.CAMERA. */
    val name: String = ""
)

/** One installed application. */
data class InstalledAppDto(
    @SerializedName("package_name")
    val packageName: String,

    val name: String = "",

    val pid: Int? = null,

    @SerializedName("cpu_pct")
    val cpuPct: Double = 0.0,

    @SerializedName("mem_b")
    val memB: Long = 0L,

    /** installed / running / system / disabled. */
    val status: String = "installed",

    /** unknown / low / medium / high / critical. */
    val risk: String = "unknown",

    /** Signing certificate fingerprint, "n/a" if unavailable. */
    val signature: String = "n/a",

    val permissions: List<AppPermissionDto> = emptyList()
)

/** POST /api/agent/apps payload. */
data class AppsRequest(
    val apps: List<InstalledAppDto>
)
