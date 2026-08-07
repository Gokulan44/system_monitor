package com.soc.agent.security

import android.app.admin.DevicePolicyManager
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.util.Log
import com.soc.agent.api.dto.PolicyDto
import com.soc.agent.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Evaluates server-supplied device policies against the actual device state and
 * returns a list of human-readable violations.
 *
 * Supported policy_type values (see server device_policies.policy_type):
 *  - "install_restriction"  rules: { enabled, allowlist: List<String> }
 *  - "usb_debugging"        rules: { enabled }           (enforced OFF)
 *  - "screen_lock"          rules: { enabled }           (secure lock required)
 *  - "camera"               rules: { enabled }           (camera restricted)
 *  - "device_admin"         rules: { enabled }           (admin app required)
 *
 * Unknown policy types are ignored so the agent stays forward-compatible when
 * the server introduces new policy kinds.
 */
class PolicyEngine(
    private val context: Context,
    private val policies: List<PolicyDto>
) {

    /** Returns an empty list when the device is fully compliant. */
    fun evaluate(): List<String> =
        evaluateViolations().map { "${it.name}: ${it.detail}" }

    /**
     * Evaluates every policy in [policies] and returns a list of violations.
     * Exceptions raised by individual rule evaluators are logged and skipped
     * so a single bad policy won't break evaluation for the rest.
     */
    fun evaluateViolations(): List<PolicyViolation> {
        val violations = mutableListOf<PolicyViolation>()
        for (policy in policies) {
            try {
                violations += when (policy.policyType) {
                    "install_restriction" -> evalInstallRestriction(policy)
                    "usb_debugging" -> evalUsbDebugging(policy)
                    "screen_lock" -> evalScreenLock(policy)
                    "camera" -> evalCamera(policy)
                    "device_admin" -> evalDeviceAdmin(policy)
                    else -> emptyList()
                }
            } catch (e: Exception) {
                Log.w("PolicyEngine", "Failed to evaluate policy '${policy.policyType}': ${e.message}")
            }
        }
        return violations
    }

    companion object {
        /**
         * Convenience entry point used by the dashboard: loads the locally
         * cached policies from Room and evaluates them against device state.
         */
        fun violations(context: Context): List<PolicyViolation> = runBlocking(Dispatchers.IO) {
            val entities = runCatching {
                AppDatabase.getInstance(context).policyDao().getPolicies()
            }.getOrElse { emptyList() }
            val dtos = entities.map { entity ->
                PolicyDto(id = entity.id, name = entity.name, policyType = entity.policyType, rules = entity.rules)
            }
            PolicyEngine(context, dtos).evaluateViolations()
        }
    }

    // --- install_restriction -------------------------------------------------
    private fun evalInstallRestriction(p: PolicyDto): List<PolicyViolation> {
        if (!(p.rules["enabled"] as? Boolean ?: true)) return emptyList()
        val allowlist = rulesListOf(p.rules["allowlist"]).toSet()
        if (allowlist.isEmpty()) return emptyList()

        val installed = installedPackages()
        val nonAllowed = installed - allowlist
        return if (nonAllowed.isEmpty()) {
            emptyList()
        } else {
            listOf(
                PolicyViolation(
                    name = label(p),
                    detail = "${nonAllowed.size} app(s) installed that are not on the " +
                        "allowlist: ${nonAllowed.take(5).joinToString(", ")}"
                )
            )
        }
    }

    // --- usb_debugging -------------------------------------------------------
    private fun evalUsbDebugging(p: PolicyDto): List<PolicyViolation> {
        val enforce = p.rules["enabled"] as? Boolean ?: true
        if (!enforce) return emptyList()
        val adbEnabled = try {
            Settings.Global.getInt(context.contentResolver, Settings.Global.ADB_ENABLED, 0) == 1
        } catch (e: Exception) {
            false
        }
        return if (adbEnabled) {
            listOf(
                PolicyViolation(
                    name = label(p),
                    detail = "USB debugging (ADB) is enabled but policy requires it disabled"
                )
            )
        } else {
            emptyList()
        }
    }

    // --- screen_lock ----------------------------------------------------------
    private fun evalScreenLock(p: PolicyDto): List<PolicyViolation> {
        val require = p.rules["enabled"] as? Boolean ?: true
        if (!require) return emptyList()
        val km = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val hasSecureLock = try { km.isDeviceSecure } catch (e: Exception) { false }
        return if (hasSecureLock) {
            emptyList()
        } else {
            listOf(
                PolicyViolation(
                    name = label(p),
                    detail = "device has no secure screen lock (a PIN/pattern/biometric is required)"
                )
            )
        }
    }

    // --- camera ---------------------------------------------------------------
    private fun evalCamera(p: PolicyDto): List<PolicyViolation> {
        val restrict = p.rules["enabled"] as? Boolean ?: true
        if (!restrict) return emptyList()
        val dpm = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as? DevicePolicyManager
        val activeAdmins = dpm?.activeAdmins ?: emptyList()
        val cameraDisabled = activeAdmins.any { admin ->
            try { dpm?.getCameraDisabled(admin) ?: false } catch (e: Exception) { false }
        }
        val hasCamera = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
        return if (hasCamera && !cameraDisabled) {
            listOf(
                PolicyViolation(
                    name = label(p),
                    detail = "camera is present but not restricted by a Device Admin as the policy requires"
                )
            )
        } else {
            emptyList()
        }
    }

    // --- device_admin ---------------------------------------------------------
    private fun evalDeviceAdmin(p: PolicyDto): List<PolicyViolation> {
        val require = p.rules["enabled"] as? Boolean ?: true
        if (!require) return emptyList()
        val dpm = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as? DevicePolicyManager
        val hasActiveAdmin = dpm != null && !dpm.activeAdmins.isNullOrEmpty()
        return if (hasActiveAdmin) {
            emptyList()
        } else {
            listOf(
                PolicyViolation(
                    name = label(p),
                    detail = "requires a Device Admin app, but none is active on this device"
                )
            )
        }
    }

    // --- helpers --------------------------------------------------------------
    private fun label(p: PolicyDto): String =
        if (p.name.isNotBlank()) p.name else p.policyType
    private fun installedPackages(): Set<String> {
        return try {
            context.packageManager.getInstalledApplications(PackageManager.MATCH_ALL)
                .map { it.packageName }.toSet()
        } catch (e: Exception) {
            emptySet()
        }
    }

    private fun rulesListOf(value: Any?): List<String> = when (value) {
        is List<*> -> value.mapNotNull { it?.toString() }
        is String -> if (value.isBlank()) emptyList() else value.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        else -> emptyList()
    }
}