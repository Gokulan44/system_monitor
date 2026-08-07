package com.soc.agent.ui.settings;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.soc.agent.R;
import com.soc.agent.data.SettingsRepository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 92\u00020\u0001:\u00019B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J&\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J$\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010'\u001a\u00020\u0017H\u0016J\b\u0010(\u001a\u00020\u0017H\u0002J<\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0*2\u0006\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00052\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\b\u0002\u0010.\u001a\u00020\u0005H\u0002J\b\u0010/\u001a\u00020\u0017H\u0002J\u0010\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\u0005H\u0002J\b\u00102\u001a\u00020\u0017H\u0002J4\u00103\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u001d2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001706H\u0002J\u0018\u00107\u001a\u00020\u00172\u0006\u00108\u001a\u00020\n2\u0006\u00101\u001a\u00020\u0005H\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/soc/agent/ui/settings/PrivacyFragment;", "Landroidx/fragment/app/Fragment;", "()V", "cameraPermLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "locationPermLauncher", "storagePermLauncher", "tvCameraStatus", "Landroid/widget/TextView;", "tvLocationStatus", "tvStorageStatus", "tvUsageStatus", "buildCard", "Landroid/widget/LinearLayout;", "buildLayout", "Landroid/view/View;", "buttonRow", "label", "subtitle", "onClick", "Lkotlin/Function0;", "", "dp", "", "value", "handleCameraResult", "granted", "", "handleLocationResult", "handleStorageResult", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "openPrivacyPolicy", "permissionRow", "Lkotlin/Pair;", "title", "statusText", "onRequest", "buttonText", "refreshPermissionStatuses", "sectionTitle", "text", "showExportDialog", "toggleRow", "checked", "onToggled", "Lkotlin/Function1;", "updateStatusTv", "tv", "Companion", "app_debug"})
public final class PrivacyFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PRIVACY_POLICY_URL = "https://socagent.app/privacy";
    private android.widget.TextView tvCameraStatus;
    private android.widget.TextView tvLocationStatus;
    private android.widget.TextView tvStorageStatus;
    private android.widget.TextView tvUsageStatus;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> cameraPermLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> locationPermLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> storagePermLauncher = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.ui.settings.PrivacyFragment.Companion Companion = null;
    
    public PrivacyFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final android.view.View buildLayout() {
        return null;
    }
    
    private final android.widget.LinearLayout buildCard() {
        return null;
    }
    
    private final int dp(int value) {
        return 0;
    }
    
    private final android.widget.TextView sectionTitle(java.lang.String text) {
        return null;
    }
    
    private final android.widget.LinearLayout toggleRow(java.lang.String label, java.lang.String subtitle, boolean checked, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggled) {
        return null;
    }
    
    private final kotlin.Pair<android.widget.LinearLayout, android.widget.TextView> permissionRow(java.lang.String title, java.lang.String statusText, kotlin.jvm.functions.Function0<kotlin.Unit> onRequest, java.lang.String buttonText) {
        return null;
    }
    
    private final android.widget.LinearLayout buttonRow(java.lang.String label, java.lang.String subtitle, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
        return null;
    }
    
    private final void handleCameraResult(boolean granted) {
    }
    
    private final void handleLocationResult(boolean granted) {
    }
    
    private final void handleStorageResult(boolean granted) {
    }
    
    private final void refreshPermissionStatuses() {
    }
    
    private final void updateStatusTv(android.widget.TextView tv, java.lang.String text) {
    }
    
    private final void showExportDialog() {
    }
    
    private final void openPrivacyPolicy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/soc/agent/ui/settings/PrivacyFragment$Companion;", "", "()V", "PRIVACY_POLICY_URL", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}