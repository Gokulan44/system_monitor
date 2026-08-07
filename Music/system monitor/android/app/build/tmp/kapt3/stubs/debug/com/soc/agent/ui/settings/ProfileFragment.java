package com.soc.agent.ui.settings;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.soc.agent.R;
import com.soc.agent.data.SettingsRepository;
import com.google.android.material.appbar.MaterialToolbar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ProfileFragment — Profile dashboard showing user avatar, personal info,
 * account status badge, member-since date, registered devices count,
 * security score with circular progress, last login time, and a 6-item
 * quick-action grid.  All data sourced from [SettingsRepository].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001*B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J&\u0010 \u001a\u0004\u0018\u00010\u00182\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u001a\u0010'\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010(\u001a\u00020\u0016H\u0002J\b\u0010)\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/soc/agent/ui/settings/ProfileFragment;", "Landroidx/fragment/app/Fragment;", "()V", "gridQuickActions", "Landroid/widget/GridLayout;", "ivAvatar", "Landroid/widget/ImageView;", "progressSecurityScore", "Landroid/widget/ProgressBar;", "toolbar", "Lcom/google/android/material/appbar/MaterialToolbar;", "tvAccountStatus", "Landroid/widget/TextView;", "tvDeviceCount", "tvEmail", "tvFullName", "tvLastLogin", "tvMemberSince", "tvPhone", "tvSecurityLabel", "tvSecurityScore", "bindViews", "", "view", "Landroid/view/View;", "buildQuickActionsGrid", "createQuickActionItem", "action", "Lcom/soc/agent/ui/settings/ProfileFragment$QuickAction;", "handleQuickAction", "title", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "populateProfile", "setupToolbar", "QuickAction", "app_debug"})
public final class ProfileFragment extends androidx.fragment.app.Fragment {
    private com.google.android.material.appbar.MaterialToolbar toolbar;
    private android.widget.ImageView ivAvatar;
    private android.widget.TextView tvFullName;
    private android.widget.TextView tvEmail;
    private android.widget.TextView tvPhone;
    private android.widget.TextView tvAccountStatus;
    private android.widget.TextView tvMemberSince;
    private android.widget.TextView tvDeviceCount;
    private android.widget.TextView tvLastLogin;
    private android.widget.ProgressBar progressSecurityScore;
    private android.widget.TextView tvSecurityScore;
    private android.widget.TextView tvSecurityLabel;
    private android.widget.GridLayout gridQuickActions;
    
    public ProfileFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
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
    
    private final void bindViews(android.view.View view) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void populateProfile() {
    }
    
    private final void buildQuickActionsGrid() {
    }
    
    private final android.view.View createQuickActionItem(com.soc.agent.ui.settings.ProfileFragment.QuickAction action) {
        return null;
    }
    
    private final void handleQuickAction(java.lang.String title) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lcom/soc/agent/ui/settings/ProfileFragment$QuickAction;", "", "title", "", "iconRes", "", "colorRes", "(Ljava/lang/String;II)V", "getColorRes", "()I", "getIconRes", "getTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    public static final class QuickAction {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        private final int iconRes = 0;
        private final int colorRes = 0;
        
        public QuickAction(@org.jetbrains.annotations.NotNull()
        java.lang.String title, int iconRes, int colorRes) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final int getIconRes() {
            return 0;
        }
        
        public final int getColorRes() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.ui.settings.ProfileFragment.QuickAction copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title, int iconRes, int colorRes) {
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
}