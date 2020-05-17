package com.dimpossitorus.android.tmdb.di

import com.dimpossitorus.android.tmdb.data.di.DataModule
import com.dimpossitorus.android.tmdb.data.di.NetworkModule
import com.dimpossitorus.android.tmdb.BaseApplication
import com.dimpossitorus.android.tmdb.feature.discover.DiscoverMovieFragment
import com.dimpossitorus.android.tmdb.feature.genre.GenreListFragment
import com.dimpossitorus.android.tmdb.feature.movie.MovieDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        DataModule::class,
        NetworkModule::class]
)
interface AppComponent {
    fun inject(application: BaseApplication)
    fun inject(genreFragment: GenreListFragment)
    fun inject(discoverFragment: DiscoverMovieFragment)
    fun inject(movieDetailFragment: MovieDetailFragment)
}