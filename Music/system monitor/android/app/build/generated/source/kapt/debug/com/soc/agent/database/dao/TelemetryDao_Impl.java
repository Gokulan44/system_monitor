package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soc.agent.database.Converters;
import com.soc.agent.database.entity.BatteryStatusEntity;
import com.soc.agent.database.entity.CpuUsageEntity;
import com.soc.agent.database.entity.DeviceInfoEntity;
import com.soc.agent.database.entity.MemoryUsageEntity;
import com.soc.agent.database.entity.StorageUsageEntity;
import java.lang.Class;
import java.lang.Double;
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
public final class TelemetryDao_Impl implements TelemetryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CpuUsageEntity> __insertionAdapterOfCpuUsageEntity;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<MemoryUsageEntity> __insertionAdapterOfMemoryUsageEntity;

  private final EntityInsertionAdapter<StorageUsageEntity> __insertionAdapterOfStorageUsageEntity;

  private final EntityInsertionAdapter<BatteryStatusEntity> __insertionAdapterOfBatteryStatusEntity;

  private final EntityInsertionAdapter<DeviceInfoEntity> __insertionAdapterOfDeviceInfoEntity;

  public TelemetryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCpuUsageEntity = new EntityInsertionAdapter<CpuUsageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `cpu_usage` (`id`,`device_id`,`timestamp_millis`,`load_pct`,`cores`,`per_core`,`speed_ghz`,`temp_c`,`usage_history`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CpuUsageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        statement.bindLong(3, entity.getTimestampMillis());
        statement.bindDouble(4, entity.getLoadPct());
        statement.bindLong(5, entity.getCores());
        final String _tmp = __converters.toDoubleList(entity.getPerCore());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        if (entity.getSpeedGhz() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getSpeedGhz());
        }
        if (entity.getTempC() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getTempC());
        }
        final String _tmp_1 = __converters.toDoubleList(entity.getUsageHistory());
        if (_tmp_1 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_1);
        }
      }
    };
    this.__insertionAdapterOfMemoryUsageEntity = new EntityInsertionAdapter<MemoryUsageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `memory_usage` (`id`,`device_id`,`timestamp_millis`,`total_b`,`used_b`,`free_b`,`usage_pct`,`swap_total_b`,`swap_used_b`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MemoryUsageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        statement.bindLong(3, entity.getTimestampMillis());
        statement.bindLong(4, entity.getTotalB());
        statement.bindLong(5, entity.getUsedB());
        statement.bindLong(6, entity.getFreeB());
        statement.bindDouble(7, entity.getUsagePct());
        if (entity.getSwapTotalB() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getSwapTotalB());
        }
        if (entity.getSwapUsedB() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getSwapUsedB());
        }
      }
    };
    this.__insertionAdapterOfStorageUsageEntity = new EntityInsertionAdapter<StorageUsageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `storage_usage` (`id`,`device_id`,`timestamp_millis`,`filesystem`,`mount`,`type`,`total_b`,`used_b`,`free_b`,`usage_pct`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StorageUsageEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        statement.bindLong(3, entity.getTimestampMillis());
        if (entity.getFilesystem() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFilesystem());
        }
        if (entity.getMount() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMount());
        }
        if (entity.getType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getType());
        }
        statement.bindLong(7, entity.getTotalB());
        statement.bindLong(8, entity.getUsedB());
        statement.bindLong(9, entity.getFreeB());
        statement.bindDouble(10, entity.getUsagePct());
      }
    };
    this.__insertionAdapterOfBatteryStatusEntity = new EntityInsertionAdapter<BatteryStatusEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `battery_status` (`id`,`device_id`,`timestamp_millis`,`has_battery`,`percent`,`charging`,`status`,`time_remaining`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BatteryStatusEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        statement.bindLong(3, entity.getTimestampMillis());
        final int _tmp = entity.getHasBattery() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getPercent());
        final int _tmp_1 = entity.getCharging() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
        if (entity.getTimeRemaining() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getTimeRemaining());
        }
      }
    };
    this.__insertionAdapterOfDeviceInfoEntity = new EntityInsertionAdapter<DeviceInfoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `device_info` (`id`,`device_id`,`timestamp_millis`,`manufacturer`,`model`,`kernel`,`os_name`,`os_version`,`security_patch_level`,`os_build`,`uptime_sec`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DeviceInfoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        statement.bindLong(3, entity.getTimestampMillis());
        if (entity.getManufacturer() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getManufacturer());
        }
        if (entity.getModel() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getModel());
        }
        if (entity.getKernel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getKernel());
        }
        if (entity.getOsName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getOsName());
        }
        if (entity.getOsVersion() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getOsVersion());
        }
        if (entity.getSecurityPatchLevel() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSecurityPatchLevel());
        }
        if (entity.getOsBuild() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getOsBuild());
        }
        statement.bindLong(11, entity.getUptimeSec());
      }
    };
  }

  @Override
  public Object insertCpu(final CpuUsageEntity sample,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCpuUsageEntity.insert(sample);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMemory(final MemoryUsageEntity sample,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMemoryUsageEntity.insert(sample);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertStorage(final List<StorageUsageEntity> samples,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStorageUsageEntity.insert(samples);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertBattery(final BatteryStatusEntity sample,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBatteryStatusEntity.insert(sample);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertDeviceInfo(final DeviceInfoEntity sample,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDeviceInfoEntity.insert(sample);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestCpu(final Continuation<? super CpuUsageEntity> $completion) {
    final String _sql = "SELECT * FROM cpu_usage ORDER BY timestamp_millis DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CpuUsageEntity>() {
      @Override
      @Nullable
      public CpuUsageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfLoadPct = CursorUtil.getColumnIndexOrThrow(_cursor, "load_pct");
          final int _cursorIndexOfCores = CursorUtil.getColumnIndexOrThrow(_cursor, "cores");
          final int _cursorIndexOfPerCore = CursorUtil.getColumnIndexOrThrow(_cursor, "per_core");
          final int _cursorIndexOfSpeedGhz = CursorUtil.getColumnIndexOrThrow(_cursor, "speed_ghz");
          final int _cursorIndexOfTempC = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_c");
          final int _cursorIndexOfUsageHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "usage_history");
          final CpuUsageEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final double _tmpLoadPct;
            _tmpLoadPct = _cursor.getDouble(_cursorIndexOfLoadPct);
            final int _tmpCores;
            _tmpCores = _cursor.getInt(_cursorIndexOfCores);
            final List<Double> _tmpPerCore;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfPerCore)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfPerCore);
            }
            _tmpPerCore = __converters.fromDoubleList(_tmp);
            final Double _tmpSpeedGhz;
            if (_cursor.isNull(_cursorIndexOfSpeedGhz)) {
              _tmpSpeedGhz = null;
            } else {
              _tmpSpeedGhz = _cursor.getDouble(_cursorIndexOfSpeedGhz);
            }
            final Double _tmpTempC;
            if (_cursor.isNull(_cursorIndexOfTempC)) {
              _tmpTempC = null;
            } else {
              _tmpTempC = _cursor.getDouble(_cursorIndexOfTempC);
            }
            final List<Double> _tmpUsageHistory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfUsageHistory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfUsageHistory);
            }
            _tmpUsageHistory = __converters.fromDoubleList(_tmp_1);
            _result = new CpuUsageEntity(_tmpId,_tmpDeviceId,_tmpTimestampMillis,_tmpLoadPct,_tmpCores,_tmpPerCore,_tmpSpeedGhz,_tmpTempC,_tmpUsageHistory);
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
  public Object getLatestMemory(final Continuation<? super MemoryUsageEntity> $completion) {
    final String _sql = "SELECT * FROM memory_usage ORDER BY timestamp_millis DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MemoryUsageEntity>() {
      @Override
      @Nullable
      public MemoryUsageEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfTotalB = CursorUtil.getColumnIndexOrThrow(_cursor, "total_b");
          final int _cursorIndexOfUsedB = CursorUtil.getColumnIndexOrThrow(_cursor, "used_b");
          final int _cursorIndexOfFreeB = CursorUtil.getColumnIndexOrThrow(_cursor, "free_b");
          final int _cursorIndexOfUsagePct = CursorUtil.getColumnIndexOrThrow(_cursor, "usage_pct");
          final int _cursorIndexOfSwapTotalB = CursorUtil.getColumnIndexOrThrow(_cursor, "swap_total_b");
          final int _cursorIndexOfSwapUsedB = CursorUtil.getColumnIndexOrThrow(_cursor, "swap_used_b");
          final MemoryUsageEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final long _tmpTotalB;
            _tmpTotalB = _cursor.getLong(_cursorIndexOfTotalB);
            final long _tmpUsedB;
            _tmpUsedB = _cursor.getLong(_cursorIndexOfUsedB);
            final long _tmpFreeB;
            _tmpFreeB = _cursor.getLong(_cursorIndexOfFreeB);
            final double _tmpUsagePct;
            _tmpUsagePct = _cursor.getDouble(_cursorIndexOfUsagePct);
            final Long _tmpSwapTotalB;
            if (_cursor.isNull(_cursorIndexOfSwapTotalB)) {
              _tmpSwapTotalB = null;
            } else {
              _tmpSwapTotalB = _cursor.getLong(_cursorIndexOfSwapTotalB);
            }
            final Long _tmpSwapUsedB;
            if (_cursor.isNull(_cursorIndexOfSwapUsedB)) {
              _tmpSwapUsedB = null;
            } else {
              _tmpSwapUsedB = _cursor.getLong(_cursorIndexOfSwapUsedB);
            }
            _result = new MemoryUsageEntity(_tmpId,_tmpDeviceId,_tmpTimestampMillis,_tmpTotalB,_tmpUsedB,_tmpFreeB,_tmpUsagePct,_tmpSwapTotalB,_tmpSwapUsedB);
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
  public Object getCpuHistory(final int limit,
      final Continuation<? super List<CpuUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM cpu_usage ORDER BY timestamp_millis DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CpuUsageEntity>>() {
      @Override
      @NonNull
      public List<CpuUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfLoadPct = CursorUtil.getColumnIndexOrThrow(_cursor, "load_pct");
          final int _cursorIndexOfCores = CursorUtil.getColumnIndexOrThrow(_cursor, "cores");
          final int _cursorIndexOfPerCore = CursorUtil.getColumnIndexOrThrow(_cursor, "per_core");
          final int _cursorIndexOfSpeedGhz = CursorUtil.getColumnIndexOrThrow(_cursor, "speed_ghz");
          final int _cursorIndexOfTempC = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_c");
          final int _cursorIndexOfUsageHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "usage_history");
          final List<CpuUsageEntity> _result = new ArrayList<CpuUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CpuUsageEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final double _tmpLoadPct;
            _tmpLoadPct = _cursor.getDouble(_cursorIndexOfLoadPct);
            final int _tmpCores;
            _tmpCores = _cursor.getInt(_cursorIndexOfCores);
            final List<Double> _tmpPerCore;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfPerCore)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfPerCore);
            }
            _tmpPerCore = __converters.fromDoubleList(_tmp);
            final Double _tmpSpeedGhz;
            if (_cursor.isNull(_cursorIndexOfSpeedGhz)) {
              _tmpSpeedGhz = null;
            } else {
              _tmpSpeedGhz = _cursor.getDouble(_cursorIndexOfSpeedGhz);
            }
            final Double _tmpTempC;
            if (_cursor.isNull(_cursorIndexOfTempC)) {
              _tmpTempC = null;
            } else {
              _tmpTempC = _cursor.getDouble(_cursorIndexOfTempC);
            }
            final List<Double> _tmpUsageHistory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfUsageHistory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfUsageHistory);
            }
            _tmpUsageHistory = __converters.fromDoubleList(_tmp_1);
            _item = new CpuUsageEntity(_tmpId,_tmpDeviceId,_tmpTimestampMillis,_tmpLoadPct,_tmpCores,_tmpPerCore,_tmpSpeedGhz,_tmpTempC,_tmpUsageHistory);
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
  public Object getMemoryHistory(final int limit,
      final Continuation<? super List<MemoryUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM memory_usage ORDER BY timestamp_millis DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MemoryUsageEntity>>() {
      @Override
      @NonNull
      public List<MemoryUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfTotalB = CursorUtil.getColumnIndexOrThrow(_cursor, "total_b");
          final int _cursorIndexOfUsedB = CursorUtil.getColumnIndexOrThrow(_cursor, "used_b");
          final int _cursorIndexOfFreeB = CursorUtil.getColumnIndexOrThrow(_cursor, "free_b");
          final int _cursorIndexOfUsagePct = CursorUtil.getColumnIndexOrThrow(_cursor, "usage_pct");
          final int _cursorIndexOfSwapTotalB = CursorUtil.getColumnIndexOrThrow(_cursor, "swap_total_b");
          final int _cursorIndexOfSwapUsedB = CursorUtil.getColumnIndexOrThrow(_cursor, "swap_used_b");
          final List<MemoryUsageEntity> _result = new ArrayList<MemoryUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MemoryUsageEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final long _tmpTotalB;
            _tmpTotalB = _cursor.getLong(_cursorIndexOfTotalB);
            final long _tmpUsedB;
            _tmpUsedB = _cursor.getLong(_cursorIndexOfUsedB);
            final long _tmpFreeB;
            _tmpFreeB = _cursor.getLong(_cursorIndexOfFreeB);
            final double _tmpUsagePct;
            _tmpUsagePct = _cursor.getDouble(_cursorIndexOfUsagePct);
            final Long _tmpSwapTotalB;
            if (_cursor.isNull(_cursorIndexOfSwapTotalB)) {
              _tmpSwapTotalB = null;
            } else {
              _tmpSwapTotalB = _cursor.getLong(_cursorIndexOfSwapTotalB);
            }
            final Long _tmpSwapUsedB;
            if (_cursor.isNull(_cursorIndexOfSwapUsedB)) {
              _tmpSwapUsedB = null;
            } else {
              _tmpSwapUsedB = _cursor.getLong(_cursorIndexOfSwapUsedB);
            }
            _item = new MemoryUsageEntity(_tmpId,_tmpDeviceId,_tmpTimestampMillis,_tmpTotalB,_tmpUsedB,_tmpFreeB,_tmpUsagePct,_tmpSwapTotalB,_tmpSwapUsedB);
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
  public Object getLatestBattery(final Continuation<? super BatteryStatusEntity> $completion) {
    final String _sql = "SELECT * FROM battery_status ORDER BY timestamp_millis DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BatteryStatusEntity>() {
      @Override
      @Nullable
      public BatteryStatusEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfHasBattery = CursorUtil.getColumnIndexOrThrow(_cursor, "has_battery");
          final int _cursorIndexOfPercent = CursorUtil.getColumnIndexOrThrow(_cursor, "percent");
          final int _cursorIndexOfCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "charging");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTimeRemaining = CursorUtil.getColumnIndexOrThrow(_cursor, "time_remaining");
          final BatteryStatusEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final boolean _tmpHasBattery;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasBattery);
            _tmpHasBattery = _tmp != 0;
            final int _tmpPercent;
            _tmpPercent = _cursor.getInt(_cursorIndexOfPercent);
            final boolean _tmpCharging;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfCharging);
            _tmpCharging = _tmp_1 != 0;
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final Long _tmpTimeRemaining;
            if (_cursor.isNull(_cursorIndexOfTimeRemaining)) {
              _tmpTimeRemaining = null;
            } else {
              _tmpTimeRemaining = _cursor.getLong(_cursorIndexOfTimeRemaining);
            }
            _result = new BatteryStatusEntity(_tmpId,_tmpDeviceId,_tmpTimestampMillis,_tmpHasBattery,_tmpPercent,_tmpCharging,_tmpStatus,_tmpTimeRemaining);
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
  public Object getLatestStorage(final Continuation<? super List<StorageUsageEntity>> $completion) {
    final String _sql = "SELECT * FROM storage_usage WHERE timestamp_millis = (SELECT MAX(timestamp_millis) FROM storage_usage) ORDER BY mount ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StorageUsageEntity>>() {
      @Override
      @NonNull
      public List<StorageUsageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfFilesystem = CursorUtil.getColumnIndexOrThrow(_cursor, "filesystem");
          final int _cursorIndexOfMount = CursorUtil.getColumnIndexOrThrow(_cursor, "mount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTotalB = CursorUtil.getColumnIndexOrThrow(_cursor, "total_b");
          final int _cursorIndexOfUsedB = CursorUtil.getColumnIndexOrThrow(_cursor, "used_b");
          final int _cursorIndexOfFreeB = CursorUtil.getColumnIndexOrThrow(_cursor, "free_b");
          final int _cursorIndexOfUsagePct = CursorUtil.getColumnIndexOrThrow(_cursor, "usage_pct");
          final List<StorageUsageEntity> _result = new ArrayList<StorageUsageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StorageUsageEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final String _tmpFilesystem;
            if (_cursor.isNull(_cursorIndexOfFilesystem)) {
              _tmpFilesystem = null;
            } else {
              _tmpFilesystem = _cursor.getString(_cursorIndexOfFilesystem);
            }
            final String _tmpMount;
            if (_cursor.isNull(_cursorIndexOfMount)) {
              _tmpMount = null;
            } else {
              _tmpMount = _cursor.getString(_cursorIndexOfMount);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpTotalB;
            _tmpTotalB = _cursor.getLong(_cursorIndexOfTotalB);
            final long _tmpUsedB;
            _tmpUsedB = _cursor.getLong(_cursorIndexOfUsedB);
            final long _tmpFreeB;
            _tmpFreeB = _cursor.getLong(_cursorIndexOfFreeB);
            final double _tmpUsagePct;
            _tmpUsagePct = _cursor.getDouble(_cursorIndexOfUsagePct);
            _item = new StorageUsageEntity(_tmpId,_tmpDeviceId,_tmpTimestampMillis,_tmpFilesystem,_tmpMount,_tmpType,_tmpTotalB,_tmpUsedB,_tmpFreeB,_tmpUsagePct);
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
  public Object getLatestDeviceInfo(final Continuation<? super DeviceInfoEntity> $completion) {
    final String _sql = "SELECT * FROM device_info ORDER BY timestamp_millis DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DeviceInfoEntity>() {
      @Override
      @Nullable
      public DeviceInfoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfKernel = CursorUtil.getColumnIndexOrThrow(_cursor, "kernel");
          final int _cursorIndexOfOsName = CursorUtil.getColumnIndexOrThrow(_cursor, "os_name");
          final int _cursorIndexOfOsVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "os_version");
          final int _cursorIndexOfSecurityPatchLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "security_patch_level");
          final int _cursorIndexOfOsBuild = CursorUtil.getColumnIndexOrThrow(_cursor, "os_build");
          final int _cursorIndexOfUptimeSec = CursorUtil.getColumnIndexOrThrow(_cursor, "uptime_sec");
          final DeviceInfoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
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
            final String _tmpKernel;
            if (_cursor.isNull(_cursorIndexOfKernel)) {
              _tmpKernel = null;
            } else {
              _tmpKernel = _cursor.getString(_cursorIndexOfKernel);
            }
            final String _tmpOsName;
            if (_cursor.isNull(_cursorIndexOfOsName)) {
              _tmpOsName = null;
            } else {
              _tmpOsName = _cursor.getString(_cursorIndexOfOsName);
            }
            final String _tmpOsVersion;
            if (_cursor.isNull(_cursorIndexOfOsVersion)) {
              _tmpOsVersion = null;
            } else {
              _tmpOsVersion = _cursor.getString(_cursorIndexOfOsVersion);
            }
            final String _tmpSecurityPatchLevel;
            if (_cursor.isNull(_cursorIndexOfSecurityPatchLevel)) {
              _tmpSecurityPatchLevel = null;
            } else {
              _tmpSecurityPatchLevel = _cursor.getString(_cursorIndexOfSecurityPatchLevel);
            }
            final String _tmpOsBuild;
            if (_cursor.isNull(_cursorIndexOfOsBuild)) {
              _tmpOsBuild = null;
            } else {
              _tmpOsBuild = _cursor.getString(_cursorIndexOfOsBuild);
            }
            final long _tmpUptimeSec;
            _tmpUptimeSec = _cursor.getLong(_cursorIndexOfUptimeSec);
            _result = new DeviceInfoEntity(_tmpId,_tmpDeviceId,_tmpTimestampMillis,_tmpManufacturer,_tmpModel,_tmpKernel,_tmpOsName,_tmpOsVersion,_tmpSecurityPatchLevel,_tmpOsBuild,_tmpUptimeSec);
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
