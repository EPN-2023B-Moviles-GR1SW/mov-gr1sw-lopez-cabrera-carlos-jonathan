package com.jetpack.compose.twitter_clone.ui.componenets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.twotone.Chat
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainBottomAppBar(
    onHomeIconClicked: () -> Unit = {},
    onTrendingIconClicked: () -> Unit = {},
    onSpacesIconClicked: () -> Unit = {},
    onNotificationIconClicked: () -> Unit = {},
    onChatIconClicked: () -> Unit = {}
) {
    BottomAppBar {
        // home
        NavigationBarItem(
            selected = false,
            onClick = onHomeIconClicked,
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            },
            label = { Text(text = "Home") }
        )
        // spaces
        NavigationBarItem(
            selected = false,
            onClick = onSpacesIconClicked,
            icon = {
                Icon(imageVector = Icons.Default.Stars, contentDescription = null)
            },
            label = { Text(text = "Spaces") }
        )
        // notification
        NavigationBarItem(
            selected = false,
            onClick = onNotificationIconClicked,
            icon = {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
            },
            label = { Text(text = "Notificacion") }
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainBottomNavigationPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MainBottomAppBar() },
        topBar = {
           // MainTopAppBar()
        }
    ) {
        Text(text = "something", Modifier.padding(it))
    }
}