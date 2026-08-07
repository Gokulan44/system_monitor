package com.soc.agent.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * One network observation. A single table holds both interface samples
 * (kind = "network") and phishing URL checks (kind = "phishing") so the
 * local schema stays compact while mirroring the server's `network_logs`
 * and `phishing_logs` tables.
 */
@Entity(
    tableName = "network_logs",
    indices = [Index(value = ["kind"]), Index(value = ["device_id", "timestamp_millis"])]
)
data class NetworkLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "device_id")
    val deviceId: Long,

    /** "network" for interface samples, "phishing" for URL checks. */
    @ColumnInfo(name = "kind")
    val kind: String = "network",

    @ColumnInfo(name = "timestamp_millis")
    val timestampMillis: Long = System.currentTimeMillis(),

    // ---- interface sample fields (kind = "network") ----
    @ColumnInfo(name = "iface")
    val iface: String? = null,

    @ColumnInfo(name = "ip4")
    val ip4: String? = null,

    @ColumnInfo(name = "ip6")
    val ip6: String? = null,

    @ColumnInfo(name = "mac")
    val mac: String? = null,

    /** up / down / unknown. */
    @ColumnInfo(name = "state")
    val state: String? = null,

    @ColumnInfo(name = "default_gw")
    val defaultGw: String? = null,

    /** DNS servers, JSON-encoded. */
    @ColumnInfo(name = "dns_servers")
    val dnsServers: List<String> = emptyList(),

    @ColumnInfo(name = "wifi_ssid")
    val wifiSsid: String? = null,

    @ColumnInfo(name = "wifi_rssi")
    val wifiRssi: Int? = null,

    @ColumnInfo(name = "wifi_link_speed")
    val wifiLinkSpeed: Int? = null,

    @ColumnInfo(name = "vpn_active")
    val vpnActive: Boolean = false,

    @ColumnInfo(name = "rx_bytes")
    val rxBytes: Long = 0L,

    @ColumnInfo(name = "tx_bytes")
    val txBytes: Long = 0L,

    /** Bytes per second since the previous sample. */
    @ColumnInfo(name = "rx_sec")
    val rxSec: Double = 0.0,

    @ColumnInfo(name = "tx_sec")
    val txSec: Double = 0.0,

    // ---- phishing check fields (kind = "phishing") ----
    @ColumnInfo(name = "url")
    val url: String? = null,

    /** safe / suspicious / phishing. */
    @ColumnInfo(name = "verdict")
    val verdict: String? = null,

    @ColumnInfo(name = "score")
    val score: Int? = null,

    /** Human-readable reasons, JSON-encoded. */
    @ColumnInfo(name = "reasons")
    val reasons: List<String> = emptyList()
)
