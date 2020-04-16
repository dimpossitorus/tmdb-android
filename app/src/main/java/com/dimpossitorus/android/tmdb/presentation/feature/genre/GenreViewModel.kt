package com.dimpossitorus.android.tmdb.presentation.feature.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimpossitorus.android.tmdb.domain.usecase.GetGenresUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreViewModel @Inject constructor(private val getGenresUseCase: GetGenresUseCase) :
    ViewModel() {

    var genreState: MutableLiveData<GenreViewState> = MutableLiveData()

    init {
        genreState.value = GenreViewState()
    }

    fun getGenres() {
        genreState.value = genreState.value?.copy(true, null, null)
        viewModelScope.launch {
            try {
                val result = getGenresUseCase.execute()
                result?.let {
                    genreState.value = genreState.value?.copy(false, it, null)
                } ?: run {
                    genreState.value = genreState.value?.copy(false, null, "Error")
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                genreState.value = genreState.value?.copy(false, null, "Oops something went wrong")
            }
        }
    }
}