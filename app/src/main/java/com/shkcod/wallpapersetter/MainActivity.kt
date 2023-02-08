package com.shkcod.wallpapersetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.shkcod.wallpapersetter.navigation.SetupNavGraph
import com.shkcod.wallpapersetter.ui.theme.WallpaperSetterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Adds Android 12 support for splash screen
        installSplashScreen()

        setContent {
            WallpaperSetterTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}