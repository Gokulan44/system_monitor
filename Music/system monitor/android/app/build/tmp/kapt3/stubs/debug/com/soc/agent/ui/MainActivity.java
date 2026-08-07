package com.soc.agent.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.soc.agent.R;
import com.soc.agent.databinding.ActivityMainBinding;
import com.soc.agent.ui.dashboard.DashboardFragment;
import com.soc.agent.ui.device.DeviceMonitorFragment;
import com.soc.agent.ui.security.SecurityMonitorFragment;
import com.soc.agent.ui.applock.AppLockFragment;
import com.soc.agent.ui.appusage.AppUsageContainerFragment;

/**
 * Single-activity shell hosting the eleven main sections behind a
 * BottomNavigationView:
 * - Dashboard    -> [DashboardFragment]
 * - Device       -> [DeviceMonitorFragment]
 * - Security     -> [SecurityMonitorFragment]
 * - App Lock     -> [AppLockFragment]
 * - App Usage    -> [AppUsageContainerFragment]
 * - File Center  -> [FileCenterFragment]
 * - Network      -> [NetworkCenterFragment]
 * - Privacy      -> [PrivacyCenterFragment]
 * - Notifications -> [NotificationCenterFragment]
 * - Reports      -> [ReportsFragment]
 * - Settings     -> [SettingsFragment]
 *
 * Fragments are switched with add/hide/show transactions so each section keeps
 * its state (scroll position, loaded data, open sub-tabs) while the user
 * navigates around. Fragment instances are cached in [fragments] keyed by the
 * bottom-nav item id; on configuration change they are recovered from the
 * FragmentManager by tag.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\bH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/soc/agent/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/soc/agent/databinding/ActivityMainBinding;", "fragments", "", "", "Landroidx/fragment/app/Fragment;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showFragment", "itemId", "fragment", "tagFor", "", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.soc.agent.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Integer, androidx.fragment.app.Fragment> fragments = null;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showFragment(int itemId, androidx.fragment.app.Fragment fragment) {
    }
    
    private final java.lang.String tagFor(int itemId) {
        return null;
    }
}