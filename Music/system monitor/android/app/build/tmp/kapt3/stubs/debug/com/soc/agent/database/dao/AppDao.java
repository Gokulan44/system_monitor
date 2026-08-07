package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.soc.agent.database.entity.AppPermissionEntity;
import com.soc.agent.database.entity.InstalledAppEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/database/dao/AppDao;", "", "clearApps", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearPermissions", "getAllApps", "", "Lcom/soc/agent/database/entity/InstalledAppEntity;", "getAppsByRisk", "risk", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPermissionsForApp", "Lcom/soc/agent/database/entity/AppPermissionEntity;", "deviceId", "", "packageName", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertApps", "apps", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPermissions", "permissions", "app_debug"})
@androidx.room.Dao()
public abstract interface AppDao {
    
    /**
     * Insert/update the installed app inventory, replacing on conflict.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertApps(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.InstalledAppEntity> apps, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Insert/update per-app permission records.
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPermissions(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.AppPermissionEntity> permissions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * All installed apps, alphabetically.
     */
    @androidx.room.Query(value = "SELECT * FROM installed_apps ORDER BY name COLLATE NOCASE ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllApps(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.InstalledAppEntity>> $completion);
    
    /**
     * Apps at or above the given risk level.
     */
    @androidx.room.Query(value = "SELECT * FROM installed_apps WHERE risk = :risk ORDER BY name COLLATE NOCASE ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAppsByRisk(@org.jetbrains.annotations.NotNull()
    java.lang.String risk, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.InstalledAppEntity>> $completion);
    
    /**
     * Permissions declared by one package.
     */
    @androidx.room.Query(value = "SELECT * FROM app_permissions WHERE device_id = :deviceId AND package_name = :packageName ORDER BY category ASC, name ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPermissionsForApp(long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.AppPermissionEntity>> $completion);
    
    /**
     * Wipe the app inventory (e.g. before a fresh full inventory sync).
     */
    @androidx.room.Query(value = "DELETE FROM installed_apps")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearApps(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Wipe all permission records.
     */
    @androidx.room.Query(value = "DELETE FROM app_permissions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearPermissions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}