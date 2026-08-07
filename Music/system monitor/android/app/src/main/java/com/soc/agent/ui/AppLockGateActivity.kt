package com.soc.agent.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.soc.agent.R
import com.soc.agent.databinding.ActivityAppLockGateBinding
import com.soc.agent.security.PatternLock
import com.soc.agent.security.Sha256
import com.soc.agent.services.AppLockService
import com.soc.agent.utils.Prefs
import com.soc.agent.security.SecuritySettingsManager
import com.soc.agent.security.IntruderSelfieManager
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.FailedAttemptEntity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * Unified unlock gate for the App Lock module, presenting either a PIN pad or
 * the 3x3 pattern view depending on [EXTRA_GATE].
 *
 * Modes (via [EXTRA_MODE]):
 *  - [MODE_SETUP]: create a gate. PIN gates collect the value twice (create /
 *    confirm); pattern gates require drawing the same pattern twice. On success
 *    the hash is stored (Prefs.pinHash / PatternLock) and [Prefs.lockMethod]
 *    is set. Used from the Add App Lock setup flow.
 *  - [MODE_VERIFY]: the user must satisfy the configured gate. On success we
 *    call [AppLockService.recordUnlock] so the watcher's grace window lets the
 *    package stay open, then finish. Used by [AppLockService] when a locked app
 *    comes to the foreground.
 *
 * [EXTRA_PACKAGE] is optional; when absent the verify path unlocks without
 * targeting a specific process (still enough to satisfy the watcher).
 */
class AppLockGateActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MODE = "extra_mode"
        const val EXTRA_GATE = "extra_gate"
        const val EXTRA_PACKAGE = "extra_package"

        const val MODE_SETUP = "setup"
        const val MODE_VERIFY = "verify"

        const val GATE_PIN = "pin"
        const val GATE_PATTERN = "pattern"
        const val GATE_PASSWORD = "password"
        const val GATE_BIOMETRIC = "biometric"
    }

    private lateinit var binding: ActivityAppLockGateBinding

    private var mode = MODE_SETUP
    private var gate: String = GATE_PIN
    private var targetPackage: String? = null

    private var setupStep = 0
    private var firstPattern: List<Int>? = null
    private var firstPin: String? = null
    private var firstPassword: String? = null
    private var consecutiveFailures = 0

    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var biometricPrompt: BiometricPrompt
    private var biometricAuthenticating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppLockGateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_SETUP
        gate = intent.getStringExtra(EXTRA_GATE) ?: GATE_PIN
        targetPackage = intent.getStringExtra(EXTRA_PACKAGE)

        initBiometricPrompt()
        configureForMode()
        binding.btnAction.setOnClickListener { onAction() }
    }

    private fun initBiometricPrompt() {
        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                biometricAuthenticating = false
                if (mode == MODE_SETUP && gate == GATE_BIOMETRIC) {
                    onSetupBiometricSuccess()
                } else {
                    unlockSuccess()
                }
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                biometricAuthenticating = false
                binding.tvError.text = errString
            }

            override fun onAuthenticationFailed() {
                recordFailedAttempt("biometric_failed")
                binding.tvError.text = "Biometric authentication failed"
            }
        })
    }

    private fun onSetupBiometricSuccess() {
        Prefs.biometricEnabled = true
        Prefs.lockMethod = GATE_BIOMETRIC
        finishOk()
    }

    private fun configureForMode() {
        // Show only that gate's control.
        binding.tilPin.isVisible = gate == GATE_PIN
        binding.tilPassword.isVisible = gate == GATE_PASSWORD
        binding.patternView.isVisible = gate == GATE_PATTERN

        if (mode == MODE_SETUP) {
            if (gate == GATE_PATTERN) {
                binding.tvTitle.text = "Draw a pattern"
                binding.tvSubtitle.text = "Connect at least 4 dots"
                binding.btnAction.text = "Next"
                binding.patternView.onPatternComplete = { dots ->
                    binding.tvError.text = ""
                    if (PatternLock.normalize(dots).size < PatternLock.MIN_POINTS) {
                        showError("Connect at least ${PatternLock.MIN_POINTS} dots")
                        binding.patternView.reset()
                    } else {
                        handlePatternStep(dots)
                    }
                }
            } else if (gate == GATE_PASSWORD) {
                binding.tvTitle.text = "Create a password"
                binding.tvSubtitle.text = "Choose a password (4-64 chars)"
                binding.btnAction.text = "Next"
                binding.btnAction.visibility = View.VISIBLE
            } else if (gate == GATE_BIOMETRIC) {
                binding.tvTitle.text = "Enable biometric unlock"
                binding.tvSubtitle.text = "Use fingerprint or face to unlock apps"
                binding.btnAction.text = "Enable"
                binding.btnAction.visibility = View.VISIBLE
            } else {
                binding.tvTitle.text = "Create a PIN"
                binding.tvSubtitle.text = "Choose a 4-6 digit PIN"
                binding.btnAction.text = "Next"
                binding.btnAction.visibility = View.VISIBLE
            }
        } else {
                       // Verify mode.
                       if (gate == GATE_PATTERN) {
                           binding.tvTitle.text = "Unlock"
                           binding.tvSubtitle.text = "Draw your pattern"
                           binding.btnAction.visibility = View.GONE
                           binding.patternView.onPatternComplete = { dots ->
                               binding.tvError.text = ""
                               if (PatternLock.matches(dots)) {
                                   unlockSuccess()
                               } else {
                                   recordFailedAttempt("wrong_pattern")
                                   binding.tvError.text = "Incorrect pattern"
                                   binding.patternView.showError()
                                   binding.patternView.postDelayed({ binding.patternView.reset() }, 700L)
                               }
                           }
                       } else if (gate == GATE_PASSWORD) {
                binding.tvTitle.text = "Enter password"
                binding.tvSubtitle.text = if (targetPackage != null) {
                    "Enter your password to unlock"
                } else {
                    "Enter your password"
                }
                binding.btnAction.text = "Unlock"
                binding.btnAction.visibility = View.VISIBLE
            } else if (gate == GATE_BIOMETRIC) {
                binding.tvTitle.text = "Biometric unlock"
                binding.tvSubtitle.text = "Use fingerprint or face to unlock"
                binding.btnAction.visibility = View.GONE
                startBiometricAuth()
            } else {
                binding.tvTitle.text = "Enter PIN"
                binding.tvSubtitle.text = if (targetPackage != null) {
                    "Enter your PIN to unlock"
                } else {
                    "Enter your PIN"
                }
                binding.btnAction.text = "Unlock"
                binding.btnAction.visibility = View.VISIBLE
            }
        }
    }

    private fun onAction() {
        when (gate) {
            GATE_PATTERN -> { /* pattern mode submits via the callback */ }
            GATE_PIN -> {
                val pin = binding.etPin.text?.toString().orEmpty()
                if (pin.length !in 4..6) {
                    binding.tvError.text = "PIN must be 4-6 digits"
                    return
                }
                binding.tvError.text = ""
                if (mode == MODE_VERIFY) {
                    onVerifyPin(pin)
                } else {
                    onSetupPin(pin)
                    binding.etPin.text?.clear()
                }
            }
            GATE_PASSWORD -> {
                val password = binding.etPassword.text?.toString().orEmpty()
                if (password.length !in 4..64) {
                    binding.tvError.text = "Password must be 4-64 characters"
                    return
                }
                binding.tvError.text = ""
                if (mode == MODE_VERIFY) {
                    onVerifyPassword(password)
                } else {
                    onSetupPassword(password)
                    binding.etPassword.text?.clear()
                }
            }
            GATE_BIOMETRIC -> {
                if (mode == MODE_SETUP) {
                    onSetupBiometric()
                }
            }
        }
    }

    private fun onVerifyPin(pin: String) {
        val stored = Prefs.pinHash
        if (stored.isNullOrBlank()) {
            binding.tvError.text = "No PIN configured"
            return
        }
        if (Sha256.hashString(pin) == stored) {
            unlockSuccess()
        } else {
            recordFailedAttempt("wrong_pin")
            binding.tvError.text = "Incorrect PIN"
            binding.etPin.text?.clear()
        }
    }

    private fun onVerifyPassword(password: String) {
        val stored = Prefs.passwordHash
        if (stored.isNullOrBlank()) {
            binding.tvError.text = "No password configured"
            return
        }
        if (Sha256.hashString(password) == stored) {
            unlockSuccess()
        } else {
            recordFailedAttempt("wrong_password")
            binding.tvError.text = "Incorrect password"
            binding.etPassword.text?.clear()
        }
    }

    private fun onSetupPin(pin: String) {
        if (setupStep == 0) {
            firstPin = pin
            setupStep = 1
            binding.tvTitle.text = "Confirm PIN"
            binding.tvSubtitle.text = "Enter the same PIN again"
            binding.tvError.text = ""
        } else {
            if (pin == firstPin) {
                Prefs.pinHash = Sha256.hashString(pin)
                Prefs.pinEnabled = true
                Prefs.lockMethod = GATE_PIN
                setupStep = 0
                firstPin = null
                finishOk()
            } else {
                binding.tvError.text = "PINs do not match. Start over."
                setupStep = 0
                firstPin = null
                binding.tvTitle.text = "Create a PIN"
                binding.tvSubtitle.text = "Choose a 4-6 digit PIN"
            }
        }
    }

    private fun onSetupPassword(password: String) {
        if (setupStep == 0) {
            firstPassword = password
            setupStep = 1
            binding.tvTitle.text = "Confirm password"
            binding.tvSubtitle.text = "Enter the same password again"
            binding.tvError.text = ""
        } else {
            if (password == firstPassword) {
                Prefs.passwordHash = Sha256.hashString(password)
                Prefs.lockMethod = GATE_PASSWORD
                setupStep = 0
                firstPassword = null
                finishOk()
            } else {
                binding.tvError.text = "Passwords do not match. Start over."
                setupStep = 0
                firstPassword = null
                binding.tvTitle.text = "Create a password"
                binding.tvSubtitle.text = "Choose a password (4-64 chars)"
            }
        }
    }

    private fun startBiometricAuth() {
        if (biometricAuthenticating) return
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric unlock")
            .setSubtitle("Authenticate to unlock")
            .setNegativeButtonText("Cancel")
            .build()
        biometricAuthenticating = true
        biometricPrompt.authenticate(promptInfo)
    }

    private fun onSetupBiometric() {
        // For setup, just prompt the user to authenticate once to confirm biometric works
        // Then save the biometric preference
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Set up biometric unlock")
            .setSubtitle("Authenticate to enable biometric unlock")
            .setNegativeButtonText("Cancel")
            .build()
        biometricAuthenticating = true
        biometricPrompt.authenticate(promptInfo)
    }

    private fun handlePatternStep(pattern: List<Int>) {
        if (setupStep == 0) {
            firstPattern = pattern
            setupStep = 1
            binding.tvTitle.text = "Confirm pattern"
            binding.tvSubtitle.text = "Draw the same pattern again"
            binding.patternView.reset()
        } else {
            val first = firstPattern
            if (first != null && PatternLock.canonical(first) == PatternLock.canonical(pattern)) {
                PatternLock.setPattern(pattern)
                setupStep = 0
                firstPattern = null
                finishOk()
            } else {
                binding.tvError.text = "Patterns do not match. Start over."
                setupStep = 0
                firstPattern = null
                binding.tvTitle.text = "Draw a pattern"
                binding.tvSubtitle.text = "Connect at least 4 dots"
                binding.patternView.reset()
            }
        }
    }

    private fun showError(message: String) {
        binding.tvError.text = message
    }

    private fun unlockSuccess() {
        resetFailureCounter()
        targetPackage?.let { AppLockService.recordUnlock(it, this, gate, false) }
        Toast.makeText(this, "Unlocked", Toast.LENGTH_SHORT).show()
        finish()
    }

    /** Setup-mode success: gate saved and reported back to the caller. */
    private fun finishOk() {
        Toast.makeText(this, "Lock gate saved", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun recordFailedAttempt(reason: String) {
        consecutiveFailures++
        targetPackage?.let { pkg ->
            lifecycleScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getInstance(applicationContext)
                val appName = getAppLabel(pkg) ?: pkg
                val record = FailedAttemptEntity(
                    packageName = pkg,
                    appName = appName,
                    gateMethod = gate,
                    timestamp = System.currentTimeMillis(),
                    failureReason = reason
                )
                db.failedAttemptDao().insert(record)

                // Check for intruder selfie
                val selfieManager = IntruderSelfieManager.getInstance(this@AppLockGateActivity)
                selfieManager.maybeCaptureSelfie(
                    activity = this@AppLockGateActivity,
                    packageName = pkg,
                    appName = appName,
                    gateMethod = gate,
                    failureReason = reason,
                    attemptNumber = consecutiveFailures
                )
            }
        }

        // Check for fake crash
        lifecycleScope.launch(Dispatchers.IO) {
            val settingsManager = SecuritySettingsManager.getInstance(this@AppLockGateActivity)
            if (settingsManager.isFakeCrashEnabled()) {
                runOnUiThread {
                    val intent = android.content.Intent(this@AppLockGateActivity, FakeCrashActivity::class.java)
                    intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        }
    }

    /** Reset failure counter on successful unlock. */
    private fun resetFailureCounter() {
        consecutiveFailures = 0
    }

    private fun getAppLabel(packageName: String): String? {
        return try {
            packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(packageName, 0)
            ).toString()
        } catch (e: Exception) {
            null
        }
    }
}