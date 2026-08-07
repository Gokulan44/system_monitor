package com.soc.agent.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.net.wifi.WifiManager
import android.os.SystemClock
import com.soc.agent.api.dto.NetworkSampleDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.NetworkInterface
import java.util.Collections

class NetworkMonitor(private val context: Context) {

    private var lastRxBytes: Long = -1L
    private var lastTxBytes: Long = -1L
    private var lastRateTime: Long = 0L

    suspend fun sample(): NetworkSampleDto = withContext(Dispatchers.IO) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork
        val caps = if (activeNetwork != null) cm.getNetworkCapabilities(activeNetwork) else null
        val linkProps = if (activeNetwork != null) cm.getLinkProperties(activeNetwork) else null

        val addrs = linkProps?.linkAddresses?.map { it.address } ?: emptyList()
        val ip4List: List<String> = addrs.filterIsInstance<Inet4Address>().mapNotNull { it.hostAddress }
        val ip6List: List<String> = addrs.filterIsInstance<Inet6Address>().mapNotNull { it.hostAddress }
        val dnsServers: List<String> = linkProps?.dnsServers?.mapNotNull { it.hostAddress } ?: emptyList()
        val interfaceName = linkProps?.interfaceName ?: ""

        val iface = when {
            interfaceName.isNotEmpty() -> interfaceName
            caps?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> "wlan0"
            caps?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> "rmnet0"
            caps?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> "eth0"
            else -> "unknown"
        }

        var wifiSsid: String? = null
        var wifiRssi: Int? = null
        var wifiLinkSpeed: Int? = null
        if (caps?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
            try {
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                @Suppress("DEPRECATION")
                val info = wifiManager.connectionInfo
                if (info != null && info.ssid != null && info.ssid.isNotBlank() && info.ssid != "<unknown ssid>") {
                    wifiSsid = info.ssid.removeSurrounding("\"").takeIf { it.isNotBlank() }
                }
                wifiRssi = info?.rssi
                wifiLinkSpeed = info?.linkSpeed
            } catch (e: Exception) {
            }
        }

        val isVpnActive = caps?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) == true
        val mac = macFor(interfaceName)

        val rxBytes = TrafficStats.getTotalRxBytes()
        val txBytes = TrafficStats.getTotalTxBytes()
        val now = SystemClock.elapsedRealtime()
        var rxSec = 0.0
        var txSec = 0.0
        if (lastRxBytes >= 0 && lastTxBytes >= 0 && lastRateTime > 0) {
            val deltaSec = (now - lastRateTime) / 1000.0
            if (deltaSec > 0) {
                rxSec = (rxBytes - lastRxBytes).coerceAtLeast(0L).toDouble() / deltaSec
                txSec = (txBytes - lastTxBytes).coerceAtLeast(0L).toDouble() / deltaSec
            }
        }
        lastRxBytes = rxBytes
        lastTxBytes = txBytes
        lastRateTime = now

        val primaryIp4: String = ip4List.firstOrNull() ?: ""
        val primaryIp6: String = ip6List.firstOrNull() ?: ""

        NetworkSampleDto(
            iface = iface,
            ip4 = primaryIp4,
            ip6 = primaryIp6,
            mac = mac,
            state = "up",
            defaultGw = "",
            dnsServers = dnsServers,
            wifiSsid = wifiSsid,
            wifiRssi = wifiRssi,
            wifiLinkSpeed = wifiLinkSpeed,
            vpnActive = isVpnActive,
            rxSec = rxSec,
            txSec = txSec
        )
    }

    private fun macFor(ifaceName: String): String {
        try {
            val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                if (ifaceName.isNotEmpty() && !intf.name.equals(ifaceName, ignoreCase = true)) continue
                val mac = intf.hardwareAddress ?: continue
                return mac.joinToString(":") { String.format("%02x", it) }
            }
        } catch (e: Exception) {
        }
        return ""
    }
}