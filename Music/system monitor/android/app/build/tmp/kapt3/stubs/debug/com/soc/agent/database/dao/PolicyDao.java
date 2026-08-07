package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Upsert;
import com.soc.agent.database.entity.IocEntity;
import com.soc.agent.database.entity.PolicyEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0010\u001a\u00020\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0015"}, d2 = {"Lcom/soc/agent/database/dao/PolicyDao;", "", "clearIocs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearPolicies", "getBlockedDomains", "", "", "getBlockedHashes", "getBlockedUrls", "getBlocklist", "Lcom/soc/agent/database/entity/IocEntity;", "getIocs", "getPolicies", "Lcom/soc/agent/database/entity/PolicyEntity;", "upsertIocs", "iocs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertPolicies", "policies", "app_debug"})
@androidx.room.Dao()
public abstract interface PolicyDao {
    
    /**
     * Insert/update server policies (matched on the server policy id).
     */
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertPolicies(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.PolicyEntity> policies, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * All locally cached policies.
     */
    @androidx.room.Query(value = "SELECT * FROM policies ORDER BY id ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPolicies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.PolicyEntity>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM policies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearPolicies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Insert/update IOCs. REPLACE handles the unique (value, type)
     * constraint (later blocklist entries overwrite earlier duplicates).
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertIocs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.IocEntity> iocs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Active IOCs of any type, grouped by type.
     */
    @androidx.room.Query(value = "SELECT * FROM iocs WHERE active = 1 ORDER BY type ASC, value ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getIocs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.IocEntity>> $completion);
    
    /**
     * Active IOCs for blocklist enforcement.
     */
    @androidx.room.Query(value = "SELECT * FROM iocs WHERE active = 1 ORDER BY type ASC, value ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBlocklist(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.IocEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT value FROM iocs WHERE type = 'domain' AND active = 1 ORDER BY value ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBlockedDomains(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT value FROM iocs WHERE type = 'url' AND active = 1 ORDER BY value ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBlockedUrls(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT value FROM iocs WHERE type = 'hash' AND active = 1 ORDER BY value ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBlockedHashes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM iocs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearIocs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}