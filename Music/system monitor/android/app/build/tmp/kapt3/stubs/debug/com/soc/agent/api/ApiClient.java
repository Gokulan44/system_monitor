package com.soc.agent.api;

import android.content.Context;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

/**
 * Builds the [ApiService] Retrofit instance for the SOC platform.
 *
 * Every request carries:
 * - `X-API-Key`: agent API key (created on the server by seed.js)
 * - `X-Agent-Id`: the device's stable agent UUID
 *
 * The server requires both headers for every endpoint except register
 * (which accepts the agent id in the body as a fallback).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/soc/agent/api/ApiClient;", "", "()V", "DEFAULT_TIMEOUT_SECONDS", "", "FALLBACK_BASE_URL", "", "api", "Lcom/soc/agent/api/ApiService;", "context", "Landroid/content/Context;", "serverUrl", "apiKey", "agentId", "normalizeBaseUrl", "url", "app_debug"})
public final class ApiClient {
    private static final long DEFAULT_TIMEOUT_SECONDS = 30L;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String FALLBACK_BASE_URL = "http://10.0.2.2:3000/";
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.api.ApiClient INSTANCE = null;
    
    private ApiClient() {
        super();
    }
    
    /**
     * Build (or rebuild) the API service for the given credentials.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.soc.agent.api.ApiService api(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String serverUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    java.lang.String agentId) {
        return null;
    }
    
    /**
     * Normalise a user-entered server URL: trims whitespace and guarantees a
     * single trailing slash (required by Retrofit for relative endpoints).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String normalizeBaseUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
}