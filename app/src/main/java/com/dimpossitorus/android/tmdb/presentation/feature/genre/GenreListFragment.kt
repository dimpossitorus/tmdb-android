package com.dimpossitorus.android.tmdb.presentation.feature.genre


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.domain.entities.Genre
import com.dimpossitorus.android.tmdb.presentation.adapter.GenresAdapter
import com.dimpossitorus.android.tmdb.presentation.adapter.OnGenreItemClicked
import com.dimpossitorus.android.tmdb.presentation.feature.BaseFragment
import com.dimpossitorus.android.tmdb.presentation.feature.discover.DiscoverMovieFragment
import kotlinx.android.synthetic.main.fragment_genre_list.*

/**
 * Created by Dimpos Sitorus
 */
class GenreListFragment : BaseFragment() {

    lateinit var viewModel: GenreViewModel
    lateinit var genresAdapter: GenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getApplication().appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GenreViewModel::class.java)
        return inflater.inflate(R.layout.fragment_genre_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        initView()
        setViewModelObserver()
        viewModel.getGenres()
    }

    fun initView() {
        genresAdapter = GenresAdapter(object : OnGenreItemClicked {
            override fun onClick(genre: Genre) {
                val bundle = Bundle()
                bundle.putParcelable("GENRE", genre)
                val fragment = DiscoverMovieFragment()
                fragment.arguments = bundle
                replaceFragment(fragment)
            }
        })
        genreList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = genresAdapter
        }

    }

    fun setViewModelObserver() {
        viewModel.genreState.observe(this, Observer {
            if (it.showLoading) {
                loading.visibility = VISIBLE
            } else {
                loading.visibility = GONE
            }
            it.genres?.let {
                Log.d("GENRE", it.genres.toString())
                genresAdapter.setData(it)
            }
            it.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).apply {
                    show()
                }
            }
        })
    }
}
