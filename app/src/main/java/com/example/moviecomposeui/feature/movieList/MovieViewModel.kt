package com.example.moviecomposeui.feature.movieList

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviecomposeui.core.util.Constants.PAGE_SIZE
import com.example.moviecomposeui.feature.movieList.entity.MovieView
import com.example.moviecomposeui.feature.movieList.entity.pagingDataSource.DiscoverPagingDataSource
import com.example.moviecomposeui.feature.movieList.entity.pagingDataSource.TopRatedPagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor(private val movieRepositoryImp: MovieRepositoryImp): ViewModel(){
    val movies: Flow<PagingData<MovieView>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        DiscoverPagingDataSource(movieRepositoryImp)
    }.flow

    val topRatedMovies: Flow<PagingData<MovieView>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        TopRatedPagingDataSource(movieRepositoryImp)
    }.flow
}