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
import com.soc.agent.database.entity.AppUsageEntity;
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
public final class AppUsageDao_Impl implements AppUsageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppUsageEntity> __insertionAdapterOfAppUsageEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteForPackage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUnusedSince;

  public AppUsageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppUsageEntity = new EntityInsertionAdapter<AppUsageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `app_usage` (`id`,`package_name`,`app_name`,`total_time_ms`,`launch_count`,`first_used_ms`,`last_used_ms`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppUsageEntity entity) {
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
        statement.bindLong(4, entity.getTotalTimeMs());
        statement.bindLong(5, entity.getLaunchCount());
        statement.bindLong(6, entity.getFirstUsedMs());
        statement.bindLong(7, entity.getLastUsedMs());
        statement.bindLong(8, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfDeleteForPackage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM app_usage WHERE package_name = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUnusedSince = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM app_usage WHERE last_used_ms < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AppUsageEntity appUsage,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppUsageEntity.insert(appUsage);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<AppUsageEntity> appUsageList,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppUsageEntity.insert(appUsageList);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteForPackage(final String packageName,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteForPackage.acquire();
        int _argIndex = 1;
        if (packageName == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, packageName);
        }
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
          __preparedStmtOfDeleteForPackage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUnusedSince(final long beforeTime,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUnusedSince.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, beforeTime);
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
          __preparedStmtOfDeleteUnusedSince.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getForPackage(final String packageName,
      final Continuation<? super AppUsageEntity> $completion) {
    final String _sql = "SELECT * FROM app_usage WHERE package_name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AppUsageEntity>() {
      @Override
      @Nullable
      public AppUsageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_used_ms");
          final int _cursorIndexOfLastUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_used_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final AppUsageEntity _result;
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
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstUsedMs;
            _tmpFirstUsedMs = _cursor.getLong(_cursorIndexOfFirstUsedMs);
            final long _tmpLastUsedMs;
            _tmpLastUsedMs = _cursor.getLong(_cursorIndexOfLastUsedMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new AppUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstUsedMs,_tmpLastUsedMs,_tmpUpdatedAt);
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
  public Object getTopApps(final int limit,
      final Continuation<? super List<AppUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM app_usage ORDER BY total_time_ms DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEntity>>() {
      @Override
      @NonNull
      public List<AppUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_used_ms");
          final int _cursorIndexOfLastUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_used_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AppUsageEntity> _result = new ArrayList<AppUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEntity _item;
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
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstUsedMs;
            _tmpFirstUsedMs = _cursor.getLong(_cursorIndexOfFirstUsedMs);
            final long _tmpLastUsedMs;
            _tmpLastUsedMs = _cursor.getLong(_cursorIndexOfLastUsedMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new AppUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstUsedMs,_tmpLastUsedMs,_tmpUpdatedAt);
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
  public Object getRecentlyUsed(final int limit,
      final Continuation<? super List<AppUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM app_usage ORDER BY last_used_ms DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEntity>>() {
      @Override
      @NonNull
      public List<AppUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_used_ms");
          final int _cursorIndexOfLastUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_used_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AppUsageEntity> _result = new ArrayList<AppUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEntity _item;
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
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstUsedMs;
            _tmpFirstUsedMs = _cursor.getLong(_cursorIndexOfFirstUsedMs);
            final long _tmpLastUsedMs;
            _tmpLastUsedMs = _cursor.getLong(_cursorIndexOfLastUsedMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new AppUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstUsedMs,_tmpLastUsedMs,_tmpUpdatedAt);
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
  public Object getMostLaunched(final int limit,
      final Continuation<? super List<AppUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM app_usage ORDER BY launch_count DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEntity>>() {
      @Override
      @NonNull
      public List<AppUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_used_ms");
          final int _cursorIndexOfLastUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_used_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AppUsageEntity> _result = new ArrayList<AppUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEntity _item;
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
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstUsedMs;
            _tmpFirstUsedMs = _cursor.getLong(_cursorIndexOfFirstUsedMs);
            final long _tmpLastUsedMs;
            _tmpLastUsedMs = _cursor.getLong(_cursorIndexOfLastUsedMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new AppUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstUsedMs,_tmpLastUsedMs,_tmpUpdatedAt);
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
  public Object getAppsAboveTime(final long minTime,
      final Continuation<? super List<AppUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM app_usage WHERE total_time_ms > ? ORDER BY total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, minTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEntity>>() {
      @Override
      @NonNull
      public List<AppUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_used_ms");
          final int _cursorIndexOfLastUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_used_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AppUsageEntity> _result = new ArrayList<AppUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEntity _item;
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
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstUsedMs;
            _tmpFirstUsedMs = _cursor.getLong(_cursorIndexOfFirstUsedMs);
            final long _tmpLastUsedMs;
            _tmpLastUsedMs = _cursor.getLong(_cursorIndexOfLastUsedMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new AppUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstUsedMs,_tmpLastUsedMs,_tmpUpdatedAt);
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
  public Object getTotalTimeAllApps(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT SUM(total_time_ms) FROM app_usage";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
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
  public Object getTotalLaunchesAllApps(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT SUM(launch_count) FROM app_usage";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
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
  public Object getTotalAppCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM app_usage";
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
  public Object getAll(final Continuation<? super List<AppUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM app_usage ORDER BY total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEntity>>() {
      @Override
      @NonNull
      public List<AppUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_used_ms");
          final int _cursorIndexOfLastUsedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_used_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<AppUsageEntity> _result = new ArrayList<AppUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEntity _item;
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
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstUsedMs;
            _tmpFirstUsedMs = _cursor.getLong(_cursorIndexOfFirstUsedMs);
            final long _tmpLastUsedMs;
            _tmpLastUsedMs = _cursor.getLong(_cursorIndexOfLastUsedMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new AppUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstUsedMs,_tmpLastUsedMs,_tmpUpdatedAt);
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
