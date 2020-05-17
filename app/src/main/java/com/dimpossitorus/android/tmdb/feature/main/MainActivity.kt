package com.dimpossitorus.android.tmdb.feature.main

import android.os.Bundle
import com.dimpossitorus.android.tmdb.BaseActivity
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.feature.genre.GenreListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    override fun onResume() {
        super.onResume()
        replaceFragmentWithoutBackstack(GenreListFragment())
    }

}
