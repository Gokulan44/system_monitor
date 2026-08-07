package com.soc.agent.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.soc.agent.R
import com.soc.agent.databinding.ActivityPinBinding
import com.soc.agent.security.Sha256
import com.soc.agent.utils.Prefs
import java.util.concurrent.Executor

/**
 * Local PIN gate with biometric fallback.
 *
 * Two modes, selected via [EXTRA_MODE]:
 *  - [MODE_SETUP]: the user creates a 4-6 digit PIN (entered twice, one field with
 *    a two-step state machine). The SHA-256 hash is stored in [Prefs.pinHash].
 *    After saving we optionally offer biometric enrollment. Always proceeds to
 *    [MainActivity] on success.
 *  - [MODE_VERIFY] (default): the user must enter the PIN (compared against
 *    [Prefs.pinHash]) or authenticate with a strong biometric when enabled. If no
 *    PIN is configured the gate is skipped and we finish straight through to
 *    [MainActivity].
 */
class PinLockActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MODE = "extra_mode"
        const val MODE_VERIFY = 0
        const val MODE_SETUP = 1
    }

    private lateinit var binding: ActivityPinBinding
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var executor: Executor

    private var mode = MODE_VERIFY
    private var setupStep = 0 // 0 = enter PIN, 1 = confirm PIN
    private var firstPin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mode = intent.getIntExtra(EXTRA_MODE, MODE_VERIFY)
        executor = ContextCompat.getMainExecutor(this)
        setupBiometricPrompt()

        // No PIN configured -> nothing to gate, finish straight through to main.
        if (mode == MODE_VERIFY && Prefs.pinHash.isNullOrBlank()) {
            proceedToMain()
            return
        }

        configureForMode()
        binding.btnVerify.setOnClickListener { onVerifyClicked() }
        binding.btnBiometric.setOnClickListener { biometricPrompt.authenticate(buildPromptInfo()) }
    }

    private fun setupBiometricPrompt() {
        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                proceedToMain()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                if (errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON &&
                    errorCode != BiometricPrompt.ERROR_USER_CANCELED
                ) {
                    binding.tvError.text = errString.toString()
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                binding.tvError.text = "Biometric not recognized. Try again."
            }
        })
    }

    private fun configureForMode() {
        if (mode == MODE_SETUP) {
            binding.tvTitle.text = "Create a PIN"
            binding.tvSubtitle.text = "Choose a 4-6 digit PIN to lock this app"
            binding.btnVerify.text = "Next"
        } else {
            binding.tvTitle.text = "Enter PIN"
            binding.tvSubtitle.text = "Unlock Security Agent"
            binding.btnVerify.text = "Unlock"
        }
        val biometricAvailable = Prefs.biometricEnabled && canUseBiometric()
        binding.btnBiometric.visibility =
            if (mode == MODE_VERIFY && biometricAvailable) View.VISIBLE else View.GONE
    }

    private fun onVerifyClicked() {
        val pin = binding.etPin.text?.toString().orEmpty()
        if (pin.length !in 4..6) {
            binding.tvError.text = "PIN must be 4-6 digits"
            return
        }
        binding.tvError.text = ""

        if (mode == MODE_SETUP) {
            handleSetupStep(pin)
        } else {
            if (Sha256.hashString(pin) == Prefs.pinHash) {
                proceedToMain()
            } else {
                binding.tvError.text = "Incorrect PIN"
                binding.etPin.text?.clear()
            }
        }
    }

    private fun handleSetupStep(pin: String) {
        if (setupStep == 0) {
            firstPin = pin
            setupStep = 1
            binding.etPin.text?.clear()
            binding.tvTitle.text = "Confirm PIN"
            binding.tvSubtitle.text = "Enter the same PIN again"
            binding.btnVerify.text = "Save PIN"
            binding.etPin.requestFocus()
        } else {
            if (pin == firstPin) {
                Prefs.pinHash = Sha256.hashString(pin)
                Prefs.pinEnabled = true
                maybePromptBiometricEnrollment()
                proceedToMain()
            } else {
                binding.tvError.text = "PINs do not match. Start over."
                setupStep = 0
                firstPin = ""
                binding.etPin.text?.clear()
                binding.tvTitle.text = "Create a PIN"
                binding.tvSubtitle.text = "Choose a 4-6 digit PIN to lock this app"
                binding.btnVerify.text = "Next"
            }
        }
    }

    private fun maybePromptBiometricEnrollment() {
        if (!canUseBiometric()) return
        AlertDialog.Builder(this)
            .setTitle("Enable biometric unlock?")
            .setMessage("Use fingerprint or face unlock to open the app instead of typing the PIN.")
            .setPositiveButton("Enable") { _, _ -> Prefs.biometricEnabled = true }
            .setNegativeButton("Not now", null)
            .show()
    }

    private fun canUseBiometric(): Boolean {
        return BiometricManager.from(this)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) ==
            BiometricManager.BIOMETRIC_SUCCESS
    }

    private fun buildPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock Security Agent")
            .setSubtitle("Authenticate to continue")
            .setNegativeButtonText("Cancel")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()
    }

    private fun proceedToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
