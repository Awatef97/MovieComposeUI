package com.example.moviecomposeui.feature.moviereviews.entity

data class ReviewsResponse(
    val results: List<ReviewsResult>
)
data class ReviewsResult(
    val author: String,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String,
    val author_details: AuthorDetails
)
data class AuthorDetails(
    val avatar_path: String?,
    val name: String,
    val rating: Double,
    val username: String
)
data class AuthorDetailsView(
    val avatar_path: String?
)

fun AuthorDetails.toAuthorDetailsView() = AuthorDetailsView(avatar_path = avatar_path)

fun ReviewsResult.toReviewsView() = ReviewsView(
    author = author,
    content = content,
    author_details = author_details.toAuthorDetailsView()
)