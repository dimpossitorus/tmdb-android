package com.dimpossitorus.android.tmdb

import com.dimpossitorus.android.tmdb.di.AppComponent
import com.dimpossitorus.android.tmdb.di.AppModule
import com.dimpossitorus.android.tmdb.di.DaggerAppComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication

class BaseApplication : SplitCompatApplication() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}