package com.shkcod.wallpapersetter.network

data class PixabayImage(
    val id: String,
    val webformatURL: String,
    val largeImageURL: String
)

data class PixabayImages(
    val hits: List<PixabayImage>
)