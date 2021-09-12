package com.example.moviecomposeui.feature.movievideos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeui.feature.movievideos.entity.VideoView
import com.example.moviecomposeui.feature.movievideos.entity.toVideoView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VideosViewModel
    @Inject constructor(private val videosRepositoryImp: VideosRepositoryImp): ViewModel()
{

    val videos: MutableState<List<VideoView>> = mutableStateOf(emptyList())

    fun getVideos( movieId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = videosRepositoryImp.getVideos(
                    movieId = movieId
                )
                videos.value = result.results.map { it.toVideoView() }
            }
        }

    }
}