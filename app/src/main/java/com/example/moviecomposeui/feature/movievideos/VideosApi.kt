package com.example.moviecomposeui.feature.movievideos

import com.example.moviecomposeui.feature.movievideos.entity.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VideosApi {
    @GET("/3/movie/{movie_id}/videos?")
    suspend fun getVideosList(
        @Path("movie_id") movieId: Int
    ): VideosResponse

}