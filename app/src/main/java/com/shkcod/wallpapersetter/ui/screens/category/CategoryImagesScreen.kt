package com.shkcod.wallpapersetter.ui.screens.category

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.shkcod.wallpapersetter.R
import com.shkcod.wallpapersetter.navigation.Screen
import com.shkcod.wallpapersetter.ui.theme.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CategoryImagesScreen(
    navController: NavController,
    category: String,
    viewModel: CategoryImagesViewModel = viewModel(factory = CategoryImagesViewModelFactory(category))
) {
    val context = LocalContext.current
    var isError by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.errorFlow.collect { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()
            isError = true
        }
    }

    Surface {
        ImagesGrid(navController, viewModel, isError)
    }
}

@Composable
fun ImagesGrid(
    navController: NavController,
    viewModel: CategoryImagesViewModel,
    isError: Boolean
) {
    val list by viewModel.imagesFlow.collectAsState(initial = listOf())

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (list.isEmpty() && !isError) {
            LinearProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (isError) {
            Icon(
                painter = painterResource(R.drawable.ic_broken_image),
                contentDescription = "",
                modifier = Modifier
                    .size(splashIconSize)
                    .align(Alignment.Center)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(gridCellSize),
            contentPadding = PaddingValues(vertical = smallPadding),
            verticalArrangement = Arrangement.spacedBy(smallPadding),
        ) {
            items(list) { item ->
                ImageCard(
                    navController,
                    item.webformatURL,
                    item.largeImageURL
                )
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageCard(
    navController: NavController,
    webformatUrl: String,
    largeImageUrl: String
) {
    Box(
        modifier = Modifier
            .height(gridCellSize)
            .clickable
            {
                val encodedUrl = URLEncoder.encode(largeImageUrl, StandardCharsets.UTF_8.toString())
                navController.navigate("${Screen.Wallpaper.route}/$encodedUrl")
            },
        contentAlignment = Alignment.Center
    ) {
        GlideImage(
            model = webformatUrl,
            contentDescription = "",
            modifier = Modifier.clip(RoundedCornerShape(roundedCornerSize))
        ) {
            it
                .load(Uri.parse(webformatUrl))
                .placeholder(R.drawable.loading_animation_large)
                .error(R.drawable.ic_broken_image)
        }
    }
}