package com.shkcod.wallpapersetter.ui.screens.splash

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shkcod.wallpapersetter.navigation.Screen
import com.shkcod.wallpapersetter.ui.theme.WallpaperSetterTheme
import com.shkcod.wallpapersetter.ui.theme.splashIconSize
import com.shkcod.wallpapersetter.ui.theme.splashIconSurfaceSize
import com.shkcod.wallpapersetter.ui.theme.splashSpacerHeight
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController,
    viewModel: AnimatedSplashScreenViewModel = viewModel()
) {
    var startAnimation by remember { mutableStateOf(false) }
    val progressAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    LaunchedEffect(key1= true) {
        startAnimation = true
        delay(500L)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    Splash(viewModel, progressAnim.value)
}

@Composable
fun Splash(
    viewModel: AnimatedSplashScreenViewModel,
    progress: Float
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashIcon(viewModel)
            Spacer(
                modifier = Modifier.height(splashSpacerHeight)
            )
            LinearProgressIndicator(progress)
        }
    }
}

@Composable
fun SplashIcon(
    viewModel: AnimatedSplashScreenViewModel
) {
    Surface(
        modifier = Modifier
            .size(splashIconSurfaceSize)
            .clip(CircleShape),
        color = Color(viewModel.splashIconBackgroundColor)
    ) {
        Icon(
            painter = painterResource(viewModel.splashIcon),
            modifier = Modifier.requiredSize(splashIconSize),
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    WallpaperSetterTheme {
        AnimatedSplashScreen(rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashDarkPreview() {
    WallpaperSetterTheme {
        AnimatedSplashScreen(rememberNavController())
    }
}