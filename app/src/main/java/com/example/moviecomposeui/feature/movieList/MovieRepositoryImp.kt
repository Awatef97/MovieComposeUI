package com.example.moviecomposeui.feature.movieList

import javax.inject.Inject

class MovieRepositoryImp
@Inject constructor(private val movieService: MovieService) : MovieRepository {
    override suspend fun getDiscoveredMovie(pageNumber: Int) = movieService.getMovieList(pageNumber = pageNumber)

    override suspend fun getTopRatedList(pageNumber: Int) = movieService.getTopRatedList(pageNumber = pageNumber)
}