package com.example.randomfood.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.randomfood.presentation.navigation.BottomNavBar
import com.example.randomfood.presentation.navigation.BottomNavItem
import com.example.randomfood.presentation.navigation.Route
import com.example.randomfood.presentation.screens.random_food_top.randomFoodTopScreen
import com.example.randomfood.presentation.screens.favorite_food_detail.favoriteFoodDetailScreen
import com.example.randomfood.presentation.screens.favorite_food_list.favoriteFoodListScreen
import com.example.randomfood.presentation.screens.random_food_viewer.randomFoodViewerScreen
import com.example.randomfood.ui.theme.RandomFoodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomFoodTheme {
                val navController = rememberNavController()
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
                        randomFoodViewerScreen { navController.navigate(Route.RandomFoodViewer.route) }
                        favoriteFoodListScreen { navController.navigate(Route.FavoriteDetail.passId(it)) }
                        favoriteFoodDetailScreen { navController.popBackStack() }
                    }
                }
            }
        }
    }
}
