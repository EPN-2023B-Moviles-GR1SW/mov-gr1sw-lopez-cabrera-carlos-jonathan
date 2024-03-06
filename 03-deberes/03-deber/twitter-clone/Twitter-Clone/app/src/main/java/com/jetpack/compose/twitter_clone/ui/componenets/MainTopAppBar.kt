package com.jetpack.compose.twitter_clone.ui.componenets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flaky
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    onNavigationIconClicked: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {
         Icon(
             imageVector = Icons.Default.Menu,
             contentDescription = null
         )
    },
    onChangeAppIconClicked: () -> Unit = {}

) {
    CenterAlignedTopAppBar(
        title = {
            Icon(imageVector = Icons.Default.Flaky, contentDescription = null)
            },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClicked
            ) {
                navigationIcon()
            }
        },
        actions = {
            Button(onClick = onChangeAppIconClicked) {
                Text(text = "Change App Icon")
            }
        }
    )
}

