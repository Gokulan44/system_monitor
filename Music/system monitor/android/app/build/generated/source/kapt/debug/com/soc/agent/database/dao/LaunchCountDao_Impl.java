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
import com.soc.agent.database.entity.LaunchCountEntity;
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
public final class LaunchCountDao_Impl implements LaunchCountDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LaunchCountEntity> __insertionAdapterOfLaunchCountEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  public LaunchCountDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLaunchCountEntity = new EntityInsertionAdapter<LaunchCountEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `launch_count` (`id`,`package_name`,`app_name`,`launch_time_ms`,`hour_of_day`,`day_of_week`,`date_int`,`month_int`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LaunchCountEntity entity) {
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
        statement.bindLong(4, entity.getLaunchTimeMs());
        statement.bindLong(5, entity.getHourOfDay());
        statement.bindLong(6, entity.getDayOfWeek());
        statement.bindLong(7, entity.getDateInt());
        statement.bindLong(8, entity.getMonthInt());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM launch_count WHERE launch_time_ms < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final LaunchCountEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLaunchCountEntity.insert(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<LaunchCountEntity> entities,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLaunchCountEntity.insert(entities);
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
  public Object totalLaunchesForPackage(final String packageName,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM launch_count WHERE package_name = ?";
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
  public Object totalLaunches(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM launch_count";
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
  public Object launchesForPackageOnDay(final String packageName, final int dateInt,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM launch_count WHERE package_name = ? AND date_int = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, dateInt);
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
  public Object launchesForPackageInRange(final String packageName, final int fromInt,
      final int toInt, final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM launch_count WHERE package_name = ? AND date_int BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 3;
    _statement.bindLong(_argIndex, toInt);
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
  public Object launchesPerPackageOnDay(final int dateInt,
      final Continuation<? super List<PackageLaunchRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, COUNT(*) AS launchCount\n"
            + "        FROM launch_count\n"
            + "        WHERE date_int = ?\n"
            + "        GROUP BY package_name\n"
            + "        ORDER BY launchCount DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PackageLaunchRank>>() {
      @Override
      @NonNull
      public List<PackageLaunchRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfLaunchCount = 1;
          final List<PackageLaunchRank> _result = new ArrayList<PackageLaunchRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PackageLaunchRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            _item = new PackageLaunchRank(_tmpPackageName,_tmpLaunchCount);
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
  public Object launchesPerPackageInRange(final int fromInt, final int toInt,
      final Continuation<? super List<PackageLaunchRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, COUNT(*) AS launchCount\n"
            + "        FROM launch_count\n"
            + "        WHERE date_int BETWEEN ? AND ?\n"
            + "        GROUP BY package_name\n"
            + "        ORDER BY launchCount DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PackageLaunchRank>>() {
      @Override
      @NonNull
      public List<PackageLaunchRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfLaunchCount = 1;
          final List<PackageLaunchRank> _result = new ArrayList<PackageLaunchRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PackageLaunchRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            _item = new PackageLaunchRank(_tmpPackageName,_tmpLaunchCount);
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
  public Object hourlyDistribution(final String packageName, final int fromInt, final int toInt,
      final Continuation<? super List<HourlyLaunch>> $completion) {
    final String _sql = "\n"
            + "        SELECT hour_of_day AS hourOfDay, COUNT(*) AS launchCount\n"
            + "        FROM launch_count\n"
            + "        WHERE package_name = ? AND date_int BETWEEN ? AND ?\n"
            + "        GROUP BY hour_of_day\n"
            + "        ORDER BY hour_of_day ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 3;
    _statement.bindLong(_argIndex, toInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HourlyLaunch>>() {
      @Override
      @NonNull
      public List<HourlyLaunch> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHourOfDay = 0;
          final int _cursorIndexOfLaunchCount = 1;
          final List<HourlyLaunch> _result = new ArrayList<HourlyLaunch>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HourlyLaunch _item;
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            _item = new HourlyLaunch(_tmpHourOfDay,_tmpLaunchCount);
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
  public Object weeklyDistribution(final String packageName, final int fromInt, final int toInt,
      final Continuation<? super List<DailyLaunch>> $completion) {
    final String _sql = "\n"
            + "        SELECT day_of_week AS dayOfWeek, COUNT(*) AS launchCount\n"
            + "        FROM launch_count\n"
            + "        WHERE package_name = ? AND date_int BETWEEN ? AND ?\n"
            + "        GROUP BY day_of_week\n"
            + "        ORDER BY day_of_week ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 3;
    _statement.bindLong(_argIndex, toInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyLaunch>>() {
      @Override
      @NonNull
      public List<DailyLaunch> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDayOfWeek = 0;
          final int _cursorIndexOfLaunchCount = 1;
          final List<DailyLaunch> _result = new ArrayList<DailyLaunch>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyLaunch _item;
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            _item = new DailyLaunch(_tmpDayOfWeek,_tmpLaunchCount);
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
  public Object topHoursAllApps(final int fromInt, final int toInt, final int limit,
      final Continuation<? super List<HourlyLaunch>> $completion) {
    final String _sql = "\n"
            + "        SELECT hour_of_day AS hourOfDay, COUNT(*) AS launchCount\n"
            + "        FROM launch_count\n"
            + "        WHERE date_int BETWEEN ? AND ?\n"
            + "        GROUP BY hour_of_day\n"
            + "        ORDER BY launchCount DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toInt);
    _argIndex = 3;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HourlyLaunch>>() {
      @Override
      @NonNull
      public List<HourlyLaunch> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHourOfDay = 0;
          final int _cursorIndexOfLaunchCount = 1;
          final List<HourlyLaunch> _result = new ArrayList<HourlyLaunch>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HourlyLaunch _item;
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            _item = new HourlyLaunch(_tmpHourOfDay,_tmpLaunchCount);
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
  public Object recentLaunches(final String packageName, final int limit,
      final Continuation<? super List<LaunchCountEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM launch_count\n"
            + "        WHERE package_name = ?\n"
            + "        ORDER BY launch_time_ms DESC\n"
            + "        LIMIT ?\n"
            + "    ";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<LaunchCountEntity>>() {
      @Override
      @NonNull
      public List<LaunchCountEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfLaunchTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "launch_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfMonthInt = CursorUtil.getColumnIndexOrThrow(_cursor, "month_int");
          final List<LaunchCountEntity> _result = new ArrayList<LaunchCountEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LaunchCountEntity _item;
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
            final long _tmpLaunchTimeMs;
            _tmpLaunchTimeMs = _cursor.getLong(_cursorIndexOfLaunchTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final int _tmpMonthInt;
            _tmpMonthInt = _cursor.getInt(_cursorIndexOfMonthInt);
            _item = new LaunchCountEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpLaunchTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpMonthInt);
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
  public Object firstLaunchTime(final String packageName,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MIN(launch_time_ms) FROM launch_count WHERE package_name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
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
  public Object lastLaunchTime(final String packageName,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MAX(launch_time_ms) FROM launch_count WHERE package_name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
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
  public Object activeDaysInRange(final String packageName, final int fromInt, final int toInt,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(DISTINCT date_int) FROM launch_count WHERE package_name = ? AND date_int BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 3;
    _statement.bindLong(_argIndex, toInt);
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
  public Object launchDatesDesc(final String packageName, final int toDateInt,
      final Continuation<? super List<Integer>> $completion) {
    final String _sql = "\n"
            + "        SELECT DISTINCT date_int FROM launch_count\n"
            + "        WHERE package_name = ? AND date_int <= ?\n"
            + "        ORDER BY date_int DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, toDateInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Integer>>() {
      @Override
      @NonNull
      public List<Integer> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<Integer> _result = new ArrayList<Integer>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Integer _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getInt(0);
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
  public Object totalCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM launch_count";
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
