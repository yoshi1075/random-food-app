package com.example.randomfood.presentation.screens.favorite_food_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.randomfood.presentation.navigation.Route

fun NavGraphBuilder.favoriteFoodDetailScreen(popBack: () -> Unit) {
    composable(Route.FavoriteDetail.route) {
        Column {
            Button(onClick = { popBack() }) {
                Text(text = "Favorite Food Detail")
            }
        }
    }
}
