package com.example.android_2.movie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.android_2.network.MovieRepository

class MovieViewModel @ViewModelInject constructor(private val repository: MovieRepository,
@Assisted state: SavedStateHandle) : ViewModel() {

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }
    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val movies = currentQuery.switchMap { query ->
        if (!query.isEmpty()){
            repository.getSearchMovies(query).cachedIn(viewModelScope)
        }else{
            repository.getPopular().cachedIn(viewModelScope)
        }
    }

    fun searchMovies(query:String){
        currentQuery.value = query
    }
}