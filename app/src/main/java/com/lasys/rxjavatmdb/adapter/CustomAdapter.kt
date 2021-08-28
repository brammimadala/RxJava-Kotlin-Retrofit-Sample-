package com.lasys.rxjavatmdb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lasys.rxjavatmdb.R
import com.lasys.rxjavatmdb.model.Movie


class CustomAdapter(private val mList: List<Movie>, private val context: Context) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        val posterPath = "https://image.tmdb.org/t/p/w500" + item.posterPath

        Glide.with(context)
            .load(posterPath)
            .placeholder(R.drawable.loading)
            .into(holder.imageView)

        holder.tvTitle.text = item.title
        holder.tvRating.text = item.voteAverage.toString()

    }


    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivMovie)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }
}
