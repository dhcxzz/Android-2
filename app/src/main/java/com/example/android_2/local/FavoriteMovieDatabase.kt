package com.example.android_2.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [FavoriteMovieProperty::class],
        version = 1
)
abstract class FavoriteMovieDatabase : RoomDatabase(){
    abstract fun getFavoriteMovieDao():FavoriteMovieDao
}