package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PixabayApiHelperImpl(private val apiService: PixabayApiService): PixabayApiHelper {
    override fun getImage(category: String): Flow<PixabayImage> = flow {
        emit(apiService.getImage(category))
    }

    override fun getImages(category: String): Flow<List<PixabayImage>> = flow {
        emit(apiService.getImages(category))
    }
}