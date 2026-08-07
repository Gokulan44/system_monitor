package com.soc.agent.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.R
import com.soc.agent.database.entity.UsageTimelineEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecentActivityAdapter : ListAdapter<UsageTimelineEntity, RecentActivityAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<UsageTimelineEntity>() {
            override fun areItemsTheSame(oldItem: UsageTimelineEntity, newItem: UsageTimelineEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UsageTimelineEntity, newItem: UsageTimelineEntity): Boolean =
                oldItem == newItem
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(android.R.id.text1)
        val tvSubtitle: TextView = view.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.tvTitle.text = item.appName.ifBlank { item.packageName }
        val timeStr = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(item.eventTimeMs))
        holder.tvSubtitle.text = "${item.eventType.replaceFirstChar { it.uppercase() }} at $timeStr"
    }
}
