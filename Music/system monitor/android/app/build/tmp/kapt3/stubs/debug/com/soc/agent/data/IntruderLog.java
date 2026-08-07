package com.soc.agent.data;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UI model for intruder selfie/log display. Represents a failed unlock attempt
 * where the intruder selfie was captured (with user consent).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0086\b\u0018\u0000 42\u00020\u0001:\u00014BO\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\rJ\t\u0010'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J[\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005H\u00c6\u0001J\u0013\u00100\u001a\u00020\u00192\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\tH\u00d6\u0001J\t\u00103\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0016\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0018\u001a\u00020\u00198F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000fR\u0011\u0010 \u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\u000fR\u0011\u0010\"\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u000fR\u0011\u0010$\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001d\u00a8\u00065"}, d2 = {"Lcom/soc/agent/data/IntruderLog;", "", "id", "", "packageName", "", "timestampMs", "failedGateType", "attemptCount", "", "selfieUri", "appLabel", "deviceInfo", "(JLjava/lang/String;JLjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppLabel", "()Ljava/lang/String;", "getAttemptCount", "()I", "dateTimeLabel", "getDateTimeLabel", "getDeviceInfo", "getFailedGateType", "gateLabel", "getGateLabel", "hasSelfie", "", "getHasSelfie", "()Z", "getId", "()J", "getPackageName", "getSelfieUri", "statusLabel", "getStatusLabel", "summaryLabel", "getSummaryLabel", "timeLabel", "getTimeLabel", "getTimestampMs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "Companion", "app_debug"})
public final class IntruderLog {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    private final long timestampMs = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String failedGateType = null;
    private final int attemptCount = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String selfieUri = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appLabel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String deviceInfo = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.data.IntruderLog.Companion Companion = null;
    
    public IntruderLog(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, long timestampMs, @org.jetbrains.annotations.NotNull()
    java.lang.String failedGateType, int attemptCount, @org.jetbrains.annotations.Nullable()
    java.lang.String selfieUri, @org.jetbrains.annotations.NotNull()
    java.lang.String appLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String deviceInfo) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackageName() {
        return null;
    }
    
    public final long getTimestampMs() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFailedGateType() {
        return null;
    }
    
    public final int getAttemptCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSelfieUri() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeviceInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGateLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatusLabel() {
        return null;
    }
    
    public final boolean getHasSelfie() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateTimeLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSummaryLabel() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.data.IntruderLog copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, long timestampMs, @org.jetbrains.annotations.NotNull()
    java.lang.String failedGateType, int attemptCount, @org.jetbrains.annotations.Nullable()
    java.lang.String selfieUri, @org.jetbrains.annotations.NotNull()
    java.lang.String appLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String deviceInfo) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/soc/agent/data/IntruderLog$Companion;", "", "()V", "from", "Lcom/soc/agent/data/IntruderLog;", "entity", "Lcom/soc/agent/data/IntruderLogEntity;", "appLabel", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Create from a database entity. [appLabel] is resolved externally
         * via PackageManager.
         */
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.data.IntruderLog from(@org.jetbrains.annotations.NotNull()
        com.soc.agent.data.IntruderLogEntity entity, @org.jetbrains.annotations.NotNull()
        java.lang.String appLabel) {
            return null;
        }
    }
}