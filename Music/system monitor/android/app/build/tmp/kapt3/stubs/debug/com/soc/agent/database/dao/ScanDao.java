package com.soc.agent.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.soc.agent.database.entity.FileScanLogEntity;
import com.soc.agent.database.entity.MalwareScanLogEntity;
import com.soc.agent.database.entity.ScanRunEntity;
import com.soc.agent.database.entity.ThreatEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u00152\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u001f"}, d2 = {"Lcom/soc/agent/database/dao/ScanDao;", "", "getDetections", "", "Lcom/soc/agent/database/entity/MalwareScanLogEntity;", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFileScanLogs", "Lcom/soc/agent/database/entity/FileScanLogEntity;", "getRecentRuns", "Lcom/soc/agent/database/entity/ScanRunEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentScans", "getScanLogsForRun", "scanId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getThreats", "Lcom/soc/agent/database/entity/ThreatEntity;", "insertFileScanLogs", "", "logs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertMalwareLogs", "insertScanRun", "", "run", "(Lcom/soc/agent/database/entity/ScanRunEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertThreats", "threats", "app_debug"})
@androidx.room.Dao()
public abstract interface ScanDao {
    
    /**
     * Persist a scan run summary; returns the new row id.
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertScanRun(@org.jetbrains.annotations.NotNull()
    com.soc.agent.database.entity.ScanRunEntity run, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertMalwareLogs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.MalwareScanLogEntity> logs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertThreats(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.ThreatEntity> threats, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertFileScanLogs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.database.entity.FileScanLogEntity> logs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Most recent scan runs, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM scan_runs ORDER BY started_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecentScans(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ScanRunEntity>> $completion);
    
    /**
     * Convenience alias used by the UI — the newest scan run(s), newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM scan_runs ORDER BY started_at DESC LIMIT 20")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecentRuns(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ScanRunEntity>> $completion);
    
    /**
     * Latest threats, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM threats ORDER BY detected_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getThreats(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.ThreatEntity>> $completion);
    
    /**
     * Latest file scan log entries, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM file_scan_logs ORDER BY scanned_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFileScanLogs(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.FileScanLogEntity>> $completion);
    
    /**
     * Per-item detection entries belonging to a specific scan id.
     */
    @androidx.room.Query(value = "SELECT * FROM malware_scan_logs WHERE scan_id = :scanId ORDER BY id ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getScanLogsForRun(@org.jetbrains.annotations.NotNull()
    java.lang.String scanId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.MalwareScanLogEntity>> $completion);
    
    /**
     * Items flagged suspicious or malicious, newest first.
     */
    @androidx.room.Query(value = "SELECT * FROM malware_scan_logs WHERE verdict IN ('malicious','suspicious') ORDER BY scanned_at DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDetections(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.soc.agent.database.entity.MalwareScanLogEntity>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}