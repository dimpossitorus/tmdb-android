package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.repository.TmdbRepository
import javax.inject.Inject

class GetDiscoverMovieUseCase @Inject constructor(private val tmdbRepository: TmdbRepository) {

    suspend fun execute(genreId: Int, page: Int): DiscoverResponse? {
        return tmdbRepository.getDiscover(page, genreId)
    }
}