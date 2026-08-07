package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soc.agent.database.entity.IntruderSelfieEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class IntruderSelfieDao_AppDatabase_Impl implements IntruderSelfieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IntruderSelfieEntity> __insertionAdapterOfIntruderSelfieEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public IntruderSelfieDao_AppDatabase_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIntruderSelfieEntity = new EntityInsertionAdapter<IntruderSelfieEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `intruder_selfies` (`id`,`package_name`,`app_name`,`gate_method`,`timestamp`,`image_path`,`failure_reason`,`attempt_number`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final IntruderSelfieEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPackageName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPackageName());
        }
        if (entity.getAppName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAppName());
        }
        if (entity.getGateMethod() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getGateMethod());
        }
        statement.bindLong(5, entity.getTimestamp());
        if (entity.getImagePath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImagePath());
        }
        if (entity.getFailureReason() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFailureReason());
        }
        statement.bindLong(8, entity.getAttemptNumber());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM intruder_selfies WHERE timestamp < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM intruder_selfies";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final IntruderSelfieEntity record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIntruderSelfieEntity.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final IntruderSelfieEntity[] records,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIntruderSelfieEntity.insert(records);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOlderThan(final long before,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOlderThan.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, before);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOlderThan.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getSelfiesForPackage(final String packageName, final int limit,
      final Continuation<? super List<IntruderSelfieEntity>> $completion) {
    final String _sql = "SELECT * FROM intruder_selfies WHERE package_name = ? ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<IntruderSelfieEntity>>() {
      @Override
      @NonNull
      public List<IntruderSelfieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfGateMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "gate_method");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "image_path");
          final int _cursorIndexOfFailureReason = CursorUtil.getColumnIndexOrThrow(_cursor, "failure_reason");
          final int _cursorIndexOfAttemptNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "attempt_number");
          final List<IntruderSelfieEntity> _result = new ArrayList<IntruderSelfieEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IntruderSelfieEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpAppName;
            if (_cursor.isNull(_cursorIndexOfAppName)) {
              _tmpAppName = null;
            } else {
              _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            }
            final String _tmpGateMethod;
            if (_cursor.isNull(_cursorIndexOfGateMethod)) {
              _tmpGateMethod = null;
            } else {
              _tmpGateMethod = _cursor.getString(_cursorIndexOfGateMethod);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpFailureReason;
            if (_cursor.isNull(_cursorIndexOfFailureReason)) {
              _tmpFailureReason = null;
            } else {
              _tmpFailureReason = _cursor.getString(_cursorIndexOfFailureReason);
            }
            final int _tmpAttemptNumber;
            _tmpAttemptNumber = _cursor.getInt(_cursorIndexOfAttemptNumber);
            _item = new IntruderSelfieEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpGateMethod,_tmpTimestamp,_tmpImagePath,_tmpFailureReason,_tmpAttemptNumber);
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
  public Object getRecentSelfies(final int limit,
      final Continuation<? super List<IntruderSelfieEntity>> $completion) {
    final String _sql = "SELECT * FROM intruder_selfies ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<IntruderSelfieEntity>>() {
      @Override
      @NonNull
      public List<IntruderSelfieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfGateMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "gate_method");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "image_path");
          final int _cursorIndexOfFailureReason = CursorUtil.getColumnIndexOrThrow(_cursor, "failure_reason");
          final int _cursorIndexOfAttemptNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "attempt_number");
          final List<IntruderSelfieEntity> _result = new ArrayList<IntruderSelfieEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IntruderSelfieEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpAppName;
            if (_cursor.isNull(_cursorIndexOfAppName)) {
              _tmpAppName = null;
            } else {
              _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            }
            final String _tmpGateMethod;
            if (_cursor.isNull(_cursorIndexOfGateMethod)) {
              _tmpGateMethod = null;
            } else {
              _tmpGateMethod = _cursor.getString(_cursorIndexOfGateMethod);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpFailureReason;
            if (_cursor.isNull(_cursorIndexOfFailureReason)) {
              _tmpFailureReason = null;
            } else {
              _tmpFailureReason = _cursor.getString(_cursorIndexOfFailureReason);
            }
            final int _tmpAttemptNumber;
            _tmpAttemptNumber = _cursor.getInt(_cursorIndexOfAttemptNumber);
            _item = new IntruderSelfieEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpGateMethod,_tmpTimestamp,_tmpImagePath,_tmpFailureReason,_tmpAttemptNumber);
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
  public Object countForPackage(final String packageName,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM intruder_selfies WHERE package_name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
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

  @Override
  public Object totalCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM intruder_selfies";
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

  @Override
  public Object getAll(final Continuation<? super List<IntruderSelfieEntity>> $completion) {
    final String _sql = "SELECT * FROM intruder_selfies";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<IntruderSelfieEntity>>() {
      @Override
      @NonNull
      public List<IntruderSelfieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfGateMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "gate_method");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "image_path");
          final int _cursorIndexOfFailureReason = CursorUtil.getColumnIndexOrThrow(_cursor, "failure_reason");
          final int _cursorIndexOfAttemptNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "attempt_number");
          final List<IntruderSelfieEntity> _result = new ArrayList<IntruderSelfieEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IntruderSelfieEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpAppName;
            if (_cursor.isNull(_cursorIndexOfAppName)) {
              _tmpAppName = null;
            } else {
              _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            }
            final String _tmpGateMethod;
            if (_cursor.isNull(_cursorIndexOfGateMethod)) {
              _tmpGateMethod = null;
            } else {
              _tmpGateMethod = _cursor.getString(_cursorIndexOfGateMethod);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpFailureReason;
            if (_cursor.isNull(_cursorIndexOfFailureReason)) {
              _tmpFailureReason = null;
            } else {
              _tmpFailureReason = _cursor.getString(_cursorIndexOfFailureReason);
            }
            final int _tmpAttemptNumber;
            _tmpAttemptNumber = _cursor.getInt(_cursorIndexOfAttemptNumber);
            _item = new IntruderSelfieEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpGateMethod,_tmpTimestamp,_tmpImagePath,_tmpFailureReason,_tmpAttemptNumber);
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
