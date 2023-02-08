package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PixabayApiHelper {
    fun getImages(category: String): Flow<Response<PixabayImages>>
}