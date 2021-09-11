package com.example.moviecomposeui.core.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.moviecomposeui.feature.movieList.MainScreen
import com.example.moviecomposeui.feature.movieList.entity.MovieResult
import com.example.moviecomposeui.feature.moviedetail.MovieDetailsScreen
import com.example.moviecomposeui.feature.tvlist.TvScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController, startDestination = Screen.Movie.route) {
        composable(Screen.Movie.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.Tv.route) {
            TvScreen()
        }
        composable(
            "${Screen.MovieDetails.route}/{movie_id}/{title}/{overView}",
                arguments = listOf (
                    navArgument("movie_id") { type = NavType.IntType },
                    navArgument("title"){type = NavType.StringType},
                    navArgument("image"){type = NavType.StringType}
                ))
        {
            val movieId =  it.arguments?.getInt("movie_id")  ?: 0
            val movieTitle = it.arguments?.getString("title") ?: ""
            val movieOverView = it.arguments?.getString("overView") ?: ""


            MovieDetailsScreen(id = movieId, title = movieTitle, overView = movieOverView)
        }
    }

}
