package com.soc.agent.ui.dashboard

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.soc.agent.R
import com.soc.agent.security.PolicyViolation
import com.soc.agent.ui.viewmodel.DashboardState
import com.soc.agent.ui.viewmodel.DashboardViewModel
import com.soc.agent.utils.Formatters
import java.util.Locale

class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()

    private var tvGrade: TextView? = null
    private var tvScore: TextView? = null
    private var tvCpu: TextView? = null
    private var tvMem: TextView? = null
    private var tvBattery: TextView? = null
    private var tvStorage: TextView? = null
    private var tvThreats: TextView? = null
    private var tvApps: TextView? = null
    private var tvLastSync: TextView? = null
    private var btnRefresh: View? = null
    private var llViolations: LinearLayout? = null
    private var tvBlockDomains: TextView? = null
    private var tvBlockUrls: TextView? = null
    private var tvBlockHashes: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        btnRefresh?.setOnClickListener { viewModel.refresh() }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                render(state)
            }
        }
        viewModel.refresh()
    }

    private fun bindViews(view: View) {
        tvGrade = view.findViewById(R.id.tv_grade)
        tvScore = view.findViewById(R.id.tv_score)
        tvCpu = view.findViewById(R.id.tv_cpu)
        tvMem = view.findViewById(R.id.tv_mem)
        tvBattery = view.findViewById(R.id.tv_battery)
        tvStorage = view.findViewById(R.id.tv_storage)
        tvThreats = view.findViewById(R.id.tv_threats)
        tvApps = view.findViewById(R.id.tv_apps)
        tvLastSync = view.findViewById(R.id.tv_last_sync)
        btnRefresh = view.findViewById(R.id.btn_refresh)
        llViolations = view.findViewById(R.id.ll_violations)
        tvBlockDomains = view.findViewById(R.id.tv_block_domains)
        tvBlockUrls = view.findViewById(R.id.tv_block_urls)
        tvBlockHashes = view.findViewById(R.id.tv_block_hashes)
    }

    private fun render(state: DashboardState) {
        tvGrade?.text = state.grade
        tvScore?.text = "${state.score} / 100"

        tvCpu?.text = String.format(Locale.US, "%.1f%%", state.cpuLoad)
        tvMem?.text = String.format(Locale.US, "%.1f%%", state.memPct)

        if (state.batteryPct >= 0) {
            tvBattery?.text = "${state.batteryPct}%"
        } else {
            tvBattery?.text = "N/A"
        }

        tvStorage?.text = String.format(Locale.US, "%.0f%%", state.storagePct)
        tvThreats?.text = state.threats.toString()
        tvApps?.text = state.apps.toString()

        tvLastSync?.text = if (state.lastSync > 0L) {
            "Last sync: ${Formatters.relativeTime(state.lastSync)}"
        } else {
            "Last sync: Never"
        }

        val domains = state.blocklist["domains"] ?: 0
        val urls = state.blocklist["urls"] ?: 0
        val hashes = state.blocklist["hashes"] ?: 0

        tvBlockDomains?.text = domains.toString()
        tvBlockUrls?.text = urls.toString()
        tvBlockHashes?.text = hashes.toString()

        renderViolations(state.violations)
    }

    private fun renderViolations(violations: List<PolicyViolation>) {
        val container = llViolations ?: return
        container.removeAllViews()

        if (violations.isEmpty()) {
            val emptyTv = TextView(requireContext()).apply {
                text = "✓ Device is compliant with active policies"
                setTextColor(ContextCompat.getColor(context, R.color.soc_green))
                textSize = 13f
            }
            container.addView(emptyTv)
            return
        }

        for (v in violations) {
            val tv = TextView(requireContext()).apply {
                text = "⚠️ [${v.name}] ${v.detail}"
                setTextColor(ContextCompat.getColor(context, R.color.soc_amber))
                textSize = 13f
                setTypeface(null, Typeface.BOLD)
                setPadding(0, 4, 0, 4)
            }
            container.addView(tv)
        }
    }
}
