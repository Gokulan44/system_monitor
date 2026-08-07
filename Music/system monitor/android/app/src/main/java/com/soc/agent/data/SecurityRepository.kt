package com.soc.agent.data

import android.content.Context
import com.soc.agent.BuildConfig
import com.soc.agent.api.ApiClient
import com.soc.agent.api.ApiService
import com.soc.agent.api.dto.AlertDto
import com.soc.agent.api.dto.AlertsRequest
import com.soc.agent.api.dto.AppsRequest
import com.soc.agent.api.dto.BlocklistDto
import com.soc.agent.api.dto.NetworkRequest
import com.soc.agent.api.dto.PhishingSampleDto
import com.soc.agent.api.dto.PolicyDto
import com.soc.agent.api.dto.PolicyResponse
import com.soc.agent.api.dto.RegisterRequest
import com.soc.agent.api.dto.RegisterResponse
import com.soc.agent.api.dto.ScanRequest
import com.soc.agent.api.dto.ScanResponse
import com.soc.agent.api.dto.SyncCountResponse
import com.soc.agent.api.dto.SyncResponse
import com.soc.agent.api.dto.TelemetryRequest
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.AlertEntity
import com.soc.agent.database.entity.AppPermissionEntity
import com.soc.agent.database.entity.DeviceEntity
import com.soc.agent.database.entity.DeviceHistoryEntity
import com.soc.agent.database.entity.IocEntity
import com.soc.agent.database.entity.InstalledAppEntity
import com.soc.agent.database.entity.MalwareScanLogEntity
import com.soc.agent.database.entity.NetworkLogEntity
import com.soc.agent.database.entity.PolicyEntity
import com.soc.agent.database.entity.ScanRunEntity
import com.soc.agent.database.entity.ThreatEntity
import com.soc.agent.security.IocMatcher
import com.soc.agent.security.PhishingChecker
import com.soc.agent.services.AppInventory
import com.soc.agent.services.BatteryMonitor
import com.soc.agent.services.CpuMonitor
import com.soc.agent.services.DeviceInfoCollector
import com.soc.agent.services.MemoryMonitor
import com.soc.agent.services.NetworkMonitor
import com.soc.agent.services.ScanService
import com.soc.agent.services.StorageMonitor
import com.soc.agent.utils.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.UUID

/**
 * Application-side singleton that orchestrates agent data flow and telemetry.
 */
class SecurityRepository private constructor(context: Context) {

    private val appContext = context.applicationContext
    private val db = AppDatabase.getInstance(appContext)

    init {
        // Prefs is a lazy singleton; give it the app context up front.
        Prefs.init(appContext)
    }

    @Volatile
    private var apiService: ApiService? = null

    /** The last error captured during any sync, or null when the last sync succeeded. */
    val lastError: String?
        get() = Prefs.lastError.takeIf { it.isNotEmpty() }

    // ------------------------------------------------------------------ API --

    private fun api(): ApiService {
        apiService?.let { return it }
        val url = Prefs.serverUrl.ifBlank { DEFAULT_SERVER_URL }
        val key = Prefs.apiKey
        val agentId = Prefs.agentId
        return ApiClient.api(appContext, url, key, agentId).also { apiService = it }
    }

    /** Stable agent id: persisted by [Prefs], generated once on first boot. */
    private fun agentId(): String {
        val existing = Prefs.agentId
        if (existing.isNotBlank()) return existing
        return UUID.randomUUID().toString().also { Prefs.agentId = it }
    }

    /** Server-side device id (from registration), used as a local FK. */
    private fun deviceId(): Long = Prefs.registeredDeviceId.toLongOrNull() ?: 0L

    // ------------------------------------------------------------ registration --

    /** Registers the agent with the SOC server. Safe to call repeatedly. */
    suspend fun register(): RegisterResponse = withContext(Dispatchers.IO) {
        try {
            val agentId = agentId()
            val info = DeviceInfoCollector.collect(appContext)
            val request = RegisterRequest(
                agentId = agentId,
                manufacturer = info.manufacturer,
                model = info.model,
                name = Prefs.deviceName.ifBlank {
                    listOf(info.manufacturer, info.model).filter { it.isNotBlank() }.joinToString(" ")
                }.ifBlank { "Android Device" },
                androidVersion = info.osVersion,
                appVersion = BuildConfig.VERSION_NAME
            )

            val response = api().register(request)
            if (response.ok) {
                val now = System.currentTimeMillis()
                Prefs.registeredDeviceId = response.deviceId.toString()
                db.deviceDao().upsertDevice(
                    DeviceEntity(
                        agentId = agentId,
                        deviceId = response.deviceId,
                        name = request.name,
                        manufacturer = request.manufacturer,
                        model = request.model,
                        androidVersion = request.androidVersion,
                        appVersion = request.appVersion,
                        platform = "android",
                        status = "online",
                        lastSeen = now,
                        registeredAt = if (response.registered) now else System.currentTimeMillis()
                    )
                )
                db.deviceDao().insertHistory(
                    DeviceHistoryEntity(
                        deviceId = response.deviceId,
                        event = "agent.register",
                        detail = "Registered agent $agentId with the SOC platform",
                        timestampMillis = now
                    )
                )
                markSuccess()
            } else {
                recordError("Registration rejected by server")
            }
            response
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    // -------------------------------------------------------------- telemetry --

    /** Collects a full telemetry snapshot, persists it locally and pushes it. */
    suspend fun syncTelemetry(): Unit = withContext(Dispatchers.IO) {
        try {
            val deviceId = deviceId()
            val cpu = CpuMonitor(appContext).sample()
            val memory = MemoryMonitor(appContext).sample()
            val storage = StorageMonitor(appContext).sample()
            val battery = BatteryMonitor(appContext).sample()
            val info = DeviceInfoCollector.collect(appContext)
            val now = System.currentTimeMillis()

            db.telemetryDao().insertCpu(cpu.toEntity(deviceId, now))
            db.telemetryDao().insertMemory(memory.toEntity(deviceId, now))
            db.telemetryDao().insertStorage(storage.map { it.toEntity(deviceId, now) })
            db.telemetryDao().insertBattery(battery.toEntity(deviceId, now))
            db.telemetryDao().insertDeviceInfo(info.toEntity(deviceId, now))

            val response = api().telemetry(
                TelemetryRequest(
                    cpu = cpu,
                    memory = memory,
                    storage = storage,
                    battery = battery,
                    info = info
                )
            )
            if (response.ok) markSuccess() else recordError("Telemetry rejected")
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    // ------------------------------------------------------------------ apps --

    /** Rebuilds local app inventory and pushes it to the SOC server. */
    suspend fun syncApps(): Unit = withContext(Dispatchers.IO) {
        try {
            val deviceId = deviceId()
            val apps = AppInventory(appContext).collect()

            // Refresh the cached permission rows (they carry an auto-increment id).
            db.appDao().clearPermissions()
            val perms = apps.flatMap { app ->
                app.permissions.map { p ->
                    AppPermissionEntity(
                        deviceId = deviceId,
                        packageName = app.packageName,
                        category = p.category,
                        granted = p.granted,
                        name = p.name
                    )
                }
            }
            db.appDao().insertPermissions(perms)

            // Upsert the app rows keyed on (device_id, package_name).
            db.appDao().insertApps(
                apps.map { app ->
                    InstalledAppEntity(
                        deviceId = deviceId,
                        packageName = app.packageName,
                        name = app.name,
                        pid = app.pid,
                        cpuPct = app.cpuPct,
                        memB = app.memB,
                        status = app.status,
                        risk = app.risk,
                        signature = app.signature
                    )
                }
            )

            val response = api().apps(AppsRequest(apps))
            if (response.ok) markSuccess() else recordError("App inventory rejected")
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    // --------------------------------------------------------------- network --

    /** Samples the active network state and pushes it to the SOC server. */
    suspend fun syncNetwork(): Unit = withContext(Dispatchers.IO) {
        try {
            val deviceId = deviceId()
            val network = NetworkMonitor(appContext).sample()
            val now = System.currentTimeMillis()

            db.networkDao().insertNetworkLogs(listOf(network.toEntity(deviceId, now)))

            val response = api().network(
                NetworkRequest(networks = listOf(network), phishing = emptyList())
            )
            if (response.ok) markSuccess() else recordError("network sample rejected")
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    /**
     * Scores a list of URLs with the on-device phishing heuristic (against the
     * synced IOC blocklist), persists the findings and pushes them to the server.
     * Callers supply the URLs (e.g. from DNS/history) when they become available.
     */
    suspend fun checkPhishing(urls: List<String>): Unit = withContext(Dispatchers.IO) {
        if (urls.isEmpty()) return@withContext
        try {
            val deviceId = deviceId()
            val matcher = currentIocMatcher()
            val phishing = urls.mapNotNull { url ->
                val result = PhishingChecker.score(url, matcher)
                // Only surface findings that are not clearly safe, to reduce noise.
                if (result.verdict == "safe") {
                    null
                } else {
                    PhishingSampleDto(
                        url = url,
                        verdict = result.verdict,
                        score = result.score.toInt(),
                        reasons = result.reasons
                    )
                }
            }
            if (phishing.isEmpty()) return@withContext

            val now = System.currentTimeMillis()
            db.networkDao().insertNetworkLogs(phishing.map { it.toEntity(deviceId, now) })

            val response = api().network(
                NetworkRequest(networks = emptyList(), phishing = phishing)
            )
            if (response.ok) markSuccess() else recordError("phishing samples rejected")
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    // ------------------------------------------------------------------ scan --

    /** Runs a named scan ("quick" or "full") and pushes the run to the server. */
    suspend fun runScan(scanType: String): ScanResponse = withContext(Dispatchers.IO) {
        try {
            val request = runCatching {
                val service = ScanService(appContext)
                val matcher = currentIocMatcher()
                if (scanType == "full") service.fullScan(matcher) else service.quickScan(matcher)
            }.getOrThrow()
            runScan(request)
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    /** Pushes a pre-built scan request (e.g. single-APK analysis) to the server. */
    suspend fun runScan(request: ScanRequest): ScanResponse = withContext(Dispatchers.IO) {
        try {
            val deviceId = deviceId()
            val response = api().scan(request)

            persistScan(deviceId, request, response)
            if (response.ok) markSuccess() else recordError("Scan rejected by server")

            // The server response may omit items_scanned; reflect what we actually scanned.
            response.copy(itemsScanned = request.itemsScanned)
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    /** Persists a scan run and its detections/threats in the local database. */
    private suspend fun persistScan(deviceId: Long, request: ScanRequest, response: ScanResponse) {
        val now = System.currentTimeMillis()
        val scanId = response.scanId ?: "local_${now}"
        val startedAt = runCatching { Instant.parse(request.startedAt).toEpochMilli() }
            .getOrDefault(now)

        if (request.items.isNotEmpty()) {
            db.scanDao().insertMalwareLogs(
                request.items.map { item ->
                    MalwareScanLogEntity(
                        deviceId = deviceId,
                        scanId = scanId,
                        item = item.item,
                        kind = item.kind,
                        hash = item.hash,
                        matchName = item.matchName,
                        verdict = item.verdict,
                        severity = item.severity,
                        detail = item.detail,
                        quarantined = item.quarantined,
                        scannedAt = now
                    )
                }
            )
        }

        val detections = request.items.filter {
            it.verdict == "malicious" || it.verdict == "suspicious"
        }
        if (detections.isNotEmpty()) {
            db.scanDao().insertThreats(
                detections.map { item ->
                    ThreatEntity(
                        deviceId = deviceId,
                        kind = item.kind,
                        title = item.item.take(64),
                        severity = item.severity,
                        detail = item.detail.ifBlank {
                            "${item.verdict} match: ${item.matchName ?: "unknown"}"
                        },
                        status = "open",
                        detectedAt = now
                    )
                }
            )
        }

        db.scanDao().insertScanRun(
            ScanRunEntity(
                deviceId = deviceId,
                scanType = request.scanType,
                status = "completed",
                itemsScanned = request.itemsScanned,
                threatsFound = response.threatsFound,
                score = response.score,
                grade = response.grade,
                startedAt = startedAt,
                finishedAt = now
            )
        )
    }

    // ---------------------------------------------------------------- alerts --

    /** Persists and pushes a batch of security alerts to the SOC server. */
    suspend fun pushAlerts(alerts: List<AlertDto>): SyncCountResponse = withContext(Dispatchers.IO) {
        if (alerts.isEmpty()) return@withContext SyncCountResponse(ok = true)
        try {
            val deviceId = deviceId()
            val now = System.currentTimeMillis()
            db.alertDao().insertAlerts(
                alerts.map { a ->
                    AlertEntity(
                        deviceId = deviceId,
                        level = a.level,
                        title = a.title,
                        message = a.message,
                        createdAt = now
                    )
                }
            )
            val response = api().alerts(AlertsRequest(alerts))
            if (response.ok) markSuccess() else recordError("Alerts rejected")
            response
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    // --------------------------------------------------------------- policies --

    /** Pulls the latest policies and IOC blocklist from the server. */
    suspend fun pullPolicies(): PolicyResponse = withContext(Dispatchers.IO) {
        try {
            val response = api().policies()
            persistPolicies(response.policies, response.blocklist)
            markSuccess()
            response
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    /** Periodic heartbeat returning the server's recomputed score/grade. */
    suspend fun heartbeat(): SyncResponse = withContext(Dispatchers.IO) {
        try {
            val response = api().sync()
            response.policies?.let { persistPolicies(it, response.blocklist) }
            markSuccess()
            response
        } catch (e: Exception) {
            recordError(e)
            throw e
        }
    }

    private suspend fun persistPolicies(policies: List<PolicyDto>, blocklist: BlocklistDto?) {
        if (policies.isNotEmpty()) {
            db.policyDao().upsertPolicies(
                policies.map { p ->
                    PolicyEntity(
                        id = p.id,
                        name = p.name,
                        policyType = p.policyType,
                        rules = p.rules,
                        syncedAt = System.currentTimeMillis()
                    )
                }
            )
        }
        blocklist?.let { bl ->
            val iocs = buildList {
                bl.domains.forEach { add(IocEntity(value = it, type = "domain")) }
                bl.urls.forEach { add(IocEntity(value = it, type = "url")) }
                bl.hashes.forEach { add(IocEntity(value = it, type = "hash")) }
            }
            if (iocs.isNotEmpty()) db.policyDao().upsertIocs(iocs)
        }
    }

    /** Builds an [IocMatcher] from the IOCs currently stored locally. */
    private suspend fun currentIocMatcher(): IocMatcher {
        val dao = db.policyDao()
        return IocMatcher(dao.getBlockedDomains(), dao.getBlockedUrls(), dao.getBlockedHashes())
    }

    // -------------------------------------------------------------- helpers --

    private fun markSuccess() {
        Prefs.lastSync = System.currentTimeMillis()
        Prefs.lastError = ""
    }

    private fun recordError(message: String) {
        Prefs.lastError = message
    }

    private fun recordError(e: Exception) {
        Prefs.lastError = e.message ?: e.javaClass.simpleName
    }

    companion object {
        private const val DEFAULT_SERVER_URL = "http://10.0.2.2:3000/"

        @Volatile
        private var instance: SecurityRepository? = null

        fun getInstance(context: Context): SecurityRepository =
            instance ?: synchronized(this) {
                instance ?: SecurityRepository(context.applicationContext).also { instance = it }
            }
    }
}