package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MostUsedAppsDao_Impl implements MostUsedAppsDao {
  private final RoomDatabase __db;

  public MostUsedAppsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Object topAppsForDay(final int dateInt, final int limit,
      final Continuation<? super List<UsageRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n"
            + "        FROM daily_usage\n"
            + "        WHERE date = ?\n"
            + "        GROUP BY package_name\n"
            + "        ORDER BY totalTimeMs DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageRank>>() {
      @Override
      @NonNull
      public List<UsageRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfTotalTimeMs = 1;
          final List<UsageRank> _result = new ArrayList<UsageRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            _item = new UsageRank(_tmpPackageName,_tmpTotalTimeMs);
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
  public Object topAppsForWeek(final long weekStart, final int limit,
      final Continuation<? super List<UsageRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n"
            + "        FROM weekly_usage\n"
            + "        WHERE week_start = ?\n"
            + "        GROUP BY package_name\n"
            + "        ORDER BY totalTimeMs DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, weekStart);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageRank>>() {
      @Override
      @NonNull
      public List<UsageRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfTotalTimeMs = 1;
          final List<UsageRank> _result = new ArrayList<UsageRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            _item = new UsageRank(_tmpPackageName,_tmpTotalTimeMs);
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
  public Object topAppsForMonth(final long monthStart, final int limit,
      final Continuation<? super List<UsageRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n"
            + "        FROM monthly_usage\n"
            + "        WHERE month_start = ?\n"
            + "        GROUP BY package_name\n"
            + "        ORDER BY totalTimeMs DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, monthStart);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageRank>>() {
      @Override
      @NonNull
      public List<UsageRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfTotalTimeMs = 1;
          final List<UsageRank> _result = new ArrayList<UsageRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            _item = new UsageRank(_tmpPackageName,_tmpTotalTimeMs);
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
  public Object topAppsAllTime(final int limit,
      final Continuation<? super List<UsageRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, total_time_ms AS totalTimeMs\n"
            + "        FROM app_usage\n"
            + "        ORDER BY totalTimeMs DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageRank>>() {
      @Override
      @NonNull
      public List<UsageRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfTotalTimeMs = 1;
          final List<UsageRank> _result = new ArrayList<UsageRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            _item = new UsageRank(_tmpPackageName,_tmpTotalTimeMs);
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
  public Object topAppsInRange(final int fromInt, final int toInt, final int limit,
      final Continuation<? super List<UsageRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, SUM(total_time_ms) AS totalTimeMs\n"
            + "        FROM daily_usage\n"
            + "        WHERE date BETWEEN ? AND ?\n"
            + "        GROUP BY package_name\n"
            + "        ORDER BY totalTimeMs DESC\n"
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageRank>>() {
      @Override
      @NonNull
      public List<UsageRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfTotalTimeMs = 1;
          final List<UsageRank> _result = new ArrayList<UsageRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            _item = new UsageRank(_tmpPackageName,_tmpTotalTimeMs);
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
  public Object mostLaunchedForDay(final int dateInt, final int limit,
      final Continuation<? super List<LaunchRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, launch_count AS launchCount\n"
            + "        FROM daily_usage\n"
            + "        WHERE date = ?\n"
            + "        ORDER BY launch_count DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<LaunchRank>>() {
      @Override
      @NonNull
      public List<LaunchRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfLaunchCount = 1;
          final List<LaunchRank> _result = new ArrayList<LaunchRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LaunchRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            _item = new LaunchRank(_tmpPackageName,_tmpLaunchCount);
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
  public Object mostLaunchedInRange(final int fromInt, final int toInt, final int limit,
      final Continuation<? super List<LaunchRank>> $completion) {
    final String _sql = "\n"
            + "        SELECT package_name AS packageName, SUM(launch_count) AS launchCount\n"
            + "        FROM daily_usage\n"
            + "        WHERE date BETWEEN ? AND ?\n"
            + "        GROUP BY package_name\n"
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<LaunchRank>>() {
      @Override
      @NonNull
      public List<LaunchRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfLaunchCount = 1;
          final List<LaunchRank> _result = new ArrayList<LaunchRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LaunchRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpLaunchCount;
            _tmpLaunchCount = _cursor.getInt(_cursorIndexOfLaunchCount);
            _item = new LaunchRank(_tmpPackageName,_tmpLaunchCount);
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
