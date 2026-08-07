package com.soc.agent.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.soc.agent.R
import com.soc.agent.databinding.ActivityFakeCrashBinding
import com.soc.agent.security.SecuritySettingsManager
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Fake crash dialog - shows a realistic-looking crash dialog when enabled
 * and a locked app unlock fails. This can deter intruders by making the
 * app appear broken rather than locked.
 */
class FakeCrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFakeCrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFakeCrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configure the crash message from settings
        lifecycleScope.launch(Dispatchers.IO) {
            val manager = SecuritySettingsManager.getInstance(this@FakeCrashActivity)
            val message = manager.getFakeCrashMessage()
            runOnUiThread {
                binding.tvCrashMessage.text = message
            }
        }

        // Buttons
        binding.btnOk.setOnClickListener { finish() }
        binding.btnReport.setOnClickListener {
            Toast.makeText(this, "Report sent", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Auto-dismiss after 10 seconds
        binding.root.postDelayed({ finish() }, 10_000)
    }
}