package com.msf.myomdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
class Movie {

    @SerializedName("imdbID")
    @PrimaryKey
    val imdbID: String? = null

    @SerializedName("title")
    val title: String? = null
    
    @SerializedName("year")
    val year: Int? = null
    
    @SerializedName("rated")
    val rated: String? = null
    
    @SerializedName("released")
    val released: String? = null
            
    @SerializedName("runtime")
    val runtime: String? = null
    
    @SerializedName("genre")
    val genre: String? = null

    @SerializedName("director")
    val director: String? = null

    @SerializedName("writer")
    val writer: String? = null

    @SerializedName("actors")
    val actors: String? = null

    @SerializedName("plot")
    val plot: String? = null

    @SerializedName("language")
    val language: String? = null

    @SerializedName("country")
    val country: String? = null

    @SerializedName("awards")
    val awards: String? = null

    @SerializedName("poster")
    val poster: String? = null

    @SerializedName("metascore")
    val metascore: Int? = null

    @SerializedName("imdbRating")
    val imdbRating: Double? = null

    @SerializedName("imdbVotes")
    val imdbVotes: String? = null

    @SerializedName("type")
    val type: String? = null

    @SerializedName("dVD")
    val dVD: String? = null

    @SerializedName("boxOffice")
    val boxOffice: String? = null

    @SerializedName("production")
    val production: String? = null

    @SerializedName("website")
    val website: String? = null

    @SerializedName("response")
    val response: Boolean = false
}