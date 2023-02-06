package com.shkcod.wallpapersetter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shkcod.wallpapersetter.AnimatedSplashScreen
import com.shkcod.wallpapersetter.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}