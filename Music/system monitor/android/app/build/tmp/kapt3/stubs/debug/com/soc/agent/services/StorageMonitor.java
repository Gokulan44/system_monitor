package com.soc.agent.services;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.soc.agent.api.dto.StorageSample;

/**
 * Samples storage usage for the internal data partition and, when present, the
 * external (SD) storage, using [StatFs] on the respective mount points.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J(\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/soc/agent/services/StorageMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sample", "", "Lcom/soc/agent/api/dto/StorageSample;", "statFor", "path", "Ljava/io/File;", "filesystem", "", "mount", "type", "app_debug"})
public final class StorageMonitor {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public StorageMonitor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.soc.agent.api.dto.StorageSample> sample() {
        return null;
    }
    
    private final com.soc.agent.api.dto.StorageSample statFor(java.io.File path, java.lang.String filesystem, java.lang.String mount, java.lang.String type) {
        return null;
    }
}