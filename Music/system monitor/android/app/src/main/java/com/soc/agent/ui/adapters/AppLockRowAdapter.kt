package com.soc.agent.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.databinding.ItemAppLockBinding

/** A single row in an App Lock list (Locked or Add tab). */
data class AppLockRow(
    val packageName: String,
    val name: String,
    val subtitle: String = "",
    val actionLabel: String = "Lock",
    /** Resolved lazily by the adapter; kept null until bound to avoid heavy IO. */
    val icon: Drawable? = null
)

/**
 * Row adapter for the App Lock module. Used both to render already-locked apps
 * ("Remove" action) and the picker of installable apps ("Lock" action). The
 * action button text and enabled state are supplied per row; clicks are routed
 * through [onAction].
 */
class AppLockRowAdapter(
    private val onAction: (AppLockRow) -> Unit
) : RecyclerView.Adapter<AppLockRowAdapter.VH>() {

    private val items = mutableListOf<AppLockRow>()

    fun submitList(newItems: List<AppLockRow>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemAppLockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val row = items[position]
        val binding = holder.binding
        binding.tvAppName.text = row.name.ifBlank { row.packageName }
        binding.tvAppPackage.text = if (row.subtitle.isBlank()) row.packageName else row.subtitle
        binding.btnAction.text = row.actionLabel
        binding.ivAppIcon.setImageDrawable(row.icon)
        binding.btnAction.isEnabled = row.actionLabel.isNotBlank()
        binding.btnAction.setOnClickListener { onAction(row) }
        binding.root.setOnLongClickListener {
            onAction(row)
            true
        }
    }

    override fun getItemCount(): Int = items.size

    class VH(val binding: ItemAppLockBinding) : RecyclerView.ViewHolder(binding.root)
}