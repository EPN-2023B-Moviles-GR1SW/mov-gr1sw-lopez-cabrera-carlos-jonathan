package com.jetpack.compose.twitter_clone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpack.compose.twitter_clone.ui.screens.ChatScreen
import com.jetpack.compose.twitter_clone.ui.screens.HomeScreen
import com.jetpack.compose.twitter_clone.ui.screens.NotificationScreen
import com.jetpack.compose.twitter_clone.ui.screens.SpacesScreen
import com.jetpack.compose.twitter_clone.ui.screens.TrendingScreen
import com.jetpack.compose.twitter_clone.ui.viewmodels.HomeScreenViewModel
import com.jetpack.compose.twitter_clone.ui.viewmodels.SpacesScreenViewModel

@Composable
fun TwitterCloneNavHost(
    navController: NavHostController
) {
    // home screen shit
    val homeScreenViewModel: HomeScreenViewModel = viewModel()
    val homeScreenState = homeScreenViewModel.state.collectAsState()

    // spaces screen shit
    val spacesScreenViewModel: SpacesScreenViewModel = viewModel()
    val spacesScreenState = spacesScreenViewModel.state.collectAsState()


    NavHost(navController = navController, "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(
                state = homeScreenState.value
            )
        }
        composable("NotificationScreen") {
            NotificationScreen()
        }
        composable("SpacesScreen") {
            SpacesScreen(
                state = spacesScreenState.value
            )
        }
    }
}