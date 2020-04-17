package com.dimpossitorus.android.tmdb.data.repository

import com.dimpossitorus.android.tmdb.data.api.API_KEY
import com.dimpossitorus.android.tmdb.data.api.TmdbApi
import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse
import javax.inject.Inject

class TmdbRemoteDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) {

    suspend fun getGenres(): GenreResponse? {
        val response = tmdbApi.getGenres(API_KEY)
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }

    suspend fun getDiscover(page: Int, genreId: Int? = null): DiscoverResponse? {
        val additionalQuery = mutableMapOf<String, String>()
        genreId?.let {
            additionalQuery.put("with_genres", genreId.toString())
        }
        val response = tmdbApi.getMovieDiscover(API_KEY, page, additionalQuery)
        if (response.isSuccessful) {
            return response.body()
        } else return null

    }

    suspend fun getMovieDetail(movieId: Int, additionalInfo: List<String>?): Movie? {
        val response = tmdbApi.getMovieDetail(movieId, API_KEY, additionalInfo?.joinToString())
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }

    suspend fun getMovieReview(movieId: Int, page: Int): ReviewResponse? {
        val response = tmdbApi.getMovieReview(movieId, API_KEY, page)
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }
}