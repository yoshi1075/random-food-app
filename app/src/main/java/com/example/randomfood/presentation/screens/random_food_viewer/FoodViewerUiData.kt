package com.example.randomfood.presentation.screens.random_food_viewer

import com.example.randomfood.domain.model.Food

data class FoodViewerUiData(
    val id: Int,
    val name: String,
    val url: String,
) {
    companion object {
        fun fromDomainFood(food: Food): FoodViewerUiData {
            return FoodViewerUiData(food.id, food.name, food.imageUrl)
        }
    }
}
