package com.soc.agent.security;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.*;
import kotlinx.coroutines.Dispatchers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Locale;

/**
 * Manager for App Lock backup and restore functionality.
 * Exports/imports all App Lock data as JSON.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0019\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001aR\u0016\u0010\u0002\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/soc/agent/security/BackupManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "db", "Lcom/soc/agent/database/AppDatabase;", "gson", "Lcom/google/gson/Gson;", "createBackup", "Lcom/soc/agent/database/entity/BackupEntity;", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBackup", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportBackupToFile", "Landroid/net/Uri;", "backup", "(Lcom/soc/agent/database/entity/BackupEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importBackupFromFile", "uri", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreBackupFromUri", "", "BackupData", "Companion", "app_debug"})
public final class BackupManager {
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.database.AppDatabase db = null;
    private final com.google.gson.Gson gson = null;
    private final android.content.Context context = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.security.BackupManager INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.BackupManager.Companion Companion = null;
    
    private BackupManager(android.content.Context context) {
        super();
    }
    
    /**
     * Export all App Lock data to a JSON file.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createBackup(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.BackupEntity> $completion) {
        return null;
    }
    
    /**
     * Restore App Lock data from Uri.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object restoreBackupFromUri(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Export backup to a file (for sharing/external storage).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportBackupToFile(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.BackupEntity backup, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.net.Uri> $completion) {
        return null;
    }
    
    /**
     * Import backup from a file URI.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importBackupFromFile(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.soc.agent.database.entity.BackupEntity> $completion) {
        return null;
    }
    
    /**
     * Delete a backup by ID.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBackup(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Data structure for backup export.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0011J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003Ji\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00c6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020\u0003H\u00d6\u0001J\t\u0010)\u001a\u00020*H\u00d6\u0001R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006+"}, d2 = {"Lcom/soc/agent/security/BackupManager$BackupData;", "", "version", "", "timestamp", "", "lockedApps", "", "Lcom/soc/agent/database/entity/LockedAppEntity;", "unlockHistory", "Lcom/soc/agent/database/entity/UnlockHistoryEntity;", "failedAttempts", "Lcom/soc/agent/database/entity/FailedAttemptEntity;", "intruderSelfies", "Lcom/soc/agent/database/entity/IntruderSelfieEntity;", "securitySettings", "Lcom/soc/agent/database/entity/SecuritySettingsEntity;", "(IJLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/soc/agent/database/entity/SecuritySettingsEntity;)V", "getFailedAttempts", "()Ljava/util/List;", "getIntruderSelfies", "getLockedApps", "getSecuritySettings", "()Lcom/soc/agent/database/entity/SecuritySettingsEntity;", "getTimestamp", "()J", "getUnlockHistory", "getVersion", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class BackupData {
        private final int version = 0;
        private final long timestamp = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.soc.agent.database.entity.LockedAppEntity> lockedApps = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity> unlockHistory = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.soc.agent.database.entity.FailedAttemptEntity> failedAttempts = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity> intruderSelfies = null;
        @org.jetbrains.annotations.Nullable()
        private final com.soc.agent.database.entity.SecuritySettingsEntity securitySettings = null;
        
        public BackupData(int version, long timestamp, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.LockedAppEntity> lockedApps, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity> unlockHistory, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.FailedAttemptEntity> failedAttempts, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity> intruderSelfies, @org.jetbrains.annotations.Nullable()
        com.soc.agent.database.entity.SecuritySettingsEntity securitySettings) {
            super();
        }
        
        public final int getVersion() {
            return 0;
        }
        
        public final long getTimestamp() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.LockedAppEntity> getLockedApps() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity> getUnlockHistory() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.FailedAttemptEntity> getFailedAttempts() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity> getIntruderSelfies() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.soc.agent.database.entity.SecuritySettingsEntity getSecuritySettings() {
            return null;
        }
        
        public BackupData() {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final long component2() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.LockedAppEntity> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.FailedAttemptEntity> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.soc.agent.database.entity.SecuritySettingsEntity component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.security.BackupManager.BackupData copy(int version, long timestamp, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.LockedAppEntity> lockedApps, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.UnlockHistoryEntity> unlockHistory, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.FailedAttemptEntity> failedAttempts, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.database.entity.IntruderSelfieEntity> intruderSelfies, @org.jetbrains.annotations.Nullable()
        com.soc.agent.database.entity.SecuritySettingsEntity securitySettings) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/soc/agent/security/BackupManager$Companion;", "", "()V", "INSTANCE", "Lcom/soc/agent/security/BackupManager;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.security.BackupManager getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}