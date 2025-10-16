package com.example.productsapplication.repository

import com.example.productsapplication.data.db.CartDao
import com.example.productsapplication.data.db.CartItem

class CartRepository(private val dao: CartDao) {
    suspend fun addToCart(item: CartItem) = dao.addToCart(item)
    suspend fun removeFromCart(id: Int) = dao.removeFromCart(id)
    suspend fun getAllCartItems() = dao.getAllCartItems()
    suspend fun isInCart(id: Int) = dao.isInCart(id)
}