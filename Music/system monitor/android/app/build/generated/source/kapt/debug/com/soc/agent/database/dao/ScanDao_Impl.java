package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soc.agent.database.entity.FileScanLogEntity;
import com.soc.agent.database.entity.MalwareScanLogEntity;
import com.soc.agent.database.entity.ScanRunEntity;
import com.soc.agent.database.entity.ThreatEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ScanDao_Impl implements ScanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ScanRunEntity> __insertionAdapterOfScanRunEntity;

  private final EntityInsertionAdapter<MalwareScanLogEntity> __insertionAdapterOfMalwareScanLogEntity;

  private final EntityInsertionAdapter<ThreatEntity> __insertionAdapterOfThreatEntity;

  private final EntityInsertionAdapter<FileScanLogEntity> __insertionAdapterOfFileScanLogEntity;

  public ScanDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScanRunEntity = new EntityInsertionAdapter<ScanRunEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `scan_runs` (`id`,`device_id`,`scan_type`,`status`,`items_scanned`,`threats_found`,`score`,`grade`,`started_at`,`finished_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ScanRunEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        if (entity.getScanType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getScanType());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getStatus());
        }
        statement.bindLong(5, entity.getItemsScanned());
        statement.bindLong(6, entity.getThreatsFound());
        statement.bindLong(7, entity.getScore());
        if (entity.getGrade() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getGrade());
        }
        statement.bindLong(9, entity.getStartedAt());
        statement.bindLong(10, entity.getFinishedAt());
      }
    };
    this.__insertionAdapterOfMalwareScanLogEntity = new EntityInsertionAdapter<MalwareScanLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `malware_scan_logs` (`id`,`device_id`,`scan_id`,`item`,`kind`,`hash`,`match_name`,`verdict`,`severity`,`detail`,`quarantined`,`scanned_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MalwareScanLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        if (entity.getScanId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getScanId());
        }
        if (entity.getItem() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getItem());
        }
        if (entity.getKind() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getKind());
        }
        if (entity.getHash() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getHash());
        }
        if (entity.getMatchName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getMatchName());
        }
        if (entity.getVerdict() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getVerdict());
        }
        if (entity.getSeverity() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSeverity());
        }
        if (entity.getDetail() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDetail());
        }
        final int _tmp = entity.getQuarantined() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindLong(12, entity.getScannedAt());
      }
    };
    this.__insertionAdapterOfThreatEntity = new EntityInsertionAdapter<ThreatEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `threats` (`id`,`device_id`,`kind`,`title`,`severity`,`detail`,`status`,`detected_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThreatEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        if (entity.getKind() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getKind());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTitle());
        }
        if (entity.getSeverity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getSeverity());
        }
        if (entity.getDetail() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDetail());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
        statement.bindLong(8, entity.getDetectedAt());
      }
    };
    this.__insertionAdapterOfFileScanLogEntity = new EntityInsertionAdapter<FileScanLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `file_scan_logs` (`id`,`device_id`,`scan_type`,`path`,`name`,`ext`,`size_b`,`sha256`,`verdict`,`detail`,`scanned_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FileScanLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        if (entity.getScanType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getScanType());
        }
        if (entity.getPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPath());
        }
        if (entity.getName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getName());
        }
        if (entity.getExt() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getExt());
        }
        statement.bindLong(7, entity.getSizeB());
        if (entity.getSha256() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getSha256());
        }
        if (entity.getVerdict() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getVerdict());
        }
        if (entity.getDetail() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDetail());
        }
        statement.bindLong(11, entity.getScannedAt());
      }
    };
  }

  @Override
  public Object insertScanRun(final ScanRunEntity run,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfScanRunEntity.insertAndReturnId(run);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMalwareLogs(final List<MalwareScanLogEntity> logs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMalwareScanLogEntity.insert(logs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertThreats(final List<ThreatEntity> threats,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfThreatEntity.insert(threats);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFileScanLogs(final List<FileScanLogEntity> logs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFileScanLogEntity.insert(logs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecentScans(final int limit,
      final Continuation<? super List<ScanRunEntity>> $completion) {
    final String _sql = "SELECT * FROM scan_runs ORDER BY started_at DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ScanRunEntity>>() {
      @Override
      @NonNull
      public List<ScanRunEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfScanType = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfItemsScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "items_scanned");
          final int _cursorIndexOfThreatsFound = CursorUtil.getColumnIndexOrThrow(_cursor, "threats_found");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finished_at");
          final List<ScanRunEntity> _result = new ArrayList<ScanRunEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScanRunEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpScanType;
            if (_cursor.isNull(_cursorIndexOfScanType)) {
              _tmpScanType = null;
            } else {
              _tmpScanType = _cursor.getString(_cursorIndexOfScanType);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final int _tmpItemsScanned;
            _tmpItemsScanned = _cursor.getInt(_cursorIndexOfItemsScanned);
            final int _tmpThreatsFound;
            _tmpThreatsFound = _cursor.getInt(_cursorIndexOfThreatsFound);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final String _tmpGrade;
            if (_cursor.isNull(_cursorIndexOfGrade)) {
              _tmpGrade = null;
            } else {
              _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            }
            final long _tmpStartedAt;
            _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            final long _tmpFinishedAt;
            _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            _item = new ScanRunEntity(_tmpId,_tmpDeviceId,_tmpScanType,_tmpStatus,_tmpItemsScanned,_tmpThreatsFound,_tmpScore,_tmpGrade,_tmpStartedAt,_tmpFinishedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecentRuns(final Continuation<? super List<ScanRunEntity>> $completion) {
    final String _sql = "SELECT * FROM scan_runs ORDER BY started_at DESC LIMIT 20";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ScanRunEntity>>() {
      @Override
      @NonNull
      public List<ScanRunEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfScanType = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfItemsScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "items_scanned");
          final int _cursorIndexOfThreatsFound = CursorUtil.getColumnIndexOrThrow(_cursor, "threats_found");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "started_at");
          final int _cursorIndexOfFinishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "finished_at");
          final List<ScanRunEntity> _result = new ArrayList<ScanRunEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScanRunEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpScanType;
            if (_cursor.isNull(_cursorIndexOfScanType)) {
              _tmpScanType = null;
            } else {
              _tmpScanType = _cursor.getString(_cursorIndexOfScanType);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final int _tmpItemsScanned;
            _tmpItemsScanned = _cursor.getInt(_cursorIndexOfItemsScanned);
            final int _tmpThreatsFound;
            _tmpThreatsFound = _cursor.getInt(_cursorIndexOfThreatsFound);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final String _tmpGrade;
            if (_cursor.isNull(_cursorIndexOfGrade)) {
              _tmpGrade = null;
            } else {
              _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            }
            final long _tmpStartedAt;
            _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            final long _tmpFinishedAt;
            _tmpFinishedAt = _cursor.getLong(_cursorIndexOfFinishedAt);
            _item = new ScanRunEntity(_tmpId,_tmpDeviceId,_tmpScanType,_tmpStatus,_tmpItemsScanned,_tmpThreatsFound,_tmpScore,_tmpGrade,_tmpStartedAt,_tmpFinishedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getThreats(final int limit,
      final Continuation<? super List<ThreatEntity>> $completion) {
    final String _sql = "SELECT * FROM threats ORDER BY detected_at DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ThreatEntity>>() {
      @Override
      @NonNull
      public List<ThreatEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfKind = CursorUtil.getColumnIndexOrThrow(_cursor, "kind");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfDetectedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "detected_at");
          final List<ThreatEntity> _result = new ArrayList<ThreatEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ThreatEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpKind;
            if (_cursor.isNull(_cursorIndexOfKind)) {
              _tmpKind = null;
            } else {
              _tmpKind = _cursor.getString(_cursorIndexOfKind);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpSeverity;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmpSeverity = null;
            } else {
              _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            }
            final String _tmpDetail;
            if (_cursor.isNull(_cursorIndexOfDetail)) {
              _tmpDetail = null;
            } else {
              _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpDetectedAt;
            _tmpDetectedAt = _cursor.getLong(_cursorIndexOfDetectedAt);
            _item = new ThreatEntity(_tmpId,_tmpDeviceId,_tmpKind,_tmpTitle,_tmpSeverity,_tmpDetail,_tmpStatus,_tmpDetectedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getFileScanLogs(final int limit,
      final Continuation<? super List<FileScanLogEntity>> $completion) {
    final String _sql = "SELECT * FROM file_scan_logs ORDER BY scanned_at DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FileScanLogEntity>>() {
      @Override
      @NonNull
      public List<FileScanLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfScanType = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_type");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfExt = CursorUtil.getColumnIndexOrThrow(_cursor, "ext");
          final int _cursorIndexOfSizeB = CursorUtil.getColumnIndexOrThrow(_cursor, "size_b");
          final int _cursorIndexOfSha256 = CursorUtil.getColumnIndexOrThrow(_cursor, "sha256");
          final int _cursorIndexOfVerdict = CursorUtil.getColumnIndexOrThrow(_cursor, "verdict");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scanned_at");
          final List<FileScanLogEntity> _result = new ArrayList<FileScanLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FileScanLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpScanType;
            if (_cursor.isNull(_cursorIndexOfScanType)) {
              _tmpScanType = null;
            } else {
              _tmpScanType = _cursor.getString(_cursorIndexOfScanType);
            }
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpExt;
            if (_cursor.isNull(_cursorIndexOfExt)) {
              _tmpExt = null;
            } else {
              _tmpExt = _cursor.getString(_cursorIndexOfExt);
            }
            final long _tmpSizeB;
            _tmpSizeB = _cursor.getLong(_cursorIndexOfSizeB);
            final String _tmpSha256;
            if (_cursor.isNull(_cursorIndexOfSha256)) {
              _tmpSha256 = null;
            } else {
              _tmpSha256 = _cursor.getString(_cursorIndexOfSha256);
            }
            final String _tmpVerdict;
            if (_cursor.isNull(_cursorIndexOfVerdict)) {
              _tmpVerdict = null;
            } else {
              _tmpVerdict = _cursor.getString(_cursorIndexOfVerdict);
            }
            final String _tmpDetail;
            if (_cursor.isNull(_cursorIndexOfDetail)) {
              _tmpDetail = null;
            } else {
              _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            }
            final long _tmpScannedAt;
            _tmpScannedAt = _cursor.getLong(_cursorIndexOfScannedAt);
            _item = new FileScanLogEntity(_tmpId,_tmpDeviceId,_tmpScanType,_tmpPath,_tmpName,_tmpExt,_tmpSizeB,_tmpSha256,_tmpVerdict,_tmpDetail,_tmpScannedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getScanLogsForRun(final String scanId,
      final Continuation<? super List<MalwareScanLogEntity>> $completion) {
    final String _sql = "SELECT * FROM malware_scan_logs WHERE scan_id = ? ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (scanId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, scanId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MalwareScanLogEntity>>() {
      @Override
      @NonNull
      public List<MalwareScanLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfScanId = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_id");
          final int _cursorIndexOfItem = CursorUtil.getColumnIndexOrThrow(_cursor, "item");
          final int _cursorIndexOfKind = CursorUtil.getColumnIndexOrThrow(_cursor, "kind");
          final int _cursorIndexOfHash = CursorUtil.getColumnIndexOrThrow(_cursor, "hash");
          final int _cursorIndexOfMatchName = CursorUtil.getColumnIndexOrThrow(_cursor, "match_name");
          final int _cursorIndexOfVerdict = CursorUtil.getColumnIndexOrThrow(_cursor, "verdict");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfQuarantined = CursorUtil.getColumnIndexOrThrow(_cursor, "quarantined");
          final int _cursorIndexOfScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scanned_at");
          final List<MalwareScanLogEntity> _result = new ArrayList<MalwareScanLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MalwareScanLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpScanId;
            if (_cursor.isNull(_cursorIndexOfScanId)) {
              _tmpScanId = null;
            } else {
              _tmpScanId = _cursor.getString(_cursorIndexOfScanId);
            }
            final String _tmpItem;
            if (_cursor.isNull(_cursorIndexOfItem)) {
              _tmpItem = null;
            } else {
              _tmpItem = _cursor.getString(_cursorIndexOfItem);
            }
            final String _tmpKind;
            if (_cursor.isNull(_cursorIndexOfKind)) {
              _tmpKind = null;
            } else {
              _tmpKind = _cursor.getString(_cursorIndexOfKind);
            }
            final String _tmpHash;
            if (_cursor.isNull(_cursorIndexOfHash)) {
              _tmpHash = null;
            } else {
              _tmpHash = _cursor.getString(_cursorIndexOfHash);
            }
            final String _tmpMatchName;
            if (_cursor.isNull(_cursorIndexOfMatchName)) {
              _tmpMatchName = null;
            } else {
              _tmpMatchName = _cursor.getString(_cursorIndexOfMatchName);
            }
            final String _tmpVerdict;
            if (_cursor.isNull(_cursorIndexOfVerdict)) {
              _tmpVerdict = null;
            } else {
              _tmpVerdict = _cursor.getString(_cursorIndexOfVerdict);
            }
            final String _tmpSeverity;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmpSeverity = null;
            } else {
              _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            }
            final String _tmpDetail;
            if (_cursor.isNull(_cursorIndexOfDetail)) {
              _tmpDetail = null;
            } else {
              _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            }
            final boolean _tmpQuarantined;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfQuarantined);
            _tmpQuarantined = _tmp != 0;
            final long _tmpScannedAt;
            _tmpScannedAt = _cursor.getLong(_cursorIndexOfScannedAt);
            _item = new MalwareScanLogEntity(_tmpId,_tmpDeviceId,_tmpScanId,_tmpItem,_tmpKind,_tmpHash,_tmpMatchName,_tmpVerdict,_tmpSeverity,_tmpDetail,_tmpQuarantined,_tmpScannedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDetections(final int limit,
      final Continuation<? super List<MalwareScanLogEntity>> $completion) {
    final String _sql = "SELECT * FROM malware_scan_logs WHERE verdict IN ('malicious','suspicious') ORDER BY scanned_at DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MalwareScanLogEntity>>() {
      @Override
      @NonNull
      public List<MalwareScanLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfScanId = CursorUtil.getColumnIndexOrThrow(_cursor, "scan_id");
          final int _cursorIndexOfItem = CursorUtil.getColumnIndexOrThrow(_cursor, "item");
          final int _cursorIndexOfKind = CursorUtil.getColumnIndexOrThrow(_cursor, "kind");
          final int _cursorIndexOfHash = CursorUtil.getColumnIndexOrThrow(_cursor, "hash");
          final int _cursorIndexOfMatchName = CursorUtil.getColumnIndexOrThrow(_cursor, "match_name");
          final int _cursorIndexOfVerdict = CursorUtil.getColumnIndexOrThrow(_cursor, "verdict");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
          final int _cursorIndexOfQuarantined = CursorUtil.getColumnIndexOrThrow(_cursor, "quarantined");
          final int _cursorIndexOfScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scanned_at");
          final List<MalwareScanLogEntity> _result = new ArrayList<MalwareScanLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MalwareScanLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpScanId;
            if (_cursor.isNull(_cursorIndexOfScanId)) {
              _tmpScanId = null;
            } else {
              _tmpScanId = _cursor.getString(_cursorIndexOfScanId);
            }
            final String _tmpItem;
            if (_cursor.isNull(_cursorIndexOfItem)) {
              _tmpItem = null;
            } else {
              _tmpItem = _cursor.getString(_cursorIndexOfItem);
            }
            final String _tmpKind;
            if (_cursor.isNull(_cursorIndexOfKind)) {
              _tmpKind = null;
            } else {
              _tmpKind = _cursor.getString(_cursorIndexOfKind);
            }
            final String _tmpHash;
            if (_cursor.isNull(_cursorIndexOfHash)) {
              _tmpHash = null;
            } else {
              _tmpHash = _cursor.getString(_cursorIndexOfHash);
            }
            final String _tmpMatchName;
            if (_cursor.isNull(_cursorIndexOfMatchName)) {
              _tmpMatchName = null;
            } else {
              _tmpMatchName = _cursor.getString(_cursorIndexOfMatchName);
            }
            final String _tmpVerdict;
            if (_cursor.isNull(_cursorIndexOfVerdict)) {
              _tmpVerdict = null;
            } else {
              _tmpVerdict = _cursor.getString(_cursorIndexOfVerdict);
            }
            final String _tmpSeverity;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmpSeverity = null;
            } else {
              _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            }
            final String _tmpDetail;
            if (_cursor.isNull(_cursorIndexOfDetail)) {
              _tmpDetail = null;
            } else {
              _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
            }
            final boolean _tmpQuarantined;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfQuarantined);
            _tmpQuarantined = _tmp != 0;
            final long _tmpScannedAt;
            _tmpScannedAt = _cursor.getLong(_cursorIndexOfScannedAt);
            _item = new MalwareScanLogEntity(_tmpId,_tmpDeviceId,_tmpScanId,_tmpItem,_tmpKind,_tmpHash,_tmpMatchName,_tmpVerdict,_tmpSeverity,_tmpDetail,_tmpQuarantined,_tmpScannedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
