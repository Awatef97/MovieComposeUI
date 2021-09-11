package com.example.moviecomposeui.feature.search.paginationDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecomposeui.feature.search.SearchMovieService
import com.example.moviecomposeui.feature.search.entity.SearchResult
import javax.inject.Inject

class SearchPagingDataSource
@Inject constructor(private val searchMovieService: SearchMovieService,private var query: String)
    : PagingSource<Int, SearchResult>(){
    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = searchMovieService.searchForMovie(nextPage,query = query)

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