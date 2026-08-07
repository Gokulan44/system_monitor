package com.soc.agent.data;

import android.content.Context;
import com.soc.agent.BuildConfig;
import com.soc.agent.api.ApiClient;
import com.soc.agent.api.ApiService;
import com.soc.agent.api.dto.AlertDto;
import com.soc.agent.api.dto.AlertsRequest;
import com.soc.agent.api.dto.AppsRequest;
import com.soc.agent.api.dto.BlocklistDto;
import com.soc.agent.api.dto.NetworkRequest;
import com.soc.agent.api.dto.PhishingSampleDto;
import com.soc.agent.api.dto.PolicyDto;
import com.soc.agent.api.dto.PolicyResponse;
import com.soc.agent.api.dto.RegisterRequest;
import com.soc.agent.api.dto.RegisterResponse;
import com.soc.agent.api.dto.ScanRequest;
import com.soc.agent.api.dto.ScanResponse;
import com.soc.agent.api.dto.SyncCountResponse;
import com.soc.agent.api.dto.SyncResponse;
import com.soc.agent.api.dto.TelemetryRequest;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.AlertEntity;
import com.soc.agent.database.entity.AppPermissionEntity;
import com.soc.agent.database.entity.DeviceEntity;
import com.soc.agent.database.entity.DeviceHistoryEntity;
import com.soc.agent.database.entity.IocEntity;
import com.soc.agent.database.entity.InstalledAppEntity;
import com.soc.agent.database.entity.MalwareScanLogEntity;
import com.soc.agent.database.entity.NetworkLogEntity;
import com.soc.agent.database.entity.PolicyEntity;
import com.soc.agent.database.entity.ScanRunEntity;
import com.soc.agent.database.entity.ThreatEntity;
import com.soc.agent.security.IocMatcher;
import com.soc.agent.security.PhishingChecker;
import com.soc.agent.services.AppInventory;
import com.soc.agent.services.BatteryMonitor;
import com.soc.agent.services.CpuMonitor;
import com.soc.agent.services.DeviceInfoCollector;
import com.soc.agent.services.MemoryMonitor;
import com.soc.agent.services.NetworkMonitor;
import com.soc.agent.services.ScanService;
import com.soc.agent.services.StorageMonitor;
import com.soc.agent.utils.Prefs;
import kotlinx.coroutines.Dispatchers;
import java.time.Instant;
import java.util.UUID;

/**
 * Application-side singleton that orchestrates agent data flow and telemetry.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 >2\u00020\u0001:\u0001>B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\u0006H\u0002J\u001c\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u0018J\b\u0010\u001d\u001a\u00020\u0012H\u0002J&\u0010\u001e\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00142\b\u0010!\u001a\u0004\u0018\u00010\"H\u0082@\u00a2\u0006\u0002\u0010#J&\u0010$\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0082@\u00a2\u0006\u0002\u0010)J\u000e\u0010*\u001a\u00020+H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0014\u00100\u001a\u00020\u00122\n\u00101\u001a\u000602j\u0002`3H\u0002J\u0010\u00100\u001a\u00020\u00122\u0006\u00104\u001a\u00020\fH\u0002J\u000e\u00105\u001a\u000206H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0016\u00107\u001a\u00020(2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u00108J\u0016\u00107\u001a\u00020(2\u0006\u00109\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010:J\u000e\u0010;\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010<\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010=\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0018R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u00a8\u0006?"}, d2 = {"Lcom/soc/agent/data/SecurityRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apiService", "Lcom/soc/agent/api/ApiService;", "appContext", "kotlin.jvm.PlatformType", "db", "Lcom/soc/agent/database/AppDatabase;", "lastError", "", "getLastError", "()Ljava/lang/String;", "agentId", "api", "checkPhishing", "", "urls", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentIocMatcher", "Lcom/soc/agent/security/IocMatcher;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deviceId", "", "heartbeat", "Lcom/soc/agent/api/dto/SyncResponse;", "markSuccess", "persistPolicies", "policies", "Lcom/soc/agent/api/dto/PolicyDto;", "blocklist", "Lcom/soc/agent/api/dto/BlocklistDto;", "(Ljava/util/List;Lcom/soc/agent/api/dto/BlocklistDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "persistScan", "request", "Lcom/soc/agent/api/dto/ScanRequest;", "response", "Lcom/soc/agent/api/dto/ScanResponse;", "(JLcom/soc/agent/api/dto/ScanRequest;Lcom/soc/agent/api/dto/ScanResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pullPolicies", "Lcom/soc/agent/api/dto/PolicyResponse;", "pushAlerts", "Lcom/soc/agent/api/dto/SyncCountResponse;", "alerts", "Lcom/soc/agent/api/dto/AlertDto;", "recordError", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "register", "Lcom/soc/agent/api/dto/RegisterResponse;", "runScan", "(Lcom/soc/agent/api/dto/ScanRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanType", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncApps", "syncNetwork", "syncTelemetry", "Companion", "app_debug"})
public final class SecurityRepository {
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.database.AppDatabase db = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private volatile com.soc.agent.api.ApiService apiService;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DEFAULT_SERVER_URL = "http://10.0.2.2:3000/";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.data.SecurityRepository instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.data.SecurityRepository.Companion Companion = null;
    
    private SecurityRepository(android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastError() {
        return null;
    }
    
    private final com.soc.agent.api.ApiService api() {
        return null;
    }
    
    /**
     * Stable agent id: persisted by [Prefs], generated once on first boot.
     */
    private final java.lang.String agentId() {
        return null;
    }
    
    /**
     * Server-side device id (from registration), used as a local FK.
     */
    private final long deviceId() {
        return 0L;
    }
    
    /**
     * Registers the agent with the SOC server. Safe to call repeatedly.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object register(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.RegisterResponse> $completion) {
        return null;
    }
    
    /**
     * Collects a full telemetry snapshot, persists it locally and pushes it.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncTelemetry(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Rebuilds local app inventory and pushes it to the SOC server.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncApps(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Samples the active network state and pushes it to the SOC server.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncNetwork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Scores a list of URLs with the on-device phishing heuristic (against the
     * synced IOC blocklist), persists the findings and pushes them to the server.
     * Callers supply the URLs (e.g. from DNS/history) when they become available.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkPhishing(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> urls, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Runs a named scan ("quick" or "full") and pushes the run to the server.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object runScan(@org.jetbrains.annotations.NotNull()
    java.lang.String scanType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.ScanResponse> $completion) {
        return null;
    }
    
    /**
     * Pushes a pre-built scan request (e.g. single-APK analysis) to the server.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object runScan(@org.jetbrains.annotations.NotNull()
    com.soc.agent.api.dto.ScanRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.ScanResponse> $completion) {
        return null;
    }
    
    /**
     * Persists a scan run and its detections/threats in the local database.
     */
    private final java.lang.Object persistScan(long deviceId, com.soc.agent.api.dto.ScanRequest request, com.soc.agent.api.dto.ScanResponse response, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Persists and pushes a batch of security alerts to the SOC server.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object pushAlerts(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.AlertDto> alerts, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.SyncCountResponse> $completion) {
        return null;
    }
    
    /**
     * Pulls the latest policies and IOC blocklist from the server.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object pullPolicies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.PolicyResponse> $completion) {
        return null;
    }
    
    /**
     * Periodic heartbeat returning the server's recomputed score/grade.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object heartbeat(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.SyncResponse> $completion) {
        return null;
    }
    
    private final java.lang.Object persistPolicies(java.util.List<com.soc.agent.api.dto.PolicyDto> policies, com.soc.agent.api.dto.BlocklistDto blocklist, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Builds an [IocMatcher] from the IOCs currently stored locally.
     */
    private final java.lang.Object currentIocMatcher(kotlin.coroutines.Continuation<? super com.soc.agent.security.IocMatcher> $completion) {
        return null;
    }
    
    private final void markSuccess() {
    }
    
    private final void recordError(java.lang.String message) {
    }
    
    private final void recordError(java.lang.Exception e) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/soc/agent/data/SecurityRepository$Companion;", "", "()V", "DEFAULT_SERVER_URL", "", "instance", "Lcom/soc/agent/data/SecurityRepository;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.data.SecurityRepository getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}