package com.soc.agent.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.soc.agent.data.SecurityRepository;
import kotlinx.coroutines.flow.StateFlow;

/**
 * Lifecycle of a scan run as surfaced to the UI.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/soc/agent/ui/viewmodel/ScanStatus;", "", "(Ljava/lang/String;I)V", "IDLE", "RUNNING", "COMPLETED", "ERROR", "app_debug"})
public enum ScanStatus {
    /*public static final*/ IDLE /* = new IDLE() */,
    /*public static final*/ RUNNING /* = new RUNNING() */,
    /*public static final*/ COMPLETED /* = new COMPLETED() */,
    /*public static final*/ ERROR /* = new ERROR() */;
    
    ScanStatus() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.soc.agent.ui.viewmodel.ScanStatus> getEntries() {
        return null;
    }
}