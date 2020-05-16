package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse
import com.dimpossitorus.android.tmdb.domain.repository.TmdbRepository
import javax.inject.Inject

class GetMovieReviewUseCase @Inject constructor(private val tmdbRepository: TmdbRepository) {

    suspend fun execute(movieId: Int, page: Int): ReviewResponse? {
        return tmdbRepository.getMovieReview(movieId, page)
    }
}