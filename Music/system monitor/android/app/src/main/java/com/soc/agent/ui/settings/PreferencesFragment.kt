package com.soc.agent.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.materialswitch.MaterialSwitch
import com.soc.agent.R
import com.soc.agent.data.SettingsRepository

/**
 * PreferencesFragment — User-facing app preferences screen.
 */
class PreferencesFragment : Fragment() {

    private var toolbar: MaterialToolbar? = null
    private var switchDarkMode: MaterialSwitch? = null
    private var switchAutoSync: MaterialSwitch? = null
    private var switchAutoUpdate: MaterialSwitch? = null
    private var switchAutoBackup: MaterialSwitch? = null
    private var tvCurrentTheme: TextView? = null
    private var tvCurrentLanguage: TextView? = null
    private var tvCurrentDateFormat: TextView? = null
    private var tvCurrentTimeFormat: TextView? = null
    private var btnResetPreferences: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupToolbar()
        setupListeners()
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar_preferences)
        switchDarkMode = view.findViewById(R.id.switch_dark_mode)
        switchAutoSync = view.findViewById(R.id.switch_auto_sync)
        switchAutoUpdate = view.findViewById(R.id.switch_auto_update)
        switchAutoBackup = view.findViewById(R.id.switch_auto_backup)
        tvCurrentTheme = view.findViewById(R.id.tv_current_theme)
        tvCurrentLanguage = view.findViewById(R.id.tv_current_language)
        tvCurrentDateFormat = view.findViewById(R.id.tv_current_date_format)
        tvCurrentTimeFormat = view.findViewById(R.id.tv_current_time_format)
        btnResetPreferences = view.findViewById(R.id.btn_reset_preferences)
    }

    private fun setupToolbar() {
        toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupListeners() {
        btnResetPreferences?.setOnClickListener {
            Toast.makeText(requireContext(), "Preferences reset to default", Toast.LENGTH_SHORT).show()
        }
    }
}
