package com.example.randomfood.presentation.screens.random_food_top

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.randomfood.presentation.navigation.Route

fun NavGraphBuilder.randomFoodTopScreen(navigateToRandomFoodViewer: () -> Unit) {
    composable(Route.RandomFoodTop.route) {
        Column {
            Button(onClick = { navigateToRandomFoodViewer() }) {
                Text(text = "Start Random Food")
            }
        }
    }
}
