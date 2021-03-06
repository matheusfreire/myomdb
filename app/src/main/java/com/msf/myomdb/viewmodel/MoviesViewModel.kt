package com.msf.myomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msf.myomdb.BuildConfig
import com.msf.myomdb.bd.MyMovieDatabase
import com.msf.myomdb.model.Movie
import com.msf.myomdb.repository.RetrofitInstance
import com.msf.myomdb.util.AppExecutor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    var liveDataMovies: LiveData<List<Movie>>? = null
    val mutableLiveDataMovie: MutableLiveData<Movie> by lazy {
        MutableLiveData<Movie>()
    }

    var liveDataMovie: LiveData<Movie>? = null

    lateinit var movieSelected: Movie

    fun getMovies() {
        val database = MyMovieDatabase.getInstance(getApplication())
        liveDataMovies = database.movieDao().loadMovies()
    }

    fun searchMovie(search: String) {
        val retrofitInstance = RetrofitInstance()
        val service = retrofitInstance.movieService()
        val call = service.searchByTitle(BuildConfig.OMDB_KEY, search)
        call.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                mutableLiveDataMovie.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                mutableLiveDataMovie.postValue(null)
            }
        })
    }

    fun saveMovie() {
        val database = MyMovieDatabase.getInstance(getApplication())
        AppExecutor.sInstance.getDbIo().execute {
            database.movieDao().insertFavMovie(movieSelected)
        }
    }

    fun deleteMovie() {
        val database = MyMovieDatabase.getInstance(getApplication())
        AppExecutor.sInstance.getDbIo().execute { database.movieDao().deleteMovie(movieSelected) }
    }

    fun isMovieSaved(){
        val database = MyMovieDatabase.getInstance(getApplication())
        liveDataMovie = database.movieDao().loadMovieById(movieSelected.imdbID!!)
    }
}

