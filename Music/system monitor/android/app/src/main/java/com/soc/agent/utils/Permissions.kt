package com.soc.agent.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Small runtime-permission helper. The agent needs a handful of runtime
 * permissions (POST_NOTIFICATIONS, READ_EXTERNAL_STORAGE / READ_MEDIA_*,
 * ACCESS_FINE_LOCATION, ...) that must be requested from an Activity.
 */
object Permissions {

    private const val REQUEST_CODE = 0x5A1F // "SAFE"

    /**
     * Checks the given permissions and, if any are missing, launches the system
     * permission dialog for them.
     *
     * @return true when every permission is already granted (nothing requested),
     *         false when a request was launched (the result arrives via
     *         Activity.onRequestPermissionsResult).
     */
    fun requestIfNeeded(activity: Activity, vararg permissions: String): Boolean {
        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing.isEmpty()) return true
        ActivityCompat.requestPermissions(activity, missing.toTypedArray(), REQUEST_CODE)
        return false
    }

    /**
     * Convenience check without launching a dialog.
     */
    fun has(activity: Activity, permission: String): Boolean =
        ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
}
