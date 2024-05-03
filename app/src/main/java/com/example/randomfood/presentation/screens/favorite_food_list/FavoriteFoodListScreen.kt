package com.example.randomfood.presentation.screens.favorite_food_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.randomfood.presentation.navigation.Route

fun NavGraphBuilder.favoriteFoodListScreen(navigateToFavoriteFoodDetail: (foodId: Int) -> Unit) {
    composable(Route.FavoriteList.route) {
        Column {
            Button(onClick = { navigateToFavoriteFoodDetail(1) }) {
                Text(text = "Favorite Food List")
            }
        }
    }
}
