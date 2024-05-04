package com.example.randomfood.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface FavoriteFoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerFavoriteFood(foodEntity: FavoriteFoodEntity)
}
