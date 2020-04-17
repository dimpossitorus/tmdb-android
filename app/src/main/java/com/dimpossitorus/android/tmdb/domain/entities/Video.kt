package com.dimpossitorus.android.tmdb.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    val id: String,
    val key: String,
    @SerializedName("site") val source: String,
    val type: String
) : Parcelable