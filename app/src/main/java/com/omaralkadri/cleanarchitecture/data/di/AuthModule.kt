package com.omaralkadri.cleanarchitecture.data.di

import com.omaralkadri.cleanarchitecture.data.api.AuthService
import com.omaralkadri.cleanarchitecture.utils.Constants.Companion.BASE_URL
import com.omaralkadri.cleanarchitecture.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthService(): AuthService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(Utils.clientAuth)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AuthService::class.java)
}

