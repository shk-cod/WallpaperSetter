package com.shkcod.wallpapersetter.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CategoryImagesViewModelFactory(
    private val category: String
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryImagesViewModel::class.java)) {
            return CategoryImagesViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}