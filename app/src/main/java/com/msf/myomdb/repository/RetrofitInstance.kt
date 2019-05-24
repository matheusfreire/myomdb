package com.msf.myomdb.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.msf.myomdb.BuildConfig
import com.msf.myomdb.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val retrofit: Retrofit by lazy {
         Retrofit.Builder().baseUrl(BuildConfig.MOVIE_ROOT_URI)
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .build()
    }

    fun getRetrofitInstance(): Retrofit {
        return retrofit
    }

    private fun createGson(): Gson {
        return GsonBuilder().setDateFormat(Constants.PATTERN_DATE_CONVERT).create()
    }
}