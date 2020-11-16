package com.example.android_2.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieProperty(
    val id:String,
    val overview:String?,
    val poster_path:String,
    val original_title:String,
    val vote_average:String,
    val release_date:String
):Parcelable{
    val base_image_url get() = "https://image.tmdb.org/t/p/w500"
}