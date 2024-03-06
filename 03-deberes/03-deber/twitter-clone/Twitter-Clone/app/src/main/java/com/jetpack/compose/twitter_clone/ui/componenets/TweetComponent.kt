package com.jetpack.compose.twitter_clone.ui.componenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.twitter_clone.ui.states.TweetUiState

@Composable
fun TweetComponent(
    modifier: Modifier = Modifier,
    state: TweetUiState = TweetUiState()
) {
    Row(
        modifier
    ) {
        // image
        Image(
            painter = painterResource(id = state.writerImageId),
            contentDescription = "${state.writerNickName} image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .weight(0.1f)
        )
        Spacer(modifier = Modifier.weight(0.02f))
        // content
        Column(
            modifier = Modifier.weight(0.88f)
        ) {
            Row(
                horizontalArrangement = spacedBy(8.dp)
            ){
                // nickname
                Text(
                    text = state.writerNickName,
                    fontWeight = Bold
                )

                // username
                Text(
                    text = "@${state.writerUserName}",
                    modifier = Modifier.alpha(0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // date
                Text(text = state.tweetPublishingDate)
            }

            // content text
            Text(text = state.tweetContent,
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp))

            // statistics
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatisticsBadge(icon = Icons.Default.Comment, value = "${state.comments}")
                StatisticsBadge(icon = Icons.Default.PlayCircleOutline, value = "${state.loves}")
                StatisticsBadge(icon = Icons.Default.Recycling, value = "${state.retweets}")
                StatisticsBadge(icon = Icons.Default.Share, value = "${state.shares}")
            }
        }
    }
}

@Composable
fun StatisticsBadge(
    icon: ImageVector,
    value: String,
    modifier: Modifier = Modifier,
    iconContentDescription: String? = ""
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription,
            modifier = Modifier.alpha(0.7f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = value, modifier = Modifier.alpha(0.7f))
    }
}

@Preview(showSystemUi = true, showBackground = true,
    device = "spec:parent=pixel_5"
)
@Composable
fun TweetComponentPreview() {
    TweetComponent()
}