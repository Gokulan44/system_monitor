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
import com.soc.agent.database.Converters;
import com.soc.agent.database.entity.NetworkLogEntity;
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
public final class NetworkDao_Impl implements NetworkDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NetworkLogEntity> __insertionAdapterOfNetworkLogEntity;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteOld;

  public NetworkDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNetworkLogEntity = new EntityInsertionAdapter<NetworkLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `network_logs` (`id`,`device_id`,`kind`,`timestamp_millis`,`iface`,`ip4`,`ip6`,`mac`,`state`,`default_gw`,`dns_servers`,`wifi_ssid`,`wifi_rssi`,`wifi_link_speed`,`vpn_active`,`rx_bytes`,`tx_bytes`,`rx_sec`,`tx_sec`,`url`,`verdict`,`score`,`reasons`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NetworkLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeviceId());
        if (entity.getKind() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getKind());
        }
        statement.bindLong(4, entity.getTimestampMillis());
        if (entity.getIface() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getIface());
        }
        if (entity.getIp4() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getIp4());
        }
        if (entity.getIp6() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getIp6());
        }
        if (entity.getMac() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getMac());
        }
        if (entity.getState() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getState());
        }
        if (entity.getDefaultGw() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDefaultGw());
        }
        final String _tmp = __converters.toStringList(entity.getDnsServers());
        if (_tmp == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp);
        }
        if (entity.getWifiSsid() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getWifiSsid());
        }
        if (entity.getWifiRssi() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getWifiRssi());
        }
        if (entity.getWifiLinkSpeed() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getWifiLinkSpeed());
        }
        final int _tmp_1 = entity.getVpnActive() ? 1 : 0;
        statement.bindLong(15, _tmp_1);
        statement.bindLong(16, entity.getRxBytes());
        statement.bindLong(17, entity.getTxBytes());
        statement.bindDouble(18, entity.getRxSec());
        statement.bindDouble(19, entity.getTxSec());
        if (entity.getUrl() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getUrl());
        }
        if (entity.getVerdict() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getVerdict());
        }
        if (entity.getScore() == null) {
          statement.bindNull(22);
        } else {
          statement.bindLong(22, entity.getScore());
        }
        final String _tmp_2 = __converters.toStringList(entity.getReasons());
        if (_tmp_2 == null) {
          statement.bindNull(23);
        } else {
          statement.bindString(23, _tmp_2);
        }
      }
    };
    this.__preparedStmtOfDeleteOld = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM network_logs WHERE timestamp_millis < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertNetworkLogs(final List<NetworkLogEntity> logs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNetworkLogEntity.insert(logs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOld(final long cutoffMillis, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOld.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffMillis);
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
          __preparedStmtOfDeleteOld.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestNetwork(final int limit,
      final Continuation<? super List<NetworkLogEntity>> $completion) {
    final String _sql = "SELECT * FROM network_logs WHERE kind = 'network' ORDER BY timestamp_millis DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NetworkLogEntity>>() {
      @Override
      @NonNull
      public List<NetworkLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfKind = CursorUtil.getColumnIndexOrThrow(_cursor, "kind");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfIface = CursorUtil.getColumnIndexOrThrow(_cursor, "iface");
          final int _cursorIndexOfIp4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ip4");
          final int _cursorIndexOfIp6 = CursorUtil.getColumnIndexOrThrow(_cursor, "ip6");
          final int _cursorIndexOfMac = CursorUtil.getColumnIndexOrThrow(_cursor, "mac");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfDefaultGw = CursorUtil.getColumnIndexOrThrow(_cursor, "default_gw");
          final int _cursorIndexOfDnsServers = CursorUtil.getColumnIndexOrThrow(_cursor, "dns_servers");
          final int _cursorIndexOfWifiSsid = CursorUtil.getColumnIndexOrThrow(_cursor, "wifi_ssid");
          final int _cursorIndexOfWifiRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "wifi_rssi");
          final int _cursorIndexOfWifiLinkSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "wifi_link_speed");
          final int _cursorIndexOfVpnActive = CursorUtil.getColumnIndexOrThrow(_cursor, "vpn_active");
          final int _cursorIndexOfRxBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "rx_bytes");
          final int _cursorIndexOfTxBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_bytes");
          final int _cursorIndexOfRxSec = CursorUtil.getColumnIndexOrThrow(_cursor, "rx_sec");
          final int _cursorIndexOfTxSec = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_sec");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfVerdict = CursorUtil.getColumnIndexOrThrow(_cursor, "verdict");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfReasons = CursorUtil.getColumnIndexOrThrow(_cursor, "reasons");
          final List<NetworkLogEntity> _result = new ArrayList<NetworkLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NetworkLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpKind;
            if (_cursor.isNull(_cursorIndexOfKind)) {
              _tmpKind = null;
            } else {
              _tmpKind = _cursor.getString(_cursorIndexOfKind);
            }
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final String _tmpIface;
            if (_cursor.isNull(_cursorIndexOfIface)) {
              _tmpIface = null;
            } else {
              _tmpIface = _cursor.getString(_cursorIndexOfIface);
            }
            final String _tmpIp4;
            if (_cursor.isNull(_cursorIndexOfIp4)) {
              _tmpIp4 = null;
            } else {
              _tmpIp4 = _cursor.getString(_cursorIndexOfIp4);
            }
            final String _tmpIp6;
            if (_cursor.isNull(_cursorIndexOfIp6)) {
              _tmpIp6 = null;
            } else {
              _tmpIp6 = _cursor.getString(_cursorIndexOfIp6);
            }
            final String _tmpMac;
            if (_cursor.isNull(_cursorIndexOfMac)) {
              _tmpMac = null;
            } else {
              _tmpMac = _cursor.getString(_cursorIndexOfMac);
            }
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpDefaultGw;
            if (_cursor.isNull(_cursorIndexOfDefaultGw)) {
              _tmpDefaultGw = null;
            } else {
              _tmpDefaultGw = _cursor.getString(_cursorIndexOfDefaultGw);
            }
            final List<String> _tmpDnsServers;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDnsServers)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDnsServers);
            }
            _tmpDnsServers = __converters.fromStringList(_tmp);
            final String _tmpWifiSsid;
            if (_cursor.isNull(_cursorIndexOfWifiSsid)) {
              _tmpWifiSsid = null;
            } else {
              _tmpWifiSsid = _cursor.getString(_cursorIndexOfWifiSsid);
            }
            final Integer _tmpWifiRssi;
            if (_cursor.isNull(_cursorIndexOfWifiRssi)) {
              _tmpWifiRssi = null;
            } else {
              _tmpWifiRssi = _cursor.getInt(_cursorIndexOfWifiRssi);
            }
            final Integer _tmpWifiLinkSpeed;
            if (_cursor.isNull(_cursorIndexOfWifiLinkSpeed)) {
              _tmpWifiLinkSpeed = null;
            } else {
              _tmpWifiLinkSpeed = _cursor.getInt(_cursorIndexOfWifiLinkSpeed);
            }
            final boolean _tmpVpnActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfVpnActive);
            _tmpVpnActive = _tmp_1 != 0;
            final long _tmpRxBytes;
            _tmpRxBytes = _cursor.getLong(_cursorIndexOfRxBytes);
            final long _tmpTxBytes;
            _tmpTxBytes = _cursor.getLong(_cursorIndexOfTxBytes);
            final double _tmpRxSec;
            _tmpRxSec = _cursor.getDouble(_cursorIndexOfRxSec);
            final double _tmpTxSec;
            _tmpTxSec = _cursor.getDouble(_cursorIndexOfTxSec);
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpVerdict;
            if (_cursor.isNull(_cursorIndexOfVerdict)) {
              _tmpVerdict = null;
            } else {
              _tmpVerdict = _cursor.getString(_cursorIndexOfVerdict);
            }
            final Integer _tmpScore;
            if (_cursor.isNull(_cursorIndexOfScore)) {
              _tmpScore = null;
            } else {
              _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            }
            final List<String> _tmpReasons;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfReasons)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfReasons);
            }
            _tmpReasons = __converters.fromStringList(_tmp_2);
            _item = new NetworkLogEntity(_tmpId,_tmpDeviceId,_tmpKind,_tmpTimestampMillis,_tmpIface,_tmpIp4,_tmpIp6,_tmpMac,_tmpState,_tmpDefaultGw,_tmpDnsServers,_tmpWifiSsid,_tmpWifiRssi,_tmpWifiLinkSpeed,_tmpVpnActive,_tmpRxBytes,_tmpTxBytes,_tmpRxSec,_tmpTxSec,_tmpUrl,_tmpVerdict,_tmpScore,_tmpReasons);
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
  public Object getLatestPhishing(final int limit,
      final Continuation<? super List<NetworkLogEntity>> $completion) {
    final String _sql = "SELECT * FROM network_logs WHERE kind = 'phishing' ORDER BY timestamp_millis DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NetworkLogEntity>>() {
      @Override
      @NonNull
      public List<NetworkLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "device_id");
          final int _cursorIndexOfKind = CursorUtil.getColumnIndexOrThrow(_cursor, "kind");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp_millis");
          final int _cursorIndexOfIface = CursorUtil.getColumnIndexOrThrow(_cursor, "iface");
          final int _cursorIndexOfIp4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ip4");
          final int _cursorIndexOfIp6 = CursorUtil.getColumnIndexOrThrow(_cursor, "ip6");
          final int _cursorIndexOfMac = CursorUtil.getColumnIndexOrThrow(_cursor, "mac");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfDefaultGw = CursorUtil.getColumnIndexOrThrow(_cursor, "default_gw");
          final int _cursorIndexOfDnsServers = CursorUtil.getColumnIndexOrThrow(_cursor, "dns_servers");
          final int _cursorIndexOfWifiSsid = CursorUtil.getColumnIndexOrThrow(_cursor, "wifi_ssid");
          final int _cursorIndexOfWifiRssi = CursorUtil.getColumnIndexOrThrow(_cursor, "wifi_rssi");
          final int _cursorIndexOfWifiLinkSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "wifi_link_speed");
          final int _cursorIndexOfVpnActive = CursorUtil.getColumnIndexOrThrow(_cursor, "vpn_active");
          final int _cursorIndexOfRxBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "rx_bytes");
          final int _cursorIndexOfTxBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_bytes");
          final int _cursorIndexOfRxSec = CursorUtil.getColumnIndexOrThrow(_cursor, "rx_sec");
          final int _cursorIndexOfTxSec = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_sec");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfVerdict = CursorUtil.getColumnIndexOrThrow(_cursor, "verdict");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfReasons = CursorUtil.getColumnIndexOrThrow(_cursor, "reasons");
          final List<NetworkLogEntity> _result = new ArrayList<NetworkLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NetworkLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeviceId;
            _tmpDeviceId = _cursor.getLong(_cursorIndexOfDeviceId);
            final String _tmpKind;
            if (_cursor.isNull(_cursorIndexOfKind)) {
              _tmpKind = null;
            } else {
              _tmpKind = _cursor.getString(_cursorIndexOfKind);
            }
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final String _tmpIface;
            if (_cursor.isNull(_cursorIndexOfIface)) {
              _tmpIface = null;
            } else {
              _tmpIface = _cursor.getString(_cursorIndexOfIface);
            }
            final String _tmpIp4;
            if (_cursor.isNull(_cursorIndexOfIp4)) {
              _tmpIp4 = null;
            } else {
              _tmpIp4 = _cursor.getString(_cursorIndexOfIp4);
            }
            final String _tmpIp6;
            if (_cursor.isNull(_cursorIndexOfIp6)) {
              _tmpIp6 = null;
            } else {
              _tmpIp6 = _cursor.getString(_cursorIndexOfIp6);
            }
            final String _tmpMac;
            if (_cursor.isNull(_cursorIndexOfMac)) {
              _tmpMac = null;
            } else {
              _tmpMac = _cursor.getString(_cursorIndexOfMac);
            }
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpDefaultGw;
            if (_cursor.isNull(_cursorIndexOfDefaultGw)) {
              _tmpDefaultGw = null;
            } else {
              _tmpDefaultGw = _cursor.getString(_cursorIndexOfDefaultGw);
            }
            final List<String> _tmpDnsServers;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDnsServers)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDnsServers);
            }
            _tmpDnsServers = __converters.fromStringList(_tmp);
            final String _tmpWifiSsid;
            if (_cursor.isNull(_cursorIndexOfWifiSsid)) {
              _tmpWifiSsid = null;
            } else {
              _tmpWifiSsid = _cursor.getString(_cursorIndexOfWifiSsid);
            }
            final Integer _tmpWifiRssi;
            if (_cursor.isNull(_cursorIndexOfWifiRssi)) {
              _tmpWifiRssi = null;
            } else {
              _tmpWifiRssi = _cursor.getInt(_cursorIndexOfWifiRssi);
            }
            final Integer _tmpWifiLinkSpeed;
            if (_cursor.isNull(_cursorIndexOfWifiLinkSpeed)) {
              _tmpWifiLinkSpeed = null;
            } else {
              _tmpWifiLinkSpeed = _cursor.getInt(_cursorIndexOfWifiLinkSpeed);
            }
            final boolean _tmpVpnActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfVpnActive);
            _tmpVpnActive = _tmp_1 != 0;
            final long _tmpRxBytes;
            _tmpRxBytes = _cursor.getLong(_cursorIndexOfRxBytes);
            final long _tmpTxBytes;
            _tmpTxBytes = _cursor.getLong(_cursorIndexOfTxBytes);
            final double _tmpRxSec;
            _tmpRxSec = _cursor.getDouble(_cursorIndexOfRxSec);
            final double _tmpTxSec;
            _tmpTxSec = _cursor.getDouble(_cursorIndexOfTxSec);
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpVerdict;
            if (_cursor.isNull(_cursorIndexOfVerdict)) {
              _tmpVerdict = null;
            } else {
              _tmpVerdict = _cursor.getString(_cursorIndexOfVerdict);
            }
            final Integer _tmpScore;
            if (_cursor.isNull(_cursorIndexOfScore)) {
              _tmpScore = null;
            } else {
              _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            }
            final List<String> _tmpReasons;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfReasons)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfReasons);
            }
            _tmpReasons = __converters.fromStringList(_tmp_2);
            _item = new NetworkLogEntity(_tmpId,_tmpDeviceId,_tmpKind,_tmpTimestampMillis,_tmpIface,_tmpIp4,_tmpIp6,_tmpMac,_tmpState,_tmpDefaultGw,_tmpDnsServers,_tmpWifiSsid,_tmpWifiRssi,_tmpWifiLinkSpeed,_tmpVpnActive,_tmpRxBytes,_tmpTxBytes,_tmpRxSec,_tmpTxSec,_tmpUrl,_tmpVerdict,_tmpScore,_tmpReasons);
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
