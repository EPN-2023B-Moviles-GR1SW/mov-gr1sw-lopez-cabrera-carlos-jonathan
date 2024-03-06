package com.jetpack.compose.twitter_clone

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jetpack.compose.twitter_clone.ui.componenets.MainNavigationDrawer

@Composable
fun TwitterCloneApp(
    onChangeAppIconClicked: () -> Unit = {}
) {
    val navController = rememberNavController()
    MainNavigationDrawer(
        onBottomAppBarHomeIconClicked = {
            navController.navigate("HomeScreen")
        },
        onBottomAppBarSpacesIconClicked = {
            navController.navigate("SpacesScreen")
        },
        onBottomAppBarNotificationIconClicked = {
            navController.navigate("NotificationScreen")
        },
    ) {
        TwitterCloneNavHost(
            navController
        )
    }
}


@Preview
@Composable
fun TwitterCloneAppPreview() {
    TwitterCloneApp {

    }
}