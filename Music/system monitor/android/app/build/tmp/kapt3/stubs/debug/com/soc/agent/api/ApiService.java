package com.soc.agent.api;

import com.soc.agent.api.dto.AlertsRequest;
import com.soc.agent.api.dto.AppsRequest;
import com.soc.agent.api.dto.FilesRequest;
import com.soc.agent.api.dto.GenericResponse;
import com.soc.agent.api.dto.NetworkAckResponse;
import com.soc.agent.api.dto.NetworkRequest;
import com.soc.agent.api.dto.PolicyResponse;
import com.soc.agent.api.dto.RegisterRequest;
import com.soc.agent.api.dto.RegisterResponse;
import com.soc.agent.api.dto.ScanRequest;
import com.soc.agent.api.dto.ScanResponse;
import com.soc.agent.api.dto.SyncCountResponse;
import com.soc.agent.api.dto.SyncResponse;
import com.soc.agent.api.dto.TelemetryRequest;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Retrofit definition of the SOC server's agent sync API
 * (see server/src/routes/agent.js). Every call is authenticated with the
 * X-API-Key and X-Agent-Id headers added by [ApiClient].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0004\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\u0004\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\u0004\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u001e\u001a\u00020\u001f2\b\b\u0001\u0010\u0004\u001a\u00020 H\u00a7@\u00a2\u0006\u0002\u0010!\u00a8\u0006\""}, d2 = {"Lcom/soc/agent/api/ApiService;", "", "alerts", "Lcom/soc/agent/api/dto/SyncCountResponse;", "request", "Lcom/soc/agent/api/dto/AlertsRequest;", "(Lcom/soc/agent/api/dto/AlertsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apps", "Lcom/soc/agent/api/dto/AppsRequest;", "(Lcom/soc/agent/api/dto/AppsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "files", "Lcom/soc/agent/api/dto/FilesRequest;", "(Lcom/soc/agent/api/dto/FilesRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network", "Lcom/soc/agent/api/dto/NetworkAckResponse;", "Lcom/soc/agent/api/dto/NetworkRequest;", "(Lcom/soc/agent/api/dto/NetworkRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "policies", "Lcom/soc/agent/api/dto/PolicyResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/soc/agent/api/dto/RegisterResponse;", "Lcom/soc/agent/api/dto/RegisterRequest;", "(Lcom/soc/agent/api/dto/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scan", "Lcom/soc/agent/api/dto/ScanResponse;", "Lcom/soc/agent/api/dto/ScanRequest;", "(Lcom/soc/agent/api/dto/ScanRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sync", "Lcom/soc/agent/api/dto/SyncResponse;", "telemetry", "Lcom/soc/agent/api/dto/GenericResponse;", "Lcom/soc/agent/api/dto/TelemetryRequest;", "(Lcom/soc/agent/api/dto/TelemetryRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    /**
     * First contact — registers (or re-registers) this device.
     */
    @retrofit2.http.POST(value = "api/agent/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.RegisterResponse> $completion);
    
    /**
     * Push a CPU/memory/storage/battery/device-info snapshot.
     */
    @retrofit2.http.POST(value = "api/agent/telemetry")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object telemetry(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.TelemetryRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.GenericResponse> $completion);
    
    /**
     * Push the installed-app inventory with permission counts.
     */
    @retrofit2.http.POST(value = "api/agent/apps")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object apps(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.AppsRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.SyncCountResponse> $completion);
    
    /**
     * Push file scan log entries.
     */
    @retrofit2.http.POST(value = "api/agent/files")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object files(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.FilesRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.SyncCountResponse> $completion);
    
    /**
     * Push a completed scan run plus its per-item detections.
     */
    @retrofit2.http.POST(value = "api/agent/scan")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object scan(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.ScanRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.ScanResponse> $completion);
    
    /**
     * Push alerts raised on the device.
     */
    @retrofit2.http.POST(value = "api/agent/alerts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object alerts(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.AlertsRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.SyncCountResponse> $completion);
    
    /**
     * Push network interface samples and phishing URL checks.
     */
    @retrofit2.http.POST(value = "api/agent/network")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object network(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.NetworkRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.NetworkAckResponse> $completion);
    
    /**
     * Pull policies + IOC blocklist applicable to this device.
     */
    @retrofit2.http.GET(value = "api/agent/policies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object policies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.PolicyResponse> $completion);
    
    /**
     * Heartbeat — also returns the recomputed score, policies and blocklist.
     */
    @retrofit2.http.GET(value = "api/agent/sync")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.SyncResponse> $completion);
}