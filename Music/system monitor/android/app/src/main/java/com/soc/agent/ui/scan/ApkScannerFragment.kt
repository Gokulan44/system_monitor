package com.soc.agent.ui.scan

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.soc.agent.R
import com.soc.agent.api.dto.ScanRequest
import com.soc.agent.data.SecurityRepository
import com.soc.agent.security.ApkScanner
import com.soc.agent.utils.Formatters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ApkScannerFragment : Fragment() {

    private var pickedUri: Uri? = null
    private val repository by lazy { SecurityRepository.getInstance(requireContext()) }

    private var btnPickApk: Button? = null
    private var tvFileName: TextView? = null
    private var btnAnalyze: Button? = null
    private var pbAnalyze: ProgressBar? = null
    private var cardResult: View? = null
    private var tvResultVerdict: TextView? = null
    private var tvResultSeverity: TextView? = null
    private var tvResultHash: TextView? = null
    private var tvResultDetail: TextView? = null

    private val pickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { onFilePicked(it) }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apk_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        btnPickApk?.setOnClickListener { pickerLauncher.launch("application/vnd.android.package-archive") }
        btnAnalyze?.setOnClickListener { analyze() }
    }

    private fun bindViews(view: View) {
        btnPickApk = view.findViewById(R.id.btn_pick_apk)
        tvFileName = view.findViewById(R.id.tv_file_name)
        btnAnalyze = view.findViewById(R.id.btn_analyze)
        pbAnalyze = view.findViewById(R.id.pb_analyze)
        cardResult = view.findViewById(R.id.card_result)
        tvResultVerdict = view.findViewById(R.id.tv_result_verdict)
        tvResultSeverity = view.findViewById(R.id.tv_result_severity)
        tvResultHash = view.findViewById(R.id.tv_result_hash)
        tvResultDetail = view.findViewById(R.id.tv_result_detail)
    }

    private fun onFilePicked(uri: Uri) {
        pickedUri = uri
        tvFileName?.text = uri.lastPathSegment ?: uri.toString()
        btnAnalyze?.isEnabled = true
        cardResult?.visibility = View.GONE
    }

    private fun analyze() {
        val uri = pickedUri ?: return
        btnAnalyze?.isEnabled = false
        pbAnalyze?.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val file = withContext(Dispatchers.IO) { copyToCache(uri) }
                val dto = withContext(Dispatchers.IO) {
                    ApkScanner(requireContext()).scanApk(file)
                }
                val response = repository.runScan(
                    ScanRequest(scanType = "apk", startedAt = Formatters.isoNow(), items = listOf(dto))
                )
                showResult(dto)
            } catch (e: Exception) {
                cardResult?.visibility = View.VISIBLE
                tvResultVerdict?.text = "Analysis failed"
                tvResultVerdict?.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.soc_red)
                )
                tvResultSeverity?.text = e.message ?: "Unknown error"
                tvResultHash?.text = ""
                tvResultDetail?.text = ""
            } finally {
                pbAnalyze?.visibility = View.GONE
                btnAnalyze?.isEnabled = true
            }
        }
    }

    private fun showResult(dto: com.soc.agent.api.dto.ScanItemDto) {
        cardResult?.visibility = View.VISIBLE
        tvResultVerdict?.text = dto.verdict.uppercase()
        tvResultSeverity?.text = "Severity: ${dto.severity}"
        tvResultHash?.text = dto.hash ?: ""
        tvResultDetail?.text = dto.detail
    }

    private fun copyToCache(uri: Uri): File {
        val ctx = requireContext()
        val dest = File(ctx.cacheQueueDir(), "picked_${System.currentTimeMillis()}.apk")
        ctx.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(dest).use { output ->
                input.copyTo(output)
            }
        }
        return dest
    }

    private fun Context.cacheQueueDir(): File {
        val dir = File(cacheDir, "apks")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }
}
