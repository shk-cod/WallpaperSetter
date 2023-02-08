package com.shkcod.wallpapersetter.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Pixabay API helper class.
 * @param apiService retrofit service.
 */
class PixabayApiHelper(private val apiService: PixabayApiService) {
    /**
     * Makes API request and returns result as [Flow],
     * which is convenient for handling network errors.
     * @param category images category.
     * @return [Flow] with [Response].
     */
    fun getImages(category: String): Flow<Response<PixabayImages>> = flow {
        emit(apiService.getImages(category))
    }
}