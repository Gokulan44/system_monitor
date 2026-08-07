package com.soc.agent.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.R
import com.soc.agent.databinding.ItemThreatBinding
import com.soc.agent.utils.Formatters

/**
 * Generic row model for threat/alert lists: a title, a severity, a detail line
 * and a timestamp.
 */
data class ThreatRow(
    val title: String,
    val severity: String,
    val detail: String,
    val timestamp: Long
)

/**
 * Adapter for threat and alert rows (title, colored severity dot, detail,
 * relative time). Used by the Threats and Alerts tabs of the Security section.
 */
class ThreatAdapter : RecyclerView.Adapter<ThreatAdapter.VH>() {

    private val items = mutableListOf<ThreatRow>()

    fun submitList(newItems: List<ThreatRow>) {
        val diff = DiffUtil.calculateDiff(ItemCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemThreatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val row = items[position]
        val context = holder.binding.root.context
        holder.binding.tvThreatTitle.text = row.title
        holder.binding.tvThreatDetail.text = row.detail
        holder.binding.tvThreatTime.text = Formatters.timeAgo(row.timestamp)
        holder.binding.tvSeverityDot.setTextColor(severityColor(row.severity, context))
    }

    override fun getItemCount(): Int = items.size

    private fun severityColor(severity: String, context: Context): Int {
        return when (severity.lowercase()) {
            "critical", "high" -> ContextCompat.getColor(context, R.color.soc_red)
            "medium", "warning" -> ContextCompat.getColor(context, R.color.soc_amber)
            "low", "info" -> ContextCompat.getColor(context, R.color.soc_cyan)
            else -> ContextCompat.getColor(context, R.color.soc_muted)
        }
    }

    class VH(val binding: ItemThreatBinding) : RecyclerView.ViewHolder(binding.root)

    private class ItemCallback(
        private val oldList: List<ThreatRow>,
        private val newList: List<ThreatRow>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition].title == newList[newPosition].title &&
                oldList[oldPosition].timestamp == newList[newPosition].timestamp
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition] == newList[newPosition]
        }
    }
}
