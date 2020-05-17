package com.dimpossitorus.android.tmdb

import android.app.Application
import com.dimpossitorus.android.tmdb.di.AppComponent
import com.dimpossitorus.android.tmdb.di.AppModule
import com.dimpossitorus.android.tmdb.di.DaggerAppComponent

class BaseApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent= DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}