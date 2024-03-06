package com.jetpack.compose.twitter_clone.ui.states

import androidx.annotation.DrawableRes
import com.jetpack.compose.twitter_clone.R

data class TweetUiState(
    @DrawableRes
    val writerImageId: Int = R.drawable.blue_white,
    val writerNickName: String = "Carfox",
    val writerUserName: String = "Carfox",
    val tweetPublishingDate: String = "1/1/2024",
    val tweetContent: String = """
        Este es mi primer tweet
        Cuando X se llamaba twitter
    """.trimIndent(),
    val comments: Int = 0,
    val retweets: Int = 0,
    val loves: Int = 0,
    val shares: Int = 0
)
