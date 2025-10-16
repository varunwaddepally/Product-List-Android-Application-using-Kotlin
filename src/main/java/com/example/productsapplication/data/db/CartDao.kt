package com.example.productsapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(item: CartItem)

    @Query("DELETE FROM cart WHERE productId = :id")
    suspend fun removeFromCart(id: Int)

    @Query("SELECT * FROM cart")
    suspend fun getAllCartItems(): List<CartItem>

    @Query("SELECT EXISTS(SELECT 1 FROM cart WHERE productId = :id)")
    suspend fun isInCart(id: Int): Boolean
}