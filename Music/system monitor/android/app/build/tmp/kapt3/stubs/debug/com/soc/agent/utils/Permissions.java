package com.soc.agent.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Small runtime-permission helper. The agent needs a handful of runtime
 * permissions (POST_NOTIFICATIONS, READ_EXTERNAL_STORAGE / READ_MEDIA_*,
 * ACCESS_FINE_LOCATION, ...) that must be requested from an Activity.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ'\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\r\"\u00020\n\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/soc/agent/utils/Permissions;", "", "()V", "REQUEST_CODE", "", "has", "", "activity", "Landroid/app/Activity;", "permission", "", "requestIfNeeded", "permissions", "", "(Landroid/app/Activity;[Ljava/lang/String;)Z", "app_debug"})
public final class Permissions {
    private static final int REQUEST_CODE = 23071;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.utils.Permissions INSTANCE = null;
    
    private Permissions() {
        super();
    }
    
    /**
     * Checks the given permissions and, if any are missing, launches the system
     * permission dialog for them.
     *
     * @return true when every permission is already granted (nothing requested),
     *        false when a request was launched (the result arrives via
     *        Activity.onRequestPermissionsResult).
     */
    public final boolean requestIfNeeded(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String... permissions) {
        return false;
    }
    
    /**
     * Convenience check without launching a dialog.
     */
    public final boolean has(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String permission) {
        return false;
    }
}