package com.soc.agent.data

import java.text.SimpleDateFormat
import java.util.*

/**
 * UI model for intruder selfie/log display. Represents a failed unlock attempt
 * where the intruder selfie was captured (with user consent).
 */
data class IntruderLog(
    val id: Long = 0,
    val packageName: String,
    val timestampMs: Long,
    val failedGateType: String,
    val attemptCount: Int,
    val selfieUri: String? = null,
    val appLabel: String = "",
    val deviceInfo: String = ""
) {
    /** Human-readable gate type that was attempted. */
    val gateLabel: String
        get() = when (failedGateType) {
            "pin" -> "PIN"
            "pattern" -> "Pattern"
            "password" -> "Password"
            "biometric" -> "Biometric"
            else -> failedGateType.replaceFirstChar { it.uppercase() }
        }

    /** Status line: "3 failed PIN attempts". */
    val statusLabel: String
        get() = "$attemptCount failed ${gateLabel} attempt${if (attemptCount != 1) "s" else ""}"

    /** Whether a selfie was captured. */
    val hasSelfie: Boolean
        get() = !selfieUri.isNullOrBlank()

    /** Relative time label: "Just now", "5m ago", "2h ago", "Yesterday", date. */
    val timeLabel: String
        get() {
            val now = System.currentTimeMillis()
            val diff = now - timestampMs
            return when {
                diff < 60_000 -> "Just now"
                diff < 3_600_000 -> "${diff / 60_000}m ago"
                diff < 86_400_000 -> "${diff / 3_600_000}h ago"
                diff < 172_800_000 -> "Yesterday"
                else -> {
                    val sdf = SimpleDateFormat("MMM d, h:mm a", Locale.US)
                    sdf.format(Date(timestampMs))
                }
            }
        }

    /** Full date-time for detail view. */
    val dateTimeLabel: String
        get() {
            val sdf = SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a", Locale.US)
            return sdf.format(Date(timestampMs))
        }

    /** Summary line for list display. */
    val summaryLabel: String
        get() {
            val base = "$statusLabel — $appLabel"
            return if (hasSelfie) "$base 📷" else base
        }

    companion object {
        /**
         * Create from a database entity. [appLabel] is resolved externally
         * via PackageManager.
         */
        fun from(
            entity: IntruderLogEntity,
            appLabel: String = ""
        ) = IntruderLog(
            id = entity.id,
            packageName = entity.packageName,
            timestampMs = entity.timestampMs,
            failedGateType = entity.failedGateType,
            attemptCount = entity.attemptCount,
            selfieUri = entity.selfieUri,
            appLabel = appLabel
        )
    }
}

/**
 * Database entity for intruder selfie log. Stores failed unlock attempts
 * with optional selfie capture (requires user consent in Security Settings).
 */
data class IntruderLogEntity(
    val id: Long = 0,
    val packageName: String,
    val timestampMs: Long,
    val failedGateType: String,
    val attemptCount: Int,
    val selfieUri: String? = null,
    val deviceInfo: String = ""
)