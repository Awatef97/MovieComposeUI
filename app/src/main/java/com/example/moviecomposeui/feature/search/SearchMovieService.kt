package com.example.moviecomposeui.feature.search

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMovieService
@Inject constructor(retrofit: Retrofit): SearchMovieApi{
    private  val movieApi by lazy { retrofit.create(SearchMovieApi::class.java) }

    override suspend fun searchForMovie(pageNumber: Int, query: String) = movieApi.searchForMovie(
        pageNumber = pageNumber,
        query = query
    )
}