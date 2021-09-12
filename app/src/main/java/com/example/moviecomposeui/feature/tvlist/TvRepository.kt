package com.example.moviecomposeui.feature.tvlist

import androidx.paging.PagingData
import com.example.moviecomposeui.feature.tvlist.entity.TvView
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getTvList(): Flow<PagingData<TvView>>
    fun getTvTopRatedList(): Flow<PagingData<TvView>>
}