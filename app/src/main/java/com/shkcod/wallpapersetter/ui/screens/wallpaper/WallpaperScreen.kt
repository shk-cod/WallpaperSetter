package com.shkcod.wallpapersetter.ui.screens.wallpaper

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.shkcod.wallpapersetter.ui.theme.largePadding

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WallpaperScreen(
    url: String,
    viewModel: WallpaperScreenViewModel = viewModel()
) {
    val context = LocalContext.current

    Box {
        GlideImage(
            model = url,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        ) {
            it
                .load(Uri.parse(url))
                .timeout(5000)
                .placeholder(viewModel.loadingAnimation)
                .error(viewModel.errorImage)
        }

        Button(
            onClick = {
                    setWallpaper(context, url)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = largePadding)
        ) {
            Text("Set wallpaper")
        }
    }

}

fun setWallpaper(context: Context, url: String) {
    Glide.with(context)
        .asBitmap()
        .load(Uri.parse(url))
        .timeout(1000)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(context)
                wallpaperManager.setBitmap(resource)

                Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show()
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
}