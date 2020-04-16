package com.dimpossitorus.android.tmdb.domain.entities

import com.google.gson.annotations.SerializedName

data class Video(
    val id: String,
    val key: String,
    @SerializedName("site") val source: String,
    val type: String
)