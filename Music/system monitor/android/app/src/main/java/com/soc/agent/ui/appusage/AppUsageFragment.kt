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

/**
 * Shows per-app usage breakdown: total time, launch count, last used.
 */
class AppUsageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var adapter: AppUsageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_usage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvAppUsage)
        tvEmpty = view.findViewById(R.id.tvEmpty)

        adapter = AppUsageAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadAppUsage()
    }

    private fun loadAppUsage() {
        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())

            val usage = withContext(Dispatchers.IO) {
                db.appUsageDao().getAll()
            }

            if (usage.isEmpty()) {
                tvEmpty.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                tvEmpty.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.submitList(usage)
            }
        }
    }
}

/**
 * Adapter for app usage items.
 */
class AppUsageAdapter : RecyclerView.Adapter<AppUsageAdapter.ViewHolder>() {

    private var items: List<com.soc.agent.database.entity.AppUsageEntity> = emptyList()

    fun submitList(newItems: List<com.soc.agent.database.entity.AppUsageEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app_usage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvPackageName.text = item.packageName
        holder.tvTotalTime.text = formatDuration(item.totalTimeMs)
        holder.tvLaunchCount.text = "${item.launchCount} launches"
    }

    override fun getItemCount() = items.size

    private fun formatDuration(ms: Long): String {
        val hours = ms / 3600000
        val minutes = (ms % 3600000) / 60000
        return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPackageName: TextView = view.findViewById(R.id.tvPackageName)
        val tvTotalTime: TextView = view.findViewById(R.id.tvTotalTime)
        val tvLaunchCount: TextView = view.findViewById(R.id.tvLaunchCount)
    }
}