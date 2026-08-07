package com.soc.agent.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
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
import com.soc.agent.database.Converters;
import com.soc.agent.database.entity.IocEntity;
import com.soc.agent.database.entity.PolicyEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PolicyDao_Impl implements PolicyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IocEntity> __insertionAdapterOfIocEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearPolicies;

  private final SharedSQLiteStatement __preparedStmtOfClearIocs;

  private final EntityUpsertionAdapter<PolicyEntity> __upsertionAdapterOfPolicyEntity;

  private final Converters __converters = new Converters();

  public PolicyDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIocEntity = new EntityInsertionAdapter<IocEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `iocs` (`id`,`value`,`type`,`threat`,`source`,`severity`,`active`,`added_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final IocEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getValue() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getValue());
        }
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getType());
        }
        if (entity.getThreat() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getThreat());
        }
        if (entity.getSource() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getSource());
        }
        if (entity.getSeverity() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSeverity());
        }
        final int _tmp = entity.getActive() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getAddedAt());
      }
    };
    this.__preparedStmtOfClearPolicies = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM policies";
        return _query;
      }
    };
    this.__preparedStmtOfClearIocs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM iocs";
        return _query;
      }
    };
    this.__upsertionAdapterOfPolicyEntity = new EntityUpsertionAdapter<PolicyEntity>(new EntityInsertionAdapter<PolicyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `policies` (`id`,`name`,`policy_type`,`rules`,`enabled`,`synced_at`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PolicyEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getPolicyType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPolicyType());
        }
        final String _tmp = __converters.toRulesJson(entity.getRules());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final int _tmp_1 = entity.getEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getSyncedAt());
      }
    }, new EntityDeletionOrUpdateAdapter<PolicyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `policies` SET `id` = ?,`name` = ?,`policy_type` = ?,`rules` = ?,`enabled` = ?,`synced_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PolicyEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getPolicyType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPolicyType());
        }
        final String _tmp = __converters.toRulesJson(entity.getRules());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final int _tmp_1 = entity.getEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getSyncedAt());
        statement.bindLong(7, entity.getId());
      }
    });
  }

  @Override
  public Object upsertIocs(final List<IocEntity> iocs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIocEntity.insert(iocs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearPolicies(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearPolicies.acquire();
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
          __preparedStmtOfClearPolicies.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearIocs(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearIocs.acquire();
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
          __preparedStmtOfClearIocs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertPolicies(final List<PolicyEntity> policies,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfPolicyEntity.upsert(policies);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPolicies(final Continuation<? super List<PolicyEntity>> $completion) {
    final String _sql = "SELECT * FROM policies ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PolicyEntity>>() {
      @Override
      @NonNull
      public List<PolicyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPolicyType = CursorUtil.getColumnIndexOrThrow(_cursor, "policy_type");
          final int _cursorIndexOfRules = CursorUtil.getColumnIndexOrThrow(_cursor, "rules");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "synced_at");
          final List<PolicyEntity> _result = new ArrayList<PolicyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PolicyEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPolicyType;
            if (_cursor.isNull(_cursorIndexOfPolicyType)) {
              _tmpPolicyType = null;
            } else {
              _tmpPolicyType = _cursor.getString(_cursorIndexOfPolicyType);
            }
            final Map<String, Object> _tmpRules;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRules)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRules);
            }
            _tmpRules = __converters.fromRulesJson(_tmp);
            final boolean _tmpEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp_1 != 0;
            final long _tmpSyncedAt;
            _tmpSyncedAt = _cursor.getLong(_cursorIndexOfSyncedAt);
            _item = new PolicyEntity(_tmpId,_tmpName,_tmpPolicyType,_tmpRules,_tmpEnabled,_tmpSyncedAt);
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
  public Object getIocs(final Continuation<? super List<IocEntity>> $completion) {
    final String _sql = "SELECT * FROM iocs WHERE active = 1 ORDER BY type ASC, value ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<IocEntity>>() {
      @Override
      @NonNull
      public List<IocEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfThreat = CursorUtil.getColumnIndexOrThrow(_cursor, "threat");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "added_at");
          final List<IocEntity> _result = new ArrayList<IocEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IocEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpValue;
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _tmpValue = null;
            } else {
              _tmpValue = _cursor.getString(_cursorIndexOfValue);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpThreat;
            if (_cursor.isNull(_cursorIndexOfThreat)) {
              _tmpThreat = null;
            } else {
              _tmpThreat = _cursor.getString(_cursorIndexOfThreat);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpSeverity;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmpSeverity = null;
            } else {
              _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            }
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new IocEntity(_tmpId,_tmpValue,_tmpType,_tmpThreat,_tmpSource,_tmpSeverity,_tmpActive,_tmpAddedAt);
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
  public Object getBlocklist(final Continuation<? super List<IocEntity>> $completion) {
    final String _sql = "SELECT * FROM iocs WHERE active = 1 ORDER BY type ASC, value ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<IocEntity>>() {
      @Override
      @NonNull
      public List<IocEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfThreat = CursorUtil.getColumnIndexOrThrow(_cursor, "threat");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "added_at");
          final List<IocEntity> _result = new ArrayList<IocEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IocEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpValue;
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _tmpValue = null;
            } else {
              _tmpValue = _cursor.getString(_cursorIndexOfValue);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpThreat;
            if (_cursor.isNull(_cursorIndexOfThreat)) {
              _tmpThreat = null;
            } else {
              _tmpThreat = _cursor.getString(_cursorIndexOfThreat);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpSeverity;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmpSeverity = null;
            } else {
              _tmpSeverity = _cursor.getString(_cursorIndexOfSeverity);
            }
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new IocEntity(_tmpId,_tmpValue,_tmpType,_tmpThreat,_tmpSource,_tmpSeverity,_tmpActive,_tmpAddedAt);
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
  public Object getBlockedDomains(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT value FROM iocs WHERE type = 'domain' AND active = 1 ORDER BY value ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
  public Object getBlockedUrls(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT value FROM iocs WHERE type = 'url' AND active = 1 ORDER BY value ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
  public Object getBlockedHashes(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT value FROM iocs WHERE type = 'hash' AND active = 1 ORDER BY value ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
