package com.soc.agent.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.soc.agent.database.dao.BackupDao;
import com.soc.agent.database.dao.BackupDao_LockDatabase_Impl;
import com.soc.agent.database.dao.FailedAttemptDao;
import com.soc.agent.database.dao.FailedAttemptDao_LockDatabase_Impl;
import com.soc.agent.database.dao.IntruderSelfieDao;
import com.soc.agent.database.dao.IntruderSelfieDao_LockDatabase_Impl;
import com.soc.agent.database.dao.LockedAppDao;
import com.soc.agent.database.dao.LockedAppDao_LockDatabase_Impl;
import com.soc.agent.database.dao.SecuritySettingsDao;
import com.soc.agent.database.dao.SecuritySettingsDao_LockDatabase_Impl;
import com.soc.agent.database.dao.UnlockHistoryDao;
import com.soc.agent.database.dao.UnlockHistoryDao_LockDatabase_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LockDatabase_Impl extends LockDatabase {
  private volatile LockedAppDao _lockedAppDao;

  private volatile UnlockHistoryDao _unlockHistoryDao;

  private volatile FailedAttemptDao _failedAttemptDao;

  private volatile IntruderSelfieDao _intruderSelfieDao;

  private volatile SecuritySettingsDao _securitySettingsDao;

  private volatile BackupDao _backupDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `locked_apps` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `name` TEXT NOT NULL, `lock_method` TEXT NOT NULL, `added_at` INTEGER NOT NULL, `enabled` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_locked_apps_package_name` ON `locked_apps` (`package_name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_locked_apps_lock_method` ON `locked_apps` (`lock_method`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `unlock_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `gate_method` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `auto_unlock` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_unlock_history_package_name_timestamp` ON `unlock_history` (`package_name`, `timestamp`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_unlock_history_timestamp` ON `unlock_history` (`timestamp`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `failed_attempts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `gate_method` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `failure_reason` TEXT NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_failed_attempts_package_name_timestamp` ON `failed_attempts` (`package_name`, `timestamp`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_failed_attempts_timestamp` ON `failed_attempts` (`timestamp`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `intruder_selfies` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `gate_method` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `image_path` TEXT NOT NULL, `failure_reason` TEXT NOT NULL, `attempt_number` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_intruder_selfies_package_name_timestamp` ON `intruder_selfies` (`package_name`, `timestamp`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_intruder_selfies_timestamp` ON `intruder_selfies` (`timestamp`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `security_settings` (`id` INTEGER NOT NULL, `intruder_selfie_enabled` INTEGER NOT NULL, `intruder_selfie_threshold` INTEGER NOT NULL, `fake_crash_enabled` INTEGER NOT NULL, `fake_crash_message` TEXT NOT NULL, `breakin_alert_enabled` INTEGER NOT NULL, `auto_lock_screen_off` INTEGER NOT NULL, `require_unlock_on_background` INTEGER NOT NULL, `vibrate_on_failed` INTEGER NOT NULL, `sound_on_failed` INTEGER NOT NULL, `lock_delay_ms` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `backups` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `payload` TEXT NOT NULL, `created_at` INTEGER NOT NULL, `version` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '457771abdda0cde20a6d46ef24b147fb')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `locked_apps`");
        db.execSQL("DROP TABLE IF EXISTS `unlock_history`");
        db.execSQL("DROP TABLE IF EXISTS `failed_attempts`");
        db.execSQL("DROP TABLE IF EXISTS `intruder_selfies`");
        db.execSQL("DROP TABLE IF EXISTS `security_settings`");
        db.execSQL("DROP TABLE IF EXISTS `backups`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsLockedApps = new HashMap<String, TableInfo.Column>(6);
        _columnsLockedApps.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockedApps.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockedApps.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockedApps.put("lock_method", new TableInfo.Column("lock_method", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockedApps.put("added_at", new TableInfo.Column("added_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockedApps.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLockedApps = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLockedApps = new HashSet<TableInfo.Index>(2);
        _indicesLockedApps.add(new TableInfo.Index("index_locked_apps_package_name", true, Arrays.asList("package_name"), Arrays.asList("ASC")));
        _indicesLockedApps.add(new TableInfo.Index("index_locked_apps_lock_method", false, Arrays.asList("lock_method"), Arrays.asList("ASC")));
        final TableInfo _infoLockedApps = new TableInfo("locked_apps", _columnsLockedApps, _foreignKeysLockedApps, _indicesLockedApps);
        final TableInfo _existingLockedApps = TableInfo.read(db, "locked_apps");
        if (!_infoLockedApps.equals(_existingLockedApps)) {
          return new RoomOpenHelper.ValidationResult(false, "locked_apps(com.soc.agent.database.entity.LockedAppEntity).\n"
                  + " Expected:\n" + _infoLockedApps + "\n"
                  + " Found:\n" + _existingLockedApps);
        }
        final HashMap<String, TableInfo.Column> _columnsUnlockHistory = new HashMap<String, TableInfo.Column>(6);
        _columnsUnlockHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUnlockHistory.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUnlockHistory.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUnlockHistory.put("gate_method", new TableInfo.Column("gate_method", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUnlockHistory.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUnlockHistory.put("auto_unlock", new TableInfo.Column("auto_unlock", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUnlockHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUnlockHistory = new HashSet<TableInfo.Index>(2);
        _indicesUnlockHistory.add(new TableInfo.Index("index_unlock_history_package_name_timestamp", false, Arrays.asList("package_name", "timestamp"), Arrays.asList("ASC", "ASC")));
        _indicesUnlockHistory.add(new TableInfo.Index("index_unlock_history_timestamp", false, Arrays.asList("timestamp"), Arrays.asList("ASC")));
        final TableInfo _infoUnlockHistory = new TableInfo("unlock_history", _columnsUnlockHistory, _foreignKeysUnlockHistory, _indicesUnlockHistory);
        final TableInfo _existingUnlockHistory = TableInfo.read(db, "unlock_history");
        if (!_infoUnlockHistory.equals(_existingUnlockHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "unlock_history(com.soc.agent.database.entity.UnlockHistoryEntity).\n"
                  + " Expected:\n" + _infoUnlockHistory + "\n"
                  + " Found:\n" + _existingUnlockHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsFailedAttempts = new HashMap<String, TableInfo.Column>(6);
        _columnsFailedAttempts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFailedAttempts.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFailedAttempts.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFailedAttempts.put("gate_method", new TableInfo.Column("gate_method", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFailedAttempts.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFailedAttempts.put("failure_reason", new TableInfo.Column("failure_reason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFailedAttempts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFailedAttempts = new HashSet<TableInfo.Index>(2);
        _indicesFailedAttempts.add(new TableInfo.Index("index_failed_attempts_package_name_timestamp", false, Arrays.asList("package_name", "timestamp"), Arrays.asList("ASC", "ASC")));
        _indicesFailedAttempts.add(new TableInfo.Index("index_failed_attempts_timestamp", false, Arrays.asList("timestamp"), Arrays.asList("ASC")));
        final TableInfo _infoFailedAttempts = new TableInfo("failed_attempts", _columnsFailedAttempts, _foreignKeysFailedAttempts, _indicesFailedAttempts);
        final TableInfo _existingFailedAttempts = TableInfo.read(db, "failed_attempts");
        if (!_infoFailedAttempts.equals(_existingFailedAttempts)) {
          return new RoomOpenHelper.ValidationResult(false, "failed_attempts(com.soc.agent.database.entity.FailedAttemptEntity).\n"
                  + " Expected:\n" + _infoFailedAttempts + "\n"
                  + " Found:\n" + _existingFailedAttempts);
        }
        final HashMap<String, TableInfo.Column> _columnsIntruderSelfies = new HashMap<String, TableInfo.Column>(8);
        _columnsIntruderSelfies.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIntruderSelfies.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIntruderSelfies.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIntruderSelfies.put("gate_method", new TableInfo.Column("gate_method", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIntruderSelfies.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIntruderSelfies.put("image_path", new TableInfo.Column("image_path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIntruderSelfies.put("failure_reason", new TableInfo.Column("failure_reason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIntruderSelfies.put("attempt_number", new TableInfo.Column("attempt_number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIntruderSelfies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIntruderSelfies = new HashSet<TableInfo.Index>(2);
        _indicesIntruderSelfies.add(new TableInfo.Index("index_intruder_selfies_package_name_timestamp", false, Arrays.asList("package_name", "timestamp"), Arrays.asList("ASC", "ASC")));
        _indicesIntruderSelfies.add(new TableInfo.Index("index_intruder_selfies_timestamp", false, Arrays.asList("timestamp"), Arrays.asList("ASC")));
        final TableInfo _infoIntruderSelfies = new TableInfo("intruder_selfies", _columnsIntruderSelfies, _foreignKeysIntruderSelfies, _indicesIntruderSelfies);
        final TableInfo _existingIntruderSelfies = TableInfo.read(db, "intruder_selfies");
        if (!_infoIntruderSelfies.equals(_existingIntruderSelfies)) {
          return new RoomOpenHelper.ValidationResult(false, "intruder_selfies(com.soc.agent.database.entity.IntruderSelfieEntity).\n"
                  + " Expected:\n" + _infoIntruderSelfies + "\n"
                  + " Found:\n" + _existingIntruderSelfies);
        }
        final HashMap<String, TableInfo.Column> _columnsSecuritySettings = new HashMap<String, TableInfo.Column>(12);
        _columnsSecuritySettings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("intruder_selfie_enabled", new TableInfo.Column("intruder_selfie_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("intruder_selfie_threshold", new TableInfo.Column("intruder_selfie_threshold", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("fake_crash_enabled", new TableInfo.Column("fake_crash_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("fake_crash_message", new TableInfo.Column("fake_crash_message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("breakin_alert_enabled", new TableInfo.Column("breakin_alert_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("auto_lock_screen_off", new TableInfo.Column("auto_lock_screen_off", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("require_unlock_on_background", new TableInfo.Column("require_unlock_on_background", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("vibrate_on_failed", new TableInfo.Column("vibrate_on_failed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("sound_on_failed", new TableInfo.Column("sound_on_failed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("lock_delay_ms", new TableInfo.Column("lock_delay_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecuritySettings.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSecuritySettings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSecuritySettings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSecuritySettings = new TableInfo("security_settings", _columnsSecuritySettings, _foreignKeysSecuritySettings, _indicesSecuritySettings);
        final TableInfo _existingSecuritySettings = TableInfo.read(db, "security_settings");
        if (!_infoSecuritySettings.equals(_existingSecuritySettings)) {
          return new RoomOpenHelper.ValidationResult(false, "security_settings(com.soc.agent.database.entity.SecuritySettingsEntity).\n"
                  + " Expected:\n" + _infoSecuritySettings + "\n"
                  + " Found:\n" + _existingSecuritySettings);
        }
        final HashMap<String, TableInfo.Column> _columnsBackups = new HashMap<String, TableInfo.Column>(5);
        _columnsBackups.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBackups.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBackups.put("payload", new TableInfo.Column("payload", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBackups.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBackups.put("version", new TableInfo.Column("version", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBackups = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBackups = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBackups = new TableInfo("backups", _columnsBackups, _foreignKeysBackups, _indicesBackups);
        final TableInfo _existingBackups = TableInfo.read(db, "backups");
        if (!_infoBackups.equals(_existingBackups)) {
          return new RoomOpenHelper.ValidationResult(false, "backups(com.soc.agent.database.entity.BackupEntity).\n"
                  + " Expected:\n" + _infoBackups + "\n"
                  + " Found:\n" + _existingBackups);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "457771abdda0cde20a6d46ef24b147fb", "c9e8229ef65f1440a3bec31baf31202d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "locked_apps","unlock_history","failed_attempts","intruder_selfies","security_settings","backups");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `locked_apps`");
      _db.execSQL("DELETE FROM `unlock_history`");
      _db.execSQL("DELETE FROM `failed_attempts`");
      _db.execSQL("DELETE FROM `intruder_selfies`");
      _db.execSQL("DELETE FROM `security_settings`");
      _db.execSQL("DELETE FROM `backups`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LockedAppDao.class, LockedAppDao_LockDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(UnlockHistoryDao.class, UnlockHistoryDao_LockDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(FailedAttemptDao.class, FailedAttemptDao_LockDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(IntruderSelfieDao.class, IntruderSelfieDao_LockDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(SecuritySettingsDao.class, SecuritySettingsDao_LockDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(BackupDao.class, BackupDao_LockDatabase_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LockedAppDao lockedAppDao() {
    if (_lockedAppDao != null) {
      return _lockedAppDao;
    } else {
      synchronized(this) {
        if(_lockedAppDao == null) {
          _lockedAppDao = new LockedAppDao_LockDatabase_Impl(this);
        }
        return _lockedAppDao;
      }
    }
  }

  @Override
  public UnlockHistoryDao unlockHistoryDao() {
    if (_unlockHistoryDao != null) {
      return _unlockHistoryDao;
    } else {
      synchronized(this) {
        if(_unlockHistoryDao == null) {
          _unlockHistoryDao = new UnlockHistoryDao_LockDatabase_Impl(this);
        }
        return _unlockHistoryDao;
      }
    }
  }

  @Override
  public FailedAttemptDao failedAttemptDao() {
    if (_failedAttemptDao != null) {
      return _failedAttemptDao;
    } else {
      synchronized(this) {
        if(_failedAttemptDao == null) {
          _failedAttemptDao = new FailedAttemptDao_LockDatabase_Impl(this);
        }
        return _failedAttemptDao;
      }
    }
  }

  @Override
  public IntruderSelfieDao intruderSelfieDao() {
    if (_intruderSelfieDao != null) {
      return _intruderSelfieDao;
    } else {
      synchronized(this) {
        if(_intruderSelfieDao == null) {
          _intruderSelfieDao = new IntruderSelfieDao_LockDatabase_Impl(this);
        }
        return _intruderSelfieDao;
      }
    }
  }

  @Override
  public SecuritySettingsDao securitySettingsDao() {
    if (_securitySettingsDao != null) {
      return _securitySettingsDao;
    } else {
      synchronized(this) {
        if(_securitySettingsDao == null) {
          _securitySettingsDao = new SecuritySettingsDao_LockDatabase_Impl(this);
        }
        return _securitySettingsDao;
      }
    }
  }

  @Override
  public BackupDao backupDao() {
    if (_backupDao != null) {
      return _backupDao;
    } else {
      synchronized(this) {
        if(_backupDao == null) {
          _backupDao = new BackupDao_LockDatabase_Impl(this);
        }
        return _backupDao;
      }
    }
  }
}
