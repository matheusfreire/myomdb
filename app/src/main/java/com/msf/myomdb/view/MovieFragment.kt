package com.msf.myomdb.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.msf.myomdb.R
import com.msf.myomdb.databinding.FragmentMovieBinding
import com.msf.myomdb.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.widget.RatingBar
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.msf.myomdb.util.Constants
import com.msf.myomdb.model.Movie


class MovieFragment : Fragment() {

    private var isSaved: Boolean = false
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var menuItem:MenuItem
    private lateinit var dataBinding: FragmentMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie,container, false)
        moviesViewModel = activity?.run {
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        if(savedInstanceState == null){
            isMovieSaved()
        }
        dataBinding.apply {
            movie = moviesViewModel.movieSelected
            Picasso.get().load(moviesViewModel.movieSelected.poster).into(posterView)
            ratebar.rating = getVoteAverage(moviesViewModel.movieSelected)
        }
        animateRatingbar(dataBinding.ratebar)
        return dataBinding.root
    }

    private fun isMovieSaved() {
        moviesViewModel.isMovieSaved()
        moviesViewModel.liveDataMovie!!.observe(this, Observer {
            isSaved = it != null
            changeItem(isSaved)
        })
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun animateRatingbar(mRatebar: RatingBar) {
        val current = mRatebar.rating
        val anim = ObjectAnimator.ofFloat(mRatebar, "mRatebar", 0f, current)
        anim.duration = Constants.DURATION_ANIMATION
        anim.start()
    }

    private fun getVoteAverage(movieResult: Movie): Float {
        try{
            return movieResult.imdbRating!!.toFloat() / 2
        } catch (e: Exception){
            return 0f
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_movie, menu)
        if(isSaved){
            menu!!.findItem(R.id.action_save_movie).icon = context!!.getDrawable(R.drawable.ic_delete)
        }
        menuItem = menu!!.findItem(R.id.action_save_movie)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.action_save_movie -> saveOrDeleteMovie()
        }
        return true
    }

    private fun changeItem(saved:Boolean){
        activity!!.runOnUiThread{
            if(saved){
                menuItem.icon = context!!.getDrawable(R.drawable.ic_delete)
            } else {
                menuItem.icon = context!!.getDrawable(R.drawable.ic_done)
            }
        }
    }

    private fun saveOrDeleteMovie() {
        if(isSaved){
            moviesViewModel.deleteMovie()
            changeItem(false)
            showSnackBar(R.string.movie_deleted_successfully)
        } else {
            moviesViewModel.saveMovie()
            changeItem(true)
            showSnackBar(R.string.movie_saved_successfully)
        }
    }
    private fun showSnackBar(idMessage: Int){
        Snackbar.make(dataBinding.root, getString(idMessage),
                Snackbar.LENGTH_LONG).show()
    }
}
