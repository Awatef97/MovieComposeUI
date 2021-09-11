package com.example.moviecomposeui.feature.moviereviews

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewService
@Inject constructor(retrofit: Retrofit):ReviewApi
{
    private val reviewApi by lazy { retrofit.create(ReviewApi::class.java) }
    override suspend fun getReviewsList(movieId: Int) = reviewApi.getReviewsList(movieId)

}