package com.example.moviecomposeui.feature.movieList.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieView(
    val id: Int,
    val overview: String,
    val title: String,
    val poster_path: String
): Parcelable