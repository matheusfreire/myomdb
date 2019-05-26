package com.msf.myomdb


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.msf.myomdb.databinding.FragmentSearchMovieBinding
import com.msf.myomdb.model.Movie
import com.msf.myomdb.view.MovieSearchedRecyclerViewAdapter
import com.msf.myomdb.viewmodel.MoviesViewModel

const val QTDE_CHAR_TO_SEARCH = 3

class SearchMovieFragment : Fragment() {

    private var movieClickedListener: OnMovieClicked? = null

    private lateinit var binding:FragmentSearchMovieBinding
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search_movie, container, false)
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        with(binding.recyclerMovieSearched){
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.searchText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(start != before){
                    setProgress()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length >= QTDE_CHAR_TO_SEARCH){
                    moviesViewModel.searchMovie(s.toString())
                }
            }

        })
        moviesViewModel.mutableLiveDataMovie.observe(this, Observer {
            if(it?.title != null){
                binding.recyclerMovieSearched.adapter = MovieSearchedRecyclerViewAdapter(it, movieClickedListener)
                setVisibilityViews(true)
            } else {
                showMessage()
            }
        })
    }

    private fun setProgress() {
        binding.progressLoading.visibility = View.VISIBLE
        binding.messageInfo.visibility = View.GONE
        binding.recyclerMovieSearched.visibility = View.GONE
    }

    private fun showMessage() {
        binding.progressLoading.visibility = View.GONE
        binding.messageInfo.visibility = View.VISIBLE
        binding.recyclerMovieSearched.visibility = View.GONE
    }

    private fun setVisibilityViews(visibilityOfRecycler: Boolean){
        binding.progressLoading.visibility = if(visibilityOfRecycler) View.GONE else View.VISIBLE
        binding.messageInfo.visibility = if(visibilityOfRecycler) View.GONE else View.VISIBLE
        binding.recyclerMovieSearched.visibility = if(visibilityOfRecycler) View.VISIBLE else View.GONE
    }

    interface OnMovieClicked {
        fun onMovieClicked(item: Movie?)
    }


}
