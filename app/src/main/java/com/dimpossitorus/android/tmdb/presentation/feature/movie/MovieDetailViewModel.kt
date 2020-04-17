package com.dimpossitorus.android.tmdb.presentation.feature.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimpossitorus.android.tmdb.domain.usecase.GetMovieDetailUseCase
import com.dimpossitorus.android.tmdb.domain.usecase.GetMovieReviewUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieReviewUseCase: GetMovieReviewUseCase
) : ViewModel() {

    var movieDetailViewState: MutableLiveData<MovieDetailViewState> = MutableLiveData()
    var reviewViewState: MutableLiveData<ReviewViewState> = MutableLiveData()

    val additionalInfo: ArrayList<String> = arrayListOf()

    var reviewPage = 1;

    var canGetNextPage = true

    var movieId = -1

    init {
        movieDetailViewState.value = MovieDetailViewState()
        reviewViewState.value = ReviewViewState()
        additionalInfo.add("videos")
    }

    fun getMovieDetail(movieId: Int) {
        movieDetailViewState.value = movieDetailViewState.value?.copy(true)
        viewModelScope.launch {
            try {
                val result = getMovieDetailUseCase.execute(movieId, additionalInfo)
                result?.let {
                    movieDetailViewState.value = movieDetailViewState.value?.copy(false, it)
                } ?: run {
                    movieDetailViewState.value = movieDetailViewState.value?.copy(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                movieDetailViewState.value =
                    movieDetailViewState.value?.copy(showLoading = false, error = e.message)
            }
        }
    }

    fun getReview(movieId: Int) {
        this.movieId = movieId
        reviewViewState.value = reviewViewState.value?.copy(true)
        viewModelScope.launch {
            try {
                val result = getMovieReviewUseCase.execute(movieId, reviewPage)
                result?.let {
                    if (it.page == it.totalPages) {
                        canGetNextPage = false
                    }
                    reviewViewState.value = reviewViewState.value?.copy(false, it.results)
                } ?: run {
                    reviewViewState.value = reviewViewState.value?.copy(false, null)
                    canGetNextPage = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                reviewViewState.value = reviewViewState.value?.copy(false, null)
                canGetNextPage = false
            }
        }
    }

    fun getNextPageReview() {
        reviewPage++
        getReview(movieId)
    }
}