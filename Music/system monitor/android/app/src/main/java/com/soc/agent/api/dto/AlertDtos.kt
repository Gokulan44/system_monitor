package com.soc.agent.api.dto

/** One alert pushed from the device to the SOC platform. */
data class AlertDto(
    /** info / warning / critical. */
    val level: String = "info",

    val title: String,

    val message: String = ""
)

/** POST /api/agent/alerts payload. */
data class AlertsRequest(
    val alerts: List<AlertDto>
)