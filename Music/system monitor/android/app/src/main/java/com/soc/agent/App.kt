package com.soc.agent

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.soc.agent.data.SecurityRepository
import com.soc.agent.services.SyncWorker
import java.util.concurrent.TimeUnit

/**
 * Application entry point. Owns the singleton [SecurityRepository] and
 * schedules the periodic telemetry sync worker.
 *
 * SERVICES LAYER CONTRACT (implemented by AndroidSecurityAgent/services/):
 *  - SyncWorker(appContext, workerParams) : CoroutineWorker whose doWork()
 *    performs a heartbeat/policy pull via SecurityRepository.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        schedulePeriodicSync()
    }

    /**
     * Enqueue a 15-minute heartbeat WorkManager job. KEEP matches an
     * already-enqueued job rather than replacing it, so repeated app starts
     * never stack duplicate workers.
     */
    private fun schedulePeriodicSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            SYNC_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    companion object {
        private const val SYNC_WORK_NAME = "soc_agent_periodic_sync"

        /** Always set in onCreate — safe to access after app start. */
        lateinit var instance: App
            private set

        /** Shared repository instance for the whole application. */
        val repository: SecurityRepository by lazy {
            SecurityRepository.getInstance(instance)
        }
    }
}