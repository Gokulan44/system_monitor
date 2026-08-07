package com.soc.agent.ui.applock

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.soc.agent.R
import com.soc.agent.databinding.FragmentBackupBinding
import com.soc.agent.security.BackupManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BackupFragment : Fragment() {

    private var _binding: FragmentBackupBinding? = null
    private val binding get() = _binding!!
    private val backupManager by lazy { BackupManager.getInstance(requireContext()) }

    private val filePicker = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { restoreBackup(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBackupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateBackup.setOnClickListener { createBackup() }
        binding.btnImportBackup.setOnClickListener { importBackup() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun createBackup() {
        binding.btnCreateBackup.isEnabled = false
        binding.btnCreateBackup.text = "Creating..."
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    backupManager.createBackup("App Lock Backup")
                }
                Toast.makeText(requireContext(), "Backup created successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Backup failed: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                if (_binding != null) {
                    binding.btnCreateBackup.isEnabled = true
                    binding.btnCreateBackup.text = "Create Backup"
                }
            }
        }
    }

    private fun importBackup() {
        filePicker.launch(arrayOf("application/json", "*/*"))
    }

    private fun restoreBackup(uri: Uri) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val success = withContext(Dispatchers.IO) {
                    backupManager.restoreBackupFromUri(uri)
                }
                if (success) {
                    Toast.makeText(requireContext(), "Backup restored successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to restore backup", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Restore failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}