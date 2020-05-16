package com.dimpossitorus.android.tmdb.data.source.tmdb.remote

import com.dimpossitorus.android.tmdb.data.api.TmdbApi
import com.dimpossitorus.android.tmdb.data.source.tmdb.TmdbDataSource
import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse
import retrofit2.Response
import javax.inject.Inject

const val API_KEY = "9a0a61fee20ec5341f8e061937f20174"

class TmdbRemoteDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : TmdbDataSource.Remote {
    override suspend fun getGenres(): Response<GenreResponse> {
        return tmdbApi.getGenres(API_KEY)
    }

    override suspend fun getDiscover(page: Int, genreId: Int?): Response<DiscoverResponse> {
        val additionalQuery = mutableMapOf<String, String>()
        genreId?.let {
            additionalQuery.put("with_genres", genreId.toString())
        }
        return tmdbApi.getMovieDiscover(API_KEY, page, additionalQuery)
    }

    override suspend fun getMovieDetail(
        movieId: Int,
        additionalInfo: List<String>?
    ): Response<Movie> {
        return tmdbApi.getMovieDetail(movieId, API_KEY, additionalInfo?.joinToString())
    }

    override suspend fun getMovieReview(movieId: Int, page: Int): Response<ReviewResponse> {
        return tmdbApi.getMovieReview(movieId, API_KEY, page)
    }

}