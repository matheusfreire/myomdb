package com.msf.myomdb.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.msf.myomdb.R
import com.msf.myomdb.databinding.FragmentMovieListBinding
import com.msf.myomdb.model.Movie
import com.msf.myomdb.util.ItemDecoration
import com.msf.myomdb.viewmodel.MoviesViewModel
import androidx.lifecycle.ViewModelProviders


class MoviesListFragment : Fragment() {

    private var columnCount = 2

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var binding:FragmentMovieListBinding
    private lateinit var moviesViewModel:MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_list, container, false)
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        with(binding.recyclerViewMovies) {
            layoutManager = GridLayoutManager(context, columnCount)
            addItemDecoration(ItemDecoration(columnCount, dpToPx(10)))
        }
        setVisibilityViews(false)
        moviesViewModel.getMovies()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.liveDataMovies?.observe(this, Observer {
            if (it == null){
                showMessage()
            } else {
                binding.recyclerViewMovies.adapter = MovieRecyclerViewAdapter(it, listener)
                setVisibilityViews(true)
            }
        })
    }

    private fun showMessage() {
        binding.progressLoading.visibility = View.GONE
        binding.errorMessage.visibility = View.VISIBLE
    }

    private fun setVisibilityViews(visibilityOfRecycler: Boolean){
        binding.progressLoading.visibility = if(visibilityOfRecycler) View.GONE else View.VISIBLE
        binding.recyclerViewMovies.visibility = if(visibilityOfRecycler) View.VISIBLE else View.GONE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Movie?)
    }

}
