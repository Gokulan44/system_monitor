package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Static device identity snapshot.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006&"}, d2 = {"Lcom/soc/agent/api/dto/DeviceInfoSample;", "", "manufacturer", "", "model", "kernel", "osName", "osVersion", "securityPatchLevel", "osBuild", "uptimeSec", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getKernel", "()Ljava/lang/String;", "getManufacturer", "getModel", "getOsBuild", "getOsName", "getOsVersion", "getSecurityPatchLevel", "getUptimeSec", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class DeviceInfoSample {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String manufacturer = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String model = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String kernel = null;
    @com.google.gson.annotations.SerializedName(value = "os_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String osName = null;
    @com.google.gson.annotations.SerializedName(value = "os_version")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String osVersion = null;
    @com.google.gson.annotations.SerializedName(value = "security_patch_level")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String securityPatchLevel = null;
    @com.google.gson.annotations.SerializedName(value = "os_build")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String osBuild = null;
    @com.google.gson.annotations.SerializedName(value = "uptime_sec")
    private final long uptimeSec = 0L;
    
    public DeviceInfoSample(@org.jetbrains.annotations.NotNull()
    java.lang.String manufacturer, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String kernel, @org.jetbrains.annotations.NotNull()
    java.lang.String osName, @org.jetbrains.annotations.NotNull()
    java.lang.String osVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String securityPatchLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String osBuild, long uptimeSec) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getManufacturer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKernel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOsName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOsVersion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSecurityPatchLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOsBuild() {
        return null;
    }
    
    public final long getUptimeSec() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.DeviceInfoSample copy(@org.jetbrains.annotations.NotNull()
    java.lang.String manufacturer, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String kernel, @org.jetbrains.annotations.NotNull()
    java.lang.String osName, @org.jetbrains.annotations.NotNull()
    java.lang.String osVersion, @org.jetbrains.annotations.NotNull()
    java.lang.String securityPatchLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String osBuild, long uptimeSec) {
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