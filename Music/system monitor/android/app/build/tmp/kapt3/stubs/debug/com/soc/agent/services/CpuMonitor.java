package com.soc.agent.services;

import android.content.Context;
import com.soc.agent.api.dto.CpuSample;
import java.io.File;

/**
 * Samples CPU load by diffing /proc/stat over a 300 ms window and reads
 * topology/speed/thermal data from /proc/cpuinfo and /sys/class/thermal.
 *
 * Keeps a rolling history of the last 60 aggregate load values that is included
 * in each sample so the UI/server can render a trend sparkline.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0014\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u001a\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u0010H\u0002J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0002\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/soc/agent/services/CpuMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "history", "Lkotlin/collections/ArrayDeque;", "", "extractMhz", "raw", "", "loadBetween", "a", "Lcom/soc/agent/services/CpuMonitor$Stat;", "b", "readCpuInfo", "Lkotlin/Pair;", "", "readStat", "", "readThermal", "()Ljava/lang/Double;", "sample", "Lcom/soc/agent/api/dto/CpuSample;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Stat", "app_debug"})
public final class CpuMonitor {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.collections.ArrayDeque<java.lang.Double> history = null;
    private static final int HISTORY_SIZE = 60;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.services.CpuMonitor.Companion Companion = null;
    
    public CpuMonitor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sample(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.CpuSample> $completion) {
        return null;
    }
    
    private final kotlin.Pair<com.soc.agent.services.CpuMonitor.Stat, java.util.List<com.soc.agent.services.CpuMonitor.Stat>> readStat() {
        return null;
    }
    
    private final double loadBetween(com.soc.agent.services.CpuMonitor.Stat a, com.soc.agent.services.CpuMonitor.Stat b) {
        return 0.0;
    }
    
    /**
     * Returns (maxSpeedMHz, coreCount) parsed from /proc/cpuinfo.
     */
    private final kotlin.Pair<java.lang.Double, java.lang.Integer> readCpuInfo() {
        return null;
    }
    
    private final double extractMhz(java.lang.String raw) {
        return 0.0;
    }
    
    /**
     * Thermal zone temp in Celsius (millidegrees read from sysfs).
     */
    private final java.lang.Double readThermal() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/soc/agent/services/CpuMonitor$Companion;", "", "()V", "HISTORY_SIZE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    /**
     * Cumulative idle + total CPU time from /proc/stat.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/soc/agent/services/CpuMonitor$Stat;", "", "idle", "", "total", "(JJ)V", "getIdle", "()J", "getTotal", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    static final class Stat {
        private final long idle = 0L;
        private final long total = 0L;
        
        public Stat(long idle, long total) {
            super();
        }
        
        public final long getIdle() {
            return 0L;
        }
        
        public final long getTotal() {
            return 0L;
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.services.CpuMonitor.Stat copy(long idle, long total) {
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
}