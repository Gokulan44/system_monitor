package com.soc.agent.services

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.soc.agent.data.SecurityRepository

class SyncWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val repository = SecurityRepository.getInstance(applicationContext)
        return try {
            repository.syncTelemetry()
            repository.syncApps()
            repository.syncNetwork()
            repository.heartbeat()
            repository.pullPolicies()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}