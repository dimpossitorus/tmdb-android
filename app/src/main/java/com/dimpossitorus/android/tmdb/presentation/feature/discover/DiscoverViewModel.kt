package com.dimpossitorus.android.tmdb.presentation.feature.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse

class DiscoverViewModel: ViewModel() {

    var discoverViewState : MutableLiveData<DiscoverViewState> = MutableLiveData()
    var currentPage = 1

    init {
        discoverViewState.value = DiscoverViewState()
    }

    fun getDiscoverMovie(){
        discoverViewState.value = discoverViewState.value?.copy(true)
    }

    fun getNextPage(){
        discoverViewState.value = discoverViewState.value?.copy(true)
    }
}