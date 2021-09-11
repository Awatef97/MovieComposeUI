package com.example.moviecomposeui.feature.tvlist

import com.example.moviecomposeui.feature.movieList.entity.MovieResponse
import com.example.moviecomposeui.feature.tvlist.entity.TvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApi {
    @GET("/3/discover/tv?")
    suspend fun getTvList(
        @Query("page") pageNumber: Int
    ): TvResponse

    @GET("/3/tv/top_rated?")
    suspend fun getTvTopRatedList(
        @Query("page") pageNumber: Int
    ): TvResponse

}