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
import com.soc.agent.database.dao.AlertDao;
import com.soc.agent.database.dao.AlertDao_Impl;
import com.soc.agent.database.dao.AppDao;
import com.soc.agent.database.dao.AppDao_Impl;
import com.soc.agent.database.dao.AppUsageDao;
import com.soc.agent.database.dao.AppUsageDao_Impl;
import com.soc.agent.database.dao.BackupDao;
import com.soc.agent.database.dao.BackupDao_AppDatabase_Impl;
import com.soc.agent.database.dao.DailyLimitDao;
import com.soc.agent.database.dao.DailyLimitDao_Impl;
import com.soc.agent.database.dao.DailyUsageDao;
import com.soc.agent.database.dao.DailyUsageDao_Impl;
import com.soc.agent.database.dao.DeviceDao;
import com.soc.agent.database.dao.DeviceDao_Impl;
import com.soc.agent.database.dao.ExportReportDao;
import com.soc.agent.database.dao.ExportReportDao_Impl;
import com.soc.agent.database.dao.FailedAttemptDao;
import com.soc.agent.database.dao.FailedAttemptDao_AppDatabase_Impl;
import com.soc.agent.database.dao.FocusModeDao;
import com.soc.agent.database.dao.FocusModeDao_Impl;
import com.soc.agent.database.dao.IntruderSelfieDao;
import com.soc.agent.database.dao.IntruderSelfieDao_AppDatabase_Impl;
import com.soc.agent.database.dao.LaunchCountDao;
import com.soc.agent.database.dao.LaunchCountDao_Impl;
import com.soc.agent.database.dao.LockedAppDao;
import com.soc.agent.database.dao.LockedAppDao_AppDatabase_Impl;
import com.soc.agent.database.dao.MonthlyUsageDao;
import com.soc.agent.database.dao.MonthlyUsageDao_Impl;
import com.soc.agent.database.dao.MostUsedAppsDao;
import com.soc.agent.database.dao.MostUsedAppsDao_Impl;
import com.soc.agent.database.dao.NetworkDao;
import com.soc.agent.database.dao.NetworkDao_Impl;
import com.soc.agent.database.dao.NotificationCountDao;
import com.soc.agent.database.dao.NotificationCountDao_Impl;
import com.soc.agent.database.dao.PolicyDao;
import com.soc.agent.database.dao.PolicyDao_Impl;
import com.soc.agent.database.dao.ScanDao;
import com.soc.agent.database.dao.ScanDao_Impl;
import com.soc.agent.database.dao.ScreenTimeDao;
import com.soc.agent.database.dao.ScreenTimeDao_Impl;
import com.soc.agent.database.dao.SecuritySettingsDao;
import com.soc.agent.database.dao.SecuritySettingsDao_AppDatabase_Impl;
import com.soc.agent.database.dao.TelemetryDao;
import com.soc.agent.database.dao.TelemetryDao_Impl;
import com.soc.agent.database.dao.UnlockHistoryDao;
import com.soc.agent.database.dao.UnlockHistoryDao_AppDatabase_Impl;
import com.soc.agent.database.dao.UsageSessionDao;
import com.soc.agent.database.dao.UsageSessionDao_Impl;
import com.soc.agent.database.dao.UsageTimelineDao;
import com.soc.agent.database.dao.UsageTimelineDao_Impl;
import com.soc.agent.database.dao.WeeklyReportDao;
import com.soc.agent.database.dao.WeeklyReportDao_Impl;
import com.soc.agent.database.dao.WeeklyUsageDao;
import com.soc.agent.database.dao.WeeklyUsageDao_Impl;
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
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DeviceDao _deviceDao;

  private volatile TelemetryDao _telemetryDao;

  private volatile AppDao _appDao;

  private volatile ScanDao _scanDao;

  private volatile AlertDao _alertDao;

  private volatile NetworkDao _networkDao;

  private volatile PolicyDao _policyDao;

  private volatile LockedAppDao _lockedAppDao;

  private volatile UnlockHistoryDao _unlockHistoryDao;

  private volatile FailedAttemptDao _failedAttemptDao;

  private volatile IntruderSelfieDao _intruderSelfieDao;

  private volatile SecuritySettingsDao _securitySettingsDao;

  private volatile BackupDao _backupDao;

  private volatile DailyUsageDao _dailyUsageDao;

  private volatile AppUsageDao _appUsageDao;

  private volatile UsageSessionDao _usageSessionDao;

  private volatile WeeklyUsageDao _weeklyUsageDao;

  private volatile MonthlyUsageDao _monthlyUsageDao;

  private volatile ScreenTimeDao _screenTimeDao;

  private volatile MostUsedAppsDao _mostUsedAppsDao;

  private volatile LaunchCountDao _launchCountDao;

  private volatile NotificationCountDao _notificationCountDao;

  private volatile UsageTimelineDao _usageTimelineDao;

  private volatile FocusModeDao _focusModeDao;

  private volatile DailyLimitDao _dailyLimitDao;

  private volatile WeeklyReportDao _weeklyReportDao;

  private volatile ExportReportDao _exportReportDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(14) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `devices` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `agent_id` TEXT NOT NULL, `device_id` INTEGER, `name` TEXT NOT NULL, `manufacturer` TEXT NOT NULL, `model` TEXT NOT NULL, `android_version` TEXT NOT NULL, `app_version` TEXT NOT NULL, `platform` TEXT NOT NULL, `status` TEXT NOT NULL, `risk` TEXT NOT NULL, `risk_score` INTEGER NOT NULL, `last_seen` INTEGER NOT NULL, `registered_at` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_devices_agent_id` ON `devices` (`agent_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `device_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `event` TEXT NOT NULL, `detail` TEXT NOT NULL, `timestamp_millis` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_device_history_device_id` ON `device_history` (`device_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `cpu_usage` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `timestamp_millis` INTEGER NOT NULL, `load_pct` REAL NOT NULL, `cores` INTEGER NOT NULL, `per_core` TEXT NOT NULL, `speed_ghz` REAL, `temp_c` REAL, `usage_history` TEXT NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_cpu_usage_device_id_timestamp_millis` ON `cpu_usage` (`device_id`, `timestamp_millis`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `memory_usage` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `timestamp_millis` INTEGER NOT NULL, `total_b` INTEGER NOT NULL, `used_b` INTEGER NOT NULL, `free_b` INTEGER NOT NULL, `usage_pct` REAL NOT NULL, `swap_total_b` INTEGER, `swap_used_b` INTEGER)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_memory_usage_device_id_timestamp_millis` ON `memory_usage` (`device_id`, `timestamp_millis`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `storage_usage` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `timestamp_millis` INTEGER NOT NULL, `filesystem` TEXT NOT NULL, `mount` TEXT NOT NULL, `type` TEXT NOT NULL, `total_b` INTEGER NOT NULL, `used_b` INTEGER NOT NULL, `free_b` INTEGER NOT NULL, `usage_pct` REAL NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_storage_usage_device_id_timestamp_millis` ON `storage_usage` (`device_id`, `timestamp_millis`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `battery_status` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `timestamp_millis` INTEGER NOT NULL, `has_battery` INTEGER NOT NULL, `percent` INTEGER NOT NULL, `charging` INTEGER NOT NULL, `status` TEXT NOT NULL, `time_remaining` INTEGER)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_battery_status_device_id_timestamp_millis` ON `battery_status` (`device_id`, `timestamp_millis`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `device_info` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `timestamp_millis` INTEGER NOT NULL, `manufacturer` TEXT NOT NULL, `model` TEXT NOT NULL, `kernel` TEXT NOT NULL, `os_name` TEXT NOT NULL, `os_version` TEXT NOT NULL, `security_patch_level` TEXT NOT NULL, `os_build` TEXT NOT NULL, `uptime_sec` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_device_info_device_id_timestamp_millis` ON `device_info` (`device_id`, `timestamp_millis`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `installed_apps` (`device_id` INTEGER NOT NULL, `package_name` TEXT NOT NULL, `name` TEXT NOT NULL, `pid` INTEGER, `cpu_pct` REAL NOT NULL, `mem_b` INTEGER NOT NULL, `status` TEXT NOT NULL, `risk` TEXT NOT NULL, `signature` TEXT NOT NULL, `first_seen` INTEGER NOT NULL, PRIMARY KEY(`device_id`, `package_name`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_installed_apps_device_id` ON `installed_apps` (`device_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_installed_apps_risk` ON `installed_apps` (`risk`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_permissions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `package_name` TEXT NOT NULL, `category` TEXT NOT NULL, `granted` INTEGER NOT NULL, `name` TEXT NOT NULL, `timestamp_millis` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_app_permissions_device_id_package_name` ON `app_permissions` (`device_id`, `package_name`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `file_scan_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `scan_type` TEXT NOT NULL, `path` TEXT NOT NULL, `name` TEXT NOT NULL, `ext` TEXT NOT NULL, `size_b` INTEGER NOT NULL, `sha256` TEXT, `verdict` TEXT NOT NULL, `detail` TEXT NOT NULL, `scanned_at` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_file_scan_logs_verdict` ON `file_scan_logs` (`verdict`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_file_scan_logs_device_id_scanned_at` ON `file_scan_logs` (`device_id`, `scanned_at`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `scan_runs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `scan_type` TEXT NOT NULL, `status` TEXT NOT NULL, `items_scanned` INTEGER NOT NULL, `threats_found` INTEGER NOT NULL, `score` INTEGER NOT NULL, `grade` TEXT NOT NULL, `started_at` INTEGER NOT NULL, `finished_at` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_scan_runs_device_id_started_at` ON `scan_runs` (`device_id`, `started_at`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `malware_scan_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `scan_id` TEXT NOT NULL, `item` TEXT NOT NULL, `kind` TEXT NOT NULL, `hash` TEXT, `match_name` TEXT, `verdict` TEXT NOT NULL, `severity` TEXT NOT NULL, `detail` TEXT NOT NULL, `quarantined` INTEGER NOT NULL, `scanned_at` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_malware_scan_logs_verdict` ON `malware_scan_logs` (`verdict`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_malware_scan_logs_scan_id` ON `malware_scan_logs` (`scan_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_malware_scan_logs_device_id_scanned_at` ON `malware_scan_logs` (`device_id`, `scanned_at`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `threats` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `kind` TEXT NOT NULL, `title` TEXT NOT NULL, `severity` TEXT NOT NULL, `detail` TEXT NOT NULL, `status` TEXT NOT NULL, `detected_at` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_threats_status` ON `threats` (`status`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_threats_severity` ON `threats` (`severity`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_threats_device_id_detected_at` ON `threats` (`device_id`, `detected_at`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `alerts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `level` TEXT NOT NULL, `title` TEXT NOT NULL, `message` TEXT NOT NULL, `read` INTEGER NOT NULL, `created_at` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_alerts_read` ON `alerts` (`read`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_alerts_device_id_created_at` ON `alerts` (`device_id`, `created_at`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `network_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_id` INTEGER NOT NULL, `kind` TEXT NOT NULL, `timestamp_millis` INTEGER NOT NULL, `iface` TEXT, `ip4` TEXT, `ip6` TEXT, `mac` TEXT, `state` TEXT, `default_gw` TEXT, `dns_servers` TEXT NOT NULL, `wifi_ssid` TEXT, `wifi_rssi` INTEGER, `wifi_link_speed` INTEGER, `vpn_active` INTEGER NOT NULL, `rx_bytes` INTEGER NOT NULL, `tx_bytes` INTEGER NOT NULL, `rx_sec` REAL NOT NULL, `tx_sec` REAL NOT NULL, `url` TEXT, `verdict` TEXT, `score` INTEGER, `reasons` TEXT NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_network_logs_kind` ON `network_logs` (`kind`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_network_logs_device_id_timestamp_millis` ON `network_logs` (`device_id`, `timestamp_millis`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `iocs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `value` TEXT NOT NULL, `type` TEXT NOT NULL, `threat` TEXT, `source` TEXT, `severity` TEXT NOT NULL, `active` INTEGER NOT NULL, `added_at` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_iocs_value_type` ON `iocs` (`value`, `type`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_iocs_type` ON `iocs` (`type`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `locked_apps` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `name` TEXT NOT NULL, `lock_method` TEXT NOT NULL, `added_at` INTEGER NOT NULL, `enabled` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_locked_apps_package_name` ON `locked_apps` (`package_name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_locked_apps_lock_method` ON `locked_apps` (`lock_method`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `policies` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `policy_type` TEXT NOT NULL, `rules` TEXT NOT NULL, `enabled` INTEGER NOT NULL, `synced_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
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
        db.execSQL("CREATE TABLE IF NOT EXISTS `daily_usage` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `date` INTEGER NOT NULL, `total_time_ms` INTEGER NOT NULL, `launch_count` INTEGER NOT NULL, `first_time_ms` INTEGER NOT NULL, `last_time_ms` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_daily_usage_package_name_date` ON `daily_usage` (`package_name`, `date`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_daily_usage_date` ON `daily_usage` (`date`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_daily_usage_total_time_ms` ON `daily_usage` (`total_time_ms`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_usage` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `total_time_ms` INTEGER NOT NULL, `launch_count` INTEGER NOT NULL, `first_used_ms` INTEGER NOT NULL, `last_used_ms` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_app_usage_package_name` ON `app_usage` (`package_name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_app_usage_total_time_ms` ON `app_usage` (`total_time_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_app_usage_last_used_ms` ON `app_usage` (`last_used_ms`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `usage_sessions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `start_time_ms` INTEGER NOT NULL, `end_time_ms` INTEGER NOT NULL, `duration_ms` INTEGER NOT NULL, `active` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usage_sessions_package_name_start_time_ms` ON `usage_sessions` (`package_name`, `start_time_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usage_sessions_start_time_ms` ON `usage_sessions` (`start_time_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usage_sessions_end_time_ms` ON `usage_sessions` (`end_time_ms`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `weekly_usage` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `week_start` INTEGER NOT NULL, `total_time_ms` INTEGER NOT NULL, `launch_count` INTEGER NOT NULL, `active_days` INTEGER NOT NULL, `active_dates` TEXT NOT NULL, `updated_at` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_weekly_usage_package_name_week_start` ON `weekly_usage` (`package_name`, `week_start`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_weekly_usage_week_start` ON `weekly_usage` (`week_start`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_weekly_usage_total_time_ms` ON `weekly_usage` (`total_time_ms`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `monthly_usage` (`package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `month_start` INTEGER NOT NULL, `total_time_ms` INTEGER NOT NULL, `launch_count` INTEGER NOT NULL, `active_days` INTEGER NOT NULL, `active_dates` TEXT NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`package_name`, `month_start`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_monthly_usage_month_start` ON `monthly_usage` (`month_start`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_monthly_usage_package_name` ON `monthly_usage` (`package_name`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `screen_time` (`bucket_start` INTEGER NOT NULL, `granularity` TEXT NOT NULL, `total_time_ms` INTEGER NOT NULL, `app_count` INTEGER NOT NULL, `top_apps` TEXT NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`bucket_start`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_screen_time_bucket_start` ON `screen_time` (`bucket_start`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `launch_count` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `launch_time_ms` INTEGER NOT NULL, `hour_of_day` INTEGER NOT NULL, `day_of_week` INTEGER NOT NULL, `date_int` INTEGER NOT NULL, `month_int` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_launch_count_package_name` ON `launch_count` (`package_name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_launch_count_launch_time_ms` ON `launch_count` (`launch_time_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_launch_count_package_name_launch_time_ms` ON `launch_count` (`package_name`, `launch_time_ms`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `notification_count` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `title` TEXT, `category` TEXT, `priority` INTEGER NOT NULL, `posted_at_ms` INTEGER NOT NULL, `hour_of_day` INTEGER NOT NULL, `day_of_week` INTEGER NOT NULL, `date_int` INTEGER NOT NULL, `month_int` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_notification_count_package_name` ON `notification_count` (`package_name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_notification_count_posted_at_ms` ON `notification_count` (`posted_at_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_notification_count_package_name_posted_at_ms` ON `notification_count` (`package_name`, `posted_at_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_notification_count_date_int` ON `notification_count` (`date_int`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `usage_timeline` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `event_type` TEXT NOT NULL, `event_time_ms` INTEGER NOT NULL, `hour_of_day` INTEGER NOT NULL, `day_of_week` INTEGER NOT NULL, `date_int` INTEGER NOT NULL, `duration_ms` INTEGER NOT NULL, `previous_app` TEXT)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usage_timeline_package_name` ON `usage_timeline` (`package_name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usage_timeline_event_time_ms` ON `usage_timeline` (`event_time_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usage_timeline_date_int` ON `usage_timeline` (`date_int`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usage_timeline_package_name_event_time_ms` ON `usage_timeline` (`package_name`, `event_time_ms`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `focus_mode` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `blocked_apps` TEXT NOT NULL, `start_time_ms` INTEGER NOT NULL, `end_time_ms` INTEGER NOT NULL, `active` INTEGER NOT NULL, `schedule` TEXT NOT NULL, `created_at_ms` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_focus_mode_active` ON `focus_mode` (`active`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_focus_mode_start_time_ms` ON `focus_mode` (`start_time_ms`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_focus_mode_end_time_ms` ON `focus_mode` (`end_time_ms`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `daily_limit` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `package_name` TEXT NOT NULL, `app_name` TEXT NOT NULL, `limit_ms` INTEGER NOT NULL, `warning_threshold` REAL NOT NULL, `exceeded_action` TEXT NOT NULL, `enabled` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_daily_limit_package_name` ON `daily_limit` (`package_name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_daily_limit_enabled` ON `daily_limit` (`enabled`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `weekly_report` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `week_start` INTEGER NOT NULL, `week_end` INTEGER NOT NULL, `total_screen_time_ms` INTEGER NOT NULL, `total_launches` INTEGER NOT NULL, `apps_used_count` INTEGER NOT NULL, `top_apps` TEXT NOT NULL, `most_used_app` TEXT, `most_used_app_time_ms` INTEGER NOT NULL, `peak_day` INTEGER, `peak_day_time_ms` INTEGER NOT NULL, `avg_daily_time_ms` INTEGER NOT NULL, `peak_hour` INTEGER, `notifications_count` INTEGER NOT NULL, `focus_sessions_count` INTEGER NOT NULL, `focus_time_ms` INTEGER NOT NULL, `generated_at` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_weekly_report_week_start` ON `weekly_report` (`week_start`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_weekly_report_generated_at` ON `weekly_report` (`generated_at`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `export_report` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `format` TEXT NOT NULL, `scope` TEXT NOT NULL, `date_from` INTEGER NOT NULL, `date_to` INTEGER NOT NULL, `file_uri` TEXT NOT NULL, `file_size_bytes` INTEGER NOT NULL, `record_count` INTEGER NOT NULL, `exported_at` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_export_report_exported_at` ON `export_report` (`exported_at`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5216e4819da17fab796390802985bb55')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `devices`");
        db.execSQL("DROP TABLE IF EXISTS `device_history`");
        db.execSQL("DROP TABLE IF EXISTS `cpu_usage`");
        db.execSQL("DROP TABLE IF EXISTS `memory_usage`");
        db.execSQL("DROP TABLE IF EXISTS `storage_usage`");
        db.execSQL("DROP TABLE IF EXISTS `battery_status`");
        db.execSQL("DROP TABLE IF EXISTS `device_info`");
        db.execSQL("DROP TABLE IF EXISTS `installed_apps`");
        db.execSQL("DROP TABLE IF EXISTS `app_permissions`");
        db.execSQL("DROP TABLE IF EXISTS `file_scan_logs`");
        db.execSQL("DROP TABLE IF EXISTS `scan_runs`");
        db.execSQL("DROP TABLE IF EXISTS `malware_scan_logs`");
        db.execSQL("DROP TABLE IF EXISTS `threats`");
        db.execSQL("DROP TABLE IF EXISTS `alerts`");
        db.execSQL("DROP TABLE IF EXISTS `network_logs`");
        db.execSQL("DROP TABLE IF EXISTS `iocs`");
        db.execSQL("DROP TABLE IF EXISTS `locked_apps`");
        db.execSQL("DROP TABLE IF EXISTS `policies`");
        db.execSQL("DROP TABLE IF EXISTS `unlock_history`");
        db.execSQL("DROP TABLE IF EXISTS `failed_attempts`");
        db.execSQL("DROP TABLE IF EXISTS `intruder_selfies`");
        db.execSQL("DROP TABLE IF EXISTS `security_settings`");
        db.execSQL("DROP TABLE IF EXISTS `backups`");
        db.execSQL("DROP TABLE IF EXISTS `daily_usage`");
        db.execSQL("DROP TABLE IF EXISTS `app_usage`");
        db.execSQL("DROP TABLE IF EXISTS `usage_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `weekly_usage`");
        db.execSQL("DROP TABLE IF EXISTS `monthly_usage`");
        db.execSQL("DROP TABLE IF EXISTS `screen_time`");
        db.execSQL("DROP TABLE IF EXISTS `launch_count`");
        db.execSQL("DROP TABLE IF EXISTS `notification_count`");
        db.execSQL("DROP TABLE IF EXISTS `usage_timeline`");
        db.execSQL("DROP TABLE IF EXISTS `focus_mode`");
        db.execSQL("DROP TABLE IF EXISTS `daily_limit`");
        db.execSQL("DROP TABLE IF EXISTS `weekly_report`");
        db.execSQL("DROP TABLE IF EXISTS `export_report`");
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
        final HashMap<String, TableInfo.Column> _columnsDevices = new HashMap<String, TableInfo.Column>(14);
        _columnsDevices.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("agent_id", new TableInfo.Column("agent_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("device_id", new TableInfo.Column("device_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("manufacturer", new TableInfo.Column("manufacturer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("model", new TableInfo.Column("model", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("android_version", new TableInfo.Column("android_version", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("app_version", new TableInfo.Column("app_version", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("platform", new TableInfo.Column("platform", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("risk", new TableInfo.Column("risk", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("risk_score", new TableInfo.Column("risk_score", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("last_seen", new TableInfo.Column("last_seen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("registered_at", new TableInfo.Column("registered_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDevices = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDevices = new HashSet<TableInfo.Index>(1);
        _indicesDevices.add(new TableInfo.Index("index_devices_agent_id", true, Arrays.asList("agent_id"), Arrays.asList("ASC")));
        final TableInfo _infoDevices = new TableInfo("devices", _columnsDevices, _foreignKeysDevices, _indicesDevices);
        final TableInfo _existingDevices = TableInfo.read(db, "devices");
        if (!_infoDevices.equals(_existingDevices)) {
          return new RoomOpenHelper.ValidationResult(false, "devices(com.soc.agent.database.entity.DeviceEntity).\n"
                  + " Expected:\n" + _infoDevices + "\n"
                  + " Found:\n" + _existingDevices);
        }
        final HashMap<String, TableInfo.Column> _columnsDeviceHistory = new HashMap<String, TableInfo.Column>(5);
        _columnsDeviceHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceHistory.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceHistory.put("event", new TableInfo.Column("event", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceHistory.put("detail", new TableInfo.Column("detail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceHistory.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDeviceHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDeviceHistory = new HashSet<TableInfo.Index>(1);
        _indicesDeviceHistory.add(new TableInfo.Index("index_device_history_device_id", false, Arrays.asList("device_id"), Arrays.asList("ASC")));
        final TableInfo _infoDeviceHistory = new TableInfo("device_history", _columnsDeviceHistory, _foreignKeysDeviceHistory, _indicesDeviceHistory);
        final TableInfo _existingDeviceHistory = TableInfo.read(db, "device_history");
        if (!_infoDeviceHistory.equals(_existingDeviceHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "device_history(com.soc.agent.database.entity.DeviceHistoryEntity).\n"
                  + " Expected:\n" + _infoDeviceHistory + "\n"
                  + " Found:\n" + _existingDeviceHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsCpuUsage = new HashMap<String, TableInfo.Column>(9);
        _columnsCpuUsage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("load_pct", new TableInfo.Column("load_pct", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("cores", new TableInfo.Column("cores", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("per_core", new TableInfo.Column("per_core", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("speed_ghz", new TableInfo.Column("speed_ghz", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("temp_c", new TableInfo.Column("temp_c", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCpuUsage.put("usage_history", new TableInfo.Column("usage_history", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCpuUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCpuUsage = new HashSet<TableInfo.Index>(1);
        _indicesCpuUsage.add(new TableInfo.Index("index_cpu_usage_device_id_timestamp_millis", false, Arrays.asList("device_id", "timestamp_millis"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoCpuUsage = new TableInfo("cpu_usage", _columnsCpuUsage, _foreignKeysCpuUsage, _indicesCpuUsage);
        final TableInfo _existingCpuUsage = TableInfo.read(db, "cpu_usage");
        if (!_infoCpuUsage.equals(_existingCpuUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "cpu_usage(com.soc.agent.database.entity.CpuUsageEntity).\n"
                  + " Expected:\n" + _infoCpuUsage + "\n"
                  + " Found:\n" + _existingCpuUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsMemoryUsage = new HashMap<String, TableInfo.Column>(9);
        _columnsMemoryUsage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("total_b", new TableInfo.Column("total_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("used_b", new TableInfo.Column("used_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("free_b", new TableInfo.Column("free_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("usage_pct", new TableInfo.Column("usage_pct", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("swap_total_b", new TableInfo.Column("swap_total_b", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemoryUsage.put("swap_used_b", new TableInfo.Column("swap_used_b", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMemoryUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMemoryUsage = new HashSet<TableInfo.Index>(1);
        _indicesMemoryUsage.add(new TableInfo.Index("index_memory_usage_device_id_timestamp_millis", false, Arrays.asList("device_id", "timestamp_millis"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoMemoryUsage = new TableInfo("memory_usage", _columnsMemoryUsage, _foreignKeysMemoryUsage, _indicesMemoryUsage);
        final TableInfo _existingMemoryUsage = TableInfo.read(db, "memory_usage");
        if (!_infoMemoryUsage.equals(_existingMemoryUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "memory_usage(com.soc.agent.database.entity.MemoryUsageEntity).\n"
                  + " Expected:\n" + _infoMemoryUsage + "\n"
                  + " Found:\n" + _existingMemoryUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsStorageUsage = new HashMap<String, TableInfo.Column>(10);
        _columnsStorageUsage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("filesystem", new TableInfo.Column("filesystem", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("mount", new TableInfo.Column("mount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("total_b", new TableInfo.Column("total_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("used_b", new TableInfo.Column("used_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("free_b", new TableInfo.Column("free_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStorageUsage.put("usage_pct", new TableInfo.Column("usage_pct", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStorageUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStorageUsage = new HashSet<TableInfo.Index>(1);
        _indicesStorageUsage.add(new TableInfo.Index("index_storage_usage_device_id_timestamp_millis", false, Arrays.asList("device_id", "timestamp_millis"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoStorageUsage = new TableInfo("storage_usage", _columnsStorageUsage, _foreignKeysStorageUsage, _indicesStorageUsage);
        final TableInfo _existingStorageUsage = TableInfo.read(db, "storage_usage");
        if (!_infoStorageUsage.equals(_existingStorageUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "storage_usage(com.soc.agent.database.entity.StorageUsageEntity).\n"
                  + " Expected:\n" + _infoStorageUsage + "\n"
                  + " Found:\n" + _existingStorageUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsBatteryStatus = new HashMap<String, TableInfo.Column>(8);
        _columnsBatteryStatus.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBatteryStatus.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBatteryStatus.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBatteryStatus.put("has_battery", new TableInfo.Column("has_battery", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBatteryStatus.put("percent", new TableInfo.Column("percent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBatteryStatus.put("charging", new TableInfo.Column("charging", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBatteryStatus.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBatteryStatus.put("time_remaining", new TableInfo.Column("time_remaining", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBatteryStatus = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBatteryStatus = new HashSet<TableInfo.Index>(1);
        _indicesBatteryStatus.add(new TableInfo.Index("index_battery_status_device_id_timestamp_millis", false, Arrays.asList("device_id", "timestamp_millis"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoBatteryStatus = new TableInfo("battery_status", _columnsBatteryStatus, _foreignKeysBatteryStatus, _indicesBatteryStatus);
        final TableInfo _existingBatteryStatus = TableInfo.read(db, "battery_status");
        if (!_infoBatteryStatus.equals(_existingBatteryStatus)) {
          return new RoomOpenHelper.ValidationResult(false, "battery_status(com.soc.agent.database.entity.BatteryStatusEntity).\n"
                  + " Expected:\n" + _infoBatteryStatus + "\n"
                  + " Found:\n" + _existingBatteryStatus);
        }
        final HashMap<String, TableInfo.Column> _columnsDeviceInfo = new HashMap<String, TableInfo.Column>(11);
        _columnsDeviceInfo.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("manufacturer", new TableInfo.Column("manufacturer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("model", new TableInfo.Column("model", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("kernel", new TableInfo.Column("kernel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("os_name", new TableInfo.Column("os_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("os_version", new TableInfo.Column("os_version", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("security_patch_level", new TableInfo.Column("security_patch_level", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("os_build", new TableInfo.Column("os_build", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeviceInfo.put("uptime_sec", new TableInfo.Column("uptime_sec", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDeviceInfo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDeviceInfo = new HashSet<TableInfo.Index>(1);
        _indicesDeviceInfo.add(new TableInfo.Index("index_device_info_device_id_timestamp_millis", false, Arrays.asList("device_id", "timestamp_millis"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoDeviceInfo = new TableInfo("device_info", _columnsDeviceInfo, _foreignKeysDeviceInfo, _indicesDeviceInfo);
        final TableInfo _existingDeviceInfo = TableInfo.read(db, "device_info");
        if (!_infoDeviceInfo.equals(_existingDeviceInfo)) {
          return new RoomOpenHelper.ValidationResult(false, "device_info(com.soc.agent.database.entity.DeviceInfoEntity).\n"
                  + " Expected:\n" + _infoDeviceInfo + "\n"
                  + " Found:\n" + _existingDeviceInfo);
        }
        final HashMap<String, TableInfo.Column> _columnsInstalledApps = new HashMap<String, TableInfo.Column>(10);
        _columnsInstalledApps.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("pid", new TableInfo.Column("pid", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("cpu_pct", new TableInfo.Column("cpu_pct", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("mem_b", new TableInfo.Column("mem_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("risk", new TableInfo.Column("risk", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("signature", new TableInfo.Column("signature", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstalledApps.put("first_seen", new TableInfo.Column("first_seen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInstalledApps = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInstalledApps = new HashSet<TableInfo.Index>(2);
        _indicesInstalledApps.add(new TableInfo.Index("index_installed_apps_device_id", false, Arrays.asList("device_id"), Arrays.asList("ASC")));
        _indicesInstalledApps.add(new TableInfo.Index("index_installed_apps_risk", false, Arrays.asList("risk"), Arrays.asList("ASC")));
        final TableInfo _infoInstalledApps = new TableInfo("installed_apps", _columnsInstalledApps, _foreignKeysInstalledApps, _indicesInstalledApps);
        final TableInfo _existingInstalledApps = TableInfo.read(db, "installed_apps");
        if (!_infoInstalledApps.equals(_existingInstalledApps)) {
          return new RoomOpenHelper.ValidationResult(false, "installed_apps(com.soc.agent.database.entity.InstalledAppEntity).\n"
                  + " Expected:\n" + _infoInstalledApps + "\n"
                  + " Found:\n" + _existingInstalledApps);
        }
        final HashMap<String, TableInfo.Column> _columnsAppPermissions = new HashMap<String, TableInfo.Column>(7);
        _columnsAppPermissions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("granted", new TableInfo.Column("granted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppPermissions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppPermissions = new HashSet<TableInfo.Index>(1);
        _indicesAppPermissions.add(new TableInfo.Index("index_app_permissions_device_id_package_name", false, Arrays.asList("device_id", "package_name"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoAppPermissions = new TableInfo("app_permissions", _columnsAppPermissions, _foreignKeysAppPermissions, _indicesAppPermissions);
        final TableInfo _existingAppPermissions = TableInfo.read(db, "app_permissions");
        if (!_infoAppPermissions.equals(_existingAppPermissions)) {
          return new RoomOpenHelper.ValidationResult(false, "app_permissions(com.soc.agent.database.entity.AppPermissionEntity).\n"
                  + " Expected:\n" + _infoAppPermissions + "\n"
                  + " Found:\n" + _existingAppPermissions);
        }
        final HashMap<String, TableInfo.Column> _columnsFileScanLogs = new HashMap<String, TableInfo.Column>(11);
        _columnsFileScanLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("scan_type", new TableInfo.Column("scan_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("ext", new TableInfo.Column("ext", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("size_b", new TableInfo.Column("size_b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("sha256", new TableInfo.Column("sha256", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("verdict", new TableInfo.Column("verdict", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("detail", new TableInfo.Column("detail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFileScanLogs.put("scanned_at", new TableInfo.Column("scanned_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFileScanLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFileScanLogs = new HashSet<TableInfo.Index>(2);
        _indicesFileScanLogs.add(new TableInfo.Index("index_file_scan_logs_verdict", false, Arrays.asList("verdict"), Arrays.asList("ASC")));
        _indicesFileScanLogs.add(new TableInfo.Index("index_file_scan_logs_device_id_scanned_at", false, Arrays.asList("device_id", "scanned_at"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoFileScanLogs = new TableInfo("file_scan_logs", _columnsFileScanLogs, _foreignKeysFileScanLogs, _indicesFileScanLogs);
        final TableInfo _existingFileScanLogs = TableInfo.read(db, "file_scan_logs");
        if (!_infoFileScanLogs.equals(_existingFileScanLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "file_scan_logs(com.soc.agent.database.entity.FileScanLogEntity).\n"
                  + " Expected:\n" + _infoFileScanLogs + "\n"
                  + " Found:\n" + _existingFileScanLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsScanRuns = new HashMap<String, TableInfo.Column>(10);
        _columnsScanRuns.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("scan_type", new TableInfo.Column("scan_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("items_scanned", new TableInfo.Column("items_scanned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("threats_found", new TableInfo.Column("threats_found", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("score", new TableInfo.Column("score", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("grade", new TableInfo.Column("grade", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("started_at", new TableInfo.Column("started_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanRuns.put("finished_at", new TableInfo.Column("finished_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScanRuns = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScanRuns = new HashSet<TableInfo.Index>(1);
        _indicesScanRuns.add(new TableInfo.Index("index_scan_runs_device_id_started_at", false, Arrays.asList("device_id", "started_at"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoScanRuns = new TableInfo("scan_runs", _columnsScanRuns, _foreignKeysScanRuns, _indicesScanRuns);
        final TableInfo _existingScanRuns = TableInfo.read(db, "scan_runs");
        if (!_infoScanRuns.equals(_existingScanRuns)) {
          return new RoomOpenHelper.ValidationResult(false, "scan_runs(com.soc.agent.database.entity.ScanRunEntity).\n"
                  + " Expected:\n" + _infoScanRuns + "\n"
                  + " Found:\n" + _existingScanRuns);
        }
        final HashMap<String, TableInfo.Column> _columnsMalwareScanLogs = new HashMap<String, TableInfo.Column>(12);
        _columnsMalwareScanLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("scan_id", new TableInfo.Column("scan_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("item", new TableInfo.Column("item", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("kind", new TableInfo.Column("kind", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("hash", new TableInfo.Column("hash", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("match_name", new TableInfo.Column("match_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("verdict", new TableInfo.Column("verdict", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("detail", new TableInfo.Column("detail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("quarantined", new TableInfo.Column("quarantined", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMalwareScanLogs.put("scanned_at", new TableInfo.Column("scanned_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMalwareScanLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMalwareScanLogs = new HashSet<TableInfo.Index>(3);
        _indicesMalwareScanLogs.add(new TableInfo.Index("index_malware_scan_logs_verdict", false, Arrays.asList("verdict"), Arrays.asList("ASC")));
        _indicesMalwareScanLogs.add(new TableInfo.Index("index_malware_scan_logs_scan_id", false, Arrays.asList("scan_id"), Arrays.asList("ASC")));
        _indicesMalwareScanLogs.add(new TableInfo.Index("index_malware_scan_logs_device_id_scanned_at", false, Arrays.asList("device_id", "scanned_at"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoMalwareScanLogs = new TableInfo("malware_scan_logs", _columnsMalwareScanLogs, _foreignKeysMalwareScanLogs, _indicesMalwareScanLogs);
        final TableInfo _existingMalwareScanLogs = TableInfo.read(db, "malware_scan_logs");
        if (!_infoMalwareScanLogs.equals(_existingMalwareScanLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "malware_scan_logs(com.soc.agent.database.entity.MalwareScanLogEntity).\n"
                  + " Expected:\n" + _infoMalwareScanLogs + "\n"
                  + " Found:\n" + _existingMalwareScanLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsThreats = new HashMap<String, TableInfo.Column>(8);
        _columnsThreats.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreats.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreats.put("kind", new TableInfo.Column("kind", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreats.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreats.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreats.put("detail", new TableInfo.Column("detail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreats.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThreats.put("detected_at", new TableInfo.Column("detected_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysThreats = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesThreats = new HashSet<TableInfo.Index>(3);
        _indicesThreats.add(new TableInfo.Index("index_threats_status", false, Arrays.asList("status"), Arrays.asList("ASC")));
        _indicesThreats.add(new TableInfo.Index("index_threats_severity", false, Arrays.asList("severity"), Arrays.asList("ASC")));
        _indicesThreats.add(new TableInfo.Index("index_threats_device_id_detected_at", false, Arrays.asList("device_id", "detected_at"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoThreats = new TableInfo("threats", _columnsThreats, _foreignKeysThreats, _indicesThreats);
        final TableInfo _existingThreats = TableInfo.read(db, "threats");
        if (!_infoThreats.equals(_existingThreats)) {
          return new RoomOpenHelper.ValidationResult(false, "threats(com.soc.agent.database.entity.ThreatEntity).\n"
                  + " Expected:\n" + _infoThreats + "\n"
                  + " Found:\n" + _existingThreats);
        }
        final HashMap<String, TableInfo.Column> _columnsAlerts = new HashMap<String, TableInfo.Column>(7);
        _columnsAlerts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("level", new TableInfo.Column("level", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("read", new TableInfo.Column("read", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlerts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlerts = new HashSet<TableInfo.Index>(2);
        _indicesAlerts.add(new TableInfo.Index("index_alerts_read", false, Arrays.asList("read"), Arrays.asList("ASC")));
        _indicesAlerts.add(new TableInfo.Index("index_alerts_device_id_created_at", false, Arrays.asList("device_id", "created_at"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoAlerts = new TableInfo("alerts", _columnsAlerts, _foreignKeysAlerts, _indicesAlerts);
        final TableInfo _existingAlerts = TableInfo.read(db, "alerts");
        if (!_infoAlerts.equals(_existingAlerts)) {
          return new RoomOpenHelper.ValidationResult(false, "alerts(com.soc.agent.database.entity.AlertEntity).\n"
                  + " Expected:\n" + _infoAlerts + "\n"
                  + " Found:\n" + _existingAlerts);
        }
        final HashMap<String, TableInfo.Column> _columnsNetworkLogs = new HashMap<String, TableInfo.Column>(23);
        _columnsNetworkLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("device_id", new TableInfo.Column("device_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("kind", new TableInfo.Column("kind", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("timestamp_millis", new TableInfo.Column("timestamp_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("iface", new TableInfo.Column("iface", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("ip4", new TableInfo.Column("ip4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("ip6", new TableInfo.Column("ip6", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("mac", new TableInfo.Column("mac", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("state", new TableInfo.Column("state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("default_gw", new TableInfo.Column("default_gw", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("dns_servers", new TableInfo.Column("dns_servers", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("wifi_ssid", new TableInfo.Column("wifi_ssid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("wifi_rssi", new TableInfo.Column("wifi_rssi", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("wifi_link_speed", new TableInfo.Column("wifi_link_speed", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("vpn_active", new TableInfo.Column("vpn_active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("rx_bytes", new TableInfo.Column("rx_bytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("tx_bytes", new TableInfo.Column("tx_bytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("rx_sec", new TableInfo.Column("rx_sec", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("tx_sec", new TableInfo.Column("tx_sec", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("url", new TableInfo.Column("url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("verdict", new TableInfo.Column("verdict", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("score", new TableInfo.Column("score", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNetworkLogs.put("reasons", new TableInfo.Column("reasons", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNetworkLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNetworkLogs = new HashSet<TableInfo.Index>(2);
        _indicesNetworkLogs.add(new TableInfo.Index("index_network_logs_kind", false, Arrays.asList("kind"), Arrays.asList("ASC")));
        _indicesNetworkLogs.add(new TableInfo.Index("index_network_logs_device_id_timestamp_millis", false, Arrays.asList("device_id", "timestamp_millis"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoNetworkLogs = new TableInfo("network_logs", _columnsNetworkLogs, _foreignKeysNetworkLogs, _indicesNetworkLogs);
        final TableInfo _existingNetworkLogs = TableInfo.read(db, "network_logs");
        if (!_infoNetworkLogs.equals(_existingNetworkLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "network_logs(com.soc.agent.database.entity.NetworkLogEntity).\n"
                  + " Expected:\n" + _infoNetworkLogs + "\n"
                  + " Found:\n" + _existingNetworkLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsIocs = new HashMap<String, TableInfo.Column>(8);
        _columnsIocs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIocs.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIocs.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIocs.put("threat", new TableInfo.Column("threat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIocs.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIocs.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIocs.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIocs.put("added_at", new TableInfo.Column("added_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIocs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIocs = new HashSet<TableInfo.Index>(2);
        _indicesIocs.add(new TableInfo.Index("index_iocs_value_type", true, Arrays.asList("value", "type"), Arrays.asList("ASC", "ASC")));
        _indicesIocs.add(new TableInfo.Index("index_iocs_type", false, Arrays.asList("type"), Arrays.asList("ASC")));
        final TableInfo _infoIocs = new TableInfo("iocs", _columnsIocs, _foreignKeysIocs, _indicesIocs);
        final TableInfo _existingIocs = TableInfo.read(db, "iocs");
        if (!_infoIocs.equals(_existingIocs)) {
          return new RoomOpenHelper.ValidationResult(false, "iocs(com.soc.agent.database.entity.IocEntity).\n"
                  + " Expected:\n" + _infoIocs + "\n"
                  + " Found:\n" + _existingIocs);
        }
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
        final HashMap<String, TableInfo.Column> _columnsPolicies = new HashMap<String, TableInfo.Column>(6);
        _columnsPolicies.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("policy_type", new TableInfo.Column("policy_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("rules", new TableInfo.Column("rules", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("synced_at", new TableInfo.Column("synced_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPolicies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPolicies = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPolicies = new TableInfo("policies", _columnsPolicies, _foreignKeysPolicies, _indicesPolicies);
        final TableInfo _existingPolicies = TableInfo.read(db, "policies");
        if (!_infoPolicies.equals(_existingPolicies)) {
          return new RoomOpenHelper.ValidationResult(false, "policies(com.soc.agent.database.entity.PolicyEntity).\n"
                  + " Expected:\n" + _infoPolicies + "\n"
                  + " Found:\n" + _existingPolicies);
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
        final HashMap<String, TableInfo.Column> _columnsDailyUsage = new HashMap<String, TableInfo.Column>(9);
        _columnsDailyUsage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("total_time_ms", new TableInfo.Column("total_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("launch_count", new TableInfo.Column("launch_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("first_time_ms", new TableInfo.Column("first_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("last_time_ms", new TableInfo.Column("last_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyUsage.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDailyUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDailyUsage = new HashSet<TableInfo.Index>(3);
        _indicesDailyUsage.add(new TableInfo.Index("index_daily_usage_package_name_date", true, Arrays.asList("package_name", "date"), Arrays.asList("ASC", "ASC")));
        _indicesDailyUsage.add(new TableInfo.Index("index_daily_usage_date", false, Arrays.asList("date"), Arrays.asList("ASC")));
        _indicesDailyUsage.add(new TableInfo.Index("index_daily_usage_total_time_ms", false, Arrays.asList("total_time_ms"), Arrays.asList("ASC")));
        final TableInfo _infoDailyUsage = new TableInfo("daily_usage", _columnsDailyUsage, _foreignKeysDailyUsage, _indicesDailyUsage);
        final TableInfo _existingDailyUsage = TableInfo.read(db, "daily_usage");
        if (!_infoDailyUsage.equals(_existingDailyUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "daily_usage(com.soc.agent.database.entity.DailyUsageEntity).\n"
                  + " Expected:\n" + _infoDailyUsage + "\n"
                  + " Found:\n" + _existingDailyUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsAppUsage = new HashMap<String, TableInfo.Column>(8);
        _columnsAppUsage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("total_time_ms", new TableInfo.Column("total_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("launch_count", new TableInfo.Column("launch_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("first_used_ms", new TableInfo.Column("first_used_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("last_used_ms", new TableInfo.Column("last_used_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppUsage = new HashSet<TableInfo.Index>(3);
        _indicesAppUsage.add(new TableInfo.Index("index_app_usage_package_name", true, Arrays.asList("package_name"), Arrays.asList("ASC")));
        _indicesAppUsage.add(new TableInfo.Index("index_app_usage_total_time_ms", false, Arrays.asList("total_time_ms"), Arrays.asList("ASC")));
        _indicesAppUsage.add(new TableInfo.Index("index_app_usage_last_used_ms", false, Arrays.asList("last_used_ms"), Arrays.asList("ASC")));
        final TableInfo _infoAppUsage = new TableInfo("app_usage", _columnsAppUsage, _foreignKeysAppUsage, _indicesAppUsage);
        final TableInfo _existingAppUsage = TableInfo.read(db, "app_usage");
        if (!_infoAppUsage.equals(_existingAppUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "app_usage(com.soc.agent.database.entity.AppUsageEntity).\n"
                  + " Expected:\n" + _infoAppUsage + "\n"
                  + " Found:\n" + _existingAppUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsUsageSessions = new HashMap<String, TableInfo.Column>(7);
        _columnsUsageSessions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageSessions.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageSessions.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageSessions.put("start_time_ms", new TableInfo.Column("start_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageSessions.put("end_time_ms", new TableInfo.Column("end_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageSessions.put("duration_ms", new TableInfo.Column("duration_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageSessions.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsageSessions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsageSessions = new HashSet<TableInfo.Index>(3);
        _indicesUsageSessions.add(new TableInfo.Index("index_usage_sessions_package_name_start_time_ms", false, Arrays.asList("package_name", "start_time_ms"), Arrays.asList("ASC", "ASC")));
        _indicesUsageSessions.add(new TableInfo.Index("index_usage_sessions_start_time_ms", false, Arrays.asList("start_time_ms"), Arrays.asList("ASC")));
        _indicesUsageSessions.add(new TableInfo.Index("index_usage_sessions_end_time_ms", false, Arrays.asList("end_time_ms"), Arrays.asList("ASC")));
        final TableInfo _infoUsageSessions = new TableInfo("usage_sessions", _columnsUsageSessions, _foreignKeysUsageSessions, _indicesUsageSessions);
        final TableInfo _existingUsageSessions = TableInfo.read(db, "usage_sessions");
        if (!_infoUsageSessions.equals(_existingUsageSessions)) {
          return new RoomOpenHelper.ValidationResult(false, "usage_sessions(com.soc.agent.database.entity.UsageSessionEntity).\n"
                  + " Expected:\n" + _infoUsageSessions + "\n"
                  + " Found:\n" + _existingUsageSessions);
        }
        final HashMap<String, TableInfo.Column> _columnsWeeklyUsage = new HashMap<String, TableInfo.Column>(9);
        _columnsWeeklyUsage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("week_start", new TableInfo.Column("week_start", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("total_time_ms", new TableInfo.Column("total_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("launch_count", new TableInfo.Column("launch_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("active_days", new TableInfo.Column("active_days", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("active_dates", new TableInfo.Column("active_dates", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyUsage.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWeeklyUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWeeklyUsage = new HashSet<TableInfo.Index>(3);
        _indicesWeeklyUsage.add(new TableInfo.Index("index_weekly_usage_package_name_week_start", true, Arrays.asList("package_name", "week_start"), Arrays.asList("ASC", "ASC")));
        _indicesWeeklyUsage.add(new TableInfo.Index("index_weekly_usage_week_start", false, Arrays.asList("week_start"), Arrays.asList("ASC")));
        _indicesWeeklyUsage.add(new TableInfo.Index("index_weekly_usage_total_time_ms", false, Arrays.asList("total_time_ms"), Arrays.asList("ASC")));
        final TableInfo _infoWeeklyUsage = new TableInfo("weekly_usage", _columnsWeeklyUsage, _foreignKeysWeeklyUsage, _indicesWeeklyUsage);
        final TableInfo _existingWeeklyUsage = TableInfo.read(db, "weekly_usage");
        if (!_infoWeeklyUsage.equals(_existingWeeklyUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "weekly_usage(com.soc.agent.database.entity.WeeklyUsageEntity).\n"
                  + " Expected:\n" + _infoWeeklyUsage + "\n"
                  + " Found:\n" + _existingWeeklyUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsMonthlyUsage = new HashMap<String, TableInfo.Column>(8);
        _columnsMonthlyUsage.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMonthlyUsage.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMonthlyUsage.put("month_start", new TableInfo.Column("month_start", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMonthlyUsage.put("total_time_ms", new TableInfo.Column("total_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMonthlyUsage.put("launch_count", new TableInfo.Column("launch_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMonthlyUsage.put("active_days", new TableInfo.Column("active_days", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMonthlyUsage.put("active_dates", new TableInfo.Column("active_dates", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMonthlyUsage.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMonthlyUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMonthlyUsage = new HashSet<TableInfo.Index>(2);
        _indicesMonthlyUsage.add(new TableInfo.Index("index_monthly_usage_month_start", false, Arrays.asList("month_start"), Arrays.asList("ASC")));
        _indicesMonthlyUsage.add(new TableInfo.Index("index_monthly_usage_package_name", false, Arrays.asList("package_name"), Arrays.asList("ASC")));
        final TableInfo _infoMonthlyUsage = new TableInfo("monthly_usage", _columnsMonthlyUsage, _foreignKeysMonthlyUsage, _indicesMonthlyUsage);
        final TableInfo _existingMonthlyUsage = TableInfo.read(db, "monthly_usage");
        if (!_infoMonthlyUsage.equals(_existingMonthlyUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "monthly_usage(com.soc.agent.database.entity.MonthlyUsageEntity).\n"
                  + " Expected:\n" + _infoMonthlyUsage + "\n"
                  + " Found:\n" + _existingMonthlyUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsScreenTime = new HashMap<String, TableInfo.Column>(6);
        _columnsScreenTime.put("bucket_start", new TableInfo.Column("bucket_start", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTime.put("granularity", new TableInfo.Column("granularity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTime.put("total_time_ms", new TableInfo.Column("total_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTime.put("app_count", new TableInfo.Column("app_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTime.put("top_apps", new TableInfo.Column("top_apps", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScreenTime.put("updated_at", new TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScreenTime = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScreenTime = new HashSet<TableInfo.Index>(1);
        _indicesScreenTime.add(new TableInfo.Index("index_screen_time_bucket_start", false, Arrays.asList("bucket_start"), Arrays.asList("ASC")));
        final TableInfo _infoScreenTime = new TableInfo("screen_time", _columnsScreenTime, _foreignKeysScreenTime, _indicesScreenTime);
        final TableInfo _existingScreenTime = TableInfo.read(db, "screen_time");
        if (!_infoScreenTime.equals(_existingScreenTime)) {
          return new RoomOpenHelper.ValidationResult(false, "screen_time(com.soc.agent.database.entity.ScreenTimeEntity).\n"
                  + " Expected:\n" + _infoScreenTime + "\n"
                  + " Found:\n" + _existingScreenTime);
        }
        final HashMap<String, TableInfo.Column> _columnsLaunchCount = new HashMap<String, TableInfo.Column>(8);
        _columnsLaunchCount.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLaunchCount.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLaunchCount.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLaunchCount.put("launch_time_ms", new TableInfo.Column("launch_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLaunchCount.put("hour_of_day", new TableInfo.Column("hour_of_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLaunchCount.put("day_of_week", new TableInfo.Column("day_of_week", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLaunchCount.put("date_int", new TableInfo.Column("date_int", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLaunchCount.put("month_int", new TableInfo.Column("month_int", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLaunchCount = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLaunchCount = new HashSet<TableInfo.Index>(3);
        _indicesLaunchCount.add(new TableInfo.Index("index_launch_count_package_name", false, Arrays.asList("package_name"), Arrays.asList("ASC")));
        _indicesLaunchCount.add(new TableInfo.Index("index_launch_count_launch_time_ms", false, Arrays.asList("launch_time_ms"), Arrays.asList("ASC")));
        _indicesLaunchCount.add(new TableInfo.Index("index_launch_count_package_name_launch_time_ms", false, Arrays.asList("package_name", "launch_time_ms"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoLaunchCount = new TableInfo("launch_count", _columnsLaunchCount, _foreignKeysLaunchCount, _indicesLaunchCount);
        final TableInfo _existingLaunchCount = TableInfo.read(db, "launch_count");
        if (!_infoLaunchCount.equals(_existingLaunchCount)) {
          return new RoomOpenHelper.ValidationResult(false, "launch_count(com.soc.agent.database.entity.LaunchCountEntity).\n"
                  + " Expected:\n" + _infoLaunchCount + "\n"
                  + " Found:\n" + _existingLaunchCount);
        }
        final HashMap<String, TableInfo.Column> _columnsNotificationCount = new HashMap<String, TableInfo.Column>(11);
        _columnsNotificationCount.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("posted_at_ms", new TableInfo.Column("posted_at_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("hour_of_day", new TableInfo.Column("hour_of_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("day_of_week", new TableInfo.Column("day_of_week", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("date_int", new TableInfo.Column("date_int", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationCount.put("month_int", new TableInfo.Column("month_int", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotificationCount = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotificationCount = new HashSet<TableInfo.Index>(4);
        _indicesNotificationCount.add(new TableInfo.Index("index_notification_count_package_name", false, Arrays.asList("package_name"), Arrays.asList("ASC")));
        _indicesNotificationCount.add(new TableInfo.Index("index_notification_count_posted_at_ms", false, Arrays.asList("posted_at_ms"), Arrays.asList("ASC")));
        _indicesNotificationCount.add(new TableInfo.Index("index_notification_count_package_name_posted_at_ms", false, Arrays.asList("package_name", "posted_at_ms"), Arrays.asList("ASC", "ASC")));
        _indicesNotificationCount.add(new TableInfo.Index("index_notification_count_date_int", false, Arrays.asList("date_int"), Arrays.asList("ASC")));
        final TableInfo _infoNotificationCount = new TableInfo("notification_count", _columnsNotificationCount, _foreignKeysNotificationCount, _indicesNotificationCount);
        final TableInfo _existingNotificationCount = TableInfo.read(db, "notification_count");
        if (!_infoNotificationCount.equals(_existingNotificationCount)) {
          return new RoomOpenHelper.ValidationResult(false, "notification_count(com.soc.agent.database.entity.NotificationCountEntity).\n"
                  + " Expected:\n" + _infoNotificationCount + "\n"
                  + " Found:\n" + _existingNotificationCount);
        }
        final HashMap<String, TableInfo.Column> _columnsUsageTimeline = new HashMap<String, TableInfo.Column>(10);
        _columnsUsageTimeline.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("event_type", new TableInfo.Column("event_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("event_time_ms", new TableInfo.Column("event_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("hour_of_day", new TableInfo.Column("hour_of_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("day_of_week", new TableInfo.Column("day_of_week", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("date_int", new TableInfo.Column("date_int", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("duration_ms", new TableInfo.Column("duration_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsageTimeline.put("previous_app", new TableInfo.Column("previous_app", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsageTimeline = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsageTimeline = new HashSet<TableInfo.Index>(4);
        _indicesUsageTimeline.add(new TableInfo.Index("index_usage_timeline_package_name", false, Arrays.asList("package_name"), Arrays.asList("ASC")));
        _indicesUsageTimeline.add(new TableInfo.Index("index_usage_timeline_event_time_ms", false, Arrays.asList("event_time_ms"), Arrays.asList("ASC")));
        _indicesUsageTimeline.add(new TableInfo.Index("index_usage_timeline_date_int", false, Arrays.asList("date_int"), Arrays.asList("ASC")));
        _indicesUsageTimeline.add(new TableInfo.Index("index_usage_timeline_package_name_event_time_ms", false, Arrays.asList("package_name", "event_time_ms"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoUsageTimeline = new TableInfo("usage_timeline", _columnsUsageTimeline, _foreignKeysUsageTimeline, _indicesUsageTimeline);
        final TableInfo _existingUsageTimeline = TableInfo.read(db, "usage_timeline");
        if (!_infoUsageTimeline.equals(_existingUsageTimeline)) {
          return new RoomOpenHelper.ValidationResult(false, "usage_timeline(com.soc.agent.database.entity.UsageTimelineEntity).\n"
                  + " Expected:\n" + _infoUsageTimeline + "\n"
                  + " Found:\n" + _existingUsageTimeline);
        }
        final HashMap<String, TableInfo.Column> _columnsFocusMode = new HashMap<String, TableInfo.Column>(8);
        _columnsFocusMode.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFocusMode.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFocusMode.put("blocked_apps", new TableInfo.Column("blocked_apps", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFocusMode.put("start_time_ms", new TableInfo.Column("start_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFocusMode.put("end_time_ms", new TableInfo.Column("end_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFocusMode.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFocusMode.put("schedule", new TableInfo.Column("schedule", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFocusMode.put("created_at_ms", new TableInfo.Column("created_at_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFocusMode = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFocusMode = new HashSet<TableInfo.Index>(3);
        _indicesFocusMode.add(new TableInfo.Index("index_focus_mode_active", false, Arrays.asList("active"), Arrays.asList("ASC")));
        _indicesFocusMode.add(new TableInfo.Index("index_focus_mode_start_time_ms", false, Arrays.asList("start_time_ms"), Arrays.asList("ASC")));
        _indicesFocusMode.add(new TableInfo.Index("index_focus_mode_end_time_ms", false, Arrays.asList("end_time_ms"), Arrays.asList("ASC")));
        final TableInfo _infoFocusMode = new TableInfo("focus_mode", _columnsFocusMode, _foreignKeysFocusMode, _indicesFocusMode);
        final TableInfo _existingFocusMode = TableInfo.read(db, "focus_mode");
        if (!_infoFocusMode.equals(_existingFocusMode)) {
          return new RoomOpenHelper.ValidationResult(false, "focus_mode(com.soc.agent.database.entity.FocusModeEntity).\n"
                  + " Expected:\n" + _infoFocusMode + "\n"
                  + " Found:\n" + _existingFocusMode);
        }
        final HashMap<String, TableInfo.Column> _columnsDailyLimit = new HashMap<String, TableInfo.Column>(7);
        _columnsDailyLimit.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyLimit.put("package_name", new TableInfo.Column("package_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyLimit.put("app_name", new TableInfo.Column("app_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyLimit.put("limit_ms", new TableInfo.Column("limit_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyLimit.put("warning_threshold", new TableInfo.Column("warning_threshold", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyLimit.put("exceeded_action", new TableInfo.Column("exceeded_action", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyLimit.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDailyLimit = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDailyLimit = new HashSet<TableInfo.Index>(2);
        _indicesDailyLimit.add(new TableInfo.Index("index_daily_limit_package_name", true, Arrays.asList("package_name"), Arrays.asList("ASC")));
        _indicesDailyLimit.add(new TableInfo.Index("index_daily_limit_enabled", false, Arrays.asList("enabled"), Arrays.asList("ASC")));
        final TableInfo _infoDailyLimit = new TableInfo("daily_limit", _columnsDailyLimit, _foreignKeysDailyLimit, _indicesDailyLimit);
        final TableInfo _existingDailyLimit = TableInfo.read(db, "daily_limit");
        if (!_infoDailyLimit.equals(_existingDailyLimit)) {
          return new RoomOpenHelper.ValidationResult(false, "daily_limit(com.soc.agent.database.entity.DailyLimitEntity).\n"
                  + " Expected:\n" + _infoDailyLimit + "\n"
                  + " Found:\n" + _existingDailyLimit);
        }
        final HashMap<String, TableInfo.Column> _columnsWeeklyReport = new HashMap<String, TableInfo.Column>(17);
        _columnsWeeklyReport.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("week_start", new TableInfo.Column("week_start", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("week_end", new TableInfo.Column("week_end", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("total_screen_time_ms", new TableInfo.Column("total_screen_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("total_launches", new TableInfo.Column("total_launches", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("apps_used_count", new TableInfo.Column("apps_used_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("top_apps", new TableInfo.Column("top_apps", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("most_used_app", new TableInfo.Column("most_used_app", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("most_used_app_time_ms", new TableInfo.Column("most_used_app_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("peak_day", new TableInfo.Column("peak_day", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("peak_day_time_ms", new TableInfo.Column("peak_day_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("avg_daily_time_ms", new TableInfo.Column("avg_daily_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("peak_hour", new TableInfo.Column("peak_hour", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("notifications_count", new TableInfo.Column("notifications_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("focus_sessions_count", new TableInfo.Column("focus_sessions_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("focus_time_ms", new TableInfo.Column("focus_time_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeeklyReport.put("generated_at", new TableInfo.Column("generated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWeeklyReport = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWeeklyReport = new HashSet<TableInfo.Index>(2);
        _indicesWeeklyReport.add(new TableInfo.Index("index_weekly_report_week_start", true, Arrays.asList("week_start"), Arrays.asList("ASC")));
        _indicesWeeklyReport.add(new TableInfo.Index("index_weekly_report_generated_at", false, Arrays.asList("generated_at"), Arrays.asList("ASC")));
        final TableInfo _infoWeeklyReport = new TableInfo("weekly_report", _columnsWeeklyReport, _foreignKeysWeeklyReport, _indicesWeeklyReport);
        final TableInfo _existingWeeklyReport = TableInfo.read(db, "weekly_report");
        if (!_infoWeeklyReport.equals(_existingWeeklyReport)) {
          return new RoomOpenHelper.ValidationResult(false, "weekly_report(com.soc.agent.database.entity.WeeklyReportEntity).\n"
                  + " Expected:\n" + _infoWeeklyReport + "\n"
                  + " Found:\n" + _existingWeeklyReport);
        }
        final HashMap<String, TableInfo.Column> _columnsExportReport = new HashMap<String, TableInfo.Column>(9);
        _columnsExportReport.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("format", new TableInfo.Column("format", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("scope", new TableInfo.Column("scope", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("date_from", new TableInfo.Column("date_from", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("date_to", new TableInfo.Column("date_to", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("file_uri", new TableInfo.Column("file_uri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("file_size_bytes", new TableInfo.Column("file_size_bytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("record_count", new TableInfo.Column("record_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExportReport.put("exported_at", new TableInfo.Column("exported_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExportReport = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExportReport = new HashSet<TableInfo.Index>(1);
        _indicesExportReport.add(new TableInfo.Index("index_export_report_exported_at", false, Arrays.asList("exported_at"), Arrays.asList("ASC")));
        final TableInfo _infoExportReport = new TableInfo("export_report", _columnsExportReport, _foreignKeysExportReport, _indicesExportReport);
        final TableInfo _existingExportReport = TableInfo.read(db, "export_report");
        if (!_infoExportReport.equals(_existingExportReport)) {
          return new RoomOpenHelper.ValidationResult(false, "export_report(com.soc.agent.database.entity.ExportReportEntity).\n"
                  + " Expected:\n" + _infoExportReport + "\n"
                  + " Found:\n" + _existingExportReport);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "5216e4819da17fab796390802985bb55", "9d85de5922214342c5b8e443ed96ba09");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "devices","device_history","cpu_usage","memory_usage","storage_usage","battery_status","device_info","installed_apps","app_permissions","file_scan_logs","scan_runs","malware_scan_logs","threats","alerts","network_logs","iocs","locked_apps","policies","unlock_history","failed_attempts","intruder_selfies","security_settings","backups","daily_usage","app_usage","usage_sessions","weekly_usage","monthly_usage","screen_time","launch_count","notification_count","usage_timeline","focus_mode","daily_limit","weekly_report","export_report");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `devices`");
      _db.execSQL("DELETE FROM `device_history`");
      _db.execSQL("DELETE FROM `cpu_usage`");
      _db.execSQL("DELETE FROM `memory_usage`");
      _db.execSQL("DELETE FROM `storage_usage`");
      _db.execSQL("DELETE FROM `battery_status`");
      _db.execSQL("DELETE FROM `device_info`");
      _db.execSQL("DELETE FROM `installed_apps`");
      _db.execSQL("DELETE FROM `app_permissions`");
      _db.execSQL("DELETE FROM `file_scan_logs`");
      _db.execSQL("DELETE FROM `scan_runs`");
      _db.execSQL("DELETE FROM `malware_scan_logs`");
      _db.execSQL("DELETE FROM `threats`");
      _db.execSQL("DELETE FROM `alerts`");
      _db.execSQL("DELETE FROM `network_logs`");
      _db.execSQL("DELETE FROM `iocs`");
      _db.execSQL("DELETE FROM `locked_apps`");
      _db.execSQL("DELETE FROM `policies`");
      _db.execSQL("DELETE FROM `unlock_history`");
      _db.execSQL("DELETE FROM `failed_attempts`");
      _db.execSQL("DELETE FROM `intruder_selfies`");
      _db.execSQL("DELETE FROM `security_settings`");
      _db.execSQL("DELETE FROM `backups`");
      _db.execSQL("DELETE FROM `daily_usage`");
      _db.execSQL("DELETE FROM `app_usage`");
      _db.execSQL("DELETE FROM `usage_sessions`");
      _db.execSQL("DELETE FROM `weekly_usage`");
      _db.execSQL("DELETE FROM `monthly_usage`");
      _db.execSQL("DELETE FROM `screen_time`");
      _db.execSQL("DELETE FROM `launch_count`");
      _db.execSQL("DELETE FROM `notification_count`");
      _db.execSQL("DELETE FROM `usage_timeline`");
      _db.execSQL("DELETE FROM `focus_mode`");
      _db.execSQL("DELETE FROM `daily_limit`");
      _db.execSQL("DELETE FROM `weekly_report`");
      _db.execSQL("DELETE FROM `export_report`");
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
    _typeConvertersMap.put(DeviceDao.class, DeviceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TelemetryDao.class, TelemetryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppDao.class, AppDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ScanDao.class, ScanDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AlertDao.class, AlertDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NetworkDao.class, NetworkDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PolicyDao.class, PolicyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LockedAppDao.class, LockedAppDao_AppDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(UnlockHistoryDao.class, UnlockHistoryDao_AppDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(FailedAttemptDao.class, FailedAttemptDao_AppDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(IntruderSelfieDao.class, IntruderSelfieDao_AppDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(SecuritySettingsDao.class, SecuritySettingsDao_AppDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(BackupDao.class, BackupDao_AppDatabase_Impl.getRequiredConverters());
    _typeConvertersMap.put(DailyUsageDao.class, DailyUsageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppUsageDao.class, AppUsageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UsageSessionDao.class, UsageSessionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WeeklyUsageDao.class, WeeklyUsageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MonthlyUsageDao.class, MonthlyUsageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ScreenTimeDao.class, ScreenTimeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MostUsedAppsDao.class, MostUsedAppsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LaunchCountDao.class, LaunchCountDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NotificationCountDao.class, NotificationCountDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UsageTimelineDao.class, UsageTimelineDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FocusModeDao.class, FocusModeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DailyLimitDao.class, DailyLimitDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WeeklyReportDao.class, WeeklyReportDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExportReportDao.class, ExportReportDao_Impl.getRequiredConverters());
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
  public DeviceDao deviceDao() {
    if (_deviceDao != null) {
      return _deviceDao;
    } else {
      synchronized(this) {
        if(_deviceDao == null) {
          _deviceDao = new DeviceDao_Impl(this);
        }
        return _deviceDao;
      }
    }
  }

  @Override
  public TelemetryDao telemetryDao() {
    if (_telemetryDao != null) {
      return _telemetryDao;
    } else {
      synchronized(this) {
        if(_telemetryDao == null) {
          _telemetryDao = new TelemetryDao_Impl(this);
        }
        return _telemetryDao;
      }
    }
  }

  @Override
  public AppDao appDao() {
    if (_appDao != null) {
      return _appDao;
    } else {
      synchronized(this) {
        if(_appDao == null) {
          _appDao = new AppDao_Impl(this);
        }
        return _appDao;
      }
    }
  }

  @Override
  public ScanDao scanDao() {
    if (_scanDao != null) {
      return _scanDao;
    } else {
      synchronized(this) {
        if(_scanDao == null) {
          _scanDao = new ScanDao_Impl(this);
        }
        return _scanDao;
      }
    }
  }

  @Override
  public AlertDao alertDao() {
    if (_alertDao != null) {
      return _alertDao;
    } else {
      synchronized(this) {
        if(_alertDao == null) {
          _alertDao = new AlertDao_Impl(this);
        }
        return _alertDao;
      }
    }
  }

  @Override
  public NetworkDao networkDao() {
    if (_networkDao != null) {
      return _networkDao;
    } else {
      synchronized(this) {
        if(_networkDao == null) {
          _networkDao = new NetworkDao_Impl(this);
        }
        return _networkDao;
      }
    }
  }

  @Override
  public PolicyDao policyDao() {
    if (_policyDao != null) {
      return _policyDao;
    } else {
      synchronized(this) {
        if(_policyDao == null) {
          _policyDao = new PolicyDao_Impl(this);
        }
        return _policyDao;
      }
    }
  }

  @Override
  public LockedAppDao lockedAppDao() {
    if (_lockedAppDao != null) {
      return _lockedAppDao;
    } else {
      synchronized(this) {
        if(_lockedAppDao == null) {
          _lockedAppDao = new LockedAppDao_AppDatabase_Impl(this);
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
          _unlockHistoryDao = new UnlockHistoryDao_AppDatabase_Impl(this);
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
          _failedAttemptDao = new FailedAttemptDao_AppDatabase_Impl(this);
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
          _intruderSelfieDao = new IntruderSelfieDao_AppDatabase_Impl(this);
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
          _securitySettingsDao = new SecuritySettingsDao_AppDatabase_Impl(this);
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
          _backupDao = new BackupDao_AppDatabase_Impl(this);
        }
        return _backupDao;
      }
    }
  }

  @Override
  public DailyUsageDao dailyUsageDao() {
    if (_dailyUsageDao != null) {
      return _dailyUsageDao;
    } else {
      synchronized(this) {
        if(_dailyUsageDao == null) {
          _dailyUsageDao = new DailyUsageDao_Impl(this);
        }
        return _dailyUsageDao;
      }
    }
  }

  @Override
  public AppUsageDao appUsageDao() {
    if (_appUsageDao != null) {
      return _appUsageDao;
    } else {
      synchronized(this) {
        if(_appUsageDao == null) {
          _appUsageDao = new AppUsageDao_Impl(this);
        }
        return _appUsageDao;
      }
    }
  }

  @Override
  public UsageSessionDao usageSessionDao() {
    if (_usageSessionDao != null) {
      return _usageSessionDao;
    } else {
      synchronized(this) {
        if(_usageSessionDao == null) {
          _usageSessionDao = new UsageSessionDao_Impl(this);
        }
        return _usageSessionDao;
      }
    }
  }

  @Override
  public WeeklyUsageDao weeklyUsageDao() {
    if (_weeklyUsageDao != null) {
      return _weeklyUsageDao;
    } else {
      synchronized(this) {
        if(_weeklyUsageDao == null) {
          _weeklyUsageDao = new WeeklyUsageDao_Impl(this);
        }
        return _weeklyUsageDao;
      }
    }
  }

  @Override
  public MonthlyUsageDao monthlyUsageDao() {
    if (_monthlyUsageDao != null) {
      return _monthlyUsageDao;
    } else {
      synchronized(this) {
        if(_monthlyUsageDao == null) {
          _monthlyUsageDao = new MonthlyUsageDao_Impl(this);
        }
        return _monthlyUsageDao;
      }
    }
  }

  @Override
  public ScreenTimeDao screenTimeDao() {
    if (_screenTimeDao != null) {
      return _screenTimeDao;
    } else {
      synchronized(this) {
        if(_screenTimeDao == null) {
          _screenTimeDao = new ScreenTimeDao_Impl(this);
        }
        return _screenTimeDao;
      }
    }
  }

  @Override
  public MostUsedAppsDao mostUsedAppsDao() {
    if (_mostUsedAppsDao != null) {
      return _mostUsedAppsDao;
    } else {
      synchronized(this) {
        if(_mostUsedAppsDao == null) {
          _mostUsedAppsDao = new MostUsedAppsDao_Impl(this);
        }
        return _mostUsedAppsDao;
      }
    }
  }

  @Override
  public LaunchCountDao launchCountDao() {
    if (_launchCountDao != null) {
      return _launchCountDao;
    } else {
      synchronized(this) {
        if(_launchCountDao == null) {
          _launchCountDao = new LaunchCountDao_Impl(this);
        }
        return _launchCountDao;
      }
    }
  }

  @Override
  public NotificationCountDao notificationCountDao() {
    if (_notificationCountDao != null) {
      return _notificationCountDao;
    } else {
      synchronized(this) {
        if(_notificationCountDao == null) {
          _notificationCountDao = new NotificationCountDao_Impl(this);
        }
        return _notificationCountDao;
      }
    }
  }

  @Override
  public UsageTimelineDao usageTimelineDao() {
    if (_usageTimelineDao != null) {
      return _usageTimelineDao;
    } else {
      synchronized(this) {
        if(_usageTimelineDao == null) {
          _usageTimelineDao = new UsageTimelineDao_Impl(this);
        }
        return _usageTimelineDao;
      }
    }
  }

  @Override
  public FocusModeDao focusModeDao() {
    if (_focusModeDao != null) {
      return _focusModeDao;
    } else {
      synchronized(this) {
        if(_focusModeDao == null) {
          _focusModeDao = new FocusModeDao_Impl(this);
        }
        return _focusModeDao;
      }
    }
  }

  @Override
  public DailyLimitDao dailyLimitDao() {
    if (_dailyLimitDao != null) {
      return _dailyLimitDao;
    } else {
      synchronized(this) {
        if(_dailyLimitDao == null) {
          _dailyLimitDao = new DailyLimitDao_Impl(this);
        }
        return _dailyLimitDao;
      }
    }
  }

  @Override
  public WeeklyReportDao weeklyReportDao() {
    if (_weeklyReportDao != null) {
      return _weeklyReportDao;
    } else {
      synchronized(this) {
        if(_weeklyReportDao == null) {
          _weeklyReportDao = new WeeklyReportDao_Impl(this);
        }
        return _weeklyReportDao;
      }
    }
  }

  @Override
  public ExportReportDao exportReportDao() {
    if (_exportReportDao != null) {
      return _exportReportDao;
    } else {
      synchronized(this) {
        if(_exportReportDao == null) {
          _exportReportDao = new ExportReportDao_Impl(this);
        }
        return _exportReportDao;
      }
    }
  }
}
