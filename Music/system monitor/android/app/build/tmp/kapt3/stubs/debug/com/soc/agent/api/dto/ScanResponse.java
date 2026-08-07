package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * POST /api/agent/scan response — includes the recomputed security score.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003JQ\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\tH\u00d6\u0001J\t\u0010$\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011\u00a8\u0006%"}, d2 = {"Lcom/soc/agent/api/dto/ScanResponse;", "", "ok", "", "scanId", "", "runId", "", "threatsFound", "", "itemsScanned", "score", "grade", "(ZLjava/lang/String;JIIILjava/lang/String;)V", "getGrade", "()Ljava/lang/String;", "getItemsScanned", "()I", "getOk", "()Z", "getRunId", "()J", "getScanId", "getScore", "getThreatsFound", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class ScanResponse {
    private final boolean ok = false;
    @com.google.gson.annotations.SerializedName(value = "scan_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String scanId = null;
    @com.google.gson.annotations.SerializedName(value = "run_id")
    private final long runId = 0L;
    @com.google.gson.annotations.SerializedName(value = "threats_found")
    private final int threatsFound = 0;
    
    /**
     * Reflects the number of items the agent scanned (server echo or local).
     */
    @com.google.gson.annotations.SerializedName(value = "items_scanned")
    private final int itemsScanned = 0;
    private final int score = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String grade = null;
    
    public ScanResponse(boolean ok, @org.jetbrains.annotations.Nullable()
    java.lang.String scanId, long runId, int threatsFound, int itemsScanned, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade) {
        super();
    }
    
    public final boolean getOk() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getScanId() {
        return null;
    }
    
    public final long getRunId() {
        return 0L;
    }
    
    public final int getThreatsFound() {
        return 0;
    }
    
    /**
     * Reflects the number of items the agent scanned (server echo or local).
     */
    public final int getItemsScanned() {
        return 0;
    }
    
    public final int getScore() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGrade() {
        return null;
    }
    
    public ScanResponse() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.ScanResponse copy(boolean ok, @org.jetbrains.annotations.Nullable()
    java.lang.String scanId, long runId, int threatsFound, int itemsScanned, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade) {
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