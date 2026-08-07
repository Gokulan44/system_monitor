package com.soc.agent.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.soc.agent.R
import com.soc.agent.ui.viewmodel.ScanProgress
import com.soc.agent.ui.viewmodel.ScanStatus
import com.soc.agent.ui.viewmodel.ScanViewModel

class ScanCenterFragment : Fragment() {

    private val scanViewModel: ScanViewModel by activityViewModels()

    private var scrollScan: View? = null
    private var containerScan: View? = null
    private var btnQuickScan: Button? = null
    private var btnFullScan: Button? = null
    private var btnApkScan: Button? = null
    private var pbScan: ProgressBar? = null
    private var tvScanStatus: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan_center, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        btnQuickScan?.setOnClickListener { openSubScreen(QuickScanFragment()) }
        btnFullScan?.setOnClickListener { scanViewModel.runScan("full") }
        btnApkScan?.setOnClickListener { openSubScreen(ApkScannerFragment()) }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            scanViewModel.progress.collect { progress ->
                progress?.let { onScanProgress(it) }
            }
        }

        childFragmentManager.addOnBackStackChangedListener {
            if (childFragmentManager.backStackEntryCount == 0) {
                containerScan?.visibility = View.GONE
                scrollScan?.visibility = View.VISIBLE
            }
        }
    }

    private fun bindViews(view: View) {
        scrollScan = view.findViewById(R.id.scroll_scan)
        containerScan = view.findViewById(R.id.container_scan)
        btnQuickScan = view.findViewById(R.id.btn_quick_scan)
        btnFullScan = view.findViewById(R.id.btn_full_scan)
        btnApkScan = view.findViewById(R.id.btn_apk_scan)
        pbScan = view.findViewById(R.id.pb_scan)
        tvScanStatus = view.findViewById(R.id.tv_scan_status)
    }

    private fun openSubScreen(fragment: Fragment) {
        scrollScan?.visibility = View.GONE
        containerScan?.visibility = View.VISIBLE
        val containerId = containerScan?.id ?: return
        childFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun onScanProgress(progress: ScanProgress) {
        val running = progress.status == ScanStatus.RUNNING
        pbScan?.visibility = if (running) View.VISIBLE else View.GONE
        tvScanStatus?.visibility = if (running) View.VISIBLE else View.GONE
        tvScanStatus?.text = "${progress.message} (${progress.itemsScanned} items · ${progress.threatsFound} threats)"
    }
}
