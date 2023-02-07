package com.shkcod.wallpapersetter.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")

    object Home: Screen("home_screen")

    object CategoryImages: Screen("category_images_screen")

    object Wallpaper: Screen("wallpaper_screen")
}