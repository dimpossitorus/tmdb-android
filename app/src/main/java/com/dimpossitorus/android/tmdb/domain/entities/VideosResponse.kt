package com.dimpossitorus.android.tmdb.domain.entities

import java.io.Serializable

data class VideosResponse(
    val results: List<Video>
) : Serializable