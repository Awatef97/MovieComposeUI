package com.example.moviecomposeui.feature.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviecomposeui.core.util.Constants.PAGE_SIZE
import com.example.moviecomposeui.feature.search.paginationDataSource.SearchPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.moviecomposeui.feature.search.entity.SearchResult
class SearchMovieRepository
@Inject constructor(private val movieService: SearchMovieService) {
    fun getSearchResultStream(query: String): Flow<PagingData<SearchResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingDataSource(movieService, query) }
        ).flow
    }

}