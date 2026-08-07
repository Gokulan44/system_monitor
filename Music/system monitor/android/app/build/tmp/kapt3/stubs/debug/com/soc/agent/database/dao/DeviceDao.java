package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Upsert;
import com.soc.agent.database.entity.DeviceEntity;
import com.soc.agent.database.entity.DeviceHistoryEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/soc/agent/database/dao/DeviceDao;", "", "clear", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDevices", "", "Lcom/soc/agent/database/entity/DeviceEntity;", "getDevice", "agentId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestDevice", "insertHistory", "event", "Lcom/soc/agent/database/entity/DeviceHistoryEntity;", "(Lcom/soc/agent/database/entity/DeviceHistoryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertDevice", "device", "(Lcom/soc/agent/database/entity/DeviceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface DeviceDao {
    
    /**
     * Upsert the agent's own device record (matched on the autogen pk).
     */
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertDevice(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.DeviceEntity device, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Look up a device by its stable agent UUID.
     */
    @androidx.room.Query(value = "SELECT * FROM devices WHERE agent_id = :agentId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String agentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.DeviceEntity> $completion);
    
    /**
     * Return every locally-known device, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM devices ORDER BY id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllDevices(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.DeviceEntity>> $completion);
    
    /**
     * Return the single most recent device record.
     */
    @androidx.room.Query(value = "SELECT * FROM devices ORDER BY id DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestDevice(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.DeviceEntity> $completion);
    
    /**
     * Insert a local device history event.
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertHistory(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.DeviceHistoryEntity event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Remove all locally cached device records.
     */
    @androidx.room.Query(value = "DELETE FROM devices")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clear(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}