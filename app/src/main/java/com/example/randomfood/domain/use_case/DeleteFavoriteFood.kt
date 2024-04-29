package com.example.randomfood.domain.use_case

import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.repository.FavoriteFoodRepository

class DeleteFavoriteFood(
    private var favoriteFoodRepository: FavoriteFoodRepository
) {
    suspend operator fun invoke(food: Food): Boolean {
        return try {
            favoriteFoodRepository.deleteFavoriteFood(food)
        } catch (e: Exception) {
            false
        }
    }
}
