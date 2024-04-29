package com.example.randomfood.domain.repository

import com.example.randomfood.domain.model.Food

/** Manage random food resources */
interface RandomFoodRepository {

    /** @return food at first index */
    suspend fun getRandomFood(): Food
}
