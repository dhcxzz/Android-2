package com.example.android_2.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Insert fun addToFavorite(favoriteMovieProperty: FavoriteMovieProperty)

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovie(): LiveData<List<FavoriteMovieProperty>>

    @Query("SELECT count(*) FROM favorite_movie WHERE favorite_movie.id_movie = :id")
    suspend fun  checkMovie(id:String): Int

    @Query("DELETE FROM favorite_movie WHERE favorite_movie.id_movie = :id")
    suspend fun removeFromFavorite(id: String):Int
}