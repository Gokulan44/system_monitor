package com.soc.agent.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.R
import com.soc.agent.databinding.ItemLogBinding

/**
 * Generic row model for scan-log style lists: a label (e.g. item name), a
 * detail line and a severity used for coloring.
 */
data class LogRow(
    val label: String,
    val detail: String,
    val severity: String
)

/**
 * Generic adapter for scan-log rows, e.g. the malicious items list shown after
 * a quick scan.
 */
class LogAdapter : RecyclerView.Adapter<LogAdapter.VH>() {

    private val items = mutableListOf<LogRow>()

    fun submitList(newItems: List<LogRow>) {
        val diff = DiffUtil.calculateDiff(ItemCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val row = items[position]
        val context = holder.binding.root.context
        holder.binding.tvLogLabel.text = row.label
        holder.binding.tvLogDetail.text = row.detail
        holder.binding.tvLogSeverity.text = row.severity.uppercase()
        holder.binding.tvLogSeverity.setTextColor(severityColor(row.severity, context))
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

    class VH(val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root)

    private class ItemCallback(
        private val oldList: List<LogRow>,
        private val newList: List<LogRow>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition].label == newList[newPosition].label &&
                oldList[oldPosition].detail == newList[newPosition].detail
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition] == newList[newPosition]
        }
    }
}
