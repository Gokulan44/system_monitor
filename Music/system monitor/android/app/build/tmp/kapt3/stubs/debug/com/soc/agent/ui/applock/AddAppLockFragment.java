package com.soc.agent.ui.applock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.LockedAppEntity;
import com.soc.agent.databinding.FragmentAddAppLockBinding;
import com.soc.agent.security.AppLockPolicy;
import com.soc.agent.services.AppInventory;
import com.soc.agent.ui.adapters.AppLockRow;
import com.soc.agent.ui.adapters.AppLockRowAdapter;
import com.soc.agent.utils.Prefs;
import kotlinx.coroutines.Dispatchers;

/**
 * "Add App Lock" tab of the App Lock module. Shows every installed app that is
 * not already in the locked set and not in [AppLockPolicy.RESERVED]; tapping
 * "Lock" inserts a [LockedAppEntity] carrying the currently selected gate
 * method ([Prefs.lockMethod]). The list refreshes on resume so apps locked from
 * this screen disappear into the Locked Apps tab.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000bH\u0016J\b\u0010\u0018\u001a\u00020\u000bH\u0016J\u001a\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u001b"}, d2 = {"Lcom/soc/agent/ui/applock/AddAppLockFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/soc/agent/databinding/FragmentAddAppLockBinding;", "adapter", "Lcom/soc/agent/ui/adapters/AppLockRowAdapter;", "binding", "getBinding", "()Lcom/soc/agent/databinding/FragmentAddAppLockBinding;", "loadCandidates", "", "lockApp", "row", "Lcom/soc/agent/ui/adapters/AppLockRow;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "app_debug"})
public final class AddAppLockFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.soc.agent.databinding.FragmentAddAppLockBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.ui.adapters.AppLockRowAdapter adapter = null;
    
    public AddAppLockFragment() {
        super();
    }
    
    private final com.soc.agent.databinding.FragmentAddAppLockBinding getBinding() {
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
    
    private final void loadCandidates() {
    }
    
    private final void lockApp(com.soc.agent.ui.adapters.AppLockRow row) {
    }
}