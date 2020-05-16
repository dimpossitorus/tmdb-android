package com.dimpossitorus.android.tmdb.domain.usecase

import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.repository.TmdbRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val tmdbRepository: TmdbRepository) {

    suspend fun execute(): GenreResponse? {
        return tmdbRepository.getGenres()
    }
}