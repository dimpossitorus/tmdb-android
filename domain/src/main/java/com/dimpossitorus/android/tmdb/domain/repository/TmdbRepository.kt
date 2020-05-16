package com.dimpossitorus.android.tmdb.domain.repository

import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse

interface TmdbRepository {

    suspend fun getGenres(): GenreResponse?

    suspend fun getDiscover(page: Int, genreId: Int? = null): DiscoverResponse?

    suspend fun getMovieDetail(movieId: Int, additionalInfo: List<String>?): Movie?

    suspend fun getMovieReview(movieId: Int, page: Int): ReviewResponse?
}

