package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/** One detection item within a scan run. */
data class ScanItemDto(
    val item: String,

    /** file / apk / url / process / ... */
    val kind: String = "file",

    val hash: String? = null,

    @SerializedName("match_name")
    val matchName: String? = null,

    /** clean / suspicious / malicious. */
    val verdict: String = "clean",

    /** low / medium / high / critical. */
    val severity: String = "low",

    val detail: String = "",

    val quarantined: Boolean = false
)

/** POST /api/agent/scan payload. Timestamps are ISO-8601 strings. */
data class ScanRequest(
    @SerializedName("scan_type")
    val scanType: String = "quick",

    /** running / completed / failed. */
    val status: String = "completed",

    @SerializedName("items_scanned")
    val itemsScanned: Int = 0,

    @SerializedName("threats_found")
    val threatsFound: Int = 0,

    @SerializedName("started_at")
    val startedAt: String,

    @SerializedName("finished_at")
    val finishedAt: String? = null,

    val items: List<ScanItemDto> = emptyList()
)

/** POST /api/agent/scan response — includes the recomputed security score. */
data class ScanResponse(
    val ok: Boolean = false,

    @SerializedName("scan_id")
    val scanId: String? = null,

    @SerializedName("run_id")
    val runId: Long = 0L,

    @SerializedName("threats_found")
    val threatsFound: Int = 0,

    /** Reflects the number of items the agent scanned (server echo or local). */
    @SerializedName("items_scanned")
    val itemsScanned: Int = 0,

    val score: Int = 100,

    val grade: String = "Excellent"
)