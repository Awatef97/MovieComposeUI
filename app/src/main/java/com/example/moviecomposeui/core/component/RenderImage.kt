package com.example.moviecomposeui.core.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.moviecomposeui.R

@Composable
fun RenderImage(imageUrl: String, modifier: Modifier = Modifier){
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