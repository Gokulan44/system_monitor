package com.soc.agent.api.dto

import com.google.gson.annotations.SerializedName

/** One network interface sample. */
data class NetworkSampleDto(
    val iface: String = "wlan0",

    val ip4: String? = null,

    val ip6: String? = null,

    val mac: String? = null,

    /** up / down / unknown. */
    val state: String = "up",

    @SerializedName("default_gw")
    val defaultGw: String? = null,

    @SerializedName("dns_servers")
    val dnsServers: List<String> = emptyList(),

    @SerializedName("wifi_ssid")
    val wifiSsid: String? = null,

    @SerializedName("wifi_rssi")
    val wifiRssi: Int? = null,

    @SerializedName("wifi_link_speed")
    val wifiLinkSpeed: Int? = null,

    @SerializedName("vpn_active")
    val vpnActive: Boolean = false,

    @SerializedName("rx_bytes")
    val rxBytes: Long = 0L,

    @SerializedName("tx_bytes")
    val txBytes: Long = 0L,

    /** Bytes per second since the previous sample. */
    @SerializedName("rx_sec")
    val rxSec: Double = 0.0,

    @SerializedName("tx_sec")
    val txSec: Double = 0.0
)

/** One phishing URL check result. */
data class PhishingSampleDto(
    val url: String,

    /** safe / suspicious / phishing. */
    val verdict: String = "safe",

    val score: Int = 0,

    val reasons: List<String> = emptyList()
)

/** POST /api/agent/network payload. */
data class NetworkRequest(
    val networks: List<NetworkSampleDto> = emptyList(),
    val phishing: List<PhishingSampleDto> = emptyList()
)

/**
 * Aggregated output of a network collection pass. Used as the return type of
 * [com.soc.agent.services.NetworkMonitor].sample() (services layer) and
 * consumed by [com.soc.agent.data.SecurityRepository].
 */
data class NetworkSnapshot(
    val networks: List<NetworkSampleDto> = emptyList(),
    val phishing: List<PhishingSampleDto> = emptyList()
)