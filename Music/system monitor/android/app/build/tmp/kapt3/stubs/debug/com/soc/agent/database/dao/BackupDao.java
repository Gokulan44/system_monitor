package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.BackupEntity;
import kotlinx.coroutines.flow.Flow;

/**
 * DAO for backup records.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0013"}, d2 = {"Lcom/soc/agent/database/dao/BackupDao;", "", "deleteBackup", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOlderThan", "before", "getAllBackups", "", "Lcom/soc/agent/database/entity/BackupEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBackupById", "insert", "", "backup", "(Lcom/soc/agent/database/entity/BackupEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "totalCount", "app_debug"})
@androidx.room.Dao()
public abstract interface BackupDao {
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.BackupEntity backup, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM backups ORDER BY created_at DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllBackups(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.BackupEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM backups WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBackupById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.BackupEntity> $completion);
    
    @androidx.room.Query(value = "DELETE FROM backups WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBackup(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM backups WHERE created_at < :before")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long before, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM backups")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object totalCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}