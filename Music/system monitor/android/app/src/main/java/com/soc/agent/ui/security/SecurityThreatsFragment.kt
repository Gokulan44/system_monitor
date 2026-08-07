package com.soc.agent.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.soc.agent.R
import com.soc.agent.database.AppDatabase
import com.soc.agent.databinding.FragmentSecurityThreatsBinding
import com.soc.agent.ui.adapters.ThreatAdapter
import com.soc.agent.ui.adapters.ThreatRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Threats tab of the Security section. Lists persisted [ThreatEntity] rows from
 * ScanDao — i.e. detections recorded by the most recent scan runs. Reloads on
 * resume so detections pushed by the Scan tab appear immediately.
 */
class SecurityThreatsFragment : Fragment() {

    private var _binding: FragmentSecurityThreatsBinding? = null
    private val binding get() = _binding!!

    private val adapter = ThreatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecurityThreatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvThreats.layoutManager = LinearLayoutManager(requireContext())
        binding.rvThreats.adapter = adapter
        loadThreats()
    }

    override fun onResume() {
        super.onResume()
        loadThreats()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadThreats() {
        viewLifecycleOwner.lifecycleScope.launch {
            val threats = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).scanDao().getThreats()
            }
            adapter.submitList(threats.map {
                ThreatRow(
                    title = it.title,
                    severity = it.severity,
                    detail = it.detail,
                    timestamp = it.detectedAt
                )
            })
            binding.tvEmpty.visibility = if (threats.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}
