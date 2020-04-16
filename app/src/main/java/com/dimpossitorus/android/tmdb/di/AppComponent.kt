package com.dimpossitorus.android.tmdb.di

import com.dimpossitorus.android.tmdb.data.di.NetworkModule
import com.dimpossitorus.android.tmdb.presentation.BaseApplication
import com.dimpossitorus.android.tmdb.presentation.feature.genre.GenreListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        NetworkModule::class]
)
interface AppComponent {
    fun inject(application: BaseApplication)
    fun inject(genreFragment: GenreListFragment)
}