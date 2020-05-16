package com.dimpossitorus.android.tmdb.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("original_title") var originalTitle: String,
    val overview: String,
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genres") val genres: List<Genre>,
    val homepage: String,
    @SerializedName("release_date") val releaseDate: Date,
    val runtime: Int,
    @SerializedName("poster_path") val posterUrl: String,
    val reviews: ReviewResponse,
    val videos: VideosResponse
) : Parcelable {
    fun getRuntimeFriendly(): String {
        val stringBuilder = StringBuilder()
        if (runtime / 60 > 0) {
            stringBuilder.append(runtime / 60)
            stringBuilder.append("h ")
        }
        stringBuilder.append(runtime % 60)
        stringBuilder.append("m")
        return stringBuilder.toString()
    }
}
