package com.soc.agent.data;

/**
 * UI model for unlock history display. Maps from [UnlockHistoryEntity]
 * with computed fields for presentation.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b$\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\rJ\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\bH\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003JY\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010*\u001a\u00020\b2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020-H\u00d6\u0001J\t\u0010.\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0013\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u0019\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u000fR\u0011\u0010\u001b\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u00060"}, d2 = {"Lcom/soc/agent/data/UnlockHistory;", "", "id", "", "packageName", "", "gateType", "wasSuccessful", "", "unlockDurationMs", "timestampMs", "appLabel", "failureReason", "(JLjava/lang/String;Ljava/lang/String;ZJJLjava/lang/String;Ljava/lang/String;)V", "getAppLabel", "()Ljava/lang/String;", "durationFormatted", "getDurationFormatted", "getFailureReason", "gateLabel", "getGateLabel", "getGateType", "getId", "()J", "getPackageName", "statusLabel", "getStatusLabel", "timeLabel", "getTimeLabel", "getTimestampMs", "getUnlockDurationMs", "getWasSuccessful", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app_debug"})
public final class UnlockHistory {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String gateType = null;
    private final boolean wasSuccessful = false;
    private final long unlockDurationMs = 0L;
    private final long timestampMs = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appLabel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String failureReason = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.data.UnlockHistory.Companion Companion = null;
    
    public UnlockHistory(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String gateType, boolean wasSuccessful, long unlockDurationMs, long timestampMs, @org.jetbrains.annotations.NotNull()
    java.lang.String appLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String failureReason) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackageName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGateType() {
        return null;
    }
    
    public final boolean getWasSuccessful() {
        return false;
    }
    
    public final long getUnlockDurationMs() {
        return 0L;
    }
    
    public final long getTimestampMs() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFailureReason() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDurationFormatted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeLabel() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long component6() {
        return 0L;
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
    public final com.soc.agent.data.UnlockHistory copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String gateType, boolean wasSuccessful, long unlockDurationMs, long timestampMs, @org.jetbrains.annotations.NotNull()
    java.lang.String appLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String failureReason) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/soc/agent/data/UnlockHistory$Companion;", "", "()V", "from", "Lcom/soc/agent/data/UnlockHistory;", "entity", "Lcom/soc/agent/database/entity/UnlockHistoryEntity;", "appLabel", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Create from a database entity. [appLabel] is resolved externally
         * (PackageManager) and passed in for display.
         */
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.data.UnlockHistory from(@org.jetbrains.annotations.NotNull()
        com.soc.agent.database.entity.UnlockHistoryEntity entity, @org.jetbrains.annotations.NotNull()
        java.lang.String appLabel) {
            return null;
        }
    }
}