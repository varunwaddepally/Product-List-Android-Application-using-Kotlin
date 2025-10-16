package com.example.productsapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppState::class, CartItem::class, FavoriteItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appStateDao(): AppStateDao
    abstract fun cartDao(): CartDao
    abstract fun favoritesDao(): FavoritesDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}