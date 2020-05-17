package com.dimpossitorus.android.tmdb.feature.movie


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.domain.entities.Movie
import com.dimpossitorus.android.tmdb.domain.entities.VideosResponse
import com.dimpossitorus.android.tmdb.adapter.ReviewAdapter
import com.dimpossitorus.android.tmdb.BaseFragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : BaseFragment() {

    lateinit var viewModel: MovieDetailViewModel
    lateinit var reviewAdapter: ReviewAdapter
    var movie: Movie? = null
    var youtubePlayer: YouTubePlayerFragment? = null

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
                    viewModel.getNextPageReview()
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
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        movie = arguments?.get("MOVIE") as Movie
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        youtubePlayer = YouTubePlayerFragment.newInstance()
//        childFragmentManager.beginTransaction().add(R.id.trailer, youtubePlayer)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie?.let {
            viewModel.getMovieDetail(it.id)
            viewModel.getReview(it.id)
        }
        initView()
        setViewModelObserver()
    }

    fun initView() {
        reviewAdapter = ReviewAdapter()
        reviewList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
            addOnScrollListener(onScrollListener)
        }
    }

    fun setViewModelObserver() {
        viewModel.movieDetailViewState.observe(this, Observer {
            it.movie?.let { movie ->
                Log.d("MOVIE_DETAIL", movie.toString())
                setView(movie)
            }
            it.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).apply {
                    show()
                }
            }
        })

        viewModel.reviewViewState.observe(this, Observer {
            it.review?.let { reviews ->
                reviewAdapter.setData(reviews)
            }
            it.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).apply {
                    show()
                }
            }
        })

    }

    fun setView(movie: Movie) {
        movieTitle.text = movie.title
        overview.text = movie.overview
        val localDate = SimpleDateFormat("dd MMMM yyyy").format(movie.releaseDate)
        description.text = "${movie.getRuntimeFriendly()} - ${localDate}"
        val stringBuilder = StringBuilder()
        for (i in 0 until movie.genres.size - 1) {
            stringBuilder.append(movie.genres.get(i).name)
            stringBuilder.append(", ")
        }
        stringBuilder.append(movie.genres.get(movie.genres.size - 1).name)
        genres.text = "Genre : ${stringBuilder.toString()}"

        setTrailer(movie.videos)
    }

    fun setTrailer(video: VideosResponse) {
        var trailerId = ""
        for (videoItem in video.results) {
            if (videoItem.source.equals("youtube", true)
                && videoItem.source.equals("trailer", true)
            ) {
                trailerId = videoItem.key
                break
            }
        }
        youtubePlayer?.initialize(
            "AIzaSyASm6H_QZzjOQfGjATSHTfzc7dPencyvbw",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    wasRestored: Boolean
                ) {
                    if (!wasRestored) {
                        player?.loadVideo(trailerId)
                        player?.play()
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {

                }
            })
    }
}
