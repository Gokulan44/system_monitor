package com.soc.agent.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.TrafficStats;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import com.soc.agent.api.dto.NetworkSampleDto;
import kotlinx.coroutines.Dispatchers;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.NetworkInterface;
import java.util.Collections;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u000e\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/soc/agent/services/NetworkMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lastRateTime", "", "lastRxBytes", "lastTxBytes", "macFor", "", "ifaceName", "sample", "Lcom/soc/agent/api/dto/NetworkSampleDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class NetworkMonitor {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private long lastRxBytes = -1L;
    private long lastTxBytes = -1L;
    private long lastRateTime = 0L;
    
    public NetworkMonitor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sample(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.api.dto.NetworkSampleDto> $completion) {
        return null;
    }
    
    private final java.lang.String macFor(java.lang.String ifaceName) {
        return null;
    }
}