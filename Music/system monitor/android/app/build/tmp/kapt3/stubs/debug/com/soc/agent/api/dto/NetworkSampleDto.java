package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * One network interface sample.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b.\b\u0086\b\u0018\u00002\u00020\u0001B\u00af\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u0017J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010*J\t\u00100\u001a\u00020\u0010H\u00c6\u0003J\t\u00101\u001a\u00020\u0012H\u00c6\u0003J\t\u00102\u001a\u00020\u0012H\u00c6\u0003J\t\u00103\u001a\u00020\u0015H\u00c6\u0003J\t\u00104\u001a\u00020\u0015H\u00c6\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010*J\u00b8\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u00c6\u0001\u00a2\u0006\u0002\u0010>J\u0013\u0010?\u001a\u00020\u00102\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010A\u001a\u00020\rH\u00d6\u0001J\t\u0010B\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0016\u0010\u0013\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0016\u0010\u0016\u001a\u00020\u00158\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0016\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u001a\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b,\u0010*R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0019\u00a8\u0006C"}, d2 = {"Lcom/soc/agent/api/dto/NetworkSampleDto;", "", "iface", "", "ip4", "ip6", "mac", "state", "defaultGw", "dnsServers", "", "wifiSsid", "wifiRssi", "", "wifiLinkSpeed", "vpnActive", "", "rxBytes", "", "txBytes", "rxSec", "", "txSec", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;ZJJDD)V", "getDefaultGw", "()Ljava/lang/String;", "getDnsServers", "()Ljava/util/List;", "getIface", "getIp4", "getIp6", "getMac", "getRxBytes", "()J", "getRxSec", "()D", "getState", "getTxBytes", "getTxSec", "getVpnActive", "()Z", "getWifiLinkSpeed", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getWifiRssi", "getWifiSsid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;ZJJDD)Lcom/soc/agent/api/dto/NetworkSampleDto;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class NetworkSampleDto {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String iface = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ip4 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ip6 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String mac = null;
    
    /**
     * up / down / unknown.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String state = null;
    @com.google.gson.annotations.SerializedName(value = "default_gw")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String defaultGw = null;
    @com.google.gson.annotations.SerializedName(value = "dns_servers")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> dnsServers = null;
    @com.google.gson.annotations.SerializedName(value = "wifi_ssid")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String wifiSsid = null;
    @com.google.gson.annotations.SerializedName(value = "wifi_rssi")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer wifiRssi = null;
    @com.google.gson.annotations.SerializedName(value = "wifi_link_speed")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer wifiLinkSpeed = null;
    @com.google.gson.annotations.SerializedName(value = "vpn_active")
    private final boolean vpnActive = false;
    @com.google.gson.annotations.SerializedName(value = "rx_bytes")
    private final long rxBytes = 0L;
    @com.google.gson.annotations.SerializedName(value = "tx_bytes")
    private final long txBytes = 0L;
    
    /**
     * Bytes per second since the previous sample.
     */
    @com.google.gson.annotations.SerializedName(value = "rx_sec")
    private final double rxSec = 0.0;
    @com.google.gson.annotations.SerializedName(value = "tx_sec")
    private final double txSec = 0.0;
    
    public NetworkSampleDto(@org.jetbrains.annotations.NotNull()
    java.lang.String iface, @org.jetbrains.annotations.Nullable()
    java.lang.String ip4, @org.jetbrains.annotations.Nullable()
    java.lang.String ip6, @org.jetbrains.annotations.Nullable()
    java.lang.String mac, @org.jetbrains.annotations.NotNull()
    java.lang.String state, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultGw, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> dnsServers, @org.jetbrains.annotations.Nullable()
    java.lang.String wifiSsid, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiRssi, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiLinkSpeed, boolean vpnActive, long rxBytes, long txBytes, double rxSec, double txSec) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIface() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getIp4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getIp6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMac() {
        return null;
    }
    
    /**
     * up / down / unknown.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDefaultGw() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getDnsServers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getWifiSsid() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getWifiRssi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getWifiLinkSpeed() {
        return null;
    }
    
    public final boolean getVpnActive() {
        return false;
    }
    
    public final long getRxBytes() {
        return 0L;
    }
    
    public final long getTxBytes() {
        return 0L;
    }
    
    /**
     * Bytes per second since the previous sample.
     */
    public final double getRxSec() {
        return 0.0;
    }
    
    public final double getTxSec() {
        return 0.0;
    }
    
    public NetworkSampleDto() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component10() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final long component12() {
        return 0L;
    }
    
    public final long component13() {
        return 0L;
    }
    
    public final double component14() {
        return 0.0;
    }
    
    public final double component15() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.NetworkSampleDto copy(@org.jetbrains.annotations.NotNull()
    java.lang.String iface, @org.jetbrains.annotations.Nullable()
    java.lang.String ip4, @org.jetbrains.annotations.Nullable()
    java.lang.String ip6, @org.jetbrains.annotations.Nullable()
    java.lang.String mac, @org.jetbrains.annotations.NotNull()
    java.lang.String state, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultGw, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> dnsServers, @org.jetbrains.annotations.Nullable()
    java.lang.String wifiSsid, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiRssi, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiLinkSpeed, boolean vpnActive, long rxBytes, long txBytes, double rxSec, double txSec) {
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