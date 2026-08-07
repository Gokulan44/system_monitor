package com.soc.agent.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.soc.agent.R

class HelpSupportFragment : Fragment() {

    private var etFeedback: EditText? = null
    private var btnSubmitFeedback: View? = null
    private var btnUserGuide: View? = null
    private var btnContactSupport: View? = null
    private var btnReportBug: View? = null
    private var btnRequestFeature: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help_support, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupToolbar(view)
        setupButtons()
        setupFeedback()
    }

    private fun initViews(view: View) {
        etFeedback = view.findViewById(R.id.et_feedback_message)
        btnSubmitFeedback = view.findViewById(R.id.btn_submit_feedback)
        btnUserGuide = view.findViewById(R.id.btn_user_guide)
        btnContactSupport = view.findViewById(R.id.btn_contact_support)
        btnReportBug = view.findViewById(R.id.btn_report_bug)
        btnRequestFeature = view.findViewById(R.id.btn_feature_request)
    }

    private fun setupToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_help)
        toolbar?.setNavigationIcon(R.drawable.ic_back)
        toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        toolbar?.title = "Help & Support"
    }

    private fun setupButtons() {
        btnUserGuide?.setOnClickListener { openUserGuide() }
        btnContactSupport?.setOnClickListener { openContactSupport() }
        btnReportBug?.setOnClickListener { openReportBug() }
        btnRequestFeature?.setOnClickListener { openRequestFeature() }
    }

    private fun setupFeedback() {
        btnSubmitFeedback?.setOnClickListener {
            val feedback = etFeedback?.text?.toString()?.trim() ?: ""

            if (feedback.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your feedback", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            submitFeedback(feedback)
        }
    }

    private fun openUserGuide() {
        Toast.makeText(requireContext(), "Opening User Guide...", Toast.LENGTH_SHORT).show()
    }

    private fun openContactSupport() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("support@socagent.com"))
            putExtra(Intent.EXTRA_SUBJECT, "SOC Agent - Support Request")
            putExtra(Intent.EXTRA_TEXT, getDeviceInfoFooter())
        }
        startActivity(Intent.createChooser(intent, "Contact Support"))
    }

    private fun openReportBug() {
        val deviceInfo = buildString {
            appendLine("--- Bug Report ---")
            appendLine()
            appendLine("Device: ${Build.MANUFACTURER} ${Build.MODEL}")
            appendLine("Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})")
            appendLine("App Version: ${getAppVersion()}")
            appendLine()
            appendLine("Steps to reproduce:")
            appendLine("1. ")
            appendLine("2. ")
            appendLine("3. ")
            appendLine()
            appendLine("Expected behavior:")
            appendLine()
            appendLine("Actual behavior:")
        }

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("bugs@socagent.com"))
            putExtra(Intent.EXTRA_SUBJECT, "SOC Agent - Bug Report")
            putExtra(Intent.EXTRA_TEXT, deviceInfo)
        }
        startActivity(Intent.createChooser(intent, "Report Bug"))
    }

    private fun openRequestFeature() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("features@socagent.com"))
            putExtra(Intent.EXTRA_SUBJECT, "SOC Agent - Feature Request")
            putExtra(Intent.EXTRA_TEXT, buildString {
                appendLine("Feature Request")
                appendLine()
                appendLine("Feature description:")
                appendLine()
                appendLine("Use case:")
                appendLine()
                appendLine(getDeviceInfoFooter())
            })
        }
        startActivity(Intent.createChooser(intent, "Request Feature"))
    }

    private fun submitFeedback(feedback: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("feedback@socagent.com"))
            putExtra(Intent.EXTRA_SUBJECT, "SOC Agent - User Feedback")
            putExtra(Intent.EXTRA_TEXT, buildString {
                appendLine(feedback)
                appendLine()
                appendLine(getDeviceInfoFooter())
            })
        }
        startActivity(Intent.createChooser(intent, "Send Feedback"))
        etFeedback?.text?.clear()
        Toast.makeText(requireContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show()
    }

    private fun getAppVersion(): String {
        return try {
            val pInfo = requireContext().packageManager.getPackageInfo(requireContext().packageName, 0)
            pInfo.versionName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    private fun getDeviceInfoFooter(): String {
        return buildString {
            appendLine("---")
            appendLine("Device: ${Build.MANUFACTURER} ${Build.MODEL}")
            appendLine("Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})")
            appendLine("App Version: ${getAppVersion()}")
        }
    }

    data class FAQItem(
        val question: String,
        val answer: String
    )
}
