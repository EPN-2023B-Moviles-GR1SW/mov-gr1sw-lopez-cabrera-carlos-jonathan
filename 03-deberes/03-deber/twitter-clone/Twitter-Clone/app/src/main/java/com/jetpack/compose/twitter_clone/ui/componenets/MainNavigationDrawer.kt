package com.jetpack.compose.twitter_clone.ui.componenets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.compose.twitter_clone.R
import com.jetpack.compose.twitter_clone.utils.LocalDataProvider
import kotlinx.coroutines.launch

data class NavigationDrawerBodyItem(
    val label: String,
    val icon: ImageVector,
    var onclick: () -> Unit = {}
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNavigationDrawer(
    navigationDrawerBodyItems: List<NavigationDrawerBodyItem> =
        LocalDataProvider.getNavigationDrawerBodyItems(),
    onBottomAppBarHomeIconClicked: () -> Unit = {},
    onBottomAppBarSpacesIconClicked: () -> Unit = {},
    onBottomAppBarNotificationIconClicked: () -> Unit = {},
    onBottomAppBarChatIconClicked: () -> Unit = {},
    onTopAppBarChangeAppIconClicked: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // header component
                    stickyHeader {
                        NavigationDrawerHeader(
                            imageResId = R.drawable.blue_white,
                            userNickname = "Carfox",
                            userName = "Carlos Lopez",
                            followers = 777,
                            following = 10,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(MaterialTheme.colorScheme.background)
                        )
                    }
                    // body component *list of items*
                    items(navigationDrawerBodyItems) {
                        NavigationDrawerItem(
                            label = { Text(text = it.label) },
                            selected = false,
                            onClick = { it.onclick },
                            icon = {
                                Icon(imageVector = it.icon, contentDescription = "add")
                            }
                        )
                    }
                    // footer 2 icons only
                    item {
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween
                       ) {
                           IconButton(onClick = { /*TODO*/ }) {
                               Icon(imageVector = Icons.Default.Lightbulb, contentDescription = "")
                           }
                           IconButton(onClick = { /*TODO*/ }) {
                               Icon(imageVector = Icons.Default.QrCode2, contentDescription = "")
                           }
                       }
                   }
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    navigationIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.blue_white),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                        )
                    },
                    onNavigationIconClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                ) },
            bottomBar = {
                MainBottomAppBar(
                    onHomeIconClicked = onBottomAppBarHomeIconClicked,
                    onSpacesIconClicked = onBottomAppBarSpacesIconClicked,
                    onNotificationIconClicked = onBottomAppBarNotificationIconClicked,
                )
            }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                content()
            }
        }
    }
}

@Composable
fun NavigationDrawerHeader(
    imageResId: Int,
    modifier: Modifier = Modifier,
    userNickname: String,
    userName: String,
    followers: Int,
    following: Int,
    onclick: () -> Unit = {},
    imageContentDescription: String? = null,

) {
    Column(
        modifier = modifier.clickable { onclick() },
        horizontalAlignment = Alignment.Start
    ) {
        // image
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = imageContentDescription,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(60.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // usernickname
        Text(
            text = userNickname,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        // username
        Text(
            text = "@${userName}",
            modifier = Modifier.alpha(0.7f)
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        // follower-following sections
        Row {
            // followers section
            ValueLabeledHorizontalSection(
                value = followers,
                label = "Followers"
            )
            Spacer(modifier = Modifier.width(8.dp))
            // following section
            ValueLabeledHorizontalSection(
                value = following,
                label = "Following"
            )
        }
    }
}

// used in header
@Composable
fun ValueLabeledHorizontalSection(
    value: Int,
    label: String,
    modifier: Modifier = Modifier,
    onclick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable { onclick() }
    ) {
        Text(text = "$value ",
            fontWeight = FontWeight.Bold)
        Text(text = label,
            modifier = Modifier.alpha(0.7f))
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainNavigationDrawerPreview() {
    MainNavigationDrawer()
}