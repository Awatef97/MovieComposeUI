package com.example.moviecomposeui.feature.moviedetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecomposeui.R
import com.example.moviecomposeui.core.util.Constants.BASE_POSTER_URL
import com.example.moviecomposeui.core.util.Constants.YOUTUBE_VIDEO_URL
import com.example.moviecomposeui.core.component.ExpandableCard
import com.example.moviecomposeui.feature.moviereviews.ReviewsViewModel
import com.example.moviecomposeui.feature.movievideos.VideosViewModel
import com.example.moviecomposeui.ui.theme.SurfaceColor

@ExperimentalMaterialApi
@Composable
fun MovieDetailsScreen(id: Int, title: String, overView: String) {
    val context = LocalContext.current
    val viewModel: VideosViewModel = hiltViewModel()
    val reviewsViewModel: ReviewsViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    LaunchedEffect(true ) {
        with(viewModel) {
            getVideos(id)

        }
        with(reviewsViewModel) {
            getReviews(id)
        }
    }
    Surface(color = SurfaceColor,modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(10.dp)) {
            Spacer(modifier = Modifier.size(10.dp))
            Column(Modifier.verticalScroll(scrollState)) {
                Text(text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
                Text(text = overView,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp,bottom = 10.dp)
                )
                if (viewModel.videos.value.isNotEmpty()) {
                    Text(text = "Videos",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontStyle = FontStyle.Italic
                    )
                    LazyRow {
                        items(viewModel.videos.value.size) { index ->
                            Spacer(modifier = Modifier.padding(end = 10.dp))
                            Image(
                                painter = painterResource(id = R.drawable.youtube_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        val playVideo =
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(YOUTUBE_VIDEO_URL + viewModel.videos.value[index].key)
                                            )
                                        context.startActivity(playVideo)
                                    }
                            )
                        }
                    }
                }


            }
            if (reviewsViewModel.reviews.value.isNotEmpty()) {
                Text(text = "See Some Reviews",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
                LazyColumn {
                    items(reviewsViewModel.reviews.value.size) { index ->
                        Spacer(modifier = Modifier.height(10.dp))
                        ExpandableCard(
                            title = reviewsViewModel.reviews.value[index].author,
                            description = reviewsViewModel.reviews.value[index].content,
                            imageUrl = BASE_POSTER_URL + reviewsViewModel.reviews.value[index].author_details.avatar_path
                        )

                    }
                }
            }
        }

    }
}