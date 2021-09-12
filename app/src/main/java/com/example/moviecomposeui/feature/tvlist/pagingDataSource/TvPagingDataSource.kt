package com.example.moviecomposeui.feature.tvlist.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecomposeui.feature.tvlist.TvService
import com.example.moviecomposeui.feature.tvlist.entity.TvView
import com.example.moviecomposeui.feature.tvlist.entity.toTvViews
import javax.inject.Inject

class TvPagingDataSource
@Inject constructor(private val tvService: TvService)
    : PagingSource<Int, TvView>(){
    override fun getRefreshKey(state: PagingState<Int, TvView>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvView> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = tvService.getTvList(nextPage)

            LoadResult.Page(
                data = movieListResponse.results.map { it.toTvViews() },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}