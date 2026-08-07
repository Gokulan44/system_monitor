package com.soc.agent.data

/**
 * UI model for unlock history display. Maps from [UnlockHistoryEntity]
 * with computed fields for presentation.
 */
data class UnlockHistory(
    val id: Long = 0,
    val packageName: String,
    val gateType: String,
    val wasSuccessful: Boolean,
    val unlockDurationMs: Long,
    val timestampMs: Long,
    val appLabel: String = "",
    val failureReason: String = ""
) {
    /** Human-readable gate type. */
    val gateLabel: String
        get() = when (gateType) {
            "pin" -> "PIN"
            "pattern" -> "Pattern"
            "password" -> "Password"
            "biometric" -> "Biometric"
            else -> gateType.replaceFirstChar { it.uppercase() }
        }

    /** Status badge: "Unlocked" or "Failed". */
    val statusLabel: String
        get() = if (wasSuccessful) "Unlocked" else "Failed"

    /** Duration formatted as "Xm Ys" or "Xs". */
    val durationFormatted: String
        get() {
            if (unlockDurationMs <= 0) return ""
            val min = unlockDurationMs / 60000
            val sec = (unlockDurationMs % 60000) / 1000
            return if (min > 0) "${min}m ${sec}s" else "${sec}s"
        }

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
                    val sdf = java.text.SimpleDateFormat("MMM d", java.util.Locale.US)
                    sdf.format(java.util.Date(timestampMs))
                }
            }
        }

    companion object {
        /**
         * Create from a database entity. [appLabel] is resolved externally
         * (PackageManager) and passed in for display.
         */
        fun from(
            entity: com.soc.agent.database.entity.UnlockHistoryEntity,
            appLabel: String = ""
        ) = UnlockHistory(
            id = entity.id,
            packageName = entity.packageName,
            gateType = entity.gateMethod,
            wasSuccessful = true,
            unlockDurationMs = 0L,
            timestampMs = entity.timestamp,
            appLabel = if (appLabel.isNotEmpty()) appLabel else entity.appName
        )
    }
}