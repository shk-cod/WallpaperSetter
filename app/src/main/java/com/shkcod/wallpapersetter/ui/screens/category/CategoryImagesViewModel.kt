package com.shkcod.wallpapersetter.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkcod.wallpapersetter.network.PixabayApi
import com.shkcod.wallpapersetter.network.PixabayApiHelperImpl
import com.shkcod.wallpapersetter.network.PixabayImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CategoryImagesViewModel(
    val category: String
): ViewModel() {
    private val apiHelper = PixabayApiHelperImpl(PixabayApi.retrofitService)

    private val _imagesFlow = MutableSharedFlow<List<PixabayImage>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val imagesFlow: SharedFlow<List<PixabayImage>>
        get() = _imagesFlow

    private val _errorFlow = MutableSharedFlow<String?>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val errorFlow: SharedFlow<String?>
        get() = _errorFlow

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            apiHelper.getImages(category)
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    _errorFlow.tryEmit(error.message)
                }
                .collect {
                    _imagesFlow.tryEmit(it.hits)
                }
        }
    }

}