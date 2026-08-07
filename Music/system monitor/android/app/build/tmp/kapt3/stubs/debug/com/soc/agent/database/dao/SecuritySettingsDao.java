package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.SecuritySettingsEntity;

/**
 * DAO for security settings (singleton row).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/soc/agent/database/dao/SecuritySettingsDao;", "", "clearSettings", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSettings", "Lcom/soc/agent/database/entity/SecuritySettingsEntity;", "upsert", "", "settings", "(Lcom/soc/agent/database/entity/SecuritySettingsEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface SecuritySettingsDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.SecuritySettingsEntity settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM security_settings WHERE id = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.SecuritySettingsEntity> $completion);
    
    @androidx.room.Query(value = "DELETE FROM security_settings WHERE id = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}