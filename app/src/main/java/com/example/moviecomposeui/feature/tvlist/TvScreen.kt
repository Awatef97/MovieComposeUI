package com.example.moviecomposeui.feature.tvlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
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
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.moviecomposeui.R
import com.example.moviecomposeui.core.util.Constants.BASE_POSTER_URL
import com.example.moviecomposeui.feature.tvlist.entity.TvResult
import com.example.moviecomposeui.ui.theme.SurfaceColor
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun TvScreen(mainViewModel: TvViewModel = hiltViewModel()) {
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
                TopRatedMovie(movies = mainViewModel.tvTopRatedMovies)
            }

            Text(
                text = "Tv List",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
            MovieScreen(movies = mainViewModel.tvSeries)

        }
    }
}
@Composable
fun TopRatedMovie(movies: Flow<PagingData<TvResult>>){
    val lazyMovieItems = movies.collectAsLazyPagingItems()
    LazyRow{
        items(lazyMovieItems.itemCount){index ->
            Spacer(modifier = Modifier.padding(end = 10.dp))
            lazyMovieItems[index]?.poster_path?.let { MovieImage(
                imageUrl = BASE_POSTER_URL + it,
                modifier = Modifier
                        .size(60.dp)

            )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MovieScreen(movies: Flow<PagingData<TvResult>>){

    val lazyMovieItems = movies.collectAsLazyPagingItems()

    LazyVerticalGrid(cells = GridCells.Fixed(2),modifier = Modifier.fillMaxSize(),contentPadding = PaddingValues(start = 5.dp, end = 5.dp)) {

        items(lazyMovieItems.itemCount) { index ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)

            ) {
            lazyMovieItems[index]?.let { MovieItem(movie = it) }
        }
        }


    }
}

@Composable
fun MovieItem(movie: TvResult) {

    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            , content = {
            Spacer(modifier = Modifier.height(10.dp))
            MovieImage(
                imageUrl =BASE_POSTER_URL + movie.poster_path,
                modifier = Modifier
                        .size(170.dp)
                    )

        }
    )

}
@Composable
fun MovieImage(imageUrl: String, modifier: Modifier = Modifier){
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_error)
            crossfade(true)
            transformations(RoundedCornersTransformation(20f))

        })

        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier
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