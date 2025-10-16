package com.example.productsapplication.repository

import android.content.Context
import com.example.productsapplication.data.model.Product
import com.example.productsapplication.data.network.ApiService
import com.example.productsapplication.data.network.RetrofitInstance
import com.example.productsapplication.utils.NetworkUtils


class ProductRepository(private val context: Context) {

    private val api: ApiService = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)

    private fun isInternetAvailable(): Boolean {
        return NetworkUtils.getInstance(context).isInternetAvailable()
    }

    suspend fun getProducts(): Result<List<Product>> {
        return try {
            if (!isInternetAvailable()) return Result.failure(Exception("No internet connection"))
            val response = api.getProducts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: Int): Result<Product> {
        return try {
            if (!isInternetAvailable()) return Result.failure(Exception("No internet connection"))
            val response = api.getProductById(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}