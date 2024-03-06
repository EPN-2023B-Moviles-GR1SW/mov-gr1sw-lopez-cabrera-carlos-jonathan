package com.jetpack.compose.twitter_clone.ui.states

data class HomeScreenUiState(
    val tweets: List<TweetUiState> = emptyList(),
    val isLoading: Boolean = true
)
