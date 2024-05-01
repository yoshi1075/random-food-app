package com.example.randomfood.domain.use_case

import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.repository.RandomFoodRepository

class GetRandomFood(
    private val randomFoodRepository: RandomFoodRepository,
) {
    suspend operator fun invoke(): Result<Food> {
        return try {
            Result.success(randomFoodRepository.getRandomFood())
        } catch(e: Exception) {
            Result.failure(e)
        }
    }
}
