package com.dimpossitorus.android.tmdb.feature.movie

import com.dimpossitorus.android.tmdb.domain.entities.Movie

data class MovieDetailViewState(
    var showLoading: Boolean? = true,
    var movie: Movie? = null,
    var error: String? = null
)