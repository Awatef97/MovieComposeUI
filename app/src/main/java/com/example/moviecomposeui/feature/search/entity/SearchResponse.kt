package com.example.moviecomposeui.feature.search.entity

data class SearchResponse(
    val page: Int,
    val results: List<SearchResult>,
    val total_pages: Int,
    val total_results: Int
)
data class SearchResult(
    val id: Int,
    val logo_path: Any,
    val name: String,
    val origin_country: String
)