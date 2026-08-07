package com.soc.agent.ui.applock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.materialswitch.MaterialSwitch
import com.soc.agent.R
import com.soc.agent.security.SecuritySettingsManager

class SecuritySettingsFragment : Fragment() {

    private var toolbar: MaterialToolbar? = null
    private var switchFingerprint: MaterialSwitch? = null
    private var switchPin: MaterialSwitch? = null
    private var switchPattern: MaterialSwitch? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_security_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupToolbar()
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar_security)
        switchFingerprint = view.findViewById(R.id.switch_fingerprint)
        switchPin = view.findViewById(R.id.switch_pin)
        switchPattern = view.findViewById(R.id.switch_pattern)
    }

    private fun setupToolbar() {
        toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}