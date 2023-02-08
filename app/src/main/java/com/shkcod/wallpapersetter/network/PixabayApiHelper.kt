package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class PixabayApiHelper(private val apiService: PixabayApiService) {
    fun getImages(category: String): Flow<Response<PixabayImages>> = flow {
        emit(apiService.getImages(category))
    }
}