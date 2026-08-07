package com.soc.agent.data;

import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.LockedAppEntity;
import com.soc.agent.database.entity.UnlockHistoryEntity;

/**
 * Repository for App Lock data — wraps LockedAppDao and UnlockHistoryDao
 * behind a clean API. All methods are suspend functions (call from coroutines).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0013\u0018\u0000 62\u00020\u0001:\u00016B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nH\u0086@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nH\u0086@\u00a2\u0006\u0002\u0010\fJ&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0006\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\u0017\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001cJ*\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$H\u0086@\u00a2\u0006\u0002\u0010%J4\u0010&\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010'\u001a\u00020\u00102\b\b\u0002\u0010(\u001a\u00020\u00102\b\b\u0002\u0010)\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010*J\u001c\u0010+\u001a\u00020\u001e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@\u00a2\u0006\u0002\u0010-J\u001c\u0010.\u001a\u00020\u001e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000e0\nH\u0086@\u00a2\u0006\u0002\u0010-J\u001e\u00100\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u00102J\u0016\u00103\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u00104\u001a\u00020\u001e2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00100\nH\u0086@\u00a2\u0006\u0002\u0010-R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/soc/agent/data/LockedAppsRepository;", "", "db", "Lcom/soc/agent/database/AppDatabase;", "(Lcom/soc/agent/database/AppDatabase;)V", "historyDao", "Lcom/soc/agent/database/dao/UnlockHistoryDao;", "lockedDao", "Lcom/soc/agent/database/dao/LockedAppDao;", "backupAll", "", "Lcom/soc/agent/database/entity/LockedAppEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "backupHistory", "Lcom/soc/agent/database/entity/UnlockHistoryEntity;", "getEnabledPackages", "", "getHistoryForPackage", "packageName", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLockedApps", "getLockedCount", "getUnlockHistory", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isAppLocked", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockApp", "", "lockMethod", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "purgeHistory", "olderThanMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordUnlock", "gateMethod", "appName", "autoUnlock", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreAll", "apps", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreHistory", "records", "setEnabled", "enabled", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlockApp", "unlockApps", "packageNames", "Companion", "app_debug"})
public final class LockedAppsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.database.dao.LockedAppDao lockedDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.database.dao.UnlockHistoryDao historyDao = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.data.LockedAppsRepository INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.data.LockedAppsRepository.Companion Companion = null;
    
    private LockedAppsRepository(com.soc.agent.database.AppDatabase db) {
        super();
    }
    
    /**
     * Lock an app with the given lock method (pin/pattern).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object lockApp(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String lockMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Remove lock for a single app.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object unlockApp(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Remove locks for multiple apps.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object unlockApps(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> packageNames, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Get all locked apps, sorted by name.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLockedApps(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.LockedAppEntity>> $completion) {
        return null;
    }
    
    /**
     * Check if a specific app is locked.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isAppLocked(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Enable or disable lock for an app.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setEnabled(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Get count of locked apps.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLockedCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Get all enabled locked package names.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getEnabledPackages(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
    
    /**
     * Backup all locked apps.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object backupAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.LockedAppEntity>> $completion) {
        return null;
    }
    
    /**
     * Restore locked apps from backup (clears existing).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object restoreAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.LockedAppEntity> apps, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Record an unlock event.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object recordUnlock(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String gateMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, boolean autoUnlock, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Get recent unlock history.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUnlockHistory(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity>> $completion) {
        return null;
    }
    
    /**
     * Get unlock history for a specific app.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getHistoryForPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity>> $completion) {
        return null;
    }
    
    /**
     * Purge unlock history older than the given timestamp.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object purgeHistory(long olderThanMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Backup all unlock history.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object backupHistory(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity>> $completion) {
        return null;
    }
    
    /**
     * Restore unlock history from backup.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object restoreHistory(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity> records, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/soc/agent/data/LockedAppsRepository$Companion;", "", "()V", "INSTANCE", "Lcom/soc/agent/data/LockedAppsRepository;", "getInstance", "db", "Lcom/soc/agent/database/AppDatabase;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.data.LockedAppsRepository getInstance(@org.jetbrains.annotations.NotNull()
        com.soc.agent.database.AppDatabase db) {
            return null;
        }
    }
}