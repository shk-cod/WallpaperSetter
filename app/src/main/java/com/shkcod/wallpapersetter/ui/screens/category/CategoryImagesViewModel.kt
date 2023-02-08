package com.shkcod.wallpapersetter.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkcod.wallpapersetter.network.PixabayApi
import com.shkcod.wallpapersetter.network.PixabayApiHelper
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

    private val apiHelper = PixabayApiHelper(PixabayApi.retrofitService)

    // SharedFlow that emits image list.
    // Using backing property to prevent mutable object from external modification.
    private val _imagesFlow = MutableSharedFlow<List<PixabayImage>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val imagesFlow: SharedFlow<List<PixabayImage>>
        get() = _imagesFlow

    // SharedFlow that emits errors.
    // Using backing property to prevent mutable object from external modification.
    private val _errorFlow = MutableSharedFlow<String>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val errorFlow: SharedFlow<String>
        get() = _errorFlow

    init {
        fetchImages()
    }

    /**
     * Fetches images from server.
     */
    private fun fetchImages() {
        // viewModelScope is used to cancel coroutine when ViewModel is cleared.
        viewModelScope.launch {
            apiHelper.getImages(category)
                .flowOn(Dispatchers.IO)
                .catch { error ->
                    if (error.message != null) {
                        _errorFlow.tryEmit(error.message!!)
                    }

                }
                .collect { response ->
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

}