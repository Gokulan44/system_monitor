package com.soc.agent.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.soc.agent.data.SecurityRepository
import com.soc.agent.database.AppDatabase
import com.soc.agent.security.IocMatcher
import com.soc.agent.security.PolicyEngine
import com.soc.agent.security.PolicyViolation
import com.soc.agent.services.AppInventory
import com.soc.agent.services.CpuMonitor
import com.soc.agent.services.MemoryMonitor
import com.soc.agent.services.StorageMonitor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Everything the Home/Dashboard screen shows in one immutable snapshot.
 */
data class DashboardState(
    val loading: Boolean = false,
    val score: Int = 0,
    val grade: String = "--",
    val cpuLoad: Float = 0f,
    val memPct: Float = 0f,
    val batteryPct: Int = -1,
    val storagePct: Float = 0f,
    val threats: Int = 0,
    val apps: Int = 0,
    val lastSync: Long = 0L,
    val violations: List<PolicyViolation> = emptyList(),
    val blocklist: Map<String, Int> = emptyMap(),
    val error: String? = null
)

/**
 * Aggregates the dashboard snapshot: server heartbeat (score/grade from
 * [SecurityRepository.heartbeat]), a telemetry push, local CPU/memory/storage
 * samples, battery level, persisted threat & app counts, PolicyEngine
 * violations and IocMatcher blocklist counts.
 */
class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SecurityRepository.getInstance(application)
    private val database = AppDatabase.getInstance(application)

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    /** Pulls the full dashboard snapshot. Safe to call repeatedly. */
    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            try {
                // Server-side data first: heartbeat carries the recomputed
                // security score + grade, then push current telemetry.
                val sync = repository.heartbeat()
                repository.syncTelemetry()

                val cpu = withContext(Dispatchers.IO) { CpuMonitor(getApplication()).sample() }
                val mem = withContext(Dispatchers.IO) { MemoryMonitor(getApplication()).sample() }
                val storage = withContext(Dispatchers.IO) {
                    StorageMonitor(getApplication()).sample().firstOrNull()
                }
                val batteryPct = readBatteryPct(getApplication())
                val threatCount = withContext(Dispatchers.IO) {
                    database.scanDao().getThreats().size
                }
                val appCount = withContext(Dispatchers.IO) {
                    AppInventory.scan(getApplication()).size
                }
                val violations = withContext(Dispatchers.IO) {
                    PolicyEngine.violations(getApplication())
                }
                val blocklist = withContext(Dispatchers.IO) {
                    IocMatcher.blocklistCounts()
                }

                _state.value = DashboardState(
                    loading = false,
                    score = sync.score,
                    grade = sync.grade,
                    cpuLoad = cpu.loadPct.toFloat(),
                    memPct = mem.usagePct.toFloat(),
                    batteryPct = batteryPct,
                    storagePct = (storage?.usagePct ?: 0.0).toFloat(),
                    threats = threatCount,
                    apps = appCount,
                    lastSync = System.currentTimeMillis(),
                    violations = violations,
                    blocklist = blocklist,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    loading = false,
                    error = e.message ?: "Refresh failed",
                    lastSync = System.currentTimeMillis()
                )
            }
        }
    }

    /** Reads the current battery percentage from the sticky battery-changed intent. */
    private fun readBatteryPct(context: Context): Int {
        return try {
            val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Context.RECEIVER_NOT_EXPORTED
            } else {
                0
            }
            val intent = context.registerReceiver(
                null, IntentFilter(Intent.ACTION_BATTERY_CHANGED), flags
            )
            val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
            val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, 100) ?: 100
            if (level < 0 || scale <= 0) -1 else (level * 100) / scale
        } catch (e: Exception) {
            -1
        }
    }
}
