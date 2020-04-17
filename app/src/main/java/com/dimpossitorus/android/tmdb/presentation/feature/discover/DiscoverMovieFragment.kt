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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.domain.entities.Genre
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.presentation.adapter.DiscoverAdapter
import com.dimpossitorus.android.tmdb.presentation.adapter.OnDiscoverItemClicked
import com.dimpossitorus.android.tmdb.presentation.feature.BaseFragment
import com.dimpossitorus.android.tmdb.presentation.feature.movie.MovieDetailFragment
import kotlinx.android.synthetic.main.fragment_discover_movie.*

/**
 * A simple [Fragment] subclass.
 */
class DiscoverMovieFragment : BaseFragment() {

    lateinit var viewModel: DiscoverViewModel
    lateinit var discoverAdapter: DiscoverAdapter
    var genre: Genre? = null
    var onScrollListener: RecyclerView.OnScrollListener

    init {
        onScrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                val firstVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (viewModel.canGetNextPage && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    viewModel.getNextPage()
                    recyclerView.removeOnScrollListener(this)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getApplication().appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DiscoverViewModel::class.java)
        genre = arguments?.get("GENRE") as Genre
        return inflater.inflate(R.layout.fragment_discover_movie, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setViewModelObserver()
        genre?.let {
            viewModel.getDiscoverMovie(it.id)
            genreTitle.text = it.name
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.currentPage = 1
    }

    fun initView() {
        discoverAdapter = DiscoverAdapter(object : OnDiscoverItemClicked {
            override fun onClick(movie: Movie) {
                val bundle = Bundle()
                bundle.putParcelable("MOVIE", movie)
                val fragment = MovieDetailFragment()
                fragment.arguments = bundle
                addFragment(fragment)
            }
        })

        movieList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = discoverAdapter
            addOnScrollListener(onScrollListener)
        }
    }

    fun setViewModelObserver() {
        viewModel.discoverViewState.observe(this, Observer {

            it.discoverResponse?.let { response ->
                Log.d("DISCOVER", response.results.toString())
                discoverAdapter.setData(response)
                movieList.addOnScrollListener(onScrollListener)
            }
            it.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).apply {
                    show()
                }
            }
        })
    }
}
