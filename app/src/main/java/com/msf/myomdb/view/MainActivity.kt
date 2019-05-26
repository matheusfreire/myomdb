package com.msf.myomdb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.msf.myomdb.R
import com.msf.myomdb.SearchMovieFragment
import com.msf.myomdb.databinding.ActivityMainBinding
import com.msf.myomdb.model.Movie

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }
}
