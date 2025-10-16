package com.example.productsapplication.repository

import com.example.productsapplication.data.db.FavoriteItem
import com.example.productsapplication.data.db.FavoritesDao

class FavoritesRepository(private val dao: FavoritesDao) {
    suspend fun addToFavorites(item: FavoriteItem) = dao.addToFavorites(item)
    suspend fun removeFromFavorites(id: Int) = dao.removeFromFavorites(id)
    suspend fun getAllFavorites() = dao.getAllFavorites()
    suspend fun isInFavorites(id: Int) = dao.isInFavorites(id)
}