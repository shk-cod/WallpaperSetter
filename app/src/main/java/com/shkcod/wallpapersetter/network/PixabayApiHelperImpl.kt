package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PixabayApiHelperImpl(private val apiService: PixabayApiService): PixabayApiHelper {
    override fun getImages(category: String): Flow<PixabayImages> = flow {
        emit(apiService.getImages(category))
    }
}