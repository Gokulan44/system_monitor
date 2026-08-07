package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * POST /api/agent/scan payload. Timestamps are ISO-8601 strings.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003JW\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\u0006H\u00d6\u0001J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013\u00a8\u0006%"}, d2 = {"Lcom/soc/agent/api/dto/ScanRequest;", "", "scanType", "", "status", "itemsScanned", "", "threatsFound", "startedAt", "finishedAt", "items", "", "Lcom/soc/agent/api/dto/ScanItemDto;", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getFinishedAt", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "getItemsScanned", "()I", "getScanType", "getStartedAt", "getStatus", "getThreatsFound", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class ScanRequest {
    @com.google.gson.annotations.SerializedName(value = "scan_type")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String scanType = null;
    
    /**
     * running / completed / failed.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    @com.google.gson.annotations.SerializedName(value = "items_scanned")
    private final int itemsScanned = 0;
    @com.google.gson.annotations.SerializedName(value = "threats_found")
    private final int threatsFound = 0;
    @com.google.gson.annotations.SerializedName(value = "started_at")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String startedAt = null;
    @com.google.gson.annotations.SerializedName(value = "finished_at")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String finishedAt = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.soc.agent.api.dto.ScanItemDto> items = null;
    
    public ScanRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String scanType, @org.jetbrains.annotations.NotNull()
    java.lang.String status, int itemsScanned, int threatsFound, @org.jetbrains.annotations.NotNull()
    java.lang.String startedAt, @org.jetbrains.annotations.Nullable()
    java.lang.String finishedAt, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.ScanItemDto> items) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getScanType() {
        return null;
    }
    
    /**
     * running / completed / failed.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    public final int getItemsScanned() {
        return 0;
    }
    
    public final int getThreatsFound() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStartedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFinishedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.ScanItemDto> getItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
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
    public final java.util.List<com.soc.agent.api.dto.ScanItemDto> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.ScanRequest copy(@org.jetbrains.annotations.NotNull()
    java.lang.String scanType, @org.jetbrains.annotations.NotNull()
    java.lang.String status, int itemsScanned, int threatsFound, @org.jetbrains.annotations.NotNull()
    java.lang.String startedAt, @org.jetbrains.annotations.Nullable()
    java.lang.String finishedAt, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.ScanItemDto> items) {
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