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
import com.soc.agent.database.entity.DailyUsageEntity;
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
public final class DailyUsageDao_Impl implements DailyUsageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyUsageEntity> __insertionAdapterOfDailyUsageEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  private final SharedSQLiteStatement __preparedStmtOfDeleteForPackage;

  public DailyUsageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyUsageEntity = new EntityInsertionAdapter<DailyUsageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_usage` (`id`,`package_name`,`app_name`,`date`,`total_time_ms`,`launch_count`,`first_time_ms`,`last_time_ms`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyUsageEntity entity) {
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
        statement.bindLong(4, entity.getDate());
        statement.bindLong(5, entity.getTotalTimeMs());
        statement.bindLong(6, entity.getLaunchCount());
        statement.bindLong(7, entity.getFirstTimeMs());
        statement.bindLong(8, entity.getLastTimeMs());
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM daily_usage WHERE date < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteForPackage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM daily_usage WHERE package_name = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final DailyUsageEntity dailyUsage,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDailyUsageEntity.insert(dailyUsage);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<DailyUsageEntity> dailyUsageList,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDailyUsageEntity.insert(dailyUsageList);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOlderThan(final int beforeDate,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOlderThan.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, beforeDate);
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
  public Object getForPackageAndDate(final String packageName, final int date,
      final Continuation<? super DailyUsageEntity> $completion) {
    final String _sql = "SELECT * FROM daily_usage WHERE package_name = ? AND date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyUsageEntity>() {
      @Override
      @Nullable
      public DailyUsageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_time_ms");
          final int _cursorIndexOfLastTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_time_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final DailyUsageEntity _result;
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
            final int _tmpDate;
            _tmpDate = _cursor.getInt(_cursorIndexOfDate);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstTimeMs;
            _tmpFirstTimeMs = _cursor.getLong(_cursorIndexOfFirstTimeMs);
            final long _tmpLastTimeMs;
            _tmpLastTimeMs = _cursor.getLong(_cursorIndexOfLastTimeMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new DailyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpDate,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstTimeMs,_tmpLastTimeMs,_tmpUpdatedAt);
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
  public Object getForDate(final int date,
      final Continuation<? super List<DailyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_usage WHERE date = ? ORDER BY total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyUsageEntity>>() {
      @Override
      @NonNull
      public List<DailyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_time_ms");
          final int _cursorIndexOfLastTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_time_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<DailyUsageEntity> _result = new ArrayList<DailyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyUsageEntity _item;
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
            final int _tmpDate;
            _tmpDate = _cursor.getInt(_cursorIndexOfDate);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstTimeMs;
            _tmpFirstTimeMs = _cursor.getLong(_cursorIndexOfFirstTimeMs);
            final long _tmpLastTimeMs;
            _tmpLastTimeMs = _cursor.getLong(_cursorIndexOfLastTimeMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpDate,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstTimeMs,_tmpLastTimeMs,_tmpUpdatedAt);
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
  public Object getDateRange(final int startDate, final int endDate,
      final Continuation<? super List<DailyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_usage WHERE date BETWEEN ? AND ? ORDER BY date DESC, total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyUsageEntity>>() {
      @Override
      @NonNull
      public List<DailyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_time_ms");
          final int _cursorIndexOfLastTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_time_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<DailyUsageEntity> _result = new ArrayList<DailyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyUsageEntity _item;
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
            final int _tmpDate;
            _tmpDate = _cursor.getInt(_cursorIndexOfDate);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstTimeMs;
            _tmpFirstTimeMs = _cursor.getLong(_cursorIndexOfFirstTimeMs);
            final long _tmpLastTimeMs;
            _tmpLastTimeMs = _cursor.getLong(_cursorIndexOfLastTimeMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpDate,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstTimeMs,_tmpLastTimeMs,_tmpUpdatedAt);
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
  public Object getForPackage(final String packageName, final int limit,
      final Continuation<? super List<DailyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_usage WHERE package_name = ? ORDER BY date DESC LIMIT ?";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyUsageEntity>>() {
      @Override
      @NonNull
      public List<DailyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_time_ms");
          final int _cursorIndexOfLastTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_time_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<DailyUsageEntity> _result = new ArrayList<DailyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyUsageEntity _item;
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
            final int _tmpDate;
            _tmpDate = _cursor.getInt(_cursorIndexOfDate);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstTimeMs;
            _tmpFirstTimeMs = _cursor.getLong(_cursorIndexOfFirstTimeMs);
            final long _tmpLastTimeMs;
            _tmpLastTimeMs = _cursor.getLong(_cursorIndexOfLastTimeMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpDate,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstTimeMs,_tmpLastTimeMs,_tmpUpdatedAt);
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
  public Object getRecent(final int limit,
      final Continuation<? super List<DailyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_usage ORDER BY date DESC, total_time_ms DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyUsageEntity>>() {
      @Override
      @NonNull
      public List<DailyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_time_ms");
          final int _cursorIndexOfLastTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_time_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<DailyUsageEntity> _result = new ArrayList<DailyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyUsageEntity _item;
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
            final int _tmpDate;
            _tmpDate = _cursor.getInt(_cursorIndexOfDate);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstTimeMs;
            _tmpFirstTimeMs = _cursor.getLong(_cursorIndexOfFirstTimeMs);
            final long _tmpLastTimeMs;
            _tmpLastTimeMs = _cursor.getLong(_cursorIndexOfLastTimeMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpDate,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstTimeMs,_tmpLastTimeMs,_tmpUpdatedAt);
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
  public Object getTotalTimeForDate(final int date, final Continuation<? super Long> $completion) {
    final String _sql = "SELECT SUM(total_time_ms) FROM daily_usage WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
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
  public Object getAppCountForDate(final int date,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM daily_usage WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
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
  public Object getForDateWithApps(final int date,
      final Continuation<? super List<DailyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_usage WHERE date = ? ORDER BY total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<List<DailyUsageEntity>>() {
      @Override
      @NonNull
      public List<DailyUsageEntity> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
            final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
            final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
            final int _cursorIndexOfFirstTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_time_ms");
            final int _cursorIndexOfLastTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_time_ms");
            final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
            final List<DailyUsageEntity> _result = new ArrayList<DailyUsageEntity>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final DailyUsageEntity _item;
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
              final int _tmpDate;
              _tmpDate = _cursor.getInt(_cursorIndexOfDate);
              final long _tmpTotalTimeMs;
              _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
              final int _tmpLaunchCount;
              _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
              final long _tmpFirstTimeMs;
              _tmpFirstTimeMs = _cursor.getLong(_cursorIndexOfFirstTimeMs);
              final long _tmpLastTimeMs;
              _tmpLastTimeMs = _cursor.getLong(_cursorIndexOfLastTimeMs);
              final long _tmpUpdatedAt;
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
              _item = new DailyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpDate,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstTimeMs,_tmpLastTimeMs,_tmpUpdatedAt);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllInRange(final int fromDate, final int toDate,
      final Continuation<? super List<DailyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_usage WHERE date >= ? AND date <= ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyUsageEntity>>() {
      @Override
      @NonNull
      public List<DailyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfFirstTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "first_time_ms");
          final int _cursorIndexOfLastTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "last_time_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<DailyUsageEntity> _result = new ArrayList<DailyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyUsageEntity _item;
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
            final int _tmpDate;
            _tmpDate = _cursor.getInt(_cursorIndexOfDate);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final long _tmpFirstTimeMs;
            _tmpFirstTimeMs = _cursor.getLong(_cursorIndexOfFirstTimeMs);
            final long _tmpLastTimeMs;
            _tmpLastTimeMs = _cursor.getLong(_cursorIndexOfLastTimeMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpDate,_tmpTotalTimeMs,_tmpLaunchCount,_tmpFirstTimeMs,_tmpLastTimeMs,_tmpUpdatedAt);
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
