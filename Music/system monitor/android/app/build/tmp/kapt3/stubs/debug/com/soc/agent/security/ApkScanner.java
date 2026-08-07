package com.soc.agent.security;

import android.content.Context;
import com.soc.agent.api.dto.ScanItemDto;
import kotlinx.coroutines.Dispatchers;
import java.io.File;
import java.nio.file.Files;
import java.util.zip.ZipFile;

/**
 * Scans APK files and directories for malware. The primary signal is the
 * SHA-256 of the DEX bytecode (or the whole file when no DEX is present),
 * checked against the local signature DB (EICAR) and the remote IOC blocklist.
 * The final verdict is produced by [ThreatClassifier].
 *
 * @param context app context (reserved for future needs such as package lookup).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0002J(\u0010\u0011\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00060\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J \u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0017J.\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00192\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u001e\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/soc/agent/security/ApkScanner;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "buildDetail", "", "verdict", "severity", "reason", "cleanIoItem", "Lcom/soc/agent/api/dto/ScanItemDto;", "file", "Ljava/io/File;", "note", "isSymlink", "", "resolveMatch", "Lkotlin/Pair;", "hash", "iocMatcher", "Lcom/soc/agent/security/IocMatcher;", "scanApk", "(Ljava/io/File;Lcom/soc/agent/security/IocMatcher;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanDirectory", "", "dir", "maxDepth", "", "(Ljava/io/File;Lcom/soc/agent/security/IocMatcher;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanPlainFile", "app_debug"})
public final class ApkScanner {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public ApkScanner(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Scans a single APK. Opens the archive as a [ZipFile], hashes the
     * `classes.dex` entry when present (falling back to hashing the whole file),
     * checks EICAR + blocklist, then classifies via [ThreatClassifier].
     *
     * @return a [ScanItemDto] with kind = "apk".
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object scanApk(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    com.soc.agent.security.IocMatcher iocMatcher, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.ScanItemDto> $completion) {
        return null;
    }
    
    /**
     * Walks a directory up to [maxDepth] levels (default 4), skipping symlinks,
     * and scans every regular file. Non-APK files are hashed and classified too,
     * so this is safe on mixed public directories. Runs on the IO dispatcher.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object scanDirectory(@org.jetbrains.annotations.NotNull()
    java.io.File dir, @org.jetbrains.annotations.NotNull()
    com.soc.agent.security.IocMatcher iocMatcher, int maxDepth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.api.dto.ScanItemDto>> $completion) {
        return null;
    }
    
    private final java.lang.Object scanPlainFile(java.io.File file, com.soc.agent.security.IocMatcher iocMatcher, kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.ScanItemDto> $completion) {
        return null;
    }
    
    /**
     * Resolves the IOC match label and correlated reason for a hash.
     */
    private final kotlin.Pair<java.lang.String, java.lang.String> resolveMatch(java.lang.String hash, com.soc.agent.security.IocMatcher iocMatcher) {
        return null;
    }
    
    private final java.lang.String buildDetail(java.lang.String verdict, java.lang.String severity, java.lang.String reason) {
        return null;
    }
    
    private final com.soc.agent.api.dto.ScanItemDto cleanIoItem(java.io.File file, java.lang.String note) {
        return null;
    }
    
    private final boolean isSymlink(java.io.File file) {
        return false;
    }
}