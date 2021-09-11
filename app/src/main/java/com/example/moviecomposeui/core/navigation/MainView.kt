package com.example.moviecomposeui.core.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviecomposeui.ui.theme.SurfaceColor

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainView(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar = {
            if(currentRoute != "${Screen.MovieDetails.route}/{movie_id}/{title}/{overView}")
            BottomBar(
                navController
            )
        }
    )
    {
        NavGraph(navController)
    }

}
