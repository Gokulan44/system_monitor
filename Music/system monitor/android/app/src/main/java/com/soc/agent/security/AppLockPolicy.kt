package com.soc.agent.security

import android.content.Context
import com.soc.agent.database.AppDatabase
import com.soc.agent.utils.Prefs

/**
 * Decision engine for the App Lock module.
 *
 * Centralises "should this foreground app be gated right now?" so the
 * watcher service ([com.soc.agent.services.AppLockService]) and the dashboard
 * agree on one rule set. A package needs a gate when:
 *  - the module is enabled ([Prefs.appLockEnabled]),
 *  - the package is in the enabled locked set, and
 *  - it isn't excluded (_EXCLUDED cover the agent itself, the launcher and a
 *    small hard-coded set that must never trap the user).
 *
 * This is pure logic — no blocking I/O beyond the cached DAO queries — and is
 * safe to call from a service polling loop.
 */
object AppLockPolicy {

    /** Packages that must never be gated (would lock the user out of core UI). */
    val RESERVED = setOf(
        "android",
        "com.android.systemui",
        "com.android.settings",
        "com.google.android.apps.nexuslauncher",
        "com.android.launcher",
        "com.soc.agent"
    )

    /**
     * True when the given foreground package currently needs a lock gate.
     * [enabledPackages] is the pre-fetched locked set (hot-set) to avoid a DB
     * hit on every poll; passing it keeps the watcher cheap.
     */
    fun shouldGate(
        context: Context,
        packageName: String,
        enabledPackages: Set<String>
    ): Boolean {
        if (!Prefs.appLockEnabled) return false
        if (packageName.isBlank()) return false
        if (packageName in RESERVED) return false
        if (packageName !in enabledPackages) return false
        if (!isConfigured()) return false // no gate configured yet -> nothing to compare
        return true
    }

    /**
     * The gate type to present: "pin", "pattern", "password", "biometric", or null when no usable gate
     * is configured. Falls back to pin when a pattern was the selection but
     * none is stored (and vice versa).
     */
    fun gateFor(): String? {
        if (!isConfigured()) return null
        return when (Prefs.lockMethod) {
            "pattern" -> if (PatternLock.isConfigured()) "pattern" else if (Prefs.pinHash.isNotBlank()) "pin" else if (Prefs.passwordHash.isNotBlank()) "password" else if (Prefs.biometricEnabled) "biometric" else null
            "pin" -> if (Prefs.pinHash.isNotBlank()) "pin" else if (PatternLock.isConfigured()) "pattern" else if (Prefs.passwordHash.isNotBlank()) "password" else if (Prefs.biometricEnabled) "biometric" else null
            "password" -> if (Prefs.passwordHash.isNotBlank()) "password" else if (Prefs.pinHash.isNotBlank()) "pin" else if (PatternLock.isConfigured()) "pattern" else if (Prefs.biometricEnabled) "biometric" else null
            "biometric" -> if (Prefs.biometricEnabled) "biometric" else if (Prefs.passwordHash.isNotBlank()) "password" else if (Prefs.pinHash.isNotBlank()) "pin" else if (PatternLock.isConfigured()) "pattern" else null
            else -> when {
                Prefs.biometricEnabled -> "biometric"
                Prefs.passwordHash.isNotBlank() -> "password"
                Prefs.pinHash.isNotBlank() -> "pin"
                PatternLock.isConfigured() -> "pattern"
                else -> null
            }
        }
    }

    /** True when any unlock gate (PIN, pattern, password or biometric) is configured. */
    fun isConfigured(): Boolean =
        Prefs.pinHash.isNotBlank() || PatternLock.isConfigured() || Prefs.passwordHash.isNotBlank() || Prefs.biometricEnabled

    /** Loads the hot-set of enabled locked package names for the watcher. */
    suspend fun loadEnabledPackages(context: Context): Set<String> =
        AppDatabase.getInstance(context.applicationContext)
            .lockedAppDao()
            .getEnabledPackages()
            .toSet()
}