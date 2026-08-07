package com.soc.agent.ui.settings

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

/**
 * Helper object providing permission status checks for PrivacyFragment.
 */
object PrivacyAuditHelper {

    fun cameraStatusText(context: Context): String {
        return if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) "Granted" else "Not granted"
    }

    fun locationStatusText(context: Context): String {
        return if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) "Granted" else "Not granted"
    }

    fun storageStatusText(context: Context): String {
        val perm = if (Build.VERSION.SDK_INT >= 33)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE

        return if (ContextCompat.checkSelfPermission(
                context, perm
            ) == PackageManager.PERMISSION_GRANTED
        ) "Granted" else "Not granted"
    }

    fun usageAccessStatusText(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val appOps = context.getSystemService(android.app.AppOpsManager::class.java)
            val mode = appOps?.unsafeCheckOpNoThrow(
                android.app.AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                context.packageName
            )
            if (mode == android.app.AppOpsManager.MODE_ALLOWED) "Granted" else "Not granted"
        } else {
            "Not available"
        }
    }
}
