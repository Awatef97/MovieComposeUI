package com.example.moviecomposeui.feature.tvlist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviecomposeui.core.util.Constants
import com.example.moviecomposeui.feature.search.entity.SearchResult
import com.example.moviecomposeui.feature.search.paginationDataSource.SearchPagingDataSource
import com.example.moviecomposeui.feature.tvlist.entity.TvResult
import com.example.moviecomposeui.feature.tvlist.pagingDataSource.TvPagingDataSource
import com.example.moviecomposeui.feature.tvlist.pagingDataSource.TvTopRatedPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvRepository
@Inject constructor(private val tvService: TvService) {
    fun getTvList(): Flow<PagingData<TvResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TvPagingDataSource(tvService) }
        ).flow
    }
    fun getTvTopRatedList(): Flow<PagingData<TvResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TvTopRatedPagingDataSource(tvService) }
        ).flow
    }

}