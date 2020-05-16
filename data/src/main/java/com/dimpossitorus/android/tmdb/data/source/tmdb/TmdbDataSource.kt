package com.dimpossitorus.android.tmdb.data.source.tmdb

import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse
import retrofit2.Response

interface TmdbDataSource {

    interface Remote {
        suspend fun getGenres(): Response<GenreResponse>

        suspend fun getDiscover(page: Int, genreId: Int?): Response<DiscoverResponse>

        suspend fun getMovieDetail(movieId: Int, additionalInfo: List<String>?): Response<Movie>

        suspend fun getMovieReview(movieId: Int, page: Int): Response<ReviewResponse>
    }
}