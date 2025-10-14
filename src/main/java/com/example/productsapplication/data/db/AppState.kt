package com.example.productsapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_state")
data class AppState(@PrimaryKey val id: Int = 0,
                    val lastScreen: String,
                    val productId: Int? = null)
