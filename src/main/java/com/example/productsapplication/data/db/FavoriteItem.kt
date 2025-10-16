package com.example.productsapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteItem(
    @PrimaryKey val productId: Int
)