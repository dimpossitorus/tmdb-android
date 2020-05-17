package com.dimpossitorus.android.tmdb.feature.discover

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

    var canGetNextPage = true

    init {
        discoverViewState.value = DiscoverViewState()
    }

    fun getDiscoverMovie(genre: Int) {
        genreId = genre
        discoverViewState.value = discoverViewState.value?.copy(true, null, null)
        viewModelScope.launch {
            try {
                val result = getDiscoverMovieUseCase.execute(genreId, currentPage)
                result?.let {
                    if (result.page == result.totalPages) {
                        canGetNextPage = false
                    }
                    discoverViewState.value =
                        discoverViewState.value?.copy(false, it, null)
                } ?: run {
                    canGetNextPage = false
                    discoverViewState.value =
                        discoverViewState.value?.copy(false, null, "Error")
                }
            } catch (e: Throwable) {
                canGetNextPage = false
                e.printStackTrace()
                discoverViewState.value =
                    discoverViewState.value?.copy(false, null, e.message)
            }
        }
    }

    fun getNextPage() {
        currentPage++
        getDiscoverMovie(genreId)
    }
}