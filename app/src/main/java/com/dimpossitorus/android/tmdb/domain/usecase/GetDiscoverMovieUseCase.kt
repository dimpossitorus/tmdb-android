package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.data.repository.TmdbRemoteDataSource
import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import javax.inject.Inject

class GetDiscoverMovieUseCase @Inject constructor(private val tmdbRepository: TmdbRemoteDataSource) {

    suspend fun execute(genreId: Int, page: Int): DiscoverResponse? {
        return tmdbRepository.getDiscover(page, genreId)
    }
}