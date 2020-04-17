package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.data.repository.TmdbRemoteDataSource
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val tmdbRepository: TmdbRemoteDataSource) {

    suspend fun execute(movieId: Int, additionalInfo: List<String>): Movie? {
        return tmdbRepository.getMovieDetail(movieId, additionalInfo)
    }
}