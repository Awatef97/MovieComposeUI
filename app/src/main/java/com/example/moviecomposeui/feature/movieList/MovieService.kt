package com.example.moviecomposeui.feature.movieList

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieService
@Inject constructor(retrofit: Retrofit): MovieApi{
    private  val movieApi by lazy { retrofit.create(MovieApi::class.java) }
    override suspend fun getMovieList(pageNumber: Int) = movieApi.getMovieList(pageNumber)

    /**
     * get popular movies*/
    override suspend fun getTopRatedList(pageNumber: Int) = movieApi.getTopRatedList(pageNumber = pageNumber)
}