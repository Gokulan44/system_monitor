package com.soc.agent.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.soc.agent.data.SecurityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Lifecycle of a scan run as surfaced to the UI. */
enum class ScanStatus { IDLE, RUNNING, COMPLETED, ERROR }

/**
 * Live progress of a scan run. The same instance is shared across the Scan
 * Center, the Quick Scan screen and the APK scanner via an activity-scoped
 * [ScanViewModel], so the hub can render the progress indicator while a scan
 * runs on another screen.
 */
data class ScanProgress(
    val status: ScanStatus = ScanStatus.IDLE,
    val itemsScanned: Int = 0,
    val threatsFound: Int = 0,
    val score: Int = 0,
    val grade: String = "",
    val message: String = ""
)

/**
 * Drives scan runs through [SecurityRepository.runScan] and publishes the
 * result as a [StateFlow] of [ScanProgress]. The repository performs the actual
 * scanning (via ScanService) and syncs the run + detections to the SOC server;
 * this view model only brokers status, counters and the final score/grade.
 */
class ScanViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SecurityRepository.getInstance(application)

    private val _progress = MutableStateFlow<ScanProgress?>(null)
    val progress: StateFlow<ScanProgress?> = _progress.asStateFlow()

    /** Runs a scan of the given type ("quick" or "full") and updates [progress]. */
    fun runScan(scanType: String) {
        viewModelScope.launch {
            _progress.value = ScanProgress(
                status = ScanStatus.RUNNING,
                message = "Starting $scanType scan…"
            )
            try {
                val response = repository.runScan(scanType)
                _progress.value = ScanProgress(
                    status = ScanStatus.COMPLETED,
                    itemsScanned = response.itemsScanned,
                    threatsFound = response.threatsFound,
                    score = response.score,
                    grade = response.grade,
                    message = "Scan complete"
                )
            } catch (e: Exception) {
                _progress.value = ScanProgress(
                    status = ScanStatus.ERROR,
                    message = e.message ?: "Scan failed"
                )
            }
        }
    }
}
