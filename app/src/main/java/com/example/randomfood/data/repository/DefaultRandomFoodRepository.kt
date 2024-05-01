package com.example.randomfood.data.repository

import com.example.randomfood.data.data_source.remote.FreeMealApi
import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.repository.RandomFoodRepository

class DefaultRandomFoodRepository(
    private val api: FreeMealApi,
) : RandomFoodRepository {
    override suspend fun getRandomFood(): Food {
        try {
            val response = api.fetchSingleRandomMeal()
            val data = response.body()
            if (response.isSuccessful && data != null) {
                 return data.toDomain()
            } else {
                throw Exception("Failed!")
            }
        } catch (e: Exception) {
            throw Exception("Failed!")
        }
    }
}
