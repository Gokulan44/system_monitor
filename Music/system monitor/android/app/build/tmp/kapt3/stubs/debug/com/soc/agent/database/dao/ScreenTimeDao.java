package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.ScreenTimeEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J&\u0010\u0013\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u001b\u00a8\u0006\u001c"}, d2 = {"Lcom/soc/agent/database/dao/ScreenTimeDao;", "", "deleteOlderThan", "", "beforeMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllByGranularity", "", "Lcom/soc/agent/database/entity/ScreenTimeEntity;", "granularity", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForBucket", "bucketStart", "getRecent", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalInRange", "fromMs", "toMs", "(Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "entity", "(Lcom/soc/agent/database/entity/ScreenTimeEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ScreenTimeDao {
    
    /**
     * Upsert (insert or replace).
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.ScreenTimeEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * All buckets for a given granularity, ordered newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM screen_time WHERE granularity = :granularity ORDER BY bucket_start DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllByGranularity(@org.jetbrains.annotations.NotNull()
    java.lang.String granularity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ScreenTimeEntity>> $completion);
    
    /**
     * Single bucket by start time.
     */
    @androidx.room.Query(value = "SELECT * FROM screen_time WHERE bucket_start = :bucketStart LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getForBucket(long bucketStart, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.ScreenTimeEntity> $completion);
    
    /**
     * Recent buckets across all granularities.
     */
    @androidx.room.Query(value = "SELECT * FROM screen_time ORDER BY updated_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ScreenTimeEntity>> $completion);
    
    /**
     * Total screen time for a granularity range.
     */
    @androidx.room.Query(value = "SELECT COALESCE(SUM(total_time_ms), 0) FROM screen_time WHERE granularity = :granularity AND bucket_start BETWEEN :fromMs AND :toMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String granularity, long fromMs, long toMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * Delete rows older than a given epoch millis.
     */
    @androidx.room.Query(value = "DELETE FROM screen_time WHERE bucket_start < :beforeMs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long beforeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Count all screen_time rows.
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM screen_time")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}