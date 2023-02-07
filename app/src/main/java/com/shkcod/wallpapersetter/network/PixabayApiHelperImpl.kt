package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class PixabayApiHelperImpl(private val apiService: PixabayApiService): PixabayApiHelper {
    override fun getImages(category: String): Flow<Response<PixabayImages>> = flow {
        emit(apiService.getImages(category))
    }
}