package com.example.moviecomposeui.feature.tvlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecomposeui.feature.tvlist.entity.TvView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TvViewModel
@Inject constructor(private val tvRepositoryImp: TvRepositoryImp): ViewModel(){
    val tvSeries: Flow<PagingData<TvView>> = tvRepositoryImp.getTvList().cachedIn(viewModelScope)

    val tvTopRatedMovies: Flow<PagingData<TvView>> = tvRepositoryImp.getTvTopRatedList().cachedIn(viewModelScope)
}