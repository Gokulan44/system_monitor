package com.soc.agent.ui.scan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.soc.agent.R;
import com.soc.agent.ui.viewmodel.ScanProgress;
import com.soc.agent.ui.viewmodel.ScanStatus;
import com.soc.agent.ui.viewmodel.ScanViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0002J&\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0001H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/soc/agent/ui/scan/ScanCenterFragment;", "Landroidx/fragment/app/Fragment;", "()V", "btnApkScan", "Landroid/widget/Button;", "btnFullScan", "btnQuickScan", "containerScan", "Landroid/view/View;", "pbScan", "Landroid/widget/ProgressBar;", "scanViewModel", "Lcom/soc/agent/ui/viewmodel/ScanViewModel;", "getScanViewModel", "()Lcom/soc/agent/ui/viewmodel/ScanViewModel;", "scanViewModel$delegate", "Lkotlin/Lazy;", "scrollScan", "tvScanStatus", "Landroid/widget/TextView;", "bindViews", "", "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onScanProgress", "progress", "Lcom/soc/agent/ui/viewmodel/ScanProgress;", "onViewCreated", "openSubScreen", "fragment", "app_debug"})
public final class ScanCenterFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy scanViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.view.View scrollScan;
    @org.jetbrains.annotations.Nullable()
    private android.view.View containerScan;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button btnQuickScan;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button btnFullScan;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button btnApkScan;
    @org.jetbrains.annotations.Nullable()
    private android.widget.ProgressBar pbScan;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvScanStatus;
    
    public ScanCenterFragment() {
        super();
    }
    
    private final com.soc.agent.ui.viewmodel.ScanViewModel getScanViewModel() {
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
    
    private final void bindViews(android.view.View view) {
    }
    
    private final void openSubScreen(androidx.fragment.app.Fragment fragment) {
    }
    
    private final void onScanProgress(com.soc.agent.ui.viewmodel.ScanProgress progress) {
    }
}