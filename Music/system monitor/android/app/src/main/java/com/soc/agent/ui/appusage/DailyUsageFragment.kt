package com.soc.agent.ui.appusage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.soc.agent.R
import com.soc.agent.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * Shows daily usage breakdown: screen time per app for today.
 */
class DailyUsageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var tvTotalTime: TextView
    private lateinit var adapter: DailyUsageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_usage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvDailyUsage)
        tvEmpty = view.findViewById(R.id.tvEmpty)
        tvTotalTime = view.findViewById(R.id.tvTotalTime)

        adapter = DailyUsageAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadDailyUsage()
    }

    private fun loadDailyUsage() {
        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())
            val today = SimpleDateFormat("yyyyMMdd", Locale.US).format(Date()).toInt()

            val usage = withContext(Dispatchers.IO) {
                db.dailyUsageDao().getForDate(today)
            }

            if (usage.isEmpty()) {
                tvEmpty.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                tvTotalTime.text = "Total: 0h 0m"
            } else {
                tvEmpty.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.submitList(usage)
                val totalMs = usage.sumOf { it.totalTimeMs }
                tvTotalTime.text = "Total: ${formatDuration(totalMs)}"
            }
        }
    }

    private fun formatDuration(ms: Long): String {
        val hours = ms / 3600000
        val minutes = (ms % 3600000) / 60000
        return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    }
}

/**
 * Simple adapter for daily usage items.
 */
class DailyUsageAdapter : RecyclerView.Adapter<DailyUsageAdapter.ViewHolder>() {

    private var items: List<com.soc.agent.database.entity.DailyUsageEntity> = emptyList()

    fun submitList(newItems: List<com.soc.agent.database.entity.DailyUsageEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daily_usage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvPackageName.text = item.packageName
        holder.tvTime.text = formatDuration(item.totalTimeMs)
    }

    override fun getItemCount() = items.size

    private fun formatDuration(ms: Long): String {
        val hours = ms / 3600000
        val minutes = (ms % 3600000) / 60000
        return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPackageName: TextView = view.findViewById(R.id.tvPackageName)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
    }
}