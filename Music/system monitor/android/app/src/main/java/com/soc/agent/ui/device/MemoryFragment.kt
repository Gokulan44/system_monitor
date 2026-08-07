package com.soc.agent.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soc.agent.databinding.FragmentMemoryBinding
import com.soc.agent.services.MemoryMonitor
import com.soc.agent.utils.Formatters
import java.util.Locale

class MemoryFragment : Fragment() {

    private var _binding: FragmentMemoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun refresh() {
        val sample = MemoryMonitor(requireContext()).sample()

        binding.tvMemUsed.text = Formatters.bytes(sample.usedB)
        binding.tvMemTotal.text = "of ${Formatters.bytes(sample.totalB)}"
        binding.tvMemFree.text = "${Formatters.bytes(sample.freeB)} free"
        binding.tvMemPct.text = String.format(Locale.US, "%.0f%% used", sample.usagePct)
        binding.pbMemory.progress = sample.usagePct.toInt().coerceIn(0, 100)

        val swapTotal = sample.swapTotalB ?: 0L
        val swapUsed = sample.swapUsedB ?: 0L

        if (swapTotal > 0L) {
            val swapPct = (swapUsed * 100f / swapTotal).coerceIn(0f, 100f)
            binding.pbSwap.progress = swapPct.toInt()
            binding.tvSwap.text =
                "${Formatters.bytes(swapUsed)} / ${Formatters.bytes(swapTotal)}"
        } else {
            binding.pbSwap.progress = 0
            binding.tvSwap.text = "No swap"
        }
    }
}
