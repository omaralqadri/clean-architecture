package com.omaralkadri.cleanarchitecture.data.di

import android.content.Context
import com.google.gson.Gson
import com.omaralkadri.cleanarchitecture.utils.cache.ApplicationCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationCache(@ApplicationContext appContext: Context): ApplicationCache {
        return ApplicationCache(appContext, Gson())
    }
}
