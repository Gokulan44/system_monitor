package com.soc.agent.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.soc.agent.database.dao.LockedAppDao;
import com.soc.agent.database.dao.UnlockHistoryDao;
import com.soc.agent.database.dao.FailedAttemptDao;
import com.soc.agent.database.dao.IntruderSelfieDao;
import com.soc.agent.database.dao.SecuritySettingsDao;
import com.soc.agent.database.dao.BackupDao;
import com.soc.agent.database.entity.LockedAppEntity;
import com.soc.agent.database.entity.UnlockHistoryEntity;
import com.soc.agent.database.entity.FailedAttemptEntity;
import com.soc.agent.database.entity.IntruderSelfieEntity;
import com.soc.agent.database.entity.SecuritySettingsEntity;
import com.soc.agent.database.entity.BackupEntity;

/**
 * Room database for App Lock module. Isolated from the main [AppDatabase]
 * for modularity — locked apps, unlock history, failed attempts, intruder
 * selfies, security settings, and backups live here.
 *
 * Access via [LockDatabase.getInstance]. All DAOs are exposed as abstract
 * val properties.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&\u00a8\u0006\u0010"}, d2 = {"Lcom/soc/agent/database/LockDatabase;", "Landroidx/room/RoomDatabase;", "()V", "backupDao", "Lcom/soc/agent/database/dao/BackupDao;", "failedAttemptDao", "Lcom/soc/agent/database/dao/FailedAttemptDao;", "intruderSelfieDao", "Lcom/soc/agent/database/dao/IntruderSelfieDao;", "lockedAppDao", "Lcom/soc/agent/database/dao/LockedAppDao;", "securitySettingsDao", "Lcom/soc/agent/database/dao/SecuritySettingsDao;", "unlockHistoryDao", "Lcom/soc/agent/database/dao/UnlockHistoryDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.soc.agent.database.entity.LockedAppEntity.class, com.soc.agent.database.entity.UnlockHistoryEntity.class, com.soc.agent.database.entity.FailedAttemptEntity.class, com.soc.agent.database.entity.IntruderSelfieEntity.class, com.soc.agent.database.entity.SecuritySettingsEntity.class, com.soc.agent.database.entity.BackupEntity.class}, version = 1, exportSchema = false)
public abstract class LockDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DB_NAME = "lock_database.db";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.database.LockDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.database.LockDatabase.Companion Companion = null;
    
    public LockDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.LockedAppDao lockedAppDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.UnlockHistoryDao unlockHistoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.FailedAttemptDao failedAttemptDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.IntruderSelfieDao intruderSelfieDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.SecuritySettingsDao securitySettingsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.soc.agent.database.dao.BackupDao backupDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/soc/agent/database/LockDatabase$Companion;", "", "()V", "DB_NAME", "", "INSTANCE", "Lcom/soc/agent/database/LockDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.database.LockDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}