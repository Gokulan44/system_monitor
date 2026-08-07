package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soc.agent.database.entity.FocusModeEntity;
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
public final class FocusModeDao_Impl implements FocusModeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FocusModeEntity> __insertionAdapterOfFocusModeEntity;

  private final EntityDeletionOrUpdateAdapter<FocusModeEntity> __updateAdapterOfFocusModeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeactivate;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public FocusModeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFocusModeEntity = new EntityInsertionAdapter<FocusModeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `focus_mode` (`id`,`name`,`blocked_apps`,`start_time_ms`,`end_time_ms`,`active`,`schedule`,`created_at_ms`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FocusModeEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getBlockedApps() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBlockedApps());
        }
        statement.bindLong(4, entity.getStartTimeMs());
        statement.bindLong(5, entity.getEndTimeMs());
        final int _tmp = entity.getActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getSchedule() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getSchedule());
        }
        statement.bindLong(8, entity.getCreatedAtMs());
      }
    };
    this.__updateAdapterOfFocusModeEntity = new EntityDeletionOrUpdateAdapter<FocusModeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `focus_mode` SET `id` = ?,`name` = ?,`blocked_apps` = ?,`start_time_ms` = ?,`end_time_ms` = ?,`active` = ?,`schedule` = ?,`created_at_ms` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FocusModeEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getBlockedApps() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBlockedApps());
        }
        statement.bindLong(4, entity.getStartTimeMs());
        statement.bindLong(5, entity.getEndTimeMs());
        final int _tmp = entity.getActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getSchedule() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getSchedule());
        }
        statement.bindLong(8, entity.getCreatedAtMs());
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeactivate = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE focus_mode SET active = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeactivateAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE focus_mode SET active = 0";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM focus_mode WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final FocusModeEntity entity, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFocusModeEntity.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final FocusModeEntity entity, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFocusModeEntity.handle(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivate(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivate.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfDeactivate.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivateAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateAll.acquire();
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
          __preparedStmtOfDeactivateAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final long id, final Continuation<? super FocusModeEntity> $completion) {
    final String _sql = "SELECT * FROM focus_mode WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FocusModeEntity>() {
      @Override
      @Nullable
      public FocusModeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBlockedApps = CursorUtil.getColumnIndexOrThrow(_cursor, "blocked_apps");
          final int _cursorIndexOfStartTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time_ms");
          final int _cursorIndexOfEndTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time_ms");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_ms");
          final FocusModeEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBlockedApps;
            if (_cursor.isNull(_cursorIndexOfBlockedApps)) {
              _tmpBlockedApps = null;
            } else {
              _tmpBlockedApps = _cursor.getString(_cursorIndexOfBlockedApps);
            }
            final long _tmpStartTimeMs;
            _tmpStartTimeMs = _cursor.getLong(_cursorIndexOfStartTimeMs);
            final long _tmpEndTimeMs;
            _tmpEndTimeMs = _cursor.getLong(_cursorIndexOfEndTimeMs);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final String _tmpSchedule;
            if (_cursor.isNull(_cursorIndexOfSchedule)) {
              _tmpSchedule = null;
            } else {
              _tmpSchedule = _cursor.getString(_cursorIndexOfSchedule);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _result = new FocusModeEntity(_tmpId,_tmpName,_tmpBlockedApps,_tmpStartTimeMs,_tmpEndTimeMs,_tmpActive,_tmpSchedule,_tmpCreatedAtMs);
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
  public Object getAll(final Continuation<? super List<FocusModeEntity>> $completion) {
    final String _sql = "SELECT * FROM focus_mode ORDER BY created_at_ms DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FocusModeEntity>>() {
      @Override
      @NonNull
      public List<FocusModeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBlockedApps = CursorUtil.getColumnIndexOrThrow(_cursor, "blocked_apps");
          final int _cursorIndexOfStartTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time_ms");
          final int _cursorIndexOfEndTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time_ms");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_ms");
          final List<FocusModeEntity> _result = new ArrayList<FocusModeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FocusModeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBlockedApps;
            if (_cursor.isNull(_cursorIndexOfBlockedApps)) {
              _tmpBlockedApps = null;
            } else {
              _tmpBlockedApps = _cursor.getString(_cursorIndexOfBlockedApps);
            }
            final long _tmpStartTimeMs;
            _tmpStartTimeMs = _cursor.getLong(_cursorIndexOfStartTimeMs);
            final long _tmpEndTimeMs;
            _tmpEndTimeMs = _cursor.getLong(_cursorIndexOfEndTimeMs);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final String _tmpSchedule;
            if (_cursor.isNull(_cursorIndexOfSchedule)) {
              _tmpSchedule = null;
            } else {
              _tmpSchedule = _cursor.getString(_cursorIndexOfSchedule);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _item = new FocusModeEntity(_tmpId,_tmpName,_tmpBlockedApps,_tmpStartTimeMs,_tmpEndTimeMs,_tmpActive,_tmpSchedule,_tmpCreatedAtMs);
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
  public Object getActive(final Continuation<? super List<FocusModeEntity>> $completion) {
    final String _sql = "SELECT * FROM focus_mode WHERE active = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FocusModeEntity>>() {
      @Override
      @NonNull
      public List<FocusModeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBlockedApps = CursorUtil.getColumnIndexOrThrow(_cursor, "blocked_apps");
          final int _cursorIndexOfStartTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time_ms");
          final int _cursorIndexOfEndTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time_ms");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_ms");
          final List<FocusModeEntity> _result = new ArrayList<FocusModeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FocusModeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBlockedApps;
            if (_cursor.isNull(_cursorIndexOfBlockedApps)) {
              _tmpBlockedApps = null;
            } else {
              _tmpBlockedApps = _cursor.getString(_cursorIndexOfBlockedApps);
            }
            final long _tmpStartTimeMs;
            _tmpStartTimeMs = _cursor.getLong(_cursorIndexOfStartTimeMs);
            final long _tmpEndTimeMs;
            _tmpEndTimeMs = _cursor.getLong(_cursorIndexOfEndTimeMs);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final String _tmpSchedule;
            if (_cursor.isNull(_cursorIndexOfSchedule)) {
              _tmpSchedule = null;
            } else {
              _tmpSchedule = _cursor.getString(_cursorIndexOfSchedule);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _item = new FocusModeEntity(_tmpId,_tmpName,_tmpBlockedApps,_tmpStartTimeMs,_tmpEndTimeMs,_tmpActive,_tmpSchedule,_tmpCreatedAtMs);
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
  public Object getByName(final String name,
      final Continuation<? super FocusModeEntity> $completion) {
    final String _sql = "SELECT * FROM focus_mode WHERE name = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FocusModeEntity>() {
      @Override
      @Nullable
      public FocusModeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBlockedApps = CursorUtil.getColumnIndexOrThrow(_cursor, "blocked_apps");
          final int _cursorIndexOfStartTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time_ms");
          final int _cursorIndexOfEndTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time_ms");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_ms");
          final FocusModeEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBlockedApps;
            if (_cursor.isNull(_cursorIndexOfBlockedApps)) {
              _tmpBlockedApps = null;
            } else {
              _tmpBlockedApps = _cursor.getString(_cursorIndexOfBlockedApps);
            }
            final long _tmpStartTimeMs;
            _tmpStartTimeMs = _cursor.getLong(_cursorIndexOfStartTimeMs);
            final long _tmpEndTimeMs;
            _tmpEndTimeMs = _cursor.getLong(_cursorIndexOfEndTimeMs);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final String _tmpSchedule;
            if (_cursor.isNull(_cursorIndexOfSchedule)) {
              _tmpSchedule = null;
            } else {
              _tmpSchedule = _cursor.getString(_cursorIndexOfSchedule);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _result = new FocusModeEntity(_tmpId,_tmpName,_tmpBlockedApps,_tmpStartTimeMs,_tmpEndTimeMs,_tmpActive,_tmpSchedule,_tmpCreatedAtMs);
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
  public Object isPackageBlocked(final String packageName,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM focus_mode WHERE active = 1 AND blocked_apps LIKE '%' || ? || '%'";
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
  public Object getActiveModesBlockingPackage(final String packageName,
      final Continuation<? super List<FocusModeEntity>> $completion) {
    final String _sql = "SELECT * FROM focus_mode WHERE active = 1 AND blocked_apps LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FocusModeEntity>>() {
      @Override
      @NonNull
      public List<FocusModeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBlockedApps = CursorUtil.getColumnIndexOrThrow(_cursor, "blocked_apps");
          final int _cursorIndexOfStartTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time_ms");
          final int _cursorIndexOfEndTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time_ms");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_ms");
          final List<FocusModeEntity> _result = new ArrayList<FocusModeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FocusModeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBlockedApps;
            if (_cursor.isNull(_cursorIndexOfBlockedApps)) {
              _tmpBlockedApps = null;
            } else {
              _tmpBlockedApps = _cursor.getString(_cursorIndexOfBlockedApps);
            }
            final long _tmpStartTimeMs;
            _tmpStartTimeMs = _cursor.getLong(_cursorIndexOfStartTimeMs);
            final long _tmpEndTimeMs;
            _tmpEndTimeMs = _cursor.getLong(_cursorIndexOfEndTimeMs);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final String _tmpSchedule;
            if (_cursor.isNull(_cursorIndexOfSchedule)) {
              _tmpSchedule = null;
            } else {
              _tmpSchedule = _cursor.getString(_cursorIndexOfSchedule);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _item = new FocusModeEntity(_tmpId,_tmpName,_tmpBlockedApps,_tmpStartTimeMs,_tmpEndTimeMs,_tmpActive,_tmpSchedule,_tmpCreatedAtMs);
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
  public Object getScheduled(final Continuation<? super List<FocusModeEntity>> $completion) {
    final String _sql = "SELECT * FROM focus_mode WHERE schedule != ''";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FocusModeEntity>>() {
      @Override
      @NonNull
      public List<FocusModeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBlockedApps = CursorUtil.getColumnIndexOrThrow(_cursor, "blocked_apps");
          final int _cursorIndexOfStartTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time_ms");
          final int _cursorIndexOfEndTimeMs = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time_ms");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "schedule");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_ms");
          final List<FocusModeEntity> _result = new ArrayList<FocusModeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FocusModeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBlockedApps;
            if (_cursor.isNull(_cursorIndexOfBlockedApps)) {
              _tmpBlockedApps = null;
            } else {
              _tmpBlockedApps = _cursor.getString(_cursorIndexOfBlockedApps);
            }
            final long _tmpStartTimeMs;
            _tmpStartTimeMs = _cursor.getLong(_cursorIndexOfStartTimeMs);
            final long _tmpEndTimeMs;
            _tmpEndTimeMs = _cursor.getLong(_cursorIndexOfEndTimeMs);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final String _tmpSchedule;
            if (_cursor.isNull(_cursorIndexOfSchedule)) {
              _tmpSchedule = null;
            } else {
              _tmpSchedule = _cursor.getString(_cursorIndexOfSchedule);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _item = new FocusModeEntity(_tmpId,_tmpName,_tmpBlockedApps,_tmpStartTimeMs,_tmpEndTimeMs,_tmpActive,_tmpSchedule,_tmpCreatedAtMs);
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
    final String _sql = "SELECT COUNT(*) FROM focus_mode";
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
