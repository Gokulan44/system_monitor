package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soc.agent.database.entity.ExportReportEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class ExportReportDao_Impl implements ExportReportDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExportReportEntity> __insertionAdapterOfExportReportEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public ExportReportDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExportReportEntity = new EntityInsertionAdapter<ExportReportEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `export_report` (`id`,`format`,`scope`,`date_from`,`date_to`,`file_uri`,`file_size_bytes`,`record_count`,`exported_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExportReportEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFormat() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFormat());
        }
        if (entity.getScope() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getScope());
        }
        statement.bindLong(4, entity.getDateFrom());
        statement.bindLong(5, entity.getDateTo());
        if (entity.getFileUri() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFileUri());
        }
        statement.bindLong(7, entity.getFileSizeBytes());
        statement.bindLong(8, entity.getRecordCount());
        statement.bindLong(9, entity.getExportedAt());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM export_report WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ExportReportEntity entity,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfExportReportEntity.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final long id, final Continuation<? super ExportReportEntity> $completion) {
    final String _sql = "SELECT * FROM export_report WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExportReportEntity>() {
      @Override
      @Nullable
      public ExportReportEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfScope = CursorUtil.getColumnIndexOrThrow(_cursor, "scope");
          final int _cursorIndexOfDateFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "date_from");
          final int _cursorIndexOfDateTo = CursorUtil.getColumnIndexOrThrow(_cursor, "date_to");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "file_uri");
          final int _cursorIndexOfFileSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "file_size_bytes");
          final int _cursorIndexOfRecordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "record_count");
          final int _cursorIndexOfExportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "exported_at");
          final ExportReportEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpScope;
            if (_cursor.isNull(_cursorIndexOfScope)) {
              _tmpScope = null;
            } else {
              _tmpScope = _cursor.getString(_cursorIndexOfScope);
            }
            final long _tmpDateFrom;
            _tmpDateFrom = _cursor.getLong(_cursorIndexOfDateFrom);
            final long _tmpDateTo;
            _tmpDateTo = _cursor.getLong(_cursorIndexOfDateTo);
            final String _tmpFileUri;
            if (_cursor.isNull(_cursorIndexOfFileUri)) {
              _tmpFileUri = null;
            } else {
              _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            }
            final long _tmpFileSizeBytes;
            _tmpFileSizeBytes = _cursor.getLong(_cursorIndexOfFileSizeBytes);
            final int _tmpRecordCount;
            _tmpRecordCount = _cursor.getInt(_cursorIndexOfRecordCount);
            final long _tmpExportedAt;
            _tmpExportedAt = _cursor.getLong(_cursorIndexOfExportedAt);
            _result = new ExportReportEntity(_tmpId,_tmpFormat,_tmpScope,_tmpDateFrom,_tmpDateTo,_tmpFileUri,_tmpFileSizeBytes,_tmpRecordCount,_tmpExportedAt);
          } else {
            _result = null;
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
  public Object getAll(final Continuation<? super List<ExportReportEntity>> $completion) {
    final String _sql = "SELECT * FROM export_report ORDER BY exported_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ExportReportEntity>>() {
      @Override
      @NonNull
      public List<ExportReportEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfScope = CursorUtil.getColumnIndexOrThrow(_cursor, "scope");
          final int _cursorIndexOfDateFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "date_from");
          final int _cursorIndexOfDateTo = CursorUtil.getColumnIndexOrThrow(_cursor, "date_to");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "file_uri");
          final int _cursorIndexOfFileSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "file_size_bytes");
          final int _cursorIndexOfRecordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "record_count");
          final int _cursorIndexOfExportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "exported_at");
          final List<ExportReportEntity> _result = new ArrayList<ExportReportEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExportReportEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpScope;
            if (_cursor.isNull(_cursorIndexOfScope)) {
              _tmpScope = null;
            } else {
              _tmpScope = _cursor.getString(_cursorIndexOfScope);
            }
            final long _tmpDateFrom;
            _tmpDateFrom = _cursor.getLong(_cursorIndexOfDateFrom);
            final long _tmpDateTo;
            _tmpDateTo = _cursor.getLong(_cursorIndexOfDateTo);
            final String _tmpFileUri;
            if (_cursor.isNull(_cursorIndexOfFileUri)) {
              _tmpFileUri = null;
            } else {
              _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            }
            final long _tmpFileSizeBytes;
            _tmpFileSizeBytes = _cursor.getLong(_cursorIndexOfFileSizeBytes);
            final int _tmpRecordCount;
            _tmpRecordCount = _cursor.getInt(_cursorIndexOfRecordCount);
            final long _tmpExportedAt;
            _tmpExportedAt = _cursor.getLong(_cursorIndexOfExportedAt);
            _item = new ExportReportEntity(_tmpId,_tmpFormat,_tmpScope,_tmpDateFrom,_tmpDateTo,_tmpFileUri,_tmpFileSizeBytes,_tmpRecordCount,_tmpExportedAt);
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
  public Object getLatest(final Continuation<? super ExportReportEntity> $completion) {
    final String _sql = "SELECT * FROM export_report ORDER BY exported_at DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExportReportEntity>() {
      @Override
      @Nullable
      public ExportReportEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfScope = CursorUtil.getColumnIndexOrThrow(_cursor, "scope");
          final int _cursorIndexOfDateFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "date_from");
          final int _cursorIndexOfDateTo = CursorUtil.getColumnIndexOrThrow(_cursor, "date_to");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "file_uri");
          final int _cursorIndexOfFileSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "file_size_bytes");
          final int _cursorIndexOfRecordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "record_count");
          final int _cursorIndexOfExportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "exported_at");
          final ExportReportEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpScope;
            if (_cursor.isNull(_cursorIndexOfScope)) {
              _tmpScope = null;
            } else {
              _tmpScope = _cursor.getString(_cursorIndexOfScope);
            }
            final long _tmpDateFrom;
            _tmpDateFrom = _cursor.getLong(_cursorIndexOfDateFrom);
            final long _tmpDateTo;
            _tmpDateTo = _cursor.getLong(_cursorIndexOfDateTo);
            final String _tmpFileUri;
            if (_cursor.isNull(_cursorIndexOfFileUri)) {
              _tmpFileUri = null;
            } else {
              _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            }
            final long _tmpFileSizeBytes;
            _tmpFileSizeBytes = _cursor.getLong(_cursorIndexOfFileSizeBytes);
            final int _tmpRecordCount;
            _tmpRecordCount = _cursor.getInt(_cursorIndexOfRecordCount);
            final long _tmpExportedAt;
            _tmpExportedAt = _cursor.getLong(_cursorIndexOfExportedAt);
            _result = new ExportReportEntity(_tmpId,_tmpFormat,_tmpScope,_tmpDateFrom,_tmpDateTo,_tmpFileUri,_tmpFileSizeBytes,_tmpRecordCount,_tmpExportedAt);
          } else {
            _result = null;
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
  public Object getByFormat(final String format,
      final Continuation<? super List<ExportReportEntity>> $completion) {
    final String _sql = "SELECT * FROM export_report WHERE format = ? ORDER BY exported_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (format == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, format);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ExportReportEntity>>() {
      @Override
      @NonNull
      public List<ExportReportEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfScope = CursorUtil.getColumnIndexOrThrow(_cursor, "scope");
          final int _cursorIndexOfDateFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "date_from");
          final int _cursorIndexOfDateTo = CursorUtil.getColumnIndexOrThrow(_cursor, "date_to");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "file_uri");
          final int _cursorIndexOfFileSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "file_size_bytes");
          final int _cursorIndexOfRecordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "record_count");
          final int _cursorIndexOfExportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "exported_at");
          final List<ExportReportEntity> _result = new ArrayList<ExportReportEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExportReportEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpScope;
            if (_cursor.isNull(_cursorIndexOfScope)) {
              _tmpScope = null;
            } else {
              _tmpScope = _cursor.getString(_cursorIndexOfScope);
            }
            final long _tmpDateFrom;
            _tmpDateFrom = _cursor.getLong(_cursorIndexOfDateFrom);
            final long _tmpDateTo;
            _tmpDateTo = _cursor.getLong(_cursorIndexOfDateTo);
            final String _tmpFileUri;
            if (_cursor.isNull(_cursorIndexOfFileUri)) {
              _tmpFileUri = null;
            } else {
              _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            }
            final long _tmpFileSizeBytes;
            _tmpFileSizeBytes = _cursor.getLong(_cursorIndexOfFileSizeBytes);
            final int _tmpRecordCount;
            _tmpRecordCount = _cursor.getInt(_cursorIndexOfRecordCount);
            final long _tmpExportedAt;
            _tmpExportedAt = _cursor.getLong(_cursorIndexOfExportedAt);
            _item = new ExportReportEntity(_tmpId,_tmpFormat,_tmpScope,_tmpDateFrom,_tmpDateTo,_tmpFileUri,_tmpFileSizeBytes,_tmpRecordCount,_tmpExportedAt);
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
  public Object getByScope(final String scope,
      final Continuation<? super List<ExportReportEntity>> $completion) {
    final String _sql = "SELECT * FROM export_report WHERE scope = ? ORDER BY exported_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (scope == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, scope);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ExportReportEntity>>() {
      @Override
      @NonNull
      public List<ExportReportEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfScope = CursorUtil.getColumnIndexOrThrow(_cursor, "scope");
          final int _cursorIndexOfDateFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "date_from");
          final int _cursorIndexOfDateTo = CursorUtil.getColumnIndexOrThrow(_cursor, "date_to");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "file_uri");
          final int _cursorIndexOfFileSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "file_size_bytes");
          final int _cursorIndexOfRecordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "record_count");
          final int _cursorIndexOfExportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "exported_at");
          final List<ExportReportEntity> _result = new ArrayList<ExportReportEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExportReportEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpScope;
            if (_cursor.isNull(_cursorIndexOfScope)) {
              _tmpScope = null;
            } else {
              _tmpScope = _cursor.getString(_cursorIndexOfScope);
            }
            final long _tmpDateFrom;
            _tmpDateFrom = _cursor.getLong(_cursorIndexOfDateFrom);
            final long _tmpDateTo;
            _tmpDateTo = _cursor.getLong(_cursorIndexOfDateTo);
            final String _tmpFileUri;
            if (_cursor.isNull(_cursorIndexOfFileUri)) {
              _tmpFileUri = null;
            } else {
              _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            }
            final long _tmpFileSizeBytes;
            _tmpFileSizeBytes = _cursor.getLong(_cursorIndexOfFileSizeBytes);
            final int _tmpRecordCount;
            _tmpRecordCount = _cursor.getInt(_cursorIndexOfRecordCount);
            final long _tmpExportedAt;
            _tmpExportedAt = _cursor.getLong(_cursorIndexOfExportedAt);
            _item = new ExportReportEntity(_tmpId,_tmpFormat,_tmpScope,_tmpDateFrom,_tmpDateTo,_tmpFileUri,_tmpFileSizeBytes,_tmpRecordCount,_tmpExportedAt);
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
  public Object totalCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM export_report";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
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
