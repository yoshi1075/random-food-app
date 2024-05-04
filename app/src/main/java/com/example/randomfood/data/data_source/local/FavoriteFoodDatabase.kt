package com.example.randomfood.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteFoodEntity::class], version = 1)
abstract class FavoriteFoodDatabase : RoomDatabase() {
    abstract val dao: FavoriteFoodDao

    companion object {
        const val DATABASE_NAME = "favorite_food"
    }
}
