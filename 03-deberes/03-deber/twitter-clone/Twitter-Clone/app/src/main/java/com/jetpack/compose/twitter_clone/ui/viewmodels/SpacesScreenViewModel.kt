package com.jetpack.compose.twitter_clone.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.jetpack.compose.twitter_clone.ui.states.SpaceScreenUiState
import com.jetpack.compose.twitter_clone.utils.LocalDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SpacesScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(SpaceScreenUiState())
    val state = _state.asStateFlow()

    init {
        loadSpaces()
    }

    private fun loadSpaces() {
        _state.update {
            it.copy(
                spaces = setSpaces()
            )
        }
    }
    private fun setSpaces() = LocalDataProvider.getSpaces()


}