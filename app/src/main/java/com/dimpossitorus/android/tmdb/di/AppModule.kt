package com.dimpossitorus.android.tmdb.di

import android.content.Context
import com.dimpossitorus.android.tmdb.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: BaseApplication) {

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application
    }
}