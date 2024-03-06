package com.jetpack.compose.twitter_clone.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Topic
import com.jetpack.compose.twitter_clone.ui.componenets.NavigationDrawerBodyItem
import com.jetpack.compose.twitter_clone.ui.states.ChatUiState
import com.jetpack.compose.twitter_clone.ui.states.SpaceCardUiState
import com.jetpack.compose.twitter_clone.ui.states.TrendingListItemUiState
import com.jetpack.compose.twitter_clone.ui.states.TweetUiState
import java.util.Random

object LocalDataProvider {

    fun getNavigationDrawerBodyItems(): List<NavigationDrawerBodyItem> {
        return listOf(
            NavigationDrawerBodyItem(
                "Perfil",
                Icons.Default.Person,
                onclick = {
                    println("clicked on Profile")
                }
            ),
            NavigationDrawerBodyItem(
                "Listas",
                Icons.Default.List,
                onclick = {
                    println("clicked on Lists")
                }
            ),
            NavigationDrawerBodyItem(
                "Saves",
                Icons.Default.Bookmark,
            ),
        )
    }
    
    fun getNavigationDrawerBodyItemsNavigationActions(): List< () -> Unit > {
        return listOf(
            {},
            {},
            {},
            {},
        )
    }


    fun getChats(count: Int = 10): List<ChatUiState> {
        val random = Random(System.currentTimeMillis())

        return List(count) {
            ChatUiState(
//                imageId = random.nextInt(10), // Replace with actual image resource IDs
                imageContentDescription = null,
                userName = generateRandomName(random),
                nickName = generateRandomNickName(random),
                lastMessageText = generateRandomMessage(random),
                lastMessageDate = generateRandomDate()
            )
        }
    }

    fun generateRandomName(random: Random): String {
        val firstNames = listOf("Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Harry")
        val lastNames = listOf("Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia")
        return "${firstNames[random.nextInt(firstNames.size)]} ${lastNames[random.nextInt(lastNames.size)]}"
    }

    fun generateRandomNickName(random: Random): String {
        val nicknames = listOf("Ace", "Bear", "Cake", "Dizzy", "Echo", "Fox", "Glitch", "Hacker")
        return nicknames[random.nextInt(nicknames.size)]
    }

    fun generateRandomMessage(random: Random): String {
        val messages = listOf("Hey there!", "What's up?", "How's it going?", "Check out this cool thing!", "Busy day?", "See you soon!", "Just thinking of you.")
        return messages[random.nextInt(messages.size)]
    }

    fun generateRandomDate(): String {
        val now = System.currentTimeMillis()
        val randomDaysAgo = Random(now).nextInt(7)
        val date = now - randomDaysAgo * 24 * 60 * 60 * 1000
        return date.toString()
    }

    fun getHomeScreenTweets(): List<TweetUiState> {
        return listOf(
            TweetUiState(),
            TweetUiState(),
            TweetUiState(),
            TweetUiState(),
            TweetUiState(),
            TweetUiState(),
            TweetUiState(),
        )
    }

    fun getSpaces(): List<SpaceCardUiState> {
        return listOf(
            SpaceCardUiState(),
            SpaceCardUiState(),
            SpaceCardUiState(),
            SpaceCardUiState(),
            SpaceCardUiState(),
            SpaceCardUiState(),
        )
    }

    fun generateTrendingListItems(count: Int = 10): List<TrendingListItemUiState> {
        val labels = listOf("Trending in Egypt", "Popular in Cairo", "Viral in Alexandria", "Top in Giza", "Trending Worldwide")
        val titles = listOf(
            "Watch this amazing video!",
            "This photo is going viral!",
            "Can you believe this story?",
            "Everyone is talking about this!",
            "Don't miss this trending topic!"
        )

        return List(count) {
            TrendingListItemUiState(
                label = labels.random(),
                title = titles.random(),
                numberOfPosts = Random().nextInt()
            )
        }
    }



}
