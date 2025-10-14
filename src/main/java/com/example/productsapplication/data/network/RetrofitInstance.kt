package com.example.productsapplication.data.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private const val BASE_URL = "https://fakestoreapi.com/"

        @Volatile
        private var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit {
            return retrofit ?: synchronized(this) {
                retrofit ?: Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
                    .also { retrofit = it }
            }
        }
    }
}