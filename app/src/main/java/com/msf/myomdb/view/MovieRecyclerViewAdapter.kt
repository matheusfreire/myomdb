package com.msf.myomdb.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.msf.myomdb.R
import com.msf.myomdb.model.Movie
import com.msf.myomdb.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_content.view.*

class MovieRecyclerViewAdapter(private val mValues: List<Movie>,private val moviesViewModel: MoviesViewModel): RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val movie = v.tag as Movie
            moviesViewModel.movieSelected = movie
            v.findNavController().navigate(R.id.action_moviesFragment_to_movieFragment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = mValues[position]
        holder.bind(movie)
        with(holder.mView) {
            tag = movie
            setOnClickListener(mOnClickListener)
            Picasso.get().load(movie.poster).into(thumbnail)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(movie:Movie){
            with(itemView) {
                title.text = movie.title
                rating.text = movie.imdbRating
            }
        }
    }
}
