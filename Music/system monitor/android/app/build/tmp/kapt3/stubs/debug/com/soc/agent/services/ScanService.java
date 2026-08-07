package com.soc.agent.services;

import android.content.Context;
import android.os.Environment;
import com.soc.agent.api.dto.ScanItemDto;
import com.soc.agent.api.dto.ScanRequest;
import com.soc.agent.security.ApkScanner;
import com.soc.agent.security.IocMatcher;
import java.io.File;
import java.time.Instant;

/**
 * Coordinates on-device malware scans and packages the results into a
 * [ScanRequest] for the SOC server. Quick scan targets installed APKs plus the
 * top level of the usual public directories; the full scan additionally attempts
 * private app data and read-only system dirs (best-effort).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/services/ScanService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apkScanner", "Lcom/soc/agent/security/ApkScanner;", "buildRequest", "Lcom/soc/agent/api/dto/ScanRequest;", "scanType", "", "startedAt", "Ljava/time/Instant;", "items", "", "Lcom/soc/agent/api/dto/ScanItemDto;", "fullScan", "iocMatcher", "Lcom/soc/agent/security/IocMatcher;", "(Lcom/soc/agent/security/IocMatcher;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "quickScan", "safePublicDir", "Ljava/io/File;", "directoryType", "app_debug"})
public final class ScanService {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.security.ApkScanner apkScanner = null;
    
    public ScanService(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Quick scan: installed APKs under /data/app plus the top-level contents of
     * the public Downloads and DCIM directories.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object quickScan(@org.jetbrains.annotations.NotNull()
    com.soc.agent.security.IocMatcher iocMatcher, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.ScanRequest> $completion) {
        return null;
    }
    
    /**
     * Full scan: quick scan targets plus private app data dirs (best-effort) and
     * read-only system directories. Likely to hit permission bumps — those are
     * skipped gracefully rather than treated as failures.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fullScan(@org.jetbrains.annotations.NotNull()
    com.soc.agent.security.IocMatcher iocMatcher, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.ScanRequest> $completion) {
        return null;
    }
    
    private final java.io.File safePublicDir(java.lang.String directoryType) {
        return null;
    }
    
    private final com.soc.agent.api.dto.ScanRequest buildRequest(java.lang.String scanType, java.time.Instant startedAt, java.util.List<com.soc.agent.api.dto.ScanItemDto> items) {
        return null;
    }
}