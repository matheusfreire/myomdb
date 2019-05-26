package com.msf.myomdb.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
class Movie() : Parcelable {

    @SerializedName("imdbID")
    @PrimaryKey
    @NonNull
    var imdbID: String? = null

    @SerializedName("Title")
    var title: String? = null
    
    @SerializedName("Year")
    var year: String? = null
    
    @SerializedName("Rated")
    var rated: String? = null
    
    @SerializedName("Released")
    var released: String? = null
            
    @SerializedName("Runtime")
    var runtime: String? = null
    
    @SerializedName("Genre")
    var genre: String? = null

    @SerializedName("Director")
    var director: String? = null

    @SerializedName("Writer")
    var writer: String? = null

    @SerializedName("Actors")
    var actors: String? = null

    @SerializedName("Plot")
    var plot: String? = null

    @SerializedName("Language")
    var language: String? = null

    @SerializedName("Country")
    var country: String? = null

    @SerializedName("Awards")
    var awards: String? = null

    @SerializedName("Poster")
    var poster: String? = null

    @SerializedName("Metascore")
    var metascore: String? = null

    @SerializedName("imdbRating")
    var imdbRating: String? = null

    @SerializedName("imdbVotes")
    var imdbVotes: String? = null

    @SerializedName("Type")
    var type: String? = null

    @SerializedName("DVD")
    var dVD: String? = null

    @SerializedName("BoxOffice")
    var boxOffice: String? = null

    @SerializedName("Production")
    var production: String? = null

    @SerializedName("Website")
    var website: String? = null

    @SerializedName("Response")
    var response: Boolean = false

    constructor(parcel: Parcel) : this()

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}