package com.example.moviecomposeui.feature.search

import com.example.moviecomposeui.feature.movieList.entity.MovieResponse
import com.example.moviecomposeui.feature.search.entity.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchMovieApi {

    @GET("/3/search/company?")
    suspend fun searchForMovie(
        @Query("page") pageNumber: Int,
        @Query("query") query: String
    ): SearchResponse
}