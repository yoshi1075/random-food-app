package com.example.randomfood.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
) {
    data object Random : BottomNavItem(Route.RandomFoodTop.route, Icons.Default.Search)
    data object Favorite : BottomNavItem(Route.FavoriteList.route, Icons.Default.Favorite)

    companion object {
        val values = listOf(Random, Favorite)
    }
}