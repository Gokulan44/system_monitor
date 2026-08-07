package com.soc.agent.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.R
import com.soc.agent.ui.applock.SecuritySettingsFragment

/**
 * Settings screen — displays eleven section cards in a vertical
 * RecyclerView.  Each card shows an icon, title, subtitle and a
 * trailing chevron.  Tapping a card navigates to the corresponding
 * sub-fragment (via the parent activity's FragmentManager) or opens
 * a dialog for "About" and "Log Out".
 *
 * The fragment is hosted inside [com.soc.agent.ui.MainActivity] as
 * the 11th tab of the BottomNavigationView.
 */
class SettingsFragment : Fragment() {

    private lateinit var rvSettings: RecyclerView

    // ────────────────────────────────────────────────────────────
    // Lifecycle
    // ────────────────────────────────────────────────────────────

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSettings = view.findViewById(R.id.rv_settings)
        rvSettings.layoutManager = LinearLayoutManager(requireContext())
        rvSettings.setHasFixedSize(true)

        rvSettings.adapter = SettingsSectionAdapter(buildSections())
    }

    // ────────────────────────────────────────────────────────────
    // Section definitions
    // ────────────────────────────────────────────────────────────

    /**
     * Builds the ordered list of all 11 settings sections.
     * Each entry wires its `onClick` lambda to either navigate
     * to a fragment or show a dialog.
     */
    private fun buildSections(): List<SettingsSection> = listOf(

        // 1 ─ User Profile
        SettingsSection(
            iconRes = R.drawable.ic_settings_person,
            title = getString(R.string.settings_title_profile),
            subtitle = getString(R.string.settings_subtitle_profile),
            onClick = { navigateTo(ProfileFragment(), "ProfileFragment") }
        ),

        // 2 ─ Security Settings
        SettingsSection(
            iconRes = R.drawable.ic_settings_shield,
            title = getString(R.string.settings_title_security),
            subtitle = getString(R.string.settings_subtitle_security),
            onClick = { navigateTo(SecurityFragment(), "SecurityFragment") }
        ),

        // 3 ─ Devices
        SettingsSection(
            iconRes = R.drawable.ic_settings_phone,
            title = getString(R.string.settings_title_devices),
            subtitle = getString(R.string.settings_subtitle_devices),
            onClick = { navigateTo(DevicesFragment(), "DevicesFragment") }
        ),

        // 4 ─ Privacy
        SettingsSection(
            iconRes = R.drawable.ic_nav_privacy,
            title = getString(R.string.settings_title_privacy),
            subtitle = getString(R.string.settings_subtitle_privacy),
            onClick = { navigateTo(PrivacyFragment(), "PrivacyFragment") }
        ),

        // 5 ─ Notifications
        SettingsSection(
            iconRes = R.drawable.ic_settings_bell,
            title = getString(R.string.settings_title_notifications),
            subtitle = getString(R.string.settings_subtitle_notifications),
            onClick = { navigateTo(NotificationSettingsFragment(), "NotificationSettingsFragment") }
        ),

        // 6 ─ Preferences
        SettingsSection(
            iconRes = R.drawable.ic_nav_settings,
            title = getString(R.string.settings_title_preferences),
            subtitle = getString(R.string.settings_subtitle_preferences),
            onClick = { navigateTo(PreferencesFragment(), "PreferencesFragment") }
        ),

        // 7 ─ Activity History
        SettingsSection(
            iconRes = R.drawable.ic_settings_history,
            title = getString(R.string.settings_title_history),
            subtitle = getString(R.string.settings_subtitle_history),
            onClick = { navigateTo(ActivityHistoryFragment(), "ActivityHistoryFragment") }
        ),

        // 8 ─ Backup and Sync
        SettingsSection(
            iconRes = R.drawable.ic_settings_cloud,
            title = getString(R.string.settings_title_backup),
            subtitle = getString(R.string.settings_subtitle_backup),
            onClick = { navigateTo(BackupSyncFragment(), "BackupSyncFragment") }
        ),

        // 9 ─ Help and Support
        SettingsSection(
            iconRes = R.drawable.ic_settings_help,
            title = getString(R.string.settings_title_help),
            subtitle = getString(R.string.settings_subtitle_help),
            onClick = { navigateTo(HelpSupportFragment(), "HelpSupportFragment") }
        ),

        // 10 ─ About
        SettingsSection(
            iconRes = R.drawable.ic_settings_info,
            title = getString(R.string.settings_title_about),
            subtitle = getString(R.string.settings_subtitle_about),
            onClick = { showAboutDialog() }
        ),

        // 11 ─ Log Out (destructive red icon)
        SettingsSection(
            iconRes = R.drawable.ic_settings_logout,
            title = getString(R.string.settings_title_logout),
            subtitle = getString(R.string.settings_subtitle_logout),
            isDestructive = true,
            onClick = { showLogoutConfirmDialog() }
        )
    )

    // ────────────────────────────────────────────────────────────
    // Navigation helpers
    // ────────────────────────────────────────────────────────────

    /**
     * Pushes [fragment] onto the back stack under the given [tag].
     * The parent activity's `fragment_container` is used as the
     * target view-id, which matches the existing navigation setup.
     */
    private fun navigateTo(fragment: Fragment, tag: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(tag)
            .commit()
    }

    // ────────────────────────────────────────────────────────────
    // Dialogs
    // ────────────────────────────────────────────────────────────

    /**
     * Shows an informational dialog with the app name, version
     * string and a short description.
     */
    private fun showAboutDialog() {
        val versionName = try {
            requireContext().packageManager
                .getPackageInfo(requireContext().packageName, 0)
                .versionName
        } catch (_: Exception) {
            "1.0.0"
        }

        AlertDialog.Builder(requireContext(), R.style.Theme_SOCAgent_Dialog)
            .setTitle(R.string.settings_title_about)
            .setMessage(
                getString(
                    R.string.settings_about_message,
                    getString(R.string.app_name),
                    versionName
                )
            )
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    /**
     * Shows a confirmation dialog before signing the user out.
     * On positive confirmation the user is redirected to the
     * [com.soc.agent.ui.LoginActivity] and the back stack is cleared.
     */
    private fun showLogoutConfirmDialog() {
        AlertDialog.Builder(requireContext(), R.style.Theme_SOCAgent_Dialog)
            .setTitle(R.string.settings_title_logout)
            .setMessage(R.string.settings_logout_message)
            .setPositiveButton(R.string.settings_logout_confirm) { _, _ ->
                performLogout()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    /**
     * Clears the activity back stack and restarts from the login
     * screen.  This is a best-effort approach that works for the
     * single-activity shell without needing DI or a global nav graph.
     */
    private fun performLogout() {
        Toast.makeText(
            requireContext(),
            R.string.settings_logout_toast,
            Toast.LENGTH_SHORT
        ).show()

        // Clear every cached fragment so nothing leaks across sessions.
        val fm = parentFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }

        // Launch the login activity with FLAG_ACTIVITY_NEW_TASK |
        // FLAG_ACTIVITY_CLEAR_TASK so the user cannot press back into
        // an authenticated session.
        val intent = android.content.Intent(
            requireContext(),
            com.soc.agent.ui.LoginActivity::class.java
        ).apply {
            flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or
                android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        requireActivity().finish()
    }
}
