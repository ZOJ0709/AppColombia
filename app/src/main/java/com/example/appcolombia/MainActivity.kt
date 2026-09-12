package com.example.appcolombia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.appcolombia.ui.Screens.splash.DepartmentDetailScreen
import com.example.appcolombia.ui.Screens.splash.HomeScreen
import com.example.appcolombia.ui.Screens.splash.SplashScreen
import com.example.appcolombia.ui.theme.AppColombiaTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppColombiaTheme {

                var showSplash by remember {
                    mutableStateOf(true)
                }

                LaunchedEffect(Unit) {
                    delay(2000)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {
                    HomeScreen()
                }

            }
        }
    }
}