package com.example.randomfood.domain.use_case

import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.repository.FavoriteFoodRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteFoodListStreamUseCase(
    private var favoriteFoodRepository: FavoriteFoodRepository
) {
    operator fun invoke(): Result<Flow<List<Food>>> {
        return try {
            Result.success(favoriteFoodRepository.getFavoriteFoodListStream())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
