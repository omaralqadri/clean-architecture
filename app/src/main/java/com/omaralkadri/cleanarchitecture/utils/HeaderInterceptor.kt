package com.omaralkadri.cleanarchitecture.utils

import com.omaralkadri.cleanarchitecture.utils.cache.ApplicationCache
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor(var cache: ApplicationCache) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        var original: Request = chain.request()
        var builder = chain.request().newBuilder()
        builder.addHeader("Authorization", "Bearer ${cache.getUserToken()}")
        builder.addHeader("Content-Type", "application/json;charset=UTF-8")
        builder.addHeader("Accept", "application/vnd.api+json")
        builder.addHeader("Accept-Language", "en")


        return chain.proceed(builder.build())
    }
}
