package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * GET /api/agent/sync response — heartbeat that also returns policies.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\rH\u00c6\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\u0007H\u00d6\u0001J\t\u0010$\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012\u00a8\u0006%"}, d2 = {"Lcom/soc/agent/api/dto/SyncResponse;", "", "ok", "", "serverTime", "", "score", "", "grade", "policies", "", "Lcom/soc/agent/api/dto/PolicyDto;", "blocklist", "Lcom/soc/agent/api/dto/BlocklistDto;", "(ZLjava/lang/String;ILjava/lang/String;Ljava/util/List;Lcom/soc/agent/api/dto/BlocklistDto;)V", "getBlocklist", "()Lcom/soc/agent/api/dto/BlocklistDto;", "getGrade", "()Ljava/lang/String;", "getOk", "()Z", "getPolicies", "()Ljava/util/List;", "getScore", "()I", "getServerTime", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class SyncResponse {
    private final boolean ok = false;
    @com.google.gson.annotations.SerializedName(value = "server_time")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String serverTime = null;
    private final int score = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String grade = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.soc.agent.api.dto.PolicyDto> policies = null;
    @org.jetbrains.annotations.Nullable()
    private final com.soc.agent.api.dto.BlocklistDto blocklist = null;
    
    public SyncResponse(boolean ok, @org.jetbrains.annotations.Nullable()
    java.lang.String serverTime, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.PolicyDto> policies, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.BlocklistDto blocklist) {
        super();
    }
    
    public final boolean getOk() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getServerTime() {
        return null;
    }
    
    public final int getScore() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGrade() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.PolicyDto> getPolicies() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.BlocklistDto getBlocklist() {
        return null;
    }
    
    public SyncResponse() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.PolicyDto> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.BlocklistDto component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.SyncResponse copy(boolean ok, @org.jetbrains.annotations.Nullable()
    java.lang.String serverTime, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, @org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.PolicyDto> policies, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.BlocklistDto blocklist) {
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