package com.soc.agent.ui.applock;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.soc.agent.databinding.ActivityChooseUnlockMethodBinding;
import com.soc.agent.security.PatternLock;
import com.soc.agent.ui.AppLockGateActivity;
import com.soc.agent.utils.Prefs;

/**
 * Screen where the user chooses their preferred unlock method for App Lock.
 * Shows available methods, indicates the currently active one, and navigates
 * to setup if the chosen method is not yet configured.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\u0006H\u0014J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/soc/agent/ui/applock/ChooseUnlockMethodActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/soc/agent/databinding/ActivityChooseUnlockMethodBinding;", "clearHighlights", "", "highlightCurrentMethod", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "selectMethod", "method", "", "setupMethodCards", "Companion", "app_debug"})
public final class ChooseUnlockMethodActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.soc.agent.databinding.ActivityChooseUnlockMethodBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String METHOD_PIN = "pin";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String METHOD_PATTERN = "pattern";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String METHOD_PASSWORD = "password";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String METHOD_BIOMETRIC = "biometric";
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.ui.applock.ChooseUnlockMethodActivity.Companion Companion = null;
    
    public ChooseUnlockMethodActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void setupMethodCards() {
    }
    
    private final void highlightCurrentMethod() {
    }
    
    private final void clearHighlights() {
    }
    
    private final void selectMethod(java.lang.String method) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/soc/agent/ui/applock/ChooseUnlockMethodActivity$Companion;", "", "()V", "METHOD_BIOMETRIC", "", "METHOD_PASSWORD", "METHOD_PATTERN", "METHOD_PIN", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}