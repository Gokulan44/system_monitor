package com.soc.agent.ui.settings

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.soc.agent.R
import com.soc.agent.data.SettingsRepository

class PrivacyFragment : Fragment() {

    companion object {
        private const val PRIVACY_POLICY_URL = "https://socagent.app/privacy"
    }

    private lateinit var tvCameraStatus: TextView
    private lateinit var tvLocationStatus: TextView
    private lateinit var tvStorageStatus: TextView
    private lateinit var tvUsageStatus: TextView

    private val cameraPermLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> handleCameraResult(granted) }

    private val locationPermLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> handleLocationResult(granted) }

    private val storagePermLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> handleStorageResult(granted) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return buildLayout()
    }

    override fun onResume() {
        super.onResume()
        refreshPermissionStatuses()
    }

    private fun buildLayout(): View {
        val ctx = requireContext()

        val scrollView = android.widget.ScrollView(ctx).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.parseColor("#0A0F1E"))
            isFillViewport = true
        }

        val root = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(16), dp(20), dp(32))
        }

        root.addView(sectionTitle("Privacy Settings"))

        root.addView(buildCard().apply {
            addView(toggleRow(
                label = "Data Collection",
                subtitle = "Allow the app to collect diagnostic data",
                checked = SettingsRepository.getPrivacySettings().dataCollection,
                onToggled = { SettingsRepository.savePrivacySettings(SettingsRepository.getPrivacySettings().copy(dataCollection = it)) }
            ))
        })

        root.addView(buildCard().apply {
            addView(toggleRow(
                label = "Analytics",
                subtitle = "Help us improve by sharing anonymous usage stats",
                checked = SettingsRepository.getPrivacySettings().analytics,
                onToggled = { SettingsRepository.savePrivacySettings(SettingsRepository.getPrivacySettings().copy(analytics = it)) }
            ))
        })

        root.addView(buildCard().apply {
            val (row, statusTv) = permissionRow(
                title = "Camera Access",
                statusText = PrivacyAuditHelper.cameraStatusText(ctx),
                onRequest = { cameraPermLauncher.launch(Manifest.permission.CAMERA) }
            )
            tvCameraStatus = statusTv
            addView(row)
        })

        root.addView(buildCard().apply {
            val (row, statusTv) = permissionRow(
                title = "Location Access",
                statusText = PrivacyAuditHelper.locationStatusText(ctx),
                onRequest = { locationPermLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }
            )
            tvLocationStatus = statusTv
            addView(row)
        })

        root.addView(buildCard().apply {
            val perm = if (android.os.Build.VERSION.SDK_INT >= 33) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE
            val (row, statusTv) = permissionRow(
                title = "Storage Access",
                statusText = PrivacyAuditHelper.storageStatusText(ctx),
                onRequest = { storagePermLauncher.launch(perm) }
            )
            tvStorageStatus = statusTv
            addView(row)
        })

        root.addView(buildCard().apply {
            val (row, statusTv) = permissionRow(
                title = "Usage Access",
                statusText = PrivacyAuditHelper.usageAccessStatusText(ctx),
                onRequest = { startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)) },
                buttonText = "Open Settings"
            )
            tvUsageStatus = statusTv
            addView(row)
        })

        root.addView(buildCard().apply {
            addView(buttonRow(
                label = "Export Data",
                subtitle = "Download your activity data as CSV or JSON",
                onClick = { showExportDialog() }
            ))
        })

        root.addView(buildCard().apply {
            addView(buttonRow(
                label = "Privacy Policy",
                subtitle = "Read how we handle your data",
                onClick = { openPrivacyPolicy() }
            ))
        })

        scrollView.addView(root)
        return scrollView
    }

    private fun buildCard(): LinearLayout {
        return LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { it.bottomMargin = dp(12) }
            setBackgroundColor(Color.parseColor("#111827"))
            setPadding(dp(16), dp(14), dp(16), dp(14))
            background = android.graphics.drawable.GradientDrawable().apply {
                setColor(Color.parseColor("#111827"))
                cornerRadius = dp(12).toFloat()
            }
        }
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    private fun sectionTitle(text: String): TextView {
        return TextView(requireContext()).apply {
            this.text = text
            setTextColor(Color.parseColor("#E2E8F0"))
            textSize = 22f
            typeface = Typeface.DEFAULT_BOLD
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { it.bottomMargin = dp(16) }
        }
    }

    private fun toggleRow(label: String, subtitle: String, checked: Boolean, onToggled: (Boolean) -> Unit): LinearLayout {
        val ctx = requireContext()
        return LinearLayout(ctx).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            addView(LinearLayout(ctx).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                addView(TextView(ctx).apply {
                    this.text = label
                    setTextColor(Color.parseColor("#E2E8F0"))
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                })
                addView(TextView(ctx).apply {
                    this.text = subtitle
                    setTextColor(Color.parseColor("#94A3B8"))
                    textSize = 13f
                    setPadding(0, dp(2), 0, 0)
                })
            })
            addView(SwitchMaterial(ctx).apply {
                isChecked = checked
                setOnCheckedChangeListener { _, on -> onToggled(on) }
            })
        }
    }

    private fun permissionRow(title: String, statusText: String, onRequest: () -> Unit, buttonText: String = "Request"): Pair<LinearLayout, TextView> {
        val ctx = requireContext()
        val statusTv = TextView(ctx).apply {
            this.text = statusText
            setTextColor(Color.parseColor("#94A3B8"))
            textSize = 13f
            setPadding(0, dp(2), 0, 0)
        }
        val layout = LinearLayout(ctx).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            addView(LinearLayout(ctx).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                addView(TextView(ctx).apply {
                    this.text = title
                    setTextColor(Color.parseColor("#E2E8F0"))
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                })
                addView(statusTv)
            })
            addView(Button(ctx).apply {
                text = buttonText
                setTextColor(Color.parseColor("#22D3EE"))
                setBackgroundColor(Color.TRANSPARENT)
                isAllCaps = false
                setOnClickListener { onRequest() }
            })
        }
        return Pair(layout, statusTv)
    }

    private fun buttonRow(label: String, subtitle: String, onClick: () -> Unit): LinearLayout {
        val ctx = requireContext()
        return LinearLayout(ctx).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            addView(LinearLayout(ctx).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                addView(TextView(ctx).apply {
                    this.text = label
                    setTextColor(Color.parseColor("#E2E8F0"))
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                })
                addView(TextView(ctx).apply {
                    this.text = subtitle
                    setTextColor(Color.parseColor("#94A3B8"))
                    textSize = 13f
                    setPadding(0, dp(2), 0, 0)
                })
            })
            addView(Button(ctx).apply {
                text = "›"
                setTextColor(Color.parseColor("#6366F1"))
                setBackgroundColor(Color.TRANSPARENT)
                textSize = 22f
                setOnClickListener { onClick() }
            })
        }
    }

    private fun handleCameraResult(granted: Boolean) {
        tvCameraStatus.text = if (granted) "Granted" else "Not granted"
        tvCameraStatus.setTextColor(Color.parseColor(if (granted) "#34D399" else "#F87171"))
    }

    private fun handleLocationResult(granted: Boolean) {
        tvLocationStatus.text = if (granted) "Granted" else "Not granted"
        tvLocationStatus.setTextColor(Color.parseColor(if (granted) "#34D399" else "#F87171"))
    }

    private fun handleStorageResult(granted: Boolean) {
        tvStorageStatus.text = if (granted) "Granted" else "Not granted"
        tvStorageStatus.setTextColor(Color.parseColor(if (granted) "#34D399" else "#F87171"))
    }

    private fun refreshPermissionStatuses() {
        val ctx = requireContext()
        updateStatusTv(tvCameraStatus, PrivacyAuditHelper.cameraStatusText(ctx))
        updateStatusTv(tvLocationStatus, PrivacyAuditHelper.locationStatusText(ctx))
        updateStatusTv(tvStorageStatus, PrivacyAuditHelper.storageStatusText(ctx))
        updateStatusTv(tvUsageStatus, PrivacyAuditHelper.usageAccessStatusText(ctx))
    }

    private fun updateStatusTv(tv: TextView, text: String) {
        tv.text = text
        tv.setTextColor(Color.parseColor(if (text == "Granted") "#34D399" else "#94A3B8"))
    }

    private fun showExportDialog() {
        val items = arrayOf("Export as CSV", "Export as JSON")
        AlertDialog.Builder(requireContext(), R.style.Theme_SOCAgent)
            .setTitle("Export Data")
            .setItems(items) { _, which ->
                val format = if (which == 0) "CSV" else "JSON"
                Toast.makeText(requireContext(), "Exporting data as $format…", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun openPrivacyPolicy() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY_POLICY_URL)))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Unable to open Privacy Policy", Toast.LENGTH_SHORT).show()
        }
    }
}
