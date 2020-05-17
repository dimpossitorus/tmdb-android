package com.dimpossitorus.android.tmdb.feature.genre

import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse

data class GenreViewState(
    var showLoading: Boolean = true,
    var genres: GenreResponse? = null,
    var error: String? = null
)