package com.soc.agent.ui.applock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.soc.agent.database.AppDatabase
import com.soc.agent.databinding.FragmentLockedAppsBinding
import com.soc.agent.ui.adapters.AppLockRow
import com.soc.agent.ui.adapters.AppLockRowAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * "Locked Apps" tab of the App Lock module. Lists everything in the locked set
 * from LockedAppDao, each row offering to remove the lock. Refreshes on resume
 * so removals from other screens and DB changes are reflected immediately.
 */
class LockedAppsFragment : Fragment() {

    private var _binding: FragmentLockedAppsBinding? = null
    private val binding get() = _binding!!

    private val adapter = AppLockRowAdapter { row -> removeLock(row) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLockedAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLocked.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLocked.adapter = adapter
        loadLocked()
    }

    override fun onResume() {
        super.onResume()
        loadLocked()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadLocked() {
        viewLifecycleOwner.lifecycleScope.launch {
            val dao = AppDatabase.getInstance(requireContext()).lockedAppDao()
            val rows = withContext(Dispatchers.IO) { dao.getLockedAppsSorted() }.map { e ->
                val icon = runCatching {
                    requireContext().packageManager.getApplicationIcon(e.packageName)
                }.getOrNull()
                AppLockRow(
                    packageName = e.packageName,
                    name = e.name.ifBlank { e.packageName },
                    subtitle = (if (e.lockMethod == "pattern") "Pattern" else "PIN") +
                        " \u00b7 since " + android.text.format.DateUtils.getRelativeTimeSpanString(
                            e.addedAt, System.currentTimeMillis(), android.text.format.DateUtils.MINUTE_IN_MILLIS
                        ).toString(),
                    actionLabel = "Remove",
                    icon = icon
                )
            }
            adapter.submitList(rows)
            binding.tvEmpty.visibility = if (rows.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun removeLock(row: AppLockRow) {
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext())
                    .lockedAppDao()
                    .removeLockedApp(row.packageName)
            }
            loadLocked()
        }
    }
}