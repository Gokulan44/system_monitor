package com.soc.agent.ui.security

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.soc.agent.R
import com.soc.agent.databinding.FragmentSecurityPermissionsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Permissions tab of the Security section. Enumerates every installed package
 * (with its requested permissions) and aggregates the granted permissions by
 * permission group, rendering one progress row per group. Heavy PackageManager
 * work runs on the IO dispatcher.
 */
class SecurityPermissionsFragment : Fragment() {

    private var _binding: FragmentSecurityPermissionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecurityPermissionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPermissions()
    }

    override fun onResume() {
        super.onResume()
        loadPermissions()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadPermissions() {
        viewLifecycleOwner.lifecycleScope.launch {
            val grouped = withContext(Dispatchers.IO) { collectGroups() }
            renderGroups(grouped)
        }
    }

    /**
     * Returns a sorted map of permission-group name -> number of granted
     * permissions in that group across all installed apps.
     */
    private fun collectGroups(): Map<String, Int> {
        val pm = requireContext().packageManager
        val groups = LinkedHashMap<String, Int>()
        val packages = try {
            pm.getInstalledPackages(PackageManager.GET_PERMISSIONS)
        } catch (e: Exception) {
            emptyList()
        }
        for (pkg in packages) {
            val permissions = pkg.requestedPermissions ?: emptyArray()
            for (permission in permissions) {
                if (pm.checkPermission(permission, pkg.packageName) == PackageManager.PERMISSION_GRANTED) {
                    val group = permissionGroup(pm, permission)
                    groups[group] = (groups[group] ?: 0) + 1
                }
            }
        }
        return groups.toList().sortedByDescending { it.second }.toMap(LinkedHashMap())
    }

    private fun permissionGroup(pm: PackageManager, permission: String): String {
        return try {
            val info = pm.getPermissionInfo(permission, 0)
            info.group?.takeIf { it.isNotBlank() }?.let { group ->
                group.substringAfterLast('.')
            } ?: permission.substringAfterLast('.')
        } catch (e: Exception) {
            permission.substringAfterLast('.')
        }
    }

    private fun renderGroups(grouped: Map<String, Int>) {
        val container = binding.llPermissions
        container.removeAllViews()
        if (grouped.isEmpty()) {
            container.addView(
                TextView(requireContext()).apply {
                    text = "No granted permissions found"
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.soc_muted))
                    textSize = 13f
                    setPadding(0, dp(16), 0, dp(16))
                }
            )
            return
        }
        val maxCount = grouped.values.maxOrNull() ?: 1
        grouped.forEach { (group, count) ->
            val card = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(dp(12), dp(10), dp(12), dp(10))
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.soc_panel))
            }

            val header = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
            }
            header.addView(
                TextView(requireContext()).apply {
                    text = group
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.soc_text))
                    textSize = 14f
                    layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                }
            )
            header.addView(
                TextView(requireContext()).apply {
                    text = count.toString()
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.soc_cyan))
                    textSize = 14f
                }
            )
            card.addView(header)

            val bar = ProgressBar(requireContext(), null, android.R.attr.progressBarStyleHorizontal).apply {
                max = 100
                progress = ((count * 100) / maxCount).coerceIn(0, 100)
                progressTintList = android.content.res.ColorStateList.valueOf(
                    ContextCompat.getColor(requireContext(), R.color.soc_indigo)
                )
                progressBackgroundTintList = android.content.res.ColorStateList.valueOf(
                    ContextCompat.getColor(requireContext(), R.color.soc_panel2)
                )
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp(6)
                ).apply { topMargin = dp(6) }
            }
            card.addView(bar)
            container.addView(card, LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = dp(8) })
        }
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
