package com.dimpossitorus.android.tmdb.data.di

import com.dimpossitorus.android.tmdb.data.api.TmdbApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class NetworkModule {

    @Provides
    fun provideTmdbApiService():TmdbApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }
}