package com.example.moviecomposeui.core.navigation

import androidx.annotation.DrawableRes
import com.example.moviecomposeui.R

sealed class Screen(  val route: String, val title : String= "", @DrawableRes val icon : Int= 0) {
    object Movie : Screen("Movie",title = "Movie", icon= R.drawable.ic_movie)
    object Tv : Screen("Tv",title = "Tv", icon= R.drawable.ic_tv)
    object MovieDetails : Screen("MovieDetails")
//    {
//
//        const val routeWithArgument: String = "MovieDetails/{movieId}"
//
//        const val argument0: String = "movieId"
//    }
}