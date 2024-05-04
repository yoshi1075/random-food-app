package com.example.randomfood.data.repository

import com.example.randomfood.data.data_source.local.FavoriteFoodDao
import com.example.randomfood.data.data_source.local.FavoriteFoodEntity
import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.model.FoodDetail
import com.example.randomfood.domain.repository.FavoriteFoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultFavoriteFoodRepository @Inject constructor(
    private val dao: FavoriteFoodDao,
) : FavoriteFoodRepository {
    override suspend fun getFavoriteFoodListStream(): Flow<List<Food>> {
        return try {
            dao.getFavoriteFoodListStream()
                .map {
                    it.map { entity ->
                        entity.toFood()
                    }
                }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getFavoriteFoodDetail(food: Food): FoodDetail {
        TODO("Not yet implemented")
    }

    override suspend fun registerFavoriteFood(food: Food): Boolean {
        return try {
            dao.registerFavoriteFood(FavoriteFoodEntity.fromFood(food))
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteFavoriteFood(food: Food): Boolean {
        TODO("Not yet implemented")
    }
}
