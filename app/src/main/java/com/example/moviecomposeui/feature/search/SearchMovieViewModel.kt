package com.example.moviecomposeui.feature.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecomposeui.feature.search.entity.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel
@Inject constructor(private val movieRepository: SearchMovieRepository): ViewModel(){
    private val _query = mutableStateOf("")

    fun getQuery(query: String){
        _query.value = query
    }

     fun searchRepo(): Flow<PagingData<SearchResult>> =
        movieRepository.getSearchResultStream(_query.value)
            .cachedIn(viewModelScope)
}

