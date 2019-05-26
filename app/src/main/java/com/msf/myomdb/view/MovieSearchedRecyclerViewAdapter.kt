package com.msf.myomdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msf.myomdb.R
import com.msf.myomdb.SearchMovieFragment
import com.msf.myomdb.model.Movie
import kotlinx.android.synthetic.main.movie_searched_content.view.*

class MovieSearchedRecyclerViewAdapter(val movie: Movie,val movieClickedListener: SearchMovieFragment.OnMovieClicked?) : RecyclerView.Adapter<MovieSearchedRecyclerViewAdapter.MovieSearchedHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val movie = v.tag as Movie
            movieClickedListener?.onMovieClicked(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchedHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_searched_content, parent, false)
        return MovieSearchedHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: MovieSearchedHolder, position: Int) {
        holder.bind(movie)
        with(holder.mView) {
            tag = movie
            setOnClickListener(mOnClickListener)
        }
    }

    inner class MovieSearchedHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(movie:Movie){
            itemView.title_movie_searched.text = "${movie.title} (${movie.year})"
        }
    }

}
