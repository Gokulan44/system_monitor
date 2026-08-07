package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Battery status sample.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014JB\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00d6\u0001J\t\u0010 \u001a\u00020\bH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006!"}, d2 = {"Lcom/soc/agent/api/dto/BatterySample;", "", "hasBattery", "", "percent", "", "charging", "status", "", "timeRemaining", "", "(ZIZLjava/lang/String;Ljava/lang/Long;)V", "getCharging", "()Z", "getHasBattery", "getPercent", "()I", "getStatus", "()Ljava/lang/String;", "getTimeRemaining", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "copy", "(ZIZLjava/lang/String;Ljava/lang/Long;)Lcom/soc/agent/api/dto/BatterySample;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class BatterySample {
    @com.google.gson.annotations.SerializedName(value = "has_battery")
    private final boolean hasBattery = false;
    private final int percent = 0;
    private final boolean charging = false;
    
    /**
     * charging / discharging / full / not_charging / unknown.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    @com.google.gson.annotations.SerializedName(value = "time_remaining")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long timeRemaining = null;
    
    public BatterySample(boolean hasBattery, int percent, boolean charging, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.Long timeRemaining) {
        super();
    }
    
    public final boolean getHasBattery() {
        return false;
    }
    
    public final int getPercent() {
        return 0;
    }
    
    public final boolean getCharging() {
        return false;
    }
    
    /**
     * charging / discharging / full / not_charging / unknown.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getTimeRemaining() {
        return null;
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.BatterySample copy(boolean hasBattery, int percent, boolean charging, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.Long timeRemaining) {
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