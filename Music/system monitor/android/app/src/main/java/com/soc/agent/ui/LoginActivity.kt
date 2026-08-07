package com.soc.agent.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.soc.agent.R
import com.soc.agent.data.SecurityRepository
import com.soc.agent.databinding.ActivityLoginBinding
import com.soc.agent.utils.Prefs
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * First-run registration screen (also the launcher activity).
 *
 * Flow:
 *  - If the device is already configured ([Prefs.isConfigured]) we skip the form and
 *    go straight to the PIN gate ([PinLockActivity] in verify mode).
 *  - Otherwise the user enters the SOC server URL, the agent API key and a device
 *    name. On "Connect" the repository registers this agent with the server; when
 *    registration succeeds we either jump straight to [MainActivity] or, when the
 *    "Protect with PIN" switch is on, to [PinLockActivity] in setup mode first.
 *
 * The agent id is a stable random UUID persisted in [Prefs] so re-registration
 * updates the same device row on the server.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val repository: SecurityRepository by lazy {
        SecurityRepository.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Already registered on a previous run: PIN gate, then main screen.
        if (Prefs.isConfigured()) {
            startActivity(
                Intent(this, PinLockActivity::class.java).apply {
                    putExtra(PinLockActivity.EXTRA_MODE, PinLockActivity.MODE_VERIFY)
                }
            )
            finish()
            return
        }

        // Sensible default device name based on the hardware.
        binding.etDeviceName.setText("${Build.MANUFACTURER} ${Build.MODEL}".trim())
        binding.btnConnect.setOnClickListener { attemptRegister() }
    }

    /**
     * Validates the three inputs, persists the connection config in [Prefs] and
     * calls [SecurityRepository.register] on the main dispatcher's coroutine.
     * Shows progress while the network call is in flight and surfaces errors
     * through [repository.lastError] or the thrown exception.
     */
    private fun attemptRegister() {
        val serverUrl = binding.etServerUrl.text?.toString()?.trim().orEmpty()
        val apiKey = binding.etApiKey.text?.toString()?.trim().orEmpty()
        val deviceName = binding.etDeviceName.text?.toString()?.trim().orEmpty()

        var valid = true
        if (serverUrl.isEmpty()) {
            binding.tilServerUrl.error = "Server URL is required"
            valid = false
        } else if (!serverUrl.startsWith("http://") && !serverUrl.startsWith("https://")) {
            binding.tilServerUrl.error = "Must start with http:// or https://"
            valid = false
        } else {
            binding.tilServerUrl.error = null
        }
        if (apiKey.isEmpty()) {
            binding.tilApiKey.error = "API key is required"
            valid = false
        } else {
            binding.tilApiKey.error = null
        }
        if (deviceName.isEmpty()) {
            binding.tilDeviceName.error = "Device name is required"
            valid = false
        } else {
            binding.tilDeviceName.error = null
        }
        if (!valid) return

        // Persist the connection config; the repository reads it from Prefs.
        Prefs.serverUrl = serverUrl.trimEnd('/')
        Prefs.apiKey = apiKey
        Prefs.deviceName = deviceName
        if (Prefs.agentId.isNullOrBlank()) {
            Prefs.agentId = UUID.randomUUID().toString()
        }

        setBusy(true)
        lifecycleScope.launch {
            try {
                repository.register()
                val error = repository.lastError
                if (error != null) {
                    setBusy(false)
                    Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).show()
                    return@launch
                }
                setBusy(false)

                val next = if (binding.switchPin.isChecked) {
                    Intent(this@LoginActivity, PinLockActivity::class.java).apply {
                        putExtra(PinLockActivity.EXTRA_MODE, PinLockActivity.MODE_SETUP)
                    }
                } else {
                    Intent(this@LoginActivity, MainActivity::class.java)
                }
                startActivity(next)
                finish()
            } catch (e: Exception) {
                setBusy(false)
                Snackbar.make(
                    binding.root,
                    "Connection failed: ${e.message ?: "unknown error"}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setBusy(busy: Boolean) {
        binding.btnConnect.isEnabled = !busy
        binding.progressLogin.visibility = if (busy) View.VISIBLE else View.GONE
    }
}
