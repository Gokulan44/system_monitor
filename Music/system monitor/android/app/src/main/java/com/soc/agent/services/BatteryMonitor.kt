package com.soc.agent.services

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.soc.agent.api.dto.BatterySample

/**
 * Samples battery state from the sticky ACTION_BATTERY_CHANGED broadcast.
 * No permission is required to read the sticky battery intent.
 */
class BatteryMonitor(private val context: Context) {

    fun sample(): BatterySample {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as? BatteryManager
        val sticky = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        val percent: Int
        val hasBattery: Boolean
        val charging: Boolean
        val status: String

        if (sticky != null) {
            val level = sticky.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = sticky.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            percent = if (level >= 0 && scale > 0) (level * 100 / scale).coerceIn(0, 100) else -1
            hasBattery = sticky.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false)
            val statusCode = sticky.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            charging = statusCode == BatteryManager.BATTERY_STATUS_CHARGING ||
                statusCode == BatteryManager.BATTERY_STATUS_FULL
            status = when (statusCode) {
                BatteryManager.BATTERY_STATUS_CHARGING -> "charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> "discharging"
                BatteryManager.BATTERY_STATUS_FULL -> "full"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "not_charging"
                else -> "unknown"
            }
        } else {
            // Fallback via BatteryManager API (API 21+).
            percent = batteryManager?.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) ?: -1
            hasBattery = percent >= 0
            val isCharging = batteryManager?.isCharging ?: false
            charging = isCharging
            status = if (isCharging) "charging" else "unknown"
        }

        // No public time-remaining extra exists on the broadcast; report -1
        // unless the vendor exposed one (some do via EXTRA_TIME_REMAINING).
        val timeRemaining = sticky?.getLongExtra("timeRemaining", -1L) ?: -1L

        return BatterySample(
            hasBattery = hasBattery,
            percent = percent,
            charging = charging,
            status = status,
            timeRemaining = timeRemaining
        )
    }
}