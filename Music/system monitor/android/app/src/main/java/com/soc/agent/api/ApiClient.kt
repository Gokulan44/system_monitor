package com.soc.agent.api

import android.content.Context
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Builds the [ApiService] Retrofit instance for the SOC platform.
 *
 * Every request carries:
 *  - `X-API-Key`: agent API key (created on the server by seed.js)
 *  - `X-Agent-Id`: the device's stable agent UUID
 *
 * The server requires both headers for every endpoint except register
 * (which accepts the agent id in the body as a fallback).
 */
object ApiClient {

    private const val DEFAULT_TIMEOUT_SECONDS = 30L
    private const val FALLBACK_BASE_URL = "http://10.0.2.2:3000/"

    /** Build (or rebuild) the API service for the given credentials. */
    fun api(context: Context, serverUrl: String, apiKey: String, agentId: String): ApiService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                    .header("User-Agent", "SOC-Agent/1.0 (Android)")
                if (apiKey.isNotBlank()) builder.header("X-API-Key", apiKey)
                if (agentId.isNotBlank()) builder.header("X-Agent-Id", agentId)
                chain.proceed(builder.build())
            }
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(normalizeBaseUrl(serverUrl))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)
    }

    /**
     * Normalise a user-entered server URL: trims whitespace and guarantees a
     * single trailing slash (required by Retrofit for relative endpoints).
     */
    fun normalizeBaseUrl(url: String): String {
        val trimmed = url.trim().trimEnd('/')
        return if (trimmed.isEmpty()) FALLBACK_BASE_URL else "$trimmed/"
    }
}