package com.jetpack.compose.twitter_clone.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.twitter_clone.ui.componenets.TweetComponent
import com.jetpack.compose.twitter_clone.ui.states.HomeScreenUiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenUiState
) {
    if(state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Loading....")
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(state.tweets) {
                TweetComponent(state = it)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeScreenUiState()
    )
}