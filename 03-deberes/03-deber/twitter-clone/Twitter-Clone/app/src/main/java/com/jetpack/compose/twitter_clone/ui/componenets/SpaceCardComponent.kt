package com.jetpack.compose.twitter_clone.ui.componenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlignHorizontalLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.twitter_clone.R
import com.jetpack.compose.twitter_clone.ui.states.SpaceCardUiState

@Composable
fun SpaceCardComponent(
    modifier: Modifier = Modifier,
    state: SpaceCardUiState = SpaceCardUiState()
) {
    /*
    *
    * Column
    *   row contains state and option button
    *   title
    *   subtitles
    *   row contains images for top listeners and number of listeners
    *   row contains host image, host nickname and label to determine this is the host
    *   description written by the host
    *
    * */

    Card(
        modifier = modifier
    ) {
        // row contains state and option button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(imageVector = state.statusIcon, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = state.status,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.AlignHorizontalLeft, contentDescription = null)
            }
        }

        // title
        Text(
            text = state.title,
            fontWeight = FontWeight.Bold,
//            fontSize = 32.sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )
        // subtitles
        Row {
            state.subtitles.forEach {
                Text(text = it)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        // row contains images for top listeners and number of listeners
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            // images
            state.topListenersImageId.forEach {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${state.numberOfListener} Listening")
        }

        // row contains host image, host nickname and label to determine this is the host
        Row(
            horizontalArrangement = spacedBy(8.dp), 
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            // host image
            Image(
                painter = painterResource(id = state.hostImageId),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
            )
            Text(text = state.hostNickName)
            // label
            Text(
                text = "Host",
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(2.dp)
                    .clip(RoundedCornerShape(2.dp))
            )
        }

        // description written by the host
        Text(
            text = state.hostDescription,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SpaceCardComponentPreview() {
    Column {
        SpaceCardComponent(
            modifier = Modifier.padding(8.dp),
            state = SpaceCardUiState(
                topListenersImageId = listOf(
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                )
            )
        )
        SpaceCardComponent(
            modifier = Modifier.padding(8.dp),
            state = SpaceCardUiState(
                topListenersImageId = listOf(
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                )
            )
        )
        SpaceCardComponent(
            modifier = Modifier.padding(8.dp),
            state = SpaceCardUiState(
                topListenersImageId = listOf(
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                )
            )
        )
        SpaceCardComponent(
            modifier = Modifier.padding(8.dp),
            state = SpaceCardUiState(
                topListenersImageId = listOf(
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                    R.drawable.blue_white,
                )
            )
        )
    }
}
