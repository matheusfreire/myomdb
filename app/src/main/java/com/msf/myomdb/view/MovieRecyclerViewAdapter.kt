package com.msf.myomdb.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msf.myomdb.R


import com.msf.myomdb.view.MoviesListFragment.OnListFragmentInteractionListener
import com.msf.myomdb.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_content.view.*
import kotlinx.android.synthetic.main.movie_searched_content.view.*

class MovieRecyclerViewAdapter(private val mValues: List<Movie>,
                               private val mListener: OnListFragmentInteractionListener?): RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val movie = v.tag as Movie
            mListener?.onListFragmentInteraction(movie)
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
//                promotion_item_title.text = item.title
//                promotion_item_subtitle.text = textForDiscount(item.discount)
//                promotion_item_description.text = item.description
//                promotion_item_special_label.visibility = if (item.isSpecial) View.VISIBLE else View.GONE

            }

        }
    }
}
