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
import androidx.navigation.findNavController


class MoviesListFragment : Fragment() {

    private var columnCount = 2

    private lateinit var binding:FragmentMovieListBinding
    private lateinit var moviesViewModel:MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_list, container, false)
        moviesViewModel = activity?.run {
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        with(binding.recyclerViewMovies) {
            layoutManager = GridLayoutManager(context, columnCount)
            addItemDecoration(ItemDecoration(columnCount, dpToPx(10)))
        }
        setVisibilityViews(false)
        moviesViewModel.getMovies()
        binding.searchMovie.setOnClickListener {
            it.findNavController().navigate(R.id.action_moviesFragment_to_searchMovieFragment)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.liveDataMovies?.observe(this, Observer {
            if (it == null || it.isEmpty()){
                showMessage()
            } else {
                binding.recyclerViewMovies.adapter = MovieRecyclerViewAdapter(it, moviesViewModel)
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

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

}
