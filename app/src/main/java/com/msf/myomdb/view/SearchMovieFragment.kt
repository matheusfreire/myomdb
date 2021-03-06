package com.msf.myomdb.view


import android.content.Context
import android.net.ConnectivityManager
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
import com.msf.myomdb.R
import com.msf.myomdb.databinding.FragmentSearchMovieBinding
import com.msf.myomdb.viewmodel.MoviesViewModel


const val QTDE_CHAR_TO_SEARCH = 3

class SearchMovieFragment : Fragment() {

    private lateinit var binding:FragmentSearchMovieBinding
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie, container, false)
        moviesViewModel = activity?.run {
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        with(binding.recyclerMovieSearched){
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.searchText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(start != before){
                    setProgress()
                }
                if(start < QTDE_CHAR_TO_SEARCH){
                    showMessage()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length >= QTDE_CHAR_TO_SEARCH){
                    if(!isOnline()){
                        showMessaOffline()
                        return
                    }
                    moviesViewModel.searchMovie(s.toString())
                }
            }

        })
        moviesViewModel.mutableLiveDataMovie.observe(this, Observer {
            if(it?.title != null){
                binding.recyclerMovieSearched.adapter = MovieSearchedRecyclerViewAdapter(it,moviesViewModel)
                setVisibilityViews(true)
            } else {
                showMessage()
            }
        })
    }

    private fun showMessaOffline() {
        binding.messageInfo.text = getString(R.string.no_network)
        showMessage()
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
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

}
