package com.soc.agent.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.soc.agent.R
import com.soc.agent.databinding.ActivityMainBinding
import com.soc.agent.ui.dashboard.DashboardFragment
import com.soc.agent.ui.device.DeviceMonitorFragment
import com.soc.agent.ui.security.SecurityMonitorFragment
import com.soc.agent.ui.applock.AppLockFragment
import com.soc.agent.ui.appusage.AppUsageContainerFragment

/**
 * Single-activity shell hosting the eleven main sections behind a
 * BottomNavigationView:
 *  - Dashboard    -> [DashboardFragment]
 *  - Device       -> [DeviceMonitorFragment]
 *  - Security     -> [SecurityMonitorFragment]
 *  - App Lock     -> [AppLockFragment]
 *  - App Usage    -> [AppUsageContainerFragment]
 *  - File Center  -> [FileCenterFragment]
 *  - Network      -> [NetworkCenterFragment]
 *  - Privacy      -> [PrivacyCenterFragment]
 *  - Notifications -> [NotificationCenterFragment]
 *  - Reports      -> [ReportsFragment]
 *  - Settings     -> [SettingsFragment]
 *
 * Fragments are switched with add/hide/show transactions so each section keeps
 * its state (scroll position, loaded data, open sub-tabs) while the user
 * navigates around. Fragment instances are cached in [fragments] keyed by the
 * bottom-nav item id; on configuration change they are recovered from the
 * FragmentManager by tag.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragments = mutableMapOf<Int, Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    showFragment(R.id.nav_dashboard, DashboardFragment())
                    true
                }
                R.id.nav_device -> {
                    showFragment(R.id.nav_device, DeviceMonitorFragment())
                    true
                }
                R.id.nav_security -> {
                    showFragment(R.id.nav_security, SecurityMonitorFragment())
                    true
                }
                R.id.nav_applock -> {
                    showFragment(R.id.nav_applock, AppLockFragment())
                    true
                }
                R.id.nav_appusage -> {
                    showFragment(R.id.nav_appusage, AppUsageContainerFragment())
                    true
                }
                R.id.nav_filecenter -> {
                    showFragment(R.id.nav_filecenter, FileCenterFragment())
                    true
                }
                R.id.nav_network -> {
                    showFragment(R.id.nav_network, NetworkCenterFragment())
                    true
                }
                R.id.nav_privacy -> {
                    showFragment(R.id.nav_privacy, PrivacyCenterFragment())
                    true
                }
                R.id.nav_notification -> {
                    showFragment(R.id.nav_notification, NotificationCenterFragment())
                    true
                }
                R.id.nav_reports -> {
                    showFragment(R.id.nav_reports, ReportsFragment())
                    true
                }
                R.id.nav_settings -> {
                    showFragment(R.id.nav_settings, SettingsFragment())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            showFragment(R.id.nav_dashboard, DashboardFragment())
        } else {
            // Re-attach to fragments the FragmentManager restored for us.
            val ids = listOf(
                R.id.nav_dashboard, R.id.nav_device, R.id.nav_security,
                R.id.nav_applock, R.id.nav_appusage, R.id.nav_filecenter,
                R.id.nav_network, R.id.nav_privacy, R.id.nav_notification,
                R.id.nav_reports, R.id.nav_settings
            )
            ids.forEach { id ->
                supportFragmentManager.findFragmentByTag(tagFor(id))?.let {
                    fragments[id] = it
                }
            }
        }
    }

    private fun showFragment(itemId: Int, fragment: Fragment) {
        val target = fragments.getOrPut(itemId) { fragment }
        val ft = supportFragmentManager.beginTransaction()
        if (!target.isAdded) {
            ft.add(R.id.fragment_container, target, tagFor(itemId))
        }
        fragments.values.forEach { other ->
            if (other !== target) ft.hide(other)
        }
        ft.show(target)
        ft.commit()
    }

    private fun tagFor(itemId: Int): String = "frag_$itemId"
}
