package com.example.moviecomposeui.feature.moviereviews

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeui.feature.moviereviews.entity.ReviewsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel
    @Inject constructor(private val repository: ReviewsRepository):ViewModel()
{
    val reviews: MutableState<List<ReviewsResult>> = mutableStateOf(emptyList())

    fun getReviews( movieId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getReviews(
                    movieId = movieId
                )
                reviews.value = result.results
            }
        }

    }
}