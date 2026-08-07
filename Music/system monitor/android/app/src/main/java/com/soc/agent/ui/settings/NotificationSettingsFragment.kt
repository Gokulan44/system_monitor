package com.soc.agent.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.materialswitch.MaterialSwitch
import com.soc.agent.R

/**
 * NotificationSettingsFragment — Manages notification preferences.
 */
class NotificationSettingsFragment : Fragment() {

    private var toolbar: MaterialToolbar? = null
    private var switchPush: MaterialSwitch? = null
    private var switchEmail: MaterialSwitch? = null
    private var switchSecurity: MaterialSwitch? = null
    private var switchSystem: MaterialSwitch? = null
    private var switchSound: MaterialSwitch? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupToolbar()
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar_notifications)
        switchPush = view.findViewById(R.id.switch_push_notifications)
        switchEmail = view.findViewById(R.id.switch_email_notifications)
        switchSecurity = view.findViewById(R.id.switch_security_alerts)
        switchSystem = view.findViewById(R.id.switch_system_updates)
        switchSound = view.findViewById(R.id.switch_sound_vibration)
    }

    private fun setupToolbar() {
        toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
