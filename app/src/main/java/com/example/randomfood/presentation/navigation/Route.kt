package com.example.randomfood.presentation.navigation

sealed class Route(val route: String) {
    data object RandomFoodTop : Route("randomFoodTop")
    data object RandomFoodViewer : Route("randomFoodViewer")
    data object FavoriteList : Route("favoriteList")
    data object FavoriteDetail : Route("favoriteDetail/{${FOOD_ID}}") {
        fun passId(foodId: Int) = route.replace("{$FOOD_ID}", foodId.toString())
    }

    companion object {
        const val FOOD_ID = "foodId"
    }
}
