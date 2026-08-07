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
import com.soc.agent.database.entity.SecuritySettingsEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SecuritySettingsDao_LockDatabase_Impl implements SecuritySettingsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SecuritySettingsEntity> __insertionAdapterOfSecuritySettingsEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearSettings;

  public SecuritySettingsDao_LockDatabase_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSecuritySettingsEntity = new EntityInsertionAdapter<SecuritySettingsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `security_settings` (`id`,`intruder_selfie_enabled`,`intruder_selfie_threshold`,`fake_crash_enabled`,`fake_crash_message`,`breakin_alert_enabled`,`auto_lock_screen_off`,`require_unlock_on_background`,`vibrate_on_failed`,`sound_on_failed`,`lock_delay_ms`,`updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SecuritySettingsEntity entity) {
        statement.bindLong(1, entity.getId());
        final int _tmp = entity.getIntruderSelfieEnabled() ? 1 : 0;
        statement.bindLong(2, _tmp);
        statement.bindLong(3, entity.getIntruderSelfieThreshold());
        final int _tmp_1 = entity.getFakeCrashEnabled() ? 1 : 0;
        statement.bindLong(4, _tmp_1);
        if (entity.getFakeCrashMessage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFakeCrashMessage());
        }
        final int _tmp_2 = entity.getBreakinAlertEnabled() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        final int _tmp_3 = entity.getAutoLockScreenOff() ? 1 : 0;
        statement.bindLong(7, _tmp_3);
        final int _tmp_4 = entity.getRequireUnlockOnBackground() ? 1 : 0;
        statement.bindLong(8, _tmp_4);
        final int _tmp_5 = entity.getVibrateOnFailed() ? 1 : 0;
        statement.bindLong(9, _tmp_5);
        final int _tmp_6 = entity.getSoundOnFailed() ? 1 : 0;
        statement.bindLong(10, _tmp_6);
        statement.bindLong(11, entity.getLockDelayMs());
        statement.bindLong(12, entity.getUpdatedAt());
      }
    };
    this.__preparedStmtOfClearSettings = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM security_settings WHERE id = 1";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final SecuritySettingsEntity settings,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSecuritySettingsEntity.insert(settings);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearSettings(final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearSettings.acquire();
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearSettings.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getSettings(final Continuation<? super SecuritySettingsEntity> $completion) {
    final String _sql = "SELECT * FROM security_settings WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SecuritySettingsEntity>() {
      @Override
      @Nullable
      public SecuritySettingsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIntruderSelfieEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "intruder_selfie_enabled");
          final int _cursorIndexOfIntruderSelfieThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "intruder_selfie_threshold");
          final int _cursorIndexOfFakeCrashEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "fake_crash_enabled");
          final int _cursorIndexOfFakeCrashMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "fake_crash_message");
          final int _cursorIndexOfBreakinAlertEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "breakin_alert_enabled");
          final int _cursorIndexOfAutoLockScreenOff = CursorUtil.getColumnIndexOrThrow(_cursor, "auto_lock_screen_off");
          final int _cursorIndexOfRequireUnlockOnBackground = CursorUtil.getColumnIndexOrThrow(_cursor, "require_unlock_on_background");
          final int _cursorIndexOfVibrateOnFailed = CursorUtil.getColumnIndexOrThrow(_cursor, "vibrate_on_failed");
          final int _cursorIndexOfSoundOnFailed = CursorUtil.getColumnIndexOrThrow(_cursor, "sound_on_failed");
          final int _cursorIndexOfLockDelayMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lock_delay_ms");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final SecuritySettingsEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final boolean _tmpIntruderSelfieEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIntruderSelfieEnabled);
            _tmpIntruderSelfieEnabled = _tmp != 0;
            final int _tmpIntruderSelfieThreshold;
            _tmpIntruderSelfieThreshold = _cursor.getInt(_cursorIndexOfIntruderSelfieThreshold);
            final boolean _tmpFakeCrashEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFakeCrashEnabled);
            _tmpFakeCrashEnabled = _tmp_1 != 0;
            final String _tmpFakeCrashMessage;
            if (_cursor.isNull(_cursorIndexOfFakeCrashMessage)) {
              _tmpFakeCrashMessage = null;
            } else {
              _tmpFakeCrashMessage = _cursor.getString(_cursorIndexOfFakeCrashMessage);
            }
            final boolean _tmpBreakinAlertEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfBreakinAlertEnabled);
            _tmpBreakinAlertEnabled = _tmp_2 != 0;
            final boolean _tmpAutoLockScreenOff;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfAutoLockScreenOff);
            _tmpAutoLockScreenOff = _tmp_3 != 0;
            final boolean _tmpRequireUnlockOnBackground;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfRequireUnlockOnBackground);
            _tmpRequireUnlockOnBackground = _tmp_4 != 0;
            final boolean _tmpVibrateOnFailed;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfVibrateOnFailed);
            _tmpVibrateOnFailed = _tmp_5 != 0;
            final boolean _tmpSoundOnFailed;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfSoundOnFailed);
            _tmpSoundOnFailed = _tmp_6 != 0;
            final long _tmpLockDelayMs;
            _tmpLockDelayMs = _cursor.getLong(_cursorIndexOfLockDelayMs);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new SecuritySettingsEntity(_tmpId,_tmpIntruderSelfieEnabled,_tmpIntruderSelfieThreshold,_tmpFakeCrashEnabled,_tmpFakeCrashMessage,_tmpBreakinAlertEnabled,_tmpAutoLockScreenOff,_tmpRequireUnlockOnBackground,_tmpVibrateOnFailed,_tmpSoundOnFailed,_tmpLockDelayMs,_tmpUpdatedAt);
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
