package com.dimpossitorus.android.tmdb.data.repository

import com.dimpossitorus.android.tmdb.data.api.API_KEY
import com.dimpossitorus.android.tmdb.data.api.TmdbApi
import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie
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
        val additionalQuery = mutableMapOf<String, Any>()
        genreId?.let {
            additionalQuery.put("with_genres", genreId)
        }
        val response = tmdbApi.getMovieDiscover(API_KEY, page, additionalQuery)
        if (response.isSuccessful) {
            return response.body()
        } else return null

    }

    suspend fun getMovieDetail(movieId: Int, additionalInfo: List<String>?): Movie? {
        val response = tmdbApi.getMovieDetail(API_KEY, movieId, additionalInfo?.joinToString())
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }
}