package com.soc.agent.ui.appusage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.soc.agent.database.AppDatabase
import com.soc.agent.databinding.FragmentAppUsageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppUsageContainerFragment : Fragment() {

    private var _binding: FragmentAppUsageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppUsageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadHeader()
    }

    override fun onResume() {
        super.onResume()
        loadHeader()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun loadHeader() {
        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())
            val today = SimpleDateFormat("yyyyMMdd", Locale.US).format(Date()).toInt()

            val todayMs = withContext(Dispatchers.IO) {
                db.dailyUsageDao().getTotalTimeForDate(today) ?: 0L
            }
            binding.tvScreenTime.text = "Today: ${formatDuration(todayMs)}"

            val mostUsed = withContext(Dispatchers.IO) {
                db.appUsageDao().getAll().maxByOrNull { it.totalTimeMs }
            }
            binding.tvMostUsed.text = "Most used: ${mostUsed?.appName ?: "None"}"

            val weekMs = withContext(Dispatchers.IO) {
                db.weeklyUsageDao().getRecent(1).firstOrNull()?.totalTimeMs ?: 0L
            }
            binding.tvWeeklySummary.text = "This week: ${formatDuration(weekMs)}"
        }
    }

    private fun formatDuration(ms: Long): String {
        val hours = ms / 3600000
        val minutes = (ms % 3600000) / 60000
        return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    }
}