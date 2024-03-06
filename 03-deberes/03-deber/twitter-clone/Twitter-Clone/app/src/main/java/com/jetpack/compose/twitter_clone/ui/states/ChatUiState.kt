package com.jetpack.compose.twitter_clone.ui.states

import androidx.annotation.DrawableRes
import com.jetpack.compose.twitter_clone.R

data class ChatUiState(
    @DrawableRes
    val imageId: Int = R.drawable.blue_white,
    val imageContentDescription: String? = "",
    val userName: String = "",
    val nickName: String = "",
    val lastMessageText: String = "",
    val lastMessageDate: String = ""
)

