package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.data.repository.TmdbRemoteDataSource
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val tmdbRepository: TmdbRemoteDataSource) {

    suspend fun execute(): GenreResponse? {
        return tmdbRepository.getGenres()
    }
}