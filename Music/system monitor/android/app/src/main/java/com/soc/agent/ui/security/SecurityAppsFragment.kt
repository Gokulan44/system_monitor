package com.soc.agent.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.soc.agent.R
import com.soc.agent.databinding.FragmentSecurityAppsBinding
import com.soc.agent.services.AppInventory
import com.soc.agent.ui.adapters.AppAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Apps tab of the Security section. Lists every installed application with its
 * icon, package name, memory footprint and a colored risk badge, sourced from
 * [AppInventory] and rendered by [AppAdapter]. Reloads whenever the tab becomes
 * visible again so freshly synced inventories show up.
 */
class SecurityAppsFragment : Fragment() {

    private var _binding: FragmentSecurityAppsBinding? = null
    private val binding get() = _binding!!

    private val adapter = AppAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecurityAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvApps.layoutManager = LinearLayoutManager(requireContext())
        binding.rvApps.adapter = adapter
        loadApps()
    }

    override fun onResume() {
        super.onResume()
        loadApps()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadApps() {
        viewLifecycleOwner.lifecycleScope.launch {
            val apps = withContext(Dispatchers.IO) {
                AppInventory.scan(requireContext())
            }
            adapter.submitList(apps)
            binding.tvEmpty.visibility = if (apps.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}
