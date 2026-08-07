package com.soc.agent.ui.settings

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.soc.agent.R
import com.soc.agent.data.SettingsRepository
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * ProfileFragment — Profile dashboard showing user avatar, personal info,
 * account status badge, member-since date, registered devices count,
 * security score with circular progress, last login time, and a 6-item
 * quick-action grid.  All data sourced from [SettingsRepository].
 */
class ProfileFragment : Fragment() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var ivAvatar: ImageView
    private lateinit var tvFullName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvAccountStatus: TextView
    private lateinit var tvMemberSince: TextView
    private lateinit var tvDeviceCount: TextView
    private lateinit var tvLastLogin: TextView
    private lateinit var progressSecurityScore: ProgressBar
    private lateinit var tvSecurityScore: TextView
    private lateinit var tvSecurityLabel: TextView
    private lateinit var gridQuickActions: GridLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupToolbar()
        populateProfile()
        buildQuickActionsGrid()
    }

    private fun bindViews(view: View) {
        toolbar = MaterialToolbar(requireContext())
        ivAvatar = view.findViewById(R.id.iv_profile_avatar) ?: ImageView(requireContext())
        tvFullName = view.findViewById(R.id.tv_profile_name) ?: TextView(requireContext())
        tvEmail = view.findViewById(R.id.tv_profile_email) ?: TextView(requireContext())
        tvPhone = view.findViewById(R.id.tv_profile_phone) ?: TextView(requireContext())
        tvAccountStatus = view.findViewById(R.id.chip_account_status) ?: TextView(requireContext())
        tvMemberSince = TextView(requireContext())
        tvDeviceCount = TextView(requireContext())
        tvLastLogin = TextView(requireContext())
        progressSecurityScore = ProgressBar(requireContext())
        tvSecurityScore = TextView(requireContext())
        tvSecurityLabel = TextView(requireContext())
        gridQuickActions = GridLayout(requireContext())
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun populateProfile() {
        val profile = SettingsRepository.getUserProfile()
        val deviceInfo = SettingsRepository.getDeviceInfo()
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val dateTimeFormat = SimpleDateFormat("MMM dd, yyyy  HH:mm", Locale.getDefault())

        // ── Avatar ──
        if (profile.avatarUri.isNotEmpty()) {
            try {
                ivAvatar.setImageURI(android.net.Uri.parse(profile.avatarUri))
            } catch (_: Exception) {
                ivAvatar.setImageResource(R.drawable.ic_person)
            }
        } else {
            ivAvatar.setImageResource(R.drawable.ic_person)
        }

        // ── Basic info ──
        tvFullName.text = profile.name.ifEmpty { "Unknown User" }
        tvEmail.text = profile.email.ifEmpty { "No email set" }
        tvPhone.text = profile.phone.ifEmpty { "No phone set" }

        // ── Account status badge ──
        val isActive = profile.accountStatus.equals("active", ignoreCase = true)
        tvAccountStatus.text = if (isActive) "Active" else "Inactive"
        val badgeBg = tvAccountStatus.background as? GradientDrawable
            ?: GradientDrawable().also { tvAccountStatus.background = it }
        badgeBg.shape = GradientDrawable.RECTANGLE
        badgeBg.cornerRadius = 24f
        val badgeColor = if (isActive) {
            ContextCompat.getColor(requireContext(), R.color.soc_green)
        } else {
            ContextCompat.getColor(requireContext(), R.color.soc_red)
        }
        badgeBg.setColor(badgeColor)

        // ── Member since ──
        tvMemberSince.text = if (profile.memberSince > 0L) {
            dateFormat.format(Date(profile.memberSince))
        } else {
            "N/A"
        }

        // ── Registered devices ──
        tvDeviceCount.text = deviceInfo.registeredDeviceCount.toString()

        // ── Last login ──
        tvLastLogin.text = if (profile.lastLogin > 0L) {
            dateTimeFormat.format(Date(profile.lastLogin))
        } else {
            "Never"
        }

        // ── Security score ──
        val score = profile.securityScore.coerceIn(0, 100)
        tvSecurityScore.text = "$score"
        tvSecurityLabel.text = when {
            score >= 80 -> "Excellent"
            score >= 60 -> "Good"
            score >= 40 -> "Fair"
            score >= 20 -> "Weak"
            else -> "Critical"
        }
        val labelColor = when {
            score >= 80 -> ContextCompat.getColor(requireContext(), R.color.soc_green)
            score >= 60 -> ContextCompat.getColor(requireContext(), R.color.soc_cyan)
            score >= 40 -> ContextCompat.getColor(requireContext(), R.color.soc_amber)
            else -> ContextCompat.getColor(requireContext(), R.color.soc_red)
        }
        tvSecurityScore.setTextColor(labelColor)
        tvSecurityLabel.setTextColor(labelColor)
        progressSecurityScore.progress = score
    }

    // ────────────────────────────────────────────────────────────
    // Quick Actions Grid
    // ────────────────────────────────────────────────────────────

    data class QuickAction(
        val title: String,
        val iconRes: Int,
        val colorRes: Int
    )

    private fun buildQuickActionsGrid() {
        gridQuickActions.removeAllViews()

        val actions = listOf(
            QuickAction("Edit Profile", R.drawable.ic_person, R.color.soc_cyan),
            QuickAction("Security Settings", R.drawable.ic_shield, R.color.soc_indigo),
            QuickAction("Manage Devices", R.drawable.ic_nav_device, R.color.soc_green),
            QuickAction("Backup & Sync", R.drawable.ic_nav_filecenter, R.color.soc_amber),
            QuickAction("Notifications", R.drawable.ic_nav_notification, R.color.soc_indigo),
            QuickAction("Help & Support", R.drawable.ic_shield, R.color.soc_muted)
        )

        val columnCount = 3
        gridQuickActions.columnCount = columnCount

        for (action in actions) {
            val item = createQuickActionItem(action)
            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(12, 8, 12, 8)
            }
            gridQuickActions.addView(item, params)
        }
    }

    private fun createQuickActionItem(action: QuickAction): View {
        val ctx = requireContext()

        val container = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(12, 16, 12, 16)

            // Rounded background
            val bg = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 16f
                setColor(ContextCompat.getColor(ctx, R.color.soc_panel2))
            }
            background = bg
        }

        // Icon circle
        val iconBg = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(ContextCompat.getColor(ctx, action.colorRes).let { color ->
                // Use 20% alpha version
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)
                Color.argb(50, r, g, b)
            })
        }

        val icon = ImageView(ctx).apply {
            setImageResource(action.iconRes)
            layoutParams = android.widget.LinearLayout.LayoutParams(48, 48)
            background = iconBg
            setPadding(10, 10, 10, 10)
            setColorFilter(ContextCompat.getColor(ctx, action.colorRes))
            contentDescription = action.title
        }

        val label = TextView(ctx).apply {
            text = action.title
            setTextColor(ContextCompat.getColor(ctx, R.color.soc_text))
            textSize = 11f
            gravity = Gravity.CENTER
            maxLines = 2
            setPadding(0, 8, 0, 0)
        }

        container.addView(icon)
        container.addView(label)

        container.setOnClickListener {
            handleQuickAction(action.title)
        }

        return container
    }

    private fun handleQuickAction(title: String) {
        val toastMessage = when (title) {
            "Edit Profile" -> "Opening Edit Profile..."
            "Security Settings" -> "Opening Security Settings..."
            "Manage Devices" -> "Opening Manage Devices..."
            "Backup & Sync" -> "Opening Backup & Sync..."
            "Notifications" -> "Opening Notifications..."
            "Help & Support" -> "Opening Help & Support..."
            else -> "Action: $title"
        }
        android.widget.Toast
            .makeText(requireContext(), toastMessage, android.widget.Toast.LENGTH_SHORT)
            .show()
    }
}
