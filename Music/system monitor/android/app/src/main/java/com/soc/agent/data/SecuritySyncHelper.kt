package com.soc.agent.data

import com.soc.agent.api.dto.BatterySample
import com.soc.agent.api.dto.CpuSample
import com.soc.agent.api.dto.DeviceInfoSample
import com.soc.agent.api.dto.MemorySample
import com.soc.agent.api.dto.NetworkSampleDto
import com.soc.agent.api.dto.PhishingSampleDto
import com.soc.agent.api.dto.StorageSample
import com.soc.agent.database.entity.BatteryStatusEntity
import com.soc.agent.database.entity.CpuUsageEntity
import com.soc.agent.database.entity.DeviceInfoEntity
import com.soc.agent.database.entity.MemoryUsageEntity
import com.soc.agent.database.entity.NetworkLogEntity
import com.soc.agent.database.entity.StorageUsageEntity

/**
 * Mapping extension functions translating DTOs into Room database entities.
 */
fun CpuSample.toEntity(deviceId: Long, ts: Long) = CpuUsageEntity(
    deviceId = deviceId,
    timestampMillis = ts,
    loadPct = loadPct,
    cores = cores,
    perCore = perCore,
    speedGhz = speedGhz,
    tempC = tempC,
    usageHistory = history
)

fun MemorySample.toEntity(deviceId: Long, ts: Long) = MemoryUsageEntity(
    deviceId = deviceId,
    timestampMillis = ts,
    totalB = totalB,
    usedB = usedB,
    freeB = freeB,
    usagePct = usagePct,
    swapTotalB = swapTotalB,
    swapUsedB = swapUsedB
)

fun StorageSample.toEntity(deviceId: Long, ts: Long) = StorageUsageEntity(
    deviceId = deviceId,
    timestampMillis = ts,
    filesystem = filesystem,
    mount = mount,
    type = type,
    totalB = totalB,
    usedB = usedB,
    freeB = freeB,
    usagePct = usagePct
)

fun BatterySample.toEntity(deviceId: Long, ts: Long) = BatteryStatusEntity(
    deviceId = deviceId,
    timestampMillis = ts,
    hasBattery = hasBattery,
    percent = percent,
    charging = charging,
    status = status,
    timeRemaining = timeRemaining?.let { if (it >= 0) it else null }
)

fun DeviceInfoSample.toEntity(deviceId: Long, ts: Long) = DeviceInfoEntity(
    deviceId = deviceId,
    timestampMillis = ts,
    manufacturer = manufacturer,
    model = model,
    kernel = kernel,
    osName = osName,
    osVersion = osVersion,
    securityPatchLevel = securityPatchLevel,
    osBuild = osBuild,
    uptimeSec = uptimeSec
)

fun NetworkSampleDto.toEntity(deviceId: Long, ts: Long) = NetworkLogEntity(
    deviceId = deviceId,
    kind = "network",
    timestampMillis = ts,
    iface = iface,
    ip4 = ip4,
    ip6 = ip6,
    mac = mac,
    state = state,
    defaultGw = defaultGw,
    dnsServers = dnsServers,
    wifiSsid = wifiSsid,
    wifiRssi = wifiRssi,
    wifiLinkSpeed = wifiLinkSpeed,
    vpnActive = vpnActive,
    rxBytes = rxBytes,
    txBytes = txBytes,
    rxSec = rxSec,
    txSec = txSec
)

fun PhishingSampleDto.toEntity(deviceId: Long, ts: Long) = NetworkLogEntity(
    deviceId = deviceId,
    kind = "phishing",
    timestampMillis = ts,
    url = url,
    verdict = verdict,
    score = score,
    reasons = reasons
)
