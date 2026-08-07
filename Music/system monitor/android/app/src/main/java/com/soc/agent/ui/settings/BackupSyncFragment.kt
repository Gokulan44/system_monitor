package com.soc.agent.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch
import com.soc.agent.R
import com.soc.agent.security.BackupManager

/**
 * BackupSyncFragment — Surface backup/restore and sync controls.
 */
class BackupSyncFragment : Fragment() {

    private var toolbar: MaterialToolbar? = null
    private var tvLastBackup: TextView? = null
    private var tvBackupSize: TextView? = null
    private var tvSyncStatus: TextView? = null
    private var tvLastSync: TextView? = null
    private var btnBackupNow: MaterialButton? = null
    private var btnRestore: MaterialButton? = null
    private var btnSyncNow: MaterialButton? = null
    private var switchAutoSync: MaterialSwitch? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_backup_sync, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupToolbar()
        setupListeners()
    }

    private fun bindViews(view: View) {
        toolbar = view.findViewById(R.id.toolbar_backup)
        tvLastBackup = view.findViewById(R.id.tv_last_backup)
        tvBackupSize = view.findViewById(R.id.tv_backup_size)
        tvSyncStatus = view.findViewById(R.id.tv_sync_status)
        tvLastSync = view.findViewById(R.id.tv_last_sync)
        btnBackupNow = view.findViewById(R.id.btn_backup_now)
        btnRestore = view.findViewById(R.id.btn_restore)
        btnSyncNow = view.findViewById(R.id.btn_sync_now)
        switchAutoSync = view.findViewById(R.id.switch_auto_sync)
    }

    private fun setupToolbar() {
        toolbar?.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupListeners() {
        btnBackupNow?.setOnClickListener {
            Toast.makeText(requireContext(), "Backup started...", Toast.LENGTH_SHORT).show()
        }
        btnRestore?.setOnClickListener {
            Toast.makeText(requireContext(), "Restore started...", Toast.LENGTH_SHORT).show()
        }
        btnSyncNow?.setOnClickListener {
            Toast.makeText(requireContext(), "Syncing data...", Toast.LENGTH_SHORT).show()
        }
    }
}
