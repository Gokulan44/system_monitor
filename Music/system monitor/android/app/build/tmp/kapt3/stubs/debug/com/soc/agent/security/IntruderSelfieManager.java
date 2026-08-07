package com.soc.agent.security;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.soc.agent.database.AppDatabase;
import com.soc.agent.database.entity.IntruderSelfieEntity;
import kotlinx.coroutines.Dispatchers;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ6\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0019J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0016\u0010\u0002\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/soc/agent/security/IntruderSelfieManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "db", "Lcom/soc/agent/database/AppDatabase;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "settingsManager", "Lcom/soc/agent/security/SecuritySettingsManager;", "captureSelfieBlocking", "", "hasCameraPermission", "", "maybeCaptureSelfie", "", "activity", "Landroid/app/Activity;", "packageName", "appName", "gateMethod", "failureReason", "attemptNumber", "", "requestCameraPermission", "requestCode", "saveImageToStorage", "image", "Landroid/media/Image;", "Companion", "app_debug"})
public final class IntruderSelfieManager {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.database.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.soc.agent.security.SecuritySettingsManager settingsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.soc.agent.security.IntruderSelfieManager INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.IntruderSelfieManager.Companion Companion = null;
    
    private IntruderSelfieManager(android.content.Context context) {
        super();
    }
    
    public final boolean hasCameraPermission() {
        return false;
    }
    
    public final void requestCameraPermission(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, int requestCode) {
    }
    
    public final void maybeCaptureSelfie(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName, @org.jetbrains.annotations.NotNull()
    java.lang.String appName, @org.jetbrains.annotations.NotNull()
    java.lang.String gateMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String failureReason, int attemptNumber) {
    }
    
    private final java.lang.String captureSelfieBlocking() {
        return null;
    }
    
    private final java.lang.String saveImageToStorage(android.media.Image image) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/soc/agent/security/IntruderSelfieManager$Companion;", "", "()V", "INSTANCE", "Lcom/soc/agent/security/IntruderSelfieManager;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.security.IntruderSelfieManager getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}