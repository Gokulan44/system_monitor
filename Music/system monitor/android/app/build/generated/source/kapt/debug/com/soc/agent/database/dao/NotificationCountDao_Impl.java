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
import com.soc.agent.database.entity.NotificationCountEntity;
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
public final class NotificationCountDao_Impl implements NotificationCountDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NotificationCountEntity> __insertionAdapterOfNotificationCountEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  public NotificationCountDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotificationCountEntity = new EntityInsertionAdapter<NotificationCountEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `notification_count` (`id`,`package_name`,`app_name`,`title`,`category`,`priority`,`posted_at_ms`,`hour_of_day`,`day_of_week`,`date_int`,`month_int`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NotificationCountEntity entity) {
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
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTitle());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategory());
        }
        statement.bindLong(6, entity.getPriority());
        statement.bindLong(7, entity.getPostedAtMs());
        statement.bindLong(8, entity.getHourOfDay());
        statement.bindLong(9, entity.getDayOfWeek());
        statement.bindLong(10, entity.getDateInt());
        statement.bindLong(11, entity.getMonthInt());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM notification_count WHERE posted_at_ms < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final NotificationCountEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNotificationCountEntity.insert(entity);
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
  public Object totalNotifications(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM notification_count";
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
  public Object totalForPackage(final String packageName,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM notification_count WHERE package_name = ?";
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
  public Object notificationsPerPackageOnDay(final int dateInt,
      final Continuation<? super List<PackageNotificationRank>> $completion) {
    final String _sql = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE date_int = ? GROUP BY package_name ORDER BY notificationCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dateInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PackageNotificationRank>>() {
      @Override
      @NonNull
      public List<PackageNotificationRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<PackageNotificationRank> _result = new ArrayList<PackageNotificationRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PackageNotificationRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new PackageNotificationRank(_tmpPackageName,_tmpNotificationCount);
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
  public Object notificationsPerPackageInRange(final int fromInt, final int toInt,
      final Continuation<? super List<PackageNotificationRank>> $completion) {
    final String _sql = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE date_int BETWEEN ? AND ? GROUP BY package_name ORDER BY notificationCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PackageNotificationRank>>() {
      @Override
      @NonNull
      public List<PackageNotificationRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<PackageNotificationRank> _result = new ArrayList<PackageNotificationRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PackageNotificationRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new PackageNotificationRank(_tmpPackageName,_tmpNotificationCount);
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
  public Object topPackages(final int limit,
      final Continuation<? super List<PackageNotificationRank>> $completion) {
    final String _sql = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count GROUP BY package_name ORDER BY notificationCount DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PackageNotificationRank>>() {
      @Override
      @NonNull
      public List<PackageNotificationRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<PackageNotificationRank> _result = new ArrayList<PackageNotificationRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PackageNotificationRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new PackageNotificationRank(_tmpPackageName,_tmpNotificationCount);
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
      final Continuation<? super List<HourlyNotification>> $completion) {
    final String _sql = "SELECT hour_of_day AS hourOfDay, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = ? AND date_int BETWEEN ? AND ? GROUP BY hour_of_day ORDER BY hour_of_day ASC";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HourlyNotification>>() {
      @Override
      @NonNull
      public List<HourlyNotification> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHourOfDay = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<HourlyNotification> _result = new ArrayList<HourlyNotification>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HourlyNotification _item;
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new HourlyNotification(_tmpHourOfDay,_tmpNotificationCount);
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
      final Continuation<? super List<DailyNotification>> $completion) {
    final String _sql = "SELECT day_of_week AS dayOfWeek, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = ? AND date_int BETWEEN ? AND ? GROUP BY day_of_week ORDER BY day_of_week ASC";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyNotification>>() {
      @Override
      @NonNull
      public List<DailyNotification> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDayOfWeek = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<DailyNotification> _result = new ArrayList<DailyNotification>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyNotification _item;
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new DailyNotification(_tmpDayOfWeek,_tmpNotificationCount);
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
      final Continuation<? super List<HourlyNotification>> $completion) {
    final String _sql = "SELECT hour_of_day AS hourOfDay, COUNT(*) AS notificationCount FROM notification_count WHERE date_int BETWEEN ? AND ? GROUP BY hour_of_day ORDER BY notificationCount DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toInt);
    _argIndex = 3;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HourlyNotification>>() {
      @Override
      @NonNull
      public List<HourlyNotification> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfHourOfDay = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<HourlyNotification> _result = new ArrayList<HourlyNotification>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HourlyNotification _item;
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new HourlyNotification(_tmpHourOfDay,_tmpNotificationCount);
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
  public Object dailyTrend(final String packageName, final int fromInt, final int toInt,
      final Continuation<? super List<DailyNotificationCount>> $completion) {
    final String _sql = "SELECT date_int AS dateInt, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = ? AND date_int BETWEEN ? AND ? GROUP BY date_int ORDER BY date_int ASC";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyNotificationCount>>() {
      @Override
      @NonNull
      public List<DailyNotificationCount> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDateInt = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<DailyNotificationCount> _result = new ArrayList<DailyNotificationCount>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyNotificationCount _item;
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new DailyNotificationCount(_tmpDateInt,_tmpNotificationCount);
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
  public Object recentForPackage(final String packageName, final int limit,
      final Continuation<? super List<NotificationCountEntity>> $completion) {
    final String _sql = "SELECT * FROM notification_count WHERE package_name = ? ORDER BY posted_at_ms DESC LIMIT ?";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NotificationCountEntity>>() {
      @Override
      @NonNull
      public List<NotificationCountEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "app_name");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfPostedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "posted_at_ms");
          final int _cursorIndexOfHourOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_of_day");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "day_of_week");
          final int _cursorIndexOfDateInt = CursorUtil.getColumnIndexOrThrow(_cursor, "date_int");
          final int _cursorIndexOfMonthInt = CursorUtil.getColumnIndexOrThrow(_cursor, "month_int");
          final List<NotificationCountEntity> _result = new ArrayList<NotificationCountEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NotificationCountEntity _item;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpPostedAtMs;
            _tmpPostedAtMs = _cursor.getLong(_cursorIndexOfPostedAtMs);
            final int _tmpHourOfDay;
            _tmpHourOfDay = _cursor.getInt(_cursorIndexOfHourOfDay);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final int _tmpDateInt;
            _tmpDateInt = _cursor.getInt(_cursorIndexOfDateInt);
            final int _tmpMonthInt;
            _tmpMonthInt = _cursor.getInt(_cursorIndexOfMonthInt);
            _item = new NotificationCountEntity(_tmpId,_tmpPackageName,_tmpAppName,_tmpTitle,_tmpCategory,_tmpPriority,_tmpPostedAtMs,_tmpHourOfDay,_tmpDayOfWeek,_tmpDateInt,_tmpMonthInt);
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
  public Object byCategory(final String packageName,
      final Continuation<? super List<CategoryNotification>> $completion) {
    final String _sql = "SELECT category, COUNT(*) AS notificationCount FROM notification_count WHERE package_name = ? GROUP BY category ORDER BY notificationCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CategoryNotification>>() {
      @Override
      @NonNull
      public List<CategoryNotification> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategory = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<CategoryNotification> _result = new ArrayList<CategoryNotification>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryNotification _item;
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new CategoryNotification(_tmpCategory,_tmpNotificationCount);
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
  public Object highPriorityByPackage(final int minPriority, final int fromInt, final int toInt,
      final Continuation<? super List<PackageNotificationRank>> $completion) {
    final String _sql = "SELECT package_name AS packageName, COUNT(*) AS notificationCount FROM notification_count WHERE priority >= ? AND date_int BETWEEN ? AND ? GROUP BY package_name ORDER BY notificationCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, minPriority);
    _argIndex = 2;
    _statement.bindLong(_argIndex, fromInt);
    _argIndex = 3;
    _statement.bindLong(_argIndex, toInt);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PackageNotificationRank>>() {
      @Override
      @NonNull
      public List<PackageNotificationRank> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfNotificationCount = 1;
          final List<PackageNotificationRank> _result = new ArrayList<PackageNotificationRank>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PackageNotificationRank _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpNotificationCount;
            _tmpNotificationCount = _cursor.getInt(_cursorIndexOfNotificationCount);
            _item = new PackageNotificationRank(_tmpPackageName,_tmpNotificationCount);
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
  public Object firstNotificationTime(final String packageName,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MIN(posted_at_ms) FROM notification_count WHERE package_name = ?";
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
  public Object lastNotificationTime(final String packageName,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MAX(posted_at_ms) FROM notification_count WHERE package_name = ?";
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
    final String _sql = "SELECT COUNT(DISTINCT date_int) FROM notification_count WHERE package_name = ? AND date_int BETWEEN ? AND ?";
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
  public Object totalCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM notification_count";
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
