package com.example.moviecomposeui.feature.tvlist.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecomposeui.feature.tvlist.TvService
import com.example.moviecomposeui.feature.tvlist.entity.TvResult
import javax.inject.Inject

class TvPagingDataSource
@Inject constructor(private val tvService: TvService)
    : PagingSource<Int, TvResult>(){
    override fun getRefreshKey(state: PagingState<Int, TvResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvResult> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = tvService.getTvList(nextPage)

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