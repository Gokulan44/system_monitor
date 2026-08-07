package com.soc.agent.services;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Debug;
import com.soc.agent.api.dto.AppPermissionDto;
import com.soc.agent.api.dto.InstalledAppDto;
import com.soc.agent.utils.Formatters;
import kotlinx.coroutines.Dispatchers;
import java.security.MessageDigest;

/**
 * Builds a full inventory of installed applications for the SOC server,
 * including a fingerprint of each app's signing certificate and its runtime
 * memory footprint (when a live process is observable). Runs on the IO dispatcher
 * because PackageManager queries can be slow with many apps.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0014\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/soc/agent/services/AppInventory;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "categoryOf", "", "permission", "collect", "", "Lcom/soc/agent/api/dto/InstalledAppDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "permissionsFor", "Lcom/soc/agent/api/dto/AppPermissionDto;", "pm", "Landroid/content/pm/PackageManager;", "pkg", "runningProcessPids", "", "", "signingFingerprint", "Companion", "app_debug"})
public final class AppInventory {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.AppInventory.Companion Companion = null;
    
    public AppInventory(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object collect(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.api.dto.InstalledAppDto>> $completion) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Integer> runningProcessPids() {
        return null;
    }
    
    /**
     * Signature fingerprint: SHA-256 hex of the signing certificate.
     */
    private final java.lang.String signingFingerprint(android.content.pm.PackageManager pm, java.lang.String pkg) {
        return null;
    }
    
    private final java.util.List<com.soc.agent.api.dto.AppPermissionDto> permissionsFor(android.content.pm.PackageManager pm, java.lang.String pkg) {
        return null;
    }
    
    /**
     * Maps a permission string to a concise category for the SOC dashboard.
     */
    private final java.lang.String categoryOf(java.lang.String permission) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2 = {"Lcom/soc/agent/services/AppInventory$Companion;", "", "()V", "scan", "", "Lcom/soc/agent/api/dto/InstalledAppDto;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Synchronous convenience entry point used by the UI (already on an IO
         * dispatcher): collects the full inventory in a single call.
         */
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.api.dto.InstalledAppDto> scan(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}