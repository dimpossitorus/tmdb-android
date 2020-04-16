package com.dimpossitorus.android.tmdb.presentation.feature.genre


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.presentation.BaseApplication
import com.dimpossitorus.android.tmdb.presentation.feature.BaseFragment
import kotlinx.android.synthetic.main.fragment_genre_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class GenreListFragment : BaseFragment() {

    lateinit var viewModel: GenreViewModel

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
        setViewModelObserver()
        viewModel.getGenres()
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
            }
            it.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).apply {
                    show()
                }
            }
        })
    }
}
