package com.example.randomfood.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomfood.data.data_source.local.FavoriteFoodDatabase.Companion.DATABASE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFoodDao {
    @Query("SELECT * FROM $DATABASE_NAME")
    fun getFavoriteFoodListStream(): Flow<List<FavoriteFoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerFavoriteFood(foodEntity: FavoriteFoodEntity)
}
