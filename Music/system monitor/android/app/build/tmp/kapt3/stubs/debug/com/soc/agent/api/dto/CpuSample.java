package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * CPU sample, serialised with the snake_case names the server expects.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u00c6\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u00c6\u0003JZ\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001a\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014\u00a8\u0006%"}, d2 = {"Lcom/soc/agent/api/dto/CpuSample;", "", "loadPct", "", "cores", "", "perCore", "", "speedGhz", "tempC", "history", "(DILjava/util/List;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)V", "getCores", "()I", "getHistory", "()Ljava/util/List;", "getLoadPct", "()D", "getPerCore", "getSpeedGhz", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getTempC", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(DILjava/util/List;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)Lcom/soc/agent/api/dto/CpuSample;", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class CpuSample {
    @com.google.gson.annotations.SerializedName(value = "load_pct")
    private final double loadPct = 0.0;
    private final int cores = 0;
    @com.google.gson.annotations.SerializedName(value = "per_core")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Double> perCore = null;
    @com.google.gson.annotations.SerializedName(value = "speed_ghz")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double speedGhz = null;
    @com.google.gson.annotations.SerializedName(value = "temp_c")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double tempC = null;
    
    /**
     * Short rolling load history for the dashboard chart.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Double> history = null;
    
    public CpuSample(double loadPct, int cores, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> perCore, @org.jetbrains.annotations.Nullable()
    java.lang.Double speedGhz, @org.jetbrains.annotations.Nullable()
    java.lang.Double tempC, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> history) {
        super();
    }
    
    public final double getLoadPct() {
        return 0.0;
    }
    
    public final int getCores() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> getPerCore() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getSpeedGhz() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getTempC() {
        return null;
    }
    
    /**
     * Short rolling load history for the dashboard chart.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> getHistory() {
        return null;
    }
    
    public final double component1() {
        return 0.0;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.CpuSample copy(double loadPct, int cores, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> perCore, @org.jetbrains.annotations.Nullable()
    java.lang.Double speedGhz, @org.jetbrains.annotations.Nullable()
    java.lang.Double tempC, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Double> history) {
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