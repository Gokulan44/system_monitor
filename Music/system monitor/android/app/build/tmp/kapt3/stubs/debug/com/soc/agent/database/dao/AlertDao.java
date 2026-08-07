package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.soc.agent.database.entity.AlertEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000e\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0011\u001a\u00020\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/soc/agent/database/dao/AlertDao;", "", "countUnread", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOld", "", "cutoffMillis", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlerts", "", "Lcom/soc/agent/database/entity/AlertEntity;", "getAllAlerts", "limit", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnreadAlerts", "insertAlerts", "alerts", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markRead", "id", "app_debug"})
@androidx.room.Dao()
public abstract interface AlertDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAlerts(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.AlertEntity> alerts, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Latest alerts, newest first (regardless of read state).
     */
    @androidx.room.Query(value = "SELECT * FROM alerts ORDER BY created_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAlerts(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AlertEntity>> $completion);
    
    /**
     * Convenience alias used by the UI — all persisted alerts, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM alerts ORDER BY created_at DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAlerts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AlertEntity>> $completion);
    
    /**
     * Unread alerts, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM alerts WHERE read = 0 ORDER BY created_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnreadAlerts(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AlertEntity>> $completion);
    
    /**
     * Count of unread alerts (for badges).
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM alerts WHERE read = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countUnread(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "UPDATE alerts SET read = 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markRead(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Prune alerts older than [cutoffMillis] to bound local storage.
     */
    @androidx.room.Query(value = "DELETE FROM alerts WHERE created_at < :cutoffMillis")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOld(long cutoffMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}