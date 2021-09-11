package com.example.moviecomposeui.feature.moviereviews

import com.example.moviecomposeui.feature.moviereviews.entity.ReviewsResponse
import com.example.moviecomposeui.feature.moviereviews.entity.ReviewsResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewApi {
    @GET("/3/movie/{movie_id}/reviews?")
    suspend fun getReviewsList(
        @Path("movie_id") movieId: Int
    ): ReviewsResponse
}