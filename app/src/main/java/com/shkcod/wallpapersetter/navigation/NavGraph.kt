package com.shkcod.wallpapersetter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shkcod.wallpapersetter.ui.screens.AnimatedSplashScreen
import com.shkcod.wallpapersetter.ui.screens.category.CategoryImagesScreen
import com.shkcod.wallpapersetter.ui.screens.home.HomeScreen
import com.shkcod.wallpapersetter.ui.screens.wallpaper.WallpaperScreen

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
            HomeScreen(navController)
        }

        composable(
            route = "${Screen.CategoryImages.route}/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            CategoryImagesScreen(
                category = it.arguments!!.getString("category") as String,
                navController = navController
            )
        }

        composable(
            route = "${Screen.Wallpaper.route}/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {
            WallpaperScreen(it.arguments!!.getString("url") as String)
        }
    }
}