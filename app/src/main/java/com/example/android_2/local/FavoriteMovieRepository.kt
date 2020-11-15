package com.example.android_2.local

import javax.inject.Inject

class FavoriteMovieRepository @Inject constructor(
        private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun addToFavorite(favoriteMovieProperty: FavoriteMovieProperty) = favoriteMovieDao.addToFavorite(favoriteMovieProperty)
    fun getFavoriteMovies() = favoriteMovieDao.getFavoriteMovie()
    suspend fun checkMovie(id: String) = favoriteMovieDao.checkMovie(id)
    suspend fun removeFromFavorite(id: String) {
        favoriteMovieDao.removeFromFavorite(id)
    }
}