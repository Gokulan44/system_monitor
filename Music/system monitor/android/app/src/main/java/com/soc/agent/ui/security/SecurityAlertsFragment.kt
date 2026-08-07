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
import com.soc.agent.databinding.FragmentSecurityAlertsBinding
import com.soc.agent.ui.adapters.ThreatAdapter
import com.soc.agent.ui.adapters.ThreatRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Alerts tab of the Security section. Lists persisted [AlertEntity] rows from
 * AlertDao — local device alerts plus anything pushed from the SOC server.
 * Reloads on resume.
 */
class SecurityAlertsFragment : Fragment() {

    private var _binding: FragmentSecurityAlertsBinding? = null
    private val binding get() = _binding!!

    private val adapter = ThreatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecurityAlertsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAlerts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAlerts.adapter = adapter
        loadAlerts()
    }

    override fun onResume() {
        super.onResume()
        loadAlerts()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadAlerts() {
        viewLifecycleOwner.lifecycleScope.launch {
            val alerts = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).alertDao().getAlerts()
            }
            adapter.submitList(alerts.map {
                ThreatRow(
                    title = it.title,
                    severity = it.level,
                    detail = it.message,
                    timestamp = it.createdAt
                )
            })
            binding.tvEmpty.visibility = if (alerts.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}
