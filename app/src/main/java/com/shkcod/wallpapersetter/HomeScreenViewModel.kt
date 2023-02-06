package com.shkcod.wallpapersetter

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel

class HomeScreenViewModel: ViewModel() {
    data class DrawableStringPair(
        @DrawableRes val drawable: Int,
        val text: String
    )

    val categoriesData = listOf(
        R.drawable.animals to "Animals",
        R.drawable.backgrounds to "Backgrounds",
        R.drawable.buildings to "Buildings",
        R.drawable.food to "Food",
        R.drawable.nature to "Nature",
        R.drawable.places to "Places",
        R.drawable.sports to "Sports",
        R.drawable.travel to "Travel"
    ).map { DrawableStringPair(it.first, it.second) }

}