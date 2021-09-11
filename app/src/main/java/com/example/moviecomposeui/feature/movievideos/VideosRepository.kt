package com.example.moviecomposeui.feature.movievideos

import javax.inject.Inject

class VideosRepository
@Inject constructor(private val videoService: VideosService)
{
    suspend fun getVideos(movieId: Int) = videoService.getVideosList(movieId)
}