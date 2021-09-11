package com.example.moviecomposeui.feature.tvlist

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvService
@Inject constructor(retrofit: Retrofit): TvApi{
    private  val movieApi by lazy { retrofit.create(TvApi::class.java) }
    override suspend fun getTvList(pageNumber: Int) = movieApi.getTvList(pageNumber)

    /**
     * get popular movies*/
    override suspend fun getTvTopRatedList(pageNumber: Int) = movieApi.getTvTopRatedList(pageNumber = pageNumber)
}