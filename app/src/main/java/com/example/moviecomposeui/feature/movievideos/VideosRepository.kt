package com.example.moviecomposeui.feature.movievideos

import com.example.moviecomposeui.feature.movievideos.entity.VideosResponse

interface VideosRepository {
    suspend fun getVideos(movieId: Int) : VideosResponse

}