package com.example.randomfood.data.data_source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomfood.domain.model.Food

@Entity(tableName = "favorite_food")
data class FavoriteFoodEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
) {
    companion object {
        fun fromFood(food: Food): FavoriteFoodEntity {
            return FavoriteFoodEntity(food.id, food.name, food.imageUrl)
        }
    }
}
