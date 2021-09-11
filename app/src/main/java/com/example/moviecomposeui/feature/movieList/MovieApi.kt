package com.example.moviecomposeui.feature.movieList

import com.example.moviecomposeui.feature.movieList.entity.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/discover/movie?")
    suspend fun getMovieList(
        @Query("page") pageNumber: Int
    ): MovieResponse

    @GET("/3/movie/top_rated?")
    suspend fun getTopRatedList(
        @Query("page") pageNumber: Int
    ): MovieResponse

}