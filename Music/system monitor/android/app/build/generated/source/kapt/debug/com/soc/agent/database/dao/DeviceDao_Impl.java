package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soc.agent.database.entity.DeviceEntity;
import com.soc.agent.database.entity.DeviceHistoryEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class DeviceDao_Impl implements DeviceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DeviceHistoryEntity> __insertionAdapterOfDeviceHistoryEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  private final EntityUpsertionAdapter<DeviceEntity> __upsertionAdapterOfDeviceEntity;

  public DeviceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDeviceHistoryEntity = new EntityInsertionAdapter<DeviceHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `device_history` (`id`,`device_id`,`event`,`detail`,`timestamp_millis`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DeviceHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        if (entity.getEvent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEvent());
        }
        if (entity.getDetail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDetail());
        }
        statement.bindLong(5, entity.getTimestampMillis());
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM devices";
        return _query;
      }
    };
    this.__upsertionAdapterOfDeviceEntity = new EntityUpsertionAdapter<DeviceEntity>(new EntityInsertionAdapter<DeviceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `devices` (`id`,`agent_id`,`device_id`,`name`,`manufacturer`,`model`,`android_version`,`app_version`,`platform`,`status`,`risk`,`risk_score`,`last_seen`,`registered_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DeviceEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getAgentId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAgentId());
        }
        if (entity.getDeviceId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getDeviceId());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getManufacturer() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getManufacturer());
        }
        if (entity.getModel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getModel());
        }
        if (entity.getAndroidVersion() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getAndroidVersion());
        }
        if (entity.getAppVersion() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAppVersion());
        }
        if (entity.getPlatform() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPlatform());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getStatus());
        }
        if (entity.getRisk() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getRisk());
        }
        statement.bindLong(12, entity.getRiskScore());
        statement.bindLong(13, entity.getLastSeen());
        statement.bindLong(14, entity.getRegisteredAt());
      }
    }, new EntityDeletionOrUpdateAdapter<DeviceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `devices` SET `id` = ?,`agent_id` = ?,`device_id` = ?,`name` = ?,`manufacturer` = ?,`model` = ?,`android_version` = ?,`app_version` = ?,`platform` = ?,`status` = ?,`risk` = ?,`risk_score` = ?,`last_seen` = ?,`registered_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DeviceEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getAgentId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAgentId());
        }
        if (entity.getDeviceId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getDeviceId());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getManufacturer() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getManufacturer());
        }
        if (entity.getModel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getModel());
        }
        if (entity.getAndroidVersion() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getAndroidVersion());
        }
        if (entity.getAppVersion() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAppVersion());
        }
        if (entity.getPlatform() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPlatform());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getStatus());
        }
        if (entity.getRisk() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getRisk());
        }
        statement.bindLong(12, entity.getRiskScore());
        statement.bindLong(13, entity.getLastSeen());
        statement.bindLong(14, entity.getRegisteredAt());
        statement.bindLong(15, entity.getId());
      }
    });
  }

  @Override
  public Object insertHistory(final DeviceHistoryEntity event,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDeviceHistoryEntity.insert(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clear(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
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
          __preparedStmtOfClear.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertDevice(final DeviceEntity device,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfDeviceEntity.upsert(device);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDevice(final String agentId,
      final Continuation<? super DeviceEntity> $completion) {
    final String _sql = "SELECT * FROM devices WHERE agent_id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (agentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, agentId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DeviceEntity>() {
      @Override
      @Nullable
      public DeviceEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAgentId = CursorUtil.getColumnIndexOrThrow(_cursor, "agent_id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfAndroidVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "android_version");
          final int _cursorIndexOfAppVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "app_version");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRisk = CursorUtil.getColumnIndexOrThrow(_cursor, "risk");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "risk_score");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "last_seen");
          final int _cursorIndexOfRegisteredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "registered_at");
          final DeviceEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpAgentId;
            if (_cursor.isNull(_cursorIndexOfAgentId)) {
              _tmpAgentId = null;
            } else {
              _tmpAgentId = _cursor.getString(_cursorIndexOfAgentId);
            }
            final Long _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpManufacturer;
            if (_cursor.isNull(_cursorIndexOfManufacturer)) {
              _tmpManufacturer = null;
            } else {
              _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            }
            final String _tmpModel;
            if (_cursor.isNull(_cursorIndexOfModel)) {
              _tmpModel = null;
            } else {
              _tmpModel = _cursor.getString(_cursorIndexOfModel);
            }
            final String _tmpAndroidVersion;
            if (_cursor.isNull(_cursorIndexOfAndroidVersion)) {
              _tmpAndroidVersion = null;
            } else {
              _tmpAndroidVersion = _cursor.getString(_cursorIndexOfAndroidVersion);
            }
            final String _tmpAppVersion;
            if (_cursor.isNull(_cursorIndexOfAppVersion)) {
              _tmpAppVersion = null;
            } else {
              _tmpAppVersion = _cursor.getString(_cursorIndexOfAppVersion);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
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
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpRegisteredAt;
            _tmpRegisteredAt = _cursor.getLong(_cursorIndexOfRegisteredAt);
            _result = new DeviceEntity(_tmpId,_tmpAgentId,_tmpDeviceId,_tmpName,_tmpManufacturer,_tmpModel,_tmpAndroidVersion,_tmpAppVersion,_tmpPlatform,_tmpStatus,_tmpRisk,_tmpRiskScore,_tmpLastSeen,_tmpRegisteredAt);
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
  public Object getAllDevices(final Continuation<? super List<DeviceEntity>> $completion) {
    final String _sql = "SELECT * FROM devices ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DeviceEntity>>() {
      @Override
      @NonNull
      public List<DeviceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAgentId = CursorUtil.getColumnIndexOrThrow(_cursor, "agent_id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfAndroidVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "android_version");
          final int _cursorIndexOfAppVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "app_version");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRisk = CursorUtil.getColumnIndexOrThrow(_cursor, "risk");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "risk_score");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "last_seen");
          final int _cursorIndexOfRegisteredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "registered_at");
          final List<DeviceEntity> _result = new ArrayList<DeviceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DeviceEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpAgentId;
            if (_cursor.isNull(_cursorIndexOfAgentId)) {
              _tmpAgentId = null;
            } else {
              _tmpAgentId = _cursor.getString(_cursorIndexOfAgentId);
            }
            final Long _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpManufacturer;
            if (_cursor.isNull(_cursorIndexOfManufacturer)) {
              _tmpManufacturer = null;
            } else {
              _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            }
            final String _tmpModel;
            if (_cursor.isNull(_cursorIndexOfModel)) {
              _tmpModel = null;
            } else {
              _tmpModel = _cursor.getString(_cursorIndexOfModel);
            }
            final String _tmpAndroidVersion;
            if (_cursor.isNull(_cursorIndexOfAndroidVersion)) {
              _tmpAndroidVersion = null;
            } else {
              _tmpAndroidVersion = _cursor.getString(_cursorIndexOfAndroidVersion);
            }
            final String _tmpAppVersion;
            if (_cursor.isNull(_cursorIndexOfAppVersion)) {
              _tmpAppVersion = null;
            } else {
              _tmpAppVersion = _cursor.getString(_cursorIndexOfAppVersion);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
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
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpRegisteredAt;
            _tmpRegisteredAt = _cursor.getLong(_cursorIndexOfRegisteredAt);
            _item = new DeviceEntity(_tmpId,_tmpAgentId,_tmpDeviceId,_tmpName,_tmpManufacturer,_tmpModel,_tmpAndroidVersion,_tmpAppVersion,_tmpPlatform,_tmpStatus,_tmpRisk,_tmpRiskScore,_tmpLastSeen,_tmpRegisteredAt);
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
  public Object getLatestDevice(final Continuation<? super DeviceEntity> $completion) {
    final String _sql = "SELECT * FROM devices ORDER BY id DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DeviceEntity>() {
      @Override
      @Nullable
      public DeviceEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAgentId = CursorUtil.getColumnIndexOrThrow(_cursor, "agent_id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfAndroidVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "android_version");
          final int _cursorIndexOfAppVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "app_version");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRisk = CursorUtil.getColumnIndexOrThrow(_cursor, "risk");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "risk_score");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "last_seen");
          final int _cursorIndexOfRegisteredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "registered_at");
          final DeviceEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpAgentId;
            if (_cursor.isNull(_cursorIndexOfAgentId)) {
              _tmpAgentId = null;
            } else {
              _tmpAgentId = _cursor.getString(_cursorIndexOfAgentId);
            }
            final Long _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpManufacturer;
            if (_cursor.isNull(_cursorIndexOfManufacturer)) {
              _tmpManufacturer = null;
            } else {
              _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            }
            final String _tmpModel;
            if (_cursor.isNull(_cursorIndexOfModel)) {
              _tmpModel = null;
            } else {
              _tmpModel = _cursor.getString(_cursorIndexOfModel);
            }
            final String _tmpAndroidVersion;
            if (_cursor.isNull(_cursorIndexOfAndroidVersion)) {
              _tmpAndroidVersion = null;
            } else {
              _tmpAndroidVersion = _cursor.getString(_cursorIndexOfAndroidVersion);
            }
            final String _tmpAppVersion;
            if (_cursor.isNull(_cursorIndexOfAppVersion)) {
              _tmpAppVersion = null;
            } else {
              _tmpAppVersion = _cursor.getString(_cursorIndexOfAppVersion);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
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
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpRegisteredAt;
            _tmpRegisteredAt = _cursor.getLong(_cursorIndexOfRegisteredAt);
            _result = new DeviceEntity(_tmpId,_tmpAgentId,_tmpDeviceId,_tmpName,_tmpManufacturer,_tmpModel,_tmpAndroidVersion,_tmpAppVersion,_tmpPlatform,_tmpStatus,_tmpRisk,_tmpRiskScore,_tmpLastSeen,_tmpRegisteredAt);
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
