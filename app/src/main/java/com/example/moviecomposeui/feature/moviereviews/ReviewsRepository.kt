package com.example.moviecomposeui.feature.moviereviews

import javax.inject.Inject

class ReviewsRepository
@Inject constructor(private val service: ReviewService){
    suspend fun getReviews(movieId: Int) = service.getReviewsList(movieId)
}