package com.shkcod.wallpapersetter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun CategoryImagesScreen(
    navController: NavController,
    category: String,
    viewModel: CategoryImagesViewModel = viewModel(factory = CategoryImagesViewModelFactory(category))
) {
    val error by viewModel.errorFlow.collectAsState(initial = "")

    Surface {
        ImagesGrid(navController, viewModel)
        error?.let { Text(it) }
    }
}

@Composable
fun ImagesGrid(
    navController: NavController,
    viewModel: CategoryImagesViewModel
) {
    val list by viewModel.imagesFlow.collectAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(all = 8.dp)
    ) {
        items(list) { item ->
            ImageCard(
                navController,
                item.webformatURL
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageCard(
    navController: NavController,
    url: String,
) {
    val imgUri = url.toUri().buildUpon().scheme("https").build()

    Box(
        modifier = Modifier
            .height(192.dp)
            .padding(all = 8.dp)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        GlideImage(
            model = url,
            contentDescription = "",
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        ) {
            it
                .load(imgUri)
                .error(R.drawable.ic_broken_image)
        }
    }
}