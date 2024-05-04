package com.example.randomfood.domain.use_case

import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.repository.FavoriteFoodRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteFoodListStreamUseCase(
    private var favoriteFoodRepository: FavoriteFoodRepository
) {
    suspend operator fun invoke(): Flow<List<Food>> {
        return favoriteFoodRepository.getFavoriteFoodListStream()
    }
}
