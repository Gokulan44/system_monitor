package com.soc.agent.services;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import com.soc.agent.api.dto.DeviceInfoSample;
import java.io.File;

/**
 * Collects static device identity/OS information. Called once at registration
 * and on each telemetry push (cheap enough for a 15-minute cadence).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002\u00a8\u0006\t"}, d2 = {"Lcom/soc/agent/services/DeviceInfoCollector;", "", "()V", "collect", "Lcom/soc/agent/api/dto/DeviceInfoSample;", "context", "Landroid/content/Context;", "readKernelVersion", "", "app_debug"})
public final class DeviceInfoCollector {
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.DeviceInfoCollector INSTANCE = null;
    
    private DeviceInfoCollector() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.DeviceInfoSample collect(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    private final java.lang.String readKernelVersion() {
        return null;
    }
}