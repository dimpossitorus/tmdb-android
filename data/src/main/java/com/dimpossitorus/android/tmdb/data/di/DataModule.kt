package com.dimpossitorus.android.tmdb.data.di

import com.dimpossitorus.android.tmdb.data.repository.TmdbDataRepository
import com.dimpossitorus.android.tmdb.data.source.tmdb.remote.TmdbRemoteDataSource
import com.dimpossitorus.android.tmdb.domain.repository.TmdbRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataModule {

    @Singleton
    @Provides
    fun provideTmdbRepository(
        remoteDataSource: TmdbRemoteDataSource
    ): TmdbRepository {
        return TmdbDataRepository(remoteDataSource)
    }
}