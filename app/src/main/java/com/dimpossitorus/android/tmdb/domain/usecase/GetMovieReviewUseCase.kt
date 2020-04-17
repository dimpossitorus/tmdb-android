package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.data.repository.TmdbRemoteDataSource
import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse
import javax.inject.Inject

class GetMovieReviewUseCase @Inject constructor(private val tmdbRepository: TmdbRemoteDataSource) {

    suspend fun execute(movieId: Int, page: Int): ReviewResponse? {
        return tmdbRepository.getMovieReview(movieId, page)
    }
}