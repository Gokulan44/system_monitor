package com.soc.agent.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.soc.agent.R;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J&\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002J\b\u0010\u001e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/soc/agent/ui/settings/HelpSupportFragment;", "Landroidx/fragment/app/Fragment;", "()V", "btnContactSupport", "Landroid/view/View;", "btnReportBug", "btnRequestFeature", "btnSubmitFeedback", "btnUserGuide", "etFeedback", "Landroid/widget/EditText;", "getAppVersion", "", "getDeviceInfoFooter", "initViews", "", "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "openContactSupport", "openReportBug", "openRequestFeature", "openUserGuide", "setupButtons", "setupFeedback", "setupToolbar", "submitFeedback", "feedback", "FAQItem", "app_debug"})
public final class HelpSupportFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private android.widget.EditText etFeedback;
    @org.jetbrains.annotations.Nullable()
    private android.view.View btnSubmitFeedback;
    @org.jetbrains.annotations.Nullable()
    private android.view.View btnUserGuide;
    @org.jetbrains.annotations.Nullable()
    private android.view.View btnContactSupport;
    @org.jetbrains.annotations.Nullable()
    private android.view.View btnReportBug;
    @org.jetbrains.annotations.Nullable()
    private android.view.View btnRequestFeature;
    
    public HelpSupportFragment() {
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
    
    private final void initViews(android.view.View view) {
    }
    
    private final void setupToolbar(android.view.View view) {
    }
    
    private final void setupButtons() {
    }
    
    private final void setupFeedback() {
    }
    
    private final void openUserGuide() {
    }
    
    private final void openContactSupport() {
    }
    
    private final void openReportBug() {
    }
    
    private final void openRequestFeature() {
    }
    
    private final void submitFeedback(java.lang.String feedback) {
    }
    
    private final java.lang.String getAppVersion() {
        return null;
    }
    
    private final java.lang.String getDeviceInfoFooter() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/soc/agent/ui/settings/HelpSupportFragment$FAQItem;", "", "question", "", "answer", "(Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "getQuestion", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class FAQItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String question = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String answer = null;
        
        public FAQItem(@org.jetbrains.annotations.NotNull()
        java.lang.String question, @org.jetbrains.annotations.NotNull()
        java.lang.String answer) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getQuestion() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAnswer() {
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
        public final com.soc.agent.ui.settings.HelpSupportFragment.FAQItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String question, @org.jetbrains.annotations.NotNull()
        java.lang.String answer) {
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