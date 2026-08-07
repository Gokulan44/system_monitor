package com.soc.agent.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.soc.agent.R;
import com.soc.agent.data.SecurityRepository;
import com.soc.agent.databinding.ActivityLoginBinding;
import com.soc.agent.utils.Prefs;
import java.util.UUID;

/**
 * First-run registration screen (also the launcher activity).
 *
 * Flow:
 * - If the device is already configured ([Prefs.isConfigured]) we skip the form and
 *   go straight to the PIN gate ([PinLockActivity] in verify mode).
 * - Otherwise the user enters the SOC server URL, the agent API key and a device
 *   name. On "Connect" the repository registers this agent with the server; when
 *   registration succeeds we either jump straight to [MainActivity] or, when the
 *   "Protect with PIN" switch is on, to [PinLockActivity] in setup mode first.
 *
 * The agent id is a stable random UUID persisted in [Prefs] so re-registration
 * updates the same device row on the server.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0013"}, d2 = {"Lcom/soc/agent/ui/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/soc/agent/databinding/ActivityLoginBinding;", "repository", "Lcom/soc/agent/data/SecurityRepository;", "getRepository", "()Lcom/soc/agent/data/SecurityRepository;", "repository$delegate", "Lkotlin/Lazy;", "attemptRegister", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setBusy", "busy", "", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.soc.agent.databinding.ActivityLoginBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repository$delegate = null;
    
    public LoginActivity() {
        super();
    }
    
    private final com.soc.agent.data.SecurityRepository getRepository() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Validates the three inputs, persists the connection config in [Prefs] and
     * calls [SecurityRepository.register] on the main dispatcher's coroutine.
     * Shows progress while the network call is in flight and surfaces errors
     * through [repository.lastError] or the thrown exception.
     */
    private final void attemptRegister() {
    }
    
    private final void setBusy(boolean busy) {
    }
}