package com.jetpack.compose.twitter_clone

import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jetpack.compose.twitter_clone.ui.theme.TwittercloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwittercloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isMainIconActivated by remember {
                        mutableStateOf(false)
                    }
                    TwitterCloneApp(
                        onChangeAppIconClicked = {
                            if(isMainIconActivated) {
                                // change to another icon
                                changeIcon(
                                    enableName = "$packageName.anotherIcon",
                                    disableName = "$packageName.MainActivity",
                                )
                                isMainIconActivated = false
                            } else {
                                // come back to the main icon
                                changeIcon(
                                    enableName = "$packageName.MainActivity",
                                    disableName = "$packageName.anotherIcon",
                                )
                                isMainIconActivated = true
                            }
                        }
                    )
                }
            }
        }
    }
}


fun Activity.changeIcon(
    enableName: String,
    disableName: String
) {
    // enable
    packageManager.setComponentEnabledSetting(
        ComponentName(
            this,
            enableName
        ),
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP
    )

    // disable
    packageManager.setComponentEnabledSetting(
        ComponentName(
            this,
            disableName
        ),
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
    )
}