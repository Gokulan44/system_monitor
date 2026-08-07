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
import com.soc.agent.database.entity.ScreenTimeEntity;
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
public final class ScreenTimeDao_Impl implements ScreenTimeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ScreenTimeEntity> __insertionAdapterOfScreenTimeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  public ScreenTimeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScreenTimeEntity = new EntityInsertionAdapter<ScreenTimeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `screen_time` (`bucket_start`,`granularity`,`total_time_ms`,`app_count`,`top_apps`,`updated_at`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ScreenTimeEntity entity) {
        statement.bindLong(1, entity.getBucketStart());
        if (entity.getGranularity() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getGranularity());
        }
        statement.bindLong(3, entity.getTotalTimeMs());
        statement.bindLong(4, entity.getAppCount());
        if (entity.getTopApps() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTopApps());
        }
        statement.bindLong(6, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM screen_time WHERE bucket_start < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ScreenTimeEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfScreenTimeEntity.insert(entity);
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
  public Object getAllByGranularity(final String granularity,
      final Continuation<? super List<ScreenTimeEntity>> $completion) {
    final String _sql = "SELECT * FROM screen_time WHERE granularity = ? ORDER BY bucket_start DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (granularity == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, granularity);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ScreenTimeEntity>>() {
      @Override
      @NonNull
      public List<ScreenTimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBucketStart = CursorUtil.getColumnIndexOrThrow(_cursor, "bucket_start");
          final int _cursorIndexOfGranularity = CursorUtil.getColumnIndexOrThrow(_cursor, "granularity");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "app_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<ScreenTimeEntity> _result = new ArrayList<ScreenTimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScreenTimeEntity _item;
            final long _tmpBucketStart;
            _tmpBucketStart = _cursor.getLong(_cursorIndexOfBucketStart);
            final String _tmpGranularity;
            if (_cursor.isNull(_cursorIndexOfGranularity)) {
              _tmpGranularity = null;
            } else {
              _tmpGranularity = _cursor.getString(_cursorIndexOfGranularity);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpAppCount;
            _tmpAppCount = _cursor.getInt(_cursorIndexOfAppCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ScreenTimeEntity(_tmpBucketStart,_tmpGranularity,_tmpTotalTimeMs,_tmpAppCount,_tmpTopApps,_tmpUpdatedAt);
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
  public Object getForBucket(final long bucketStart,
      final Continuation<? super ScreenTimeEntity> $completion) {
    final String _sql = "SELECT * FROM screen_time WHERE bucket_start = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, bucketStart);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ScreenTimeEntity>() {
      @Override
      @Nullable
      public ScreenTimeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBucketStart = CursorUtil.getColumnIndexOrThrow(_cursor, "bucket_start");
          final int _cursorIndexOfGranularity = CursorUtil.getColumnIndexOrThrow(_cursor, "granularity");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "app_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final ScreenTimeEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpBucketStart;
            _tmpBucketStart = _cursor.getLong(_cursorIndexOfBucketStart);
            final String _tmpGranularity;
            if (_cursor.isNull(_cursorIndexOfGranularity)) {
              _tmpGranularity = null;
            } else {
              _tmpGranularity = _cursor.getString(_cursorIndexOfGranularity);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpAppCount;
            _tmpAppCount = _cursor.getInt(_cursorIndexOfAppCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ScreenTimeEntity(_tmpBucketStart,_tmpGranularity,_tmpTotalTimeMs,_tmpAppCount,_tmpTopApps,_tmpUpdatedAt);
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
  public Object getRecent(final int limit,
      final Continuation<? super List<ScreenTimeEntity>> $completion) {
    final String _sql = "SELECT * FROM screen_time ORDER BY updated_at DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ScreenTimeEntity>>() {
      @Override
      @NonNull
      public List<ScreenTimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBucketStart = CursorUtil.getColumnIndexOrThrow(_cursor, "bucket_start");
          final int _cursorIndexOfGranularity = CursorUtil.getColumnIndexOrThrow(_cursor, "granularity");
          final int _cursorIndexOfTotalTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "total_time_ms");
          final int _cursorIndexOfAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "app_count");
          final int _cursorIndexOfTopApps = CursorUtil.getColumnIndexOrThrow(_cursor, "top_apps");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<ScreenTimeEntity> _result = new ArrayList<ScreenTimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScreenTimeEntity _item;
            final long _tmpBucketStart;
            _tmpBucketStart = _cursor.getLong(_cursorIndexOfBucketStart);
            final String _tmpGranularity;
            if (_cursor.isNull(_cursorIndexOfGranularity)) {
              _tmpGranularity = null;
            } else {
              _tmpGranularity = _cursor.getString(_cursorIndexOfGranularity);
            }
            final long _tmpTotalTimeMs;
            _tmpTotalTimeMs = _cursor.getLong(_cursorIndexOfTotalTimeMs);
            final int _tmpAppCount;
            _tmpAppCount = _cursor.getInt(_cursorIndexOfAppCount);
            final String _tmpTopApps;
            if (_cursor.isNull(_cursorIndexOfTopApps)) {
              _tmpTopApps = null;
            } else {
              _tmpTopApps = _cursor.getString(_cursorIndexOfTopApps);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ScreenTimeEntity(_tmpBucketStart,_tmpGranularity,_tmpTotalTimeMs,_tmpAppCount,_tmpTopApps,_tmpUpdatedAt);
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
  public Object getTotalInRange(final String granularity, final long fromMs, final long toMs,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT COALESCE(SUM(total_time_ms), 0) FROM screen_time WHERE granularity = ? AND bucket_start BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (granularity == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, granularity);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, fromMs);
    _argIndex = 3;
    _statement.bindLong(_argIndex, toMs);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @NonNull
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
  public Object totalCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM screen_time";
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
