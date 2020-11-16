package com.example.android_2.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.android_2.local.FavoriteMovieProperty
import com.example.android_2.local.FavoriteMovieRepository
import com.example.android_2.network.MovieProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository
): ViewModel(){
    fun addToFavorite(movie:MovieProperty){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFavorite(
                FavoriteMovieProperty(
                    movie.id,
                    movie.original_title,
                    movie.overview,
                    movie.poster_path,
                    movie.vote_average,
                    movie.release_date
                )
            )
        }
    }

    suspend fun checkMovie(id:String) = repository.checkMovie(id)

    fun removeFromFavorite(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromFavorite(id)
        }
    }
}