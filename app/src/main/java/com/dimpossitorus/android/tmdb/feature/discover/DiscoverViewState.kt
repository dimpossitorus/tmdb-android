package com.dimpossitorus.android.tmdb.feature.discover

import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse

data class DiscoverViewState(
    var showLoading: Boolean = true,
    var discoverResponse: DiscoverResponse? = null,
    var error: String? = null
)