package com.example.moviecomposeui.feature.movieList

import javax.inject.Inject

class MovieRepository
@Inject constructor(private val movieService: MovieService) {
    suspend fun getDiscoveredMovie(pageNumber: Int) = movieService.getMovieList(pageNumber = pageNumber)

    suspend fun getTopRatedList(pageNumber: Int) = movieService.getTopRatedList(pageNumber = pageNumber)
}