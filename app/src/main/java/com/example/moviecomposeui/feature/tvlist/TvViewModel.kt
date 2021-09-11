package com.example.moviecomposeui.feature.tvlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecomposeui.feature.tvlist.entity.TvResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TvViewModel
@Inject constructor(private val tvRepository: TvRepository): ViewModel(){
    val tvSeries: Flow<PagingData<TvResult>> = tvRepository.getTvList().cachedIn(viewModelScope)

    val tvTopRatedMovies: Flow<PagingData<TvResult>> = tvRepository.getTvTopRatedList().cachedIn(viewModelScope)
}