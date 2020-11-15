package com.example.android_2.network

import com.example.android_2.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService{
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.MOVIEDB_API_KEY
    }

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopular(
        @Query("page") position: Int
    ): MovieResponse

    @GET ("search/movie?api_key=$API_KEY")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse
}