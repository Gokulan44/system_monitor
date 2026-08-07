package com.soc.agent.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import com.soc.agent.R;
import com.soc.agent.databinding.ActivityAppLockGateBinding;
import com.soc.agent.security.PatternLock;
import com.soc.agent.security.Sha256;
import com.soc.agent.services.AppLockService;
import com.soc.agent.utils.Prefs;
import com.soc.agent.security.SecuritySettingsManager;
import com.soc.agent.security.IntruderSelfieManager;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.FailedAttemptEntity;
import kotlinx.coroutines.Dispatchers;
import java.util.concurrent.Executors;

/**
 * Unified unlock gate for the App Lock module, presenting either a PIN pad or
 * the 3x3 pattern view depending on [EXTRA_GATE].
 *
 * Modes (via [EXTRA_MODE]):
 * - [MODE_SETUP]: create a gate. PIN gates collect the value twice (create /
 *   confirm); pattern gates require drawing the same pattern twice. On success
 *   the hash is stored (Prefs.pinHash / PatternLock) and [Prefs.lockMethod]
 *   is set. Used from the Add App Lock setup flow.
 * - [MODE_VERIFY]: the user must satisfy the configured gate. On success we
 *   call [AppLockService.recordUnlock] so the watcher's grace window lets the
 *   package stay open, then finish. Used by [AppLockService] when a locked app
 *   comes to the foreground.
 *
 * [EXTRA_PACKAGE] is optional; when absent the verify path unlocks without
 * targeting a specific process (still enough to satisfy the watcher).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 22\u00020\u0001:\u00012B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0002J\u0016\u0010\u001c\u001a\u00020\u00182\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020\u0018H\u0002J\u0012\u0010 \u001a\u00020\u00182\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u0018H\u0002J\b\u0010$\u001a\u00020\u0018H\u0002J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u000fH\u0002J\u0010\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u000fH\u0002J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u000fH\u0002J\u0010\u0010*\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u000fH\u0002J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u000fH\u0002J\b\u0010-\u001a\u00020\u0018H\u0002J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u000fH\u0002J\b\u00100\u001a\u00020\u0018H\u0002J\b\u00101\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/soc/agent/ui/AppLockGateActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/soc/agent/databinding/ActivityAppLockGateBinding;", "biometricAuthenticating", "", "biometricPrompt", "Landroidx/biometric/BiometricPrompt;", "consecutiveFailures", "", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "firstPassword", "", "firstPattern", "", "firstPin", "gate", "mode", "setupStep", "targetPackage", "configureForMode", "", "finishOk", "getAppLabel", "packageName", "handlePatternStep", "pattern", "initBiometricPrompt", "onAction", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSetupBiometric", "onSetupBiometricSuccess", "onSetupPassword", "password", "onSetupPin", "pin", "onVerifyPassword", "onVerifyPin", "recordFailedAttempt", "reason", "resetFailureCounter", "showError", "message", "startBiometricAuth", "unlockSuccess", "Companion", "app_debug"})
public final class AppLockGateActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MODE = "extra_mode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_GATE = "extra_gate";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_PACKAGE = "extra_package";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MODE_SETUP = "setup";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MODE_VERIFY = "verify";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GATE_PIN = "pin";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GATE_PATTERN = "pattern";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GATE_PASSWORD = "password";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GATE_BIOMETRIC = "biometric";
    private com.soc.agent.databinding.ActivityAppLockGateBinding binding;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String mode = "setup";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String gate = "pin";
    @org.jetbrains.annotations.Nullable()
    private java.lang.String targetPackage;
    private int setupStep = 0;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<java.lang.Integer> firstPattern;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String firstPin;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String firstPassword;
    private int consecutiveFailures = 0;
    private final java.util.concurrent.ExecutorService executor = null;
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private boolean biometricAuthenticating = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.ui.AppLockGateActivity.Companion Companion = null;
    
    public AppLockGateActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initBiometricPrompt() {
    }
    
    private final void onSetupBiometricSuccess() {
    }
    
    private final void configureForMode() {
    }
    
    private final void onAction() {
    }
    
    private final void onVerifyPin(java.lang.String pin) {
    }
    
    private final void onVerifyPassword(java.lang.String password) {
    }
    
    private final void onSetupPin(java.lang.String pin) {
    }
    
    private final void onSetupPassword(java.lang.String password) {
    }
    
    private final void startBiometricAuth() {
    }
    
    private final void onSetupBiometric() {
    }
    
    private final void handlePatternStep(java.util.List<java.lang.Integer> pattern) {
    }
    
    private final void showError(java.lang.String message) {
    }
    
    private final void unlockSuccess() {
    }
    
    /**
     * Setup-mode success: gate saved and reported back to the caller.
     */
    private final void finishOk() {
    }
    
    private final void recordFailedAttempt(java.lang.String reason) {
    }
    
    /**
     * Reset failure counter on successful unlock.
     */
    private final void resetFailureCounter() {
    }
    
    private final java.lang.String getAppLabel(java.lang.String packageName) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/soc/agent/ui/AppLockGateActivity$Companion;", "", "()V", "EXTRA_GATE", "", "EXTRA_MODE", "EXTRA_PACKAGE", "GATE_BIOMETRIC", "GATE_PASSWORD", "GATE_PATTERN", "GATE_PIN", "MODE_SETUP", "MODE_VERIFY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}