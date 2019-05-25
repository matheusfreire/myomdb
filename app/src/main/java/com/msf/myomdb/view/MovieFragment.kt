package com.msf.myomdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.msf.myomdb.R
import com.msf.myomdb.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val dataBinding:FragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie,container, false)
        return dataBinding.root
    }
}
