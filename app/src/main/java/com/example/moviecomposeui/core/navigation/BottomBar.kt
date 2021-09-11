package com.example.moviecomposeui.core.navigation

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviecomposeui.ui.theme.BottomBarColor
import com.example.moviecomposeui.ui.theme.SurfaceColor

@Composable
fun BottomBar(navController: NavController){

    val items = listOf(
        Screen.Movie,
        Screen.Tv
    )

    BottomNavigation(
        elevation = 5.dp,

    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.map {
            BottomNavigationItem(
                modifier = Modifier.background(BottomBarColor),
                icon= {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                },
                label= {
                    Text(
                        text = it.title
                    )
                },
                selected = currentRoute == it.route,
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(it.route)
                }
            )
        }

    }
}