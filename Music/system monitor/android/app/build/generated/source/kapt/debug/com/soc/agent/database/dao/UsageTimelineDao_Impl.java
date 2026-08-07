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
import com.soc.agent.database.entity.UsageTimelineEntity;
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
public final class UsageTimelineDao_Impl implements UsageTimelineDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UsageTimelineEntity> __insertionAdapterOfUsageTimelineEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  public UsageTimelineDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsageTimelineEntity = new EntityInsertionAdapter<UsageTimelineEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `usage_timeline` (`id`,`package_name`,`app_name`,`event_type`,`event_time_ms`,`hour_of_day`,`day_of_week`,`date_int`,`duration_ms`,`previous_app`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsageTimelineEntity entity) {
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
        if (entity.getEventType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEventType());
        }
        statement.bindLong(5, entity.getEventTimeMs());
        statement.bindLong(6, entity.getHourOfDay());
        statement.bindLong(7, entity.getDayOfWeek());
        statement.bindLong(8, entity.getDateInt());
        statement.bindLong(9, entity.getDurationMs());
        if (entity.getPreviousApp() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getPreviousApp());
        }
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM usage_timeline WHERE event_time_ms < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final UsageTimelineEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUsageTimelineEntity.insert(entity);
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
  public Object getForDay(final int dateInt,
      final Continuation<? super List<UsageTimelineEntity>> $completion) {
    final String _sql = "SELECT * FROM usage_timeline WHERE date_int = ? ORDER BY event_time_ms ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageTimelineEntity>>() {
      @Override
      @NonNull
      public List<UsageTimelineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfEventTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "event_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_ms");
          final int _cursorIndexOfPreviousApp = CursorUtil.getColumnIndexOrThrow(_cursor, "previous_app");
          final List<UsageTimelineEntity> _result = new ArrayList<UsageTimelineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageTimelineEntity _item;
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
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final long _tmpEventTimeMs;
            _tmpEventTimeMs = _cursor.getLong(_cursorIndexOfEventTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            _item = new UsageTimelineEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpEventType,_tmpEventTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpDurationMs,_tmpPreviousApp);
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
  public Object getForPackageOnDay(final String packageName, final int dateInt,
      final Continuation<? super List<UsageTimelineEntity>> $completion) {
    final String _sql = "SELECT * FROM usage_timeline WHERE package_name = ? AND date_int = ? ORDER BY event_time_ms ASC";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageTimelineEntity>>() {
      @Override
      @NonNull
      public List<UsageTimelineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfEventTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "event_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_ms");
          final int _cursorIndexOfPreviousApp = CursorUtil.getColumnIndexOrThrow(_cursor, "previous_app");
          final List<UsageTimelineEntity> _result = new ArrayList<UsageTimelineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageTimelineEntity _item;
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
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final long _tmpEventTimeMs;
            _tmpEventTimeMs = _cursor.getLong(_cursorIndexOfEventTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            _item = new UsageTimelineEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpEventType,_tmpEventTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpDurationMs,_tmpPreviousApp);
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
  public Object getForPackageInRange(final String packageName, final int fromInt, final int toInt,
      final Continuation<? super List<UsageTimelineEntity>> $completion) {
    final String _sql = "SELECT * FROM usage_timeline WHERE package_name = ? AND date_int BETWEEN ? AND ? ORDER BY event_time_ms ASC";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageTimelineEntity>>() {
      @Override
      @NonNull
      public List<UsageTimelineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfEventTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "event_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_ms");
          final int _cursorIndexOfPreviousApp = CursorUtil.getColumnIndexOrThrow(_cursor, "previous_app");
          final List<UsageTimelineEntity> _result = new ArrayList<UsageTimelineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageTimelineEntity _item;
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
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final long _tmpEventTimeMs;
            _tmpEventTimeMs = _cursor.getLong(_cursorIndexOfEventTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            _item = new UsageTimelineEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpEventType,_tmpEventTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpDurationMs,_tmpPreviousApp);
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
  public Object getFullTimeline(final int fromInt, final int toInt,
      final Continuation<? super List<UsageTimelineEntity>> $completion) {
    final String _sql = "SELECT * FROM usage_timeline WHERE date_int BETWEEN ? AND ? ORDER BY event_time_ms ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageTimelineEntity>>() {
      @Override
      @NonNull
      public List<UsageTimelineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfEventTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "event_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_ms");
          final int _cursorIndexOfPreviousApp = CursorUtil.getColumnIndexOrThrow(_cursor, "previous_app");
          final List<UsageTimelineEntity> _result = new ArrayList<UsageTimelineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageTimelineEntity _item;
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
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final long _tmpEventTimeMs;
            _tmpEventTimeMs = _cursor.getLong(_cursorIndexOfEventTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            _item = new UsageTimelineEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpEventType,_tmpEventTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpDurationMs,_tmpPreviousApp);
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
      final Continuation<? super List<UsageTimelineEntity>> $completion) {
    final String _sql = "SELECT * FROM usage_timeline ORDER BY event_time_ms DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageTimelineEntity>>() {
      @Override
      @NonNull
      public List<UsageTimelineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfEventTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "event_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_ms");
          final int _cursorIndexOfPreviousApp = CursorUtil.getColumnIndexOrThrow(_cursor, "previous_app");
          final List<UsageTimelineEntity> _result = new ArrayList<UsageTimelineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageTimelineEntity _item;
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
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final long _tmpEventTimeMs;
            _tmpEventTimeMs = _cursor.getLong(_cursorIndexOfEventTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            _item = new UsageTimelineEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpEventType,_tmpEventTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpDurationMs,_tmpPreviousApp);
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
  public Object getForegroundEventsForDay(final int dateInt,
      final Continuation<? super List<UsageTimelineEntity>> $completion) {
    final String _sql = "SELECT * FROM usage_timeline WHERE date_int = ? AND event_type = 'foreground' ORDER BY event_time_ms ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageTimelineEntity>>() {
      @Override
      @NonNull
      public List<UsageTimelineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfEventTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "event_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_ms");
          final int _cursorIndexOfPreviousApp = CursorUtil.getColumnIndexOrThrow(_cursor, "previous_app");
          final List<UsageTimelineEntity> _result = new ArrayList<UsageTimelineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageTimelineEntity _item;
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
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final long _tmpEventTimeMs;
            _tmpEventTimeMs = _cursor.getLong(_cursorIndexOfEventTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            _item = new UsageTimelineEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpEventType,_tmpEventTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpDurationMs,_tmpPreviousApp);
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
  public Object getSessionDurations(final String packageName, final int fromInt, final int toInt,
      final Continuation<? super List<UsageTimelineEntity>> $completion) {
    final String _sql = "SELECT * FROM usage_timeline WHERE package_name = ? AND event_type = 'background' AND duration_ms > 0 AND date_int BETWEEN ? AND ? ORDER BY event_time_ms ASC";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UsageTimelineEntity>>() {
      @Override
      @NonNull
      public List<UsageTimelineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "event_type");
          final int _cursorIndexOfEventTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "event_time_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_ms");
          final int _cursorIndexOfPreviousApp = CursorUtil.getColumnIndexOrThrow(_cursor, "previous_app");
          final List<UsageTimelineEntity> _result = new ArrayList<UsageTimelineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsageTimelineEntity _item;
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
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            final long _tmpEventTimeMs;
            _tmpEventTimeMs = _cursor.getLong(_cursorIndexOfEventTimeMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            _item = new UsageTimelineEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpEventType,_tmpEventTimeMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpDurationMs,_tmpPreviousApp);
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
  public Object getSwitchPatterns(final int dateInt, final int limit,
      final Continuation<? super List<AppSwitch>> $completion) {
    final String _sql = "SELECT package_name AS packageName, previous_app AS previousApp, COUNT(*) AS switchCount FROM usage_timeline WHERE date_int = ? AND event_type = 'foreground' AND previous_app IS NOT NULL GROUP BY package_name, previous_app ORDER BY switchCount DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppSwitch>>() {
      @Override
      @NonNull
      public List<AppSwitch> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfPreviousApp = 1;
          final int _cursorIndexOfSwitchCount = 2;
          final List<AppSwitch> _result = new ArrayList<AppSwitch>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppSwitch _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpPreviousApp;
            if (_cursor.isNull(_cursorIndexOfPreviousApp)) {
              _tmpPreviousApp = null;
            } else {
              _tmpPreviousApp = _cursor.getString(_cursorIndexOfPreviousApp);
            }
            final int _tmpSwitchCount;
            _tmpSwitchCount = _cursor.getInt(_cursorIndexOfSwitchCount);
            _item = new AppSwitch(_tmpPackageName,_tmpPreviousApp,_tmpSwitchCount);
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
  public Object getActiveAppsForDay(final int dateInt,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT package_name FROM usage_timeline WHERE date_int = ? ORDER BY package_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
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
  public Object getActiveAppsInRange(final int fromInt, final int toInt,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT package_name FROM usage_timeline WHERE date_int BETWEEN ? AND ? ORDER BY package_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
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
  public Object getForegroundTimestamps(final int dateInt,
      final Continuation<? super List<Long>> $completion) {
    final String _sql = "SELECT event_time_ms FROM usage_timeline WHERE date_int = ? AND event_type = 'foreground' ORDER BY event_time_ms ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
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
  public Object totalCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM usage_timeline";
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
