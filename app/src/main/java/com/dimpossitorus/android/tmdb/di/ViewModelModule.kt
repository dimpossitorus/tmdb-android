package com.dimpossitorus.android.tmdb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimpossitorus.android.tmdb.presentation.feature.discover.DiscoverViewModel
import com.dimpossitorus.android.tmdb.presentation.feature.genre.GenreViewModel
import com.dimpossitorus.android.tmdb.presentation.feature.movie.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GenreViewModel::class)
    abstract fun bindGenreViewModel(genreViewModel: GenreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(genreViewModel: DiscoverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel
}