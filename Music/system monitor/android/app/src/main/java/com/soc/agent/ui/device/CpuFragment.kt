package com.soc.agent.ui.device

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.soc.agent.R
import com.soc.agent.databinding.FragmentCpuBinding
import com.soc.agent.services.CpuMonitor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

/**
 * CPU monitor tab. Shows the current load as a big number, temperature and
 * speed, a per-core load bar for every core, and a live line history of the
 * last 60 load samples (see [CpuHistoryView]). Samples are taken from
 * [CpuMonitor] every two seconds while the fragment is resumed.
 */
class CpuFragment : Fragment() {

    private var _binding: FragmentCpuBinding? = null
    private val binding get() = _binding!!

    private var refreshJob: Job? = null
    private val coreRows = mutableListOf<Pair<TextView, ProgressBar>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCpuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh()
    }

    override fun onResume() {
        super.onResume()
        startPolling()
    }

    override fun onPause() {
        stopPolling()
        super.onPause()
    }

    override fun onDestroyView() {
        stopPolling()
        _binding = null
        super.onDestroyView()
    }

    private fun startPolling() {
        refreshJob?.cancel()
        refreshJob = viewLifecycleOwner.lifecycleScope.launch {
            while (isActive) {
                refresh()
                delay(2000)
            }
        }
    }

    private fun stopPolling() {
        refreshJob?.cancel()
        refreshJob = null
    }

    private fun refresh() {
        val b = _binding ?: return
        viewLifecycleOwner.lifecycleScope.launch {
            val sample = withContext(Dispatchers.IO) { CpuMonitor(requireContext()).sample() }

            b.tvCpuLoad.text = String.format(Locale.US, "%.0f%%", sample.loadPct)
            b.tvCpuTemp.text = sample.tempC?.let { String.format(Locale.US, "%.1f°C", it) }
                ?: "temp n/a"
            b.tvCpuSpeed.text = String.format(Locale.US, "%.2f GHz", sample.speedGhz)
            b.cpuHistory.addSample(sample.loadPct.toFloat())
            renderCores(sample.perCore)
        }
    }

    private fun renderCores(perCore: List<Double>) {
        val b = _binding ?: return
        val container = b.llCores

        // Grow the cached row list if more cores appeared (e.g. after resume).
        while (coreRows.size < perCore.size) {
            val row = layoutInflater.inflate(R.layout.row_core, container, false)
            val label = row.findViewById<TextView>(R.id.tv_core_label)
            val bar = row.findViewById<ProgressBar>(R.id.pb_core)
            container.addView(row)
            coreRows.add(label to bar)
        }

        perCore.forEachIndexed { index, load ->
            val (label, bar) = coreRows[index]
            label.text = "C$index"
            label.visibility = View.VISIBLE
            bar.visibility = View.VISIBLE
            bar.progress = load.toInt().coerceIn(0, 100)
        }
        // Hide surplus rows when the core count shrank.
        for (index in perCore.size until coreRows.size) {
            coreRows[index].first.visibility = View.GONE
            coreRows[index].second.visibility = View.GONE
        }
    }
}

/**
 * Small custom view that renders the last [maxSamples] load samples as a line
 * chart with a subtle fill and 25% grid lines. Fed through [addSample] by the
 * CPU/memory monitors.
 */
class CpuHistoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val samples = ArrayDeque<Float>()
    private val maxSamples = 60

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3f * resources.displayMetrics.density
        color = ContextCompat.getColor(context, R.color.soc_cyan)
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = (ContextCompat.getColor(context, R.color.soc_cyan) and 0x00FFFFFF) or (0x33 shl 24)
    }

    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1f * resources.displayMetrics.density
        color = ContextCompat.getColor(context, R.color.soc_border)
    }

    /** Appends one sample and redraws; keeps at most [maxSamples] entries. */
    fun addSample(value: Float) {
        samples.addLast(value.coerceIn(0f, 100f))
        while (samples.size > maxSamples) samples.removeFirst()
        invalidate()
    }

    /** Replaces the whole history (e.g. samples loaded from the database). */
    fun setSamples(values: List<Float>) {
        samples.clear()
        values.takeLast(maxSamples).forEach { samples.addLast(it.coerceIn(0f, 100f)) }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        if (w <= 0f || h <= 0f) return

        // Horizontal grid lines at 0/25/50/75/100%.
        for (i in 0..4) {
            val y = h - (h * i / 4f)
            canvas.drawLine(0f, y, w, y, gridPaint)
        }
        if (samples.isEmpty()) return

        val step = w / maxSamples
        val path = Path()
        var first = true
        var index = 0
        for (value in samples) {
            val x = step * (maxSamples - samples.size + index) + step / 2f
            val y = h - (h * value / 100f)
            if (first) {
                path.moveTo(x, y)
                first = false
            } else {
                path.lineTo(x, y)
            }
            index++
        }

        // Fill under the line down to the baseline.
        val fill = Path(path)
        fill.lineTo(step * (maxSamples - 1) + step / 2f, h)
        fill.lineTo(step / 2f, h)
        fill.close()
        canvas.drawPath(fill, fillPaint)

        canvas.drawPath(path, linePaint)
    }
}
