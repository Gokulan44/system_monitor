package com.soc.agent.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.soc.agent.R
import com.soc.agent.ui.viewmodel.ScanProgress
import com.soc.agent.ui.viewmodel.ScanStatus
import com.soc.agent.ui.viewmodel.ScanViewModel

class QuickScanFragment : Fragment() {

    private val scanViewModel: ScanViewModel by activityViewModels()

    private var btnStartScan: Button? = null
    private var cardProgress: View? = null
    private var tvScanProgress: TextView? = null
    private var cardResult: View? = null
    private var tvResultGrade: TextView? = null
    private var tvResultScore: TextView? = null
    private var tvResultThreats: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quick_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        btnStartScan?.setOnClickListener { scanViewModel.runScan("quick") }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            scanViewModel.progress.collect { progress ->
                progress?.let { renderProgress(it) }
            }
        }
    }

    private fun bindViews(view: View) {
        btnStartScan = view.findViewById(R.id.btn_start_scan)
        cardProgress = view.findViewById(R.id.card_progress)
        tvScanProgress = view.findViewById(R.id.tv_scan_progress)
        cardResult = view.findViewById(R.id.card_result)
        tvResultGrade = view.findViewById(R.id.tv_result_grade)
        tvResultScore = view.findViewById(R.id.tv_result_score)
        tvResultThreats = view.findViewById(R.id.tv_result_threats)
    }

    private fun renderProgress(progress: ScanProgress) {
        val running = progress.status == ScanStatus.RUNNING
        btnStartScan?.isEnabled = !running
        btnStartScan?.text = if (running) "Scanning..." else "Start Quick Scan"
        cardProgress?.visibility = if (running) View.VISIBLE else View.GONE
        tvScanProgress?.text = progress.message

        if (progress.status == ScanStatus.COMPLETED) {
            cardResult?.visibility = View.VISIBLE
            tvResultGrade?.text = progress.grade
            tvResultScore?.text = "Score ${progress.score}"
            tvResultThreats?.text = "${progress.threatsFound} threats found"
        }
    }
}
