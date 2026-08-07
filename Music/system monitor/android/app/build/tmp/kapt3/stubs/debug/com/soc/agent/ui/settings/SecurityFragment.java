package com.soc.agent.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.soc.agent.R;
import com.soc.agent.data.SettingsRepository;
import com.soc.agent.ui.AppLockGateActivity;
import com.soc.agent.ui.ReportsFragment;
import com.soc.agent.ui.applock.AppLockFragment;

/**
 * SecuritySettingsFragment — Security settings screen.
 *
 * Provides toggles and setup actions for all security-related features:
 * - Fingerprint Login (SwitchMaterial)
 * - Face Unlock (SwitchMaterial)
 * - PIN Lock toggle + "Set up PIN" button (navigates to PinLockActivity)
 * - Pattern Lock toggle + "Set up Pattern" button (navigates to AppLockGateActivity)
 * - Password Lock toggle + "Set up Password" button (navigates to AppLockGateActivity)
 * - App Lock Settings (navigates to AppLockFragment)
 * - Trusted Devices list (RecyclerView)
 * - Security Alerts toggle (SwitchMaterial)
 * - Login Notifications toggle (SwitchMaterial)
 * - Security Report button (navigates to Reports)
 *
 * Uses [SettingsRepository] to persist every toggle and setting.
 * Toolbar includes a back arrow that pops the fragment back stack.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u000289B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u001dH\u0002J\b\u0010%\u001a\u00020\u001dH\u0002J\b\u0010&\u001a\u00020\u001dH\u0002J\b\u0010'\u001a\u00020\u001dH\u0002J$\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\u001dH\u0016J\u001a\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u00102\u001a\u00020\u001dH\u0002J\b\u00103\u001a\u00020\u001dH\u0002J\b\u00104\u001a\u00020\u001dH\u0002J\u0010\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u000207H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/soc/agent/ui/settings/SecurityFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Landroid/view/View;", "binding", "getBinding", "()Landroid/view/View;", "btnAppLockSettings", "Lcom/google/android/material/button/MaterialButton;", "btnSecurityReport", "btnSetupPassword", "btnSetupPattern", "btnSetupPin", "rvTrustedDevices", "Landroidx/recyclerview/widget/RecyclerView;", "switchFaceUnlock", "Lcom/google/android/material/switchmaterial/SwitchMaterial;", "switchFingerprint", "switchLoginNotifications", "switchPasswordLock", "switchPatternLock", "switchPinLock", "switchSecurityAlerts", "toolbar", "Lcom/google/android/material/appbar/MaterialToolbar;", "tvTrustedCount", "Landroid/widget/TextView;", "bindViews", "", "root", "generateTrustedDeviceList", "", "Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDevice;", "count", "", "loadSettings", "navigateToPasswordSetup", "navigateToPatternSetup", "navigateToPinSetup", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupListeners", "setupToolbar", "setupTrustedDevicesList", "showToast", "message", "", "TrustedDevice", "TrustedDeviceAdapter", "app_debug"})
public final class SecurityFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private android.view.View _binding;
    private com.google.android.material.appbar.MaterialToolbar toolbar;
    private com.google.android.material.switchmaterial.SwitchMaterial switchFingerprint;
    private com.google.android.material.switchmaterial.SwitchMaterial switchFaceUnlock;
    private com.google.android.material.switchmaterial.SwitchMaterial switchPinLock;
    private com.google.android.material.button.MaterialButton btnSetupPin;
    private com.google.android.material.switchmaterial.SwitchMaterial switchPatternLock;
    private com.google.android.material.button.MaterialButton btnSetupPattern;
    private com.google.android.material.switchmaterial.SwitchMaterial switchPasswordLock;
    private com.google.android.material.button.MaterialButton btnSetupPassword;
    private com.google.android.material.button.MaterialButton btnAppLockSettings;
    private androidx.recyclerview.widget.RecyclerView rvTrustedDevices;
    private android.widget.TextView tvTrustedCount;
    private com.google.android.material.switchmaterial.SwitchMaterial switchSecurityAlerts;
    private com.google.android.material.switchmaterial.SwitchMaterial switchLoginNotifications;
    private com.google.android.material.button.MaterialButton btnSecurityReport;
    
    public SecurityFragment() {
        super();
    }
    
    private final android.view.View getBinding() {
        return null;
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
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void bindViews(android.view.View root) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void loadSettings() {
    }
    
    private final void setupListeners() {
    }
    
    private final void setupTrustedDevicesList() {
    }
    
    /**
     * Build a simple list of trusted device display names.
     * In a real implementation this would come from a database or server.
     */
    private final java.util.List<com.soc.agent.ui.settings.SecurityFragment.TrustedDevice> generateTrustedDeviceList(int count) {
        return null;
    }
    
    private final void navigateToPinSetup() {
    }
    
    private final void navigateToPatternSetup() {
    }
    
    private final void navigateToPasswordSetup() {
    }
    
    private final void showToast(java.lang.String message) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDevice;", "", "name", "", "type", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class TrustedDevice {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String type = null;
        
        public TrustedDevice(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String type) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.ui.settings.SecurityFragment.TrustedDevice copy(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String type) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J \u0010\u000e\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDeviceAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDeviceAdapter$DeviceViewHolder;", "Lcom/soc/agent/ui/settings/SecurityFragment;", "devices", "", "Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDevice;", "(Lcom/soc/agent/ui/settings/SecurityFragment;Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DeviceViewHolder", "app_debug"})
    final class TrustedDeviceAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.soc.agent.ui.settings.SecurityFragment.TrustedDeviceAdapter.DeviceViewHolder> {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.soc.agent.ui.settings.SecurityFragment.TrustedDevice> devices = null;
        
        public TrustedDeviceAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.soc.agent.ui.settings.SecurityFragment.TrustedDevice> devices) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.soc.agent.ui.settings.SecurityFragment.TrustedDeviceAdapter.DeviceViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.soc.agent.ui.settings.SecurityFragment.TrustedDeviceAdapter.DeviceViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDeviceAdapter$DeviceViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDeviceAdapter;Landroid/view/View;)V", "tvDeviceName", "Landroid/widget/TextView;", "tvDeviceType", "bind", "", "device", "Lcom/soc/agent/ui/settings/SecurityFragment$TrustedDevice;", "app_debug"})
        public final class DeviceViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvDeviceName = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView tvDeviceType = null;
            
            public DeviceViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View itemView) {
                super(null);
            }
            
            public final void bind(@org.jetbrains.annotations.NotNull()
            com.soc.agent.ui.settings.SecurityFragment.TrustedDevice device) {
            }
        }
    }
}