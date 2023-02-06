package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow

interface PixabayApiHelper {
    fun getImage(category: String): Flow<PixabayImage>

    fun getImages(category: String): Flow<List<PixabayImage>>
}