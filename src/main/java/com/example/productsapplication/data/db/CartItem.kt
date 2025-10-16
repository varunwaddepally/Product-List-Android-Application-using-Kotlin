package com.example.productsapplication.data.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey val productId: Int
)
