package com.example.productsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.productsapplication.repository.ProductRepository

class ProductListViewModel(private val repo: ProductRepository) : ViewModel() {

    fun products() = liveData {
        val result = repo.getProducts()
        emit(result)
    }
}
