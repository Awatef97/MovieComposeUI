package com.example.moviecomposeui.feature.tvlist.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvView(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
): Parcelable
