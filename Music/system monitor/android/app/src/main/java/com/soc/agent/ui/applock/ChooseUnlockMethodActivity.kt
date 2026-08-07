package com.soc.agent.ui.applock

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.soc.agent.databinding.ActivityChooseUnlockMethodBinding
import com.soc.agent.security.PatternLock
import com.soc.agent.ui.AppLockGateActivity
import com.soc.agent.utils.Prefs

/**
 * Screen where the user chooses their preferred unlock method for App Lock.
 * Shows available methods, indicates the currently active one, and navigates
 * to setup if the chosen method is not yet configured.
 */
class ChooseUnlockMethodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseUnlockMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseUnlockMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }
        setupMethodCards()
    }

    override fun onResume() {
        super.onResume()
        highlightCurrentMethod()
    }

    private fun setupMethodCards() {
        binding.cardPin.setOnClickListener { selectMethod(METHOD_PIN) }
        binding.cardPattern.setOnClickListener { selectMethod(METHOD_PATTERN) }
        binding.cardPassword.setOnClickListener { selectMethod(METHOD_PASSWORD) }
        binding.cardBiometric.setOnClickListener { selectMethod(METHOD_BIOMETRIC) }
    }

    private fun highlightCurrentMethod() {
        val current = Prefs.lockMethod
        val pinConfigured = Prefs.pinHash.isNotBlank()
        val patternConfigured = PatternLock.isConfigured()
        val passwordConfigured = Prefs.passwordHash.isNotBlank()
        val biometricConfigured = Prefs.biometricEnabled

        // Clear all highlights
        clearHighlights()

        // Show current indicator
        when (current) {
            METHOD_PIN -> {
                binding.tvCurrentMethod.text = "Current: PIN"
                binding.indicatorPin.visibility = View.VISIBLE
            }
            METHOD_PATTERN -> {
                binding.tvCurrentMethod.text = "Current: Pattern"
                binding.indicatorPattern.visibility = View.VISIBLE
            }
            METHOD_PASSWORD -> {
                binding.tvCurrentMethod.text = "Current: Password"
                binding.indicatorPassword.visibility = View.VISIBLE
            }
            METHOD_BIOMETRIC -> {
                binding.tvCurrentMethod.text = "Current: Biometric"
                binding.indicatorBiometric.visibility = View.VISIBLE
            }
            else -> {
                binding.tvCurrentMethod.text = "No method configured"
            }
        }

        // Show configured status
        binding.tvPinStatus.text = if (pinConfigured) "Configured" else "Not set up"
        binding.tvPatternStatus.text = if (patternConfigured) "Configured" else "Not set up"
        binding.tvPasswordStatus.text = if (passwordConfigured) "Configured" else "Not set up"
        binding.tvBiometricStatus.text = if (biometricConfigured) "Configured" else "Not set up"
    }

    private fun clearHighlights() {
        binding.indicatorPin.visibility = View.GONE
        binding.indicatorPattern.visibility = View.GONE
        binding.indicatorPassword.visibility = View.GONE
        binding.indicatorBiometric.visibility = View.GONE
    }

    private fun selectMethod(method: String) {
        when (method) {
            METHOD_PIN -> {
                if (Prefs.pinHash.isNotBlank()) {
                    // Already configured — just switch
                    Prefs.lockMethod = METHOD_PIN
                    Toast.makeText(this, "Switched to PIN", Toast.LENGTH_SHORT).show()
                    highlightCurrentMethod()
                } else {
                    // Need to set up PIN
                    val intent = Intent(this, AppLockGateActivity::class.java).apply {
                        putExtra(AppLockGateActivity.EXTRA_GATE, AppLockGateActivity.GATE_PIN)
                        putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
                    }
                    startActivity(intent)
                }
            }
            METHOD_PATTERN -> {
                if (PatternLock.isConfigured()) {
                    Prefs.lockMethod = METHOD_PATTERN
                    Toast.makeText(this, "Switched to Pattern", Toast.LENGTH_SHORT).show()
                    highlightCurrentMethod()
                } else {
                    val intent = Intent(this, AppLockGateActivity::class.java).apply {
                        putExtra(AppLockGateActivity.EXTRA_GATE, AppLockGateActivity.GATE_PATTERN)
                        putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
                    }
                    startActivity(intent)
                }
            }
            METHOD_PASSWORD -> {
                if (Prefs.passwordHash.isNotBlank()) {
                    Prefs.lockMethod = METHOD_PASSWORD
                    Toast.makeText(this, "Switched to Password", Toast.LENGTH_SHORT).show()
                    highlightCurrentMethod()
                } else {
                    val intent = Intent(this, AppLockGateActivity::class.java).apply {
                        putExtra(AppLockGateActivity.EXTRA_GATE, AppLockGateActivity.GATE_PASSWORD)
                        putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
                    }
                    startActivity(intent)
                }
            }
            METHOD_BIOMETRIC -> {
                if (Prefs.biometricEnabled) {
                    Prefs.lockMethod = METHOD_BIOMETRIC
                    Toast.makeText(this, "Switched to Biometric", Toast.LENGTH_SHORT).show()
                    highlightCurrentMethod()
                } else {
                    // Biometric setup goes through the gate in biometric mode
                    val intent = Intent(this, AppLockGateActivity::class.java).apply {
                        putExtra(AppLockGateActivity.EXTRA_GATE, AppLockGateActivity.GATE_BIOMETRIC)
                        putExtra(AppLockGateActivity.EXTRA_MODE, AppLockGateActivity.MODE_SETUP)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    companion object {
        const val METHOD_PIN = "pin"
        const val METHOD_PATTERN = "pattern"
        const val METHOD_PASSWORD = "password"
        const val METHOD_BIOMETRIC = "biometric"
    }
}