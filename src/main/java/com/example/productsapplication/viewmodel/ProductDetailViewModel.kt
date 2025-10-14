package com.example.productsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.productsapplication.repository.ProductRepository

class ProductDetailViewModel(private val repo: ProductRepository) : ViewModel() {
    fun getProductById(id: Int) = liveData {
        emit(repo.getProductById(id))
    }
}