package com.soc.agent.ui.settings

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.soc.agent.R
import com.soc.agent.services.DeviceInfoCollector

/**
 * DevicesFragment — Surface active and registered endpoint details.
 */
class DevicesFragment : Fragment() {

    private var toolbar: MaterialToolbar? = null
    private var tvDeviceName: TextView? = null
    private var tvDeviceModel: TextView? = null
    private var tvOsVersion: TextView? = null
    private var tvDeviceHealth: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupToolbar()
        populateDeviceInfo()
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar_devices)
        tvDeviceName = view.findViewById(R.id.tv_device_name)
        tvDeviceModel = view.findViewById(R.id.tv_device_model)
        tvOsVersion = view.findViewById(R.id.tv_os_version)
        tvDeviceHealth = view.findViewById(R.id.tv_device_health)
    }

    private fun setupToolbar() {
        toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun populateDeviceInfo() {
        val info = DeviceInfoCollector.collect(requireContext())
        tvDeviceName?.text = "${info.manufacturer} ${info.model}"
        tvDeviceModel?.text = info.model
        tvOsVersion?.text = "Android ${info.osVersion} (API ${Build.VERSION.SDK_INT})"
        tvDeviceHealth?.text = "Good"
    }
}
