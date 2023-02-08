package com.shkcod.wallpapersetter.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.shkcod.wallpapersetter.R

class HomeScreenViewModel: ViewModel() {

    val errorImage = R.drawable.ic_broken_image
    val loadingAnimation = R.drawable.loading_animation_large

    // Images categories list for displaying on grid
    val categoriesList = listOf(
        CategoryData(R.drawable.animals, R.string.category_animals, Categories.ANIMALS),
        CategoryData(R.drawable.backgrounds, R.string.category_backgrounds, Categories.BACKGROUNDS),
        CategoryData(R.drawable.buildings, R.string.category_buildings, Categories.BUILDINGS),
        CategoryData(R.drawable.food, R.string.category_food, Categories.FOOD),
        CategoryData(R.drawable.nature, R.string.category_nature, Categories.NATURE),
        CategoryData(R.drawable.places, R.string.category_places, Categories.PLACES),
        CategoryData(R.drawable.sports, R.string.category_sports, Categories.SPORTS),
        CategoryData(R.drawable.travel, R.string.category_travel, Categories.TRAVEL)
    )

    /**
     * Images categories enumeration.
     */
    enum class Categories(val value: String) {
        ANIMALS("animals"),
        BACKGROUNDS("backgrounds"),
        BUILDINGS("buildings"),
        FOOD("food"),
        NATURE("nature"),
        PLACES("places"),
        SPORTS("sports"),
        TRAVEL("travel")
    }

    /**
     * Represents category item.
     * @param drawable drawable resource of category image.
     * @param text text resource of category name.
     * @param category appropriate category from [Categories].
     */
    data class CategoryData(
        @DrawableRes val drawable: Int,
        @StringRes val text: Int,
        val category: Categories
    )

}

