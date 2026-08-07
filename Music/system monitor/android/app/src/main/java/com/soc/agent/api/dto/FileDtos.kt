package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/** One file visited during a scan. */
data class FileScanDto(
    @SerializedName("scan_type")
    val scanType: String = "agent",

    val path: String,

    val name: String = "",

    val ext: String = "",

    @SerializedName("size_b")
    val sizeB: Long = 0L,

    val sha256: String? = null,

    /** clean / suspicious / malicious. */
    val verdict: String = "clean",

    val detail: String = ""
)

/** POST /api/agent/files payload. */
data class FilesRequest(
    val files: List<FileScanDto>
)
