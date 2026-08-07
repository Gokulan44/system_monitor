package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soc.agent.database.entity.FileScanLogEntity
import com.soc.agent.database.entity.MalwareScanLogEntity
import com.soc.agent.database.entity.ScanRunEntity
import com.soc.agent.database.entity.ThreatEntity

@Dao
interface ScanDao {

    /** Persist a scan run summary; returns the new row id. */
    @Insert
    suspend fun insertScanRun(run: ScanRunEntity): Long

    @Insert
    suspend fun insertMalwareLogs(logs: List<MalwareScanLogEntity>)

    @Insert
    suspend fun insertThreats(threats: List<ThreatEntity>)

    @Insert
    suspend fun insertFileScanLogs(logs: List<FileScanLogEntity>)

    /** Most recent scan runs, newest first. */
    @Query("SELECT * FROM scan_runs ORDER BY started_at DESC LIMIT :limit")
    suspend fun getRecentScans(limit: Int = 20): List<ScanRunEntity>

    /** Convenience alias used by the UI — the newest scan run(s), newest first. */
    @Query("SELECT * FROM scan_runs ORDER BY started_at DESC LIMIT 20")
    suspend fun getRecentRuns(): List<ScanRunEntity>

    /** Latest threats, newest first. */
    @Query("SELECT * FROM threats ORDER BY detected_at DESC LIMIT :limit")
    suspend fun getThreats(limit: Int = 200): List<ThreatEntity>

    /** Latest file scan log entries, newest first. */
    @Query("SELECT * FROM file_scan_logs ORDER BY scanned_at DESC LIMIT :limit")
    suspend fun getFileScanLogs(limit: Int): List<FileScanLogEntity>

    /** Per-item detection entries belonging to a specific scan id. */
    @Query("SELECT * FROM malware_scan_logs WHERE scan_id = :scanId ORDER BY id ASC")
    suspend fun getScanLogsForRun(scanId: String): List<MalwareScanLogEntity>

    /** Items flagged suspicious or malicious, newest first. */
    @Query("SELECT * FROM malware_scan_logs WHERE verdict IN ('malicious','suspicious') ORDER BY scanned_at DESC LIMIT :limit")
    suspend fun getDetections(limit: Int): List<MalwareScanLogEntity>
}