# Android Security Agent — Architecture

The Android Security Agent is the mobile endpoint of the SOC platform. It
collects telemetry (CPU/memory/storage/battery/network), inventories installed
apps, scans for malware/EICAR/IOCs, enforces server-pushed device policies and
streams everything to the SOC server's `/api/agent/*` endpoints.

## Package map (`com.soc.agent`)

| Package             | Responsibility                                                     |
|---------------------|--------------------------------------------------------------------|
| `App.kt`            | `Application` subclass; owns the `SecurityRepository`; schedules the 15-min WorkManager sync |
| `data/`             | `SecurityRepository` facade, Room database + DAOs, Retrofit API client (owned by data agent) |
| `api/dto/`          | Wire DTOs (`CpuSample`, `MemorySample`, `StorageSample`, `BatterySample`, `DeviceInfoSample`, `NetworkSampleDto`, `InstalledAppDto`, `AppPermissionDto`, `ScanItemDto`, `ScanRequest`, `AlertDto`, `PolicyDto`) (owned by api agent) |
| `security/`         | `Sha256`, `Eicar`, `ThreatClassifier`, `IocMatcher`, `PhishingChecker`, `PolicyEngine`, `ApkScanner` |
| `services/`         | Collectors: `CpuMonitor`, `MemoryMonitor`, `StorageMonitor`, `BatteryMonitor`, `NetworkMonitor`, `DeviceInfoCollector`, `AppInventory`, `ScanService`, `AlertDispatcher`, `SyncWorker` |
| `utils/`            | `Prefs` (encrypted prefs), `Formatters`, `Permissions`             |
| `ui/`               | Activities/fragments/ViewModels (owned by ui agent)                |

## Data flow

```
 Collectors (services/)                  Room (local cache)                Retrofit (api/)
 ┌───────────────────────────┐          ┌──────────────────┐          ┌──────────────────────┐
 │ CpuMonitor ───────────────┼──write──▶│ CpuSampleEntity  │──flush──▶│ POST /api/agent/telemetry │
 │ MemoryMonitor ────────────┼─────────▶│ MemorySampleEntity│          │ POST /api/agent/apps       │
 │ StorageMonitor ───────────┼─────────▶│ StorageSampleEntity│         │ POST /api/agent/network    │
 │ BatteryMonitor ───────────┼─────────▶│ BatterySampleEntity│         │ POST /api/agent/scan       │
 │ NetworkMonitor ───────────┼─────────▶│ NetworkSampleEntity│         │ POST /api/agent/alerts     │
 │ DeviceInfoCollector ──────┼─────────▶│ DeviceInfoEntity  │          │ POST /api/agent/register   │
 │ AppInventory ─────────────┼─────────▶│ InstalledAppEntity│          │ GET  /api/agent/policies   │
 │ ScanService ──────────────┼─────────▶│ ScanItemEntity    │          │ GET  /api/agent/sync       │
 └───────────┬───────────────┘          └──────────────────┘          └───────────┬──────────────┘
             │                                                                     │
             └── SecurityRepository orchestrates: sample → persist → sync ────────┘
                          (X-API-Key + X-Agent-Id headers on every call)
```

`SyncWorker` (CoroutineWorker, 15-min flexible period, WorkManager) drives the
repository: `syncTelemetry()` → `syncApps()` → `syncNetwork()` → `heartbeat()`
→ `pullPolicies()`. Any failure returns `Result.retry()` and WorkManager backs
off. The server responds to `/policies` with `{ policies, blocklist }`, which
feeds `IocMatcher` (domains/urls/hashes) and `PolicyEngine`.

## Room tables (17, mirroring the server schema)

`devices`, `device_info`, `cpu_usage`, `memory_usage`, `storage_usage`,
`battery_status`, `installed_apps`, `app_permissions`, `network_logs`,
`file_scan_logs`, `malware_scan_logs`, `threats`, `phishing_logs`, `alerts`,
`scan_runs`, `dns_logs`, `connection_logs`.

Samples are written locally first (offline-tolerant) and flushed on the next
successful sync; `lastSync`/`lastError` are tracked in encrypted prefs.

## Security features

- **APK/OS scanning** — `ApkScanner` hashes `classes.dex` (64 KB chunked SHA-256),
  checks EICAR + remote IOC blocklist; `ThreatClassifier` buckets verdicts
  (malicious/high, malicious/medium, suspicious/low, clean/none).
- **Phishing heuristics** — `PhishingChecker` scores URLs (TLS, bare-IP hosts,
  `@` embeds, numeric hostnames, abuse-heavy TLDs, punycode, blocklist hits).
- **Policy enforcement** — `PolicyEngine` checks install allowlists, USB
  debugging, secure screen lock, camera restriction and device-admin presence
  against real device state (Settings.Global, KeyguardManager, DPM, PM).
- **Local IOC matching** — `IocMatcher` (case-insensitive substring) against
  server-synced domains/URLs/hashes.
- **Encrypted at rest** — all credentials in `EncryptedSharedPreferences`
  (MasterKey/AES-256-GCM) with graceful plain-prefs fallback.
- **Alerts** — `AlertDispatcher` posts a notification (channel `soc_alerts`)
  and returns an `AlertDto` that is also pushed to `/api/agent/alerts`.

## Pointing at a dev server

1. Start the platform: `cd server && npm start` (default `http://localhost:4000`).
2. Run the seed script once — it prints the Android agent API key:
   `cd server && node src/scripts/seed.js` → note the `soc_agent_…` key.
3. The seed binds the lab device to `agent_id = lab-android-9xk2`. Use that
   value as the agent id in the app settings (or let the app auto-generate one
   and register a new device via `POST /api/agent/register`).
4. In the app's connection settings enter:
   - Server URL: `http://10.0.2.2:4000` (emulator) or the LAN IP of the server
     machine (`http://192.168.x.x:4000` for a physical device).
   - API key: the `soc_agent_…` key from the seed output.
5. Cleartext HTTP must be allowed for dev: `android:usesCleartextTraffic="true"`
   in the manifest (or a `network_security_config.xml` permitting the dev host).

## Sync cadence

Telemetry, app inventory, network samples, heartbeat and policy pull run every
15 minutes (WorkManager flexible window, initial delay 30 s). Scans are
user-triggered from the UI (quick = installed APKs + public dirs; full = plus
system/private dirs, best-effort). Alerts push immediately when raised.
