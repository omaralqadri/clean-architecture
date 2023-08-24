package com.omaralkadri.cleanarchitecture.utils

import com.google.gson.Gson
import com.omaralkadri.cleanarchitecture.ui.currentContext
import com.omaralkadri.cleanarchitecture.utils.cache.ApplicationCache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object Utils {
    private var logging = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    var clientAuth: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .addInterceptor(HeaderInterceptor(ApplicationCache(currentContext, Gson())))
        .addInterceptor(logging)
        .build()
}