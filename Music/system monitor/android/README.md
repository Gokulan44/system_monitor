# Android Security Agent

Android client for the **SOC platform** (Security Operations Center). This app turns an Android device
into a monitored endpoint: it registers with the SOC server, pushes live telemetry (CPU / memory /
storage / battery / network), reports installed applications, runs malware scans against a local
IOC (Indicators of Compromise) blocklist, analyzes individual APK files, and surfaces policy
violations — all viewable through a dark SOC-themed dashboard.

The server side lives in the sibling `server/` directory (Node + Express + better-sqlite3) and the
SOC web console in `client/`.

---

## What the app does

| Screen | Purpose |
| ------ | ------- |
| **Login** | First-run configuration: SOC server URL, API key, device name → registers the device. |
| **PIN lock** | Optional local gate (4–6 digit PIN, SHA-256 hashed, biometric fallback) shown on subsequent launches. |
| **Home / Dashboard** | Security grade + score from the server, quick stats, refresh, policy violations, blocklist counts. |
| **Device** | Tabbed monitor: device info, live battery, storage, network (Device / CPU / Memory tabs). |
| **Security** | Tabbed: installed Apps with risk badges, Permissions hotspots, Threats, Alerts. |
| **Scan** | Scan hub: Quick Scan, Full Scan, and a standalone APK Scanner. |

---

## Architecture overview

```
UI layer (this package)                Data / engines (other agents)
─────────────────────────              ─────────────────────────────────
LoginActivity ────────────────┐        SecurityRepository  — REST client to /api/agent/*
   │ register() + store creds │        Prefs               — encrypted shared prefs (url, key, agentId, pin…)
   ▼                           │        AppDatabase (Room)  — threats, alerts, scans, telemetry history
PinLockActivity (biometric)    │        PolicyEngine        — policy violation checks
   │      ┌────────────────────┘        IocMatcher          — hash/domain/URL/IP blocklist match
   ▼      ▼                            ScanService          — quick/full package enumeration
MainActivity (BottomNav)               ApkScanner           — single-APK static analysis
 ├─ DashboardFragment  ─ DashboardViewModel → heartbeat()+syncTelemetry(), PolicyEngine, IocMatcher
 ├─ DeviceMonitorFragment               DeviceInfoCollector / StorageMonitor / NetworkMonitor /
 │    ├─ CpuFragment                          CpuMonitor / MemoryMonitor / AppInventory
 │    └─ MemoryFragment ─ TelemetryDao.getMemoryHistory()
 ├─ SecurityMonitorFragment (TabLayout + ViewPager2)
 │    ├─ Apps / Permissions / Threats(ScanDao) / Alerts(AlertDao)
 └─ ScanCenterFragment ─ ScanViewModel (activity-scoped, shared progress)
      ├─ QuickScanFragment   → repository.runScan("quick")
      └─ ApkScannerFragment  → ApkScanner.scanApk + repository.runScan(apk item)
```

Layered responsibilities:

- **`ui/`** — Activities, fragments, adapters, view models. View binding everywhere. Fragments never
  touch HTTP directly; they go through `SecurityRepository` for server calls and Room DAOs for
  persisted data.
- **`data/`** — `SecurityRepository` (singleton). Owns the HTTP client, attaches the
  `X-API-Key` / `X-Agent-Id` headers, and exposes `register`, `heartbeat`, `syncTelemetry`,
  `syncApps`, `syncNetwork`, `runScan`, `pushAlerts`, `pullPolicies`.
- **`services/`** — `DeviceInfoCollector`, `StorageMonitor`, `NetworkMonitor`, `CpuMonitor`,
  `MemoryMonitor`, `AppInventory`. All collectors expose a typed sample for easy rendering.
- **`security/`** — `PolicyEngine.violations()`, `IocMatcher` (blocklist + counts), `ApkScanner`,
  `Sha256` (used by the PIN flow). `ScanService` drives bulk package scans.
- **`database/`** — Room `AppDatabase`, DAOs (`ScanDao`, `AlertDao`, `TelemetryDao`, …) and entities.
- **`utils/`** — `Prefs`, `Formatters`, `SessionManager`.

All long-running work (network, disk, package manager) runs on `Dispatchers.IO` from
`lifecycleScope` / `viewModelScope` coroutines. The custom history chart (`CpuHistoryView`) is a
plain `View` drawing a 60-sample line + fill.

---

## How it syncs to the SOC server

Every endpoint is mounted at `/api` and requires the **`X-API-Key`** header (the *android-agent*
key). Device identity is carried by the **`X-Agent-Id`** header (the device’s stable UUID) or the
`agent_id` body field. Only `register` is allowed before an agent id exists.

| Method | Path | App method | Purpose |
| ------ | ---- | ---------- | ------- |
| `POST` | `/api/agent/register` | `repository.register()` | First contact; upserts the device row keyed by `agent_id`. |
| `POST` | `/api/agent/telemetry` | `repository.syncTelemetry()` | Push CPU / memory / storage / battery / device info snapshot. |
| `POST` | `/api/agent/apps` | `repository.syncApps()` | Push installed-app inventory (names, packages, risk, mem). |
| `POST` | `/api/agent/network` | `repository.syncNetwork()` | Push WiFi SSID / IP / VPN / rx–tx rates. |
| `POST` | `/api/agent/scan` | `repository.runScan()` | Push a scan run (`quick` / `full` / `apk` + items). Server recomputes the device risk score and returns it. |
| `POST` | `/api/agent/alerts` | `repository.pushAlerts()` | Push local alert events. |
| `GET` | `/api/agent/policies` | `repository.pullPolicies()` | Pull enabled policies for local evaluation. |
| `GET` | `/api/agent/sync` | `repository.heartbeat()` | Full sync/state incl. recomputed security score + grade. |

Beating `heartbeat()` marks the device **online** on the server and returns the authoritative
`score` / `grade` shown on the dashboard scorecard.

> **IOC blocklist:** the server ships seeded IOCs (EICAR hash, WannaCry, C2 IPs, phishing domains).
> The app keeps a mirrored local blocklist in `IocMatcher` — hashes, domains, URLs and IPs — which
> both the APK scanner and package scan match against. `IocMatcher.blocklistCounts()` is surfaced on
> the dashboard.

---

## Setup

### 1. Start the SOC server

```bash
cd server
npm install
npm run seed   # first time only — see API key below
npm run dev    # or: npm start
```

The server listens on **port 4000** (`http://localhost:4000`, default). The app default field is
`http://192.168.1.100:4000` — change it to your machine’s LAN IP so a phone on the same network can
reach it.

### 2. Get the API key

The seed script prints the Android agent API key on the **first** run, in the form
`soc_agent_<48 hex characters>`:

```text
created API key: android-agent -> soc_agent_abc123…def
```

If you missed it, look it up in the SQLite database (`server/data/*.db` — table `api_keys`,
column `key_hash` is a SHA-256 of the key, so you can’t recover it after the fact). Subsequent seed
runs will only print the prefix (`soc_agent_…`). Best practice: wipe the DB and re-seed, or insert a
new key — keep the printed value somewhere safe.

> Keep the API key secret. It authenticates all agent traffic. Do **not** commit it to git.
> The seed also creates demo web users — `admin@soc.local / admin123`, `analyst@soc.local /
> analyst123` — for the web console, unrelated to the agent key.

### 3. Build & run the app (Android Studio)

1. Open the `android/` folder in **Android Studio** (Hedgehog or newer).
2. Let Gradle sync (Kotlin 1.9.24, AGP 8.4.0, compileSdk 34, minSdk 26). No special plugins beyond
   Material Components and the standard Android toolchain are required.
3. Connect a device/emulator (API 26+) and press **Run**.

> **Cleartext HTTP.** The app talks to the SOC server over plain `http://`. On API 28+ cleartext is
> blocked by default, so the manifest must opt in for debug (and, for lab use, release). Confirm the
> manifest declares `android:usesCleartextTraffic="true"` (or a network security config allowing the
> server host) while testing against your local server.

### 4. First-run login

1. Launch `LoginActivity`.
2. Set the **Server URL** (default `http://192.168.1.100:4000`), paste the **API key**, and give the
   device a **name**.
3. Tap **Connect**. The app calls `POST /api/agent/register` with a stable `agent_id` you generate
   on this run. On success it stores the credentials in `Prefs` and jumps to the dashboard.
4. Optionally enable a **PIN lock** during login; from then on every launch is gated by `PinLockActivity`.

---

## Screen guide

### Login (`ui/LoginActivity.kt`)
Dark themed. Validates the three fields (URL must be `http(s)://`, API key non-empty, name
non-empty), shows a progress spinner and inline errors, then registers and starts `MainActivity`.
Offers an optional “Set PIN” step that opens `PinLockActivity` in setup mode. A hint explains where
the API key comes from (`npm run seed` on the server).

### PIN lock (`ui/PinLockActivity.kt`)
4–6 digits. `Prefs.pinHash` stores `SHA-256(pin)`. When a PIN is configured the lock verifies it; if
biometrics are enabled a `BiometricPrompt` (BIOMETRIC_STRONG) fallback is offered. In **setup mode**
the user chooses a new PIN; if none is configured the activity finishes straight through.

### Main (`ui/MainActivity.kt`)
Single activity with a bottom `BottomNavigationView` and four tabs. Fragments are switched with
`add` / `hide` / `show` transactions so each preserves its state across tab changes.

### Dashboard (`ui/dashboard/DashboardFragment.kt`)
- Big **grade + score** card colored by score (green ≥ 85, amber ≥ 70, red else) from the latest
  `SyncResponse`.
- Quick stat grid: CPU load, memory %, battery %, storage %, threats count, apps count, last-sync
  relative time.
- **Refresh** → `repository.heartbeat()` + `syncTelemetry()`, reevaluates `PolicyEngine.violations()`
  and `IocMatcher.blocklistCounts()`.

### Device (`ui/device/`)
- `DeviceMonitorFragment`: Device Info (manufacturer / model / Android / security patch / uptime),
  live Battery (Broadcast `ACTION_BATTERY_CHANGED`), Storage (internal/external bars), Network
  (SSID, IP, VPN, rx/tx rates), plus tabs to CPU and Memory.
- `CpuFragment`: big current load, temperature, GHz speed, per-core bars, and a live 60-sample
  history drawn by the custom `CpuHistoryView`.
- `MemoryFragment`: used/total/free, usage bar, swap bar, and a history chart fed from
  `TelemetryDao.getMemoryHistory()` stored samples.

### Security (`ui/security/` — TabLayout + ViewPager2)
- **Apps**: `AppInventory` installed apps in a `RecyclerView` (`AppAdapter`) with icon, package, mem,
  and colored risk badge. Off the IO thread.
- **Permissions**: aggregates granted Android permissions across installed packages by group, with a
  proportional bar per group.
- **Threats**: `ThreatEntity` rows from `ScanDao`.
- **Alerts**: `AlertEntity` rows from `AlertDao`.

### Scan (`ui/scan/`)
- **Scan Center**: three action cards (Quick / Full / each pushes scanned results to the Security
  tab), last-scan summary from `ScanRunEntity`, and a live progress indicator driven by the shared
  `ScanViewModel`.
- **Quick Scan**: `repository.runScan("quick")` with an items-scanned counter and a result card
  (score, grade, threats, malicious items list).
- **APK Scanner**: pick an `.apk` via `ACTION_GET_CONTENT`, copies it to cache, runs
  `ApkScanner.scanApk`, and pushes a single-item `ScanRequest` through `repository.runScan`. Shows
  verdict / severity / SHA-256 hash, color coded (red / amber / green).

---

## Room tables (database layer, `database/`)

Owned by a teammate agent; the UI reads these via the DAO interfaces:

- **`ThreatEntity` / `ScanDao`** — scan detections: `title`, `severity`, `detail`, `timestamp`;
  `getThreats()`, `getRecentRuns()`, `getHistory()`.
- **`AlertEntity` / `AlertDao`** — alerts: `title`, `level`, `message`, `timestamp`; `getAlerts()`.
- **`ScanRunEntity` / `ScanDao`** — one row per scan: `scanType`, `status`, `itemsScanned`,
  `threatsFound`, `startedAt`; `getRecentRuns()`.
- **`TelemetryHistoryEntity` / `TelemetryDao`** — stored monitor samples; `getMemoryHistory()`
  (list of `{ usagePct, capturedAt }`).

---

## Security features

- **Device registration** — stable `agent_id` reported to the server; duplicates merge, not duplicate.
- **API-key auth** — every request carries `X-API-Key`; rejected by the server without a valid key.
- **Optional local PIN + biometrics** — PIN hashed with SHA-256 in `Prefs`; biometric prompt
  fallback (`BIOMETRIC_STRONG`) when enrolled.
- **On-device IOC matching** — local blocklist catches hashes/domains/URLs/IPs before anything
  reaches the server.
- **APK fingerprinting** — SHA-256 + APIL analysis of an .APK you choose; verdicts surfaced with
  color.
- **Alerting** — local alert rows forwarded to the server via `pushAlerts`.
- **No placeholders** — every screen binds real service/repository data; no mocks, stubs, or fake
  numbers.

---

## Troubleshooting

**App says "Cannot connect"** — confirm the URL is reachable (open it in a phone browser), the server
is bound to `0.0.0.0`, and both devices are on the same LAN. Check cleartext is permitted
(`android:usesCleartextTraffic="true"` in the manifest).

**"API key required" (401)** — the key isn’t being sent or is wrong. Confirm you copied the full
`soc_agent_…` key from the seed output, and that the server DB still has an enabled `android-agent`
row.

**"Unknown agent id. Call POST /api/agent/register first."** — a sync ran before registration.
Normally the app registers on login; if the local store was wiped, log in again.

**Score stays 0 / "--"** — the server needs a recent heartbeat to recompute state. Pull-to-refresh
on the dashboard or tap Refresh.

**APK analyzer returns no results** — the picker can also return plain files; the app copies the URI
to cache and analyzes it. If the file isn’t a valid APK the scanner reports it rather than guessing.

**Port 4000 —** change the server port in `server/src/config.js` … then update the Server URL in
the app login screen to match.

Always check `adb logcat` for exceptions; failures are also surfaced inline on each screen.