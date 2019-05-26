package com.msf.myomdb.bd


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msf.myomdb.dao.MovieDao
import com.msf.myomdb.model.Movie
import com.msf.myomdb.util.Constants

var dbInstance: MyMovieDatabase? = null

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MyMovieDatabase : RoomDatabase() {

    companion object{
        fun getInstance(context: Context): MyMovieDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MyMovieDatabase::class.java, Constants.DATABASE_NAME)
                        .build()
            }
            return dbInstance!!
        }

    }

    abstract fun movieDao() : MovieDao
}