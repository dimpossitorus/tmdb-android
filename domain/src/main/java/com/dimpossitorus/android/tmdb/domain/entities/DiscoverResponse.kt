package com.dimpossitorus.android.tmdb.domain.entities

import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
    val page: Int,
    @SerializedName("total_results") val totalResult: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<Movie>
) {
}