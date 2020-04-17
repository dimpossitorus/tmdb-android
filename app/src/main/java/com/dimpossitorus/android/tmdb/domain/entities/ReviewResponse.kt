package com.dimpossitorus.android.tmdb.domain.entities

import android.os.Parcelable
import com.dimpossitorus.android.tmdb.domain.entities.Review
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class ReviewResponse(
    val page: Int,
    val results: List<Review>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) : Serializable