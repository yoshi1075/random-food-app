package com.example.randomfood.data.data_source.remote.model

import com.example.randomfood.domain.model.Food

data class SingleRandomMealResponse(val meals: List<Meal>) {
    fun toDomain(): Food {
        return meals.map {
            Food(it.idMeal.toInt(), it.strMeal, it.strMealThumb)
        }.first()
    }
}
