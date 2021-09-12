package com.example.moviecomposeui.feature.movievideos.entity

data class VideosResponse(
    val id: Int,
    val results: List<VideosResult>
)
data class VideosResult(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val published_at: String,
    val site: String,
    val type: String
)
fun VideosResult.toVideoView() = VideoView(
    key = key
)