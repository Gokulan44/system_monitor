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
import com.soc.agent.database.entity.MonthlyUsageEntity;
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
public final class MonthlyUsageDao_Impl implements MonthlyUsageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MonthlyUsageEntity> __insertionAdapterOfMonthlyUsageEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  public MonthlyUsageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMonthlyUsageEntity = new EntityInsertionAdapter<MonthlyUsageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `monthly_usage` (`package_name`,`app_name`,`month_start`,`total_time_ms`,`launch_count`,`active_days`,`active_dates`,`updated_at`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MonthlyUsageEntity entity) {
        if (entity.getPackageName() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getPackageName());
        }
        if (entity.getAppName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAppName());
        }
        statement.bindLong(3, entity.getMonthStart());
        statement.bindLong(4, entity.getTotalTimeMs());
        statement.bindLong(5, entity.getLaunchCount());
        statement.bindLong(6, entity.getActiveDays());
        if (entity.getActiveDates() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getActiveDates());
        }
        statement.bindLong(8, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM monthly_usage WHERE month_start < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final MonthlyUsageEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMonthlyUsageEntity.insert(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOlderThan(final long beforeMs, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOlderThan.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, beforeMs);
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
          __preparedStmtOfDeleteOlderThan.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getForMonth(final long monthStart,
      final Continuation<? super List<MonthlyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM monthly_usage WHERE month_start = ? ORDER BY total_time_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, monthStart);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MonthlyUsageEntity>>() {
      @Override
      @NonNull
      public List<MonthlyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfMonthStart = CursorUtil.getColumnIndexOrThrow(_cursor, "month_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<MonthlyUsageEntity> _result = new ArrayList<MonthlyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonthlyUsageEntity _item;
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
            final long _tmpMonthStart;
            _tmpMonthStart = _cursor.getLong(_cursorIndexOfMonthStart);
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
            _item = new MonthlyUsageEntity(_tmpPackageName,_tmpAppName,_tmpMonthStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
  public Object getForPackageAndMonth(final String packageName, final long monthStart,
      final Continuation<? super MonthlyUsageEntity> $completion) {
    final String _sql = "SELECT * FROM monthly_usage WHERE package_name = ? AND month_start = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, monthStart);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MonthlyUsageEntity>() {
      @Override
      @Nullable
      public MonthlyUsageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfMonthStart = CursorUtil.getColumnIndexOrThrow(_cursor, "month_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final MonthlyUsageEntity _result;
          if (_cursor.moveToFirst()) {
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
            final long _tmpMonthStart;
            _tmpMonthStart = _cursor.getLong(_cursorIndexOfMonthStart);
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
            _result = new MonthlyUsageEntity(_tmpPackageName,_tmpAppName,_tmpMonthStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
  public Object getAllMonths(final Continuation<? super List<Long>> $completion) {
    final String _sql = "SELECT DISTINCT month_start FROM monthly_usage ORDER BY month_start DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<Long> _result = new ArrayList<Long>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Long _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getLong(0);
            }
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
  public Object getAllForPackage(final String packageName,
      final Continuation<? super List<MonthlyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM monthly_usage WHERE package_name = ? ORDER BY month_start DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MonthlyUsageEntity>>() {
      @Override
      @NonNull
      public List<MonthlyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfMonthStart = CursorUtil.getColumnIndexOrThrow(_cursor, "month_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<MonthlyUsageEntity> _result = new ArrayList<MonthlyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonthlyUsageEntity _item;
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
            final long _tmpMonthStart;
            _tmpMonthStart = _cursor.getLong(_cursorIndexOfMonthStart);
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
            _item = new MonthlyUsageEntity(_tmpPackageName,_tmpAppName,_tmpMonthStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
      final Continuation<? super List<MonthlyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM monthly_usage ORDER BY updated_at DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MonthlyUsageEntity>>() {
      @Override
      @NonNull
      public List<MonthlyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfMonthStart = CursorUtil.getColumnIndexOrThrow(_cursor, "month_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<MonthlyUsageEntity> _result = new ArrayList<MonthlyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonthlyUsageEntity _item;
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
            final long _tmpMonthStart;
            _tmpMonthStart = _cursor.getLong(_cursorIndexOfMonthStart);
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
            _item = new MonthlyUsageEntity(_tmpPackageName,_tmpAppName,_tmpMonthStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
  public Object getMonthTotals(final long monthStart,
      final Continuation<? super MonthTotals> $completion) {
    final String _sql = "SELECT COALESCE(SUM(total_time_ms), 0) AS totalTimeMs, COALESCE(SUM(launch_count), 0) AS totalLaunches FROM monthly_usage WHERE month_start = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, monthStart);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MonthTotals>() {
      @Override
      @NonNull
      public MonthTotals call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTotalTimeMs = 0;
          final int _cursorIndexOfTotalLaunches = 1;
          final MonthTotals _result;
          if (_cursor.moveToFirst()) {
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpTotalLaunches;
            _tmpTotalLaunches = _cursor.getInt(_cursorIndexOfTotalLaunches);
            _result = new MonthTotals(_tmpTotalTimeMs,_tmpTotalLaunches);
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
    final String _sql = "SELECT COUNT(*) FROM monthly_usage";
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
  public Object getAll(final Continuation<? super List<MonthlyUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM monthly_usage ORDER BY month_start DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MonthlyUsageEntity>>() {
      @Override
      @NonNull
      public List<MonthlyUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfMonthStart = CursorUtil.getColumnIndexOrThrow(_cursor, "month_start");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfLaunchCount = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_count");
          final int _cursorIndexOfActiveDays = CursorUtil.getColumnIndexOrThrow(_cursor, "active_days");
          final int _cursorIndexOfActiveDates = CursorUtil.getColumnIndexOrThrow(_cursor, "active_dates");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<MonthlyUsageEntity> _result = new ArrayList<MonthlyUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonthlyUsageEntity _item;
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
            final long _tmpMonthStart;
            _tmpMonthStart = _cursor.getLong(_cursorIndexOfMonthStart);
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
            _item = new MonthlyUsageEntity(_tmpPackageName,_tmpAppName,_tmpMonthStart,_tmpTotalTimeMs,_tmpLaunchCount,_tmpActiveDays,_tmpActiveDates,_tmpUpdatedAt);
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
