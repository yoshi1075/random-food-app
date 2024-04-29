package com.example.randomfood.domain.use_case

import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.model.FoodDetail
import com.example.randomfood.domain.repository.FavoriteFoodRepository

class GetFavoriteFoodDetail(
    private var favoriteFoodRepository: FavoriteFoodRepository
) {
    suspend operator fun invoke(food: Food): Result<FoodDetail> {
        return try {
            Result.success(favoriteFoodRepository.getFavoriteFoodDetail(food))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
