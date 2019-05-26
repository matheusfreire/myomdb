package com.msf.myomdb.repository

import com.msf.myomdb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("/")
    fun searchByTitle(@Query("apikey")apiKey: String, @Query("t") search: String) : Call<Movie>
}