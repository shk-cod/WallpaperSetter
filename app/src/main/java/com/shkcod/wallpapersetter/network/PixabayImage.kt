package com.shkcod.wallpapersetter.network

/**
 * Represents image item, parsed from JSON.
 * @param webformatURL URL for middle-size image.
 * @param largeImageURL URL for large-size image.
 */
data class PixabayImage(
    val webformatURL: String,
    val largeImageURL: String
)

/**
 * Represents array of image items.
 * @param hits List of [PixabayImage] items.
 */
data class PixabayImages(
    val hits: List<PixabayImage>
)