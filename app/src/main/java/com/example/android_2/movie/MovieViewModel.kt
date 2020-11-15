package com.example.android_2.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.android_2.network.MovieRepository

class MovieViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel() {
    val movies = repository.getPopular()
}