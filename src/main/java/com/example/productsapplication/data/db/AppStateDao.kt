package com.example.productsapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppStateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAppState(state: AppState)

    @Query("SELECT * FROM app_state WHERE id = 0")
    suspend fun getAppState(): AppState?
}