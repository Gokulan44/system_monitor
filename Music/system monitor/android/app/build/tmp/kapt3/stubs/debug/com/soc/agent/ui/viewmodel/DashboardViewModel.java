package com.soc.agent.ui.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import androidx.lifecycle.AndroidViewModel;
import com.soc.agent.data.SecurityRepository;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.security.IocMatcher;
import com.soc.agent.security.PolicyEngine;
import com.soc.agent.security.PolicyViolation;
import com.soc.agent.services.AppInventory;
import com.soc.agent.services.CpuMonitor;
import com.soc.agent.services.MemoryMonitor;
import com.soc.agent.services.StorageMonitor;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;

/**
 * Aggregates the dashboard snapshot: server heartbeat (score/grade from
 * [SecurityRepository.heartbeat]), a telemetry push, local CPU/memory/storage
 * samples, battery level, persisted threat & app counts, PolicyEngine
 * violations and IocMatcher blocklist counts.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0016"}, d2 = {"Lcom/soc/agent/ui/viewmodel/DashboardViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/soc/agent/ui/viewmodel/DashboardState;", "database", "Lcom/soc/agent/database/AppDatabase;", "repository", "Lcom/soc/agent/data/SecurityRepository;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "readBatteryPct", "", "context", "Landroid/content/Context;", "refresh", "", "app_debug"})
public final class DashboardViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.data.SecurityRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.database.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.soc.agent.ui.viewmodel.DashboardState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.soc.agent.ui.viewmodel.DashboardState> state = null;
    
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.soc.agent.ui.viewmodel.DashboardState> getState() {
        return null;
    }
    
    /**
     * Pulls the full dashboard snapshot. Safe to call repeatedly.
     */
    public final void refresh() {
    }
    
    /**
     * Reads the current battery percentage from the sticky battery-changed intent.
     */
    private final int readBatteryPct(android.content.Context context) {
        return 0;
    }
}