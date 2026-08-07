package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * One scan run summary. Mirrors the `scan_runs` table on the SOC server,
 * extended with the server-recomputed score/grade for local display.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0006H\u00c6\u0003J\t\u0010!\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\"\u001a\u00020\tH\u00c6\u0003J\t\u0010#\u001a\u00020\tH\u00c6\u0003J\t\u0010$\u001a\u00020\tH\u00c6\u0003J\t\u0010%\u001a\u00020\u0006H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003Jm\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020\tH\u00d6\u0001J\t\u0010,\u001a\u00020\u0006H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0016\u0010\u000b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0016\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017\u00a8\u0006-"}, d2 = {"Lcom/soc/agent/database/entity/ScanRunEntity;", "", "id", "", "deviceId", "scanType", "", "status", "itemsScanned", "", "threatsFound", "score", "grade", "startedAt", "finishedAt", "(JJLjava/lang/String;Ljava/lang/String;IIILjava/lang/String;JJ)V", "getDeviceId", "()J", "getFinishedAt", "getGrade", "()Ljava/lang/String;", "getId", "getItemsScanned", "()I", "getScanType", "getScore", "getStartedAt", "getStatus", "getThreatsFound", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "scan_runs", indices = {@androidx.room.Index(value = {"device_id", "started_at"})})
public final class ScanRunEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "device_id")
    private final long deviceId = 0L;
    
    /**
     * quick / full / apk.
     */
    @androidx.room.ColumnInfo(name = "scan_type")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String scanType = null;
    
    /**
     * running / completed / failed.
     */
    @androidx.room.ColumnInfo(name = "status")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    @androidx.room.ColumnInfo(name = "items_scanned")
    private final int itemsScanned = 0;
    @androidx.room.ColumnInfo(name = "threats_found")
    private final int threatsFound = 0;
    
    /**
     * Security score recomputed by the server (0..100).
     */
    @androidx.room.ColumnInfo(name = "score")
    private final int score = 0;
    
    /**
     * Grade label from the server, e.g. "Excellent".
     */
    @androidx.room.ColumnInfo(name = "grade")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String grade = null;
    @androidx.room.ColumnInfo(name = "started_at")
    private final long startedAt = 0L;
    @androidx.room.ColumnInfo(name = "finished_at")
    private final long finishedAt = 0L;
    
    public ScanRunEntity(long id, long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String scanType, @org.jetbrains.annotations.NotNull()
    java.lang.String status, int itemsScanned, int threatsFound, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, long startedAt, long finishedAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getDeviceId() {
        return 0L;
    }
    
    /**
     * quick / full / apk.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getScanType() {
        return null;
    }
    
    /**
     * running / completed / failed.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    public final int getItemsScanned() {
        return 0;
    }
    
    public final int getThreatsFound() {
        return 0;
    }
    
    /**
     * Security score recomputed by the server (0..100).
     */
    public final int getScore() {
        return 0;
    }
    
    /**
     * Grade label from the server, e.g. "Excellent".
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGrade() {
        return null;
    }
    
    public final long getStartedAt() {
        return 0L;
    }
    
    public final long getFinishedAt() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.ScanRunEntity copy(long id, long deviceId, @org.jetbrains.annotations.NotNull()
    java.lang.String scanType, @org.jetbrains.annotations.NotNull()
    java.lang.String status, int itemsScanned, int threatsFound, int score, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, long startedAt, long finishedAt) {
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