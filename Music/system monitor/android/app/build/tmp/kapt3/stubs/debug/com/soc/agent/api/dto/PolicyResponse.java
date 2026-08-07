package com.soc.agent.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * GET /api/agent/policies response.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\bH\u00d6\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/soc/agent/api/dto/PolicyResponse;", "", "policies", "", "Lcom/soc/agent/api/dto/PolicyDto;", "blocklist", "Lcom/soc/agent/api/dto/BlocklistDto;", "syncedAt", "", "(Ljava/util/List;Lcom/soc/agent/api/dto/BlocklistDto;Ljava/lang/String;)V", "getBlocklist", "()Lcom/soc/agent/api/dto/BlocklistDto;", "getPolicies", "()Ljava/util/List;", "getSyncedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class PolicyResponse {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.soc.agent.api.dto.PolicyDto> policies = null;
    @org.jetbrains.annotations.Nullable()
    private final com.soc.agent.api.dto.BlocklistDto blocklist = null;
    @com.google.gson.annotations.SerializedName(value = "synced_at")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String syncedAt = null;
    
    public PolicyResponse(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.PolicyDto> policies, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.BlocklistDto blocklist, @org.jetbrains.annotations.Nullable()
    java.lang.String syncedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.PolicyDto> getPolicies() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.BlocklistDto getBlocklist() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSyncedAt() {
        return null;
    }
    
    public PolicyResponse() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.PolicyDto> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.soc.agent.api.dto.BlocklistDto component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.dto.PolicyResponse copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.PolicyDto> policies, @org.jetbrains.annotations.Nullable()
    com.soc.agent.api.dto.BlocklistDto blocklist, @org.jetbrains.annotations.Nullable()
    java.lang.String syncedAt) {
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