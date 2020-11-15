package com.example.android_2.network

import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val STARTTING_PAGE_INDEX = 1

class MoviePagingSource (
    private val movieApi: MovieApiService,
    private val query: String?
) : PagingSource<Int,MovieProperty>(){
    override suspend fun load(params:LoadParams<Int>) : LoadResult<Int, MovieProperty>{
        return try {
            val position = params.key ?: STARTTING_PAGE_INDEX
            val response = if (query!=null) movieApi.searchMovies(query,position)
                else movieApi.getPopular(position)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTTING_PAGE_INDEX) null else position-1,
                nextKey = if (movies.isEmpty()) null else position+1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}