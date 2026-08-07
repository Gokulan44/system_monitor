package com.soc.agent.ui.applock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.soc.agent.R;
import com.soc.agent.databinding.FragmentUnlockHistoryBinding;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.UnlockHistoryEntity;
import kotlinx.coroutines.Dispatchers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\u001a\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/soc/agent/ui/applock/UnlockHistoryFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/soc/agent/databinding/FragmentUnlockHistoryBinding;", "adapter", "Lcom/soc/agent/ui/applock/UnlockHistoryFragment$UnlockHistoryAdapter;", "binding", "getBinding", "()Lcom/soc/agent/databinding/FragmentUnlockHistoryBinding;", "loadHistory", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "UnlockHistoryAdapter", "app_debug"})
public final class UnlockHistoryFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.soc.agent.databinding.FragmentUnlockHistoryBinding _binding;
    private com.soc.agent.ui.applock.UnlockHistoryFragment.UnlockHistoryAdapter adapter;
    
    public UnlockHistoryFragment() {
        super();
    }
    
    private final com.soc.agent.databinding.FragmentUnlockHistoryBinding getBinding() {
        return null;
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
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void loadHistory() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000 \u000e2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/soc/agent/ui/applock/UnlockHistoryFragment$UnlockHistoryAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/soc/agent/database/entity/UnlockHistoryEntity;", "Lcom/soc/agent/ui/applock/UnlockHistoryFragment$UnlockHistoryAdapter$VH;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "VH", "app_debug"})
    static final class UnlockHistoryAdapter extends androidx.recyclerview.widget.ListAdapter<com.soc.agent.database.entity.UnlockHistoryEntity, com.soc.agent.ui.applock.UnlockHistoryFragment.UnlockHistoryAdapter.VH> {
        @org.jetbrains.annotations.NotNull()
        private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.soc.agent.database.entity.UnlockHistoryEntity> DIFF = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.soc.agent.ui.applock.UnlockHistoryFragment.UnlockHistoryAdapter.Companion Companion = null;
        
        public UnlockHistoryAdapter() {
            super(null);
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.soc.agent.ui.applock.UnlockHistoryFragment.UnlockHistoryAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.soc.agent.ui.applock.UnlockHistoryFragment.UnlockHistoryAdapter.VH holder, int position) {
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/soc/agent/ui/applock/UnlockHistoryFragment$UnlockHistoryAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/soc/agent/database/entity/UnlockHistoryEntity;", "app_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b\u00a8\u0006\u000f"}, d2 = {"Lcom/soc/agent/ui/applock/UnlockHistoryFragment$UnlockHistoryAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/soc/agent/ui/applock/UnlockHistoryFragment$UnlockHistoryAdapter;Landroid/view/View;)V", "tvAppName", "Landroid/widget/TextView;", "getTvAppName", "()Landroid/widget/TextView;", "tvAuto", "getTvAuto", "tvGateMethod", "getTvGateMethod", "tvTimestamp", "getTvTimestamp", "app_debug"})
        public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvAppName = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvGateMethod = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvTimestamp = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvAuto = null;
            
            public VH(@org.jetbrains.annotations.NotNull()
            android.view.View view) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTvAppName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTvGateMethod() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTvTimestamp() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTvAuto() {
                return null;
            }
        }
    }
}