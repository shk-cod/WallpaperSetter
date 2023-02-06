package com.shkcod.wallpapersetter.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "33106230-b104905cd7ff74ed17e2229af"
private const val BASE_URL = "https://pixabay.com/api/?key=$API_KEY"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PixabayApiService {
    @GET("?per_page=1&orientation=vertical")
    suspend fun getImage(@Query("category") category: String): PixabayImage

    @GET("orientation=vertical")
    suspend fun getImages(@Query("category") category: String): List<PixabayImage>
}

object PixabayApi {
    val retrofitService: PixabayApiService by lazy { retrofit.create(PixabayApiService::class.java) }
}