package com.example.randomfood.domain.repository

import com.example.randomfood.domain.model.Food
import com.example.randomfood.domain.model.FoodDetail
import kotlinx.coroutines.flow.Flow

/** Manage user's favorite food */
interface FavoriteFoodRepository {

    /** @return list of user's favorite food */
    fun getFavoriteFoodListStream(): Flow<List<Food>>

    /** @return food information*/
    suspend fun getFavoriteFoodDetail(food: Food): FoodDetail

    /** Resister favorite food */
    suspend fun registerFavoriteFood(food: Food): Boolean

    /** Delete food from repository */
    suspend fun deleteFavoriteFood(food: Food): Boolean
}
