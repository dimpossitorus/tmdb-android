package com.dimpossitorus.android.tmdb.data.repository

import com.dimpossitorus.android.tmdb.data.source.tmdb.remote.TmdbRemoteDataSource
import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse
import com.dimpossitorus.android.tmdb.domain.repository.TmdbRepository
import javax.inject.Inject

const val API_KEY = "9a0a61fee20ec5341f8e061937f20174"

class TmdbDataRepository @Inject constructor(
    private val tmdbRemoteDataSource: TmdbRemoteDataSource
) : TmdbRepository {

    override suspend fun getGenres(): GenreResponse? {
        val response = tmdbRemoteDataSource.getGenres()
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }

    override suspend fun getDiscover(page: Int, genreId: Int?): DiscoverResponse? {
        val additionalQuery = mutableMapOf<String, String>()
        genreId?.let {
            additionalQuery.put("with_genres", genreId.toString())
        }
        val response = tmdbRemoteDataSource.getDiscover(page, genreId)
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }

    override suspend fun getMovieDetail(movieId: Int, additionalInfo: List<String>?): Movie? {
        val response = tmdbRemoteDataSource.getMovieDetail(movieId, additionalInfo)
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }

    override suspend fun getMovieReview(movieId: Int, page: Int): ReviewResponse? {
        val response = tmdbRemoteDataSource.getMovieReview(movieId, page)
        if (response.isSuccessful) {
            return response.body()
        } else return null
    }
}