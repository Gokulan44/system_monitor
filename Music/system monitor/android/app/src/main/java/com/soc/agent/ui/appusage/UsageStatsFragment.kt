package com.soc.agent.ui.appusage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.soc.agent.R
import com.soc.agent.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsageStatsFragment : Fragment() {

    private lateinit var tvTotalScreenTime: TextView
    private lateinit var tvMostUsedApp: TextView
    private lateinit var tvTotalLaunches: TextView
    private lateinit var tvActiveDays: TextView
    private lateinit var tvWeeklyAvg: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usage_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTotalScreenTime = view.findViewById(R.id.tvTotalScreenTime)
        tvMostUsedApp = view.findViewById(R.id.tvMostUsedApp)
        tvTotalLaunches = view.findViewById(R.id.tvTotalLaunches)
        tvActiveDays = view.findViewById(R.id.tvActiveDays)
        tvWeeklyAvg = view.findViewById(R.id.tvWeeklyAvg)

        loadStats()
    }

    private fun loadStats() {
        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())

            val totalMs: Long = withContext(Dispatchers.IO) {
                db.screenTimeDao().totalCount().toLong() * 60000L
            }
            tvTotalScreenTime.text = formatDuration(totalMs)

            val mostUsed = withContext(Dispatchers.IO) {
                db.appUsageDao().getAll().maxByOrNull { it.totalTimeMs }
            }
            tvMostUsedApp.text = mostUsed?.appName ?: "No data"

            val totalLaunches: Int = withContext(Dispatchers.IO) {
                db.launchCountDao().totalLaunches()
            }
            tvTotalLaunches.text = "$totalLaunches launches"

            val activeDays: Int = withContext(Dispatchers.IO) {
                db.dailyUsageDao().getRecent(100).map { it.date }.distinct().size
            }
            tvActiveDays.text = "$activeDays active days"

            val weeklyAvg = withContext(Dispatchers.IO) {
                db.weeklyUsageDao().getRecent(1).firstOrNull()
            }
            tvWeeklyAvg.text = if (weeklyAvg != null) {
                "This week: ${formatDuration(weeklyAvg.totalTimeMs)}"
            } else {
                "No weekly data"
            }
        }
    }

    private fun formatDuration(ms: Long): String {
        val hours = ms / 3600000
        val minutes = (ms % 3600000) / 60000
        return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    }
}