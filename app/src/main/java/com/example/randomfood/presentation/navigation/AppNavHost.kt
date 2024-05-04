package com.example.randomfood.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.randomfood.presentation.screens.favorite_food_detail.favoriteFoodDetailScreen
import com.example.randomfood.presentation.screens.favorite_food_list.favoriteFoodListScreen
import com.example.randomfood.presentation.screens.random_food_top.randomFoodTopScreen
import com.example.randomfood.presentation.screens.random_food_viewer.randomFoodViewerScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavBar(BottomNavItem.values, navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.RandomFoodTop.route,
            modifier = Modifier.padding(it)
        ) {
            randomFoodTopScreen { navController.navigate(Route.RandomFoodViewer.route) }
            randomFoodViewerScreen {
                navController.navigate(Route.RandomFoodViewer.route) {
                    popUpTo(Route.RandomFoodTop.route) {
                        inclusive = false
                    }
                }
            }
            favoriteFoodListScreen { navController.navigate(Route.FavoriteDetail.passId(it)) }
            favoriteFoodDetailScreen { navController.popBackStack() }
        }
    }
}