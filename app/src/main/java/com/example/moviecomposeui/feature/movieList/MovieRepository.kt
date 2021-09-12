package com.example.moviecomposeui.feature.movieList

import com.example.moviecomposeui.feature.movieList.entity.MovieResponse

interface MovieRepository {
    suspend fun getDiscoveredMovie(pageNumber: Int) : MovieResponse

    suspend fun getTopRatedList(pageNumber: Int) : MovieResponse
}