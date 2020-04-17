package com.dimpossitorus.android.tmdb.presentation.feature.discover


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.presentation.feature.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class DiscoverMovieFragment : BaseFragment() {

    lateinit var viewModel: DiscoverViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getApplication().appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DiscoverViewModel::class.java)
        return inflater.inflate(R.layout.fragment_discover_movie, container, false)
    }


    override fun onResume() {
        super.onResume()
        initView()
        setViewModelObserver()
    }

    fun initView() {

    }

    fun setViewModelObserver() {
        viewModel.discoverViewState.observe(this, Observer {

            it.discoverResponse?.let { response ->
                Log.d("GENRE", response.results.toString())
            }
            it.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).apply {
                    show()
                }
            }
        })
    }
}
