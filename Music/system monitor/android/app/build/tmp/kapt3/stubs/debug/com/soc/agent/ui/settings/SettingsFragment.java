package com.soc.agent.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.soc.agent.R;
import com.soc.agent.ui.applock.SecuritySettingsFragment;

/**
 * Settings screen — displays eleven section cards in a vertical
 * RecyclerView.  Each card shows an icon, title, subtitle and a
 * trailing chevron.  Tapping a card navigates to the corresponding
 * sub-fragment (via the parent activity's FragmentManager) or opens
 * a dialog for "About" and "Log Out".
 *
 * The fragment is hosted inside [com.soc.agent.ui.MainActivity] as
 * the 11th tab of the BottomNavigationView.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0002J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0017\u001a\u00020\tH\u0002J\b\u0010\u0018\u001a\u00020\tH\u0002J\b\u0010\u0019\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/soc/agent/ui/settings/SettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "rvSettings", "Landroidx/recyclerview/widget/RecyclerView;", "buildSections", "", "Lcom/soc/agent/ui/settings/SettingsSection;", "navigateTo", "", "fragment", "tag", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "performLogout", "showAboutDialog", "showLogoutConfirmDialog", "app_debug"})
public final class SettingsFragment extends androidx.fragment.app.Fragment {
    private androidx.recyclerview.widget.RecyclerView rvSettings;
    
    public SettingsFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Builds the ordered list of all 11 settings sections.
     * Each entry wires its `onClick` lambda to either navigate
     * to a fragment or show a dialog.
     */
    private final java.util.List<com.soc.agent.ui.settings.SettingsSection> buildSections() {
        return null;
    }
    
    /**
     * Pushes [fragment] onto the back stack under the given [tag].
     * The parent activity's `fragment_container` is used as the
     * target view-id, which matches the existing navigation setup.
     */
    private final void navigateTo(androidx.fragment.app.Fragment fragment, java.lang.String tag) {
    }
    
    /**
     * Shows an informational dialog with the app name, version
     * string and a short description.
     */
    private final void showAboutDialog() {
    }
    
    /**
     * Shows a confirmation dialog before signing the user out.
     * On positive confirmation the user is redirected to the
     * [com.soc.agent.ui.LoginActivity] and the back stack is cleared.
     */
    private final void showLogoutConfirmDialog() {
    }
    
    /**
     * Clears the activity back stack and restarts from the login
     * screen.  This is a best-effort approach that works for the
     * single-activity shell without needing DI or a global nav graph.
     */
    private final void performLogout() {
    }
}