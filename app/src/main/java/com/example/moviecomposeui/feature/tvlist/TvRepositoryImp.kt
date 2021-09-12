package com.example.moviecomposeui.feature.tvlist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviecomposeui.core.util.Constants
import com.example.moviecomposeui.feature.tvlist.entity.TvView
import com.example.moviecomposeui.feature.tvlist.pagingDataSource.TvPagingDataSource
import com.example.moviecomposeui.feature.tvlist.pagingDataSource.TvTopRatedPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvRepositoryImp
@Inject constructor(private val tvService: TvService)
    :TvRepository{
    override fun getTvList(): Flow<PagingData<TvView>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TvPagingDataSource(tvService) }
        ).flow
    }
    override fun getTvTopRatedList(): Flow<PagingData<TvView>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TvTopRatedPagingDataSource(tvService) }
        ).flow
    }

}