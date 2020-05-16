package com.dimpossitorus.android.tmdb.data.api

import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.entities.ReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface TmdbApi {

    @GET("discover/movie")
    suspend fun getMovieDiscover(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @QueryMap additionalQuery: Map<String, String>?
    ): Response<DiscoverResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") additionalRequest: String?
    ): Response<Movie>

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): Response<GenreResponse>

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReview(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<ReviewResponse>

    fun foo(){
        val url = ""
        val custID = ""
        val appID = ""
        "$url/$custID+$appID/"
    }
}