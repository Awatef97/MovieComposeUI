package com.example.moviecomposeui.feature.movievideos

import javax.inject.Inject

class VideosRepositoryImp
@Inject constructor(private val videoService: VideosService)
    : VideosRepository {
   override suspend fun getVideos(movieId: Int) = videoService.getVideosList(movieId)
}