package com.soc.agent.ui.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.soc.agent.R;
import com.soc.agent.api.dto.InstalledAppDto;
import com.soc.agent.databinding.ItemAppBinding;
import com.soc.agent.utils.Formatters;

/**
 * RecyclerView adapter for installed-application rows: icon, name, package,
 * colored risk badge and memory footprint. Uses DiffUtil so inventory refreshes
 * animate cheaply. The icon is resolved from the package manager on demand so
 * it always matches the installed app, with a graceful fallback when the icon
 * cannot be loaded (e.g. package no longer visible to us).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001c\u001dB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rH\u0002J\u0014\u0010\u0019\u001a\u00020\u000f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/soc/agent/ui/adapters/AppAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/soc/agent/ui/adapters/AppAdapter$VH;", "()V", "items", "", "Lcom/soc/agent/api/dto/InstalledAppDto;", "getItemCount", "", "loadIcon", "Landroid/graphics/drawable/Drawable;", "app", "context", "Landroid/content/Context;", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "riskColor", "risk", "", "submitList", "newItems", "", "ItemCallback", "VH", "app_debug"})
public final class AppAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.soc.agent.ui.adapters.AppAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.soc.agent.api.dto.InstalledAppDto> items = null;
    
    public AppAdapter() {
        super();
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.api.dto.InstalledAppDto> newItems) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.soc.agent.ui.adapters.AppAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.soc.agent.ui.adapters.AppAdapter.VH holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    private final android.graphics.drawable.Drawable loadIcon(com.soc.agent.api.dto.InstalledAppDto app, android.content.Context context) {
        return null;
    }
    
    private final int riskColor(java.lang.String risk, android.content.Context context) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/soc/agent/ui/adapters/AppAdapter$ItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldList", "", "Lcom/soc/agent/api/dto/InstalledAppDto;", "newList", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldPosition", "", "newPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "app_debug"})
    static final class ItemCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.soc.agent.api.dto.InstalledAppDto> oldList = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.soc.agent.api.dto.InstalledAppDto> newList = null;
        
        public ItemCallback(@org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.api.dto.InstalledAppDto> oldList, @org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.api.dto.InstalledAppDto> newList) {
            super();
        }
        
        @java.lang.Override()
        public int getOldListSize() {
            return 0;
        }
        
        @java.lang.Override()
        public int getNewListSize() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(int oldPosition, int newPosition) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(int oldPosition, int newPosition) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/soc/agent/ui/adapters/AppAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/soc/agent/databinding/ItemAppBinding;", "(Lcom/soc/agent/databinding/ItemAppBinding;)V", "getBinding", "()Lcom/soc/agent/databinding/ItemAppBinding;", "app_debug"})
    public static final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.soc.agent.databinding.ItemAppBinding binding = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.soc.agent.databinding.ItemAppBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.databinding.ItemAppBinding getBinding() {
            return null;
        }
    }
}