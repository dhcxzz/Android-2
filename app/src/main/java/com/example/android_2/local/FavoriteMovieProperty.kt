package com.example.android_2.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity (tableName = "favorite_movie")
@Parcelize
data class FavoriteMovieProperty(
        val id_movie : String,
        val original_title : String,
        val overview : String?,
        val poster_path : String,
        val vote_average : String,
        val release_date : String
): Serializable, Parcelable{
    @PrimaryKey(autoGenerate = true)
    @IgnoredOnParcel var id : Int = 0
    val base_image_url get() = "https://image.tmdb.org/t/p/w500"
}