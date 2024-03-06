package com.jetpack.compose.twitter_clone.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import com.jetpack.compose.twitter_clone.ui.states.TrendingListItemUiState
import com.jetpack.compose.twitter_clone.utils.LocalDataProvider

@Composable
fun TrendingScreen(
    modifier: Modifier = Modifier
) {
    val tabs = listOf("For you", "Trending", "News", "Sports", "Entertainment")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
//    var selectedTabTitle by remember { mutableStateOf(tabs[selectedTabIndex]) }

    Column(modifier = modifier) {

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = tabTitle, fontWeight = FontWeight.Bold) }
                )
            }
        }
        when(selectedTabIndex) {
            // for you
            0 -> {
                TrendingScreenContent(
                    trends = LocalDataProvider.generateTrendingListItems(10)
                )
            }
            // trending
            1 -> {
                TrendingScreenContent(
                    trends = LocalDataProvider.generateTrendingListItems(5)
                )
            }
            // news
            2 -> {
                TrendingScreenContent(
                    trends = LocalDataProvider.generateTrendingListItems(7)
                )
            }
            // sports
            3 -> {
                TrendingScreenContent(
                    trends = LocalDataProvider.generateTrendingListItems(8)
                )
            }
            // entertainment
            4 -> {
                TrendingScreenContent(
                    trends = LocalDataProvider.generateTrendingListItems(2)
                )
            }
        }
    }
}

@Composable
fun TrendingListItem(
    modifier: Modifier = Modifier,
    state: TrendingListItemUiState = TrendingListItemUiState(),
) {

    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = state.label, modifier = Modifier.alpha(0.7f))
            Icon(imageVector = Icons.Default.PlayCircleOutline, contentDescription = null)
        }
        Text(text = state.title)
        Text(text = "${state.numberOfPosts} posts", modifier = Modifier.alpha(0.7f))
    }
}

@Composable
fun TrendingScreenContent(
    trends: List<TrendingListItemUiState>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(trends) {
            TrendingListItem(state = it)
        }
    }
}
