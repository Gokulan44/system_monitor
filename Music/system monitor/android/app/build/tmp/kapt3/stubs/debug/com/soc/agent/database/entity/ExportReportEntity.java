package com.soc.agent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Records of exported reports. One row per export operation.
 * Used to track what was exported, when, and where.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\fH\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003Jc\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0006\u0010(\u001a\u00020\u0005J\t\u0010)\u001a\u00020\fH\u00d6\u0001J\u0006\u0010*\u001a\u00020\u0005J\t\u0010+\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015\u00a8\u0006,"}, d2 = {"Lcom/soc/agent/database/entity/ExportReportEntity;", "", "id", "", "format", "", "scope", "dateFrom", "dateTo", "fileUri", "fileSizeBytes", "recordCount", "", "exportedAt", "(JLjava/lang/String;Ljava/lang/String;JJLjava/lang/String;JIJ)V", "getDateFrom", "()J", "getDateTo", "getExportedAt", "getFileSizeBytes", "getFileUri", "()Ljava/lang/String;", "getFormat", "getId", "getRecordCount", "()I", "getScope", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "fileSizeFormatted", "hashCode", "scopeLabel", "toString", "app_debug"})
@androidx.room.Entity(tableName = "export_report", indices = {@androidx.room.Index(value = {"exported_at"})})
public final class ExportReportEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final long id = 0L;
    
    /**
     * Export format: "csv", "json", or "pdf".
     */
    @androidx.room.ColumnInfo(name = "format")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String format = null;
    
    /**
     * Export scope: "daily", "weekly", "monthly", "all".
     */
    @androidx.room.ColumnInfo(name = "scope")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String scope = null;
    
    /**
     * Date range start epoch millis.
     */
    @androidx.room.ColumnInfo(name = "date_from")
    private final long dateFrom = 0L;
    
    /**
     * Date range end epoch millis.
     */
    @androidx.room.ColumnInfo(name = "date_to")
    private final long dateTo = 0L;
    
    /**
     * Output file URI (absolute path).
     */
    @androidx.room.ColumnInfo(name = "file_uri")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fileUri = null;
    
    /**
     * File size in bytes.
     */
    @androidx.room.ColumnInfo(name = "file_size_bytes")
    private final long fileSizeBytes = 0L;
    
    /**
     * Number of records exported.
     */
    @androidx.room.ColumnInfo(name = "record_count")
    private final int recordCount = 0;
    
    /**
     * Exported timestamp.
     */
    @androidx.room.ColumnInfo(name = "exported_at")
    private final long exportedAt = 0L;
    
    public ExportReportEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    java.lang.String scope, long dateFrom, long dateTo, @org.jetbrains.annotations.NotNull()
    java.lang.String fileUri, long fileSizeBytes, int recordCount, long exportedAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    /**
     * Export format: "csv", "json", or "pdf".
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormat() {
        return null;
    }
    
    /**
     * Export scope: "daily", "weekly", "monthly", "all".
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getScope() {
        return null;
    }
    
    /**
     * Date range start epoch millis.
     */
    public final long getDateFrom() {
        return 0L;
    }
    
    /**
     * Date range end epoch millis.
     */
    public final long getDateTo() {
        return 0L;
    }
    
    /**
     * Output file URI (absolute path).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFileUri() {
        return null;
    }
    
    /**
     * File size in bytes.
     */
    public final long getFileSizeBytes() {
        return 0L;
    }
    
    /**
     * Number of records exported.
     */
    public final int getRecordCount() {
        return 0;
    }
    
    /**
     * Exported timestamp.
     */
    public final long getExportedAt() {
        return 0L;
    }
    
    /**
     * Human-readable file size (e.g. "1.2 MB").
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fileSizeFormatted() {
        return null;
    }
    
    /**
     * Human-readable scope label.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String scopeLabel() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.database.entity.ExportReportEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String format, @org.jetbrains.annotations.NotNull()
    java.lang.String scope, long dateFrom, long dateTo, @org.jetbrains.annotations.NotNull()
    java.lang.String fileUri, long fileSizeBytes, int recordCount, long exportedAt) {
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