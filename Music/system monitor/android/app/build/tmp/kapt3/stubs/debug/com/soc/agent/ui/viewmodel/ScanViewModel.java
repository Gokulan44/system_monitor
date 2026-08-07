package com.soc.agent.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.soc.agent.data.SecurityRepository;
import kotlinx.coroutines.flow.StateFlow;

/**
 * Drives scan runs through [SecurityRepository.runScan] and publishes the
 * result as a [StateFlow] of [ScanProgress]. The repository performs the actual
 * scanning (via ScanService) and syncs the run + detections to the SOC server;
 * this view model only brokers status, counters and the final score/grade.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/soc/agent/ui/viewmodel/ScanViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_progress", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/soc/agent/ui/viewmodel/ScanProgress;", "progress", "Lkotlinx/coroutines/flow/StateFlow;", "getProgress", "()Lkotlinx/coroutines/flow/StateFlow;", "repository", "Lcom/soc/agent/data/SecurityRepository;", "runScan", "", "scanType", "", "app_debug"})
public final class ScanViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.data.SecurityRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.soc.agent.ui.viewmodel.ScanProgress> _progress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.soc.agent.ui.viewmodel.ScanProgress> progress = null;
    
    public ScanViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.soc.agent.ui.viewmodel.ScanProgress> getProgress() {
        return null;
    }
    
    /**
     * Runs a scan of the given type ("quick" or "full") and updates [progress].
     */
    public final void runScan(@org.jetbrains.annotations.NotNull()
    java.lang.String scanType) {
    }
}