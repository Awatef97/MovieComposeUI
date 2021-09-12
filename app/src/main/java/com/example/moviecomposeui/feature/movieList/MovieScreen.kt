package com.example.moviecomposeui.feature.movieList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviecomposeui.core.component.RenderImage
import com.example.moviecomposeui.core.navigation.Screen
import com.example.moviecomposeui.core.util.Constants.BASE_POSTER_URL
import com.example.moviecomposeui.core.util.ErrorItem
import com.example.moviecomposeui.core.util.LoadingItem
import com.example.moviecomposeui.core.util.LoadingView
import com.example.moviecomposeui.feature.movieList.entity.MovieView
import com.example.moviecomposeui.ui.theme.SurfaceColor
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavHostController) {
    val mainViewModel: MovieViewModel = hiltViewModel()
    Surface(color = SurfaceColor) {
        Column {
            Text(
                text = "Top Rate",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
            Box {
                TopRatedMovie(movies = mainViewModel.topRatedMovies, navController = navController)
            }
            Text(
                text = "Movie List",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
            MovieScreen(movies = mainViewModel.movies,navController =navController)

        }

    }


}
@Composable
fun TopRatedMovie(movies: Flow<PagingData<MovieView>>,navController: NavHostController){
    val lazyMovieItems = movies.collectAsLazyPagingItems()
    LazyRow{
        items(lazyMovieItems.itemCount){index ->
            Spacer(modifier = Modifier.padding(end = 10.dp))
            lazyMovieItems[index]?.let {
                MovieItem(movie = it, navController = navController,modifier = Modifier.size(60.dp))

            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MovieScreen(movies: Flow<PagingData<MovieView>>, navController: NavHostController){

    val lazyMovieItems = movies.collectAsLazyPagingItems()

    LazyVerticalGrid(cells = GridCells.Fixed(2),modifier = Modifier.fillMaxSize(),contentPadding = PaddingValues(start = 5.dp, end = 5.dp)) {
        items(lazyMovieItems.itemCount) { index ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)

            ) {
                lazyMovieItems[index]?.let {
                    MovieItem(
                        movie = it,
                        navController = navController,
                        modifier = Modifier.size(170.dp)
                    )
                }
            }
            }


        lazyMovieItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: MovieView, navController: NavHostController, modifier : Modifier = Modifier) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier.clickable {
            navController.navigate("${Screen.MovieDetails.route}/${movie.id}/${movie.title}/${ movie.overview}")
                                      }
            , content = {
            Spacer(modifier = Modifier.height(10.dp))
            RenderImage(
                imageUrl =BASE_POSTER_URL + movie.poster_path,
                modifier = modifier
                    )

        }
    )

}


@Composable
fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}