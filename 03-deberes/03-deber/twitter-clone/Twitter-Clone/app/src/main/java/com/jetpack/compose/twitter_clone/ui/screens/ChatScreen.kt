package com.jetpack.compose.twitter_clone.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.twitter_clone.ui.states.ChatUiState
import com.jetpack.compose.twitter_clone.utils.LocalDataProvider

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    chats: List<ChatUiState> = LocalDataProvider.getChats()
) {
    LazyColumn(
        modifier = modifier
    ){
        items(chats) {
            ChatListItem(chatUiState = it)
        }
    }
}

@Composable
fun ChatListItem(
    modifier: Modifier = Modifier,
    chatUiState: ChatUiState
) {
    Row(
        modifier = modifier.padding(16.dp)
    ) {
        // image
        Image(
            painter = painterResource(id = chatUiState.imageId),
            contentDescription = chatUiState.imageContentDescription,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
        )
        // username and nickname
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Text(
                    text = chatUiState.nickName, fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "@${chatUiState.userName}",
                    modifier = Modifier.alpha(0.7f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            // last message
            Text(text = chatUiState.lastMessageText)
        }
        // message date
        Box {
            Text(text = chatUiState.lastMessageDate)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}
