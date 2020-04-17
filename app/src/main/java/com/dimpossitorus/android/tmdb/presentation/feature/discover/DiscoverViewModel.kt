package com.dimpossitorus.android.tmdb.presentation.feature.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimpossitorus.android.tmdb.domain.usecase.GetDiscoverMovieUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(
    private val getDiscoverMovieUseCase: GetDiscoverMovieUseCase
) : ViewModel() {

    var discoverViewState: MutableLiveData<DiscoverViewState> = MutableLiveData()
    var currentPage = 1
    var genreId = -1

    init {
        discoverViewState.value = DiscoverViewState()
    }

    fun getDiscoverMovie(genre: Int) {
        genreId = genre
        discoverViewState.value = discoverViewState.value?.copy(true)
        viewModelScope.launch {
            try {
                val result = getDiscoverMovieUseCase.execute(genreId, currentPage)
                result?.let {
                    discoverViewState.value =
                        discoverViewState.value?.copy(showLoading = false, discoverResponse = it)
                } ?: run {
                    discoverViewState.value =
                        discoverViewState.value?.copy(showLoading = false, error = "Error")
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                discoverViewState.value =
                    discoverViewState.value?.copy(showLoading = false, error = e.message)
            }
        }
    }

    fun getNextPage() {
        discoverViewState.value = discoverViewState.value?.copy(true)
    }
}