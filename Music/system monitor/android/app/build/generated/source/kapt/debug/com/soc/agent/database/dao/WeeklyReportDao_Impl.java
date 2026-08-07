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
import com.soc.agent.database.entity.WeeklyReportEntity;
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
public final class WeeklyReportDao_Impl implements WeeklyReportDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WeeklyReportEntity> __insertionAdapterOfWeeklyReportEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  public WeeklyReportDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeeklyReportEntity = new EntityInsertionAdapter<WeeklyReportEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `weekly_report` (`id`,`week_start`,`week_end`,`total_screen_time_ms`,`total_launches`,`apps_used_count`,`top_apps`,`most_used_app`,`most_used_app_time_ms`,`peak_day`,`peak_day_time_ms`,`avg_daily_time_ms`,`peak_hour`,`notifications_count`,`focus_sessions_count`,`focus_time_ms`,`generated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WeeklyReportEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getWeekStart());
        statement.bindLong(3, entity.getWeekEnd());
        statement.bindLong(4, entity.getTotalScreenTimeMs());
        statement.bindLong(5, entity.getTotalLaunches());
        statement.bindLong(6, entity.getAppsUsedCount());
        if (entity.getTopApps() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getTopApps());
        }
        if (entity.getMostUsedApp() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getMostUsedApp());
        }
        statement.bindLong(9, entity.getMostUsedAppTimeMs());
        if (entity.getPeakDay() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getPeakDay());
        }
        statement.bindLong(11, entity.getPeakDayTimeMs());
        statement.bindLong(12, entity.getAvgDailyTimeMs());
        if (entity.getPeakHour() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getPeakHour());
        }
        statement.bindLong(14, entity.getNotificationsCount());
        statement.bindLong(15, entity.getFocusSessionsCount());
        statement.bindLong(16, entity.getFocusTimeMs());
        statement.bindLong(17, entity.getGeneratedAt());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM weekly_report WHERE week_start < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final WeeklyReportEntity entity,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWeeklyReportEntity.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
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
  public Object getForWeek(final long weekStart,
      final Continuation<? super WeeklyReportEntity> $completion) {
    final String _sql = "SELECT * FROM weekly_report WHERE week_start = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, weekStart);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WeeklyReportEntity>() {
      @Override
      @Nullable
      public WeeklyReportEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "week_end");
          final int _cursorIndexOfTotalScreenTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_screen_time_ms");
          final int _cursorIndexOfTotalLaunches = CursorUtil.getColumnIndexOrThrow(_cursor, "total_launches");
          final int _cursorIndexOfAppsUsedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "apps_used_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfMostUsedApp = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app");
          final int _cursorIndexOfMostUsedAppTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app_time_ms");
          final int _cursorIndexOfPeakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day");
          final int _cursorIndexOfPeakDayTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day_time_ms");
          final int _cursorIndexOfAvgDailyTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "avg_daily_time_ms");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_hour");
          final int _cursorIndexOfNotificationsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "notifications_count");
          final int _cursorIndexOfFocusSessionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_sessions_count");
          final int _cursorIndexOfFocusTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_time_ms");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generated_at");
          final WeeklyReportEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getLong(_cursorIndexOfWeekEnd);
            final long _tmpTotalScreenTimeMs;
            _tmpTotalScreenTimeMs = _cursor.getLong(_cursorIndexOfTotalScreenTimeMs);
            final int _tmpTotalLaunches;
            _tmpTotalLaunches = _cursor.getInt(_cursorIndexOfTotalLaunches);
            final int _tmpAppsUsedCount;
            _tmpAppsUsedCount = _cursor.getInt(_cursorIndexOfAppsUsedCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final String _tmpMostUsedApp;
            if (_cursor.isNull(_cursorIndexOfMostUsedApp)) {
              _tmpMostUsedApp = null;
            } else {
              _tmpMostUsedApp = _cursor.getString(_cursorIndexOfMostUsedApp);
            }
            final long _tmpMostUsedAppTimeMs;
            _tmpMostUsedAppTimeMs = _cursor.getLong(_cursorIndexOfMostUsedAppTimeMs);
            final Integer _tmpPeakDay;
            if (_cursor.isNull(_cursorIndexOfPeakDay)) {
              _tmpPeakDay = null;
            } else {
              _tmpPeakDay = _cursor.getInt(_cursorIndexOfPeakDay);
            }
            final long _tmpPeakDayTimeMs;
            _tmpPeakDayTimeMs = _cursor.getLong(_cursorIndexOfPeakDayTimeMs);
            final long _tmpAvgDailyTimeMs;
            _tmpAvgDailyTimeMs = _cursor.getLong(_cursorIndexOfAvgDailyTimeMs);
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final int _tmpNotificationsCount;
            _tmpNotificationsCount = _cursor.getInt(_cursorIndexOfNotificationsCount);
            final int _tmpFocusSessionsCount;
            _tmpFocusSessionsCount = _cursor.getInt(_cursorIndexOfFocusSessionsCount);
            final long _tmpFocusTimeMs;
            _tmpFocusTimeMs = _cursor.getLong(_cursorIndexOfFocusTimeMs);
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _result = new WeeklyReportEntity(_tmpId,_tmpWeekStart,_tmpWeekEnd,_tmpTotalScreenTimeMs,_tmpTotalLaunches,_tmpAppsUsedCount,_tmpTopApps,_tmpMostUsedApp,_tmpMostUsedAppTimeMs,_tmpPeakDay,_tmpPeakDayTimeMs,_tmpAvgDailyTimeMs,_tmpPeakHour,_tmpNotificationsCount,_tmpFocusSessionsCount,_tmpFocusTimeMs,_tmpGeneratedAt);
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
  public Object getById(final long id, final Continuation<? super WeeklyReportEntity> $completion) {
    final String _sql = "SELECT * FROM weekly_report WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WeeklyReportEntity>() {
      @Override
      @Nullable
      public WeeklyReportEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "week_end");
          final int _cursorIndexOfTotalScreenTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_screen_time_ms");
          final int _cursorIndexOfTotalLaunches = CursorUtil.getColumnIndexOrThrow(_cursor, "total_launches");
          final int _cursorIndexOfAppsUsedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "apps_used_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfMostUsedApp = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app");
          final int _cursorIndexOfMostUsedAppTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app_time_ms");
          final int _cursorIndexOfPeakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day");
          final int _cursorIndexOfPeakDayTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day_time_ms");
          final int _cursorIndexOfAvgDailyTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "avg_daily_time_ms");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_hour");
          final int _cursorIndexOfNotificationsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "notifications_count");
          final int _cursorIndexOfFocusSessionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_sessions_count");
          final int _cursorIndexOfFocusTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_time_ms");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generated_at");
          final WeeklyReportEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getLong(_cursorIndexOfWeekEnd);
            final long _tmpTotalScreenTimeMs;
            _tmpTotalScreenTimeMs = _cursor.getLong(_cursorIndexOfTotalScreenTimeMs);
            final int _tmpTotalLaunches;
            _tmpTotalLaunches = _cursor.getInt(_cursorIndexOfTotalLaunches);
            final int _tmpAppsUsedCount;
            _tmpAppsUsedCount = _cursor.getInt(_cursorIndexOfAppsUsedCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final String _tmpMostUsedApp;
            if (_cursor.isNull(_cursorIndexOfMostUsedApp)) {
              _tmpMostUsedApp = null;
            } else {
              _tmpMostUsedApp = _cursor.getString(_cursorIndexOfMostUsedApp);
            }
            final long _tmpMostUsedAppTimeMs;
            _tmpMostUsedAppTimeMs = _cursor.getLong(_cursorIndexOfMostUsedAppTimeMs);
            final Integer _tmpPeakDay;
            if (_cursor.isNull(_cursorIndexOfPeakDay)) {
              _tmpPeakDay = null;
            } else {
              _tmpPeakDay = _cursor.getInt(_cursorIndexOfPeakDay);
            }
            final long _tmpPeakDayTimeMs;
            _tmpPeakDayTimeMs = _cursor.getLong(_cursorIndexOfPeakDayTimeMs);
            final long _tmpAvgDailyTimeMs;
            _tmpAvgDailyTimeMs = _cursor.getLong(_cursorIndexOfAvgDailyTimeMs);
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final int _tmpNotificationsCount;
            _tmpNotificationsCount = _cursor.getInt(_cursorIndexOfNotificationsCount);
            final int _tmpFocusSessionsCount;
            _tmpFocusSessionsCount = _cursor.getInt(_cursorIndexOfFocusSessionsCount);
            final long _tmpFocusTimeMs;
            _tmpFocusTimeMs = _cursor.getLong(_cursorIndexOfFocusTimeMs);
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _result = new WeeklyReportEntity(_tmpId,_tmpWeekStart,_tmpWeekEnd,_tmpTotalScreenTimeMs,_tmpTotalLaunches,_tmpAppsUsedCount,_tmpTopApps,_tmpMostUsedApp,_tmpMostUsedAppTimeMs,_tmpPeakDay,_tmpPeakDayTimeMs,_tmpAvgDailyTimeMs,_tmpPeakHour,_tmpNotificationsCount,_tmpFocusSessionsCount,_tmpFocusTimeMs,_tmpGeneratedAt);
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
  public Object getAll(final Continuation<? super List<WeeklyReportEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_report ORDER BY week_start DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyReportEntity>>() {
      @Override
      @NonNull
      public List<WeeklyReportEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "week_end");
          final int _cursorIndexOfTotalScreenTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_screen_time_ms");
          final int _cursorIndexOfTotalLaunches = CursorUtil.getColumnIndexOrThrow(_cursor, "total_launches");
          final int _cursorIndexOfAppsUsedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "apps_used_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfMostUsedApp = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app");
          final int _cursorIndexOfMostUsedAppTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app_time_ms");
          final int _cursorIndexOfPeakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day");
          final int _cursorIndexOfPeakDayTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day_time_ms");
          final int _cursorIndexOfAvgDailyTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "avg_daily_time_ms");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_hour");
          final int _cursorIndexOfNotificationsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "notifications_count");
          final int _cursorIndexOfFocusSessionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_sessions_count");
          final int _cursorIndexOfFocusTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_time_ms");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generated_at");
          final List<WeeklyReportEntity> _result = new ArrayList<WeeklyReportEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyReportEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getLong(_cursorIndexOfWeekEnd);
            final long _tmpTotalScreenTimeMs;
            _tmpTotalScreenTimeMs = _cursor.getLong(_cursorIndexOfTotalScreenTimeMs);
            final int _tmpTotalLaunches;
            _tmpTotalLaunches = _cursor.getInt(_cursorIndexOfTotalLaunches);
            final int _tmpAppsUsedCount;
            _tmpAppsUsedCount = _cursor.getInt(_cursorIndexOfAppsUsedCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final String _tmpMostUsedApp;
            if (_cursor.isNull(_cursorIndexOfMostUsedApp)) {
              _tmpMostUsedApp = null;
            } else {
              _tmpMostUsedApp = _cursor.getString(_cursorIndexOfMostUsedApp);
            }
            final long _tmpMostUsedAppTimeMs;
            _tmpMostUsedAppTimeMs = _cursor.getLong(_cursorIndexOfMostUsedAppTimeMs);
            final Integer _tmpPeakDay;
            if (_cursor.isNull(_cursorIndexOfPeakDay)) {
              _tmpPeakDay = null;
            } else {
              _tmpPeakDay = _cursor.getInt(_cursorIndexOfPeakDay);
            }
            final long _tmpPeakDayTimeMs;
            _tmpPeakDayTimeMs = _cursor.getLong(_cursorIndexOfPeakDayTimeMs);
            final long _tmpAvgDailyTimeMs;
            _tmpAvgDailyTimeMs = _cursor.getLong(_cursorIndexOfAvgDailyTimeMs);
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final int _tmpNotificationsCount;
            _tmpNotificationsCount = _cursor.getInt(_cursorIndexOfNotificationsCount);
            final int _tmpFocusSessionsCount;
            _tmpFocusSessionsCount = _cursor.getInt(_cursorIndexOfFocusSessionsCount);
            final long _tmpFocusTimeMs;
            _tmpFocusTimeMs = _cursor.getLong(_cursorIndexOfFocusTimeMs);
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _item = new WeeklyReportEntity(_tmpId,_tmpWeekStart,_tmpWeekEnd,_tmpTotalScreenTimeMs,_tmpTotalLaunches,_tmpAppsUsedCount,_tmpTopApps,_tmpMostUsedApp,_tmpMostUsedAppTimeMs,_tmpPeakDay,_tmpPeakDayTimeMs,_tmpAvgDailyTimeMs,_tmpPeakHour,_tmpNotificationsCount,_tmpFocusSessionsCount,_tmpFocusTimeMs,_tmpGeneratedAt);
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
  public Object getLatest(final Continuation<? super WeeklyReportEntity> $completion) {
    final String _sql = "SELECT * FROM weekly_report ORDER BY week_start DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WeeklyReportEntity>() {
      @Override
      @Nullable
      public WeeklyReportEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "week_end");
          final int _cursorIndexOfTotalScreenTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_screen_time_ms");
          final int _cursorIndexOfTotalLaunches = CursorUtil.getColumnIndexOrThrow(_cursor, "total_launches");
          final int _cursorIndexOfAppsUsedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "apps_used_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfMostUsedApp = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app");
          final int _cursorIndexOfMostUsedAppTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app_time_ms");
          final int _cursorIndexOfPeakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day");
          final int _cursorIndexOfPeakDayTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day_time_ms");
          final int _cursorIndexOfAvgDailyTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "avg_daily_time_ms");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_hour");
          final int _cursorIndexOfNotificationsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "notifications_count");
          final int _cursorIndexOfFocusSessionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_sessions_count");
          final int _cursorIndexOfFocusTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_time_ms");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generated_at");
          final WeeklyReportEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getLong(_cursorIndexOfWeekEnd);
            final long _tmpTotalScreenTimeMs;
            _tmpTotalScreenTimeMs = _cursor.getLong(_cursorIndexOfTotalScreenTimeMs);
            final int _tmpTotalLaunches;
            _tmpTotalLaunches = _cursor.getInt(_cursorIndexOfTotalLaunches);
            final int _tmpAppsUsedCount;
            _tmpAppsUsedCount = _cursor.getInt(_cursorIndexOfAppsUsedCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final String _tmpMostUsedApp;
            if (_cursor.isNull(_cursorIndexOfMostUsedApp)) {
              _tmpMostUsedApp = null;
            } else {
              _tmpMostUsedApp = _cursor.getString(_cursorIndexOfMostUsedApp);
            }
            final long _tmpMostUsedAppTimeMs;
            _tmpMostUsedAppTimeMs = _cursor.getLong(_cursorIndexOfMostUsedAppTimeMs);
            final Integer _tmpPeakDay;
            if (_cursor.isNull(_cursorIndexOfPeakDay)) {
              _tmpPeakDay = null;
            } else {
              _tmpPeakDay = _cursor.getInt(_cursorIndexOfPeakDay);
            }
            final long _tmpPeakDayTimeMs;
            _tmpPeakDayTimeMs = _cursor.getLong(_cursorIndexOfPeakDayTimeMs);
            final long _tmpAvgDailyTimeMs;
            _tmpAvgDailyTimeMs = _cursor.getLong(_cursorIndexOfAvgDailyTimeMs);
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final int _tmpNotificationsCount;
            _tmpNotificationsCount = _cursor.getInt(_cursorIndexOfNotificationsCount);
            final int _tmpFocusSessionsCount;
            _tmpFocusSessionsCount = _cursor.getInt(_cursorIndexOfFocusSessionsCount);
            final long _tmpFocusTimeMs;
            _tmpFocusTimeMs = _cursor.getLong(_cursorIndexOfFocusTimeMs);
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _result = new WeeklyReportEntity(_tmpId,_tmpWeekStart,_tmpWeekEnd,_tmpTotalScreenTimeMs,_tmpTotalLaunches,_tmpAppsUsedCount,_tmpTopApps,_tmpMostUsedApp,_tmpMostUsedAppTimeMs,_tmpPeakDay,_tmpPeakDayTimeMs,_tmpAvgDailyTimeMs,_tmpPeakHour,_tmpNotificationsCount,_tmpFocusSessionsCount,_tmpFocusTimeMs,_tmpGeneratedAt);
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
  public Object getInRange(final long from, final long to,
      final Continuation<? super List<WeeklyReportEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_report WHERE week_start BETWEEN ? AND ? ORDER BY week_start DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, from);
    _argIndex = 2;
    _statement.bindLong(_argIndex, to);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyReportEntity>>() {
      @Override
      @NonNull
      public List<WeeklyReportEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "week_end");
          final int _cursorIndexOfTotalScreenTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_screen_time_ms");
          final int _cursorIndexOfTotalLaunches = CursorUtil.getColumnIndexOrThrow(_cursor, "total_launches");
          final int _cursorIndexOfAppsUsedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "apps_used_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfMostUsedApp = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app");
          final int _cursorIndexOfMostUsedAppTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app_time_ms");
          final int _cursorIndexOfPeakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day");
          final int _cursorIndexOfPeakDayTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day_time_ms");
          final int _cursorIndexOfAvgDailyTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "avg_daily_time_ms");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_hour");
          final int _cursorIndexOfNotificationsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "notifications_count");
          final int _cursorIndexOfFocusSessionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_sessions_count");
          final int _cursorIndexOfFocusTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_time_ms");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generated_at");
          final List<WeeklyReportEntity> _result = new ArrayList<WeeklyReportEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyReportEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getLong(_cursorIndexOfWeekEnd);
            final long _tmpTotalScreenTimeMs;
            _tmpTotalScreenTimeMs = _cursor.getLong(_cursorIndexOfTotalScreenTimeMs);
            final int _tmpTotalLaunches;
            _tmpTotalLaunches = _cursor.getInt(_cursorIndexOfTotalLaunches);
            final int _tmpAppsUsedCount;
            _tmpAppsUsedCount = _cursor.getInt(_cursorIndexOfAppsUsedCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final String _tmpMostUsedApp;
            if (_cursor.isNull(_cursorIndexOfMostUsedApp)) {
              _tmpMostUsedApp = null;
            } else {
              _tmpMostUsedApp = _cursor.getString(_cursorIndexOfMostUsedApp);
            }
            final long _tmpMostUsedAppTimeMs;
            _tmpMostUsedAppTimeMs = _cursor.getLong(_cursorIndexOfMostUsedAppTimeMs);
            final Integer _tmpPeakDay;
            if (_cursor.isNull(_cursorIndexOfPeakDay)) {
              _tmpPeakDay = null;
            } else {
              _tmpPeakDay = _cursor.getInt(_cursorIndexOfPeakDay);
            }
            final long _tmpPeakDayTimeMs;
            _tmpPeakDayTimeMs = _cursor.getLong(_cursorIndexOfPeakDayTimeMs);
            final long _tmpAvgDailyTimeMs;
            _tmpAvgDailyTimeMs = _cursor.getLong(_cursorIndexOfAvgDailyTimeMs);
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final int _tmpNotificationsCount;
            _tmpNotificationsCount = _cursor.getInt(_cursorIndexOfNotificationsCount);
            final int _tmpFocusSessionsCount;
            _tmpFocusSessionsCount = _cursor.getInt(_cursorIndexOfFocusSessionsCount);
            final long _tmpFocusTimeMs;
            _tmpFocusTimeMs = _cursor.getLong(_cursorIndexOfFocusTimeMs);
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _item = new WeeklyReportEntity(_tmpId,_tmpWeekStart,_tmpWeekEnd,_tmpTotalScreenTimeMs,_tmpTotalLaunches,_tmpAppsUsedCount,_tmpTopApps,_tmpMostUsedApp,_tmpMostUsedAppTimeMs,_tmpPeakDay,_tmpPeakDayTimeMs,_tmpAvgDailyTimeMs,_tmpPeakHour,_tmpNotificationsCount,_tmpFocusSessionsCount,_tmpFocusTimeMs,_tmpGeneratedAt);
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
      final Continuation<? super List<WeeklyReportEntity>> $completion) {
    final String _sql = "SELECT * FROM weekly_report ORDER BY week_start DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WeeklyReportEntity>>() {
      @Override
      @NonNull
      public List<WeeklyReportEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "week_start");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "week_end");
          final int _cursorIndexOfTotalScreenTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_screen_time_ms");
          final int _cursorIndexOfTotalLaunches = CursorUtil.getColumnIndexOrThrow(_cursor, "total_launches");
          final int _cursorIndexOfAppsUsedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "apps_used_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfMostUsedApp = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app");
          final int _cursorIndexOfMostUsedAppTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "most_used_app_time_ms");
          final int _cursorIndexOfPeakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day");
          final int _cursorIndexOfPeakDayTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_day_time_ms");
          final int _cursorIndexOfAvgDailyTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "avg_daily_time_ms");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peak_hour");
          final int _cursorIndexOfNotificationsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "notifications_count");
          final int _cursorIndexOfFocusSessionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_sessions_count");
          final int _cursorIndexOfFocusTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "focus_time_ms");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generated_at");
          final List<WeeklyReportEntity> _result = new ArrayList<WeeklyReportEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WeeklyReportEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWeekStart;
            _tmpWeekStart = _cursor.getLong(_cursorIndexOfWeekStart);
            final long _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getLong(_cursorIndexOfWeekEnd);
            final long _tmpTotalScreenTimeMs;
            _tmpTotalScreenTimeMs = _cursor.getLong(_cursorIndexOfTotalScreenTimeMs);
            final int _tmpTotalLaunches;
            _tmpTotalLaunches = _cursor.getInt(_cursorIndexOfTotalLaunches);
            final int _tmpAppsUsedCount;
            _tmpAppsUsedCount = _cursor.getInt(_cursorIndexOfAppsUsedCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final String _tmpMostUsedApp;
            if (_cursor.isNull(_cursorIndexOfMostUsedApp)) {
              _tmpMostUsedApp = null;
            } else {
              _tmpMostUsedApp = _cursor.getString(_cursorIndexOfMostUsedApp);
            }
            final long _tmpMostUsedAppTimeMs;
            _tmpMostUsedAppTimeMs = _cursor.getLong(_cursorIndexOfMostUsedAppTimeMs);
            final Integer _tmpPeakDay;
            if (_cursor.isNull(_cursorIndexOfPeakDay)) {
              _tmpPeakDay = null;
            } else {
              _tmpPeakDay = _cursor.getInt(_cursorIndexOfPeakDay);
            }
            final long _tmpPeakDayTimeMs;
            _tmpPeakDayTimeMs = _cursor.getLong(_cursorIndexOfPeakDayTimeMs);
            final long _tmpAvgDailyTimeMs;
            _tmpAvgDailyTimeMs = _cursor.getLong(_cursorIndexOfAvgDailyTimeMs);
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final int _tmpNotificationsCount;
            _tmpNotificationsCount = _cursor.getInt(_cursorIndexOfNotificationsCount);
            final int _tmpFocusSessionsCount;
            _tmpFocusSessionsCount = _cursor.getInt(_cursorIndexOfFocusSessionsCount);
            final long _tmpFocusTimeMs;
            _tmpFocusTimeMs = _cursor.getLong(_cursorIndexOfFocusTimeMs);
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _item = new WeeklyReportEntity(_tmpId,_tmpWeekStart,_tmpWeekEnd,_tmpTotalScreenTimeMs,_tmpTotalLaunches,_tmpAppsUsedCount,_tmpTopApps,_tmpMostUsedApp,_tmpMostUsedAppTimeMs,_tmpPeakDay,_tmpPeakDayTimeMs,_tmpAvgDailyTimeMs,_tmpPeakHour,_tmpNotificationsCount,_tmpFocusSessionsCount,_tmpFocusTimeMs,_tmpGeneratedAt);
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
    final String _sql = "SELECT COUNT(*) FROM weekly_report";
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
