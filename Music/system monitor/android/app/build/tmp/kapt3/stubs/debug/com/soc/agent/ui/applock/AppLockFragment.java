package com.soc.agent.ui.applock;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayoutMediator;
import com.soc.agent.R;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.databinding.FragmentAppLockBinding;
import com.soc.agent.security.PatternLock;
import com.soc.agent.services.AppLockService;
import com.soc.agent.ui.AppLockGateActivity;
import com.soc.agent.ui.applock.AddAppLockFragment;
import com.soc.agent.ui.applock.BackupFragment;
import com.soc.agent.ui.applock.LockedAppsFragment;
import com.soc.agent.ui.applock.SecuritySettingsFragment;
import com.soc.agent.ui.applock.UnlockHistoryFragment;
import com.soc.agent.utils.Prefs;
import kotlinx.coroutines.Dispatchers;

/**
 * App Lock module container: a Lock Dashboard header (master switch, watcher
 * status, gate summary, Usage-Approval warning and the "Set unlock" action)
 * above a TabLayout + ViewPager2 with three tabs: Locked Apps, Add App Lock, Unlock History.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0016J\u001a\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u000bH\u0002J\b\u0010\u001e\u001a\u00020\u000bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006 "}, d2 = {"Lcom/soc/agent/ui/applock/AppLockFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/soc/agent/databinding/FragmentAppLockBinding;", "binding", "getBinding", "()Lcom/soc/agent/databinding/FragmentAppLockBinding;", "gateSummary", "", "launchSetup", "", "gate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "refreshHeader", "setEnabled", "checked", "", "setupAutoLockSpinners", "showGateChoice", "AppLockPagerAdapter", "app_debug"})
public final class AppLockFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.soc.agent.databinding.FragmentAppLockBinding _binding;
    
    public AppLockFragment() {
        super();
    }
    
    private final com.soc.agent.databinding.FragmentAppLockBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
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
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void refreshHeader() {
    }
    
    private final java.lang.String gateSummary() {
        return null;
    }
    
    private final void setEnabled(boolean checked) {
    }
    
    private final void showGateChoice() {
    }
    
    private final void setupAutoLockSpinners() {
    }
    
    private final void launchSetup(java.lang.String gate) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/soc/agent/ui/applock/AppLockFragment$AppLockPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "createFragment", "position", "", "getItemCount", "app_debug"})
    static final class AppLockPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
        
        public AppLockPagerAdapter(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.Fragment fragment) {
            super(null);
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public androidx.fragment.app.Fragment createFragment(int position) {
            return null;
        }
    }
}