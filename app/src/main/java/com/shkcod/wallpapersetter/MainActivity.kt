package com.shkcod.wallpapersetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.shkcod.wallpapersetter.ui.theme.WallpaperSetterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WallpaperSetterTheme {

            }
        }
    }
}