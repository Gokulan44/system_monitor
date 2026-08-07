package com.soc.agent.ui.applock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.LockedAppEntity
import com.soc.agent.databinding.FragmentAddAppLockBinding
import com.soc.agent.security.AppLockPolicy
import com.soc.agent.services.AppInventory
import com.soc.agent.ui.adapters.AppLockRow
import com.soc.agent.ui.adapters.AppLockRowAdapter
import com.soc.agent.utils.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * "Add App Lock" tab of the App Lock module. Shows every installed app that is
 * not already in the locked set and not in [AppLockPolicy.RESERVED]; tapping
 * "Lock" inserts a [LockedAppEntity] carrying the currently selected gate
 * method ([Prefs.lockMethod]). The list refreshes on resume so apps locked from
 * this screen disappear into the Locked Apps tab.
 */
class AddAppLockFragment : Fragment() {

    private var _binding: FragmentAddAppLockBinding? = null
    private val binding get() = _binding!!

    private val adapter = AppLockRowAdapter { row -> lockApp(row) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAppLockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAdd.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdd.adapter = adapter
        loadCandidates()
    }

    override fun onResume() {
        super.onResume()
        loadCandidates()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadCandidates() {
        viewLifecycleOwner.lifecycleScope.launch {
            val context = requireContext()
            val dao = AppDatabase.getInstance(context).lockedAppDao()
            val (installed, locked) = withContext(Dispatchers.IO) {
                val apps = AppInventory.scan(context)
                val lockedSet = dao.getEnabledPackages().toSet()
                apps to lockedSet
            }
            val rows = installed
                .filter { it.packageName !in AppLockPolicy.RESERVED }
                .filter { it.packageName !in locked }
                .map { app ->
                    val icon = runCatching {
                        context.packageManager.getApplicationIcon(app.packageName)
                    }.getOrNull()
                    AppLockRow(
                        packageName = app.packageName,
                        name = app.name,
                        subtitle = app.packageName,
                        actionLabel = "Lock",
                        icon = icon
                    )
                }
            adapter.submitList(rows)
            binding.tvEmptyAdd.visibility = if (rows.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun lockApp(row: AppLockRow) {
        if (!AppLockPolicy.isConfigured()) {
            Toast.makeText(requireContext(), "Set an unlock gate first", Toast.LENGTH_SHORT).show()
            return
        }
        val method = Prefs.lockMethod.ifBlank { "pin" }
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).lockedAppDao().insertLockedApp(
                    LockedAppEntity(
                        packageName = row.packageName,
                        name = row.name,
                        lockMethod = method,
                        addedAt = System.currentTimeMillis(),
                        enabled = true
                    )
                )
            }
            Toast.makeText(requireContext(), "${row.name} is now locked", Toast.LENGTH_SHORT).show()
            loadCandidates()
        }
    }
}