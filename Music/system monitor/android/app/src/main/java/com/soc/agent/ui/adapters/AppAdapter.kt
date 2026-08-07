package com.soc.agent.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.R
import com.soc.agent.api.dto.InstalledAppDto
import com.soc.agent.databinding.ItemAppBinding
import com.soc.agent.utils.Formatters

/**
 * RecyclerView adapter for installed-application rows: icon, name, package,
 * colored risk badge and memory footprint. Uses DiffUtil so inventory refreshes
 * animate cheaply. The icon is resolved from the package manager on demand so
 * it always matches the installed app, with a graceful fallback when the icon
 * cannot be loaded (e.g. package no longer visible to us).
 */
class AppAdapter : RecyclerView.Adapter<AppAdapter.VH>() {

    private val items = mutableListOf<InstalledAppDto>()

    fun submitList(newItems: List<InstalledAppDto>) {
        val diff = DiffUtil.calculateDiff(ItemCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val app = items[position]
        val context = holder.binding.root.context
        holder.binding.tvAppName.text = app.name
        holder.binding.tvAppPackage.text = app.packageName
        holder.binding.tvAppMem.text =
            if (app.memB > 0) Formatters.bytes(app.memB) else ""

        holder.binding.ivAppIcon.setImageDrawable(loadIcon(app, context))

        val risk = app.risk.ifBlank { "unknown" }
        holder.binding.tvAppRisk.text = risk.uppercase()
        holder.binding.tvAppRisk.setBackgroundColor(riskColor(risk, context))
    }

    override fun getItemCount(): Int = items.size

    private fun loadIcon(app: InstalledAppDto, context: android.content.Context): Drawable? {
        return try {
            context.packageManager.getApplicationIcon(app.packageName)
        } catch (e: Exception) {
            null
        }
    }

    private fun riskColor(risk: String, context: android.content.Context): Int {
        return when (risk.lowercase()) {
            "critical", "high" -> ContextCompat.getColor(context, R.color.soc_red)
            "medium" -> ContextCompat.getColor(context, R.color.soc_amber)
            "low" -> ContextCompat.getColor(context, R.color.soc_green)
            else -> ContextCompat.getColor(context, R.color.soc_panel2)
        }
    }

    class VH(val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root)

    private class ItemCallback(
        private val oldList: List<InstalledAppDto>,
        private val newList: List<InstalledAppDto>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition].packageName == newList[newPosition].packageName
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val old = oldList[oldPosition]
            val new = newList[newPosition]
            return old.name == new.name &&
                old.risk == new.risk &&
                old.memB == new.memB
        }
    }
}
