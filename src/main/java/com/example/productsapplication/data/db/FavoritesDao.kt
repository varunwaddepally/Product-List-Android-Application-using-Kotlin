package com.example.productsapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorites(item: FavoriteItem)

    @Query("DELETE FROM favorites WHERE productId = :id")
    suspend fun removeFromFavorites(id: Int)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteItem>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE productId = :id)")
    suspend fun isInFavorites(id: Int): Boolean
}