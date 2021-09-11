package com.example.moviecomposeui.feature.movievideos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeui.feature.movievideos.entity.VideosResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VideosViewModel
    @Inject constructor(private val videosRepository: VideosRepository): ViewModel()
{

    val videos: MutableState<List<VideosResult>> = mutableStateOf(emptyList())

    fun getVideos( movieId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = videosRepository.getVideos(
                    movieId = movieId
                )
                videos.value = result.results
            }
        }

    }
}