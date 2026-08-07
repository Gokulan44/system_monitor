package com.soc.agent.services;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import com.soc.agent.api.dto.BatterySample;

/**
 * Samples battery state from the sticky ACTION_BATTERY_CHANGED broadcast.
 * No permission is required to read the sticky battery intent.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/soc/agent/services/BatteryMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sample", "Lcom/soc/agent/api/dto/BatterySample;", "app_debug"})
public final class BatteryMonitor {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public BatteryMonitor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.BatterySample sample() {
        return null;
    }
}