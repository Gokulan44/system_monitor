package com.soc.agent.api

import com.soc.agent.api.dto.AlertsRequest
import com.soc.agent.api.dto.AppsRequest
import com.soc.agent.api.dto.FilesRequest
import com.soc.agent.api.dto.GenericResponse
import com.soc.agent.api.dto.NetworkAckResponse
import com.soc.agent.api.dto.NetworkRequest
import com.soc.agent.api.dto.PolicyResponse
import com.soc.agent.api.dto.RegisterRequest
import com.soc.agent.api.dto.RegisterResponse
import com.soc.agent.api.dto.ScanRequest
import com.soc.agent.api.dto.ScanResponse
import com.soc.agent.api.dto.SyncCountResponse
import com.soc.agent.api.dto.SyncResponse
import com.soc.agent.api.dto.TelemetryRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Retrofit definition of the SOC server's agent sync API
 * (see server/src/routes/agent.js). Every call is authenticated with the
 * X-API-Key and X-Agent-Id headers added by [ApiClient].
 */
interface ApiService {

    /** First contact — registers (or re-registers) this device. */
    @POST("api/agent/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    /** Push a CPU/memory/storage/battery/device-info snapshot. */
    @POST("api/agent/telemetry")
    suspend fun telemetry(@Body request: TelemetryRequest): GenericResponse

    /** Push the installed-app inventory with permission counts. */
    @POST("api/agent/apps")
    suspend fun apps(@Body request: AppsRequest): SyncCountResponse

    /** Push file scan log entries. */
    @POST("api/agent/files")
    suspend fun files(@Body request: FilesRequest): SyncCountResponse

    /** Push a completed scan run plus its per-item detections. */
    @POST("api/agent/scan")
    suspend fun scan(@Body request: ScanRequest): ScanResponse

    /** Push alerts raised on the device. */
    @POST("api/agent/alerts")
    suspend fun alerts(@Body request: AlertsRequest): SyncCountResponse

    /** Push network interface samples and phishing URL checks. */
    @POST("api/agent/network")
    suspend fun network(@Body request: NetworkRequest): NetworkAckResponse

    /** Pull policies + IOC blocklist applicable to this device. */
    @GET("api/agent/policies")
    suspend fun policies(): PolicyResponse

    /** Heartbeat — also returns the recomputed score, policies and blocklist. */
    @GET("api/agent/sync")
    suspend fun sync(): SyncResponse
}