package com.msf.myomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.msf.myomdb.bd.MyMovieDatabase
import com.msf.myomdb.model.Movie


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    var liveDataMovies: LiveData<List<Movie>>? = null

    fun getMovies(){
        val database = MyMovieDatabase.getInstance(getApplication())
        liveDataMovies = database.movieDao().loadMovies()
    }
}