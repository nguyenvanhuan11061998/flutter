package com.example.myapplication.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.Utils.Companion.KEY_IMAGE
import com.example.myapplication.model.Movie

class ListMovieHorizontalAdapter() : RecyclerView.Adapter<ListMovieHorizontalAdapter.ViewHolder>() {

    var listMovie: List<Movie>
        get() = listMovie
        set(value) {
            listMovie = value
            notifyDataSetChanged()
        }

    lateinit var context: Context
    lateinit var clickItemListener: ClickItemListener

    constructor(
        listMovie: List<Movie>,
        context: Context,
        clickItemListener: ClickItemListener
    ) : this() {
        this.listMovie = listMovie
        this.context = context
        this.clickItemListener = clickItemListener
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView!!) {
        @BindView(R.id.image_movie)
        var movieImageView: ImageView? = null

        @BindView(R.id.tv_movie_name)
        var movieNameTextView: TextView? = null

        fun binData(item: Movie, clickItemListener : ClickItemListener) {
            ButterKnife.bind(this, itemView)
            val urlImage: String = KEY_IMAGE + item.posterPath
            Glide.with(movieImageView!!)
                .load(urlImage)
                .into(movieImageView!!)
            movieNameTextView?.setText(item.title)
            itemView.setOnClickListener(View.OnClickListener {
                if (clickItemListener != null) {
                    clickItemListener.onClickMovie(adapterPosition)
                }
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = listMovie.get(position)
        holder.binData(item, clickItemListener)
    }

    interface ClickItemListener {
        fun onClickMovie(position: Int)
    }
}