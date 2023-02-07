package com.shkcod.wallpapersetter.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shkcod.wallpapersetter.R
import com.shkcod.wallpapersetter.navigation.Screen
import com.shkcod.wallpapersetter.ui.theme.WallpaperSetterTheme
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val progressAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    LaunchedEffect(key1= true) {
        startAnimation = true
        delay(500)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    Splash(progressAnim.value)
}

@Composable
fun Splash(progress: Float) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashIcon()
            Spacer(
                modifier = Modifier.height(60.dp)
            )
            LinearProgressIndicator(progress)
        }
    }
}

@Composable
fun SplashIcon() {
    Surface(
        modifier = Modifier
            .size(160.dp)
            .clip(CircleShape),
        color = Color(0xFF35BD73)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            modifier = Modifier.requiredSize(240.dp),
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    WallpaperSetterTheme {
        Splash(0.7f)
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashDarkPreview() {
    WallpaperSetterTheme {
        Splash(0.7f)
    }
}