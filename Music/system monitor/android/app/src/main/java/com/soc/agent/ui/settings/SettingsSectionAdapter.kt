package com.soc.agent.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.soc.agent.R

/**
 * Data model for a single settings section row.
 *
 * @param iconRes       Drawable resource id shown on the left.
 * @param title         Primary label (bold).
 * @param subtitle      Secondary label underneath the title.
 * @param isDestructive When true the icon tint defaults to [R.color.soc_red].
 * @param onClick       Callback invoked when the row is tapped.
 */
data class SettingsSection(
    @DrawableRes val iconRes: Int,
    val title: String,
    val subtitle: String,
    val isDestructive: Boolean = false,
    val onClick: () -> Unit
)

/**
 * Lightweight adapter for the settings RecyclerView.
 * Each row shows an icon, title, subtitle and a trailing chevron arrow.
 */
class SettingsSectionAdapter(
    private val items: List<SettingsSection>
) : RecyclerView.Adapter<SettingsSectionAdapter.SectionVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_settings_section, parent, false)
        return SectionVH(view)
    }

    override fun onBindViewHolder(holder: SectionVH, position: Int) {
        val section = items[position]
        val ctx = holder.itemView.context

        holder.icon.setImageResource(section.iconRes)
        holder.title.text = section.title
        holder.subtitle.text = section.subtitle

        // Destructive items (Log Out) get a red icon tint instead of cyan.
        val tint = if (section.isDestructive) {
            ContextCompat.getColor(ctx, R.color.soc_red)
        } else {
            ContextCompat.getColor(ctx, R.color.soc_cyan)
        }
        holder.icon.setColorFilter(tint)

        holder.itemView.setOnClickListener { section.onClick() }
    }

    override fun getItemCount(): Int = items.size

    /** ViewHolder for a single settings section card. */
    class SectionVH(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.iv_settings_icon)
        val title: TextView = view.findViewById(R.id.tv_title)
        val subtitle: TextView = view.findViewById(R.id.tv_subtitle)
        // ivChevron is present but static — no reference needed.
    }
}
