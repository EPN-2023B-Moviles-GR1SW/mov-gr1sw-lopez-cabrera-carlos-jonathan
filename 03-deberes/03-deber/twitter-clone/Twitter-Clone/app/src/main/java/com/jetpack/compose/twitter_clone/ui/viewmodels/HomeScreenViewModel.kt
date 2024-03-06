package com.jetpack.compose.twitter_clone.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.compose.twitter_clone.ui.states.HomeScreenUiState
import com.jetpack.compose.twitter_clone.ui.states.TweetUiState
import com.jetpack.compose.twitter_clone.utils.LocalDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel: ViewModel() {
    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            withContext(Dispatchers.IO) {
                loadTweets()
            }
        }
    }

    private fun loadTweets(){
        _state.update {
            it.copy(
                tweets = setTweets(),
                isLoading = false
            )
        }
    }

    private fun setTweets(): List<TweetUiState> = LocalDataProvider.getHomeScreenTweets()


}