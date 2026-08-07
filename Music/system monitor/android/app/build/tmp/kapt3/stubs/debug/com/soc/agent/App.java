package com.soc.agent;

import android.app.Application;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.WorkManager;
import com.soc.agent.data.SecurityRepository;
import com.soc.agent.services.SyncWorker;
import java.util.concurrent.TimeUnit;

/**
 * Application entry point. Owns the singleton [SecurityRepository] and
 * schedules the periodic telemetry sync worker.
 *
 * SERVICES LAYER CONTRACT (implemented by AndroidSecurityAgent/services/):
 * - SyncWorker(appContext, workerParams) : CoroutineWorker whose doWork()
 *   performs a heartbeat/policy pull via SecurityRepository.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0002\u00a8\u0006\u0007"}, d2 = {"Lcom/soc/agent/App;", "Landroid/app/Application;", "()V", "onCreate", "", "schedulePeriodicSync", "Companion", "app_debug"})
public final class App extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SYNC_WORK_NAME = "soc_agent_periodic_sync";
    
    /**
     * Always set in onCreate — safe to access after app start.
     */
    private static com.soc.agent.App instance;
    
    /**
     * Shared repository instance for the whole application.
     */
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy<?> repository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.App.Companion Companion = null;
    
    public App() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    /**
     * Enqueue a 15-minute heartbeat WorkManager job. KEEP matches an
     * already-enqueued job rather than replacing it, so repeated app starts
     * never stack duplicate workers.
     */
    private final void schedulePeriodicSync() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lcom/soc/agent/App$Companion;", "", "()V", "SYNC_WORK_NAME", "", "<set-?>", "Lcom/soc/agent/App;", "instance", "getInstance", "()Lcom/soc/agent/App;", "repository", "Lcom/soc/agent/data/SecurityRepository;", "getRepository", "()Lcom/soc/agent/data/SecurityRepository;", "repository$delegate", "Lkotlin/Lazy;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Always set in onCreate — safe to access after app start.
         */
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.App getInstance() {
            return null;
        }
        
        /**
         * Shared repository instance for the whole application.
         */
        @org.jetbrains.annotations.NotNull()
        public final com.soc.agent.data.SecurityRepository getRepository() {
            return null;
        }
    }
}