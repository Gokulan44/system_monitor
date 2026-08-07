package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soc.agent.database.entity.DailyLimitEntity;
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
public final class DailyLimitDao_Impl implements DailyLimitDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyLimitEntity> __insertionAdapterOfDailyLimitEntity;

  private final EntityDeletionOrUpdateAdapter<DailyLimitEntity> __updateAdapterOfDailyLimitEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByPackage;

  public DailyLimitDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyLimitEntity = new EntityInsertionAdapter<DailyLimitEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_limit` (`id`,`package_name`,`app_name`,`limit_ms`,`warning_threshold`,`exceeded_action`,`enabled`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyLimitEntity entity) {
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
        statement.bindLong(4, entity.getLimitMs());
        statement.bindDouble(5, entity.getWarningThreshold());
        if (entity.getExceededAction() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getExceededAction());
        }
        final int _tmp = entity.getEnabled() ? 1 : 0;
        statement.bindLong(7, _tmp);
      }
    };
    this.__updateAdapterOfDailyLimitEntity = new EntityDeletionOrUpdateAdapter<DailyLimitEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `daily_limit` SET `id` = ?,`package_name` = ?,`app_name` = ?,`limit_ms` = ?,`warning_threshold` = ?,`exceeded_action` = ?,`enabled` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyLimitEntity entity) {
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
        statement.bindLong(4, entity.getLimitMs());
        statement.bindDouble(5, entity.getWarningThreshold());
        if (entity.getExceededAction() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getExceededAction());
        }
        final int _tmp = entity.getEnabled() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM daily_limit WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByPackage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM daily_limit WHERE package_name = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final DailyLimitEntity entity,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDailyLimitEntity.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final DailyLimitEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDailyLimitEntity.handle(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
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
  public Object deleteByPackage(final String packageName,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByPackage.acquire();
        int _argIndex = 1;
        if (packageName == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, packageName);
        }
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
          __preparedStmtOfDeleteByPackage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final long id, final Continuation<? super DailyLimitEntity> $completion) {
    final String _sql = "SELECT * FROM daily_limit WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyLimitEntity>() {
      @Override
      @Nullable
      public DailyLimitEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfLimitMs = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_ms");
          final int _cursorIndexOfWarningThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "warning_threshold");
          final int _cursorIndexOfExceededAction = CursorUtil.getColumnIndexOrThrow(_cursor, "exceeded_action");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final DailyLimitEntity _result;
          if (_cursor.moveToFirst()) {
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
            final long _tmpLimitMs;
            _tmpLimitMs = _cursor.getLong(_cursorIndexOfLimitMs);
            final double _tmpWarningThreshold;
            _tmpWarningThreshold = _cursor.getDouble(_cursorIndexOfWarningThreshold);
            final String _tmpExceededAction;
            if (_cursor.isNull(_cursorIndexOfExceededAction)) {
              _tmpExceededAction = null;
            } else {
              _tmpExceededAction = _cursor.getString(_cursorIndexOfExceededAction);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            _result = new DailyLimitEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpLimitMs,_tmpWarningThreshold,_tmpExceededAction,_tmpEnabled);
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
  public Object getForPackage(final String packageName,
      final Continuation<? super DailyLimitEntity> $completion) {
    final String _sql = "SELECT * FROM daily_limit WHERE package_name = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyLimitEntity>() {
      @Override
      @Nullable
      public DailyLimitEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfLimitMs = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_ms");
          final int _cursorIndexOfWarningThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "warning_threshold");
          final int _cursorIndexOfExceededAction = CursorUtil.getColumnIndexOrThrow(_cursor, "exceeded_action");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final DailyLimitEntity _result;
          if (_cursor.moveToFirst()) {
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
            final long _tmpLimitMs;
            _tmpLimitMs = _cursor.getLong(_cursorIndexOfLimitMs);
            final double _tmpWarningThreshold;
            _tmpWarningThreshold = _cursor.getDouble(_cursorIndexOfWarningThreshold);
            final String _tmpExceededAction;
            if (_cursor.isNull(_cursorIndexOfExceededAction)) {
              _tmpExceededAction = null;
            } else {
              _tmpExceededAction = _cursor.getString(_cursorIndexOfExceededAction);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            _result = new DailyLimitEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpLimitMs,_tmpWarningThreshold,_tmpExceededAction,_tmpEnabled);
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
  public Object getAll(final Continuation<? super List<DailyLimitEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_limit ORDER BY app_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyLimitEntity>>() {
      @Override
      @NonNull
      public List<DailyLimitEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfLimitMs = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_ms");
          final int _cursorIndexOfWarningThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "warning_threshold");
          final int _cursorIndexOfExceededAction = CursorUtil.getColumnIndexOrThrow(_cursor, "exceeded_action");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final List<DailyLimitEntity> _result = new ArrayList<DailyLimitEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyLimitEntity _item;
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
            final long _tmpLimitMs;
            _tmpLimitMs = _cursor.getLong(_cursorIndexOfLimitMs);
            final double _tmpWarningThreshold;
            _tmpWarningThreshold = _cursor.getDouble(_cursorIndexOfWarningThreshold);
            final String _tmpExceededAction;
            if (_cursor.isNull(_cursorIndexOfExceededAction)) {
              _tmpExceededAction = null;
            } else {
              _tmpExceededAction = _cursor.getString(_cursorIndexOfExceededAction);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            _item = new DailyLimitEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpLimitMs,_tmpWarningThreshold,_tmpExceededAction,_tmpEnabled);
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
  public Object getEnabled(final Continuation<? super List<DailyLimitEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_limit WHERE enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyLimitEntity>>() {
      @Override
      @NonNull
      public List<DailyLimitEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfLimitMs = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_ms");
          final int _cursorIndexOfWarningThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "warning_threshold");
          final int _cursorIndexOfExceededAction = CursorUtil.getColumnIndexOrThrow(_cursor, "exceeded_action");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final List<DailyLimitEntity> _result = new ArrayList<DailyLimitEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyLimitEntity _item;
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
            final long _tmpLimitMs;
            _tmpLimitMs = _cursor.getLong(_cursorIndexOfLimitMs);
            final double _tmpWarningThreshold;
            _tmpWarningThreshold = _cursor.getDouble(_cursorIndexOfWarningThreshold);
            final String _tmpExceededAction;
            if (_cursor.isNull(_cursorIndexOfExceededAction)) {
              _tmpExceededAction = null;
            } else {
              _tmpExceededAction = _cursor.getString(_cursorIndexOfExceededAction);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            _item = new DailyLimitEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpLimitMs,_tmpWarningThreshold,_tmpExceededAction,_tmpEnabled);
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
  public Object getBlockLimits(final Continuation<? super List<DailyLimitEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_limit WHERE enabled = 1 AND exceeded_action = 'block'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyLimitEntity>>() {
      @Override
      @NonNull
      public List<DailyLimitEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfLimitMs = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_ms");
          final int _cursorIndexOfWarningThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "warning_threshold");
          final int _cursorIndexOfExceededAction = CursorUtil.getColumnIndexOrThrow(_cursor, "exceeded_action");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final List<DailyLimitEntity> _result = new ArrayList<DailyLimitEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyLimitEntity _item;
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
            final long _tmpLimitMs;
            _tmpLimitMs = _cursor.getLong(_cursorIndexOfLimitMs);
            final double _tmpWarningThreshold;
            _tmpWarningThreshold = _cursor.getDouble(_cursorIndexOfWarningThreshold);
            final String _tmpExceededAction;
            if (_cursor.isNull(_cursorIndexOfExceededAction)) {
              _tmpExceededAction = null;
            } else {
              _tmpExceededAction = _cursor.getString(_cursorIndexOfExceededAction);
            }
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            _item = new DailyLimitEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpLimitMs,_tmpWarningThreshold,_tmpExceededAction,_tmpEnabled);
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
  public Object isBlockLimited(final String packageName,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM daily_limit WHERE enabled = 1 AND package_name = ? AND exceeded_action = 'block'";
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
    final String _sql = "SELECT COUNT(*) FROM daily_limit";
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
