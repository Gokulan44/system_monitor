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
 * Everything the Home/Dashboard screen shows in one immutable snapshot.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b)\b\u0086\b\u0018\u00002\u00020\u0001B\u009b\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\u0017J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0010H\u00c6\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u00c6\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003J\t\u00103\u001a\u00020\tH\u00c6\u0003J\t\u00104\u001a\u00020\tH\u00c6\u0003J\t\u00105\u001a\u00020\u0005H\u00c6\u0003J\t\u00106\u001a\u00020\tH\u00c6\u0003J\t\u00107\u001a\u00020\u0005H\u00c6\u0003J\t\u00108\u001a\u00020\u0005H\u00c6\u0003J\u009f\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010:\u001a\u00020\u00032\b\u0010;\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010<\u001a\u00020\u0005H\u00d6\u0001J\t\u0010=\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u001d\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+\u00a8\u0006>"}, d2 = {"Lcom/soc/agent/ui/viewmodel/DashboardState;", "", "loading", "", "score", "", "grade", "", "cpuLoad", "", "memPct", "batteryPct", "storagePct", "threats", "apps", "lastSync", "", "violations", "", "Lcom/soc/agent/security/PolicyViolation;", "blocklist", "", "error", "(ZILjava/lang/String;FFIFIIJLjava/util/List;Ljava/util/Map;Ljava/lang/String;)V", "getApps", "()I", "getBatteryPct", "getBlocklist", "()Ljava/util/Map;", "getCpuLoad", "()F", "getError", "()Ljava/lang/String;", "getGrade", "getLastSync", "()J", "getLoading", "()Z", "getMemPct", "getScore", "getStoragePct", "getThreats", "getViolations", "()Ljava/util/List;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class DashboardState {
    private final boolean loading = false;
    private final int score = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String grade = null;
    private final float cpuLoad = 0.0F;
    private final float memPct = 0.0F;
    private final int batteryPct = 0;
    private final float storagePct = 0.0F;
    private final int threats = 0;
    private final int apps = 0;
    private final long lastSync = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.soc.agent.security.PolicyViolation> violations = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Integer> blocklist = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public DashboardState(boolean loading, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, float cpuLoad, float memPct, int batteryPct, float storagePct, int threats, int apps, long lastSync, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.security.PolicyViolation> violations, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> blocklist, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    public final boolean getLoading() {
        return false;
    }
    
    public final int getScore() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGrade() {
        return null;
    }
    
    public final float getCpuLoad() {
        return 0.0F;
    }
    
    public final float getMemPct() {
        return 0.0F;
    }
    
    public final int getBatteryPct() {
        return 0;
    }
    
    public final float getStoragePct() {
        return 0.0F;
    }
    
    public final int getThreats() {
        return 0;
    }
    
    public final int getApps() {
        return 0;
    }
    
    public final long getLastSync() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.security.PolicyViolation> getViolations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Integer> getBlocklist() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public DashboardState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final long component10() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.security.PolicyViolation> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Integer> component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final float component7() {
        return 0.0F;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.ui.viewmodel.DashboardState copy(boolean loading, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, float cpuLoad, float memPct, int batteryPct, float storagePct, int threats, int apps, long lastSync, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.security.PolicyViolation> violations, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> blocklist, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}