package com.soc.agent.ui.dashboard;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.soc.agent.R;
import com.soc.agent.security.PolicyViolation;
import com.soc.agent.ui.viewmodel.DashboardState;
import com.soc.agent.ui.viewmodel.DashboardViewModel;
import com.soc.agent.utils.Formatters;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0002J&\u0010\u001d\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001a\u0010$\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020'H\u0002J\u0016\u0010(\u001a\u00020\u001b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006,"}, d2 = {"Lcom/soc/agent/ui/dashboard/DashboardFragment;", "Landroidx/fragment/app/Fragment;", "()V", "btnRefresh", "Landroid/view/View;", "llViolations", "Landroid/widget/LinearLayout;", "tvApps", "Landroid/widget/TextView;", "tvBattery", "tvBlockDomains", "tvBlockHashes", "tvBlockUrls", "tvCpu", "tvGrade", "tvLastSync", "tvMem", "tvScore", "tvStorage", "tvThreats", "viewModel", "Lcom/soc/agent/ui/viewmodel/DashboardViewModel;", "getViewModel", "()Lcom/soc/agent/ui/viewmodel/DashboardViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bindViews", "", "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "render", "state", "Lcom/soc/agent/ui/viewmodel/DashboardState;", "renderViolations", "violations", "", "Lcom/soc/agent/security/PolicyViolation;", "app_debug"})
public final class DashboardFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvGrade;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvScore;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvCpu;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvMem;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvBattery;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvStorage;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvThreats;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvApps;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvLastSync;
    @org.jetbrains.annotations.Nullable()
    private android.view.View btnRefresh;
    @org.jetbrains.annotations.Nullable()
    private android.widget.LinearLayout llViolations;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvBlockDomains;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvBlockUrls;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvBlockHashes;
    
    public DashboardFragment() {
        super();
    }
    
    private final com.soc.agent.ui.viewmodel.DashboardViewModel getViewModel() {
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
    
    private final void render(com.soc.agent.ui.viewmodel.DashboardState state) {
    }
    
    private final void renderViolations(java.util.List<com.soc.agent.security.PolicyViolation> violations) {
    }
}