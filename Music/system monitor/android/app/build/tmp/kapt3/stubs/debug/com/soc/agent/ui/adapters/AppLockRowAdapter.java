package com.soc.agent.ui.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.soc.agent.databinding.ItemAppLockBinding;

/**
 * Row adapter for the App Lock module. Used both to render already-locked apps
 * ("Remove" action) and the picker of installable apps ("Lock" action). The
 * action button text and enabled state are supplied per row; clicks are routed
 * through [onAction].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0014\u0010\u0013\u001a\u00020\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/soc/agent/ui/adapters/AppLockRowAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/soc/agent/ui/adapters/AppLockRowAdapter$VH;", "onAction", "Lkotlin/Function1;", "Lcom/soc/agent/ui/adapters/AppLockRow;", "", "(Lkotlin/jvm/functions/Function1;)V", "items", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "newItems", "", "VH", "app_debug"})
public final class AppLockRowAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.soc.agent.ui.adapters.AppLockRowAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.soc.agent.ui.adapters.AppLockRow, kotlin.Unit> onAction = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.soc.agent.ui.adapters.AppLockRow> items = null;
    
    public AppLockRowAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.soc.agent.ui.adapters.AppLockRow, kotlin.Unit> onAction) {
        super();
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.soc.agent.ui.adapters.AppLockRow> newItems) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.soc.agent.ui.adapters.AppLockRowAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.soc.agent.ui.adapters.AppLockRowAdapter.VH holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/soc/agent/ui/adapters/AppLockRowAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/soc/agent/databinding/ItemAppLockBinding;", "(Lcom/soc/agent/databinding/ItemAppLockBinding;)V", "getBinding", "()Lcom/soc/agent/databinding/ItemAppLockBinding;", "app_debug"})
    public static final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.soc.agent.databinding.ItemAppLockBinding binding = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.soc.agent.databinding.ItemAppLockBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.databinding.ItemAppLockBinding getBinding() {
            return null;
        }
    }
}