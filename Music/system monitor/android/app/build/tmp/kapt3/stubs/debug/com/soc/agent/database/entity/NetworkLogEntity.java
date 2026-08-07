package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * One network observation. A single table holds both interface samples
 * (kind = "network") and phishing URL checks (kind = "phishing") so the
 * local schema stays compact while mirroring the server's `network_logs`
 * and `phishing_logs` tables.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\bB\b\u0087\b\u0018\u00002\u00020\u0001B\u008d\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0012\u0012\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f\u00a2\u0006\u0002\u0010\u001fJ\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0002\u00101J\u0010\u0010C\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0002\u00101J\t\u0010D\u001a\u00020\u0015H\u00c6\u0003J\t\u0010E\u001a\u00020\u0003H\u00c6\u0003J\t\u0010F\u001a\u00020\u0003H\u00c6\u0003J\t\u0010G\u001a\u00020\u0019H\u00c6\u0003J\t\u0010H\u001a\u00020\u0019H\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010L\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0002\u00101J\u000f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u00c6\u0003J\t\u0010N\u001a\u00020\u0006H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0098\u0002\u0010U\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00122\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u00c6\u0001\u00a2\u0006\u0002\u0010VJ\u0013\u0010W\u001a\u00020\u00152\b\u0010X\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010Y\u001a\u00020\u0012H\u00d6\u0001J\t\u0010Z\u001a\u00020\u0006H\u00d6\u0001R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010!R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010%R\u0016\u0010\u0016\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0016\u0010\u0018\u001a\u00020\u00198\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u00102\u001a\u0004\b0\u00101R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010!R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010#R\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010#R\u0016\u0010\u001a\u001a\u00020\u00198\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010/R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010!R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010!R\u0016\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u00102\u001a\u0004\b;\u00101R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u00102\u001a\u0004\b<\u00101R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010!\u00a8\u0006["}, d2 = {"Lcom/soc/agent/database/entity/NetworkLogEntity;", "", "id", "", "deviceId", "kind", "", "timestampMillis", "iface", "ip4", "ip6", "mac", "state", "defaultGw", "dnsServers", "", "wifiSsid", "wifiRssi", "", "wifiLinkSpeed", "vpnActive", "", "rxBytes", "txBytes", "rxSec", "", "txSec", "url", "verdict", "score", "reasons", "(JJLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;ZJJDDLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)V", "getDefaultGw", "()Ljava/lang/String;", "getDeviceId", "()J", "getDnsServers", "()Ljava/util/List;", "getId", "getIface", "getIp4", "getIp6", "getKind", "getMac", "getReasons", "getRxBytes", "getRxSec", "()D", "getScore", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getState", "getTimestampMillis", "getTxBytes", "getTxSec", "getUrl", "getVerdict", "getVpnActive", "()Z", "getWifiLinkSpeed", "getWifiRssi", "getWifiSsid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;ZJJDDLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)Lcom/soc/agent/database/entity/NetworkLogEntity;", "equals", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "network_logs", indices = {@androidx.room.Index(value = {"kind"}), @androidx.room.Index(value = {"device_id", "timestamp_millis"})})
public final class NetworkLogEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "device_id")
    private final long deviceId = 0L;
    
    /**
     * "network" for interface samples, "phishing" for URL checks.
     */
    @androidx.room.ColumnInfo(name = "kind")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String kind = null;
    @androidx.room.ColumnInfo(name = "timestamp_millis")
    private final long timestampMillis = 0L;
    @androidx.room.ColumnInfo(name = "iface")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String iface = null;
    @androidx.room.ColumnInfo(name = "ip4")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ip4 = null;
    @androidx.room.ColumnInfo(name = "ip6")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ip6 = null;
    @androidx.room.ColumnInfo(name = "mac")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String mac = null;
    
    /**
     * up / down / unknown.
     */
    @androidx.room.ColumnInfo(name = "state")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String state = null;
    @androidx.room.ColumnInfo(name = "default_gw")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String defaultGw = null;
    
    /**
     * DNS servers, JSON-encoded.
     */
    @androidx.room.ColumnInfo(name = "dns_servers")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> dnsServers = null;
    @androidx.room.ColumnInfo(name = "wifi_ssid")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String wifiSsid = null;
    @androidx.room.ColumnInfo(name = "wifi_rssi")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer wifiRssi = null;
    @androidx.room.ColumnInfo(name = "wifi_link_speed")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer wifiLinkSpeed = null;
    @androidx.room.ColumnInfo(name = "vpn_active")
    private final boolean vpnActive = false;
    @androidx.room.ColumnInfo(name = "rx_bytes")
    private final long rxBytes = 0L;
    @androidx.room.ColumnInfo(name = "tx_bytes")
    private final long txBytes = 0L;
    
    /**
     * Bytes per second since the previous sample.
     */
    @androidx.room.ColumnInfo(name = "rx_sec")
    private final double rxSec = 0.0;
    @androidx.room.ColumnInfo(name = "tx_sec")
    private final double txSec = 0.0;
    @androidx.room.ColumnInfo(name = "url")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String url = null;
    
    /**
     * safe / suspicious / phishing.
     */
    @androidx.room.ColumnInfo(name = "verdict")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String verdict = null;
    @androidx.room.ColumnInfo(name = "score")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer score = null;
    
    /**
     * Human-readable reasons, JSON-encoded.
     */
    @androidx.room.ColumnInfo(name = "reasons")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> reasons = null;
    
    public NetworkLogEntity(long id, long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String kind, long timestampMillis, @org.jetbrains.annotations.Nullable()
    java.lang.String iface, @org.jetbrains.annotations.Nullable()
    java.lang.String ip4, @org.jetbrains.annotations.Nullable()
    java.lang.String ip6, @org.jetbrains.annotations.Nullable()
    java.lang.String mac, @org.jetbrains.annotations.Nullable()
    java.lang.String state, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultGw, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> dnsServers, @org.jetbrains.annotations.Nullable()
    java.lang.String wifiSsid, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiRssi, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiLinkSpeed, boolean vpnActive, long rxBytes, long txBytes, double rxSec, double txSec, @org.jetbrains.annotations.Nullable()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String verdict, @org.jetbrains.annotations.Nullable()
    java.lang.Integer score, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> reasons) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getDeviceId() {
        return 0L;
    }
    
    /**
     * "network" for interface samples, "phishing" for URL checks.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKind() {
        return null;
    }
    
    public final long getTimestampMillis() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
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
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDefaultGw() {
        return null;
    }
    
    /**
     * DNS servers, JSON-encoded.
     */
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUrl() {
        return null;
    }
    
    /**
     * safe / suspicious / phishing.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVerdict() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getScore() {
        return null;
    }
    
    /**
     * Human-readable reasons, JSON-encoded.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getReasons() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component14() {
        return null;
    }
    
    public final boolean component15() {
        return false;
    }
    
    public final long component16() {
        return 0L;
    }
    
    public final long component17() {
        return 0L;
    }
    
    public final double component18() {
        return 0.0;
    }
    
    public final double component19() {
        return 0.0;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.NetworkLogEntity copy(long id, long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String kind, long timestampMillis, @org.jetbrains.annotations.Nullable()
    java.lang.String iface, @org.jetbrains.annotations.Nullable()
    java.lang.String ip4, @org.jetbrains.annotations.Nullable()
    java.lang.String ip6, @org.jetbrains.annotations.Nullable()
    java.lang.String mac, @org.jetbrains.annotations.Nullable()
    java.lang.String state, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultGw, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> dnsServers, @org.jetbrains.annotations.Nullable()
    java.lang.String wifiSsid, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiRssi, @org.jetbrains.annotations.Nullable()
    java.lang.Integer wifiLinkSpeed, boolean vpnActive, long rxBytes, long txBytes, double rxSec, double txSec, @org.jetbrains.annotations.Nullable()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String verdict, @org.jetbrains.annotations.Nullable()
    java.lang.Integer score, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> reasons) {
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