package com.dimpossitorus.android.tmdb.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.domain.entities.DiscoverResponse
import com.dimpossitorus.android.tmdb.domain.entities.Movie

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

class DiscoverAdapter(val onItemClickListener: OnDiscoverItemClicked) :
    RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    val movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies.get(position), onItemClickListener)
    }

    fun setData(genreResponse: DiscoverResponse) {
        movies.addAll(genreResponse.results)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitle: TextView
        var poster: ImageView

        init {
            poster = itemView.findViewById(R.id.poster)
            movieTitle = itemView.findViewById(R.id.movieTitle)
        }

        fun bind(movie: Movie, onItemClickListener: OnDiscoverItemClicked) {
            movieTitle.text = movie.title
            Glide
                .with(itemView.context)
                .load(IMAGE_BASE_URL + movie.posterUrl)
                .into(poster)

            itemView.setOnClickListener {
                onItemClickListener.onClick(movie)
            }
        }
    }
}

interface OnDiscoverItemClicked {
    fun onClick(movie: Movie)
}