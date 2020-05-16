package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.repository.TmdbRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val tmdbRepository: TmdbRepository) {

    suspend fun execute(movieId: Int, additionalInfo: List<String>): Movie? {
        return tmdbRepository.getMovieDetail(movieId, additionalInfo)
    }
}