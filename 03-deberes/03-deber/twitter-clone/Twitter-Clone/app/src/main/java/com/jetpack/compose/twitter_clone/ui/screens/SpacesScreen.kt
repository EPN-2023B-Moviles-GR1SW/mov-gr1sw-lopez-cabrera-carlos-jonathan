package com.jetpack.compose.twitter_clone.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.twitter_clone.ui.componenets.SpaceCardComponent
import com.jetpack.compose.twitter_clone.ui.states.SpaceScreenUiState
import com.jetpack.compose.twitter_clone.utils.LocalDataProvider

@Composable
fun SpacesScreen(
    modifier: Modifier = Modifier,
    state: SpaceScreenUiState = SpaceScreenUiState()

) {
    LazyColumn(
        modifier = modifier
    ) {
        items(state.spaces) {
            SpaceCardComponent(
                state = it,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SpacesScreenPreview() {
    SpacesScreen(
        state = SpaceScreenUiState(
            spaces = LocalDataProvider.getSpaces()
        )
    )
}