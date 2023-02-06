package com.shkcod.wallpapersetter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {
    Surface() {
        CategoriesGrid(viewModel)
    }
}

@Composable
fun CategoriesGrid(
    viewModel: HomeScreenViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(all = 8.dp)
    ) {
        items(viewModel.categoriesData) { item ->
            CategoryCard(
                item.drawable,
                item.text
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryCard(
    drawable: Int,
    text: String
) {
    Box(
        modifier = Modifier
            .clickable {  }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(192.dp).padding(all = 8.dp)
        ) {
            GlideImage(
                model = drawable,
                contentDescription = "",
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            ) {
                it.error(R.drawable.ic_broken_image)
            }
            Text(text)
        }
    }
}