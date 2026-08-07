package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.soc.agent.database.entity.NetworkLogEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/soc/agent/database/dao/NetworkDao;", "", "deleteOld", "", "cutoffMillis", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestNetwork", "", "Lcom/soc/agent/database/entity/NetworkLogEntity;", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestPhishing", "insertNetworkLogs", "logs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface NetworkDao {
    
    /**
     * Persist network observations. [NetworkLogEntity] carries both
     * interface samples and phishing checks discriminated by
     * [NetworkLogEntity.kind].
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertNetworkLogs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.NetworkLogEntity> logs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Latest interface samples, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM network_logs WHERE kind = 'network' ORDER BY timestamp_millis DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestNetwork(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.NetworkLogEntity>> $completion);
    
    /**
     * Latest phishing URL checks, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM network_logs WHERE kind = 'phishing' ORDER BY timestamp_millis DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestPhishing(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.NetworkLogEntity>> $completion);
    
    /**
     * Prune observations older than [cutoffMillis].
     */
    @androidx.room.Query(value = "DELETE FROM network_logs WHERE timestamp_millis < :cutoffMillis")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOld(long cutoffMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}