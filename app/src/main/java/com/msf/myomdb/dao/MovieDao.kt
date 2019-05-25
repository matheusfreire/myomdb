package com.msf.myomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.msf.myomdb.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun loadMovies(): LiveData<List<Movie>>

    @Insert
    fun insertFavMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE imdbID = :imdbID")
    fun loadMovieById(imdbID: String): LiveData<Movie>
}