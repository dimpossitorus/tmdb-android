package com.dimpossitorus.android.tmdb.presentation.feature

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dimpossitorus.android.tmdb.presentation.BaseActivity
import com.dimpossitorus.android.tmdb.presentation.BaseApplication
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected fun getApplication(): BaseApplication {
        return (activity?.application as BaseApplication)
    }

    fun replaceFragment(fragment: Fragment) {
        (activity as BaseActivity).replaceFragment(fragment)
    }
    fun addFragment(fragment: Fragment) {
        (activity as BaseActivity).replaceFragment(fragment)
    }
}