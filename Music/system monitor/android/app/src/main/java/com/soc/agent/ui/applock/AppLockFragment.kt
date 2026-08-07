package com.soc.agent.ui.applock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.soc.agent.R
import com.soc.agent.database.AppDatabase
import com.soc.agent.databinding.FragmentAppLockBinding
import com.soc.agent.security.PatternLock
import com.soc.agent.services.AppLockService
import com.soc.agent.ui.AppLockGateActivity
import com.soc.agent.ui.applock.AddAppLockFragment
import com.soc.agent.ui.applock.BackupFragment
import com.soc.agent.ui.applock.LockedAppsFragment
import com.soc.agent.ui.applock.SecuritySettingsFragment
import com.soc.agent.ui.applock.UnlockHistoryFragment
import com.soc.agent.utils.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * App Lock module container: a Lock Dashboard header (master switch, watcher
 * status, gate summary, Usage-Approval warning and the "Set unlock" action)
 * above a TabLayout + ViewPager2 with three tabs: Locked Apps, Add App Lock, Unlock History.
 */
class AppLockFragment : Fragment() {

    private var _binding: FragmentAppLockBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppLockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagerApplock.adapter = AppLockPagerAdapter(this)
        TabLayoutMediator(binding.tabApplock, binding.pagerApplock) { tab, position ->
            tab.text = when (position) {
                0 -> "Locked Apps"
                1 -> "Add App Lock"
                2 -> "Unlock History"
                3 -> "Security Settings"
                else -> "Backup & Restore"
            }
        }.attach()

        binding.swEnable.isChecked = Prefs.appLockEnabled
        binding.swEnable.setOnCheckedChangeListener { _, checked -> setEnabled(checked) }
        binding.btnSetupGate.setOnClickListener { showGateChoice() }
        binding.tvUsageWarn.setOnClickListener {
            runCatching { startActivity(AppLockService.usageAccessIntent()) }
        }

        setupAutoLockSpinners()
    }

    override fun onResume() {
        super.onResume()
        refreshHeader()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun refreshHeader() {
        val context = requireContext()
        binding.swEnable.isChecked = Prefs.appLockEnabled
        binding.tvGate.text = "Gate: ${gateSummary()}"
        binding.tvLockStats.text =
            (if (Prefs.appLockWatcherRunning) "Watcher on" else "Watcher off") +
                " \u00b7 loading\u2026"

        val hasUsage = AppLockService.hasUsageAccess(context)
        binding.tvUsageWarn.isVisible = !hasUsage
        binding.tvUsageWarn.text = if (hasUsage) {
            ""
        } else {
            "Grant Usage Access to lock apps \u00bb"
        }

        // Load the fresh DAO count and re-render the numeric header line.
        viewLifecycleOwner.lifecycleScope.launch {
            val count = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context).lockedAppDao().countEnabled()
            }
            if (_binding != null) {
                binding.tvLockStats.text =
                    (if (Prefs.appLockWatcherRunning) "Watcher on" else "Watcher off") +
                        " \u00b7 $count apps locked"
            }
        }
    }

    private fun gateSummary(): String = when {
        Prefs.lockMethod == "pattern" && PatternLock.isConfigured() -> "Pattern"
        Prefs.lockMethod == "pin" && Prefs.pinHash.isNotBlank() -> "PIN"
        Prefs.lockMethod == "password" && Prefs.passwordHash.isNotBlank() -> "Password"
        Prefs.lockMethod == "biometric" && Prefs.biometricEnabled -> "Biometric"
        PatternLock.isConfigured() -> "Pattern"
        Prefs.pinHash.isNotBlank() -> "PIN"
        Prefs.passwordHash.isNotBlank() -> "Password"
        Prefs.biometricEnabled -> "Biometric"
        else -> "none \u2014 set one to lock apps"
    }

    private fun setEnabled(checked: Boolean) {
        Prefs.appLockEnabled = checked
        val context = requireContext()
        if (checked) {
            context.startForegroundService(Intent(context, AppLockService::class.java))
        } else {
            context.stopService(Intent(context, AppLockService::class.java))
        }
        refreshHeader()
    }

    private fun showGateChoice() {
        startActivity(Intent(requireContext(), ChooseUnlockMethodActivity::class.java))
    }

    private fun setupAutoLockSpinners() {
        val modeValues = resources.getStringArray(R.array.auto_lock_mode_values)
        val timeoutValues = resources.getStringArray(R.array.auto_lock_timeout_values)
        val delayValues = resources.getStringArray(R.array.lock_delay_values)

        // Mode spinner
        val currentMode = Prefs.autoLockMode
        binding.spAutoLockMode.setSelection(modeValues.indexOf(currentMode).coerceAtLeast(0))
        binding.spAutoLockMode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Prefs.autoLockMode = modeValues[position]
                val isTimeout = modeValues[position] == "timeout"
                binding.llAutoLockTimeout.isVisible = isTimeout
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Timeout spinner
        val currentTimeout = Prefs.autoLockTimeout.toString()
        binding.spAutoLockTimeout.setSelection(timeoutValues.indexOf(currentTimeout).coerceAtLeast(0))
        binding.spAutoLockTimeout.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Prefs.autoLockTimeout = timeoutValues[position].toLong()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Lock delay spinner
        val currentDelay = Prefs.lockDelay.toString()
        binding.spLockDelay.setSelection(delayValues.indexOf(currentDelay).coerceAtLeast(0))
        binding.spLockDelay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Prefs.lockDelay = delayValues[position].toLong()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Show/hide timeout based on current mode
        binding.llAutoLockTimeout.isVisible = currentMode == "timeout"
    }

    private fun launchSetup(gate: String) {
        val intent = Intent(requireContext(), AppLockGateActivity::class.java).apply {
            putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
            putExtra(AppLockGateActivity.EXTRA_GATE, gate)
        }
        startActivity(intent)
    }

    private class AppLockPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 5
        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> LockedAppsFragment()
            1 -> AddAppLockFragment()
            2 -> UnlockHistoryFragment()
            3 -> SecuritySettingsFragment()
            else -> BackupFragment()
        }
    }
}