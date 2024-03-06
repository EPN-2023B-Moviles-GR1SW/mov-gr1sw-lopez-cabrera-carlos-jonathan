package com.jetpack.compose.twitter_clone.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.twitter_clone.ui.componenets.TweetComponent
import com.jetpack.compose.twitter_clone.ui.states.TweetUiState
import com.jetpack.compose.twitter_clone.utils.LocalDataProvider

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier
) {

    val titles = listOf<String>("all", "mine")
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Column {
        TabRow(selectedTabIndex = selectedIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = { Text(text = title)})
            }
        }
        when(selectedIndex) {
            0 -> {
                NotificationListItem()
            }
            1 -> {
                MineNotifications()
            }
            else -> {
                AllNotifications()
            }
        }
    }
}

@Composable
fun MineNotifications(
    notifications: List<TweetUiState> = LocalDataProvider.getHomeScreenTweets()
) {
    LazyColumn {
        items(notifications) {
            NotificationListItem(state = it)
        }
    }
}

@Composable
fun AllNotifications(
    notifications: List<TweetUiState> = LocalDataProvider.getHomeScreenTweets()
) {
    LazyColumn {
        items(notifications) {
            NotificationListItem(state = it)
        }
    }
}

@Composable
fun NotificationListItem(
    modifier: Modifier = Modifier,
    state: TweetUiState = TweetUiState()
) {
    TweetComponent(
        modifier = modifier,
        state = state
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}