package com.soc.agent.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.soc.agent.R
import com.soc.agent.data.SettingsRepository
import com.soc.agent.ui.AppLockGateActivity
import com.soc.agent.ui.ReportsFragment
import com.soc.agent.ui.applock.AppLockFragment

/**
 * SecuritySettingsFragment — Security settings screen.
 *
 * Provides toggles and setup actions for all security-related features:
 *  - Fingerprint Login (SwitchMaterial)
 *  - Face Unlock (SwitchMaterial)
 *  - PIN Lock toggle + "Set up PIN" button (navigates to PinLockActivity)
 *  - Pattern Lock toggle + "Set up Pattern" button (navigates to AppLockGateActivity)
 *  - Password Lock toggle + "Set up Password" button (navigates to AppLockGateActivity)
 *  - App Lock Settings (navigates to AppLockFragment)
 *  - Trusted Devices list (RecyclerView)
 *  - Security Alerts toggle (SwitchMaterial)
 *  - Login Notifications toggle (SwitchMaterial)
 *  - Security Report button (navigates to Reports)
 *
 * Uses [SettingsRepository] to persist every toggle and setting.
 * Toolbar includes a back arrow that pops the fragment back stack.
 */
class SecurityFragment : Fragment() {

    private var _binding: View? = null
    private val binding get() = _binding!!

    // ── Views ──────────────────────────────────────────────────────────
    private lateinit var toolbar: MaterialToolbar
    private lateinit var switchFingerprint: SwitchMaterial
    private lateinit var switchFaceUnlock: SwitchMaterial
    private lateinit var switchPinLock: SwitchMaterial
    private lateinit var btnSetupPin: MaterialButton
    private lateinit var switchPatternLock: SwitchMaterial
    private lateinit var btnSetupPattern: MaterialButton
    private lateinit var switchPasswordLock: SwitchMaterial
    private lateinit var btnSetupPassword: MaterialButton
    private lateinit var btnAppLockSettings: MaterialButton
    private lateinit var rvTrustedDevices: RecyclerView
    private lateinit var tvTrustedCount: TextView
    private lateinit var switchSecurityAlerts: SwitchMaterial
    private lateinit var switchLoginNotifications: SwitchMaterial
    private lateinit var btnSecurityReport: MaterialButton

    // ── Lifecycle ──────────────────────────────────────────────────────

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflater.inflate(R.layout.fragment_security_settings, container, false)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SettingsRepository.init(requireContext().applicationContext)
        bindViews(view)
        setupToolbar()
        loadSettings()
        setupListeners()
        setupTrustedDevicesList()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    // ── View binding ───────────────────────────────────────────────────

    private fun bindViews(root: View) {
        toolbar = root.findViewById(R.id.toolbar_security)
        switchFingerprint = root.findViewById(R.id.switch_fingerprint)
        switchFaceUnlock = root.findViewById(R.id.switch_face_recognition)
        switchPinLock = root.findViewById(R.id.switch_pin)
        btnSetupPin = root.findViewById(R.id.btn_change_pin)
        switchPatternLock = root.findViewById(R.id.switch_pattern)
        btnSetupPattern = root.findViewById(R.id.btn_change_pattern)
        switchPasswordLock = root.findViewById(R.id.switch_password)
        btnSetupPassword = root.findViewById(R.id.btn_change_pin)
        btnAppLockSettings = root.findViewById(R.id.btn_change_pin)
        rvTrustedDevices = root.findViewById(R.id.rv_trusted_devices)
        tvTrustedCount = root.findViewById(R.id.tv_no_trusted_devices)
        switchSecurityAlerts = root.findViewById(R.id.switch_fingerprint)
        switchLoginNotifications = root.findViewById(R.id.switch_fingerprint)
        btnSecurityReport = root.findViewById(R.id.btn_change_pin)
    }

    // ── Toolbar ────────────────────────────────────────────────────────

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    // ── Load persisted settings ────────────────────────────────────────

    private fun loadSettings() {
        val settings = SettingsRepository.getSecuritySettings()

        switchFingerprint.isChecked = settings.fingerprintLogin
        switchFaceUnlock.isChecked = settings.faceUnlock
        switchPinLock.isChecked = settings.pinLock
        switchPatternLock.isChecked = settings.patternLock
        switchPasswordLock.isChecked = settings.passwordLock
        switchSecurityAlerts.isChecked = settings.securityAlerts
        switchLoginNotifications.isChecked = settings.loginNotifications

        tvTrustedCount.text = "${settings.trustedDevices} device(s) trusted"
    }

    // ── Listeners ──────────────────────────────────────────────────────

    private fun setupListeners() {

        // ── Fingerprint Login ──────────────────────────────────────────
        switchFingerprint.setOnCheckedChangeListener { _, isChecked ->
            SettingsRepository.setFingerprintLogin(isChecked)
            showToast(
                if (isChecked) "Fingerprint login enabled"
                else "Fingerprint login disabled"
            )
        }

        // ── Face Unlock ────────────────────────────────────────────────
        switchFaceUnlock.setOnCheckedChangeListener { _, isChecked ->
            SettingsRepository.setFaceUnlock(isChecked)
            showToast(
                if (isChecked) "Face Unlock enabled"
                else "Face Unlock disabled"
            )
        }

        // ── PIN Lock ───────────────────────────────────────────────────
        switchPinLock.setOnCheckedChangeListener { _, isChecked ->
            SettingsRepository.setPinLock(isChecked)
            if (isChecked && com.soc.agent.utils.Prefs.pinHash.isBlank()) {
                // User enabled toggle but no PIN is configured yet → direct to setup
                switchPinLock.isChecked = false
                navigateToPinSetup()
            } else {
                showToast(
                    if (isChecked) "PIN Lock enabled"
                    else "PIN Lock disabled"
                )
            }
        }

        btnSetupPin.setOnClickListener { navigateToPinSetup() }

        // ── Pattern Lock ───────────────────────────────────────────────
        switchPatternLock.setOnCheckedChangeListener { _, isChecked ->
            SettingsRepository.setPatternLock(isChecked)
            if (isChecked && com.soc.agent.security.PatternLock.isConfigured().not()) {
                switchPatternLock.isChecked = false
                navigateToPatternSetup()
            } else {
                showToast(
                    if (isChecked) "Pattern Lock enabled"
                    else "Pattern Lock disabled"
                )
            }
        }

        btnSetupPattern.setOnClickListener { navigateToPatternSetup() }

        // ── Password Lock ──────────────────────────────────────────────
        switchPasswordLock.setOnCheckedChangeListener { _, isChecked ->
            SettingsRepository.setPasswordLock(isChecked)
            if (isChecked && com.soc.agent.utils.Prefs.passwordHash.isBlank()) {
                switchPasswordLock.isChecked = false
                navigateToPasswordSetup()
            } else {
                showToast(
                    if (isChecked) "Password Lock enabled"
                    else "Password Lock disabled"
                )
            }
        }

        btnSetupPassword.setOnClickListener { navigateToPasswordSetup() }

        // ── App Lock Settings ──────────────────────────────────────────
        btnAppLockSettings.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AppLockFragment())
                .addToBackStack(null)
                .commit()
        }

        // ── Security Alerts ────────────────────────────────────────────
        switchSecurityAlerts.setOnCheckedChangeListener { _, isChecked ->
            SettingsRepository.setSecurityAlerts(isChecked)
            showToast(
                if (isChecked) "Security alerts enabled"
                else "Security alerts disabled"
            )
        }

        // ── Login Notifications ────────────────────────────────────────
        switchLoginNotifications.setOnCheckedChangeListener { _, isChecked ->
            SettingsRepository.setLoginNotifications(isChecked)
            showToast(
                if (isChecked) "Login notifications enabled"
                else "Login notifications disabled"
            )
        }

        // ── Security Report ────────────────────────────────────────────
        btnSecurityReport.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ReportsFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    // ── Trusted Devices list ───────────────────────────────────────────

    private fun setupTrustedDevicesList() {
        rvTrustedDevices.layoutManager = LinearLayoutManager(requireContext())
        val deviceCount = SettingsRepository.getSecuritySettings().trustedDevices
        val devices = generateTrustedDeviceList(deviceCount)
        rvTrustedDevices.adapter = TrustedDeviceAdapter(devices)
    }

    /**
     * Build a simple list of trusted device display names.
     * In a real implementation this would come from a database or server.
     */
    private fun generateTrustedDeviceList(count: Int): List<TrustedDevice> {
        if (count <= 0) return emptyList()
        val deviceInfo = SettingsRepository.getDeviceInfo()
        val deviceName = deviceInfo.deviceName.ifBlank { "Current Device" }
        val list = mutableListOf<TrustedDevice>()
        list.add(TrustedDevice(name = deviceName, type = "This device"))
        // Add placeholder entries for additional trusted devices
        for (i in 2..count) {
            list.add(TrustedDevice(name = "Device $i", type = "Trusted"))
        }
        return list
    }

    // ── Navigation helpers ─────────────────────────────────────────────

    private fun navigateToPinSetup() {
        val intent = Intent(requireContext(), AppLockGateActivity::class.java).apply {
            putExtra(AppLockGateActivity.EXTRA_GATE, AppLockGateActivity.GATE_PIN)
            putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
        }
        startActivity(intent)
    }

    private fun navigateToPatternSetup() {
        val intent = Intent(requireContext(), AppLockGateActivity::class.java).apply {
            putExtra(AppLockGateActivity.EXTRA_GATE, AppLockGateActivity.GATE_PATTERN)
            putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
        }
        startActivity(intent)
    }

    private fun navigateToPasswordSetup() {
        val intent = Intent(requireContext(), AppLockGateActivity::class.java).apply {
            putExtra(AppLockGateActivity.EXTRA_GATE, AppLockGateActivity.GATE_PASSWORD)
            putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
        }
        startActivity(intent)
    }

    // ── Utility ────────────────────────────────────────────────────────

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // ── Data model ─────────────────────────────────────────────────────

    data class TrustedDevice(
        val name: String,
        val type: String
    )

    // ── Trusted Device Adapter ─────────────────────────────────────────

    private inner class TrustedDeviceAdapter(
        private val devices: List<TrustedDevice>
    ) : RecyclerView.Adapter<TrustedDeviceAdapter.DeviceViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_trusted_device, parent, false)
            return DeviceViewHolder(view)
        }

        override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
            holder.bind(devices[position])
        }

        override fun getItemCount(): Int = devices.size

        inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val tvDeviceName: TextView = itemView.findViewById(R.id.tvDeviceName)
            private val tvDeviceType: TextView = itemView.findViewById(R.id.tvDeviceType)

            fun bind(device: TrustedDevice) {
                tvDeviceName.text = device.name
                tvDeviceType.text = device.type
            }
        }
    }
}
