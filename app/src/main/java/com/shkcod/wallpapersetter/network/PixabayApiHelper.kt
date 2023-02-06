package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow

interface PixabayApiHelper {
    fun getImages(category: String): Flow<PixabayImages>
}