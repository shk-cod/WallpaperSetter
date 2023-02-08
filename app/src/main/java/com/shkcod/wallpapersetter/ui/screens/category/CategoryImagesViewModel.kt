package com.shkcod.wallpapersetter.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkcod.wallpapersetter.network.PixabayApi
import com.shkcod.wallpapersetter.network.PixabayImage
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class CategoryImagesViewModel(
    private val category: String
): ViewModel() {
    private val apiService = PixabayApi.retrofitService

    private val _imagesFlow = MutableSharedFlow<List<PixabayImage>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val imagesFlow: SharedFlow<List<PixabayImage>>
        get() = _imagesFlow

    private val _errorFlow = MutableSharedFlow<String>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val errorFlow: SharedFlow<String>
        get() = _errorFlow

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            val response = apiService.getImages(category)

            if (response.isSuccessful) {
                if (response.body() != null) {
                    _imagesFlow.tryEmit(response.body()!!.hits)
                }
            } else {
                if (response.errorBody() != null) {
                    _errorFlow.tryEmit(response.errorBody()!!.toString())
                }
            }
        }
    }

}