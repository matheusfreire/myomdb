package com.msf.myomdb.bd


import android.content.Context
import android.graphics.Movie
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msf.myomdb.dummy.DummyContent
import com.msf.myomdb.util.Constants

val dbInstance: MyMovieDatabase? = null

//@Database(entities = { DummyContent.DummyItem}, version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MyMovieDatabase : RoomDatabase() {


    fun getInstance(context: Context): MyMovieDatabase {
        if (dbInstance == null) {
            Room.databaseBuilder(context.getApplicationContext(),
                MyMovieDatabase::class.java, Constants.DATABASE_NAME)
                .build()
        }
        return dbInstance!!
    }

}