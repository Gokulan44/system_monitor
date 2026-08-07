package com.soc.agent.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import com.soc.agent.R;
import com.soc.agent.databinding.ActivityPinBinding;
import com.soc.agent.security.Sha256;
import com.soc.agent.utils.Prefs;
import java.util.concurrent.Executor;

/**
 * Local PIN gate with biometric fallback.
 *
 * Two modes, selected via [EXTRA_MODE]:
 * - [MODE_SETUP]: the user creates a 4-6 digit PIN (entered twice, one field with
 *   a two-step state machine). The SHA-256 hash is stored in [Prefs.pinHash].
 *   After saving we optionally offer biometric enrollment. Always proceeds to
 *   [MainActivity] on success.
 * - [MODE_VERIFY] (default): the user must enter the PIN (compared against
 *   [Prefs.pinHash]) or authenticate with a strong biometric when enabled. If no
 *   PIN is configured the gate is skipped and we finish straight through to
 *   [MainActivity].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\u0012\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/soc/agent/ui/PinLockActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/soc/agent/databinding/ActivityPinBinding;", "biometricPrompt", "Landroidx/biometric/BiometricPrompt;", "executor", "Ljava/util/concurrent/Executor;", "firstPin", "", "mode", "", "setupStep", "buildPromptInfo", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "canUseBiometric", "", "configureForMode", "", "handleSetupStep", "pin", "maybePromptBiometricEnrollment", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onVerifyClicked", "proceedToMain", "setupBiometricPrompt", "Companion", "app_debug"})
public final class PinLockActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MODE = "extra_mode";
    public static final int MODE_VERIFY = 0;
    public static final int MODE_SETUP = 1;
    private com.soc.agent.databinding.ActivityPinBinding binding;
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private java.util.concurrent.Executor executor;
    private int mode = 0;
    private int setupStep = 0;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String firstPin = "";
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.ui.PinLockActivity.Companion Companion = null;
    
    public PinLockActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupBiometricPrompt() {
    }
    
    private final void configureForMode() {
    }
    
    private final void onVerifyClicked() {
    }
    
    private final void handleSetupStep(java.lang.String pin) {
    }
    
    private final void maybePromptBiometricEnrollment() {
    }
    
    private final boolean canUseBiometric() {
        return false;
    }
    
    private final androidx.biometric.BiometricPrompt.PromptInfo buildPromptInfo() {
        return null;
    }
    
    private final void proceedToMain() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/soc/agent/ui/PinLockActivity$Companion;", "", "()V", "EXTRA_MODE", "", "MODE_SETUP", "", "MODE_VERIFY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}