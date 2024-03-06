package com.jetpack.compose.twitter_clone.ui.states

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.ui.graphics.vector.ImageVector
import com.jetpack.compose.twitter_clone.R

data class SpaceCardUiState(
    val status: String = "LIVE",
    val statusIcon: ImageVector = Icons.Default.LiveTv,
    val title: String = "Hablando sin saber",
    val subtitles: List<String> = emptyList(),
    val topListenersImageId: List<Int> = emptyList(),
    val numberOfListener: Int = 200,
    val hostImageId: Int = R.drawable.blue_white,
    val hostNickName: String = "Jack",
    val hostDescription: String = "Que va primero, la leche o el cereal"
)
