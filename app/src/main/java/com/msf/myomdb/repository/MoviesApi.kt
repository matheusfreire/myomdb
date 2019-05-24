package com.msf.myomdb.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


interface MoviesApi {

    @GET("t={search}")
    fun searchByTitle(@Path("search") search: String ): Call<Objects>
}