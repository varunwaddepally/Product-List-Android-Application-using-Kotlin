package com.example.productsapplication.repository

import com.example.productsapplication.data.db.AppState
import com.example.productsapplication.data.db.AppStateDao

class AppRepository(private val dao: AppStateDao) {
    suspend fun saveAppState(state: AppState) = dao.saveAppState(state)
    suspend fun getAppState() = dao.getAppState()
}
