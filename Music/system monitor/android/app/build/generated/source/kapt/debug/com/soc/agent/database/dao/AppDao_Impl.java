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
import com.soc.agent.database.entity.AppPermissionEntity;
import com.soc.agent.database.entity.InstalledAppEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class AppDao_Impl implements AppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<InstalledAppEntity> __insertionAdapterOfInstalledAppEntity;

  private final EntityInsertionAdapter<AppPermissionEntity> __insertionAdapterOfAppPermissionEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearApps;

  private final SharedSQLiteStatement __preparedStmtOfClearPermissions;

  public AppDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInstalledAppEntity = new EntityInsertionAdapter<InstalledAppEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `installed_apps` (`device_id`,`package_name`,`name`,`pid`,`cpu_pct`,`mem_b`,`status`,`risk`,`signature`,`first_seen`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InstalledAppEntity entity) {
        statement.bindLong(1, entity.getDeviceId());
        if (entity.getPackageName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPackageName());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getPid() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getPid());
        }
        statement.bindDouble(5, entity.getCpuPct());
        statement.bindLong(6, entity.getMemB());
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
        if (entity.getRisk() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRisk());
        }
        if (entity.getSignature() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSignature());
        }
        statement.bindLong(10, entity.getFirstSeen());
      }
    };
    this.__insertionAdapterOfAppPermissionEntity = new EntityInsertionAdapter<AppPermissionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `app_permissions` (`id`,`device_id`,`package_name`,`category`,`granted`,`name`,`timestamp_millis`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppPermissionEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        if (entity.getPackageName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPackageName());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        final int _tmp = entity.getGranted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getName());
        }
        statement.bindLong(7, entity.getTimestampMillis());
      }
    };
    this.__preparedStmtOfClearApps = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM installed_apps";
        return _query;
      }
    };
    this.__preparedStmtOfClearPermissions = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM app_permissions";
        return _query;
      }
    };
  }

  @Override
  public Object insertApps(final List<InstalledAppEntity> apps,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfInstalledAppEntity.insert(apps);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPermissions(final List<AppPermissionEntity> permissions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppPermissionEntity.insert(permissions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearApps(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearApps.acquire();
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
          __preparedStmtOfClearApps.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearPermissions(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearPermissions.acquire();
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
          __preparedStmtOfClearPermissions.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllApps(final Continuation<? super List<InstalledAppEntity>> $completion) {
    final String _sql = "SELECT * FROM installed_apps ORDER BY name COLLATE NOCASE ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<InstalledAppEntity>>() {
      @Override
      @NonNull
      public List<InstalledAppEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
          final int _cursorIndexOfCpuPct = CursorUtil.getColumnIndexOrThrow(_cursor, "cpu_pct");
          final int _cursorIndexOfMemB = CursorUtil.getColumnIndexOrThrow(_cursor, "mem_b");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRisk = CursorUtil.getColumnIndexOrThrow(_cursor, "risk");
          final int _cursorIndexOfSignature = CursorUtil.getColumnIndexOrThrow(_cursor, "signature");
          final int _cursorIndexOfFirstSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "first_seen");
          final List<InstalledAppEntity> _result = new ArrayList<InstalledAppEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InstalledAppEntity _item;
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Integer _tmpPid;
            if (_cursor.isNull(_cursorIndexOfPid)) {
              _tmpPid = null;
            } else {
              _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            }
            final double _tmpCpuPct;
            _tmpCpuPct = _cursor.getDouble(_cursorIndexOfCpuPct);
            final long _tmpMemB;
            _tmpMemB = _cursor.getLong(_cursorIndexOfMemB);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpRisk;
            if (_cursor.isNull(_cursorIndexOfRisk)) {
              _tmpRisk = null;
            } else {
              _tmpRisk = _cursor.getString(_cursorIndexOfRisk);
            }
            final String _tmpSignature;
            if (_cursor.isNull(_cursorIndexOfSignature)) {
              _tmpSignature = null;
            } else {
              _tmpSignature = _cursor.getString(_cursorIndexOfSignature);
            }
            final long _tmpFirstSeen;
            _tmpFirstSeen = _cursor.getLong(_cursorIndexOfFirstSeen);
            _item = new InstalledAppEntity(_tmpDeviceId,_tmpPackageName,_tmpName,_tmpPid,_tmpCpuPct,_tmpMemB,_tmpStatus,_tmpRisk,_tmpSignature,_tmpFirstSeen);
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
  public Object getAppsByRisk(final String risk,
      final Continuation<? super List<InstalledAppEntity>> $completion) {
    final String _sql = "SELECT * FROM installed_apps WHERE risk = ? ORDER BY name COLLATE NOCASE ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (risk == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, risk);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<InstalledAppEntity>>() {
      @Override
      @NonNull
      public List<InstalledAppEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
          final int _cursorIndexOfCpuPct = CursorUtil.getColumnIndexOrThrow(_cursor, "cpu_pct");
          final int _cursorIndexOfMemB = CursorUtil.getColumnIndexOrThrow(_cursor, "mem_b");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRisk = CursorUtil.getColumnIndexOrThrow(_cursor, "risk");
          final int _cursorIndexOfSignature = CursorUtil.getColumnIndexOrThrow(_cursor, "signature");
          final int _cursorIndexOfFirstSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "first_seen");
          final List<InstalledAppEntity> _result = new ArrayList<InstalledAppEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InstalledAppEntity _item;
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Integer _tmpPid;
            if (_cursor.isNull(_cursorIndexOfPid)) {
              _tmpPid = null;
            } else {
              _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            }
            final double _tmpCpuPct;
            _tmpCpuPct = _cursor.getDouble(_cursorIndexOfCpuPct);
            final long _tmpMemB;
            _tmpMemB = _cursor.getLong(_cursorIndexOfMemB);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpRisk;
            if (_cursor.isNull(_cursorIndexOfRisk)) {
              _tmpRisk = null;
            } else {
              _tmpRisk = _cursor.getString(_cursorIndexOfRisk);
            }
            final String _tmpSignature;
            if (_cursor.isNull(_cursorIndexOfSignature)) {
              _tmpSignature = null;
            } else {
              _tmpSignature = _cursor.getString(_cursorIndexOfSignature);
            }
            final long _tmpFirstSeen;
            _tmpFirstSeen = _cursor.getLong(_cursorIndexOfFirstSeen);
            _item = new InstalledAppEntity(_tmpDeviceId,_tmpPackageName,_tmpName,_tmpPid,_tmpCpuPct,_tmpMemB,_tmpStatus,_tmpRisk,_tmpSignature,_tmpFirstSeen);
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
  public Object getPermissionsForApp(final long deviceId, final String packageName,
      final Continuation<? super List<AppPermissionEntity>> $completion) {
    final String _sql = "SELECT * FROM app_permissions WHERE device_id = ? AND package_name = ? ORDER BY category ASC, name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, deviceId);
    _argIndex = 2;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppPermissionEntity>>() {
      @Override
      @NonNull
      public List<AppPermissionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "package_name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfGranted = CursorUtil.getColumnIndexOrThrow(_cursor, "granted");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final List<AppPermissionEntity> _result = new ArrayList<AppPermissionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppPermissionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpGranted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfGranted);
            _tmpGranted = _tmp != 0;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            _item = new AppPermissionEntity(_tmpId,_tmpDeviceId,_tmpPackageName,_tmpCategory,_tmpGranted,_tmpName,_tmpTimestampMillis);
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
