package com.example.randomfood.presentation.screens.random_food_top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.randomfood.presentation.navigation.Route

fun NavGraphBuilder.randomFoodTopScreen(navigateToRandomFoodViewer: () -> Unit) {
    composable(Route.RandomFoodTop.route) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(onClick = { navigateToRandomFoodViewer() }) {
                Text(text = "Start Random Food")
            }
        }
    }
}
