package com.example.moviecomposeui.feature.movievideos


import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideosService
@Inject constructor(retrofit: Retrofit): VideosApi{
    private  val videoApi by lazy { retrofit.create(VideosApi::class.java) }

    override suspend fun getVideosList(movieId: Int) = videoApi.getVideosList(movieId = movieId)

}