package com.dimpossitorus.android.tmdb.domain.entities

import com.dimpossitorus.android.tmdb.domain.entities.Review
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val page: Int,
    val results: List<Review>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)