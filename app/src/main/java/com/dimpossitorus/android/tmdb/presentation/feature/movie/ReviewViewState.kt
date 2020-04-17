package com.dimpossitorus.android.tmdb.presentation.feature.movie

import com.dimpossitorus.android.tmdb.domain.entities.Review

data class ReviewViewState(
    var showLoading: Boolean? = true,
    var review: List<Review>? = null,
    var error: String? = null
)