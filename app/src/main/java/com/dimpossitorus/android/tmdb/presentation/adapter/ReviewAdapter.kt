package com.dimpossitorus.android.tmdb.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimpossitorus.android.tmdb.R
import com.dimpossitorus.android.tmdb.domain.entities.Review

class ReviewAdapter :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    val review = ArrayList<Review>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return review.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(review.get(position))
    }

    fun setData(reviews: List<Review>) {
        review.addAll(reviews)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author: TextView
        var reviewContent: TextView

        init {
            author = itemView.findViewById<TextView>(R.id.author)
            reviewContent = itemView.findViewById<TextView>(R.id.review)
        }

        fun bind(review: Review) {
            author.text = review.author
            reviewContent.text = review.content
        }
    }
}
