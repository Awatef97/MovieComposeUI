package com.example.moviecomposeui.feature.search

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalFoundationApi
@Composable
fun SearchList(
    viewModel: SearchMovieViewModel = hiltViewModel()
): LazyPagingItems<com.example.moviecomposeui.feature.search.entity.SearchResult> {
    var value by rememberSaveable { mutableStateOf("") }
    var isEmpty by rememberSaveable { mutableStateOf(false) }
    var isSuccess by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    TextField(
        value = value,
        onValueChange = {
            value = it
          //  viewModel.getQuery(value)


        },
        label = {
            if (value == "")
                Text(text = "Search", color = Color.Gray)
        },

        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.Transparent
        ),
        // trailingIcon = Icon(imageVector = Icons.Filled.Clear, contentDescription = null),
        //leadingIcon = Icon(imageVector = Icons.Filled.Search, contentDescription = null),
        singleLine = true,

        )
    viewModel.getQuery("a")
    val list = viewModel.searchRepo().collectAsLazyPagingItems()

    return list
}
