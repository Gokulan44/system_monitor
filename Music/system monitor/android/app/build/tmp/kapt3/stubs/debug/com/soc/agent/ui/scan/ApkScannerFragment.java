package com.soc.agent.ui.scan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.soc.agent.R;
import com.soc.agent.api.dto.ScanRequest;
import com.soc.agent.data.SecurityRepository;
import com.soc.agent.security.ApkScanner;
import com.soc.agent.utils.Formatters;
import kotlinx.coroutines.Dispatchers;
import java.io.File;
import java.io.FileOutputStream;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000bH\u0002J&\u0010#\u001a\u0004\u0018\u00010\u00072\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000bH\u0002J\u001a\u0010+\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.H\u0002J\f\u0010/\u001a\u00020!*\u000200H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/soc/agent/ui/scan/ApkScannerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "btnAnalyze", "Landroid/widget/Button;", "btnPickApk", "cardResult", "Landroid/view/View;", "pbAnalyze", "Landroid/widget/ProgressBar;", "pickedUri", "Landroid/net/Uri;", "pickerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "repository", "Lcom/soc/agent/data/SecurityRepository;", "getRepository", "()Lcom/soc/agent/data/SecurityRepository;", "repository$delegate", "Lkotlin/Lazy;", "tvFileName", "Landroid/widget/TextView;", "tvResultDetail", "tvResultHash", "tvResultSeverity", "tvResultVerdict", "analyze", "", "bindViews", "view", "copyToCache", "Ljava/io/File;", "uri", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFilePicked", "onViewCreated", "showResult", "dto", "Lcom/soc/agent/api/dto/ScanItemDto;", "cacheQueueDir", "Landroid/content/Context;", "app_debug"})
public final class ApkScannerFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri pickedUri;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repository$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button btnPickApk;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvFileName;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button btnAnalyze;
    @org.jetbrains.annotations.Nullable()
    private android.widget.ProgressBar pbAnalyze;
    @org.jetbrains.annotations.Nullable()
    private android.view.View cardResult;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvResultVerdict;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvResultSeverity;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvResultHash;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView tvResultDetail;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> pickerLauncher = null;
    
    public ApkScannerFragment() {
        super();
    }
    
    private final com.soc.agent.data.SecurityRepository getRepository() {
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
    
    private final void onFilePicked(android.net.Uri uri) {
    }
    
    private final void analyze() {
    }
    
    private final void showResult(com.soc.agent.api.dto.ScanItemDto dto) {
    }
    
    private final java.io.File copyToCache(android.net.Uri uri) {
        return null;
    }
    
    private final java.io.File cacheQueueDir(android.content.Context $this$cacheQueueDir) {
        return null;
    }
}