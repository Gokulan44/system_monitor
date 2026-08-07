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
import com.soc.agent.database.entity.WeeklyUsageEntity;
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
public final class WeeklyUsageDao_Impl implements WeeklyUsageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WeeklyUsageEntity> __insertionAdapterOfWeeklyUsageEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  private final SharedSQLiteStatement __preparedStmtOfDeleteForPackage;

  public WeeklyUsageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeeklyUsageEntity = new EntityInsertionAdapter<WeeklyUsageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `weekly_usage` (`id`,`package_name`,`app_name`,`week_start`,`total_time_ms`,`launch_count`,`active_days`,`active_dates`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WeeklyUsageEntity entity) {
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
        statement.bindLong(4, entity.getWeekStart());
        statement.bindLong(5, entity.getTotalTimeMs());
        statement.bindLong(6, entity.getLaunchCount());
        statement.bindLong(7, entity.getActiveDays());
        if (entity.getActiveDates() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getActiveDates());
        }
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM weekly_usage WHERE week_start < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteForPackage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM weekly_usage WHERE package_name = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final WeeklyUsageEntity weeklyUsage,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWeeklyUsageEntity.insert(weeklyUsage);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<WeeklyUsageEntity> weeklyUsageList,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWeeklyUsageEntity.insert(weeklyUsageList);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOlderThan(final long beforeWeek,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOlderThan.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, beforeWeek);
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
  public Object getForPackageAndWeek(final String packageName, final long weekStart,
      final Continuation<? super WeeklyUsageEntity> $completion) {
    final String _sql = "SELECT * FROM weekly_usage WHERE package_name = ? AND week_start = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, weekStart);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WeeklyUsageEntity>() {
      @Override
      @Nullable
      public WeeklyUsageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final WeeklyUsageEntity _result;
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
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final int _tmpActiveDays;
            _tmpActiveDays = _cursor.getInt(_cursorIndexOfActiveDays);
            final String _tmpActiveDates;
            if (_cursor.isNull(_cursorIndexOfActiveDates)) {
              _tmpActiveDates = null;
            } else {
              _tmpActiveDates = _cursor.getString(_cursorIndexOfActiveDates);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new WeeklyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpWeekStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
  public Object getForWeek(final long weekStart,
      final Continuation<? super List<WeeklyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_usage WHERE week_start = ? ORDER BY total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, weekStart);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyUsageEntity>>() {
      @Override
      @NonNull
      public List<WeeklyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<WeeklyUsageEntity> _result = new ArrayList<WeeklyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyUsageEntity _item;
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
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final int _tmpActiveDays;
            _tmpActiveDays = _cursor.getInt(_cursorIndexOfActiveDays);
            final String _tmpActiveDates;
            if (_cursor.isNull(_cursorIndexOfActiveDates)) {
              _tmpActiveDates = null;
            } else {
              _tmpActiveDates = _cursor.getString(_cursorIndexOfActiveDates);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new WeeklyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpWeekStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
  public Object getWeekRange(final long startWeek, final long endWeek,
      final Continuation<? super List<WeeklyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_usage WHERE week_start BETWEEN ? AND ? ORDER BY week_start DESC, total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startWeek);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endWeek);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyUsageEntity>>() {
      @Override
      @NonNull
      public List<WeeklyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<WeeklyUsageEntity> _result = new ArrayList<WeeklyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyUsageEntity _item;
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
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final int _tmpActiveDays;
            _tmpActiveDays = _cursor.getInt(_cursorIndexOfActiveDays);
            final String _tmpActiveDates;
            if (_cursor.isNull(_cursorIndexOfActiveDates)) {
              _tmpActiveDates = null;
            } else {
              _tmpActiveDates = _cursor.getString(_cursorIndexOfActiveDates);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new WeeklyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpWeekStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
      final Continuation<? super List<WeeklyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_usage WHERE package_name = ? ORDER BY week_start DESC LIMIT ?";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyUsageEntity>>() {
      @Override
      @NonNull
      public List<WeeklyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<WeeklyUsageEntity> _result = new ArrayList<WeeklyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyUsageEntity _item;
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
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final int _tmpActiveDays;
            _tmpActiveDays = _cursor.getInt(_cursorIndexOfActiveDays);
            final String _tmpActiveDates;
            if (_cursor.isNull(_cursorIndexOfActiveDates)) {
              _tmpActiveDates = null;
            } else {
              _tmpActiveDates = _cursor.getString(_cursorIndexOfActiveDates);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new WeeklyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpWeekStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
      final Continuation<? super List<WeeklyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_usage ORDER BY week_start DESC, total_time_ms DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyUsageEntity>>() {
      @Override
      @NonNull
      public List<WeeklyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<WeeklyUsageEntity> _result = new ArrayList<WeeklyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyUsageEntity _item;
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
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final int _tmpActiveDays;
            _tmpActiveDays = _cursor.getInt(_cursorIndexOfActiveDays);
            final String _tmpActiveDates;
            if (_cursor.isNull(_cursorIndexOfActiveDates)) {
              _tmpActiveDates = null;
            } else {
              _tmpActiveDates = _cursor.getString(_cursorIndexOfActiveDates);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new WeeklyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpWeekStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
  public Object getTotalTimeForWeek(final long weekStart,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT SUM(total_time_ms) FROM weekly_usage WHERE week_start = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, weekStart);
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
  public Object getAppCountForWeek(final long weekStart,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM weekly_usage WHERE week_start = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, weekStart);
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
  public Object getLatestWeek(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT week_start FROM weekly_usage ORDER BY week_start DESC LIMIT 1";
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
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getLong(0);
            }
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
  public Object getAllInRange(final long fromWeek, final long toWeek,
      final Continuation<? super List<WeeklyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_usage WHERE week_start >= ? AND week_start <= ? ORDER BY week_start DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromWeek);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toWeek);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyUsageEntity>>() {
      @Override
      @NonNull
      public List<WeeklyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<WeeklyUsageEntity> _result = new ArrayList<WeeklyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyUsageEntity _item;
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
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            final int _tmpActiveDays;
            _tmpActiveDays = _cursor.getInt(_cursorIndexOfActiveDays);
            final String _tmpActiveDates;
            if (_cursor.isNull(_cursorIndexOfActiveDates)) {
              _tmpActiveDates = null;
            } else {
              _tmpActiveDates = _cursor.getString(_cursorIndexOfActiveDates);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new WeeklyUsageEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpWeekStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
