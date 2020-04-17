package com.dimpossitorus.android.tmdb.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.domain.entities.Genre
import com.dimpossitorus.android.tmdb.domain.entities.GenreResponse

class GenresAdapter(val onItemClickListener: OnGenreItemClicked) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    val genres = ArrayList<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres.get(position), onItemClickListener)
    }

    fun setData(genreResponse: GenreResponse) {
        genres.clear()
        genres.addAll(genreResponse.genres)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var genreName: TextView

        init {
            genreName = itemView.findViewById<TextView>(R.id.genreName)
        }

        fun bind(genre: Genre, onItemClickListener: OnGenreItemClicked) {
            genreName.text = genre.name
            itemView.setOnClickListener {
                onItemClickListener.onClick(genre)
            }
        }
    }
}

interface OnGenreItemClicked {
    fun onClick(genre: Genre)
}