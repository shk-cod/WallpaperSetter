package com.shkcod.wallpapersetter.ui.screens.home

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.shkcod.wallpapersetter.R
import com.shkcod.wallpapersetter.navigation.Screen
import com.shkcod.wallpapersetter.ui.theme.gridCellSize
import com.shkcod.wallpapersetter.ui.theme.roundedCornerSize
import com.shkcod.wallpapersetter.ui.theme.smallPadding

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {
    CategoriesGrid(navController, viewModel)
}

@Composable
fun CategoriesGrid(
    navController: NavController,
    viewModel: HomeScreenViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(gridCellSize),
        contentPadding = PaddingValues(vertical = smallPadding),
        verticalArrangement = Arrangement.spacedBy(smallPadding)
    ) {
        items(viewModel.categoriesList) { item ->
            CategoryCard(
                navController,
                item.drawable,
                item.text,
                item.category.value
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryCard(
    navController: NavController,
    drawable: Int,
    text: Int,
    category: String
) {
    Box(
        modifier = Modifier
            .clickable { navController.navigate("${Screen.CategoryImages.route}/$category") }
    ) {
        GlideImage(
            model = drawable,
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(roundedCornerSize))
                .align(Alignment.Center)
        ) {
            it.error(R.drawable.ic_broken_image)
        }

        Text(
            stringResource(text),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(smallPadding)
                .background(Color.Black.copy(alpha = 0.5f))
        )
    }
}