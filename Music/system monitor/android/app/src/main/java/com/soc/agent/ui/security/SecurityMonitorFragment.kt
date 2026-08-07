package com.soc.agent.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.soc.agent.databinding.FragmentSecurityBinding

/**
 * Security section: a TabLayout + ViewPager2 with four tabs:
 *  - Apps: installed applications with risk badges ([SecurityAppsFragment]).
 *  - Permissions: grouped granted-permission counts ([SecurityPermissionsFragment]).
 *  - Threats: persisted threat entities from ScanDao ([SecurityThreatsFragment]).
 *  - Alerts: persisted alert entities from AlertDao ([SecurityAlertsFragment]).
 */
class SecurityMonitorFragment : Fragment() {

    private var _binding: FragmentSecurityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecurityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagerSecurity.adapter = SecurityPagerAdapter(this)
        TabLayoutMediator(binding.tabSecurity, binding.pagerSecurity) { tab, position ->
            tab.text = when (position) {
                0 -> "Apps"
                1 -> "Permissions"
                2 -> "Threats"
                else -> "Alerts"
            }
        }.attach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private class SecurityPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SecurityAppsFragment()
                1 -> SecurityPermissionsFragment()
                2 -> SecurityThreatsFragment()
                else -> SecurityAlertsFragment()
            }
        }
    }
}
