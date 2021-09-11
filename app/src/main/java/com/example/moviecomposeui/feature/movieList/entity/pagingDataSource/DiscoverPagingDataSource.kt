package com.example.moviecomposeui.feature.movieList.entity.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecomposeui.feature.movieList.entity.MovieResult
import com.example.moviecomposeui.feature.movieList.MovieRepository
import javax.inject.Inject

class DiscoverPagingDataSource
@Inject constructor(private val movieRepository: MovieRepository)
    : PagingSource<Int, MovieResult>(){
    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = movieRepository.getDiscoveredMovie(nextPage)

            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}