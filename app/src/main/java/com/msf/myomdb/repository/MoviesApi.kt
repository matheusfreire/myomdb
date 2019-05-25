package com.msf.myomdb.repository

import com.msf.myomdb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MoviesApi {

    @GET("t={search}")
    fun searchByTitle(@Path("search") search: String ): Call<Movie>
}